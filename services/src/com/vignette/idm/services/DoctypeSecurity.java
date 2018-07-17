////////////////////////////////////////////////////////////////////////////////
//	Title		:	DoctypeSecurity.java
//
//	Description	:	Class which provides document type security functions.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.services;

import java.util.ArrayList;
import java.util.logging.Level;

import com.towertechnology.common.errorhandling.TTCommonErrors;
import com.towertechnology.common.errorhandling.TTException;
import com.towertechnology.common.errorhandling.TTRepositoryErrors;
import com.vignette.idm.common.StringBufferS;

import TTCOMMON.NULL_VALUE;
import TTDOCTYPE.DTAccessActions;
import TTEXCEPTIONS.AppPermException;
import TTEXCEPTIONS.AuthenticationException;
import TTEXCEPTIONS.BadDocIdException;
import TTEXCEPTIONS.GenericException;
import TTEXCEPTIONS.TTlibException;
import TTEXCEPTIONS.UnknownDocException;
import TTSECURITY.OBJECT_ID;
import TTSECURITY.POA_NAME;

public class DoctypeSecurity
{
	private static final String START = "Start: ";
	private static final String COMPLETE = "Complete: ";

	public static final int DOCTYPE_UNKNOWN	= 1;
	public static final int DOCTYPE_VIEW 	= 2;
	public static final int DOCTYPE_CREATE 	= 4;
	public static final int DOCTYPE_DELETE 	= 8;
	public static final int DOCTYPE_MODIFY 	= 16;

	private static java.util.logging.Logger mLogger =
		java.util.logging.Logger.getLogger
		(
			"com.vignette.idm.services.DoctypeSecurity"
		);

	private IdmRepImpl mRepository;

	/**
	 * Creates an instance of this class.
	 *
	 * @param aRepository a handle to the IDM Repository implementation.
	 */
	public DoctypeSecurity(IdmRepImpl aRepository)
	{
		mRepository = aRepository;
	}

	/**
	 * Get the caching details for a user.
	 *
	 * @return	The cached object for a user.
	 */
	private DoctypeUserCache getUserCache() throws TTException
	{
		DoctypeCache dtCache = DoctypeCache.getInstance();
		String ticket = mRepository.getTicket();

		DoctypeUserCache ucache = dtCache.getUserCache(ticket);
		if (ucache == null)
		{
			DoctypeUserCache newucache = new DoctypeUserCache(ticket);
			dtCache.setUserCache(ticket, newucache);
			return newucache;
		}
		return ucache;
	}

