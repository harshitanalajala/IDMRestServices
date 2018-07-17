package com.opentext.rest.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import com.opentext.rest.utils.QueueServiceUtils;
import com.towertechnology.common.errorhandling.ITTErrorDef;
import com.towertechnology.common.errorhandling.TTCommonErrors;
import com.towertechnology.common.errorhandling.TTException;
import com.towertechnology.common.errorhandling.TTRepositoryErrors;
import com.vignette.idm.common.QNameValue;
import com.vignette.idm.common.QSelection;
import com.vignette.idm.common.QSelectionResult;
import com.vignette.idm.common.QueueEntry;
import com.vignette.idm.common.QueueInfo;
import com.vignette.idm.common.SelectionResult;
import com.vignette.idm.server.GeneralException;
import com.vignette.idm.server.GeneralExceptionFaultDetail;
import com.vignette.idm.server.InvalidParameterException;
import com.vignette.idm.server.InvalidParameterExceptionFaultDetail;
import com.vignette.idm.server.LicenseException;
import com.vignette.idm.server.LicenseExceptionFaultDetail;
import com.vignette.idm.server.NameValue;
import com.vignette.idm.server.QueueConnectionException;
import com.vignette.idm.server.QueueConnectionExceptionFaultDetail;
import com.vignette.idm.server.QueueEntryLockFailureException;
import com.vignette.idm.server.QueueEntryLockFailureExceptionFaultDetail;
import com.vignette.idm.server.QueueEntryLockedException;
import com.vignette.idm.server.QueueEntryLockedExceptionFaultDetail;
import com.vignette.idm.server.QueueEntryMovedException;
import com.vignette.idm.server.QueueEntryMovedExceptionFaultDetail;
import com.vignette.idm.server.QueueEntryNotLockedException;
import com.vignette.idm.server.QueueEntryNotLockedExceptionFaultDetail;
import com.vignette.idm.server.QueueEntryUnknownException;
import com.vignette.idm.server.QueueEntryUnknownExceptionFaultDetail;
import com.vignette.idm.server.QueueException;
import com.vignette.idm.server.QueueExceptionFaultDetail;
import com.vignette.idm.server.QueueIDUnknownException;
import com.vignette.idm.server.QueueIDUnknownExceptionFaultDetail;
import com.vignette.idm.server.QueueSelectionUnknownException;
import com.vignette.idm.server.QueueSelectionUnknownExceptionFaultDetail;
//import com.vignette.idm.server.QueueServices;
import com.vignette.idm.server.QueueVariableUnknownException;
import com.vignette.idm.server.QueueVariableUnknownExceptionFaultDetail;
import com.vignette.idm.server.SearchResult;
import com.vignette.idm.server.common.Converter;
import com.vignette.idm.server.common.ExceptionMessagesFormatter;
import com.vignette.idm.server.common.LicenceFactory;
import com.vignette.idm.server.common.StringBufferWS;
import com.vignette.idm.server.common.Validator;
import com.vignette.idm.services.IdmRepImpl;
import com.vignette.idm.services.Queue;

public class QueueServices {

	private static final String COMPLETE = "Complete: ";
	private static final String CONDITION = "condition";
	private static final String ENTRYID = "entryID";
	private static final String ENTRYVARIABLES = "entryVariables";
	private static final String FIELDNAMES = "fieldNames";
	private static final String HASMORERECORDS = "hasMoreRecords";
	private static final String LICENSE = "license";
	private static final String MAXENTRIES = "maxEntries";
	private static final String MAXQUEUEINFOS = "maxQueueInfos";
	private static final String MAXROWS = "maxRows";
	private static final String NUMSELECTED = "numSelected";
	private static final String OFFSET = "offset";
	private static final String ORDERBY = "orderBy";
	private static final String PARAMETERS = "parameters";
	private static final String PROPNAMES = "propNames";
	private static final String QSERVER = "qServer";
	private static final String QSERVICENAME = "qServiceName";
	private static final String QUEUEENTRIES = "queueEntries";
	private static final String QUEUEENTRY = "queueEntry";
	private static final String QUEUEID = "queueID";
	private static final String QUEUEIDS = "queueIDs";
	private static final String QUEUEINFO = "queueInfo";
	private static final String RANGESIZE = "rangeSize";
	private static final String ROWS = "rows";
	private static final String SELECTIONID = "selectionID";
	private static final String SERVICENAME = "QueueServices";
	private static final String START = "Start: ";
	private static final String TOTALNUMMATCHED = "totalNumMatched";
	private static final String UNKNOWN = "unknown";
	private static final String VARNAMES = "varNames";
	private static java.util.logging.Logger mLogger = java.util.logging.Logger
			.getLogger("com.vignette.idm.server.impl.QueueServicesImpl");
	private LicenceFactory mLF;
	private ExceptionMessagesFormatter mFormatter;

