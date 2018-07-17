////////////////////////////////////////////////////////////////////////////////
//	Title		:	IdmRepImpl.java
//
//	Description	:	Classes which handle connections with IDM Repository.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Level;

import com.towertechnology.common.environment.EnvironmentClassServer;
import com.towertechnology.common.errorhandling.TTCommonErrors;
import com.towertechnology.common.errorhandling.TTException;
import com.towertechnology.common.errorhandling.TTRepositoryErrors;
import com.vignette.idm.common.ConfigKeys;
import com.vignette.idm.common.Defaults;
import com.vignette.idm.common.IDMConfiguration;
import com.vignette.idm.common.StringBufferS;

import IDMCONTEXT.NAMING_CONTEXT;
import OOHONCHO.NoSuchPOAInstance;
import OOHONCHO.NoSuchPOAtypeException;
import OOHONCHO.idmservers;
import OOHONCHO.idmserversHelper;
import TTCOMMON.NULL_VALUE;
import TTEXCEPTIONS.AuthenticationException;
import TTEXCEPTIONS.GenericException;
import TTEXCEPTIONS.TTlibException;
import TTSECURITY.BadLicenseTypeException;
import TTSECURITY.CredentialException;
import TTSECURITY.ExpiredException;
import TTSECURITY.NoAvailableException;

public class IdmRepImpl {
	private static final String START = "Start: ";
	private static final String COMPLETE = "Complete: ";
	public int i = 1;

	private static java.util.logging.Logger mLogger = java.util.logging.Logger
			.getLogger("com.vignette.idm.services.IdmRepImpl");

	private UserInfo mUserInfo;

	public static void main(String[] args) {
		try {
			IdmRepImpl repository = new IdmRepImpl();
			repository.cleanupTickets();
		} catch (TTException e) {
			System.out.println("Cleaning up of expired licenses failed");
		}

	}

	public String getAppRefString() throws TTException {
		if (mUserInfo == null) {
			return null;
		}
		if (mUserInfo.mUserId == null) {
			getUserInfo();
		}
		if (mUserInfo.mApplicationRef == null) {
			return "UNKNOWN APPREF";
		}
		return mUserInfo.mApplicationRef;
	}

	public String getUserId() throws TTException {
		if (mUserInfo == null) {
			return null;
		}
		if (mUserInfo.mUserId == null) {
			getUserInfo();
		}
		return mUserInfo.mUserId;
	}

	public IdmRepImpl(String aHostId, String aUserId, String aPassword, Properties aProps) throws TTException {
		String sessionId = null;
		String clientIP = null;
		String licenceType = null;
		String appRef = null;
		String newPassword = null;
		if (aProps != null) {
			sessionId = aProps.getProperty(ConfigKeys.SESSION_ID);

			clientIP = aProps.getProperty(ConfigKeys.CLIENT_IP);
			licenceType = aProps.getProperty(ConfigKeys.LICENCE_TYPE);
			appRef = aProps.getProperty(ConfigKeys.APPLICATION_REF);
			newPassword = aProps.getProperty(ConfigKeys.NEW_PASSWORD);

		}

		if ((aHostId == null) || (aHostId.trim().equals(""))) {
			aHostId = Defaults.DefaultHostId;
		}

		login(aHostId, aUserId, aPassword, newPassword, sessionId, clientIP, licenceType, appRef);
	}

	public IdmRepImpl(String anIdmTicket) throws TTException {

		System.out.println(anIdmTicket);
		mUserInfo = UserInfo.getUser(anIdmTicket);
		if (mUserInfo == null) {
			String msg = "Ticket not found!";
			mLogger.severe(msg);
			throw new TTException(msg, TTCommonErrors.LICENSE_INVALID);
		}
		System.out.println("end");
	}

	private IdmRepImpl() throws TTException {
		String sessionId = ConfigKeys.SESSION_ID;
		String clientIP = ConfigKeys.CLIENT_IP;
		String licenceType = ConfigKeys.LICENCE_TYPE;
		mUserInfo = new UserInfo("default", "", sessionId, clientIP, licenceType);
	}

