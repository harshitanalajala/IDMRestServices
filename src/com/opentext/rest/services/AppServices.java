package com.opentext.rest.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;

import javax.activation.DataHandler;

import com.opentext.rest.utils.AppServiceUtils;
import com.towertechnology.common.errorhandling.ITTErrorDef;
import com.towertechnology.common.errorhandling.TTCommonErrors;
import com.towertechnology.common.errorhandling.TTException;
import com.towertechnology.common.errorhandling.TTRepositoryErrors;
import com.vignette.idm.common.ApplicationEntry;
import com.vignette.idm.server.AppFieldDef;
import com.vignette.idm.server.AppList;
import com.vignette.idm.server.DatabaseException;
import com.vignette.idm.server.DatabaseExceptionFaultDetail;
import com.vignette.idm.server.GeneralException;
import com.vignette.idm.server.GeneralExceptionFaultDetail;
import com.vignette.idm.server.IDMRepositoryException;
import com.vignette.idm.server.IDMRepositoryExceptionFaultDetail;
import com.vignette.idm.server.InvalidParameterException;
import com.vignette.idm.server.InvalidParameterExceptionFaultDetail;
import com.vignette.idm.server.LicenseException;
import com.vignette.idm.server.LicenseExceptionFaultDetail;
import com.vignette.idm.server.NameValue;
import com.vignette.idm.server.PermissionException;
import com.vignette.idm.server.PermissionExceptionFaultDetail;
import com.vignette.idm.server.SearchResult;
import com.vignette.idm.server.TemplateDef;
import com.vignette.idm.server.UnknownAppException;
import com.vignette.idm.server.UnknownAppExceptionFaultDetail;
import com.vignette.idm.server.UnknownDocIDException;
import com.vignette.idm.server.UnknownDocIDExceptionFaultDetail;
import com.vignette.idm.server.UnknownOptionNameException;
import com.vignette.idm.server.UnknownOptionNameExceptionFaultDetail;
import com.vignette.idm.server.UnknownOptionValueException;
import com.vignette.idm.server.UnknownOptionValueExceptionFaultDetail;
import com.vignette.idm.server.UnknownTemplateException;
import com.vignette.idm.server.UnknownTemplateExceptionFaultDetail;
import com.vignette.idm.server.common.Converter;
import com.vignette.idm.server.common.ExceptionMessagesFormatter;
import com.vignette.idm.server.common.LicenceFactory;
import com.vignette.idm.server.common.StringBufferWS;
import com.vignette.idm.server.common.Validator;
import com.vignette.idm.server.common.olf.FormatterException;
import com.vignette.idm.services.ColumnDef;
import com.vignette.idm.services.DocId;
import com.vignette.idm.services.DocImporter;
import com.vignette.idm.services.DoctypeSecurity;
import com.vignette.idm.services.General;
import com.vignette.idm.services.IdmRepImpl;
import com.vignette.idm.services.Logger;
import com.vignette.idm.services.Misc;
import com.vignette.idm.services.RecordCollection;
import com.vignette.idm.services.SQLStatement;

public class AppServices {

	////////////////////////////////////////////////////////////////////////////
	// Constant Strings
	////////////////////////////////////////////////////////////////////////////
	private static final String ALL = "all";
	private static final String ANAME = "aname";
	private static final String AND = "and";
	private static final String ANY = "any";
	private static final String APPDEFS = "appDefs";
	private static final String APPNAME = "appName";
	private static final String BETWEEN = "between";
	private static final String COL = "col";
	private static final String COMPLETE = "Complete: ";
	private static final String CONDITION = "condition";
	private static final String CONTENT = "content";
	private static final String CONTENTTYPE = "contentType";
	private static final String CONV = "conv";
	private static final String COPYIN_POOL = "COPYIN_POOL";
	private static final String COPYOUT_POOL = "COPYOUT_POOL";
	private static final String CREATE = "CREATE";
	private static final String DEFAULTAPP = "defaultApp";
	private static final String DEFAULT_APPLICATION = "default_application";
	private static final String DELETE = "DELETE";
	private static final String DELETE_FROM = "delete from";
	private static final String DELIMITERS_COND = " <>=!(),^";
	private static final String DELIMITERS_ORDER = " ,";
	private static final String DFLTVAL = "dfltval";
	private static final String DISKSET = "DiskSet";
	private static final String DISPLAYFIELDS = "displayFields";
	private static final String DOCID = "docID";
	private static final String DOCTYPEPRIV = "DoctypePriv";
	private static final String ENTRYFIELDS = "entryFields";
	private static final String EQ = "=";
	private static final String EXISTS = "exists";
	private static final String FALSE = "false";
	private static final String FDATA = "fdata";
	private static final String FIELDNAMES = "fieldNames";
	private static final String FIELDS = "fields";
	private static final String FNUM = "fnum";
	private static final String FROM = "from";
	private static final String FROW = "frow";
	private static final String FTBL = "ftbl";
	private static final String FTYPE = "ftype";
	private static final String GT = ">";
	private static final String GTE = ">=";
	private static final String HASH_DOCID = "#DOCID#";
	private static final String HASH_DOCID_DESC = "Document Identifier";
	private static final String HASMORERECORDS = "hasMoreRecords";
	private static final String HOLDING_POOL = "HOLDING_POOL";
	private static final String IFNDS = "ifnds";
	private static final String IFNID = "ifnid";
	private static final String IN = "in";
	private static final String INSERT_INTO = "insert into";
	private static final String IOTYPE = "iotype";
	private static final String IS = "is";
	private static final String ISNOTNULL = "is not null";
	private static final String ISNULL = "is null";
	private static final String LEN = "len";
	private static final String LICENSE = "license";
	private static final String LIKE = "like";
	private static final String LT = "<";
	private static final String LTE = "<=";
	private static final String MATCH = "match";
	private static final String MAXRECORDS = "maxRecords";
	private static final String MODIFY = "MODIFY";
	private static final String NEQ = "<>";
	private static final String NEWDOC = "NewDoc";
	private static final String NEWDOCID = "newDocID";
	private static final String NOT = "not";
	private static final String NO_SECURITY = "no security";
	private static final String NPAGES = "npages";
	private static final String NULL = "null";
	private static final String NUMDELETED = "numDeleted";
	private static final String NUMPAGES = "numPages";
	private static final String NUMRECORDS = "numRecords";
	private static final String NUMUPDATED = "numUpdated";
	private static final String OFFSET = "offset";
	private static final String OPTIONS = "options";
	private static final String OR = "or";
	private static final String ORDERBY = "orderBy";
	private static final String ORDER_BY = "order by";
	private static final String OUTPUT_POOL = "OUTPUT_POOL";
	private static final String OVERLAPS = "overlaps";
	private static final String PARAMETERS = "parameters";
	private static final String PIC = "pic";
	private static final String POOL = "Pool";
	private static final String PRINT_POOL = "PRINT_POOL";
	private static final String P_BLANKSRCH = "enable_blanksearches";
	private static final String RECID = "#RECID#";
	private static final String ROW = "row";
	private static final String ROWS = "rows";
	private static final String SCAN_POOL = "SCAN_POOL";
	private static final String SELECT = "select";
	private static final String SERVICENAME = "AppServices";
	private static final String SET = "set";
	private static final String SLEVEL = "slevel";
	private static final String SOME = "some";
	private static final String START = "Started App Services: ";
	private static final String TEMPLATENAME = "templateName";
	private static final String TNAME = "tname";
	private static final String TOWID = "towid";
	private static final String TRUE = "true";
	private static final String UNIQUE = "unique";
	private static final String UPDATE = "update";
	private static final String VALIDFN = "validfn";
	private static final String VALUES = "values";
	private static final String VIEW = "VIEW";
	private static final String WHERE = "where";

