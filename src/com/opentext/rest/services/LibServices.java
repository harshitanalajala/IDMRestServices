package com.opentext.rest.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;

import com.opentext.rest.utils.LibServiceUtils;
import com.towertechnology.common.errorhandling.ITTErrorDef;
import com.towertechnology.common.errorhandling.TTCommonErrors;
import com.towertechnology.common.errorhandling.TTException;
import com.towertechnology.common.errorhandling.TTRepositoryErrors;
import com.vignette.idm.common.Image;
import com.vignette.idm.common.NameValue;
import com.vignette.idm.server.CheckOutDetail;
import com.vignette.idm.server.CheckedOutException;
import com.vignette.idm.server.CheckedOutExceptionFaultDetail;
import com.vignette.idm.server.DMStatus;
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
import com.vignette.idm.server.NotCheckedOutException;
import com.vignette.idm.server.NotCheckedOutExceptionFaultDetail;
import com.vignette.idm.server.NotLibAppException;
import com.vignette.idm.server.NotLibAppExceptionFaultDetail;
import com.vignette.idm.server.NotOwnerException;
import com.vignette.idm.server.NotOwnerExceptionFaultDetail;
import com.vignette.idm.server.PermissionException;
import com.vignette.idm.server.PermissionExceptionFaultDetail;
import com.vignette.idm.server.SearchResult;
import com.vignette.idm.server.UnknownDocIDException;
import com.vignette.idm.server.UnknownDocIDExceptionFaultDetail;
import com.vignette.idm.server.UnknownOptionNameException;
import com.vignette.idm.server.UnknownOptionNameExceptionFaultDetail;
import com.vignette.idm.server.UnknownOptionValueException;
import com.vignette.idm.server.UnknownOptionValueExceptionFaultDetail;
import com.vignette.idm.server.common.Converter;
import com.vignette.idm.server.common.ExceptionMessagesFormatter;
import com.vignette.idm.server.common.LicenceFactory;
import com.vignette.idm.server.common.StringBufferWS;
import com.vignette.idm.server.common.Validator;
import com.vignette.idm.services.ColumnDef;
import com.vignette.idm.services.IdmRepImpl;
import com.vignette.idm.services.Library;
import com.vignette.idm.services.RecordCollection;
import com.vignette.idm.services.SQLStatement;

public class LibServices {

	@Resource
	WebServiceContext context;
	////////////////////////////////////////////////////////////////////////////
	// Constant Strings
	////////////////////////////////////////////////////////////////////////////
	private static final String CONTENT = "content";
	private static final String CONTENTTYPE = "contentType";
	private static final String DMCO = "dmco";
	private static final String DMCODATE = "dmcodate";
	private static final String DMCOTIME = "dmcotime";
	private static final String DMCOUSER = "dmcouser";
	private static final String DMID = "dmid";
	private static final String DMREVISE = "dmrevise";
	private static final String DMRNUM = "dmrnum";
	private static final String DMSTATUS = "dmstatus";
	private static final String DMVNUM = "dmvnum";
	private static final String IFNDS = "ifnds";
	private static final String IFNID = "ifnid";
	private static final String NPAGES = "npages";
	private static final String STATUS = "status";
	private static final String ALL = "all";
	private static final String AND = "and";
	private static final String APPNAME = "appName";
	private static final String BETWEEN = "between";
	private static final String COMMENT = "comment";
	private static final String COMPLETE = "Complete: ";
	private static final String CONDITION = "condition";
	private static final String COPYIN_POOL = "COPYIN_POOL";
	private static final String COPYOUT_POOL = "COPYOUT_POOL";
	private static final String DATE = "date";
	private static final String DELIMITERS_COND = " <>=!(),^";
	private static final String DELIMITERS_ORDER = " ,";
	private static final String DISKSET = "DiskSet";
	private static final String DOCID = "docID";
	private static final String DOCIDS = "docIDs";
	private static final String EQ = "=";
	private static final String FIELDNAMES = "fieldNames";
	private static final String FIELDS = "fields";
	private static final String FROM = "from";
	private static final String HASH_DOCID = "#DOCID#";
	private static final String HASMORERECORDS = "hasMoreRecords";
	private static final String HOLDING_POOL = "HOLDING_POOL";
	private static final String IS = "is";
	private static final String ISNOTNULL = "is not null";
	private static final String ISNULL = "is null";
	private static final String LICENSE = "license";
	private static final String MAXRECORDS = "maxRecords";
	private static final String NEQ = "<>";
	private static final String NOT = "not";
	private static final String NULL = "null";
	private static final String NUMPAGES = "numPages";
	private static final String OFFSET = "offset";
	private static final String OPTIONS = "options";
	private static final String OR = "or";
	private static final String ORDERBY = "orderBy";
	private static final String ORDER_BY = "order by";
	private static final String OUTPUT_POOL = "OUTPUT_POOL";
	private static final String PARAMETERS = "parameters";
	private static final String POOL = "Pool";
	private static final String PRINT_POOL = "PRINT_POOL";
	private static final String RECID = "#RECID#";
	private static final String ROWS = "rows";
	private static final String SCAN_POOL = "SCAN_POOL";
	private static final String SELECT = "select";
	private static final String SERVICENAME = "LibServices";
	private static final String START = "Start: ";
	private static final String TIME = "time";
	private static final String UNKNOWN = "unknown";
	private static final String USERID = "userID";
	private static final String WHERE = "where";

