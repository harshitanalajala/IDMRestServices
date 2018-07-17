package com.opentext.rest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;
import java.util.logging.Level;

import com.opentext.rest.utils.SysServiceUtils;
import com.towertechnology.common.errorhandling.ITTErrorDef;
import com.towertechnology.common.errorhandling.TTCommonErrors;
import com.towertechnology.common.errorhandling.TTException;
import com.towertechnology.common.errorhandling.TTRepositoryErrors;
import com.vignette.idm.common.NameValue;
import com.vignette.idm.server.GeneralException;
import com.vignette.idm.server.GeneralExceptionFaultDetail;
import com.vignette.idm.server.IDMRepositoryException;
import com.vignette.idm.server.IDMRepositoryExceptionFaultDetail;
import com.vignette.idm.server.InvalidCredentialsException;
import com.vignette.idm.server.InvalidCredentialsExceptionFaultDetail;
import com.vignette.idm.server.InvalidParameterException;
import com.vignette.idm.server.InvalidParameterExceptionFaultDetail;
import com.vignette.idm.server.LicenseException;
import com.vignette.idm.server.LicenseExceptionFaultDetail;
import com.vignette.idm.server.NoAvailableLicenceException;
import com.vignette.idm.server.NoAvailableLicenceExceptionFaultDetail;
import com.vignette.idm.server.PasswordExpiredException;
import com.vignette.idm.server.PasswordExpiredExceptionFaultDetail;
import com.vignette.idm.server.UnknownConfigVarException;
import com.vignette.idm.server.UnknownConfigVarExceptionFaultDetail;
import com.vignette.idm.server.UnknownEventException;
import com.vignette.idm.server.UnknownEventExceptionFaultDetail;
import com.vignette.idm.server.UnknownEventFieldException;
import com.vignette.idm.server.UnknownEventFieldExceptionFaultDetail;
import com.vignette.idm.server.UnknownOptionNameException;
import com.vignette.idm.server.UnknownOptionNameExceptionFaultDetail;
import com.vignette.idm.server.UnknownOptionValueException;
import com.vignette.idm.server.UnknownOptionValueExceptionFaultDetail;
import com.vignette.idm.server.UnknownVarNamePatternException;
import com.vignette.idm.server.UnknownVarNamePatternExceptionFaultDetail;
import com.vignette.idm.server.common.Converter;
import com.vignette.idm.server.common.ExceptionMessagesFormatter;
import com.vignette.idm.server.common.LicenceFactory;
import com.vignette.idm.server.common.StringBufferWS;
import com.vignette.idm.server.common.Validator;
import com.vignette.idm.server.common.olf.FormatterException;
import com.vignette.idm.services.IdmRepImpl;
import com.vignette.idm.services.Misc;

public class SystemServices {

	private static final String CATEGORY = "category";
	private static final String COMPLETE = "Complete: ";
	private static final String FALSE = "false";
	private static final String FIELDS = "fields";
	private static final String FILENAME = "filename";
	private static final String LICENSE = "license";
	private static final String MESSAGE = "message";
	private static final String NAME = "name";
	private static final String NEWPASSWORD = "NewPassword";
	private static final String NONDYNAMICTICKETS = "NonDynamicTickets";
	private static final String OPTIONS = "options";
	private static final String PARAMETERS = "parameters";
	private static final String PASSWORD = "password";
	private static final String REASON = "reason";
	private static final String SERVICENAME = "SysServices";
	private static final String SEVERITY = "severity";
	private static final String START = "Started System Services: ";
	private static final String TRUE = "true";
	private static final String USERID = "userID";
	private static final String VALID = "valid";
	private static final String VARNAME = "varName";
	private static final String VARNAMEPATTERN = "varNamePattern";
	private static final String VARS = "vars";
	private static final String VARVALUE = "varValue";
	private static java.util.logging.Logger mLogger = java.util.logging.Logger
			.getLogger("com.opentext.rest.services.SystemServices");

	private LicenceFactory mLF;
	private ExceptionMessagesFormatter mFormatter;
	private boolean mDynamicTickets;

	public SystemServices() throws Exception {
		mLogger.info(START);

		try {
			mLF = LicenceFactory.getInstance();
		} catch (Exception e) {
			mLogger.severe(e.toString());
			throw e;
		}
		try {
			mFormatter = ExceptionMessagesFormatter.getInstance();
		} catch (Exception e) {
			mLogger.severe(e.toString());
			throw e;
		}

		mLogger.info(COMPLETE);
	}