	/**
	 * Get the access mode for a particular document type.
	 *
	 * @param	anAppName name of an application.
	 * @param	aDocType a document type code (slevel value).
	 * @return	the bitwise privilege for this document type.
	 */
	public int getDoctypeAccess(String anAppName, int aDocType)
	throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("anAppName", anAppName, ", ");
				msg.append("aDocType", aDocType, "");
				mLogger.info(msg.toString());
			}

			DoctypeUserCache ucache = getUserCache();
			String ucacheKey = "getDoctypeAccess" + anAppName + aDocType;
			Integer ret = ucache.getInteger(ucacheKey);
			if (ret == null)
			{
				// Define formal arguments.
				Class[] formalArgs = new Class[]
				{
					java.lang.String.class,
					java.lang.String.class,
					java.lang.Integer.TYPE
				};

				// Define actual arguments.
				java.lang.Object[] args = new java.lang.Object[]
				{
					mRepository.getIdmTicket(),
					anAppName,
					new Integer(aDocType)
				};

				// Call remote method.
				ret = (Integer)mRepository.doRemoteMethod
				(
					"TTDOCTYPE.DocTypeSecurityHelper",
					POA_NAME.value,
					OBJECT_ID.value,
					null,
					"",
					"getDoctypeAccess",
					formalArgs,
					args
				);

				ucache.setInteger(ucacheKey, ret.intValue());
			}

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("ret", ret.intValue(), "");
				mLogger.info(msg.toString());
			}

			return ret.intValue();
		}
		catch (AuthenticationException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LICENSE_INVALID
			);
		}
		catch (TTlibException e)
		{
			mLogger.severe(e.errorDescription);
			String[] ctlerrno = new String[]
			{
				Integer.toString(e.errorCode),
				e.errorCodeStr
			};
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION,
				ctlerrno
			);
		}
		catch (GenericException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
		catch (TTException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			mLogger.severe(e.toString());
			throw new TTException
			(
				e.toString(),
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
	}

	/**
	 * Get the Application Access permission for a user.
	 *
	 * @param	anAppName name of an application.
	 * @return	true if the user have access to this application
	 *			false if the user don't have access to this application.
	 */
	public boolean getApplicationAccess(String anAppName) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("amAppName", anAppName, "");
				mLogger.info(msg.toString());
			}

			DoctypeUserCache ucache = getUserCache();
			String ucacheKey = "getApplicationAccess" + anAppName;
			Boolean ret = ucache.getBoolean(ucacheKey);
			if (ret == null)
			{
				// Define formal arguments.
				Class[] formalArgs = new Class[]
				{
					java.lang.String.class,
					java.lang.String.class
				};

				// Define actual arguments.
				java.lang.Object[] args = new java.lang.Object[]
				{
					mRepository.getIdmTicket(),
					anAppName
				};

				// Call remote method.
				ret = (Boolean)mRepository.doRemoteMethod
				(
					"TTDOCTYPE.DocTypeSecurityHelper",
					POA_NAME.value,
					OBJECT_ID.value,
					null,
					"",
					"getApplicationAccess",
					formalArgs,
					args
				);

				ucache.setBoolean(ucacheKey, ret.booleanValue());
			}

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("ret", ret.booleanValue(), "");
				mLogger.info(msg.toString());
			}

			return ret.booleanValue();
		}
		catch (AuthenticationException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LICENSE_INVALID
			);
		}
		catch (TTlibException e)
		{
			mLogger.severe(e.errorDescription);
			String[] ctlerrno = new String[]
			{
				Integer.toString(e.errorCode),
				e.errorCodeStr
			};
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION,
				ctlerrno
			);
		}
		catch (GenericException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
		catch (TTException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			mLogger.severe(e.toString());
			throw new TTException
			(
				e.toString(),
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
	}

	/**
	 * Get the ACL (Access Control List) permission for this application.
	 *
	 * @param	anAppName name of an application.
	 * @return	true if the user have ACL access
	 *			false if the user don't have ACL access.
	 */
 	public boolean getACLAdminAccess(String	anAppName) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("anAppName", anAppName, "");
				mLogger.info(msg.toString());
			}

			DoctypeUserCache ucache = getUserCache();
			String ucacheKey = "getACLAdminAccess" + anAppName;
			Boolean ret = ucache.getBoolean(ucacheKey);
			if (ret == null)
			{
				// Define formal arguments.
				Class[] formalArgs = new Class[]
				{
					java.lang.String.class,
					java.lang.String.class
				};

				// Define actual arguments.
				java.lang.Object[] args = new java.lang.Object[]
				{
					mRepository.getIdmTicket(),
					anAppName
				};

				// Call remote method.
				ret = (Boolean)mRepository.doRemoteMethod
				(
					"TTDOCTYPE.DocTypeSecurityHelper",
					POA_NAME.value,
					OBJECT_ID.value,
					null,
					"",
					"getACLAdminAccess",
					formalArgs,
					args
				);

				ucache.setBoolean(ucacheKey, ret.booleanValue());
			}

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("ret", ret.booleanValue(), "");
				mLogger.info(msg.toString());
			}

			return ret.booleanValue();
		}
		catch (AuthenticationException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LICENSE_INVALID
			);
		}
		catch (TTlibException e)
		{
			mLogger.severe(e.errorDescription);
			String[] ctlerrno = new String[]
			{
				Integer.toString(e.errorCode),
				e.errorCodeStr
			};
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION,
				ctlerrno
			);
		}
		catch (GenericException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
		catch (TTException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			mLogger.severe(e.toString());
			throw new TTException
			(
				e.toString(),
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
	}

	/**
	 * Get all the document type name for an application.
	 *
	 * @param	anAppName name of an application.
	 * @return	an array of strings each string represents a document type name.
	 */
	public String[] getDoctypeNames(String anAppName) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("anAppName", anAppName, "");
				mLogger.info(msg.toString());
			}

			DoctypeUserCache ucache = getUserCache();
			String ucacheKey = "getDoctypeNames" + anAppName;
			String ret = ucache.getString(ucacheKey);
			if (ret == null)
			{
				// Define formal arguments.
				Class[] formalArgs = new Class[]
				{
					java.lang.String.class,
					java.lang.String.class
				};

				// Define actual arguments.
				java.lang.Object[] args = new java.lang.Object[]
				{
					mRepository.getIdmTicket(),
					anAppName
				};

				// Call remote method.
				ret = (String)mRepository.doRemoteMethod
				(
					"TTDOCTYPE.DocTypeSecurityHelper",
					POA_NAME.value,
					OBJECT_ID.value,
					null,
					"",
					"getDoctypeNames",
					formalArgs,
					args
				);

				ucache.setString(ucacheKey, ret);
			}

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("ret", ret, "");
				mLogger.info(msg.toString());
			}

			if (ret == null || ret.length() == 0)
			{
				return null;
			}
			else
			{
				// Split up the "|dt|dt2" into "dt", "dt2".
				return ret.split("|");
			}
		}
		catch (AuthenticationException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LICENSE_INVALID
			);
		}
		catch (TTlibException e)
		{
			mLogger.severe(e.errorDescription);
			String[] ctlerrno = new String[]
			{
				Integer.toString(e.errorCode),
				e.errorCodeStr
			};
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION,
				ctlerrno
			);
		}
		catch (GenericException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
		catch (TTException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			mLogger.severe(e.toString());
			throw new TTException
			(
				e.toString(),
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
	}

	/**
	 * Get the document type name for this application base on the
	 * document type code.
	 *
	 * @param	anAppName name of an application.
	 * @param	aDocType a document type code.
	 * @return	the name of a document type.
	 */
	public String getDoctypeName(String anAppName, int aDocType)
	throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("anAppName", anAppName, ", ");
				msg.append("aDocType", aDocType, "");
				mLogger.info(msg.toString());
			}

			DoctypeUserCache ucache = getUserCache();
			String ucacheKey = "getDoctypeName" + anAppName + aDocType;
			String ret = ucache.getString(ucacheKey);
			if (ret == null)
			{
				// Define formal arguments.
				Class[] formalArgs = new Class[]
				{
					java.lang.String.class,
					java.lang.String.class,
					java.lang.Integer.TYPE
				};

				// Define actual arguments.
				java.lang.Object[] args = new java.lang.Object[]
				{
					mRepository.getIdmTicket(),
					anAppName,
					new Integer(aDocType)
				};

				// Call remote method.
				ret = (String)mRepository.doRemoteMethod
				(
					"TTDOCTYPE.DocTypeSecurityHelper",
					POA_NAME.value,
					OBJECT_ID.value,
					null,
					"",
					"getDoctypeName",
					formalArgs,
					args
				);

				ucache.setString(ucacheKey, ret);
			}

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("ret", ret, "");
				mLogger.info(msg.toString());
			}

			if (ret == null || ret.length() == 0)
			{
				return null;
			}
			else
			{
				return ret;
			}
		}
		catch (AuthenticationException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LICENSE_INVALID
			);
		}
		catch (TTlibException e)
		{
			mLogger.severe(e.errorDescription);
			String[] ctlerrno = new String[]
			{
				Integer.toString(e.errorCode),
				e.errorCodeStr
			};
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION,
				ctlerrno
			);
		}
		catch (GenericException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
		catch (TTException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			mLogger.severe(e.toString());
			throw new TTException
			(
				e.toString(),
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
	}

	/**
	 * Get the document type code for this application base on the
	 * document type name.
	 *
	 * @param	anAppName name of an application.
	 * @param	aDocTypeName name of a document type.
	 * @return	a document type code.
	 */
	public int getDoctypeCode(String anAppName, String aDocTypeName)
	throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("anAppName", anAppName, ", ");
				msg.append("aDocTypeName", aDocTypeName, "");
				mLogger.info(msg.toString());
			}

			DoctypeUserCache ucache = getUserCache();
			String ucacheKey = "getDoctypeCode" + anAppName + aDocTypeName;
			Integer ret = ucache.getInteger(ucacheKey);
			if (ret == null)
			{
				// Define formal arguments.
				Class[] formalArgs = new Class[]
				{
					java.lang.String.class,
					java.lang.String.class,
					java.lang.String.class
				};

				// Define actual arguments.
				java.lang.Object[] args = new java.lang.Object[]
				{
					mRepository.getIdmTicket(),
					anAppName,
					aDocTypeName
				};

				// Call remote method.
				ret = (Integer)mRepository.doRemoteMethod
				(
					"TTDOCTYPE.DocTypeSecurityHelper",
					POA_NAME.value,
					OBJECT_ID.value,
					null,
					"",
					"getDoctypeCode",
					formalArgs,
					args
				);

				ucache.setInteger(ucacheKey, ret.intValue());
			}

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("ret", ret.intValue(), "");
				mLogger.info(msg.toString());
			}

			return ret.intValue();
		}
		catch (AuthenticationException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LICENSE_INVALID
			);
		}
		catch (TTlibException e)
		{
			mLogger.severe(e.errorDescription);
			String[] ctlerrno = new String[]
			{
				Integer.toString(e.errorCode),
				e.errorCodeStr
			};
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION,
				ctlerrno
			);
		}
		catch (GenericException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
		catch (TTException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			mLogger.severe(e.toString());
			throw new TTException
			(
				e.toString(),
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
	}

	/**
	 * Get a partial SQL where clause to for all the document type
	 * that a user have access to.
	 *
	 * @param	anAppName name of an application.
	 * @param	anAccessMap a bitwise contains the required privileges.
	 * @return	a partial SQL where clause.
	 */
	public String getDoctypeSQLClause(String anAppName, int anAccessMap)
	throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("anAppName", anAppName, ", ");
				msg.append("anAccessMap", anAccessMap, "");
				mLogger.info(msg.toString());
			}

			DoctypeUserCache ucache = getUserCache();
			String ucacheKey = "getDoctypeSQLClause" + anAppName + anAccessMap;
			String ret = ucache.getString(ucacheKey);
			if (ret == null)
			{
				// Define formal arguments.
				Class[] formalArgs = new Class[]
				{
					java.lang.String.class,
					java.lang.String.class,
					java.lang.Integer.TYPE
				};

				// Define actual arguments.
				java.lang.Object[] args = new java.lang.Object[]
				{
					mRepository.getIdmTicket(),
					(anAppName == null) ? NULL_VALUE.value : anAppName,
					new Integer(anAccessMap)
				};

				// Call remote method.
				ret = (String)mRepository.doRemoteMethod
				(
					"TTDOCTYPE.DocTypeSecurityHelper",
					POA_NAME.value,
					OBJECT_ID.value,
					null,
					"",
					"getDoctypeSQLClause",
					formalArgs,
					args
				);

				ucache.setString(ucacheKey, ret);
			}

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("ret", ret, "");
				mLogger.info(msg.toString());
			}

			if (ret == null || ret.length() == 0)
			{
				return null;
			}
			else
			{
				return ret;
			}
		}
		catch (AuthenticationException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LICENSE_INVALID
			);
		}
		catch (AppPermException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.COLLECTION_PERMISSION_DENIED
			);
		}
		catch (TTlibException e)
		{
			mLogger.severe(e.errorDescription);
			String[] ctlerrno = new String[]
			{
				Integer.toString(e.errorCode),
				e.errorCodeStr
			};
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION,
				ctlerrno
			);
		}
		catch (GenericException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
		catch (TTException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			mLogger.severe(e.toString());
			throw new TTException
			(
				e.toString(),
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
	}

	/**
	 * Convert a string representation of document type priviledge
	 * to its integer representation
	 *
	 * @param	aDocTypePriv document type priviledge
	 * @return	document type priviledge as an integer
	 */
	private int interpretDoctypePriv(String aDocTypePriv)
	{
		if (aDocTypePriv.equalsIgnoreCase("VIEW"))
		{
			return DoctypeSecurity.DOCTYPE_VIEW;
		}
		else if (aDocTypePriv.equalsIgnoreCase("MODIFY"))
		{
			return DoctypeSecurity.DOCTYPE_MODIFY;
		}
		else if (aDocTypePriv.equalsIgnoreCase("DELETE"))
		{
			return DoctypeSecurity.DOCTYPE_DELETE;
		}
		else if (aDocTypePriv.equalsIgnoreCase("CREATE"))
		{
			return DoctypeSecurity.DOCTYPE_CREATE;
		}
		return DoctypeSecurity.DOCTYPE_UNKNOWN;
	}

	/**
	 * Get a list of document type names for an application
	 * subject to a particular document type priviledge
	 *
	 * @param	anAppName name of an application.
	 * @param	aDocTypePriv document type priviledge(VIEW,MODIFY,DELETE,UPDATE)
	 * @return	an array of strings each string represents a document type name.
	 */
	public String[] getDoctypeNamesByPriv
	(
		String	anAppName,
		String	aDocTypePriv
	) throws TTException
	{
		if (mLogger.isLoggable(Level.INFO))
		{
			StringBufferS msg = new StringBufferS(START);
			msg.append("anAppName", anAppName, ", ");
			msg.append("aDocTypePriv", aDocTypePriv, "");
			mLogger.info(msg.toString());
		}

		int dt_priv = interpretDoctypePriv(aDocTypePriv);

		ArrayList finalList = new ArrayList();

		String[] doctypeList = getDoctypeNames(anAppName);
		if (doctypeList != null)
		{
			for (int i = 0; i < doctypeList.length; i++)
			{
				int slevel = getDoctypeCode(anAppName, doctypeList[i]);
				int doctypeAccess = getDoctypeAccess(anAppName, slevel);

				if ((doctypeAccess & dt_priv) != 0)
				{
					finalList.add(doctypeList[i]);
				}
			}
		}

		String[] returnList = new String[finalList.size()];
		finalList.toArray(returnList);

		if (mLogger.isLoggable(Level.INFO))
		{
			StringBufferS msg = new StringBufferS(COMPLETE);
			msg.append("returnList", returnList, "");
			mLogger.info(msg.toString());
		}

		return returnList;
	}

	/**
	 * Get a list of document type names for an application
	 * subject to a particular document type priviledge
	 *
	 * @param	anAppName name of an application.
	 * @param	aDocTypePriv document type priviledge(VIEW,MODIFY,DELETE,UPDATE)
	 * @return	an array of strings each string represents a document type name.
	 */
	public ArrayList getDoctypeNameListByPriv
	(
		String	anAppName,
		String	aDocTypePriv
	) throws TTException
	{
		if (mLogger.isLoggable(Level.INFO))
		{
			StringBufferS msg = new StringBufferS(START);
			msg.append("anAppName", anAppName, ", ");
			msg.append("aDocTypePriv", aDocTypePriv, "");
			mLogger.info(msg.toString());
		}

		int dt_priv = interpretDoctypePriv(aDocTypePriv);

		ArrayList finalList = new ArrayList();

		String[] doctypeList = getDoctypeNames(anAppName);
		if (doctypeList != null)
		{
			for (int i = 0; i < doctypeList.length; i++)
			{
				int slevel = getDoctypeCode(anAppName, doctypeList[i]);
				int doctypeAccess = getDoctypeAccess(anAppName, slevel);

				if ((doctypeAccess & dt_priv) != 0)
				{
					finalList.add(doctypeList[i]);
				}
			}
		}


		if (mLogger.isLoggable(Level.INFO))
		{
			StringBufferS msg = new StringBufferS(COMPLETE);
			msg.append("returnList", finalList, "");
			mLogger.info(msg.toString());
		}

		return finalList;
	}

	/**
	 * Check if specified user has document type permission on application
	 *
	 * @param	anAction - Type of action to check permission for
	 * @param	anAppName - Name of application
	 * @param	aData - Data specific to action
	 * @return
	 */
	public boolean validateIfnDoctypeAccess
	(
		int		anAction,
		String	anAppName,
		String	aData
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("anAction", anAction, ", ");
				msg.append("anAppName", anAppName, ", ");
				msg.append("aData", aData, "");
				mLogger.info(msg.toString());
			}

			// Check input arguments.
			DTAccessActions action = DTAccessActions.from_int(anAction);

			// Retrieve values from document type cache if exist.
			DoctypeUserCache ucache = getUserCache();
			String ucacheKey =
				"validateIfnDoctypeAccess" + action.value() + anAppName +
				aData;
			Boolean ret = ucache.getBoolean(ucacheKey);
			if (ret == null)
			{
				// Define formal arguments.
				Class[] formalArgs = new Class[]
				{
					java.lang.String.class,
					DTAccessActions.class,
					java.lang.String.class,
					java.lang.String.class
				};

				// Define actual arguments.
				java.lang.Object[] actualArgs = new java.lang.Object[]
				{
					mRepository.getIdmTicket(),
					action,
					(anAppName == null) ? NULL_VALUE.value : anAppName,
					aData
				};

				// Call remote method.
				ret = (Boolean)mRepository.doRemoteMethod
				(
					"TTDOCTYPE.DocTypeSecurityHelper",
					POA_NAME.value,
					OBJECT_ID.value,
					null,
					"",
					"validateIfnDoctypeAccess",
					formalArgs,
					actualArgs
				);

				ucache.setBoolean(ucacheKey, ret.booleanValue());
			}

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("ret", ret.booleanValue(), "");
				mLogger.info(msg.toString());
			}

			return ret.booleanValue();
		}
		catch (org.omg.CORBA.BAD_PARAM e)
		{
			String msg = "Invalid action code";
			mLogger.severe(msg);
			throw new TTException(msg, TTCommonErrors.INVALID_ARGUMENT);
		}
		catch (AuthenticationException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LICENSE_INVALID
			);
		}
		catch (BadDocIdException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.DOCUMENT_ID_ILLEGAL
			);
		}
		catch (UnknownDocException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.DOCUMENT_UNKNOWN
			);
		}
		catch (TTlibException e)
		{
			mLogger.severe(e.errorDescription);
			String[] ctlerrno = new String[]
			{
				Integer.toString(e.errorCode),
				e.errorCodeStr
			};
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION,
				ctlerrno
			);
		}
		catch (GenericException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
		catch (TTException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			mLogger.severe(e.toString());
			throw new TTException
			(
				e.toString(),
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