	////////////////////////////////////////////////////////////////////////////
	// Constant Integers
	////////////////////////////////////////////////////////////////////////////
	private static final int F_INT16 = 4;
	private static final int F_INT32 = 5;
	private static final int F_UINT16 = 6;
	private static final int F_UINT32 = 7;
	private static final int F_DATE = 11;
	private static final int F_TIME = 12;
	private static final int F_RECCOL = 13;
	private static final int CREATE_ACTION = 0;
	private static final int EV_UWSADDTITLE = 0;
	private static final int EV_UWSDELTITLE = 1;
	private static final int EV_UWSMODTITLE = 2;

	////////////////////////////////////////////////////////////////////////////
	// Static Member Variables
	////////////////////////////////////////////////////////////////////////////
	private static java.util.logging.Logger mLogger = java.util.logging.Logger
			.getLogger("com.opentext.rest.services.AppServices");

	////////////////////////////////////////////////////////////////////////////
	// Instance Member Variables
	////////////////////////////////////////////////////////////////////////////
	private LicenceFactory mLF;
	private ExceptionMessagesFormatter mFormatter;
	private String mDoctypePriv;
	private boolean mNewDoc;
	private String mDiskset;
	private String mPool;

	public AppServices() throws Exception {
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

	public long countRecords(String license, String appName, String condition, List<NameValue> options, Long numRecords)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException, LicenseException,
			UnknownAppException, PermissionException, DatabaseException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "countRecords";

		try {
			String userID = null;
			try {
				// Validate arguments.
				if (appName == null) {
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

				appName = Validator.validateString(SERVICENAME, METHODNAME, APPNAME, appName, false, false);

				condition = AppServiceUtils.validateCondition(METHODNAME, condition, true, true, true);

				AppServiceUtils.validateNoOptions(METHODNAME, options);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(CONDITION, condition, ", ");
					msg.append(OPTIONS, options, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(localLicense));
				userID = repo.getUserId();
				RecordCollection collection = new RecordCollection(repo);
				Validator.checkApplication(SERVICENAME, METHODNAME, collection, appName);
				ColumnDef columns = collection.getFields(appName);

				ArrayList<String> fields = new ArrayList<String>();
				fields.add("count(*)");

				String dtWhereClause = AppServiceUtils.checkAccessPermission(METHODNAME, repo, appName,
						DoctypeSecurity.DOCTYPE_VIEW);
				SQLStatement statement = AppServiceUtils.constructSelect(appName, fields, condition, dtWhereClause,
						null, columns);
				com.vignette.idm.common.NameValue[][] rows = collection.executeQuery(statement, 0, 0);

				// Convert results.
				numRecords = Converter.parseLong(rows[0][0].getValue(), 0);
				license = localLicense;

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, localLicense, ", ");
					msg.append(NUMRECORDS, numRecords, "");
					mLogger.info(msg.toString());
				}

				return numRecords;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (UnknownOptionNameException e) {
				throw e;
			} catch (UnknownOptionValueException e) {
				throw e;
			} catch (LicenseException e) {
				throw e;
			} catch (UnknownAppException e) {
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
				} else if (errorDef.equals(TTRepositoryErrors.COLLECTION_PERMISSION_DENIED)) {
					PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
					faultDetail.setAppName(appName);
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setUserName(userID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new PermissionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DATABASE_ERROR)) {
					DatabaseExceptionFaultDetail faultDetail = new DatabaseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new DatabaseException(null, faultDetail);
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
				faultDetail.setCallStack(Converter.getCallStack(e));
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
		} catch (LicenseException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (UnknownAppException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownAppExceptionFaultDetail faultDetail = new UnknownAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownAppException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setUserName(e.getFaultInfo().getUserName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new PermissionException(null, faultDetail);
		} catch (DatabaseException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			DatabaseExceptionFaultDetail faultDetail = new DatabaseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new DatabaseException(null, faultDetail);
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

	public Long updateRecords(String license, String appName, List<NameValue> fields, String condition, Long numUpdated)
			throws InvalidParameterException, LicenseException, UnknownAppException, PermissionException,
			DatabaseException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "updateRecords";

		try {
			String userID = null;
			try {
				// Validate arguments.
				if (appName == null || fields == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

				appName = Validator.validateString(SERVICENAME, METHODNAME, APPNAME, appName, false, false);

				AppServiceUtils.validateFields(METHODNAME, fields, false);

				condition = AppServiceUtils.validateCondition(METHODNAME, condition, true, true, true);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(FIELDS, fields, ", ");
					msg.append(CONDITION, condition, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				userID = repo.getUserId();
				RecordCollection collection = new RecordCollection(repo);
				Validator.checkApplication(SERVICENAME, METHODNAME, collection, appName);
				ColumnDef columns = collection.getFields(appName);
				String dtWhereClause = AppServiceUtils.checkAccessPermission(METHODNAME, repo, appName,
						DoctypeSecurity.DOCTYPE_MODIFY);
				SQLStatement statement = AppServiceUtils.constructUpdate(appName, null, 0, fields, null, condition,
						dtWhereClause, columns);
				int count = collection.executeStatement(statement);

				// Log event.
				String[] eventData = new String[] { repo.getUserId(), repo.getTicket(), appName, "", "0", "Done" };
				Logger logger = new Logger(repo);
				logger.eventLog(EV_UWSMODTITLE, eventData);

				// Convert results.
				numUpdated = new Long(count);
				// license.value = sso.updateLicense(localLicense);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(NUMUPDATED, numUpdated, "");
					mLogger.info(msg.toString());
				}

				return numUpdated;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (UnknownAppException e) {
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
				} else if (errorDef.equals(TTRepositoryErrors.COLLECTION_PERMISSION_DENIED)) {
					PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
					faultDetail.setAppName(appName);
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setUserName(userID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new PermissionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DATABASE_ERROR)) {
					DatabaseExceptionFaultDetail faultDetail = new DatabaseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new DatabaseException(null, faultDetail);
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
				faultDetail.setCallStack(Converter.getCallStack(e));
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
		} catch (UnknownAppException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownAppExceptionFaultDetail faultDetail = new UnknownAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownAppException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setUserName(e.getFaultInfo().getUserName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new PermissionException(null, faultDetail);
		} catch (DatabaseException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			DatabaseExceptionFaultDetail faultDetail = new DatabaseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new DatabaseException(null, faultDetail);
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

	public Integer updateContent(String license, String appName, String docID, List<NameValue> fields, String condition,
			String contentType, byte[] content, List<NameValue> options, String newDocID, Integer numPages)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException, LicenseException,
			UnknownAppException, UnknownDocIDException, PermissionException, DatabaseException, IDMRepositoryException,
			GeneralException {
		final String METHODNAME = "updateContent";

		try {
			String userID = null;
			try {
				// Validate arguments.
				if (docID == null || contentType == null || content == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}


				appName = Validator.validateString(SERVICENAME, METHODNAME, APPNAME, appName, true, false);

				docID = AppServiceUtils.validateDocID(METHODNAME, docID);

				if (appName != null) {
					AppServiceUtils.validateFields(METHODNAME, fields, true);
				} else if (fields != null && fields.size() != 0) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(FIELDS);
					faultDetail.setParameterValue("null expected");
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}
				condition = AppServiceUtils.validateCondition(METHODNAME, condition, true, false, true);

				contentType = Validator.validateString(SERVICENAME, METHODNAME, CONTENTTYPE, contentType, false, false);

				Validator.validateByteArray(SERVICENAME, METHODNAME, CONTENT, content, false, false);
				validateContentOptions(METHODNAME, options, true, appName);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(DOCID, docID, ", ");
					msg.append(FIELDS, fields, ", ");
					msg.append(CONDITION, condition, ", ");
					msg.append(CONTENTTYPE, contentType, ", ");
					msg.append(CONTENT, content, ", ");
					msg.append(OPTIONS, options, "");
					mLogger.info(msg.toString());
				}

				org.omg.CORBA.StringHolder newDocIDHolder;
				int localNumPages;
				if (appName != null) {
					// Convert arguments.
					com.vignette.idm.common.NameValue[] commonFields = AppServiceUtils.transformFields(fields);

					// Perform operation.
					IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
					userID = repo.getUserId();
					RecordCollection collection = new RecordCollection(repo);
					Validator.checkApplication(SERVICENAME, METHODNAME, collection, appName);

					ColumnDef columns = collection.getFields(appName);
					String dtWhereClause = AppServiceUtils.checkAccessPermission(METHODNAME, repo, appName,
							DoctypeSecurity.DOCTYPE_MODIFY);
					General gen = new General(repo);
					if (mNewDoc && mDiskset == null) {
						mDiskset = gen.selectDiskset(appName, commonFields, columns);
					}
					DocImporter docImporter = new DocImporter(repo);
					newDocIDHolder = new org.omg.CORBA.StringHolder(mNewDoc ? mDiskset : docID);
					docImporter.importDocumentExt(newDocIDHolder, appName, mPool, content, contentType);
					localNumPages = gen.getDocPageCount(newDocIDHolder.value);
					SQLStatement statement = AppServiceUtils.constructUpdate(appName, newDocIDHolder.value,
							localNumPages, fields, docID, condition, dtWhereClause, columns);
					int count = collection.executeStatement(statement);

					// Log event.
					String[] eventData = new String[] { repo.getUserId(), repo.getTicket(), appName, "", "0", "Done" };
					Logger logger = new Logger(repo);
					logger.eventLog(EV_UWSMODTITLE, eventData);
				} else {
					// Perform operation.
					IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
					userID = repo.getUserId();
					String dtWhereClause = AppServiceUtils.checkAccessPermission(METHODNAME, repo, appName,
							DoctypeSecurity.DOCTYPE_MODIFY);
					DocImporter docImporter = new DocImporter(repo);
					newDocIDHolder = new org.omg.CORBA.StringHolder(mNewDoc ? mDiskset : docID);
					docImporter.importDocumentExt(newDocIDHolder, appName, mPool, content, contentType);
					General gen = new General(repo);
					localNumPages = gen.getDocPageCount(newDocIDHolder.value);
				}

				// Convert results.
				numPages = localNumPages;

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(NEWDOCID, newDocIDHolder.value, ", ");
					msg.append(NUMPAGES, numPages, "");
					mLogger.info(msg.toString());
				}

				return numPages;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (UnknownOptionNameException e) {
				throw e;
			} catch (UnknownOptionValueException e) {
				throw e;
			}

			catch (UnknownAppException e) {
				throw e;
			} catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				String errorParam1 = e.getParameter(0);
				String errorParam2 = e.getParameter(1);
				if (errorDef.equals(TTRepositoryErrors.DOCUMENT_ID_ILLEGAL)) {
					if (mDiskset != null) {
						InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
						faultDetail.setCallStack(null);
						faultDetail.setMessage(null);
						faultDetail.setParameterName(OPTIONS + "[" + DISKSET + "]");
						faultDetail.setParameterValue(mDiskset);
						faultDetail.setWsMethodName(METHODNAME);
						faultDetail.setWsName(SERVICENAME);
						throw new InvalidParameterException(null, faultDetail);
					} else {
						InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
						faultDetail.setCallStack(null);
						faultDetail.setMessage(null);
						faultDetail.setParameterName(DOCID);
						faultDetail.setParameterValue(docID);
						faultDetail.setWsMethodName(METHODNAME);
						faultDetail.setWsName(SERVICENAME);
						throw new InvalidParameterException(null, faultDetail);
					}
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_UNKNOWN)) {
					if (mDiskset != null) {
						UnknownOptionValueExceptionFaultDetail faultDetail = new UnknownOptionValueExceptionFaultDetail();
						faultDetail.setCallStack(null);
						faultDetail.setMessage(e.getMessage());
						faultDetail.setOptionName(DISKSET);
						faultDetail.setOptionValue(mDiskset);
						faultDetail.setWsMethodName(METHODNAME);
						faultDetail.setWsName(SERVICENAME);
						throw new UnknownOptionValueException(null, faultDetail);
					} else {
						UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
						faultDetail.setCallStack(null);
						faultDetail.setDocID(docID);
						faultDetail.setMessage(e.getMessage());
						faultDetail.setWsMethodName(METHODNAME);
						faultDetail.setWsName(SERVICENAME);
						throw new UnknownDocIDException(null, faultDetail);
					}
				} else if (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
					LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new LicenseException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.COLLECTION_PERMISSION_DENIED)
						|| errorDef.equals(TTRepositoryErrors.DOCUMENT_PERMISSION_DENIED)) {
					PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
					faultDetail.setAppName(appName);
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setUserName(userID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new PermissionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DATABASE_ERROR)) {
					DatabaseExceptionFaultDetail faultDetail = new DatabaseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new DatabaseException(null, faultDetail);
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
				faultDetail.setCallStack(Converter.getCallStack(e));
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
		} catch (LicenseException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (UnknownAppException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownAppExceptionFaultDetail faultDetail = new UnknownAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownAppException(null, faultDetail);
		} catch (UnknownDocIDException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownDocIDException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setUserName(e.getFaultInfo().getUserName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new PermissionException(null, faultDetail);
		} catch (DatabaseException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			DatabaseExceptionFaultDetail faultDetail = new DatabaseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new DatabaseException(null, faultDetail);
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

	/**
	 * This is a web service method implementation.
	 * 
	 * @return
	 */
	public Integer updateContentMTOM(String license, String appName, String docID, List<NameValue> fields,
			String condition, String contentType, DataHandler content, List<NameValue> options, String newDocID,
			Integer numPages) throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException,
			LicenseException, UnknownAppException, UnknownDocIDException, PermissionException, DatabaseException,
			IDMRepositoryException, GeneralException {
		final String METHODNAME = "updateContentMTOM";
		byte[] byteData = null;

		mLogger.info(METHODNAME);

		try {
			byteData = AppServiceUtils.getByteArrayFromDataHandler(content);
		} catch (IOException ex) {
			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(ex));
			faultDetail.setMessage(ex.getMessage());
			faultDetail.setWsMethodName(METHODNAME);
			faultDetail.setWsName(SERVICENAME);

			mLogger.severe(ex.getMessage());

			throw new GeneralException(null, faultDetail);
		}

		if (byteData != null) {
			numPages = updateContent(license, appName, docID, fields, condition, contentType, byteData, options,
					newDocID, numPages);
		}
		return numPages;
	}

	private String format(GeneralExceptionFaultDetail e) {
		try {
			return mFormatter.format(e);
		} catch (FormatterException fe) {
			return e.getMessage();
		}
	}

	public TemplateDef getTemplate(String license, String appName, String templateName, List<NameValue> options,
			TemplateDef templateDefs)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException, LicenseException,
			UnknownAppException, UnknownTemplateException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "getTemplate";

		try {
			try {
				// Validate arguments.
				if (appName == null || templateName == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}



				appName = Validator.validateString(SERVICENAME, METHODNAME, APPNAME, appName, false, false);

				templateName = Validator.validateString(SERVICENAME, METHODNAME, TEMPLATENAME, templateName, false,
						false);

				AppServiceUtils.validateGetTemplateOptions(METHODNAME, options);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(TEMPLATENAME, templateName, ", ");
					msg.append(OPTIONS, options, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				RecordCollection collection = new RecordCollection(repo);
				Validator.checkApplication(SERVICENAME, METHODNAME, collection, appName);
				ColumnDef appColumns = collection.getFields(appName);
				ColumnDef ftblColumns = collection.getFields(FTBL);
				ArrayList<String> fields = new ArrayList<String>();
				fields.add("*");

				String condition = ANAME + " = '" + appName + "' " + AND + " " + TNAME + " = '" + templateName + "'";
				String orderBy = FNUM;
				SQLStatement statement = AppServiceUtils.constructSelect(FTBL, fields, condition, "", orderBy,
						ftblColumns);
				com.vignette.idm.common.NameValue[][] ftblRows = collection.executeQuery(statement, 0, 0);
				if (ftblRows.length == 0) {
					UnknownTemplateExceptionFaultDetail faultDetail = new UnknownTemplateExceptionFaultDetail();
					faultDetail.setAppName(appName);
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setTemplateName(templateName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownTemplateException(null, faultDetail);
				}

				// Convert results.
				DoctypeSecurity docTypeSec = new DoctypeSecurity(repo);
				ArrayList docTypes = docTypeSec.getDoctypeNameListByPriv(appName, mDoctypePriv);

				templateDefs = AppServiceUtils.transformTemplateDef(appColumns, ftblRows, docTypes);
	
				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(DISPLAYFIELDS, templateDefs.getDisplayFields(), ", ");
					msg.append(ENTRYFIELDS, templateDefs.getEntryFields(), "");
					mLogger.info(msg.toString());
				}

				return templateDefs;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (UnknownOptionNameException e) {
				throw e;
			} catch (UnknownOptionValueException e) {
				throw e;
			}

			catch (UnknownAppException e) {
				throw e;
			} catch (UnknownTemplateException e) {
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
					faultDetail.setCallStack(Converter.getCallStack(e));
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new GeneralException(null, faultDetail);
				}
			} catch (Exception e) {
				GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
				faultDetail.setCallStack(Converter.getCallStack(e));
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
		} catch (LicenseException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (UnknownAppException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownAppExceptionFaultDetail faultDetail = new UnknownAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownAppException(null, faultDetail);
		} catch (UnknownTemplateException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownTemplateExceptionFaultDetail faultDetail = new UnknownTemplateExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setTemplateName(e.getFaultInfo().getTemplateName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownTemplateException(null, faultDetail);
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

	public Long deleteRecords(String license, String appName, String condition, List<NameValue> options,
			Long numDeleted)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException, LicenseException,
			UnknownAppException, PermissionException, DatabaseException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "deleteRecords";

		try {
			String userID = null;
			try {
				// Validate arguments.
				if (appName == null || condition == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

		

				appName = Validator.validateString(SERVICENAME, METHODNAME, APPNAME, appName, false, false);

				condition = AppServiceUtils.validateCondition(METHODNAME, condition, false, true, true);

				AppServiceUtils.validateNoOptions(METHODNAME, options);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(CONDITION, condition, ", ");
					msg.append(OPTIONS, options, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				userID = repo.getUserId();
				RecordCollection collection = new RecordCollection(repo);
				Validator.checkApplication(SERVICENAME, METHODNAME, collection, appName);
				ColumnDef columns = collection.getFields(appName);
				String dtWhereClause = AppServiceUtils.checkAccessPermission(METHODNAME, repo, appName,
						DoctypeSecurity.DOCTYPE_DELETE);
				SQLStatement statement = AppServiceUtils.constructDelete(appName, condition, dtWhereClause, columns);
				System.out.println("Statement" + statement.toString());
				int count = collection.executeStatement(statement);

				// Log event.
				String[] eventData = new String[] { repo.getUserId(), repo.getTicket(), appName, "", "0", "Done" };
				Logger logger = new Logger(repo);
				logger.eventLog(EV_UWSDELTITLE, eventData);

				// Convert results.
				numDeleted = new Long(count);
		
				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(NUMDELETED, numDeleted, "");
					mLogger.info(msg.toString());
				}

				return numDeleted;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (UnknownOptionNameException e) {
				throw e;
			} catch (UnknownOptionValueException e) {
				throw e;
			}

			catch (UnknownAppException e) {
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
				} else if (errorDef.equals(TTRepositoryErrors.COLLECTION_PERMISSION_DENIED)) {
					PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
					faultDetail.setAppName(appName);
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setUserName(userID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new PermissionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DATABASE_ERROR)) {
					DatabaseExceptionFaultDetail faultDetail = new DatabaseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new DatabaseException(null, faultDetail);
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
				faultDetail.setCallStack(Converter.getCallStack(e));
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
		} catch (LicenseException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (UnknownAppException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownAppExceptionFaultDetail faultDetail = new UnknownAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownAppException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setUserName(e.getFaultInfo().getUserName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new PermissionException(null, faultDetail);
		} catch (DatabaseException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			DatabaseExceptionFaultDetail faultDetail = new DatabaseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new DatabaseException(null, faultDetail);
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

	private void validateContentOptions(String aMethodName, List<NameValue> aOptions, boolean aOnUpdate,
			String aAppName) throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException {
		// Perform general name/value pair validation.
		mNewDoc = false;
		mDiskset = null;
		mPool = OUTPUT_POOL;
		Validator.validateNameValueList(SERVICENAME, aMethodName, OPTIONS, aOptions, true, true, false, false);

		// Perform specific option validation.
		if (aOptions != null) {
			ListIterator<NameValue> itor = aOptions.listIterator();
			while (itor.hasNext()) {
				NameValue opt = itor.next();
				String optionName = opt.getName();
				String optionValue = opt.getValue();

				// Check for valid values of NewDoc option.
				if (aOnUpdate && optionName.equals(NEWDOC)) {
					if (optionValue.equalsIgnoreCase(TRUE) == false && optionValue.equalsIgnoreCase(FALSE) == false) {
						UnknownOptionValueExceptionFaultDetail faultDetail = new UnknownOptionValueExceptionFaultDetail();
						faultDetail.setCallStack(null);
						faultDetail.setMessage(null);
						faultDetail.setOptionName(optionName);
						faultDetail.setOptionValue(optionValue);
						faultDetail.setWsMethodName(aMethodName);
						faultDetail.setWsName(SERVICENAME);
						throw new UnknownOptionValueException(null, faultDetail);
					}
					mNewDoc = Boolean.valueOf(optionValue).booleanValue();
				}
				// Check for valid values of DiskSet option.
				else if (optionName.equals(DISKSET)) {
					DocId diskset = new DocId(optionValue + "-0");
					if (diskset.toString().length() == 0) {
						UnknownOptionValueExceptionFaultDetail faultDetail = new UnknownOptionValueExceptionFaultDetail();
						faultDetail.setCallStack(null);
						faultDetail.setMessage(null);
						faultDetail.setOptionName(optionName);
						faultDetail.setOptionValue(optionValue);
						faultDetail.setWsMethodName(aMethodName);
						faultDetail.setWsName(SERVICENAME);
						throw new UnknownOptionValueException(null, faultDetail);
					}
					mDiskset = optionValue;
				}
				// Check for valid values of Pool option.
				else if (optionName.equals(POOL)) {
					if (optionValue.equals(SCAN_POOL) == false && optionValue.equals(PRINT_POOL) == false
							&& optionValue.equals(HOLDING_POOL) == false && optionValue.equals(COPYIN_POOL) == false
							&& optionValue.equals(COPYOUT_POOL) == false && optionValue.equals(OUTPUT_POOL) == false) {
						UnknownOptionValueExceptionFaultDetail faultDetail = new UnknownOptionValueExceptionFaultDetail();
						faultDetail.setCallStack(null);
						faultDetail.setMessage(null);
						faultDetail.setOptionName(optionName);
						faultDetail.setOptionValue(optionValue);
						faultDetail.setWsMethodName(aMethodName);
						faultDetail.setWsName(SERVICENAME);
						throw new UnknownOptionValueException(null, faultDetail);
					}
					mPool = optionValue;
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

		// When inserting/updating, check if diskset is required.
		if ((aOnUpdate == false && aAppName == null && mDiskset == null)
				|| (aOnUpdate && aAppName == null && mNewDoc && mDiskset == null)) {
			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setParameterName(OPTIONS + "[" + DISKSET + "]");
			faultDetail.setParameterValue(mDiskset);
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(SERVICENAME);
			throw new InvalidParameterException(null, faultDetail);
		}

		// Check if diskset is not allowed.
		if (aOnUpdate && mDiskset != null && mNewDoc == false) {
			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setParameterName(OPTIONS + "[" + DISKSET + "]");
			faultDetail.setParameterValue("null expected");
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(SERVICENAME);
			throw new InvalidParameterException(null, faultDetail);
		}
	}

	public Integer insertContent(String license, String appName, List<NameValue> fields, String contentType,
			byte[] content, List<NameValue> options, String docID, Integer numPages)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException, LicenseException,
			UnknownAppException, PermissionException, DatabaseException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "insertContent";

		try {
			String userID = null;
			try {
				// Validate arguments.
				if (contentType == null || content == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

				appName = Validator.validateString(SERVICENAME, METHODNAME, APPNAME, appName, true, false);

				if (appName != null) {
					AppServiceUtils.validateFields(METHODNAME, fields, true);
				} else if (fields != null && fields.size() != 0) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(FIELDS);
					faultDetail.setParameterValue("null expected");
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

				contentType = Validator.validateString(SERVICENAME, METHODNAME, CONTENTTYPE, contentType, false, false);

				Validator.validateByteArray(SERVICENAME, METHODNAME, CONTENT, content, false, false);
				validateContentOptions(METHODNAME, options, false, appName);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(FIELDS, fields, ", ");
					msg.append(CONTENTTYPE, contentType, ", ");
					msg.append(CONTENT, content, ", ");
					msg.append(OPTIONS, options, "");
					mLogger.info(msg.toString());
				}

				org.omg.CORBA.StringHolder localDocID = null;
				int localNumPages;
				if (appName != null) {
					// Convert arguments.
					com.vignette.idm.common.NameValue[] localFields = AppServiceUtils.transformFields(fields);

					// Perform operation.
					IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
					userID = repo.getUserId();
					RecordCollection collection = new RecordCollection(repo);
					Validator.checkApplication(SERVICENAME, METHODNAME, collection, appName);
					ColumnDef columns = collection.getFields(appName);
					AppServiceUtils.checkInsertPermission(METHODNAME, fields, repo, appName);
					if (mDiskset == null) {
						General gen = new General(repo);
						mDiskset = gen.selectDiskset(appName, localFields, columns);
					}
					DocImporter docImporter = new DocImporter(repo);
					localDocID = new org.omg.CORBA.StringHolder(mDiskset);
					localNumPages = docImporter.importDocumentExt(localDocID, appName, mPool, content, contentType);
					SQLStatement statement = AppServiceUtils.constructInsert(appName, localDocID.value, localNumPages,
							fields, columns);

					collection.executeStatement(statement);

					// Log event.
					String[] eventData = new String[] { repo.getUserId(), repo.getTicket(), appName, "",
							statement.getSQLStatement(), "Done" };
					Logger logger = new Logger(repo);
					logger.eventLog(EV_UWSADDTITLE, eventData);
				} else {
					// Perform operation.
					IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
					userID = repo.getUserId();
					AppServiceUtils.checkInsertPermission(METHODNAME, fields, repo, appName);
					DocImporter docImporter = new DocImporter(repo);
					localDocID = new org.omg.CORBA.StringHolder(mDiskset);
					localNumPages = docImporter.importDocumentExt(localDocID, appName, mPool, content, contentType);
				}

				// Convert results.

				numPages = localNumPages;

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(DOCID, docID, ", ");
					msg.append(NUMPAGES, numPages, "");
					mLogger.info(msg.toString());
				}

				return numPages;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (UnknownOptionNameException e) {
				throw e;
			} catch (UnknownOptionValueException e) {
				throw e;
			}

			catch (UnknownAppException e) {
				throw e;
			} catch (PermissionException e) {
				throw e;
			} catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				String errorParam1 = e.getParameter(0);
				String errorParam2 = e.getParameter(1);
				if (errorDef.equals(TTRepositoryErrors.DOCUMENT_ID_ILLEGAL)) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(OPTIONS + "[" + DISKSET + "]");
					faultDetail.setParameterValue(mDiskset);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_UNKNOWN)) {
					UnknownOptionValueExceptionFaultDetail faultDetail = new UnknownOptionValueExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setOptionName(DISKSET);
					faultDetail.setOptionValue(mDiskset);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownOptionValueException(null, faultDetail);
				} else if (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
					LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new LicenseException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_PERMISSION_DENIED)) {
					PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
					faultDetail.setAppName(appName);
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setUserName(userID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new PermissionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DATABASE_ERROR)) {
					DatabaseExceptionFaultDetail faultDetail = new DatabaseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new DatabaseException(null, faultDetail);
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
				faultDetail.setCallStack(Converter.getCallStack(e));
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
		} catch (LicenseException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (UnknownAppException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownAppExceptionFaultDetail faultDetail = new UnknownAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownAppException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setUserName(e.getFaultInfo().getUserName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new PermissionException(null, faultDetail);
		} catch (DatabaseException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			DatabaseExceptionFaultDetail faultDetail = new DatabaseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new DatabaseException(null, faultDetail);
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

	public Integer insertContentMTOM(String license, String appName, List<NameValue> fields, String contentType,
			DataHandler content, List<NameValue> options, String docID, Integer numPages)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException, LicenseException,
			UnknownAppException, PermissionException, DatabaseException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "insertContentMTOM";
		byte[] byteData = null;

		mLogger.info(METHODNAME);

		try {
			byteData = AppServiceUtils.getByteArrayFromDataHandler(content);
		} catch (IOException ex) {
			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(ex));
			faultDetail.setMessage(ex.getMessage());
			faultDetail.setWsMethodName(METHODNAME);
			faultDetail.setWsName(SERVICENAME);

			mLogger.severe(ex.getMessage());

			throw new GeneralException(null, faultDetail);
		}

		if (byteData != null) {
			numPages = insertContent(license, appName, fields, contentType, byteData, options, docID, numPages);
		}
		return numPages;
	}

	public AppList getAppList(String license)
			throws InvalidParameterException, LicenseException, IDMRepositoryException, GeneralException {
		mLogger.info("Came TO APPLIST");
		final String METHODNAME = "getAppList";

		try {
			try {
				String localLicense = new String();
				license = Validator.validateLicense(SERVICENAME, METHODNAME, LICENSE, license);
				localLicense = license;

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, "");
					mLogger.info(msg.toString());
				}

				System.out.println("repo");
				System.out.println(localLicense);
				System.out.println(license);

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(localLicense));
				System.out.println(repo);
				Misc misc = new Misc(repo);
				System.out.println(misc);
				ApplicationEntry[] apps = misc.getAppList();
				System.out.println("apps");
				String defaultApp = misc.getConfigVar(DEFAULT_APPLICATION);

				System.out.println(defaultApp);

				// set return value
				AppList appList = AppServiceUtils.transformAppList(apps, defaultApp);
			
				license = localLicense;

				if (mLogger.isLoggable(Level.INFO)) {
					AppList appListRslt = appList;
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, localLicense, ", ");
					msg.append(APPDEFS, appListRslt.getAppDefs(), ", ");
					msg.append(DEFAULTAPP, appListRslt.getDefaultApp(), "");
					mLogger.info(msg.toString());
				}

				return appList;
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

	public List<AppFieldDef> getFieldList(String license, String appName) throws InvalidParameterException,
			LicenseException, UnknownAppException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "getFieldList";

		try {
			try {
				// Validate arguments.
				if (appName == null) {
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

				appName = Validator.validateString(SERVICENAME, METHODNAME, APPNAME, appName, false, false);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(localLicense));
				RecordCollection collection = new RecordCollection(repo);
				Validator.checkApplication(SERVICENAME, METHODNAME, collection, appName);
				ColumnDef columns = collection.getFields(appName);

				// Convert results.
				List<AppFieldDef> fields = new ArrayList<AppFieldDef>();
				fields.addAll(AppServiceUtils.transformFieldDefs(columns));

				license = localLicense;
				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, localLicense, ", ");
					msg.append(FIELDS, fields, "");
					mLogger.info(msg.toString());
				}

				return fields;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (LicenseException e) {
				throw e;
			} catch (UnknownAppException e) {
				throw e;
			} catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				String errorParam1 = e.getParameter(0);
				String errorParam2 = e.getParameter(1);
				if (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
					LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
					faultDetail.setCallStack(Converter.getCallStack(e));
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
					faultDetail.setCallStack(Converter.getCallStack(e));
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new GeneralException(null, faultDetail);
				}
			} catch (Exception e) {
				GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
				faultDetail.setCallStack(Converter.getCallStack(e));
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
		} catch (UnknownAppException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownAppExceptionFaultDetail faultDetail = new UnknownAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownAppException(null, faultDetail);
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

	public void insertRecord(String license, String appName, List<NameValue> fields)
			throws InvalidParameterException, LicenseException, UnknownAppException, PermissionException,
			DatabaseException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "insertRecord";

		try {
			try {
				// Validate arguments.
				if (appName == null || fields == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

	
				appName = Validator.validateString(SERVICENAME, METHODNAME, APPNAME, appName, false, false);

				AppServiceUtils.validateFields(METHODNAME, fields, false);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(FIELDS, fields, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				RecordCollection collection = new RecordCollection(repo);
				Validator.checkApplication(SERVICENAME, METHODNAME, collection, appName);
				ColumnDef columns = collection.getFields(appName);
				AppServiceUtils.checkInsertPermission(METHODNAME, fields, repo, appName);
				SQLStatement statement = AppServiceUtils.constructInsert(appName, null, 0, fields, columns);

				collection.executeStatement(statement);

				// Log event.
				String[] eventData = new String[] { repo.getUserId(), repo.getTicket(), appName, "", "0", "Done" };
				Logger logger = new Logger(repo);
				logger.eventLog(EV_UWSADDTITLE, eventData);

				return;
			} catch (InvalidParameterException e) {
				throw e;
			}
			
			catch (UnknownAppException e) {
				throw e;
			} catch (PermissionException e) {
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
				} else if (errorDef.equals(TTRepositoryErrors.DATABASE_ERROR)) {
					DatabaseExceptionFaultDetail faultDetail = new DatabaseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new DatabaseException(null, faultDetail);
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
				faultDetail.setCallStack(Converter.getCallStack(e));
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
		} catch (UnknownAppException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownAppExceptionFaultDetail faultDetail = new UnknownAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownAppException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setUserName(e.getFaultInfo().getUserName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new PermissionException(null, faultDetail);
		} catch (DatabaseException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			DatabaseExceptionFaultDetail faultDetail = new DatabaseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new DatabaseException(null, faultDetail);
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

	public SearchResult searchRecords(String license, String appName, List<String> fieldNames, String condition,
			String orderBy, long offset, int maxRecords, SearchResult records)
			throws InvalidParameterException, LicenseException, UnknownAppException, PermissionException,
			DatabaseException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "searchRecords";

		try {
			String userID = null;
			try {
				// Validate arguments.
				if (appName == null || fieldNames == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

				appName = Validator.validateString(SERVICENAME, METHODNAME, APPNAME, appName, false, false);

				AppServiceUtils.validateFieldNames(METHODNAME, fieldNames);
				condition = AppServiceUtils.validateCondition(METHODNAME, condition, true, true, true);

				orderBy = AppServiceUtils.validateOrderBy(METHODNAME, orderBy, true, true);

				Validator.validateLongLimit(SERVICENAME, METHODNAME, OFFSET, offset, 0);
				Validator.validateLongLimit(SERVICENAME, METHODNAME, MAXRECORDS, maxRecords, 0);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(FIELDNAMES, fieldNames, ", ");
					msg.append(CONDITION, condition, ", ");
					msg.append(ORDERBY, orderBy, ", ");
					msg.append(OFFSET, offset, ", ");
					msg.append(MAXRECORDS, maxRecords, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				userID = repo.getUserId();
				if (condition == null && AppServiceUtils.getFlagValue(repo, P_BLANKSRCH) == false) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(CONDITION + "(blank not allowed)");
					faultDetail.setParameterValue(condition);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}
				RecordCollection collection = new RecordCollection(repo);
				Validator.checkApplication(SERVICENAME, METHODNAME, collection, appName);
				ColumnDef columns = collection.getFields(appName);
				String dtWhereClause = AppServiceUtils.checkAccessPermission(METHODNAME, repo, appName,
						DoctypeSecurity.DOCTYPE_VIEW);
				SQLStatement statement = AppServiceUtils.constructSelect(appName, fieldNames, condition, dtWhereClause,
						orderBy, columns);

				com.vignette.idm.common.NameValue[][] rows = collection.executeQuery(statement, (int) offset,
						maxRecords + 1);

				// Convert results.
				records = AppServiceUtils.transformSearchResult(rows, maxRecords);
				
				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(FIELDNAMES, records.getFieldNames(), ", ");
					msg.append(ROWS, records.getRows().getValue(), ", ");
					msg.append(HASMORERECORDS, records.isHasMoreRecords(), "");
					mLogger.info(msg.toString());
				}

				return records;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (UnknownAppException e) {
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
				} else if (errorDef.equals(TTRepositoryErrors.COLLECTION_PERMISSION_DENIED)) {
					PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
					faultDetail.setAppName(appName);
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setUserName(userID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new PermissionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DATABASE_ERROR)) {
					DatabaseExceptionFaultDetail faultDetail = new DatabaseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new DatabaseException(null, faultDetail);
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
				faultDetail.setCallStack(Converter.getCallStack(e));
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
		} catch (UnknownAppException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownAppExceptionFaultDetail faultDetail = new UnknownAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownAppException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setUserName(e.getFaultInfo().getUserName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new PermissionException(null, faultDetail);
		} catch (DatabaseException e) {
			String msg = format(e.getFaultInfo());
			mLogger.severe(msg);

			DatabaseExceptionFaultDetail faultDetail = new DatabaseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new DatabaseException(null, faultDetail);
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

}
