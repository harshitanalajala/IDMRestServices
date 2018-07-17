package com.opentext.rest.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.mail.util.ByteArrayDataSource;
import javax.xml.ws.WebServiceContext;

import com.opentext.rest.utils.AppServiceUtils;
import com.opentext.rest.utils.DocServiceUtils;
import com.towertechnology.common.errorhandling.ITTErrorDef;
import com.towertechnology.common.errorhandling.TTCommonErrors;
import com.towertechnology.common.errorhandling.TTException;
import com.towertechnology.common.errorhandling.TTRepositoryErrors;
import com.vignette.idm.common.Image;
import com.vignette.idm.common.NameValue;
import com.vignette.idm.common.RenditionDetail;
import com.vignette.idm.server.AnnotationsFormatException;
import com.vignette.idm.server.AnnotationsFormatExceptionFaultDetail;
import com.vignette.idm.server.GeneralException;
import com.vignette.idm.server.GeneralExceptionFaultDetail;
import com.vignette.idm.server.IDMRepositoryException;
import com.vignette.idm.server.IDMRepositoryExceptionFaultDetail;
import com.vignette.idm.server.InvalidParameterException;
import com.vignette.idm.server.InvalidParameterExceptionFaultDetail;
import com.vignette.idm.server.LicenseException;
import com.vignette.idm.server.LicenseExceptionFaultDetail;
import com.vignette.idm.server.PermissionException;
import com.vignette.idm.server.PermissionExceptionFaultDetail;
import com.vignette.idm.server.UnknownAppException;
import com.vignette.idm.server.UnknownAppExceptionFaultDetail;
import com.vignette.idm.server.UnknownDocIDException;
import com.vignette.idm.server.UnknownDocIDExceptionFaultDetail;
import com.vignette.idm.server.UnknownOptionNameException;
import com.vignette.idm.server.UnknownOptionNameExceptionFaultDetail;
import com.vignette.idm.server.UnknownOptionValueException;
import com.vignette.idm.server.UnknownOptionValueExceptionFaultDetail;
import com.vignette.idm.server.UnknownPageException;
import com.vignette.idm.server.UnknownPageExceptionFaultDetail;
import com.vignette.idm.server.UnknownPageInRangeException;
import com.vignette.idm.server.UnknownPageInRangeExceptionFaultDetail;
import com.vignette.idm.server.UnknownRenditionTypeException;
import com.vignette.idm.server.UnknownSubPageException;
import com.vignette.idm.server.UnknownSubPageExceptionFaultDetail;
import com.vignette.idm.server.common.Converter;
import com.vignette.idm.server.common.ExceptionMessagesFormatter;
import com.vignette.idm.server.common.LicenceFactory;
import com.vignette.idm.server.common.StringBufferWS;
import com.vignette.idm.server.common.Validator;
import com.vignette.idm.services.Annotation;
import com.vignette.idm.services.DocImporter;
import com.vignette.idm.services.General;
import com.vignette.idm.services.IdmRepImpl;
import com.vignette.idm.services.RecordCollection;
import com.vignette.idm.services.Retrieval;

public class DocServices {

	@Resource
	WebServiceContext context;