	private void cleanup() throws TTException {
		try {
			// Removing the cache information of the user when he/she logs out.
			DoctypeCache dtCache = DoctypeCache.getInstance();
			RendererCache renCache = RendererCache.getInstance();
			TicketMap tickMap = TicketMap.getInstance();

			String userTicket = getTicket();
			if (userTicket != null) {
				dtCache.removeUser(userTicket);
				renCache.cleanupUserCache(userTicket);
				String serverRef = tickMap.getValue(userTicket);
				if (serverRef != null) {
					Queue qObj = new Queue(this);
					qObj.unregisterSession(serverRef);
					tickMap.removeTicketMapping(userTicket);
				}
			}
		} catch (TTException e) {
			throw e;
		} catch (Exception e) {
			mLogger.severe(e.toString());
			throw new TTException(e.toString(), TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}
	}

	public void close() throws TTException {
		if (mUserInfo == null) {
			return;
		}

		// Do some cleaning up before logging out.
		cleanup();
		logout();

		mUserInfo = null;
	}

	public String getIdmTicket() {
		if (mUserInfo != null) {
			return mUserInfo.mIdmTicket;
		}
		return null;
	}

	private org.omg.CORBA.ORB getOrb() {
		if (mUserInfo == null) {
			return null;
		}
		return mUserInfo.mHost.getOrb();
	}

	public String getHostName() throws TTException {
		return mUserInfo.mHost.getHostName();
	}

	public String getTicket() {
		if (mUserInfo != null) {
			return mUserInfo.mTicket;
		}
		return null;
	}

	public String getHostId() {
		if (mUserInfo == null) {
			return null;
		}
		return mUserInfo.mHost.getHostId();
	}

	public String getDataSrcName() throws TTException {
		return mUserInfo.mHost.getDataSrcName();
	}

	private POAinfo getPOAinfo(String aPOApostfix, String aObjId, String aInstanceId, String aHostId,
			String aHelperClassName) throws TTException {
		String honchoPoaName = null;
		try {
			String hostName;
			if ((aHostId != null) && (aHostId.length() > 0) && (aHostId != getHostId())) {
				IdmHostInfo host = IdmHostInfo.getHost(aHostId);
				hostName = host.getHostName();
				honchoPoaName = hostName + mUserInfo.mHost.getSpecialName() + OOHONCHO.POA_NAME.value;
			} else {
				hostName = getHostName();
				honchoPoaName = hostName + mUserInfo.mHost.getSpecialName() + OOHONCHO.POA_NAME.value;
			}

			idmservers serverRef = null;
			if ((mUserInfo.mHost.mHonchoServant != null)
					&& ((aHostId == null) || (aHostId.trim().equals("")) || (aHostId.equals(getHostId())))) {
				serverRef = mUserInfo.mHost.mHonchoServant;
			} else {
				serverRef = idmserversHelper
						.narrow(getRemoteObject(honchoPoaName, OOHONCHO.OBJECT_ID.value, "", aHostId));
			}
			org.omg.CORBA.StringHolder refh = new org.omg.CORBA.StringHolder(aInstanceId);
			String poaName = serverRef.getPOAname(aPOApostfix, refh);

			// Cache the reference to the oohoncho service for the primary host.
			if ((mUserInfo.mHost.mHonchoServant == null)
					&& ((aHostId == null) || (aHostId.length() < 1) || (aHostId.equals(getHostId())))) {
				mUserInfo.mHost.mHonchoServant = serverRef;
			}

			// Get cached object if available.
			POAinfo returnVal = POAinfo.getPOAinfo(poaName, aObjId, aHelperClassName);

			if (returnVal == null) {
				returnVal = new POAinfo(poaName, aObjId, refh.value, serverRef, aHelperClassName);
			}

			return returnVal;
		} catch (NoSuchPOAInstance e) {
			String msg = "Could not find server instance " + aPOApostfix;
			mLogger.severe(msg);
			throw new TTException(msg, TTRepositoryErrors.SERVER_INSTANCE_MISSING);
		} catch (NoSuchPOAtypeException e) {
			String msg = honchoPoaName + "Could not find service type " + aInstanceId + " of type " + aPOApostfix;
			mLogger.severe(msg);
			throw new TTException(msg, TTRepositoryErrors.SERVICE_UNAVAILABLE);
		} catch (GenericException e) {
			if ((aHostId == null) || (aHostId.equals(getHostId()))) {
				mUserInfo.mHost.mHonchoServant = null;
			}
			mLogger.severe(e.errorDescription);
			throw new TTException(e.errorDescription, TTCommonErrors.LOW_LEVEL_EXCEPTION);
		} catch (TTException e) {
			if ((aHostId == null) || (aHostId.equals(getHostId()))) {
				mUserInfo.mHost.mHonchoServant = null;
			}
			throw e;
		} catch (Exception e) {
			if ((aHostId == null) || (aHostId.equals(getHostId()))) {
				mUserInfo.mHost.mHonchoServant = null;
			}
			String msg = "Could not connect to " + honchoPoaName + " (" + e.toString() + ")";
			mLogger.severe(msg);
			throw new TTException(msg, TTCommonErrors.CONNECTION_EXCEPTION);
		}
	}

	private void releaseRequest(POAinfo aPinfo) {
		try {
			int serverCount = aPinfo.getHoncho().releasePOA(aPinfo.getReference());
			POAinfo.checkCache(serverCount);
		} catch (Exception e) {
			mLogger.severe("Request failed for " + aPinfo.getPOAname() + " with exception " + e.toString());
		}
	}

	private org.omg.CORBA.Object getServant(String aHelperClassName, String aPoaName, String aObjId, String aPoaRef,
			String anOtherHostId) throws TTException {
		try {
			org.omg.CORBA.Object serverRef = null;
			org.omg.CORBA.Object rawServerRef = null;
			rawServerRef = getRemoteObject(aPoaName, aObjId, aPoaRef, anOtherHostId);
			if (rawServerRef == null) {
				String msg = "Get remote method produced null result";
				mLogger.severe(msg);
				throw new TTException(msg, TTCommonErrors.CONNECTION_EXCEPTION);
			}

			Class helperClass = Class.forName(aHelperClassName);
			Class formalArgs[] = new Class[] { org.omg.CORBA.Object.class };

			Method narrowMethod = helperClass.getMethod("narrow", formalArgs);
			Object args[] = new Object[] { rawServerRef };

			// Invoke the narrow method on the helper class.
			serverRef = (org.omg.CORBA.Object) narrowMethod.invoke(helperClass.newInstance(), args);
			if (serverRef == null) {
				String msg = "Narrow method produced null result";
				mLogger.severe(msg);
				throw new TTException(msg, TTCommonErrors.CONNECTION_EXCEPTION);
			}
			return serverRef;
		} catch (InvocationTargetException e) {
			String msg = "Caught exception trying to narrow to " + aPoaName + " (" + e.getTargetException().getMessage()
					+ ")";
			mLogger.severe(msg);
			throw new TTException(msg, TTCommonErrors.CONNECTION_EXCEPTION);
		} catch (TTException e) {
			throw e;
		} catch (Exception e) {
			String msg = "Exception obtaining/invoking narrow method for helper class " + aHelperClassName + " ("
					+ e.toString() + ")";
			mLogger.severe(msg);
			throw new TTException(msg, TTCommonErrors.CONNECTION_EXCEPTION);
		}
	}

	public Object doRemoteMethod(String aHelperClassName, String aPoaPostfix, String anObjId,
			String[] aSpecificInstanceId, String anOtherHostId, String aMethodName, Class[] aFormalArgs,
			Object[] anArgs) throws TTException, Exception {
		boolean success = false;

		// Get the name of the most available poa and register a request on it.
		String instanceId = null;
		if ((aSpecificInstanceId != null) && (aSpecificInstanceId[0] != null)) {
			instanceId = aSpecificInstanceId[0];
		} else {
			instanceId = "";
		}
		POAinfo pinfo = null;
		int retrycount = 0;
		do {
			try {
				pinfo = getPOAinfo(aPoaPostfix, anObjId, instanceId, anOtherHostId, aHelperClassName);
				retrycount = 0;
			} catch (TTException e) {
				if (++retrycount > Defaults.CorbaRetrys) {
					throw e;
				}
			}
		} while (retrycount > 0);
		String poaName = pinfo.getPOAname();
		String poaRef = pinfo.getReference();
		org.omg.CORBA.Object serverRef = pinfo.getServant();
		Method method = null;
		Object returnVal = null;

		try {
			if (serverRef == null) {
				serverRef = getServant(aHelperClassName, poaName, anObjId, poaRef, anOtherHostId);
			}
			pinfo.setServant(serverRef);
			pinfo.cachePOA();
			// Return the specific object reference.
			if (aSpecificInstanceId != null) {
				aSpecificInstanceId[0] = poaRef;
			}
			// Get the method of the class.
			method = serverRef.getClass().getMethod(aMethodName, aFormalArgs);
			// Invoke the method.
			returnVal = method.invoke(serverRef, anArgs);
			success = true;
		} catch (NoSuchMethodException ex) {
			StringBuffer msg = new StringBuffer("Could not find method " + aMethodName + "(");
			for (int i = 0; i < aFormalArgs.length; i++) {
				msg.append(aFormalArgs[i].getName());
				if (i < (aFormalArgs.length - 1)) {
					msg.append(", ");
				} else {
					msg.append(")");
				}
			}
			mLogger.severe(msg.toString());
			throw new TTException(msg.toString(), TTCommonErrors.LOW_LEVEL_EXCEPTION);
		} catch (InvocationTargetException e) {
			throw (Exception) e.getTargetException();
		} catch (TTException e) {
			throw e;
		} catch (Exception e) {
			String msg = "Remote invocation of " + aMethodName + " on " + poaName + " failed";
			mLogger.severe(msg);
			throw new TTException(msg, TTCommonErrors.CONNECTION_EXCEPTION);
		} finally {
			if (!success) {
				pinfo.deleteCache();
			}

			// Unregister request.
			releaseRequest(pinfo);
		}
		return returnVal;
	}

	private org.omg.CORBA.Object getObject(String aPoaName, String anObjId,
			org.omg.CosNaming.NamingContext aRootContext) throws TTException {
		try {
			org.omg.CosNaming.NameComponent[] objectName = new org.omg.CosNaming.NameComponent[2];
			objectName[0] = new org.omg.CosNaming.NameComponent(NAMING_CONTEXT.value, "");
			objectName[1] = new org.omg.CosNaming.NameComponent(anObjId, aPoaName);
			org.omg.CORBA.Object obj = aRootContext.resolve(objectName);
			if (obj == null) {
				String msg = "Could not resolve name " + anObjId + " " + aPoaName;
				mLogger.severe(msg);
				throw new TTException(msg, TTCommonErrors.NULL_REFERENCE);
			}
			return obj;
		} catch (TTException e) {
			throw e;
		} catch (Exception e) {
			String msg = "Could not resolve name " + anObjId + " " + aPoaName;
			mLogger.severe(msg);
			throw new TTException(msg, TTCommonErrors.CONNECTION_EXCEPTION);
		}
	}

	private org.omg.CORBA.Object getRemoteObject(String aPoaName, String anObjId, String aSpecificInstanceId,
			String anAlternateHostId) throws TTException {
		org.omg.CORBA.Object remoteObj = null;
		IdmHostInfo host = mUserInfo.mHost;
		org.omg.CORBA.ORB orb;
		org.omg.CosNaming.NamingContext rootContext = null;
		if ((anAlternateHostId != null) && (anAlternateHostId.length() > 0)) {
			host = IdmHostInfo.getHost(anAlternateHostId);
			orb = host.getOrb();
		} else {
			orb = getOrb();
		}

		int retryCount = 0;
		while (true) {
			try {
				rootContext = host.getNameService();
				remoteObj = getObject(aPoaName, anObjId, rootContext);
				break;
			} catch (TTException e) {
				if (++retryCount > Defaults.CorbaRetrys) {
					throw e;
				}
				try {
					Thread.sleep(500);
				} catch (Exception e2) {
					// Do nothing.
				}
			} finally {
				if (remoteObj == null) {
					host.resetNameService(rootContext);
					host.setOrb();
				}
			}
		}
		return remoteObj;
	}

	public void printState(java.io.PrintStream aStream) throws TTException {
		aStream.println("IdmRepImpl - state");
		if (mUserInfo == null) {
			aStream.println("mUserInfo is null");
		} else {
			if (mUserInfo.mHost == null) {
				aStream.println("mUserInfo.mHost is null");
			} else {
				getUserInfo();
				aStream.println("mHostId \"" + mUserInfo.mHost.getHostId() + "\"");
				aStream.println("mHostName \"" + getHostName() + "\"");
				aStream.print("mOrb is ");
				if (getOrb() == null) {
					aStream.println("null");
				} else {
					aStream.println("not null");
				}
			}
			aStream.println("mUserId = \"" + mUserInfo.mUserId + "\"");
			aStream.println("mSessionId = \"" + mUserInfo.mSessionId + "\"");
			aStream.println("mTicket = \"" + mUserInfo.mTicket + "\"");
			aStream.println("mIdmTicket = \"" + mUserInfo.mIdmTicket + "\"");
			aStream.println("mApplicationRef = \"" + getAppRefString() + "\"");
			aStream.println("mLoginTime = \"" + mUserInfo.mLoginTime + "\"");
			aStream.println("mLastAccessTime = \"" + mUserInfo.mLastAccessTime + "\"");
			aStream.println("mTimeoutTime = \"" + mUserInfo.mTimeoutTime + "\"");
		}
		aStream.println("There are/is " + IdmHostInfo.count() + " host(s).");
	}

	private void login(String aHostId, String aUserId, String aPassword, String aNewPassword, String aSessionId,
			String aClientIP, String aLicenceType, String anAppRef) throws TTException {
		mUserInfo = new UserInfo(aHostId, aUserId, aSessionId, aClientIP, aLicenceType);
		if (anAppRef != null) {
			mUserInfo.mApplicationRef = anAppRef;
		}

		if ((aUserId == null) || (aUserId.trim().equals("")) || (aUserId.equals(UserInfo.ANONYMOUS))) {
			mUserInfo.mIdmTicket = UserInfo.ANONYMOUS;
		} else {
			// Now log in to idm server and get a ticket.
			mUserInfo.mIdmTicket = getIdmTicket(aUserId, aPassword, aNewPassword, mUserInfo.mSessionId);
		}
		mUserInfo.mTicket = String.valueOf(aHostId.length()) + ":" + aHostId + mUserInfo.mIdmTicket;
	}

	public boolean isValid() throws TTException {
		try {
			mLogger.info(START);

			Class[] formalArgs = new Class[] { String.class, };

			Object[] args = new Object[] { getIdmTicket() };

			Boolean result = (Boolean) doRemoteMethod("TTSECURITY.LicenceHelper", TTSECURITY.POA_NAME.value,
					TTSECURITY.OBJECT_ID.value, null, "", "isValid", formalArgs, args);

			if (mLogger.isLoggable(Level.INFO)) {
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("result", result.booleanValue(), "");
				mLogger.info(msg.toString());
			}

			return result.booleanValue();
		} catch (AuthenticationException e) {
			mLogger.severe(e.errorDescription);
			throw new TTException(e.errorDescription, TTCommonErrors.LICENSE_INVALID);
		} catch (TTlibException e) {
			mLogger.severe(e.errorDescription);
			String[] ctlerrno = new String[] { Integer.toString(e.errorCode), e.errorCodeStr };
			throw new TTException(e.errorDescription, TTCommonErrors.LOW_LEVEL_EXCEPTION, ctlerrno);
		} catch (GenericException e) {
			mLogger.severe(e.errorDescription);
			throw new TTException(e.errorDescription, TTCommonErrors.LOW_LEVEL_EXCEPTION);
		} catch (TTException e) {
			throw e;
		} catch (Exception e) {
			mLogger.severe(e.toString());
			throw new TTException(e.toString(), TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}
	}

	private void logout() throws TTException {
		try {
			mLogger.info(START);

			Class[] formalArgs = new Class[] { String.class, };

			Object[] args = new Object[] { getIdmTicket() };

			doRemoteMethod("TTSECURITY.LicenceHelper", TTSECURITY.POA_NAME.value, TTSECURITY.OBJECT_ID.value, null, "",
					"logout", formalArgs, args);

			mLogger.info(COMPLETE);
		} catch (AuthenticationException e) {
			mLogger.severe(e.errorDescription);
			throw new TTException(e.errorDescription, TTCommonErrors.LICENSE_INVALID);
		} catch (TTlibException e) {
			mLogger.severe(e.errorDescription);
			String[] ctlerrno = new String[] { Integer.toString(e.errorCode), e.errorCodeStr };
			throw new TTException(e.errorDescription, TTCommonErrors.LOW_LEVEL_EXCEPTION, ctlerrno);
		} catch (GenericException e) {
			mLogger.severe(e.errorDescription);
			throw new TTException(e.errorDescription, TTCommonErrors.LOW_LEVEL_EXCEPTION);
		} catch (TTException e) {
			throw e;
		} catch (Exception e) {
			mLogger.severe(e.toString());
			throw new TTException(e.toString(), TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}
	}

	private void getUserInfo() throws TTException {
		try {
			mLogger.info(START);

			Class[] formalArgs = new Class[] { String.class };

			Object[] args = new Object[] { mUserInfo.mIdmTicket };

			TTSECURITY.UserInfo uInfo = (TTSECURITY.UserInfo) doRemoteMethod("TTSECURITY.LicenceHelper",
					TTSECURITY.POA_NAME.value, TTSECURITY.OBJECT_ID.value, null, "", "getUserInfo", formalArgs, args);

			mUserInfo.mUserId = uInfo.mUserID;
			mUserInfo.mSessionId = uInfo.mSessionID;
			mUserInfo.mApplicationRef = uInfo.mForLog;
			mUserInfo.mLoginTime = uInfo.mLoginTime;
			mUserInfo.mLastAccessTime = uInfo.mLastAccessTime;
			mUserInfo.mTimeoutTime = uInfo.mTimeoutTime;

			if (mLogger.isLoggable(Level.INFO)) {
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("mUserId", mUserInfo.mUserId, ", ");
				msg.append("mSessionId", mUserInfo.mSessionId, ", ");
				msg.append("mApplicationRef", mUserInfo.mApplicationRef, ", ");
				msg.append("mLoginTime", mUserInfo.mLoginTime, ", ");
				msg.append("mLastAccessTime", mUserInfo.mLastAccessTime, ", ");
				msg.append("mTimeoutTime", mUserInfo.mTimeoutTime, "");
				mLogger.info(msg.toString());
			}

			return;
		} catch (AuthenticationException e) {
			mLogger.severe(e.errorDescription);
			throw new TTException(e.errorDescription, TTCommonErrors.LICENSE_INVALID);
		} catch (TTlibException e) {
			mLogger.severe(e.errorDescription);
			String[] ctlerrno = new String[] { Integer.toString(e.errorCode), e.errorCodeStr };
			throw new TTException(e.errorDescription, TTCommonErrors.LOW_LEVEL_EXCEPTION, ctlerrno);
		} catch (GenericException e) {
			mLogger.severe(e.errorDescription);
			throw new TTException(e.errorDescription, TTCommonErrors.LOW_LEVEL_EXCEPTION);
		} catch (TTException e) {
			throw e;
		} catch (Exception e) {
			mLogger.severe(e.toString());
			throw new TTException(e.toString(), TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}

	}

	private void cleanupTickets() throws TTException {
		try {
			mLogger.info(START);

			Class[] formalArgs = new Class[0];

			Object[] args = new Object[0];

			doRemoteMethod("TTSECURITY.LicenceHelper", TTSECURITY.POA_NAME.value, TTSECURITY.OBJECT_ID.value, null, "",
					"cleanupExpired", formalArgs, args);

			mLogger.info(COMPLETE);
		} catch (Exception e) {
			mLogger.severe(e.toString());
			throw new TTException(e.toString(), TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}

	}

	private String getIdmTicket(String aUserId, String aPassword, String aNewPassword, String aSessionId)
			throws TTException {
		try {
			if (mLogger.isLoggable(Level.INFO)) {
				StringBufferS msg = new StringBufferS(START);
				msg.append("aUserId", aUserId, ", ");
				msg.append("aPassword", "*", ", ");
				msg.append("aNewPassword", "*", ", ");
				msg.append("aSessionId", aSessionId, "");
				mLogger.info(msg.toString());
			}

			if (aNewPassword == null) {
				aNewPassword = NULL_VALUE.value;
			}

			Class[] formalArgs = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class, String.class };

			Object[] args = new Object[] { aSessionId, mUserInfo.mClientIP, aUserId, aPassword, aNewPassword,
					mUserInfo.mLicenceType, getAppRefString() };

			String ticket = (String) doRemoteMethod("TTSECURITY.LicenceHelper", TTSECURITY.POA_NAME.value,
					TTSECURITY.OBJECT_ID.value, null, "", "login", formalArgs, args);

			if (mLogger.isLoggable(Level.INFO)) {
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("ticket", ticket, "");
				mLogger.info(msg.toString());
			}

			return ticket;
		} catch (BadLicenseTypeException e) {
			mLogger.severe(e.errorDescription);
			throw new TTException(e.errorDescription, TTCommonErrors.BAD_LOGIN_OPTION);
		} catch (CredentialException e) {
			mLogger.severe(e.errorDescription);
			throw new TTException(e.errorDescription, TTCommonErrors.INVALID_LOGIN);
		} catch (ExpiredException e) {
			mLogger.severe(e.errorDescription);
			throw new TTException(e.errorDescription, TTCommonErrors.LICENSE_EXPIRED);
		} catch (NoAvailableException e) {
			mLogger.severe(e.errorDescription);
			throw new TTException(e.errorDescription, TTCommonErrors.NO_LICENSES);
		} catch (TTlibException e) {
			mLogger.severe(e.errorDescription);
			String[] ctlerrno = new String[] { Integer.toString(e.errorCode), e.errorCodeStr };
			throw new TTException(e.errorDescription, TTCommonErrors.LOW_LEVEL_EXCEPTION, ctlerrno);
		} catch (GenericException e) {
			mLogger.severe(e.errorDescription);
			throw new TTException(e.errorDescription, TTCommonErrors.LOW_LEVEL_EXCEPTION);
		} catch (TTException e) {
			throw e;
		} catch (Exception e) {
			mLogger.severe(e.toString());
			throw new TTException(e.toString(), TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}
	}

	public void refresh() throws TTException {
		try {
			mLogger.info(START);
			mLogger.info("Got to Refresh");
			Class[] formalArgs = new Class[] { String.class, };

			Object[] args = new Object[] { getIdmTicket() };

			doRemoteMethod("TTSECURITY.LicenceHelper", TTSECURITY.POA_NAME.value, TTSECURITY.OBJECT_ID.value, null, "",
					"refresh", formalArgs, args);

			mLogger.info(COMPLETE);

			return;
		} catch (AuthenticationException e) {
			mLogger.severe(e.errorDescription);
			throw new TTException(e.errorDescription, TTCommonErrors.LICENSE_INVALID);
		} catch (TTlibException e) {
			mLogger.severe(e.errorDescription);
			String[] ctlerrno = new String[] { Integer.toString(e.errorCode), e.errorCodeStr };
			throw new TTException(e.errorDescription, TTCommonErrors.LOW_LEVEL_EXCEPTION, ctlerrno);
		} catch (GenericException e) {
			mLogger.severe(e.errorDescription);
			throw new TTException(e.errorDescription, TTCommonErrors.LOW_LEVEL_EXCEPTION);
		} catch (TTException e) {
			throw e;
		} catch (Exception e) {
			mLogger.severe(e.toString());
			throw new TTException(e.toString(), TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}

	}

}

class IdmHostInfo {
	private static Hashtable mHosts = new Hashtable(5);

	private static java.util.logging.Logger mLogger = java.util.logging.Logger
			.getLogger("com.vignette.idm.services.IdmHostInfo");

	private String mHostId;
	private String mHostName;
	private String mSpecialName;
	private String mDataSrcName;
	private long mHostLastModTime;
	private long mOrbLastModTime;
	private long mLastCheckTime;
	private org.omg.CosNaming.NamingContext[] mNameServers;
	private org.omg.CORBA.ORB[] mOrbs;
	private int mNextOrb;
	private int mMaxOrbs;

	Properties mProps;
	idmservers mHonchoServant;

	public org.omg.CosNaming.NamingContext getNameService() throws TTException {
		try {
			org.omg.CosNaming.NamingContext rootContext = null;
			org.omg.CORBA.Object obj = null;
			org.omg.CORBA.ORB orb = getOrb();
			if (mNameServers[mNextOrb] != null) {
				return mNameServers[mNextOrb];
			}
			obj = orb.resolve_initial_references("NameService");
			rootContext = org.omg.CosNaming.NamingContextHelper.narrow(obj);
			if (!isObjectOK(rootContext)) {
				String msg = "NameService root context is not OK";
				mLogger.severe(msg);
				throw new TTException(msg, TTCommonErrors.CONNECTION_EXCEPTION);
			}
			mNameServers[mNextOrb] = rootContext;
			return rootContext;
		} catch (TTException e) {
			throw e;
		} catch (Exception e) {
			String msg = "Unable to obtain naming service (" + e.toString() + ")";
			mLogger.severe(msg);
			throw new TTException(msg, TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}
	}

	boolean isObjectOK(org.omg.CORBA.Object anObj) {
		boolean ret = false;
		if (anObj != null) {
			ret = !anObj._non_existent();
		}
		return ret;
	}

	public void resetNameService(org.omg.CosNaming.NamingContext aContext) {
		mOrbs = null;
		for (int i = 0; i < mMaxOrbs; i++) {
			if (mNameServers[i] == aContext) {
				mNameServers[i] = null;
			}
		}
	}

	public static int count() {
		return mHosts.size();
	}

	public static IdmHostInfo getHost(String aHostId) throws TTException {
		synchronized (mHosts) {
			try {
				IdmHostInfo host = (IdmHostInfo) mHosts.get(aHostId);
				if (host == null) {
					host = new IdmHostInfo(aHostId);
				}
				if (host.getOrb() != null) {
					long now = System.currentTimeMillis();
					if ((now - host.mLastCheckTime) < (Defaults.CheckConfigInterval)) {
						return host;
					}
					host.mLastCheckTime = now;
					File hostFile = new File(host.hostPropsFilename());
					File orbFile = new File(host.getOrbPropsFileName());
					if (host.mHostLastModTime == hostFile.lastModified()
							&& host.mOrbLastModTime == orbFile.lastModified()) {
						return host;
					}
					host = new IdmHostInfo(aHostId);
				}
				host.setOrb();
				mHosts.put(aHostId, host);
				return host;
			} catch (TTException e) {
				throw e;
			} catch (Exception e) {
				String msg = "Unable to obtain IDM host info (" + e.toString() + ")";
				mLogger.severe(msg);
				throw new TTException(msg, TTCommonErrors.LOW_LEVEL_EXCEPTION);
			}
		}
	}

	private IdmHostInfo(String aHostId) throws TTException {
		mHostId = aHostId;
		getHostName();
		getSpecialName();
		mLastCheckTime = System.currentTimeMillis();
		mHonchoServant = null;
	}

	public String getSpecialName() throws TTException {
		if (mSpecialName == null) {
			Properties props = getHostProperties();
			String specialName = props.getProperty("ALTERNATE_SYS", "");
			mSpecialName = specialName;
		}
		return mSpecialName;
	}

	public String getHostId() {
		return mHostId;
	}

	public String getHostName() throws TTException {
		if (mHostName == null) {
			getHostProperties();
			mHostName = mProps.getProperty(ConfigKeys.IDM_HOSTNAME);
			if (mHostName == null) {
				String msg = "Could not get a host name for " + mHostId + " from config file \"" + hostPropsFilename()
						+ "\" ! Property = " + ConfigKeys.IDM_HOSTNAME;
				mLogger.severe(msg);
				throw new TTException(msg, TTRepositoryErrors.CONFIG_ITEM_MISSING);
			}
		}
		return mHostName;
	}

	public String getDataSrcName() throws TTException {
		if (mDataSrcName == null) {
			getHostProperties();

			mDataSrcName = mProps.getProperty(ConfigKeys.DATASOURCE_REF);
			if (mDataSrcName == null) {
				IDMConfiguration config = (IDMConfiguration) EnvironmentClassServer.getClass(IDMConfiguration.class);
				mDataSrcName = config.getConfigurationValue(ConfigKeys.DATASOURCE_REF, Defaults.DataSourceREF);
			}
		}
		return mDataSrcName;
	}

	private String hostPropsFilename() throws TTException {
		return getHostConfigPath() + "/" + Defaults.HostPropsFileName;
	}

	private Properties getHostProperties() throws TTException {
		if (mProps == null) {
			String hpfname = hostPropsFilename();
			try {
				File hostFile = new File(hpfname);
				mHostLastModTime = hostFile.lastModified();
				FileInputStream hpfstrm = new FileInputStream(hostFile);
				Properties props = new Properties();
				props.load(hpfstrm);
				mProps = props;
			} catch (FileNotFoundException e) {
				String msg = "Can't find host properties file \"" + hpfname + "\"";
				mLogger.severe(msg);
				throw new TTException(msg, TTRepositoryErrors.CONFIG_FILE_MISSING);
			} catch (Exception e) {
				String msg = "Reading from host properties file \"" + hpfname + "\" failed (" + e.toString() + ")";
				mLogger.severe(msg);
				throw new TTException(msg, TTCommonErrors.LOW_LEVEL_EXCEPTION);
			}
		}
		return mProps;
	}

	public org.omg.CORBA.ORB getOrb() {
		synchronized (this) {
			if (mOrbs == null) {
				return null;
			}
			if (mNextOrb >= mMaxOrbs) {
				mNextOrb = 0;
			}
			return mOrbs[mNextOrb];
		}
	}

	public void setOrb() throws TTException {
		Properties props = getOrbProps();
		setOrb(props);
	}

	private Properties getOrbProps() throws TTException {
		String orbPropsFileName = getOrbPropsFileName();
		try {
			File orbFile = new File(orbPropsFileName);
			mOrbLastModTime = orbFile.lastModified();
			FileInputStream opstrm = new FileInputStream(orbFile);
			Properties props = new Properties();
			props.load(opstrm);

			String singletonClass = props.getProperty("org.omg.CORBA.ORBSingletonClass");
			if (singletonClass != null) {
				System.setProperty("org.omg.CORBA.ORBSingletonClass", singletonClass);
			}

			return props;
		} catch (FileNotFoundException e) {
			String msg = "Can't find orb properties file \"" + orbPropsFileName + "\"";
			mLogger.severe(msg);
			throw new TTException(msg, TTRepositoryErrors.CONFIG_FILE_MISSING);
		} catch (Exception e) {
			String msg = "Reading from orb properties file \"" + orbPropsFileName + "\" failed (" + e.toString() + ")";
			mLogger.severe(msg);
			throw new TTException(msg, TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}
	}

	public String getOrbPropsFileName() throws TTException {
		return getHostConfigPath() + "/" + Defaults.OrbPropsFileName;
	}

	public String getHostConfigPath() throws TTException {
		return getConfigBasePath() + "/" + mHostId;
	}

	private static String getConfigBasePath() throws TTException {
		try {
			String basePath = System.getProperty(ConfigKeys.IDM_HOME);
			if (basePath == null || basePath.trim().length() == 0) {
				basePath = System.getProperty(ConfigKeys.TOWER_HOME, Defaults.ConfigBasePath);
			}
			basePath = basePath + Defaults.HostConfigsPath;
			File testdir = new File(basePath);
			if (!testdir.isDirectory()) {
				String msg = "Config base path \"" + basePath + "\" is not a directory";
				mLogger.severe(msg);
				throw new TTException(msg, TTRepositoryErrors.CONFIG_FILE_MISSING);
			}
			return basePath;
		} catch (TTException e) {
			throw e;
		} catch (Exception e) {
			String msg = "Unable to obtain config base path (" + e.toString() + ")";
			mLogger.severe(msg);
			throw new TTException(msg, TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}
	}

	private void setOrb(Properties aProps) throws TTException {
		if (mOrbs == null) {
			mMaxOrbs = 1;
			mOrbs = new org.omg.CORBA.ORB[mMaxOrbs];
			mNameServers = new org.omg.CosNaming.NamingContext[mMaxOrbs];
			for (mNextOrb = 0; mNextOrb < mMaxOrbs; mNextOrb++) {
				aProps.list(System.err);
				org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init((String[]) null, aProps);
				if (orb == null) {
					String msg = "ORB " + mNextOrb + " initialisation failed for hostId = \"" + mHostId + "\"";
					mLogger.severe(msg);
					throw new TTException(msg, TTCommonErrors.CONNECTION_EXCEPTION);
				}
				mOrbs[mNextOrb] = orb;
			}
		}
	}
}

class UserInfo {
	public static String ANONYMOUS = "ANONYMOUS";

	private static java.util.logging.Logger mLogger = java.util.logging.Logger
			.getLogger("com.vignette.idm.services.UserInfo");

	public IdmHostInfo mHost;
	public String mUserId;
	public String mSessionId;
	public String mClientIP;
	public String mTicket;
	public String mIdmTicket;
	public String mApplicationRef;
	public String mLicenceType;
	public int mLoginTime;
	public int mLastAccessTime;
	public int mTimeoutTime;

	public UserInfo(String aTicket) throws TTException {
		setTicket(aTicket);
	}

	private void setTicket(String aTicket) throws TTException {
		try {
			mTicket = aTicket;
			int hostLenEnd = aTicket.indexOf(":");
			if (hostLenEnd < 0) {
				String msg = "Invalid ticket format - hostIdLen \"" + aTicket + "\"";
				mLogger.severe(msg);
				throw new TTException(msg, TTCommonErrors.LICENSE_INVALID);
			}
			int hostIdEnd = Integer.parseInt(aTicket.substring(0, hostLenEnd)) + hostLenEnd;
			setHost(aTicket.substring(hostLenEnd + 1, hostIdEnd + 1));
			mIdmTicket = aTicket.substring(hostIdEnd + 1);
		} catch (TTException e) {
			throw e;
		} catch (Exception e) {
			String msg = "Problem setting ticket (" + e.toString() + ")";
			mLogger.severe(msg);
			throw new TTException(msg, TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}
	}

	public static UserInfo getUser(String aTicket) throws TTException {
		return new UserInfo(aTicket);
	}

	public UserInfo(String aHostId, String aUserId, String aSessionId, String aClientIP, String aLicenceType)
			throws TTException {
		if ((aUserId == null) || (aUserId.equals(""))) {
			mUserId = ANONYMOUS;
		} else {
			mUserId = aUserId;
		}
		mClientIP = aClientIP;
		if (mClientIP == null) {
			mClientIP = "";
		}
		mLicenceType = aLicenceType;
		setHost(aHostId);
		if ((mLicenceType == null) || (mLicenceType.equals(""))) {
			mLicenceType = mHost.mProps.getProperty(ConfigKeys.LICENCE_TYPE);
			if (mLicenceType == null) {
				String msg = "Could not find a licence type property " + ConfigKeys.LICENCE_TYPE + " in file "
						+ mHost.getOrbPropsFileName();
				mLogger.severe(msg);
				throw new TTException(msg, TTRepositoryErrors.CONFIG_ITEM_MISSING);
			}
		}
		mSessionId = makeSessionId(aSessionId);
		mApplicationRef = mHost.mProps.getProperty(ConfigKeys.APPLICATION_REF);
		if (mApplicationRef == null) {
			mApplicationRef = "GenericIDM";
		}
	}

	private String makeSessionId(String aSessionId) throws TTException {
		try {
			java.net.InetAddress thisHost = java.net.InetAddress.getLocalHost();
			long tm = System.currentTimeMillis();
			String sessId = "";
			if (aSessionId != null) {
				sessId = aSessionId;
			}
			String lenStr = Integer.toHexString(sessId.length());
			return thisHost.getHostName() + "/" + mUserId + "/" + Long.toHexString(tm) + sessId + ":" + lenStr;
		} catch (Exception e) {
			String msg = "Unable to create a session ID (" + e.toString() + ")";
			mLogger.severe(msg);
			throw new TTException(msg, TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}
	}

	private void setHost(String aHostId) throws TTException {
		mHost = IdmHostInfo.getHost(aHostId);
	}
}

class POAinfo {
	private static Hashtable mPoaCache = new Hashtable(20);

	String mPOAname;
	String mUniqueKey;
	String mInstanceRef;
	org.omg.CORBA.Object mServantRef;
	idmservers mHonchoRef;
	boolean mCached;

	public static long cacheSize() {
		return mPoaCache.size();
	}

	private static void clearCache() {
		mPoaCache.clear();
	}

	public static void checkCache(long aServerCount) {
		synchronized (mPoaCache) {
			if (POAinfo.cacheSize() > (aServerCount << 2)) {
				POAinfo.clearCache();
			}
		}
	}

	public void cachePOA() {
		if (!mCached && (mServantRef != null)) {
			synchronized (mPoaCache) {
				if (mPoaCache.get(mUniqueKey) == null) {
					mPoaCache.put(mUniqueKey, this);
				}
				mCached = true;
			}
		}
	}

	public void deleteCache() {
		if (mCached) {
			synchronized (mPoaCache) {
				mPoaCache.remove(mUniqueKey);
				mCached = false;
			}
		}
	}

	public static POAinfo getPOAinfo(String aPOAname, String anObjId, String aHelperClassName) {
		synchronized (mPoaCache) {
			POAinfo returnVal = (POAinfo) mPoaCache.get(mkUniqueKey(aPOAname, anObjId, aHelperClassName));
			return returnVal;
		}
	}

	public static String mkUniqueKey(String aPoaName, String anObjId, String aHelperClassName) {
		return aPoaName + "." + anObjId + "." + aHelperClassName;
	}

	public POAinfo(String aPOAname, String anObjId, String aRef, idmservers aSvrRef, String aHelperClassName) {
		mPOAname = aPOAname;
		mInstanceRef = aRef;
		mHonchoRef = aSvrRef;
		mServantRef = null;
		mCached = false;
		mUniqueKey = mkUniqueKey(aPOAname, anObjId, aHelperClassName);
	}

	public String getPOAname() {
		return mPOAname;
	}

	public String getReference() {
		return mInstanceRef;
	}

	public idmservers getHoncho() {
		return mHonchoRef;
	}

	public org.omg.CORBA.Object getServant() {
		return mServantRef;
	}

	public void setServant(org.omg.CORBA.Object anObj) {
		mServantRef = anObj;
	}
};

////////////////////////////////////////////////////////////////////////////////
// End of Code
////////////////////////////////////////////////////////////////////////////////