	public com.vignette.idm.server.QueueEntry lockEntry(String license, String queueServer, String queueServiceName,
			long queueID, long entryID, com.vignette.idm.server.QueueEntry queueEntry)
			throws InvalidParameterException, LicenseException, QueueException, QueueConnectionException,
			QueueIDUnknownException, QueueEntryUnknownException, QueueEntryLockedException,
			QueueEntryLockFailureException, QueueEntryMovedException, GeneralException {
		final String METHODNAME = "lockEntry";

		try {
			try {
				// Validate arguments.
				if (queueServer == null || queueServiceName == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

				
				queueServer = Validator.validateString(SERVICENAME, METHODNAME, QSERVER, queueServer, false, false);

				queueServiceName = Validator.validateString(SERVICENAME, METHODNAME, QSERVICENAME, queueServiceName,
						false, false);

				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, QUEUEID, queueID, 1,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);

				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, ENTRYID, entryID, 1,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(QSERVER, queueServer, ", ");
					msg.append(QSERVICENAME, queueServiceName, ", ");
					msg.append(QUEUEID, queueID, ", ");
					msg.append(ENTRYID, entryID, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				Queue queue = new Queue(repo);
				QueueEntry localQueueEntry = queue.lockEntry(queueServer, queueServiceName, (int) queueID,
						(int) entryID);

				// Convert results.
				queueEntry = QueueServiceUtils.transformQueueEntry(localQueueEntry);
				
				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, "");
					mLogger.info(msg.toString());
				}

				return queueEntry;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				if (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
					LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new LicenseException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_ERROR)) {
					QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_CONNECTION_ERROR)) {
					QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueConnectionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.UNKNOWN_QUEUE)) {
					QueueIDUnknownExceptionFaultDetail faultDetail = new QueueIDUnknownExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueID(queueID);
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueIDUnknownException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.UNKNOWN_QUEUE_ENTRY)) {
					QueueEntryUnknownExceptionFaultDetail faultDetail = new QueueEntryUnknownExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setEntryID(entryID);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueID(queueID);
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueEntryUnknownException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_ENTRY_LOCKED)) {
					QueueEntryLockedExceptionFaultDetail faultDetail = new QueueEntryLockedExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setEntryID(entryID);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueID(queueID);
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueEntryLockedException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_LOCK_FAILURE)) {
					QueueEntryLockFailureExceptionFaultDetail faultDetail = new QueueEntryLockFailureExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setEntryID(entryID);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueID(queueID);
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueEntryLockFailureException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_ENTRY_MOVED)) {
					QueueEntryMovedExceptionFaultDetail faultDetail = new QueueEntryMovedExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setEntryID(entryID);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueID(queueID);
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueEntryMovedException(null, faultDetail);
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (QueueEntryUnknownException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueEntryUnknownExceptionFaultDetail faultDetail = new QueueEntryUnknownExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setEntryID(e.getFaultInfo().getEntryID());
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueID(e.getFaultInfo().getQueueID());
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueEntryUnknownException(null, faultDetail);
		} catch (QueueEntryLockedException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueEntryLockedExceptionFaultDetail faultDetail = new QueueEntryLockedExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setEntryID(e.getFaultInfo().getEntryID());
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueID(e.getFaultInfo().getQueueID());
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueEntryLockedException(null, faultDetail);
		} catch (QueueEntryLockFailureException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueEntryLockFailureExceptionFaultDetail faultDetail = new QueueEntryLockFailureExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setEntryID(e.getFaultInfo().getEntryID());
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueID(e.getFaultInfo().getQueueID());
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueEntryLockFailureException(null, faultDetail);
		} catch (QueueEntryMovedException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueEntryMovedExceptionFaultDetail faultDetail = new QueueEntryMovedExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setEntryID(e.getFaultInfo().getEntryID());
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueID(e.getFaultInfo().getQueueID());
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueEntryMovedException(null, faultDetail);
		} catch (QueueIDUnknownException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueIDUnknownExceptionFaultDetail faultDetail = new QueueIDUnknownExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueID(e.getFaultInfo().getQueueID());
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueIDUnknownException(null, faultDetail);
		} catch (QueueConnectionException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueConnectionException(null, faultDetail);
		} catch (QueueException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}

	public void unlockEntry(String license, String queueServer, String queueServiceName, long entryID)
			throws InvalidParameterException, LicenseException, QueueException, QueueConnectionException,
			QueueEntryNotLockedException, GeneralException {
		final String METHODNAME = "unlockEntry";

		try {
			try {
				// Validate arguments.
				if (queueServer == null || queueServer == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}
				queueServer = Validator.validateString(SERVICENAME, METHODNAME, QSERVER, queueServer, false, false);

				queueServiceName = Validator.validateString(SERVICENAME, METHODNAME, QSERVICENAME, queueServiceName,
						false, false);

				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, ENTRYID, entryID, 1,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(QSERVER, queueServer, ", ");
					msg.append(QSERVICENAME, queueServiceName, ", ");
					msg.append(ENTRYID, entryID, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				Queue queue = new Queue(repo);
				queue.unlockEntry(queueServer, queueServiceName, (int) entryID);

				// Convert results.
			
				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					mLogger.info(msg.toString());
				}

				return;
			} catch (InvalidParameterException e) {
				throw e;
			}
			/*
			 * catch (LicenseException e) { throw e; }
			 */
			catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				if (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
					LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new LicenseException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_ERROR)) {
					QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_CONNECTION_ERROR)) {
					QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueConnectionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_ENTRY_NOT_LOCKED)) {
					QueueEntryNotLockedExceptionFaultDetail faultDetail = new QueueEntryNotLockedExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setEntryID(entryID);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueEntryNotLockedException(null, faultDetail);
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (QueueEntryNotLockedException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueEntryNotLockedExceptionFaultDetail faultDetail = new QueueEntryNotLockedExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setEntryID(e.getFaultInfo().getEntryID());
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueEntryNotLockedException(null, faultDetail);
		} catch (QueueConnectionException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueConnectionException(null, faultDetail);
		} catch (QueueException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
	 */
	public void updateEntry(String license, String queueServer, String queueServiceName, long entryID,
			List<NameValue> entryVariables) throws InvalidParameterException, LicenseException, QueueException,
			QueueConnectionException, QueueEntryNotLockedException, QueueVariableUnknownException, GeneralException {
		final String METHODNAME = "updateEntry";

		try {
			try {
				// Validate arguments.
				if (queueServer == null || queueServiceName == null || entryVariables == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

				queueServer = Validator.validateString(SERVICENAME, METHODNAME, QSERVER, queueServer, false, false);

				queueServiceName = Validator.validateString(SERVICENAME, METHODNAME, QSERVICENAME, queueServiceName,
						false, false);

				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, ENTRYID, entryID, 1,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);
				Validator.validateNameValueList(SERVICENAME, METHODNAME, ENTRYVARIABLES, entryVariables, false, false,
						false, true);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(QSERVER, queueServer, ", ");
					msg.append(QSERVICENAME, queueServiceName, ", ");
					msg.append(ENTRYID, entryID, ", ");
					msg.append(ENTRYVARIABLES, entryVariables, "");
					mLogger.info(msg.toString());
				}

				// Convert arguments.
				QNameValue[] entryVars = QueueServiceUtils.transformEntryVariables(entryVariables);

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				Queue queue = new Queue(repo);
				queue.updateEntry(queueServer, queueServiceName, (int) entryID, entryVars);

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
			/*
			 * catch (LicenseException e) { throw e; }
			 */
			catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				if (errorDef.equals(TTRepositoryErrors.QUEUE_BAD_NAME_VALUE)) {
					int index = Converter.parseInt(e.getParameter(0), 0);

					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(ENTRYVARIABLES + "[" + index + "]");
					faultDetail.setParameterValue(entryVariables.get(index).getName());
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
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_ERROR)) {
					QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_CONNECTION_ERROR)) {
					QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueConnectionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_ENTRY_NOT_LOCKED)) {
					QueueEntryNotLockedExceptionFaultDetail faultDetail = new QueueEntryNotLockedExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setEntryID(entryID);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueEntryNotLockedException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.UNKNOWN_QUEUE_VARIABLE)) {
					QueueVariableUnknownExceptionFaultDetail faultDetail = new QueueVariableUnknownExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setEntryID(entryID);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueID(-1);
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setVariableName(UNKNOWN);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueVariableUnknownException(null, faultDetail);
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (QueueVariableUnknownException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueVariableUnknownExceptionFaultDetail faultDetail = new QueueVariableUnknownExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setEntryID(e.getFaultInfo().getEntryID());
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(e.getMessage());
			faultDetail.setQueueID(e.getFaultInfo().getQueueID());
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setVariableName(e.getFaultInfo().getVariableName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueVariableUnknownException(null, faultDetail);
		} catch (QueueEntryNotLockedException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueEntryNotLockedExceptionFaultDetail faultDetail = new QueueEntryNotLockedExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setEntryID(e.getFaultInfo().getEntryID());
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueEntryNotLockedException(null, faultDetail);
		} catch (QueueConnectionException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueConnectionException(null, faultDetail);
		} catch (QueueException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}

	public SearchResult searchEntries(String license, String queueServer, String queueServiceName, long queueID,
			List<String> varNames, String condition, String orderBy, long offset, int maxEntries,
			SearchResult variables) throws InvalidParameterException, LicenseException, QueueException,
			QueueConnectionException, QueueIDUnknownException, GeneralException {
		final String METHODNAME = "searchEntries";

		try {
			try {
				// Validate arguments.
				if (queueServer == null || queueServiceName == null || varNames == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

				queueServer = Validator.validateString(SERVICENAME, METHODNAME, QSERVER, queueServer, false, false);

				queueServiceName = Validator.validateString(SERVICENAME, METHODNAME, QSERVICENAME, queueServiceName,
						false, false);

				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, QUEUEID, queueID, 1,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);

				Validator.validateStringList(SERVICENAME, METHODNAME, VARNAMES, varNames, false, false, false, false);
				condition = Validator.validateString(SERVICENAME, METHODNAME, CONDITION, condition, true, true);

				orderBy = Validator.validateString(SERVICENAME, METHODNAME, ORDERBY, orderBy, true, true);

				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, OFFSET, offset, 0,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);
				Validator.validateLongLimit(SERVICENAME, METHODNAME, MAXENTRIES, maxEntries, 0);

				String[] varNamesArray = new String[varNames.size()];
				varNames.toArray(varNamesArray);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(QSERVER, queueServer, ", ");
					msg.append(QSERVICENAME, queueServiceName, ", ");
					msg.append(QUEUEID, queueID, ", ");
					msg.append(VARNAMES, varNames, ", ");
					msg.append(CONDITION, condition, ", ");
					msg.append(ORDERBY, orderBy, ", ");
					msg.append(OFFSET, offset, ", ");
					msg.append(MAXENTRIES, maxEntries, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				Queue queue = new Queue(repo);
				QSelectionResult qSelectionResult = queue.searchEntries(queueServer, queueServiceName, (int) queueID,
						varNamesArray, condition, orderBy, (int) offset, maxEntries + 1);

				// Convert results.
				variables = QueueServiceUtils.transformSelectionResult(qSelectionResult.m_Results, maxEntries);
				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(FIELDNAMES, variables.getFieldNames(), ", ");
					msg.append(ROWS, variables.getRows().getValue(), ", ");
					msg.append(HASMORERECORDS, variables.isHasMoreRecords(), "");
					mLogger.info(msg.toString());
				}

				return variables;
			} catch (InvalidParameterException e) {
				throw e;
			}
			/*
			 * catch (LicenseException e) { throw e; }
			 */
			catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				if (errorDef.equals(TTRepositoryErrors.QUEUE_BAD_FIELD_NAMES)) {
					int index = Converter.parseInt(e.getParameter(0), 0);

					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(VARNAMES + "[" + index + "]");
					faultDetail.setParameterValue(varNames.get(index));
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_BAD_OFFSET)) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(OFFSET);
					faultDetail.setParameterValue(Long.toString(offset));
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
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_ERROR)) {
					QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_CONNECTION_ERROR)) {
					QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueConnectionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.UNKNOWN_QUEUE)) {
					QueueIDUnknownExceptionFaultDetail faultDetail = new QueueIDUnknownExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueID(queueID);
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueIDUnknownException(null, faultDetail);
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (QueueIDUnknownException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueIDUnknownExceptionFaultDetail faultDetail = new QueueIDUnknownExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueID(e.getFaultInfo().getQueueID());
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueIDUnknownException(null, faultDetail);
		} catch (QueueConnectionException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueConnectionException(null, faultDetail);
		} catch (QueueException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
	public Map<String, Object> searchEntryRange(String license, String queueServer, String queueServiceName,
			long queueID, List<String> varNames, String condition, String orderBy, int offset, int rangeSize,
			SearchResult variables, Long offset0, Long totalNumMatched) throws InvalidParameterException,
			LicenseException, QueueException, QueueConnectionException, QueueIDUnknownException, GeneralException {
		final String METHODNAME = "searchEntryRange";

		try {
			try {
				// Validate arguments.
				if (queueServer == null || queueServiceName == null || varNames == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

				queueServer = Validator.validateString(SERVICENAME, METHODNAME, QSERVER, queueServer, false, false);

				queueServiceName = Validator.validateString(SERVICENAME, METHODNAME, QSERVICENAME, queueServiceName,
						false, false);

				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, QUEUEID, queueID, 1,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);

				Validator.validateStringList(SERVICENAME, METHODNAME, VARNAMES, varNames, false, false, false, false);

				condition = Validator.validateString(SERVICENAME, METHODNAME, CONDITION, condition, true, true);

				orderBy = Validator.validateString(SERVICENAME, METHODNAME, ORDERBY, orderBy, true, true);

				Validator.validateLongLimit(SERVICENAME, METHODNAME, RANGESIZE, rangeSize, 0);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(QSERVER, queueServer, ", ");
					msg.append(QSERVICENAME, queueServiceName, ", ");
					msg.append(QUEUEID, queueID, ", ");
					msg.append(VARNAMES, varNames, ", ");
					msg.append(CONDITION, condition, ", ");
					msg.append(ORDERBY, orderBy, ", ");
					msg.append(OFFSET, offset, ", ");
					msg.append(RANGESIZE, rangeSize, "");
					mLogger.info(msg.toString());
				}

				String[] varNamesArray = new String[varNames.size()];
				varNames.toArray(varNamesArray);

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				Queue queue = new Queue(repo);
				QSelectionResult qSelectionResult = queue.searchEntryRange(queueServer, queueServiceName, (int) queueID,
						varNamesArray, condition, orderBy, (int) offset, rangeSize + 1);

				// Convert results.
				variables = QueueServiceUtils.transformSelectionResult(qSelectionResult.m_Results, rangeSize);
				offset = (int) Converter.convertUnsignedInt(qSelectionResult.m_Offset);
				totalNumMatched = Converter.convertUnsignedInt(qSelectionResult.m_NumMatched);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(FIELDNAMES, variables.getFieldNames(), ", ");
					msg.append(ROWS, variables.getRows().getValue(), ", ");
					msg.append(HASMORERECORDS, variables.isHasMoreRecords(), ", ");
					msg.append(OFFSET, offset, ", ");
					msg.append(TOTALNUMMATCHED, totalNumMatched, "");
					mLogger.info(msg.toString());
				}
				Map<String, Object> map = new HashMap<>();
				map.put("variables", variables);
				map.put("offset", offset);
				map.put("totalNumMatched", totalNumMatched);

				return map;
			} catch (InvalidParameterException e) {
				throw e;
			}
			/*
			 * catch (LicenseException e) { throw e; }
			 */
			catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				if (errorDef.equals(TTRepositoryErrors.QUEUE_BAD_FIELD_NAMES)) {
					int index = Converter.parseInt(e.getParameter(0), 0);

					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(VARNAMES + "[" + index + "]");
					faultDetail.setParameterValue(varNames.get(index));
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_BAD_OFFSET)) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(OFFSET);
					faultDetail.setParameterValue(Long.toString(offset));
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
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_ERROR)) {
					QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_CONNECTION_ERROR)) {
					QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueConnectionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.UNKNOWN_QUEUE)) {
					QueueIDUnknownExceptionFaultDetail faultDetail = new QueueIDUnknownExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueID(queueID);
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueIDUnknownException(null, faultDetail);
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (QueueIDUnknownException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueIDUnknownExceptionFaultDetail faultDetail = new QueueIDUnknownExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueID(e.getFaultInfo().getQueueID());
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueIDUnknownException(null, faultDetail);
		} catch (QueueConnectionException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueConnectionException(null, faultDetail);
		} catch (QueueException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
	public Map<String, Object> selectEntries(String license, String queueServer, String queueServiceName, long queueID,
			List<String> varNames, String condition, String orderBy, String selectionID, Long numSelected)
			throws InvalidParameterException, LicenseException, QueueException, QueueConnectionException,
			QueueIDUnknownException, GeneralException {
		final String METHODNAME = "selectEntries";

		try {
			try {
				// Validate arguments.
				if (queueServer == null || queueServiceName == null || varNames == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

				queueServer = Validator.validateString(SERVICENAME, METHODNAME, QSERVER, queueServer, false, false);

				queueServiceName = Validator.validateString(SERVICENAME, METHODNAME, QSERVICENAME, queueServiceName,
						false, false);

				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, QUEUEID, queueID, 1,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);

				Validator.validateStringList(SERVICENAME, METHODNAME, VARNAMES, varNames, false, false, false, false);

				condition = Validator.validateString(SERVICENAME, METHODNAME, CONDITION, condition, true, true);

				orderBy = Validator.validateString(SERVICENAME, METHODNAME, ORDERBY, orderBy, true, true);

				String[] varNamesArray = new String[varNames.size()];
				varNames.toArray(varNamesArray);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(QSERVER, queueServer, ", ");
					msg.append(QSERVICENAME, queueServiceName, ", ");
					msg.append(QUEUEID, queueID, ", ");
					msg.append(VARNAMES, varNames, ", ");
					msg.append(CONDITION, condition, ", ");
					msg.append(ORDERBY, orderBy, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				Queue queue = new Queue(repo);
				QSelection qSelection = queue.selectEntries(queueServer, queueServiceName, (int) queueID, varNamesArray,
						condition, orderBy);

				// Convert results.
				selectionID = qSelection.m_SelectionId;
				numSelected = Converter.convertUnsignedInt(qSelection.m_NumSelected);

				Map<String, Object> map = new HashMap<>();
				map.put("selectionID", selectionID);
				map.put("numSelected", numSelected);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(SELECTIONID, selectionID, ", ");
					msg.append(NUMSELECTED, numSelected, "");
					mLogger.info(msg.toString());
				}

				return map;
			} catch (InvalidParameterException e) {
				throw e;
			}
			/*
			 * catch (LicenseException e) { throw e; }
			 */
			catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				if (errorDef.equals(TTRepositoryErrors.QUEUE_BAD_FIELD_NAMES)) {
					int index = Converter.parseInt(e.getParameter(0), 0);

					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(VARNAMES + "[" + index + "]");
					faultDetail.setParameterValue(varNames.get(index));
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
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_ERROR)) {
					QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_CONNECTION_ERROR)) {
					QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueConnectionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.UNKNOWN_QUEUE)) {
					QueueIDUnknownExceptionFaultDetail faultDetail = new QueueIDUnknownExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueID(queueID);
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueIDUnknownException(null, faultDetail);
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (QueueIDUnknownException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueIDUnknownExceptionFaultDetail faultDetail = new QueueIDUnknownExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueID(e.getFaultInfo().getQueueID());
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueIDUnknownException(null, faultDetail);
		} catch (QueueConnectionException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueConnectionException(null, faultDetail);
		} catch (QueueException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
	public List<com.vignette.idm.server.QueueEntry> getSelectedEntries(String license, String queueServer,
			String queueServiceName, String selectionID, long offset, int maxEntries,
			List<com.vignette.idm.server.QueueEntry> queueEntries) throws InvalidParameterException, LicenseException,
			QueueException, QueueConnectionException, QueueSelectionUnknownException, GeneralException {
		final String METHODNAME = "getSelectedEntries";

		try {
			try {
				// Validate arguments.
				if (queueServer == null || queueServiceName == null || selectionID == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

				queueServer = Validator.validateString(SERVICENAME, METHODNAME, QSERVER, queueServer, false, false);

				queueServiceName = Validator.validateString(SERVICENAME, METHODNAME, QSERVICENAME, queueServiceName,
						false, false);

				selectionID = Validator.validateString(SERVICENAME, METHODNAME, SELECTIONID, selectionID, false, false);

				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, OFFSET, offset, 0,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);

				Validator.validateLongLimit(SERVICENAME, METHODNAME, MAXENTRIES, maxEntries, 0);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(QSERVER, queueServer, ", ");
					msg.append(QSERVICENAME, queueServiceName, ", ");
					msg.append(SELECTIONID, selectionID, ", ");
					msg.append(OFFSET, offset, ", ");
					msg.append(MAXENTRIES, maxEntries, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				Queue queue = new Queue(repo);
				QueueEntry[] queueEntryList = queue.getSelectedEntries(queueServer, queueServiceName, selectionID,
						(int) offset, maxEntries);

				// Convert results.
				queueEntries = QueueServiceUtils.transformQueueEntryList(queueEntryList);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(QUEUEENTRIES, queueEntries, "");
					mLogger.info(msg.toString());
				}

				return queueEntries;
			} catch (InvalidParameterException e) {
				throw e;
			}
			/*
			 * catch (LicenseException e) { throw e; }
			 */
			catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				if (errorDef.equals(TTRepositoryErrors.QUEUE_BAD_OFFSET)) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(OFFSET);
					faultDetail.setParameterValue(Long.toString(offset));
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
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_ERROR)) {
					QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_CONNECTION_ERROR)) {
					QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueConnectionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_BAD_SELECTION_ID)) {
					QueueSelectionUnknownExceptionFaultDetail faultDetail = new QueueSelectionUnknownExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setSelectionID(selectionID);
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueSelectionUnknownException(null, faultDetail);
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (QueueSelectionUnknownException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueSelectionUnknownExceptionFaultDetail faultDetail = new QueueSelectionUnknownExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setSelectionID(e.getFaultInfo().getSelectionID());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueSelectionUnknownException(null, faultDetail);
		} catch (QueueConnectionException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueConnectionException(null, faultDetail);
		} catch (QueueException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
	public SearchResult searchQueueInfo(String license, String queueServer, String queueServiceName,
			List<String> propNames, String condition, String orderBy, long offset, int maxQueueInfos,
			SearchResult properties) throws InvalidParameterException, LicenseException, QueueException,
			QueueConnectionException, GeneralException {
		final String METHODNAME = "searchQueueInfo";

		try {
			try {
				// Validate arguments.
				if (queueServer == null || queueServiceName == null || propNames == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}
				
				queueServer = Validator.validateString(SERVICENAME, METHODNAME, QSERVER, queueServer, false, false);

				queueServiceName = Validator.validateString(SERVICENAME, METHODNAME, QSERVICENAME, queueServiceName,
						false, false);

				Validator.validateStringList(SERVICENAME, METHODNAME, PROPNAMES, propNames, false, false, false, false);

				condition = Validator.validateString(SERVICENAME, METHODNAME, CONDITION, condition, true, true);

				orderBy = Validator.validateString(SERVICENAME, METHODNAME, ORDERBY, orderBy, true, true);

				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, OFFSET, offset, 0,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);

				Validator.validateLongLimit(SERVICENAME, METHODNAME, MAXQUEUEINFOS, maxQueueInfos, 0);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(QSERVER, queueServer, ", ");
					msg.append(QSERVICENAME, queueServiceName, ", ");
					msg.append(PROPNAMES, propNames, ", ");
					msg.append(CONDITION, condition, ", ");
					msg.append(ORDERBY, orderBy, ", ");
					msg.append(OFFSET, offset, ", ");
					msg.append(MAXQUEUEINFOS, maxQueueInfos, "");
					mLogger.info(msg.toString());
				}

				String[] propNamesArray = new String[propNames.size()];
				propNames.toArray(propNamesArray);

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				Queue queue = new Queue(repo);
				QSelectionResult qSelectionResult = queue.searchQueueInfo(queueServer, queueServiceName, propNamesArray,
						condition, orderBy, (int) offset, maxQueueInfos + 1);

				// Convert results.
				properties = QueueServiceUtils.transformSelectionResult(qSelectionResult.m_Results, maxQueueInfos);


				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(FIELDNAMES, properties.getFieldNames(), ", ");
					msg.append(ROWS, properties.getRows().getValue(), ", ");
					msg.append(HASMORERECORDS, properties.isHasMoreRecords(), "");
					mLogger.info(msg.toString());
				}

				return properties;
			} catch (InvalidParameterException e) {
				throw e;
			}
			/*
			 * catch (LicenseException e) { throw e; }
			 */
			catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				if (errorDef.equals(TTRepositoryErrors.QUEUE_BAD_FIELD_NAMES)) {
					int index = Converter.parseInt(e.getParameter(0), 0);

					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(PROPNAMES + "[" + index + "]");
					faultDetail.setParameterValue(propNames.get(index));
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_BAD_WHERE)) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(CONDITION);
					faultDetail.setParameterValue(condition);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_BAD_OFFSET)) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(OFFSET);
					faultDetail.setParameterValue(Long.toString(offset));
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
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_ERROR)) {
					QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_CONNECTION_ERROR)) {
					QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueConnectionException(null, faultDetail);
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (QueueConnectionException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueConnectionException(null, faultDetail);
		} catch (QueueException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
	 */
	public void selectQueueInfo(String license, String queueServer, String queueServiceName, List<String> propNames,
			String condition, String orderBy, String selectionID, Long numSelected) throws InvalidParameterException,
			LicenseException, QueueException, QueueConnectionException, GeneralException {
		final String METHODNAME = "selectQueueInfo";

		try {
			try {
				// Validate arguments.
				if (queueServer == null || queueServiceName == null || propNames == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

			
				queueServer = Validator.validateString(SERVICENAME, METHODNAME, QSERVER, queueServer, false, false);

				queueServiceName = Validator.validateString(SERVICENAME, METHODNAME, QSERVICENAME, queueServiceName,
						false, false);

				Validator.validateStringList(SERVICENAME, METHODNAME, PROPNAMES, propNames, false, false, false, false);

				condition = Validator.validateString(SERVICENAME, METHODNAME, CONDITION, condition, true, true);

				orderBy = Validator.validateString(SERVICENAME, METHODNAME, ORDERBY, orderBy, true, true);

				String[] propNameArray = new String[propNames.size()];
				propNames.toArray(propNameArray);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(QSERVER, queueServer, ", ");
					msg.append(QSERVICENAME, queueServiceName, ", ");
					msg.append(PROPNAMES, propNames, ", ");
					msg.append(CONDITION, condition, ", ");
					msg.append(ORDERBY, orderBy, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				Queue queue = new Queue(repo);
				QSelection qSelection = queue.selectQueueInfo(queueServer, queueServiceName, propNameArray, condition,
						orderBy);

				// Convert results.
				selectionID = qSelection.m_SelectionId;
				numSelected = Converter.convertUnsignedInt(qSelection.m_NumSelected);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(SELECTIONID, selectionID, ", ");
					msg.append(NUMSELECTED, numSelected, "");
					mLogger.info(msg.toString());
				}

				return;
			} catch (InvalidParameterException e) {
				throw e;
			}
			/*
			 * catch (LicenseException e) { throw e; }
			 */
			catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				if (errorDef.equals(TTRepositoryErrors.QUEUE_BAD_FIELD_NAMES)) {
					int index = Converter.parseInt(e.getParameter(0), 0);

					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(PROPNAMES + "[" + index + "]");
					faultDetail.setParameterValue(propNames.get(index));
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_BAD_WHERE)) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(CONDITION);
					faultDetail.setParameterValue(condition);
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
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_ERROR)) {
					QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_CONNECTION_ERROR)) {
					QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueConnectionException(null, faultDetail);
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (QueueConnectionException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueConnectionException(null, faultDetail);
		} catch (QueueException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}

	public Long addEntry(String license, String queueServer, String queueServiceName, long queueID,
			List<NameValue> entryVariables, Long entryID)
			throws InvalidParameterException, LicenseException, QueueException, QueueConnectionException,
			QueueIDUnknownException, QueueVariableUnknownException, GeneralException {
		final String METHODNAME = "addEntry";

		try {
			try {
				// Validate arguments.
				if (queueServer == null || queueServiceName == null || entryVariables == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}
				 queueServer = Validator.validateString(SERVICENAME, METHODNAME, QSERVER, queueServer, false, false);

				queueServiceName = Validator.validateString(SERVICENAME, METHODNAME, QSERVICENAME, queueServiceName,
						false, false);

				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, QUEUEID, queueID, 1,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);

				Validator.validateNameValueList(SERVICENAME, METHODNAME, ENTRYVARIABLES, entryVariables, false, false,
						false, true);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(QSERVER, queueServer, ", ");
					msg.append(QSERVICENAME, queueServiceName, ", ");
					msg.append(QUEUEID, queueID, ", ");
					msg.append(ENTRYVARIABLES, entryVariables, "");
					mLogger.info(msg.toString());
				}

				// Convert arguments.
				QNameValue[] entryVars = QueueServiceUtils.transformEntryVariables(entryVariables);

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				Queue queue = new Queue(repo);
				int intEntryID = queue.addEntry(queueServer, queueServiceName, (int) queueID, entryVars);

				// Convert results.
				entryID = Converter.convertUnsignedInt(intEntryID);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(ENTRYID, entryID, "");
					mLogger.info(msg.toString());
				}

				return entryID;
			} catch (InvalidParameterException e) {
				throw e;
			}
			/*
			 * catch (LicenseException e) { throw e; }
			 */
			catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				if (errorDef.equals(TTRepositoryErrors.QUEUE_BAD_NAME_VALUE)) {
					int index = Converter.parseInt(e.getParameter(0), 0);

					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(ENTRYVARIABLES + "[" + index + "]");
					faultDetail.setParameterValue(entryVariables.get(index).getName());
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
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_ERROR)) {
					QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_CONNECTION_ERROR)) {
					QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueConnectionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.UNKNOWN_QUEUE)) {
					QueueIDUnknownExceptionFaultDetail faultDetail = new QueueIDUnknownExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueID(queueID);
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueIDUnknownException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.UNKNOWN_QUEUE_VARIABLE)) {
					QueueVariableUnknownExceptionFaultDetail faultDetail = new QueueVariableUnknownExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setEntryID(-1);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueID(queueID);
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setVariableName(UNKNOWN);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueVariableUnknownException(null, faultDetail);
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (QueueVariableUnknownException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueVariableUnknownExceptionFaultDetail faultDetail = new QueueVariableUnknownExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setEntryID(e.getFaultInfo().getEntryID());
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(e.getMessage());
			faultDetail.setQueueID(e.getFaultInfo().getQueueID());
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setVariableName(e.getFaultInfo().getVariableName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueVariableUnknownException(null, faultDetail);
		} catch (QueueIDUnknownException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueIDUnknownExceptionFaultDetail faultDetail = new QueueIDUnknownExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueID(e.getFaultInfo().getQueueID());
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueIDUnknownException(null, faultDetail);
		} catch (QueueConnectionException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueConnectionException(null, faultDetail);
		} catch (QueueException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}

	//////////////////////////////////////////////////////////////
	public void freeSelectedResults(String license, String queueServer, String queueServiceName, String selectionID)
			throws InvalidParameterException, LicenseException, QueueConnectionException,
			QueueSelectionUnknownException, GeneralException {
		final String METHODNAME = "freeSelectedResults";

		try {
			try {
				// Validate arguments.
				if (queueServer == null || queueServiceName == null || selectionID == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

				
				queueServer = Validator.validateString(SERVICENAME, METHODNAME, QSERVER, queueServer, false, false);

				queueServiceName = Validator.validateString(SERVICENAME, METHODNAME, QSERVICENAME, queueServiceName,
						false, false);

				selectionID = Validator.validateString(SERVICENAME, METHODNAME, SELECTIONID, selectionID, false, false);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(QSERVER, queueServer, ", ");
					msg.append(QSERVICENAME, queueServiceName, ", ");
					msg.append(SELECTIONID, selectionID, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				Queue queue = new Queue(repo);
				queue.freeSelectedResults(queueServer, queueServiceName, selectionID);

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
			/*
			 * catch (LicenseException e) { throw e; }
			 */
			catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				if (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
					LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new LicenseException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_CONNECTION_ERROR)) {
					QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueConnectionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_BAD_SELECTION_ID)) {
					QueueSelectionUnknownExceptionFaultDetail faultDetail = new QueueSelectionUnknownExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setSelectionID(selectionID);
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueSelectionUnknownException(null, faultDetail);
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (QueueSelectionUnknownException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueSelectionUnknownExceptionFaultDetail faultDetail = new QueueSelectionUnknownExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setSelectionID(e.getFaultInfo().getSelectionID());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueSelectionUnknownException(null, faultDetail);
		} catch (QueueConnectionException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueConnectionException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}
	///////////////////////////////////////////////////

	public com.vignette.idm.server.QueueInfo getQueueInfo(String license, String queueServer, String queueServiceName,
			long queueID, com.vignette.idm.server.QueueInfo queueInfo) throws InvalidParameterException,
			LicenseException, QueueException, QueueConnectionException, QueueIDUnknownException, GeneralException {
		final String METHODNAME = "getQueueInfo";

		try {
			try {
				// Validate arguments.
				if (queueServer == null || queueServiceName == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

				
				queueServer = Validator.validateString(SERVICENAME, METHODNAME, QSERVER, queueServer, false, false);

				queueServiceName = Validator.validateString(SERVICENAME, METHODNAME, QSERVICENAME, queueServiceName,
						false, false);

				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, QUEUEID, queueID, 1,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(QSERVER, queueServer, ", ");
					msg.append(QSERVICENAME, queueServiceName, ", ");
					msg.append(QUEUEID, queueID, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				Queue queue = new Queue(repo);
				QueueInfo localQueueInfo = queue.getQueueInfo(queueServer, queueServiceName, (int) queueID);

				// Convert results.
				queueInfo = QueueServiceUtils.transformQueueInfo(localQueueInfo);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(QUEUEINFO, queueInfo, "");
					mLogger.info(msg.toString());
				}

				return queueInfo;
			} catch (InvalidParameterException e) {
				throw e;
			}
			/*
			 * catch (LicenseException e) { throw e; }
			 */
			catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				if (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
					LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new LicenseException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_ERROR)) {
					QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_CONNECTION_ERROR)) {
					QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueConnectionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.UNKNOWN_QUEUE)) {
					QueueIDUnknownExceptionFaultDetail faultDetail = new QueueIDUnknownExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueID(queueID);
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueIDUnknownException(null, faultDetail);
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (QueueIDUnknownException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueIDUnknownExceptionFaultDetail faultDetail = new QueueIDUnknownExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueID(e.getFaultInfo().getQueueID());
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueIDUnknownException(null, faultDetail);
		} catch (QueueConnectionException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueConnectionException(null, faultDetail);
		} catch (QueueException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}

	///////////////////////////////////

	/**
	 * This is a web service method implementation.
	 * 
	 * @return
	 */
	///////////////////////////////////
	public List<com.vignette.idm.server.QueueInfo> getSelectedQueueInfo(String license, String queueServer,
			String queueServiceName, String selectionID, long offset, int maxQueueInfos,
			List<com.vignette.idm.server.QueueInfo> queueInfo) throws InvalidParameterException, LicenseException,
			QueueException, QueueConnectionException, QueueSelectionUnknownException, GeneralException {
		final String METHODNAME = "getSelectedQueueInfo";

		try {
			try {
				// Validate arguments.
				if (queueServer == null || queueServiceName == null || selectionID == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

				
				queueServer = Validator.validateString(SERVICENAME, METHODNAME, QSERVER, queueServer, false, false);

				queueServiceName = Validator.validateString(SERVICENAME, METHODNAME, QSERVICENAME, queueServiceName,
						false, false);

				selectionID = Validator.validateString(SERVICENAME, METHODNAME, SELECTIONID, selectionID, false, false);

				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, OFFSET, offset, 0,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);
				Validator.validateLongLimit(SERVICENAME, METHODNAME, MAXQUEUEINFOS, maxQueueInfos, 0);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(QSERVER, queueServer, ", ");
					msg.append(QSERVICENAME, queueServiceName, ", ");
					msg.append(SELECTIONID, selectionID, ", ");
					msg.append(OFFSET, offset, ", ");
					msg.append(MAXQUEUEINFOS, maxQueueInfos, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				Queue queue = new Queue(repo);
				QueueInfo[] queueInfoList = queue.getSelectedQueueInfo(queueServer, queueServiceName, selectionID,
						(int) offset, maxQueueInfos);

				// Convert results.
				queueInfo = QueueServiceUtils.transformQueueInfoList(queueInfoList);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(QUEUEINFO, queueInfo, "");
					mLogger.info(msg.toString());
				}

				return queueInfo;
			} catch (InvalidParameterException e) {
				throw e;
			}
			/*
			 * catch (LicenseException e) { throw e; }
			 */
			catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				if (errorDef.equals(TTRepositoryErrors.QUEUE_BAD_OFFSET)) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(OFFSET);
					faultDetail.setParameterValue(Long.toString(offset));
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
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_ERROR)) {
					QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_CONNECTION_ERROR)) {
					QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueConnectionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_BAD_SELECTION_ID)) {

					QueueSelectionUnknownExceptionFaultDetail faultDetail = new QueueSelectionUnknownExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setSelectionID(selectionID);
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueSelectionUnknownException(null, faultDetail);
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (QueueSelectionUnknownException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueSelectionUnknownExceptionFaultDetail faultDetail = new QueueSelectionUnknownExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setSelectionID(e.getFaultInfo().getSelectionID());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueSelectionUnknownException(null, faultDetail);
		} catch (QueueConnectionException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueConnectionException(null, faultDetail);
		} catch (QueueException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}
	////////////////////////////////

	public SearchResult getSelectedResults(String license, String queueServer, String queueServiceName,
			String selectionID, long offset, int maxRows, SearchResult values

	) throws InvalidParameterException, LicenseException, QueueException, QueueConnectionException,
			QueueSelectionUnknownException, GeneralException {
		final String METHODNAME = "getSelectedResults";

		try {
			try {
				// Validate arguments.
				if (queueServer == null || queueServiceName == null || selectionID == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

				queueServer = Validator.validateString(SERVICENAME, METHODNAME, QSERVER, queueServer, false, false);

				queueServiceName = Validator.validateString(SERVICENAME, METHODNAME, QSERVICENAME, queueServiceName,
						false, false);

				selectionID = Validator.validateString(SERVICENAME, METHODNAME, SELECTIONID, selectionID, false, false);

				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, OFFSET, offset, 0,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);

				Validator.validateLongLimit(SERVICENAME, METHODNAME, MAXROWS, maxRows, 0);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(QSERVER, queueServer, ", ");
					msg.append(QSERVICENAME, queueServiceName, ", ");
					msg.append(SELECTIONID, selectionID, ", ");
					msg.append(OFFSET, offset, ", ");
					msg.append(MAXROWS, maxRows, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				Queue queue = new Queue(repo);
				SelectionResult selectionResult = queue.getSelectedResults(queueServer, queueServiceName, selectionID,
						(int) offset, maxRows + 1);

				// Convert results.
				values = QueueServiceUtils.transformSelectionResult(selectionResult, maxRows);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(FIELDNAMES, values.getFieldNames(), ", ");
					msg.append(ROWS, values.getRows().getValue(), ", ");
					msg.append(HASMORERECORDS, values.isHasMoreRecords(), "");
					mLogger.info(msg.toString());
				}

				return values;
			} catch (InvalidParameterException e) {
				throw e;
			}
			/*
			 * catch (LicenseException e) { throw e; }
			 */
			catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				if (errorDef.equals(TTRepositoryErrors.QUEUE_BAD_OFFSET)) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(OFFSET);
					faultDetail.setParameterValue(Long.toString(offset));
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
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_ERROR)) {
					QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_CONNECTION_ERROR)) {
					QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueConnectionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_BAD_SELECTION_ID)) {
					QueueSelectionUnknownExceptionFaultDetail faultDetail = new QueueSelectionUnknownExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setSelectionID(selectionID);
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueSelectionUnknownException(null, faultDetail);
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (QueueSelectionUnknownException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueSelectionUnknownExceptionFaultDetail faultDetail = new QueueSelectionUnknownExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setSelectionID(e.getFaultInfo().getSelectionID());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueSelectionUnknownException(null, faultDetail);
		} catch (QueueConnectionException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueConnectionException(null, faultDetail);
		} catch (QueueException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}

	/////////////////////////
	public com.vignette.idm.server.QueueEntry lockNextEntry(String license, String queueServer, String queueServiceName,
			List<Long> queueIDs, com.vignette.idm.server.QueueEntry queueEntry)
			throws InvalidParameterException, LicenseException, QueueException, QueueConnectionException,
			QueueIDUnknownException, QueueEntryLockFailureException, GeneralException {
		final String METHODNAME = "lockNextEntry";

		try {
			try {
				// Validate arguments.
				if (queueServer == null || queueServiceName == null || queueIDs == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

				queueServer = Validator.validateString(SERVICENAME, METHODNAME, QSERVER, queueServer, false, false);

				queueServiceName = Validator.validateString(SERVICENAME, METHODNAME, QSERVICENAME, queueServiceName,
						false, false);

				QueueServiceUtils.validateQueueIDs(METHODNAME, queueIDs);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(QSERVER, queueServer, ", ");
					msg.append(QSERVICENAME, queueServiceName, ", ");
					msg.append(QUEUEIDS, queueIDs, "");
					mLogger.info(msg.toString());
				}

				// Convert arguments.
				int[] intQueueIDs = QueueServiceUtils.transformQueueIDs(queueIDs);

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				Queue queue = new Queue(repo);
				QueueEntry localQueueEntry = queue.lockNextEntry(queueServer, queueServiceName, intQueueIDs);

				// Convert results.
				queueEntry = QueueServiceUtils.transformQueueEntry(localQueueEntry);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(QUEUEENTRY, queueEntry, "");
					mLogger.info(msg.toString());
				}

				return queueEntry;
			} catch (InvalidParameterException e) {
				throw e;
			}
			/*
			 * catch (LicenseException e) { throw e; }
			 */
			catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				if (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
					LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new LicenseException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_ERROR)) {
					QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_CONNECTION_ERROR)) {
					QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueConnectionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.UNKNOWN_QUEUE)) {
					QueueIDUnknownExceptionFaultDetail faultDetail = new QueueIDUnknownExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueID(-1);
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueIDUnknownException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_LOCK_FAILURE)) {
					QueueEntryLockFailureExceptionFaultDetail faultDetail = new QueueEntryLockFailureExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setEntryID(-1);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueID(-1);
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueEntryLockFailureException(null, faultDetail);
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (QueueEntryLockFailureException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueEntryLockFailureExceptionFaultDetail faultDetail = new QueueEntryLockFailureExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setEntryID(e.getFaultInfo().getEntryID());
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueID(e.getFaultInfo().getQueueID());
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueEntryLockFailureException(null, faultDetail);
		} catch (QueueIDUnknownException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueIDUnknownExceptionFaultDetail faultDetail = new QueueIDUnknownExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueID(e.getFaultInfo().getQueueID());
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueIDUnknownException(null, faultDetail);
		} catch (QueueConnectionException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueConnectionException(null, faultDetail);
		} catch (QueueException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
	 */
	public void releaseEntry(String license, String queueServer, String queueServiceName, long entryID,
			List<NameValue> entryVariables) throws InvalidParameterException, LicenseException, QueueException,
			QueueConnectionException, QueueEntryNotLockedException, GeneralException {
		final String METHODNAME = "releaseEntry";

		try {
			try {
				// Validate arguments.
				if (queueServer == null || queueServiceName == null) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(PARAMETERS);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

			
				queueServer = Validator.validateString(SERVICENAME, METHODNAME, QSERVER, queueServer, false, false);

				queueServiceName = Validator.validateString(SERVICENAME, METHODNAME, QSERVICENAME, queueServiceName,
						false, false);

				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, ENTRYID, entryID, 1,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);

				Validator.validateNameValueList(SERVICENAME, METHODNAME, ENTRYVARIABLES, entryVariables, true, true,
						false, true);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(QSERVER, queueServer, ", ");
					msg.append(QSERVICENAME, queueServiceName, ", ");
					msg.append(ENTRYID, entryID, ", ");
					msg.append(ENTRYVARIABLES, entryVariables, "");
					mLogger.info(msg.toString());
				}

				// Convert arguments.
				QNameValue[] entryVars = QueueServiceUtils.transformEntryVariables(entryVariables);

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				Queue queue = new Queue(repo);
				queue.releaseEntry(queueServer, queueServiceName, (int) entryID, entryVars);

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
			/*
			 * catch (LicenseException e) { throw e; }
			 */
			catch (TTException e) {
				ITTErrorDef errorDef = e.getErrorDef();
				if (errorDef.equals(TTCommonErrors.LICENSE_INVALID)) {
					LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new LicenseException(null, faultDetail);
				}
				if (errorDef.equals(TTRepositoryErrors.QUEUE_BAD_NAME_VALUE)) {
					int index = Converter.parseInt(e.getParameter(0), 0);

					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(ENTRYVARIABLES + "[" + index + "]");
					faultDetail.setParameterValue(entryVariables.get(index).getName());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_ERROR)) {
					QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_CONNECTION_ERROR)) {
					QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueConnectionException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.QUEUE_ENTRY_NOT_LOCKED)) {
					QueueEntryNotLockedExceptionFaultDetail faultDetail = new QueueEntryNotLockedExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setEntryID(entryID);
					faultDetail.setHostName(queueServer);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setQueueSysName(queueServiceName);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new QueueEntryNotLockedException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.UNKNOWN_QUEUE)) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(ENTRYVARIABLES);
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (QueueEntryNotLockedException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueEntryNotLockedExceptionFaultDetail faultDetail = new QueueEntryNotLockedExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setEntryID(e.getFaultInfo().getEntryID());
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueEntryNotLockedException(null, faultDetail);
		} catch (QueueConnectionException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueConnectionExceptionFaultDetail faultDetail = new QueueConnectionExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueConnectionException(null, faultDetail);
		} catch (QueueException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			QueueExceptionFaultDetail faultDetail = new QueueExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setHostName(e.getFaultInfo().getHostName());
			faultDetail.setMessage(msg);
			faultDetail.setQueueSysName(e.getFaultInfo().getQueueSysName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new QueueException(null, faultDetail);
		} catch (GeneralException e) {
			String msg = QueueServiceUtils.format(e.getFaultInfo());
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