	////////////////////////////////////////////////////////////////////////////
	// Constant Strings
	////////////////////////////////////////////////////////////////////////////
	private static final String ANNOTATIONS = "annotations";
	private static final String APPNAME = "appName";
	private static final String APPUNKNOWN = "application/unknown";
	private static final String COLORON = "ColorOn";
	private static final String COMPLETE = "Complete: ";
	private static final String CONTENT = "content";
	private static final String CONTENTTYPE = "contentType";
	private static final String CONVTYPE = "ConvType";
	private static final String DOCID = "docID";
	private static final String FALSE = "false";
	private static final String FILEUNKNOWN = "UNK";
	private static final String FONTSCALINGFACTOR = "FontScalingFactor";
	private static final String FORCE = "Force";
	private static final String FORCE8BITS = "Force8Bits";
	private static final String FORMAT = "Format";
	private static final String HASNEXTSUBPAGE = "hasNextSubPage";
	private static final String HEIGHT = "Height";
	private static final String HWOVERWRITE = "HeightWidthOverwrite";
	private static final String LICENSE = "license";
	private static final String MIMETYPE = "mimeType";
	private static final String OPTIONS = "options";
	private static final String PAGENUM = "pageNum";
	private static final String PAGES = "pages";
	private static final String PARAMETERS = "parameters";
	private static final String PARAMID = "ParamID";
	private static final String RENDITION = "rendition";
	private static final String RENDITIONS = "renditions";
	private static final String RENDITIONTYPE = "renditionType";
	private static final String SERVICENAME = "DocServices";
	private static final String START = "Start: ";
	private static final String SUBPAGENUM = "subPageNum";
	private static final String TRUE = "true";
	private static final String WIDTH = "Width";
	private static final String ZOOMFACTOR = "ZoomFactor";

	////////////////////////////////////////////////////////////////////////////
	// Constant Integers
	////////////////////////////////////////////////////////////////////////////
	private static final int READ_ACTION = 1;
	private static final int DELETE_ACTION = 3;

	////////////////////////////////////////////////////////////////////////////
	// Static Member Variables
	////////////////////////////////////////////////////////////////////////////
	private static java.util.logging.Logger mLogger = java.util.logging.Logger
			.getLogger("com.opentext.rest.services.DocServices");

	////////////////////////////////////////////////////////////////////////////
	// Instance Member Variables
	////////////////////////////////////////////////////////////////////////////
	private LicenceFactory mLF;
	private ExceptionMessagesFormatter mFormatter;
	private String mFormat;
	private String mParamID;
	private int mHeight;
	private int mWidth;
	private int mZoomFactor;
	private String mForce8Bits;
	private int mConvType;
	private String mColorOn;
	private boolean mForce;
	private int mFontScalingFactor;
	private String mHeightWidthOverwrite;