	////////////////////////////////////////////////////////////////////////////
	// Static Member Variables
	////////////////////////////////////////////////////////////////////////////
	private static java.util.logging.Logger mLogger = java.util.logging.Logger
			.getLogger("com.opentext.rest.services.LibServices");

	////////////////////////////////////////////////////////////////////////////
	// Instance Member Variables
	////////////////////////////////////////////////////////////////////////////
	private LicenceFactory mLF;
	private ExceptionMessagesFormatter mFormatter;
	private String mDiskset;
	private String mPool;

	////////////////////////////////////////////////////////////////////////////
	// Constructor
	////////////////////////////////////////////////////////////////////////////
	public LibServices() throws Exception {
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

	////////////////////////////////////////////////////////////////////////////
	// Functions required by LibServices interface
	////////////////////////////////////////////////////////////////////////////

	public Map<String, Object> createRevision(String license, String appName,
			List<com.vignette.idm.server.NameValue> fields, DMStatus status, String contentType, byte[] content,
			List<com.vignette.idm.server.NameValue> options, String docID, Long numPages)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException, LicenseException,
			NotLibAppException, PermissionException, DatabaseException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "createRevision";

		try {
			String userID = null;
			try {
				// Validate arguments.
				if (appName == null || contentType == null || content == null) {
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

				LibServiceUtils.validateFields(METHODNAME, fields);

				contentType = Validator.validateString(SERVICENAME, METHODNAME, CONTENTTYPE, contentType, false, false);

				Validator.validateByteArray(SERVICENAME, METHODNAME, CONTENT, content, false, false);

				LibServiceUtils.validateOptions(METHODNAME, options);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(FIELDS, fields, ", ");
					msg.append(STATUS, status, ", ");
					msg.append(CONTENTTYPE, contentType, ", ");
					msg.append(CONTENT, content, ", ");
					msg.append(OPTIONS, options, "");
					mLogger.info(msg.toString());
				}

				// Convert arguments.
				NameValue[] flds = LibServiceUtils.transformFields(fields);

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				userID = repo.getUserId();
				Library library = new Library(repo);
				org.omg.CORBA.StringHolder docIDStrHld = new org.omg.CORBA.StringHolder();
				int localNumPages = library.createRevision(appName, flds, status.value(), contentType, content,
						mDiskset, mPool, docIDStrHld);

				// Convert results.
				docID = docIDStrHld.value;
				numPages = Converter.convertUnsignedInt(localNumPages);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(DOCID, docID, ", ");
					msg.append(NUMPAGES, numPages, "");
					mLogger.info(msg.toString());
				}
				Map<String, Object> map = new HashMap<>();
				map.put("docID", docID);
				map.put("numPages", numPages);

				return map;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (UnknownOptionNameException e) {
				throw e;
			} catch (UnknownOptionValueException e) {
				throw e;
			}

			catch (TTException e) {
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
				} else if (errorDef.equals(TTRepositoryErrors.COLLECTION_NOT_MANAGED)) {
					NotLibAppExceptionFaultDetail faultDetail = new NotLibAppExceptionFaultDetail();
					faultDetail.setAppName(appName);
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new NotLibAppException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_UNKNOWN)) {
					UnknownOptionValueExceptionFaultDetail faultDetail = new UnknownOptionValueExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setOptionName(DISKSET);
					faultDetail.setOptionValue(mDiskset);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownOptionValueException(null, faultDetail);
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownOptionNameExceptionFaultDetail faultDetail = new UnknownOptionNameExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setOptionName(e.getFaultInfo().getOptionName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownOptionNameException(null, faultDetail);
		} catch (UnknownOptionValueException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (NotLibAppException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			NotLibAppExceptionFaultDetail faultDetail = new NotLibAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new NotLibAppException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			DatabaseExceptionFaultDetail faultDetail = new DatabaseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new DatabaseException(null, faultDetail);
		} catch (IDMRepositoryException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}

	public Map<String, Object> checkOutRevision(String license, String appName, String docID, String comment,
			List<com.vignette.idm.server.NameValue> options, byte[] content, String contentType)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException, LicenseException,
			NotLibAppException, UnknownDocIDException, CheckedOutException, PermissionException, IDMRepositoryException,
			GeneralException {
		final String METHODNAME = "checkOutRevision";

		try {
			String userID = null;
			try {
				// Validate arguments.
				if (appName == null || docID == null || comment == null) {
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

				docID = Validator.validateString(SERVICENAME, METHODNAME, APPNAME, docID, false, false);

				comment = Validator.validateString(SERVICENAME, METHODNAME, APPNAME, comment, false, true);

				LibServiceUtils.validateNoOptions(METHODNAME, options);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(DOCID, docID, ", ");
					msg.append(COMMENT, comment, ", ");
					msg.append(OPTIONS, options, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				userID = repo.getUserId();
				Library library = new Library(repo);
				Image image = library.checkOutRevision(appName, docID, comment);

				// Convert results.
				content = image.mData;
				contentType = image.mExtension;
				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(CONTENT, content, ", ");
					msg.append(CONTENTTYPE, contentType, "");
					mLogger.info(msg.toString());
				}

				Map<String, Object> map = new HashMap<>();
				map.put("content", content);
				map.put("ContentType", contentType);

				return map;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (UnknownOptionNameException e) {
				throw e;
			} catch (UnknownOptionValueException e) {
				throw e;
			}

			catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				String errorParam1 = e.getParameter(0);
				String errorParam2 = e.getParameter(1);
				if (errorDef.equals(TTRepositoryErrors.DOCUMENT_ID_ILLEGAL)) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(DOCID);
					faultDetail.setParameterValue(docID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				} else if (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
					LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new LicenseException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.COLLECTION_NOT_MANAGED)) {
					NotLibAppExceptionFaultDetail faultDetail = new NotLibAppExceptionFaultDetail();
					faultDetail.setAppName(appName);
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new NotLibAppException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_UNKNOWN)) {
					UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownDocIDException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_PERMISSION_DENIED)) {
					PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
					faultDetail.setAppName(appName);
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setUserName(userID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new PermissionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_ALREADY_CHECKOUT)) {
					CheckedOutExceptionFaultDetail faultDetail = new CheckedOutExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setUserName(UNKNOWN);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new CheckedOutException(null, faultDetail);
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownOptionNameExceptionFaultDetail faultDetail = new UnknownOptionNameExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setOptionName(e.getFaultInfo().getOptionName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownOptionNameException(null, faultDetail);
		} catch (UnknownOptionValueException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (NotLibAppException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			NotLibAppExceptionFaultDetail faultDetail = new NotLibAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new NotLibAppException(null, faultDetail);
		} catch (UnknownDocIDException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownDocIDException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setUserName(e.getFaultInfo().getUserName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new PermissionException(null, faultDetail);
		} catch (CheckedOutException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			CheckedOutExceptionFaultDetail faultDetail = new CheckedOutExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setUserName(e.getFaultInfo().getUserName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new CheckedOutException(null, faultDetail);
		} catch (IDMRepositoryException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}

	public void cancelCheckOut(String license, String appName, String docID)
			throws InvalidParameterException, LicenseException, NotLibAppException, UnknownDocIDException,
			NotCheckedOutException, NotOwnerException, PermissionException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "cancelCheckOut";

		try {
			String userID = null;
			try {
				// Validate arguments.
				if (appName == null || docID == null) {
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

				docID = Validator.validateString(SERVICENAME, METHODNAME, APPNAME, docID, false, false);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(DOCID, docID, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				userID = repo.getUserId();
				Library library = new Library(repo);
				library.cancelCheckOut(appName, docID);

				// Convert results.

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, "");
					mLogger.info(msg.toString());
				}

				return;
			} catch (InvalidParameterException e) {
				throw e;
			}

			catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				String errorParam1 = e.getParameter(0);
				String errorParam2 = e.getParameter(1);
				if (errorDef.equals(TTRepositoryErrors.DOCUMENT_ID_ILLEGAL)) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(DOCID);
					faultDetail.setParameterValue(docID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				} else if (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
					LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new LicenseException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.COLLECTION_NOT_MANAGED)) {
					NotLibAppExceptionFaultDetail faultDetail = new NotLibAppExceptionFaultDetail();
					faultDetail.setAppName(appName);
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new NotLibAppException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_UNKNOWN)) {
					UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownDocIDException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_PERMISSION_DENIED)) {
					PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
					faultDetail.setAppName(appName);
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setUserName(userID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new PermissionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_ALREADY_CHECKOUT)) {
					NotOwnerExceptionFaultDetail faultDetail = new NotOwnerExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new NotOwnerException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_NOT_CHECKOUT)) {
					NotCheckedOutExceptionFaultDetail faultDetail = new NotCheckedOutExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new NotCheckedOutException(null, faultDetail);
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (NotLibAppException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			NotLibAppExceptionFaultDetail faultDetail = new NotLibAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new NotLibAppException(null, faultDetail);
		} catch (UnknownDocIDException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownDocIDException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setUserName(e.getFaultInfo().getUserName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new PermissionException(null, faultDetail);
		} catch (NotOwnerException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			NotOwnerExceptionFaultDetail faultDetail = new NotOwnerExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new NotOwnerException(null, faultDetail);
		} catch (NotCheckedOutException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			NotCheckedOutExceptionFaultDetail faultDetail = new NotCheckedOutExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new NotCheckedOutException(null, faultDetail);
		} catch (IDMRepositoryException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}

	public CheckOutDetail getCheckOutDetail(String license, String appName, String docID)
			throws InvalidParameterException, LicenseException, NotLibAppException, UnknownDocIDException,
			NotCheckedOutException, PermissionException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "getCheckOutDetail";
		CheckOutDetail checkOutDetail;

		try {
			String userID = null;
			try {
				// Validate arguments.
				if (appName == null || docID == null) {
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

				docID = Validator.validateString(SERVICENAME, METHODNAME, APPNAME, docID, false, false);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(DOCID, docID, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				userID = repo.getUserId();
				Library library = new Library(repo);
				org.omg.CORBA.StringHolder userID2 = new org.omg.CORBA.StringHolder();
				org.omg.CORBA.StringHolder date = new org.omg.CORBA.StringHolder();
				org.omg.CORBA.StringHolder time = new org.omg.CORBA.StringHolder();
				org.omg.CORBA.StringHolder comment = new org.omg.CORBA.StringHolder();
				library.getCheckOutDetail(appName, docID, userID2, date, time, comment);

				// Convert results.
				checkOutDetail = new CheckOutDetail();
				checkOutDetail.setComment(comment.value);
				checkOutDetail.setDate(date.value);
				checkOutDetail.setTime(time.value);
				checkOutDetail.setUserID(userID2.value);
				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(USERID, checkOutDetail.getUserID(), ", ");
					msg.append(DATE, checkOutDetail.getDate(), ", ");
					msg.append(TIME, checkOutDetail.getTime(), ", ");
					msg.append(COMMENT, checkOutDetail.getComment(), "");
					mLogger.info(msg.toString());
				}

				return checkOutDetail;
			} catch (InvalidParameterException e) {
				throw e;
			}

			catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				String errorParam1 = e.getParameter(0);
				String errorParam2 = e.getParameter(1);
				if (errorDef.equals(TTRepositoryErrors.DOCUMENT_ID_ILLEGAL)) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(DOCID);
					faultDetail.setParameterValue(docID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				} else if (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
					LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new LicenseException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.COLLECTION_NOT_MANAGED)) {
					NotLibAppExceptionFaultDetail faultDetail = new NotLibAppExceptionFaultDetail();
					faultDetail.setAppName(appName);
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new NotLibAppException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_UNKNOWN)) {
					UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownDocIDException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_PERMISSION_DENIED)) {
					PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
					faultDetail.setAppName(appName);
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setUserName(userID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new PermissionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_NOT_CHECKOUT)) {
					NotCheckedOutExceptionFaultDetail faultDetail = new NotCheckedOutExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new NotCheckedOutException(null, faultDetail);
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (NotLibAppException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			NotLibAppExceptionFaultDetail faultDetail = new NotLibAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new NotLibAppException(null, faultDetail);
		} catch (UnknownDocIDException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownDocIDException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setUserName(e.getFaultInfo().getUserName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new PermissionException(null, faultDetail);
		} catch (NotCheckedOutException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			NotCheckedOutExceptionFaultDetail faultDetail = new NotCheckedOutExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new NotCheckedOutException(null, faultDetail);
		} catch (IDMRepositoryException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}

	public List<String> listCheckedOutRevisions(String license, String appName, boolean all)
			throws InvalidParameterException, LicenseException, NotLibAppException, PermissionException,
			IDMRepositoryException, GeneralException {
		final String METHODNAME = "listCheckedOutRevisions";
		List<String> docIDs;

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

				appName = Validator.validateString(SERVICENAME, METHODNAME, APPNAME, appName, false, false);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(ALL, all, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				userID = repo.getUserId();
				Library library = new Library(repo);
				library.listCheckedOutRevisionPermission(appName, all);
				RecordCollection collection = new RecordCollection(repo);
				SQLStatement statement = new SQLStatement(SELECT + " " + IFNDS + ", " + IFNID + " " + FROM + " "
						+ appName + " " + WHERE + " " + DMCO + " = 'Yes'");
				if (all == false) {
					statement.append(" " + AND + " " + DMCOUSER + " = '" + userID + "'");
				}
				statement.append(" " + ORDER_BY + " " + IFNDS + ", " + IFNID);
				NameValue[][] rows = collection.executeQuery(statement, 0, 0);

				// Convert results.
				docIDs = LibServiceUtils.transformDocIDs(rows);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(DOCIDS, docIDs, "");
					mLogger.info(msg.toString());
				}

				return docIDs;
			} catch (InvalidParameterException e) {
				throw e;
			}

			catch (TTException e) {
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
				} else if (errorDef.equals(TTRepositoryErrors.COLLECTION_NOT_MANAGED)) {
					NotLibAppExceptionFaultDetail faultDetail = new NotLibAppExceptionFaultDetail();
					faultDetail.setAppName(appName);
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new NotLibAppException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.COLLECTION_PERMISSION_DENIED)) {
					PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
					faultDetail.setAppName(appName);
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setUserName(userID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new PermissionException(null, faultDetail);
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (NotLibAppException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			NotLibAppExceptionFaultDetail faultDetail = new NotLibAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new NotLibAppException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setUserName(e.getFaultInfo().getUserName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new PermissionException(null, faultDetail);
		} catch (IDMRepositoryException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}

	public Long checkInRevision(String license, String appName, String docID, DMStatus status, String contentType,
			byte[] content, List<com.vignette.idm.server.NameValue> options, Long numPages)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException, LicenseException,
			NotLibAppException, UnknownDocIDException, NotCheckedOutException, NotOwnerException, PermissionException,
			IDMRepositoryException, GeneralException {
		final String METHODNAME = "checkInRevision";

		try {
			String userID = null;
			try {
				// Validate arguments.
				if (appName == null || docID == null || contentType == null || content == null) {
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

				docID = Validator.validateString(SERVICENAME, METHODNAME, APPNAME, docID, false, false);

				contentType = Validator.validateString(SERVICENAME, METHODNAME, CONTENTTYPE, contentType, false, false);

				Validator.validateByteArray(SERVICENAME, METHODNAME, CONTENT, content, false, false);

				LibServiceUtils.validateNoOptions(METHODNAME, options);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(DOCID, docID, ", ");
					msg.append(STATUS, status, ", ");
					msg.append(CONTENTTYPE, contentType, ", ");
					msg.append(CONTENT, content, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				userID = repo.getUserId();
				Library library = new Library(repo);
				org.omg.CORBA.StringHolder docIDHolder = new org.omg.CORBA.StringHolder();
				int localNumPages = library.checkInRevision(appName, docID, status.value(), contentType, content,
						docIDHolder);

				// Convert results.
				docID = docIDHolder.value;
				numPages = Converter.convertUnsignedInt(localNumPages);

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

			catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				String errorParam1 = e.getParameter(0);
				String errorParam2 = e.getParameter(1);
				if (errorDef.equals(TTRepositoryErrors.DOCUMENT_ID_ILLEGAL)) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(DOCID);
					faultDetail.setParameterValue(docID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				} else if (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
					LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new LicenseException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.COLLECTION_NOT_MANAGED)) {
					NotLibAppExceptionFaultDetail faultDetail = new NotLibAppExceptionFaultDetail();
					faultDetail.setAppName(appName);
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new NotLibAppException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_UNKNOWN)) {
					UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownDocIDException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_PERMISSION_DENIED)) {
					PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
					faultDetail.setAppName(appName);
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setUserName(userID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new PermissionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_ALREADY_CHECKOUT)) {
					NotOwnerExceptionFaultDetail faultDetail = new NotOwnerExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new NotOwnerException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_NOT_CHECKOUT)) {
					NotCheckedOutExceptionFaultDetail faultDetail = new NotCheckedOutExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new NotCheckedOutException(null, faultDetail);
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownOptionNameExceptionFaultDetail faultDetail = new UnknownOptionNameExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setOptionName(e.getFaultInfo().getOptionName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownOptionNameException(null, faultDetail);
		} catch (UnknownOptionValueException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (NotLibAppException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			NotLibAppExceptionFaultDetail faultDetail = new NotLibAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new NotLibAppException(null, faultDetail);
		} catch (UnknownDocIDException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownDocIDException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setUserName(e.getFaultInfo().getUserName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new PermissionException(null, faultDetail);
		} catch (NotOwnerException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			NotOwnerExceptionFaultDetail faultDetail = new NotOwnerExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new NotOwnerException(null, faultDetail);
		} catch (NotCheckedOutException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			NotCheckedOutExceptionFaultDetail faultDetail = new NotCheckedOutExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new NotCheckedOutException(null, faultDetail);
		} catch (IDMRepositoryException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}

	public Map<String, Object> publishRevision(String license, String appName, String docID, Long numPages)
			throws InvalidParameterException, LicenseException, NotLibAppException, UnknownDocIDException,
			CheckedOutException, PermissionException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "publishRevision";

		try {
			String userID = null;
			try {
				// Validate arguments.
				if (appName == null || docID == null) {
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

				docID = Validator.validateString(SERVICENAME, METHODNAME, APPNAME, docID, false, false);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(DOCID, docID, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				userID = repo.getUserId();
				Library library = new Library(repo);
				org.omg.CORBA.StringHolder docIDHolder = new org.omg.CORBA.StringHolder();
				int localNumPages = library.publishRevision(appName, docID, docIDHolder);

				// Convert results.
				docID = docIDHolder.value;
				numPages = Converter.convertUnsignedInt(localNumPages);
				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(DOCID, docID, ", ");
					msg.append(NUMPAGES, numPages, "");
					mLogger.info(msg.toString());
				}
				Map<String, Object> map = new HashMap<>();
				map.put("docID", docID);
				map.put("numPages", numPages);

				return map;
			} catch (InvalidParameterException e) {
				throw e;
			}

			catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				String errorParam1 = e.getParameter(0);
				String errorParam2 = e.getParameter(1);
				if (errorDef.equals(TTRepositoryErrors.DOCUMENT_ID_ILLEGAL)) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(DOCID);
					faultDetail.setParameterValue(docID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				} else if (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
					LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new LicenseException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.COLLECTION_NOT_MANAGED)) {
					NotLibAppExceptionFaultDetail faultDetail = new NotLibAppExceptionFaultDetail();
					faultDetail.setAppName(appName);
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new NotLibAppException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_UNKNOWN)) {
					UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownDocIDException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_PERMISSION_DENIED)) {
					PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
					faultDetail.setAppName(appName);
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setUserName(userID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new PermissionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_ALREADY_CHECKOUT)) {
					CheckedOutExceptionFaultDetail faultDetail = new CheckedOutExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setUserName(UNKNOWN);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new CheckedOutException(null, faultDetail);
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (NotLibAppException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			NotLibAppExceptionFaultDetail faultDetail = new NotLibAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new NotLibAppException(null, faultDetail);
		} catch (UnknownDocIDException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownDocIDException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setUserName(e.getFaultInfo().getUserName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new PermissionException(null, faultDetail);
		} catch (CheckedOutException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			CheckedOutExceptionFaultDetail faultDetail = new CheckedOutExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setUserName(e.getFaultInfo().getUserName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new CheckedOutException(null, faultDetail);
		} catch (IDMRepositoryException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}

	public SearchResult searchRevisionHistory(String license, String appName, String docID, List<String> fieldNames,
			String condition, String orderBy, long offset, int maxRecords, SearchResult revisionHistory)
			throws InvalidParameterException, LicenseException, NotLibAppException, UnknownDocIDException,
			PermissionException, DatabaseException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "searchRevisionHistory";

		try {
			String userID = null;
			try {
				// Validate arguments.
				if (appName == null || docID == null || fieldNames == null) {
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

				docID = Validator.validateString(SERVICENAME, METHODNAME, APPNAME, docID, false, false);

				LibServiceUtils.validateFieldNames(METHODNAME, fieldNames);

				condition = LibServiceUtils.validateCondition(METHODNAME, condition);

				orderBy = LibServiceUtils.validateOrderBy(METHODNAME, orderBy);

				Validator.validateLongLimit(SERVICENAME, METHODNAME, OFFSET, offset, 0);

				Validator.validateLongLimit(SERVICENAME, METHODNAME, MAXRECORDS, maxRecords, 0);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(DOCID, docID, ", ");
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
				Library library = new Library(repo);
				String histName = library.searchRevisionHistoryPermission(appName, docID);
				RecordCollection collection = new RecordCollection(repo);
				String dmID = LibServiceUtils.getDMID(collection, appName, docID);
				ColumnDef columns = collection.getFields(histName);
				SQLStatement statement = LibServiceUtils.constructSelect(histName, fieldNames, dmID, condition, orderBy,
						columns);
				NameValue[][] rows = collection.executeQuery(statement, (int) offset, maxRecords + 1);

				// Convert results.
				revisionHistory = LibServiceUtils.transformSearchResult(rows, maxRecords);
				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(FIELDNAMES, revisionHistory.getFieldNames(), ", ");
					msg.append(ROWS, revisionHistory.getRows().getValue(), ", ");
					msg.append(HASMORERECORDS, revisionHistory.isHasMoreRecords(), "");
					mLogger.info(msg.toString());
				}

				return revisionHistory;
			} catch (InvalidParameterException e) {
				throw e;
			}

			catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				String errorParam1 = e.getParameter(0);
				String errorParam2 = e.getParameter(1);
				if (errorDef.equals(TTRepositoryErrors.DOCUMENT_ID_ILLEGAL)) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(DOCID);
					faultDetail.setParameterValue(docID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				} else if (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
					LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new LicenseException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.COLLECTION_NOT_MANAGED)) {
					NotLibAppExceptionFaultDetail faultDetail = new NotLibAppExceptionFaultDetail();
					faultDetail.setAppName(appName);
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new NotLibAppException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_UNKNOWN)) {
					UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownDocIDException(null, faultDetail);
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (NotLibAppException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			NotLibAppExceptionFaultDetail faultDetail = new NotLibAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new NotLibAppException(null, faultDetail);
		} catch (UnknownDocIDException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownDocIDException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			DatabaseExceptionFaultDetail faultDetail = new DatabaseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new DatabaseException(null, faultDetail);
		} catch (IDMRepositoryException e) {
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
			String msg = LibServiceUtils.format(e.getFaultInfo());
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