	public String login(String userID, String password, List<com.vignette.idm.server.NameValue> options)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException,
			InvalidCredentialsException, NoAvailableLicenceException, PasswordExpiredException, IDMRepositoryException,
			GeneralException {

		final String METHODNAME = "login";
		String license;
		try {
			try {
				// Validate arguments.
				if (userID == null || password == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}
				userID = Validator.validateString(SERVICENAME, METHODNAME, USERID, userID, false, false);

				password = Validator.validateString(SERVICENAME, METHODNAME, PASSWORD, password, false, true);

				validateLoginOptions(METHODNAME, options);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(USERID, userID, ", ");
					msg.append(PASSWORD, "*", ", ");
					msg.append(OPTIONS, options, "");
					mLogger.info(msg.toString());
				}

				// Convert arguments.
				Properties opts = SysServiceUtils.transformOptions(options);

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl("default", userID, password, opts);

				license = mLF.newLicence(repo.getTicket(), mDynamicTickets);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, "");
					mLogger.info(msg.toString());
				}

				// Return results.
				return license;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (UnknownOptionNameException e) {
				throw e;
			} catch (UnknownOptionValueException e) {
				throw e;
			} catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				String errorParam1 = e.getParameter(0);
				String errorParam2 = e.getParameter(1);
				if (errorDef.equals(TTCommonErrors.INVALID_LOGIN)) {
					InvalidCredentialsExceptionFaultDetail faultDetail = new InvalidCredentialsExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setUserName(userID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidCredentialsException(null, faultDetail);
				} else if (errorDef.equals(TTCommonErrors.NO_LICENSES)) {
					NoAvailableLicenceExceptionFaultDetail faultDetail = new NoAvailableLicenceExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new NoAvailableLicenceException(null, faultDetail);
				} else if (errorDef.equals(TTCommonErrors.LICENSE_EXPIRED)) {
					PasswordExpiredExceptionFaultDetail faultDetail = new PasswordExpiredExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setUserName(userID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new PasswordExpiredException(null, faultDetail);
				} else if (errorParam1 != null && errorParam2 != null) {
					IDMRepositoryExceptionFaultDetail faultDetail = new IDMRepositoryExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setErrorCode(errorParam2);
					faultDetail.setErrorNum(Converter.parseInt(errorParam1, 0));
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new IDMRepositoryException(null, faultDetail);
				} else {
					GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
					faultDetail.setCallStack(Converter.getCallStack(e));
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new GeneralException(null, faultDetail);
				}
			} catch (Exception e) {
				GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
				faultDetail.setCallStack(null);
				faultDetail.setMessage(e.getMessage());
				faultDetail.setWsMethodName(METHODNAME);
				faultDetail.setWsName(SERVICENAME);
				throw new GeneralException(null, faultDetail);
			}
		} catch (InvalidParameterException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setParameterName(e.getFaultInfo().getParameterName());
			faultDetail.setParameterValue(e.getFaultInfo().getParameterValue());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new InvalidParameterException(null, faultDetail);
		} catch (UnknownOptionNameException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownOptionNameExceptionFaultDetail faultDetail = new UnknownOptionNameExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setOptionName(e.getFaultInfo().getOptionName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownOptionNameException(null, faultDetail);
		} catch (UnknownOptionValueException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownOptionValueExceptionFaultDetail faultDetail = new UnknownOptionValueExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setOptionName(e.getFaultInfo().getOptionName());
			faultDetail.setOptionValue(e.getFaultInfo().getOptionValue());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownOptionValueException(null, faultDetail);
		} catch (InvalidCredentialsException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			InvalidCredentialsExceptionFaultDetail faultDetail = new InvalidCredentialsExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setUserName(e.getFaultInfo().getUserName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new InvalidCredentialsException(null, faultDetail);
		} catch (NoAvailableLicenceException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			NoAvailableLicenceExceptionFaultDetail faultDetail = new NoAvailableLicenceExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new NoAvailableLicenceException(null, faultDetail);
		} catch (PasswordExpiredException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			PasswordExpiredExceptionFaultDetail faultDetail = new PasswordExpiredExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setUserName(e.getFaultInfo().getUserName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new PasswordExpiredException(null, faultDetail);
		} catch (IDMRepositoryException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			IDMRepositoryExceptionFaultDetail faultDetail = new IDMRepositoryExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setErrorCode(e.getFaultInfo().getErrorCode());
			faultDetail.setErrorNum(e.getFaultInfo().getErrorNum());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new IDMRepositoryException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}

	public void logout(String license)
			throws InvalidParameterException, LicenseException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "logout";

		try {
			try {
				license = Validator.validateLicense(SERVICENAME, METHODNAME, LICENSE, license);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				repo.close();
				mLF.deleteLicence(license);

				mLogger.info(COMPLETE);

				return;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (LicenseException e) {
				throw e;
			} catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				String errorParam1 = e.getParameter(0);
				String errorParam2 = e.getParameter(1);
				if (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
					LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new LicenseException(null, faultDetail);
				} else if (errorParam1 != null && errorParam2 != null) {
					IDMRepositoryExceptionFaultDetail faultDetail = new IDMRepositoryExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setErrorCode(errorParam2);
					faultDetail.setErrorNum(Converter.parseInt(errorParam1, 0));
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new IDMRepositoryException(null, faultDetail);
				} else {
					GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new GeneralException(null, faultDetail);
				}
			} catch (Exception e) {
				GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
				faultDetail.setCallStack(null);
				faultDetail.setMessage(e.getMessage());
				faultDetail.setWsMethodName(METHODNAME);
				faultDetail.setWsName(SERVICENAME);
				throw new GeneralException(null, faultDetail);
			}
		} catch (InvalidParameterException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setParameterName(e.getFaultInfo().getParameterName());
			faultDetail.setParameterValue(e.getFaultInfo().getParameterValue());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new InvalidParameterException(null, faultDetail);
		} catch (LicenseException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (IDMRepositoryException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			IDMRepositoryExceptionFaultDetail faultDetail = new IDMRepositoryExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setErrorCode(e.getFaultInfo().getErrorCode());
			faultDetail.setErrorNum(e.getFaultInfo().getErrorNum());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new IDMRepositoryException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}

	public boolean isValid(String license) throws InvalidParameterException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "isValid";

		try {
			try {
				license = Validator.validateString(SERVICENAME, METHODNAME, LICENSE, license, false, false);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				String idmLicense = mLF.getTicket(license);
				boolean valid;
				if (idmLicense != null) {
					IdmRepImpl repo = new IdmRepImpl(idmLicense);
					try {
						repo.refresh();
						valid = true;
					} catch (TTException e) {
						ITTErrorDef errorDef = e.getErrorDef();
						if (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
							valid = false;
						} else {
							throw e;
						}
					}
				} else {
					valid = false;
				}

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(VALID, valid, "");
					mLogger.info(msg.toString());
				}

				// Return results.
				return valid;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (TTException e) {
				String errorParam1 = e.getParameter(0);
				String errorParam2 = e.getParameter(1);
				if (errorParam1 != null && errorParam2 != null) {
					IDMRepositoryExceptionFaultDetail faultDetail = new IDMRepositoryExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setErrorCode(errorParam2);
					faultDetail.setErrorNum(Converter.parseInt(errorParam1, 0));
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new IDMRepositoryException(null, faultDetail);
				} else {
					GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new GeneralException(null, faultDetail);
				}
			} catch (Exception e) {
				GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
				faultDetail.setCallStack(null);
				faultDetail.setMessage(e.getMessage());
				faultDetail.setWsMethodName(METHODNAME);
				faultDetail.setWsName(SERVICENAME);
				throw new GeneralException(null, faultDetail);
			}
		} catch (InvalidParameterException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setParameterName(e.getFaultInfo().getParameterName());
			faultDetail.setParameterValue(e.getFaultInfo().getParameterValue());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new InvalidParameterException(null, faultDetail);
		} catch (IDMRepositoryException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			IDMRepositoryExceptionFaultDetail faultDetail = new IDMRepositoryExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setErrorCode(e.getFaultInfo().getErrorCode());
			faultDetail.setErrorNum(e.getFaultInfo().getErrorNum());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new IDMRepositoryException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}

	public String getConfigVar(String license, String varName) throws InvalidParameterException, LicenseException,
			UnknownConfigVarException, IDMRepositoryException, GeneralException {
		String varValue = "";
		final String METHODNAME = "getConfigVar";

		try {
			try {
				// Validate arguments.
				if (varName == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

				String localLicense = new String();

			license = Validator.validateLicense(SERVICENAME, METHODNAME, LICENSE, license);
				localLicense = license;

				varName = Validator.validateString(SERVICENAME, METHODNAME, VARNAME, varName, false, false);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(VARNAME, varName, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(localLicense));
				Misc misc = new Misc(repo);
				varValue = misc.getConfigVar(varName);
				license = localLicense;

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, localLicense, ", ");
					msg.append(VARVALUE, varValue, "");
					mLogger.info(msg.toString());
				}

				return varValue;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (LicenseException e) {
				throw e;
			} catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				String errorParam1 = e.getParameter(0);
				String errorParam2 = e.getParameter(1);
				if (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
					LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new LicenseException(null, faultDetail);
				} else if (errorDef.equals(TTCommonErrors.INVALID_ARGUMENT)) {
					UnknownConfigVarExceptionFaultDetail faultDetail = new UnknownConfigVarExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setConfigVarName(varName);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownConfigVarException(null, faultDetail);
				} else if (errorParam1 != null && errorParam2 != null) {
					IDMRepositoryExceptionFaultDetail faultDetail = new IDMRepositoryExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setErrorCode(errorParam2);
					faultDetail.setErrorNum(Converter.parseInt(errorParam1, 0));
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new IDMRepositoryException(null, faultDetail);
				} else {
					GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new GeneralException(null, faultDetail);
				}
			} catch (Exception e) {
				GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
				faultDetail.setCallStack(null);
				faultDetail.setMessage(e.getMessage());
				faultDetail.setWsMethodName(METHODNAME);
				faultDetail.setWsName(SERVICENAME);
				throw new GeneralException(null, faultDetail);
			}
		} catch (InvalidParameterException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setParameterName(e.getFaultInfo().getParameterName());
			faultDetail.setParameterValue(e.getFaultInfo().getParameterValue());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new InvalidParameterException(null, faultDetail);
		} catch (LicenseException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (UnknownConfigVarException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownConfigVarExceptionFaultDetail faultDetail = new UnknownConfigVarExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setConfigVarName(e.getFaultInfo().getConfigVarName());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownConfigVarException(null, faultDetail);
		} catch (IDMRepositoryException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			IDMRepositoryExceptionFaultDetail faultDetail = new IDMRepositoryExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setErrorCode(e.getFaultInfo().getErrorCode());
			faultDetail.setErrorNum(e.getFaultInfo().getErrorNum());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new IDMRepositoryException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}

	public List<NameValue> getConfigVars(String license, String varNamePattern) throws InvalidParameterException,
			LicenseException, UnknownVarNamePatternException, IDMRepositoryException, GeneralException {
		List<NameValue> vars;
		final String METHODNAME = "getConfigVars";

		try {
			try {
				// Validate arguments.
				if (varNamePattern == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

				String localLicense = new String();

				license = Validator.validateLicense(SERVICENAME, METHODNAME, LICENSE, license);
				localLicense = license;

				varNamePattern = Validator.validateString(SERVICENAME, METHODNAME, VARNAMEPATTERN, varNamePattern,
						false, false);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, localLicense, ", ");
					msg.append(VARNAMEPATTERN, varNamePattern, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(localLicense));
				Misc misc = new Misc(repo);
				NameValue[] commonVars = misc.getConfigVars(varNamePattern);

				// Convert results.
				vars = new ArrayList<NameValue>();
				vars.addAll(SysServiceUtils.transformConfigVars(METHODNAME, varNamePattern, commonVars));

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, localLicense, ", ");
					msg.append(VARS, vars, "");
					mLogger.info(msg.toString());
				}

				return vars;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				String errorParam1 = e.getParameter(0);
				String errorParam2 = e.getParameter(1);
				if (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
					LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new LicenseException(null, faultDetail);
				} else if (errorDef.equals(TTCommonErrors.INVALID_ARGUMENT)) {
					UnknownVarNamePatternExceptionFaultDetail faultDetail = new UnknownVarNamePatternExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setVarNamePattern(VARNAMEPATTERN);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownVarNamePatternException(null, faultDetail);
				} else if (errorParam1 != null && errorParam2 != null) {
					IDMRepositoryExceptionFaultDetail faultDetail = new IDMRepositoryExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setErrorCode(errorParam2);
					faultDetail.setErrorNum(Converter.parseInt(errorParam1, 0));
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new IDMRepositoryException(null, faultDetail);
				} else {
					GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new GeneralException(null, faultDetail);
				}
			} catch (Exception e) {
				GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
				faultDetail.setCallStack(null);
				faultDetail.setMessage(e.getMessage());
				faultDetail.setWsMethodName(METHODNAME);
				faultDetail.setWsName(SERVICENAME);
				throw new GeneralException(null, faultDetail);
			}
		} catch (InvalidParameterException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setParameterName(e.getFaultInfo().getParameterName());
			faultDetail.setParameterValue(e.getFaultInfo().getParameterValue());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new InvalidParameterException(null, faultDetail);
		} catch (LicenseException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (UnknownVarNamePatternException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownVarNamePatternExceptionFaultDetail faultDetail = new UnknownVarNamePatternExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setVarNamePattern(e.getFaultInfo().getVarNamePattern());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownVarNamePatternException(null, faultDetail);
		} catch (IDMRepositoryException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			IDMRepositoryExceptionFaultDetail faultDetail = new IDMRepositoryExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setErrorCode(e.getFaultInfo().getErrorCode());
			faultDetail.setErrorNum(e.getFaultInfo().getErrorNum());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new IDMRepositoryException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}

	public String logError(String license, String message, String reason)
			throws InvalidParameterException, LicenseException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "logError";

		try {
			try {
				// Validate arguments.
				if (message == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

				String localLicense = new String();

				license = Validator.validateLicense(SERVICENAME, METHODNAME, LICENSE, license);
				localLicense = license;

				message = Validator.validateString(SERVICENAME, METHODNAME, MESSAGE, message, false, true);

				reason = Validator.validateString(SERVICENAME, METHODNAME, REASON, reason, true, true);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(MESSAGE, message, ", ");
					msg.append(REASON, reason, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(localLicense));
				Misc misc = new Misc(repo);
				misc.logError(message, reason);

				// Convert results.
				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, localLicense, "");
					mLogger.info(msg.toString());
				}

				return license;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (LicenseException e) {
				throw e;
			} catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				String errorParam1 = e.getParameter(0);
				String errorParam2 = e.getParameter(1);
				if (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
					LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new LicenseException(null, faultDetail);
				} else if (errorParam1 != null && errorParam2 != null) {
					IDMRepositoryExceptionFaultDetail faultDetail = new IDMRepositoryExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setErrorCode(errorParam2);
					faultDetail.setErrorNum(Converter.parseInt(errorParam1, 0));
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new IDMRepositoryException(null, faultDetail);
				} else {
					GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new GeneralException(null, faultDetail);
				}
			} catch (Exception e) {
				GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
				faultDetail.setCallStack(null);
				faultDetail.setMessage(e.getMessage());
				faultDetail.setWsMethodName(METHODNAME);
				faultDetail.setWsName(SERVICENAME);
				throw new GeneralException(null, faultDetail);
			}
		} catch (InvalidParameterException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setParameterName(e.getFaultInfo().getParameterName());
			faultDetail.setParameterValue(e.getFaultInfo().getParameterValue());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new InvalidParameterException(null, faultDetail);
		} catch (LicenseException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (IDMRepositoryException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			IDMRepositoryExceptionFaultDetail faultDetail = new IDMRepositoryExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setErrorCode(e.getFaultInfo().getErrorCode());
			faultDetail.setErrorNum(e.getFaultInfo().getErrorNum());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new IDMRepositoryException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}

	public String logEvent(String license, String category, String name, List<com.vignette.idm.server.NameValue> fields)
			throws InvalidParameterException, LicenseException, UnknownEventException, UnknownEventFieldException,
			IDMRepositoryException, GeneralException {
		final String METHODNAME = "logEvent";

		try {
			try {
				// Validate arguments.
				if (category == null || name == null || fields == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

				String localLicense = new String();

				license = Validator.validateLicense(SERVICENAME, METHODNAME, LICENSE, license);
				localLicense = license;

				category = Validator.validateString(SERVICENAME, METHODNAME, CATEGORY, category, false, false);

				name = Validator.validateString(SERVICENAME, METHODNAME, NAME, name, false, false);

				Validator.validateNameValueList(SERVICENAME, METHODNAME, FIELDS, fields, false, false, false, true);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(CATEGORY, category, ", ");
					msg.append(NAME, name, ", ");
					msg.append(FIELDS, fields, "");
					mLogger.info(msg.toString());
				}

				// Convert arguments.
				String[] nvPair = SysServiceUtils.tranformFields(fields);

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(localLicense));
				Misc misc = new Misc(repo);
				misc.logEvent(category, name, nvPair);

				// Convert results.
				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, localLicense, "");
					mLogger.info(msg.toString());
				}

				return license;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (LicenseException e) {
				throw e;
			} catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				String errorParam1 = e.getParameter(0);
				String errorParam2 = e.getParameter(1);
				if (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
					LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new LicenseException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.UNKNOWN_EVENT_CATEGORY_OR_NAME)) {

					UnknownEventExceptionFaultDetail faultDetail = new UnknownEventExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setCategory(category);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setName(name);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownEventException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.UNKNOWN_EVENT_FIELD)) {
					UnknownEventFieldExceptionFaultDetail faultDetail = new UnknownEventFieldExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setCategory(category);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setName(name);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownEventFieldException(null, faultDetail);
				} else if (errorParam1 != null && errorParam2 != null) {
					IDMRepositoryExceptionFaultDetail faultDetail = new IDMRepositoryExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setErrorCode(errorParam2);
					faultDetail.setErrorNum(Converter.parseInt(errorParam1, 0));
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new IDMRepositoryException(null, faultDetail);
				} else {
					GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new GeneralException(null, faultDetail);
				}
			} catch (Exception e) {
				GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
				faultDetail.setCallStack(null);
				faultDetail.setMessage(e.getMessage());
				faultDetail.setWsMethodName(METHODNAME);
				faultDetail.setWsName(SERVICENAME);
				throw new GeneralException(null, faultDetail);
			}
		} catch (InvalidParameterException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setParameterName(e.getFaultInfo().getParameterName());
			faultDetail.setParameterValue(e.getFaultInfo().getParameterValue());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new InvalidParameterException(null, faultDetail);
		} catch (LicenseException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (UnknownEventException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownEventExceptionFaultDetail faultDetail = new UnknownEventExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setCategory(e.getFaultInfo().getCategory());
			faultDetail.setMessage(msg);
			faultDetail.setName(e.getFaultInfo().getName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownEventException(null, faultDetail);
		} catch (UnknownEventFieldException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownEventFieldExceptionFaultDetail faultDetail = new UnknownEventFieldExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setCategory(e.getFaultInfo().getCategory());
			faultDetail.setMessage(msg);
			faultDetail.setName(e.getFaultInfo().getName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownEventFieldException(null, faultDetail);
		} catch (IDMRepositoryException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			IDMRepositoryExceptionFaultDetail faultDetail = new IDMRepositoryExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setErrorCode(e.getFaultInfo().getErrorCode());
			faultDetail.setErrorNum(e.getFaultInfo().getErrorNum());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new IDMRepositoryException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}

	/*
	 * public void logMessage(String license, TraceSeverity severity, String
	 * message, String filename) throws InvalidParameterException, LicenseException,
	 * IDMRepositoryException, GeneralException { final String METHODNAME =
	 * "logMessage";
	 * 
	 * try { try { // Validate arguments. if (message == null || filename == null) {
	 * InvalidParameterExceptionFaultDetail faultDetail = new
	 * InvalidParameterExceptionFaultDetail(); faultDetail.setCallStack(null);
	 * faultDetail.setMessage(null); faultDetail.setParameterName(PARAMETERS);
	 * faultDetail.setParameterValue(null); faultDetail.setWsMethodName(METHODNAME);
	 * faultDetail.setWsName(SERVICENAME); throw new InvalidParameterException(null,
	 * faultDetail); }
	 * 
	 * SingleSignOn sso = new SingleSignOn(context.getMessageContext());
	 * Holder<String> localLicense = new Holder<String>();
	 * 
	 * if (sso.isSingleSignOn(localLicense) == false) { license =
	 * Validator.validateLicense(SERVICENAME, METHODNAME, LICENSE, license);
	 * localLicense.value = license; } if (severity == null) {
	 * InvalidParameterExceptionFaultDetail faultDetail = new
	 * InvalidParameterExceptionFaultDetail(); faultDetail.setCallStack(null);
	 * faultDetail.setMessage(null); faultDetail.setParameterName(PARAMETERS);
	 * faultDetail.setParameterValue(null); faultDetail.setWsMethodName(METHODNAME);
	 * faultDetail.setWsName(SERVICENAME); throw new InvalidParameterException(null,
	 * faultDetail); } message = Validator.validateString(SERVICENAME, METHODNAME,
	 * MESSAGE, message, false, true);
	 * 
	 * filename = Validator.validateString(SERVICENAME, METHODNAME, FILENAME,
	 * filename, false, false);
	 * 
	 * if (mLogger.isLoggable(Level.INFO)) { StringBufferWS msg = new
	 * StringBufferWS(START); msg.append(LICENSE, license, ", ");
	 * msg.append(SEVERITY, severity, ", "); msg.append(MESSAGE, message, ", ");
	 * msg.append(FILENAME, filename, ""); mLogger.info(msg.toString()); }
	 * 
	 * // Perform operation. IdmRepImpl repo = new IdmRepImpl(
	 * mLF.getTicket(localLicense.value)); Misc misc = new Misc(repo);
	 * misc.logMessage(severity.value(), message, filename);
	 * 
	 * // Convert results. license = sso.updateLicense(localLicense);
	 * 
	 * if (mLogger.isLoggable(Level.INFO)) { StringBufferWS msg = new
	 * StringBufferWS(COMPLETE); msg.append(LICENSE, localLicense.value, "");
	 * mLogger.info(msg.toString()); }
	 * 
	 * return; } catch (InvalidParameterException e) { throw e; } catch
	 * (LicenseException e) { throw e; } catch (TTException e) { ITTErrorDef
	 * errorDef = e.getErrorDef(); String errorParam1 = e.getParameter(0); String
	 * errorParam2 = e.getParameter(1); if
	 * (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
	 * LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
	 * faultDetail.setCallStack(null); faultDetail.setMessage(e.getMessage());
	 * faultDetail.setWsMethodName(METHODNAME); faultDetail.setWsName(SERVICENAME);
	 * throw new LicenseException(null, faultDetail); } else if (errorParam1 != null
	 * && errorParam2 != null) { IDMRepositoryExceptionFaultDetail faultDetail = new
	 * IDMRepositoryExceptionFaultDetail(); faultDetail.setCallStack(null);
	 * faultDetail.setErrorCode(errorParam2);
	 * faultDetail.setErrorNum(Converter.parseInt(errorParam1, 0));
	 * faultDetail.setMessage(e.getMessage());
	 * faultDetail.setWsMethodName(METHODNAME); faultDetail.setWsName(SERVICENAME);
	 * throw new IDMRepositoryException(null, faultDetail); } else {
	 * GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
	 * faultDetail.setCallStack(null); faultDetail.setMessage(e.getMessage());
	 * faultDetail.setWsMethodName(METHODNAME); faultDetail.setWsName(SERVICENAME);
	 * throw new GeneralException(null, faultDetail); } } catch (Exception e) {
	 * GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
	 * faultDetail.setCallStack(null); faultDetail.setMessage(e.getMessage());
	 * faultDetail.setWsMethodName(METHODNAME); faultDetail.setWsName(SERVICENAME);
	 * throw new GeneralException(null, faultDetail); } } catch
	 * (InvalidParameterException e) { String msg = format(e.getFaultInfo());
	 * mLogger.severe(msg);
	 * 
	 * InvalidParameterExceptionFaultDetail faultDetail = new
	 * InvalidParameterExceptionFaultDetail();
	 * faultDetail.setCallStack(Converter.getCallStack(e));
	 * faultDetail.setMessage(msg);
	 * faultDetail.setParameterName(e.getFaultInfo().getParameterName());
	 * faultDetail.setParameterValue(e.getFaultInfo().getParameterValue());
	 * faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
	 * faultDetail.setWsName(e.getFaultInfo().getWsName()); throw new
	 * InvalidParameterException(null, faultDetail); } catch (LicenseException e) {
	 * String msg = format(e.getFaultInfo()); mLogger.severe(msg);
	 * 
	 * LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
	 * faultDetail.setCallStack(Converter.getCallStack(e));
	 * faultDetail.setMessage(msg);
	 * faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
	 * faultDetail.setWsName(e.getFaultInfo().getWsName()); throw new
	 * LicenseException(null, faultDetail); } catch (IDMRepositoryException e) {
	 * String msg = format(e.getFaultInfo()); mLogger.severe(msg);
	 * 
	 * IDMRepositoryExceptionFaultDetail faultDetail = new
	 * IDMRepositoryExceptionFaultDetail();
	 * faultDetail.setCallStack(Converter.getCallStack(e));
	 * faultDetail.setErrorCode(e.getFaultInfo().getErrorCode());
	 * faultDetail.setErrorNum(e.getFaultInfo().getErrorNum());
	 * faultDetail.setMessage(msg);
	 * faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
	 * faultDetail.setWsName(e.getFaultInfo().getWsName()); throw new
	 * IDMRepositoryException(null, faultDetail); } catch (GeneralException e) {
	 * String msg = format(e.getFaultInfo()); mLogger.severe(msg);
	 * 
	 * GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
	 * faultDetail.setCallStack(Converter.getCallStack(e));
	 * faultDetail.setMessage(msg);
	 * faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
	 * faultDetail.setWsName(e.getFaultInfo().getWsName()); throw new
	 * GeneralException(null, faultDetail); } }
	 * 
	 * private ArrayList<com.opentext.common.NameValue> transformConfigVars( String
	 * aMethodName, String aVarNamePattern, NameValue[] aVars) {
	 * ArrayList<com.opentext.common.NameValue> vars = new
	 * ArrayList<com.opentext.common.NameValue>( aVars.length); for (int i = 0; i <
	 * aVars.length; i++) { com.opentext.common.NameValue nv = new
	 * com.opentext.common.NameValue(); nv.setName(aVars[i].getName());
	 * nv.setValue(aVars[i].getValue()); vars.add(nv); } return vars; }
	 */

	private void validateLoginOptions(String aMethodName, List<com.vignette.idm.server.NameValue> aOptions)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException {
		// Perform general name/value pair validation.
		Validator.validateNameValueList(SERVICENAME, aMethodName, OPTIONS, aOptions, true, true, false, true);

		// Perform specific option validation.
		mDynamicTickets = true;
		if (aOptions != null) {
			ListIterator<com.vignette.idm.server.NameValue> itor = aOptions.listIterator();
			while (itor.hasNext()) {
				com.vignette.idm.server.NameValue opt = itor.next();
				String optionName = opt.getName();
				String optionValue = opt.getValue();

				// Check for valid values of NewPassword option.
				if (optionName.equals(NEWPASSWORD) == true) {
					;
				}
				// Check for valid values of NonDynamicTicket option.
				else if (optionName.equals(NONDYNAMICTICKETS) == true) {
					if (optionValue.length() == 0) {
						InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
						faultDetail.setCallStack(null);
						faultDetail.setMessage(null);
						faultDetail.setParameterName(optionName);
						faultDetail.setParameterValue(optionValue);
						faultDetail.setWsMethodName(aMethodName);
						faultDetail.setWsName(SERVICENAME);
						throw new InvalidParameterException(null, faultDetail);
					} else if (optionValue.equalsIgnoreCase(TRUE) == false
							&& optionValue.equalsIgnoreCase(FALSE) == false) {
						UnknownOptionValueExceptionFaultDetail faultDetail = new UnknownOptionValueExceptionFaultDetail();
						faultDetail.setCallStack(null);
						faultDetail.setMessage(null);
						faultDetail.setOptionName(optionName);
						faultDetail.setOptionValue(optionValue);
						faultDetail.setWsMethodName(aMethodName);
						faultDetail.setWsName(SERVICENAME);
						throw new UnknownOptionValueException(null, faultDetail);
					}
					mDynamicTickets = !Boolean.valueOf(optionValue).booleanValue();
				}
				// Not a valid option.
				else {
					UnknownOptionNameExceptionFaultDetail faultDetail = new UnknownOptionNameExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setOptionName(optionName);
					faultDetail.setWsMethodName(aMethodName);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownOptionNameException(null, faultDetail);
				}
			}
		}
	}

	private String format(GeneralExceptionFaultDetail e) {
		try {
			return mFormatter.format(e);
		} catch (FormatterException fe) {
			return e.getMessage();
		}
	}

}