	////////////////////////////////////////////////////////////////////////////
	// Constructor
	////////////////////////////////////////////////////////////////////////////
	public DocServices() throws Exception {
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

	public Long getNumPages(String license, String appName, String docID)
			throws InvalidParameterException, LicenseException, UnknownAppException, UnknownDocIDException,
			PermissionException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "getNumPages";
		 Long pageNum;

		try {
			String userID = null;
			try {
				// Validate arguments.
				if (docID == null) {
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

				docID = Validator.validateString(SERVICENAME, METHODNAME, DOCID, docID, false, false);

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
				if (appName != null) {
					RecordCollection collection = new RecordCollection(repo);
					Validator.checkApplication(SERVICENAME, METHODNAME, collection, appName);
				}
				DocServiceUtils.checkPermission(METHODNAME, repo, appName, docID, READ_ACTION);
				General gen = new General(repo);
				int count = gen.getDocPageCount(docID);

				// Convert results.
				pageNum = Converter.convertUnsignedInt(count);
				
				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(PAGENUM, pageNum, "");
					mLogger.info(msg.toString());
				}

				return pageNum;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (UnknownAppException e) {
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (UnknownAppException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownAppExceptionFaultDetail faultDetail = new UnknownAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownAppException(null, faultDetail);
		} catch (UnknownDocIDException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownDocIDException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
	public List<com.vignette.idm.server.RenditionDetail> getRenditionDetails(String license, String appName,
			String docID, long pageNum)
			throws InvalidParameterException, LicenseException, UnknownAppException, UnknownDocIDException,
			UnknownPageException, PermissionException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "getRenditionDetails";
		 List<com.vignette.idm.server.RenditionDetail> renditions;
		try {
			String userID = null;
			try {
				// Validate arguments.
				if (docID == null) {
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

				docID = Validator.validateString(SERVICENAME, METHODNAME, DOCID, docID, false, false);

				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, PAGENUM, pageNum, 0,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(DOCID, docID, ", ");
					msg.append(PAGENUM, pageNum, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				userID = repo.getUserId();
				if (appName != null) {
					RecordCollection collection = new RecordCollection(repo);
					Validator.checkApplication(SERVICENAME, METHODNAME, collection, appName);
				}
				DocServiceUtils.checkPermission(METHODNAME, repo, appName, docID, READ_ACTION);
				Retrieval retrieval = new Retrieval(repo);
				RenditionDetail[] rends = retrieval.getRenditionTypes(docID, (int) pageNum);

				// Convert results.
				renditions = new ArrayList<com.vignette.idm.server.RenditionDetail>();
				renditions.addAll(DocServiceUtils.transformRenditionDetails(rends));

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(RENDITIONS, renditions, "");
					mLogger.info(msg.toString());
				}

				// Return results.
				return renditions;
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
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_UNKNOWN)) {
					UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownDocIDException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.SECTION_UNKNOWN)) {
					UnknownPageExceptionFaultDetail faultDetail = new UnknownPageExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setPageNum(pageNum);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownPageException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_PERMISSION_DENIED)) {
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (UnknownAppException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownAppExceptionFaultDetail faultDetail = new UnknownAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownAppException(null, faultDetail);
		} catch (UnknownDocIDException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownDocIDException(null, faultDetail);
		} catch (UnknownPageException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownPageExceptionFaultDetail faultDetail = new UnknownPageExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setPageNum(e.getFaultInfo().getPageNum());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownPageException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
	public Map<String, Object> getRendition(String license, String appName, String docID, long pageNum, long subPageNum,
			String renditionType, List<com.vignette.idm.server.NameValue> options, byte[] rendition,
			Boolean hasNextSubPage, String contentType, String mimeType)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException, LicenseException,
			UnknownAppException, UnknownDocIDException, UnknownPageException, UnknownSubPageException,
			UnknownRenditionTypeException, PermissionException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "getRendition";

		try {
			String userID = null;
			try {
				// Validate arguments.
				if (docID == null) {
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

				docID = Validator.validateString(SERVICENAME, METHODNAME, DOCID, docID, false, false);

				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, PAGENUM, pageNum, 0,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);
				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, SUBPAGENUM, subPageNum, 0,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);
				renditionType = DocServiceUtils.validateRenditionType(METHODNAME, renditionType);

				DocServiceUtils.validateGetRenditionOptions(METHODNAME, options);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(DOCID, docID, ", ");
					msg.append(PAGENUM, pageNum, ", ");
					msg.append(SUBPAGENUM, subPageNum, ", ");
					msg.append(RENDITIONTYPE, renditionType, ", ");
					msg.append(OPTIONS, options, "");
					mLogger.info(msg.toString());
				}

				// Convert arguments.
				NameValue[] localOptions = DocServiceUtils.transformOptions();

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				userID = repo.getUserId();
				if (appName != null) {
					RecordCollection collection = new RecordCollection(repo);
					Validator.checkApplication(SERVICENAME, METHODNAME, collection, appName);
				}
				DocServiceUtils.checkPermission(METHODNAME, repo, appName, docID, READ_ACTION);
				Retrieval retrieval = new Retrieval(repo);
				Image image = retrieval.getImageData(docID, (int) pageNum, (int) subPageNum, localOptions);

				// Convert results.
				if (image.mLastKnownSubPage > subPageNum) {
					hasNextSubPage = true;
				} else {
					hasNextSubPage = false;
				}
				if (image.mLastKnownSubPage == 0 // Pass thru image
						|| image.mLastKnownSubPage >= subPageNum) {
					contentType = image.mExtension;
					if (image.mContentType == null || image.mContentType.length() == 0) {
						mimeType = APPUNKNOWN;
					} else {
						mimeType = image.mContentType;
					}
					rendition = image.mData;
				} else {
					UnknownSubPageExceptionFaultDetail faultDetail = new UnknownSubPageExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(null);
					faultDetail.setPageNum(pageNum);
					faultDetail.setSubpageNum(subPageNum);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownSubPageException(null, faultDetail);
				}

				Map<String, Object> map = new HashMap<>();
				map.put("rendition", rendition);
				map.put("contentType", contentType);
				map.put("mimeType", mimeType);
				map.put("hasNextSubPage", hasNextSubPage);
	
				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(RENDITION, rendition, ", ");
					msg.append(HASNEXTSUBPAGE, hasNextSubPage, ", ");
					msg.append(CONTENTTYPE, contentType, ", ");
					msg.append(MIMETYPE, mimeType, "");
					mLogger.info(msg.toString());
				}

				// Return results.
				return map;
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
			} catch (UnknownSubPageException e) {
				throw e;
			} catch (TTException e) {
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
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_UNKNOWN)) {
					UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownDocIDException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.SECTION_UNKNOWN)) {
					UnknownPageExceptionFaultDetail faultDetail = new UnknownPageExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setPageNum(pageNum);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownPageException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.SUBSECTION_UNKNOWN)) {
					UnknownSubPageExceptionFaultDetail faultDetail = new UnknownSubPageExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setPageNum(pageNum);
					faultDetail.setSubpageNum(subPageNum);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownSubPageException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_PERMISSION_DENIED)) {
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownOptionNameExceptionFaultDetail faultDetail = new UnknownOptionNameExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setOptionName(e.getFaultInfo().getOptionName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownOptionNameException(null, faultDetail);
		} catch (UnknownOptionValueException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (UnknownAppException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownAppExceptionFaultDetail faultDetail = new UnknownAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownAppException(null, faultDetail);
		} catch (UnknownDocIDException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownDocIDException(null, faultDetail);
		} catch (UnknownSubPageException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownSubPageExceptionFaultDetail faultDetail = new UnknownSubPageExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setPageNum(e.getFaultInfo().getPageNum());
			faultDetail.setSubpageNum(e.getFaultInfo().getSubpageNum());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownSubPageException(null, faultDetail);
		} catch (UnknownPageException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownPageExceptionFaultDetail faultDetail = new UnknownPageExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setPageNum(e.getFaultInfo().getPageNum());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownPageException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			GeneralExceptionFaultDetail faultDetail = new GeneralExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new GeneralException(null, faultDetail);
		}
	}

	public Map<String, Object> getRenditionMTOM(String license, String appName, String docID, long pageNum,
			long subPageNum, String renditionType, List<com.vignette.idm.server.NameValue> options,
			DataHandler rendition, Boolean hasNextSubPage, String contentType, String mimeType)
			throws GeneralException, IDMRepositoryException, InvalidParameterException, LicenseException,
			PermissionException, UnknownAppException, UnknownDocIDException, UnknownOptionNameException,
			UnknownOptionValueException, UnknownPageException, UnknownRenditionTypeException, UnknownSubPageException {
		final String METHODNAME = "getRenditionMTOM";

		try {
			String userID = null;
			try {
				// Validate arguments.
				if (docID == null) {
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

				docID = Validator.validateString(SERVICENAME, METHODNAME, DOCID, docID, false, false);

				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, PAGENUM, pageNum, 0,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);
				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, SUBPAGENUM, subPageNum, 0,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);
				renditionType = DocServiceUtils.validateRenditionType(METHODNAME, renditionType);

				DocServiceUtils.validateGetRenditionOptions(METHODNAME, options);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(DOCID, docID, ", ");
					msg.append(PAGENUM, pageNum, ", ");
					msg.append(SUBPAGENUM, subPageNum, ", ");
					msg.append(RENDITIONTYPE, renditionType, ", ");
					msg.append(OPTIONS, options, "");
					mLogger.info(msg.toString());
				}
				byte[] rendition1;
				// Convert arguments.
				NameValue[] localOptions = DocServiceUtils.transformOptions();

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				userID = repo.getUserId();
				if (appName != null) {
					RecordCollection collection = new RecordCollection(repo);
					Validator.checkApplication(SERVICENAME, METHODNAME, collection, appName);
				}
				DocServiceUtils.checkPermission(METHODNAME, repo, appName, docID, READ_ACTION);
				Retrieval retrieval = new Retrieval(repo);
				Image image = retrieval.getImageData(docID, (int) pageNum, (int) subPageNum, localOptions);

				// Convert results.
				if (image.mLastKnownSubPage > subPageNum) {
					hasNextSubPage = true;
				} else {
					hasNextSubPage = false;
				}
				if (image.mLastKnownSubPage == 0 // Pass thru image
						|| image.mLastKnownSubPage >= subPageNum) {
					contentType = image.mExtension;
					if (image.mContentType == null || image.mContentType.length() == 0) {
						mimeType = APPUNKNOWN;
					} else {
						mimeType = image.mContentType;
					}

					ByteArrayDataSource dataSource = new ByteArrayDataSource(image.mData, mimeType);
					rendition = new DataHandler(dataSource);
					 rendition1 = DocServiceUtils.getByteArrayFromDataHandler(rendition);
				} else {
					UnknownSubPageExceptionFaultDetail faultDetail = new UnknownSubPageExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(null);
					faultDetail.setPageNum(pageNum);
					faultDetail.setSubpageNum(subPageNum);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownSubPageException(null, faultDetail);
				}
				
				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(RENDITION, image.mData, ", ");
					msg.append(HASNEXTSUBPAGE, hasNextSubPage, ", ");
					msg.append(CONTENTTYPE, contentType, ", ");
					msg.append(MIMETYPE, mimeType, "");
					mLogger.info(msg.toString());
				}

				Map<String, Object> map = new HashMap<>();
				map.put("rendition", rendition1);
				map.put("contentType", contentType);
				map.put("hasNextSubPage", hasNextSubPage);
				map.put("mimeType", mimeType);
				// Return results.
				return map;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (UnknownOptionNameException e) {
				throw e;
			} catch (UnknownOptionValueException e) {
				throw e;
			} catch (UnknownAppException e) {
				throw e;
			} catch (PermissionException e) {
				throw e;
			} catch (UnknownSubPageException e) {
				throw e;
			} catch (TTException e) {
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
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_UNKNOWN)) {
					UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownDocIDException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.SECTION_UNKNOWN)) {
					UnknownPageExceptionFaultDetail faultDetail = new UnknownPageExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setPageNum(pageNum);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownPageException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.SUBSECTION_UNKNOWN)) {
					UnknownSubPageExceptionFaultDetail faultDetail = new UnknownSubPageExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setPageNum(pageNum);
					faultDetail.setSubpageNum(subPageNum);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownSubPageException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_PERMISSION_DENIED)) {
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownOptionNameExceptionFaultDetail faultDetail = new UnknownOptionNameExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setOptionName(e.getFaultInfo().getOptionName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownOptionNameException(null, faultDetail);
		} catch (UnknownOptionValueException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (UnknownAppException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownAppExceptionFaultDetail faultDetail = new UnknownAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownAppException(null, faultDetail);
		} catch (UnknownDocIDException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownDocIDException(null, faultDetail);
		} catch (UnknownSubPageException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownSubPageExceptionFaultDetail faultDetail = new UnknownSubPageExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setPageNum(e.getFaultInfo().getPageNum());
			faultDetail.setSubpageNum(e.getFaultInfo().getSubpageNum());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownSubPageException(null, faultDetail);
		} catch (UnknownPageException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownPageExceptionFaultDetail faultDetail = new UnknownPageExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setPageNum(e.getFaultInfo().getPageNum());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownPageException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
	public Map<String, Object> getDocument(String license, String appName, String docID, String pages)
			throws InvalidParameterException, LicenseException, UnknownAppException, UnknownDocIDException,
			UnknownPageInRangeException, PermissionException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "getDocument";
		byte[] content;
		String contentType;
		try {
			String userID = null;
			try {
				// Validate arguments.
				if (docID == null || pages == null) {
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

				docID = Validator.validateString(SERVICENAME, METHODNAME, DOCID, docID, false, false);

				pages = Validator.validateString(SERVICENAME, METHODNAME, PAGES, pages, false, false);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(DOCID, docID, ", ");
					msg.append(PAGES, pages, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				userID = repo.getUserId();
				if (appName != null) {
					RecordCollection collection = new RecordCollection(repo);
					Validator.checkApplication(SERVICENAME, METHODNAME, collection, appName);
				}
				DocServiceUtils.checkPermission(METHODNAME, repo, appName, docID, READ_ACTION);
				Retrieval retrieval = new Retrieval(repo);
				Image image = retrieval.getDocumentData(docID, pages);

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
				map.put("contentType", contentType);
				return map;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (UnknownAppException e) {
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
					faultDetail.setParameterName(DOCID);
					faultDetail.setParameterValue(docID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.SECTION_ILLEGAL)) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(PAGES);
					faultDetail.setParameterValue(pages);
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
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_UNKNOWN)) {
					UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownDocIDException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.SECTION_UNKNOWN)) {
					UnknownPageInRangeExceptionFaultDetail faultDetail = new UnknownPageInRangeExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setPages(pages);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownPageInRangeException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_PERMISSION_DENIED)) {
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (UnknownAppException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownAppExceptionFaultDetail faultDetail = new UnknownAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownAppException(null, faultDetail);
		} catch (UnknownDocIDException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownDocIDException(null, faultDetail);
		} catch (UnknownPageInRangeException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownPageInRangeExceptionFaultDetail faultDetail = new UnknownPageInRangeExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setPages(e.getFaultInfo().getPages());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownPageInRangeException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
	public Map<String, Object> getDocumentMTOM(String license, String appName, String docID, String pages)
			throws InvalidParameterException, LicenseException, UnknownAppException, UnknownDocIDException,
			UnknownPageInRangeException, PermissionException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "getDocumentMTOM";
		DataHandler content;
		String contentType;

		try {
			String userID = null;
			try {
				// Validate arguments.
				if (docID == null || pages == null) {
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

				docID = Validator.validateString(SERVICENAME, METHODNAME, DOCID, docID, false, false);

				pages = Validator.validateString(SERVICENAME, METHODNAME, PAGES, pages, false, false);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(DOCID, docID, ", ");
					msg.append(PAGES, pages, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				userID = repo.getUserId();
				if (appName != null) {
					RecordCollection collection = new RecordCollection(repo);
					Validator.checkApplication(SERVICENAME, METHODNAME, collection, appName);
				}
				DocServiceUtils.checkPermission(METHODNAME, repo, appName, docID, READ_ACTION);
				Retrieval retrieval = new Retrieval(repo);
				Image image = retrieval.getDocumentData(docID, pages);

				// Convert results.
				ByteArrayDataSource dataSource = new ByteArrayDataSource(image.mData, image.mExtension);
				content = new DataHandler(dataSource);
				byte[] content1 = DocServiceUtils.getByteArrayFromDataHandler(content);
				
				contentType = image.mExtension;
				
				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(CONTENT, image.mData, ", ");
					msg.append(CONTENTTYPE, contentType, "");
					mLogger.info(msg.toString());
				}
				Map<String, Object> map = new HashMap<>();
				map.put("content", content1);
				map.put("contentType", contentType);

				return map;
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
				if (errorDef.equals(TTRepositoryErrors.DOCUMENT_ID_ILLEGAL)) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(DOCID);
					faultDetail.setParameterValue(docID);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.SECTION_ILLEGAL)) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(PAGES);
					faultDetail.setParameterValue(pages);
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
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_UNKNOWN)) {
					UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownDocIDException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.SECTION_UNKNOWN)) {
					UnknownPageInRangeExceptionFaultDetail faultDetail = new UnknownPageInRangeExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setPages(pages);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownPageInRangeException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_PERMISSION_DENIED)) {
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (UnknownAppException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownAppExceptionFaultDetail faultDetail = new UnknownAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownAppException(null, faultDetail);
		} catch (UnknownDocIDException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownDocIDException(null, faultDetail);
		} catch (UnknownPageInRangeException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownPageInRangeExceptionFaultDetail faultDetail = new UnknownPageInRangeExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setPages(e.getFaultInfo().getPages());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownPageInRangeException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
	public void delete(String license, String appName, String docID) throws InvalidParameterException, LicenseException,
			UnknownAppException, UnknownDocIDException, PermissionException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "delete";

		try {
			String userID = null;
			try {
				// Validate arguments.
				if (docID == null) {
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

				docID = Validator.validateString(SERVICENAME, METHODNAME, DOCID, docID, false, false);

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
				if (appName != null) {
					RecordCollection collection = new RecordCollection(repo);
					Validator.checkApplication(SERVICENAME, METHODNAME, collection, appName);
				}
				DocServiceUtils.checkPermission(METHODNAME, repo, appName, docID, DELETE_ACTION);
				DocImporter docImporter = new DocImporter(repo);
				docImporter.removeDocument(docID);

				// Convert results.

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, "");
					mLogger.info(msg.toString());
				}

				return;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (UnknownAppException e) {
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (UnknownAppException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownAppExceptionFaultDetail faultDetail = new UnknownAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownAppException(null, faultDetail);
		} catch (UnknownDocIDException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownDocIDException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
	public String getAnnotations(String license, String appName, String docID, long pageNum, long subPageNum)
			throws InvalidParameterException, LicenseException, UnknownAppException, UnknownDocIDException,
			UnknownPageException, PermissionException, IDMRepositoryException, GeneralException {
		final String METHODNAME = "getAnnotations";
		String annotations;

		try {
			String userID = null;
			try {
				// Validate arguments.
				if (docID == null) {
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

				docID = Validator.validateString(SERVICENAME, METHODNAME, DOCID, docID, false, false);

				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, PAGENUM, pageNum, 0,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);
				Validator.validateLongRangeParam(SERVICENAME, METHODNAME, SUBPAGENUM, subPageNum, 0,
						((long) Integer.MAX_VALUE) * 2 + 1 // Unsigned int max
				);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(DOCID, docID, ", ");
					msg.append(PAGENUM, pageNum, ", ");
					msg.append(SUBPAGENUM, subPageNum, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				userID = repo.getUserId();
				if (appName != null) {
					RecordCollection collection = new RecordCollection(repo);
					Validator.checkApplication(SERVICENAME, METHODNAME, collection, appName);
				}
				DocServiceUtils.checkPermission(METHODNAME, repo, appName, docID, READ_ACTION);
				Annotation annot = new Annotation(repo);
				String localAnnotations = annot.getAnnotations(docID, (int) pageNum, (int) subPageNum);

				// Convert results.
				annotations = localAnnotations;
				
				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, ", ");
					msg.append(ANNOTATIONS, annotations, "");
					mLogger.info(msg.toString());
				}

				return annotations;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (UnknownAppException e) {
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
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_UNKNOWN)) {
					UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownDocIDException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.SECTION_UNKNOWN)) {
					UnknownPageExceptionFaultDetail faultDetail = new UnknownPageExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setPageNum(pageNum);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownPageException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_PERMISSION_DENIED)) {
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (UnknownAppException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownAppExceptionFaultDetail faultDetail = new UnknownAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownAppException(null, faultDetail);
		} catch (UnknownDocIDException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownDocIDException(null, faultDetail);
		} catch (UnknownPageException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownPageExceptionFaultDetail faultDetail = new UnknownPageExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setPageNum(e.getFaultInfo().getPageNum());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownPageException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
	public void setAnnotations(String license, String appName, String docID, String annotations,
			List<com.vignette.idm.server.NameValue> options)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException, LicenseException,
			UnknownAppException, UnknownDocIDException, AnnotationsFormatException, PermissionException,
			IDMRepositoryException, GeneralException {
		final String METHODNAME = "setAnnotations";

		try {
			String userID = null;
			try {
				// Validate arguments.
				if (docID == null || annotations == null) {
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

				docID = Validator.validateString(SERVICENAME, METHODNAME, DOCID, docID, false, false);

				annotations = Validator.validateString(SERVICENAME, METHODNAME, ANNOTATIONS, annotations, false, false);

				DocServiceUtils.validateSetAnnotationOptions(METHODNAME, options);

				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(START);
					msg.append(LICENSE, license, ", ");
					msg.append(APPNAME, appName, ", ");
					msg.append(DOCID, docID, ", ");
					msg.append(ANNOTATIONS, annotations, ", ");
					msg.append(OPTIONS, options, "");
					mLogger.info(msg.toString());
				}

				// Perform operation.
				IdmRepImpl repo = new IdmRepImpl(mLF.getTicket(license));
				userID = repo.getUserId();
				if (appName != null) {
					RecordCollection collection = new RecordCollection(repo);
					Validator.checkApplication(SERVICENAME, METHODNAME, collection, appName);
				}
				DocServiceUtils.checkPermission(METHODNAME, repo, appName, docID, READ_ACTION);
				Annotation annotation = new Annotation(repo);
				annotation.setAnnotations(docID, annotations, mForce);

				// Convert results.
				
				if (mLogger.isLoggable(Level.INFO)) {
					StringBufferWS msg = new StringBufferWS(COMPLETE);
					msg.append(LICENSE, license, "");
					mLogger.info(msg.toString());
				}

				return;
			} catch (InvalidParameterException e) {
				throw e;
			} catch (UnknownOptionNameException e) {
				throw e;
			} catch (UnknownOptionValueException e) {
				throw e;
			} catch (UnknownAppException e) {
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
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_UNKNOWN)) {
					UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setDocID(docID);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownDocIDException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.ANNOTATION_ACCESS_FAILURE)) {
					AnnotationsFormatExceptionFaultDetail faultDetail = new AnnotationsFormatExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(e.getMessage());
					faultDetail.setParameterName(ANNOTATIONS);
					faultDetail.setParameterValue(annotations);
					faultDetail.setWsMethodName(METHODNAME);
					faultDetail.setWsName(SERVICENAME);
					throw new AnnotationsFormatException(null, faultDetail);
				} else if (errorDef.equals(TTRepositoryErrors.DOCUMENT_PERMISSION_DENIED)) {
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
		} catch (AnnotationsFormatException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			AnnotationsFormatExceptionFaultDetail faultDetail = new AnnotationsFormatExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setParameterName(e.getFaultInfo().getParameterName());
			faultDetail.setParameterValue(e.getFaultInfo().getParameterValue());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new AnnotationsFormatException(null, faultDetail);
		} catch (InvalidParameterException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownOptionNameExceptionFaultDetail faultDetail = new UnknownOptionNameExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setOptionName(e.getFaultInfo().getOptionName());
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownOptionNameException(null, faultDetail);
		} catch (UnknownOptionValueException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new LicenseException(null, faultDetail);
		} catch (UnknownAppException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownAppExceptionFaultDetail faultDetail = new UnknownAppExceptionFaultDetail();
			faultDetail.setAppName(e.getFaultInfo().getAppName());
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownAppException(null, faultDetail);
		} catch (UnknownDocIDException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
			mLogger.severe(msg);

			UnknownDocIDExceptionFaultDetail faultDetail = new UnknownDocIDExceptionFaultDetail();
			faultDetail.setCallStack(Converter.getCallStack(e));
			faultDetail.setDocID(e.getFaultInfo().getDocID());
			faultDetail.setMessage(msg);
			faultDetail.setWsMethodName(e.getFaultInfo().getWsMethodName());
			faultDetail.setWsName(e.getFaultInfo().getWsName());
			throw new UnknownDocIDException(null, faultDetail);
		} catch (PermissionException e) {
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
			String msg = DocServiceUtils.format(e.getFaultInfo());
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
