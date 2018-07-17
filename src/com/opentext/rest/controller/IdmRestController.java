package com.opentext.rest.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.opentext.rest.model.AddEntry;
import com.opentext.rest.model.CancelCheckOut;
import com.opentext.rest.model.CheckInRevision;
import com.opentext.rest.model.CheckOutRevision;
import com.opentext.rest.model.CountRecords;
import com.opentext.rest.model.CreateRevision;
import com.opentext.rest.model.Delete;
import com.opentext.rest.model.DeleteRecords;
import com.opentext.rest.model.FreeSelectedResults;
import com.opentext.rest.model.GetAnnotations;
import com.opentext.rest.model.GetAppList;
import com.opentext.rest.model.GetCheckOutDetail;
import com.opentext.rest.model.GetConfigVar;
import com.opentext.rest.model.GetConfigVars;
import com.opentext.rest.model.GetDocument;
import com.opentext.rest.model.GetDocumentMTOM;
import com.opentext.rest.model.GetFieldList;
import com.opentext.rest.model.GetNumPages;
import com.opentext.rest.model.GetQueueInfo;
import com.opentext.rest.model.GetRendition;
import com.opentext.rest.model.GetRenditionDetails;
import com.opentext.rest.model.GetRenditionMTOM;
import com.opentext.rest.model.GetSelectedEntries;
import com.opentext.rest.model.GetSelectedQueueInfo;
import com.opentext.rest.model.GetSelectedResults;
import com.opentext.rest.model.GetTemplate;
import com.opentext.rest.model.InsertContentMTOM;
import com.opentext.rest.model.InsertRecord;
import com.opentext.rest.model.ListCheckedOutRevisions;
import com.opentext.rest.model.LockEntry;
import com.opentext.rest.model.LockNextEntry;
import com.opentext.rest.model.LogError;
import com.opentext.rest.model.LogEvent;
import com.opentext.rest.model.Login;
import com.opentext.rest.model.Logout;
import com.opentext.rest.model.PublishRevision;
import com.opentext.rest.model.ReleaseEntry;
import com.opentext.rest.model.SearchEntries;
import com.opentext.rest.model.SearchEntryRange;
import com.opentext.rest.model.SearchQueueInfo;
import com.opentext.rest.model.SearchRecords;
import com.opentext.rest.model.SearchRevisionHistory;
import com.opentext.rest.model.SelectEntries;
import com.opentext.rest.model.SelectQueueInfo;
import com.opentext.rest.model.SetAnnotations;
import com.opentext.rest.model.UnlockEntry;
import com.opentext.rest.model.UpdateContent;
import com.opentext.rest.model.UpdateContentMTOM;
import com.opentext.rest.model.UpdateEntry;
import com.opentext.rest.model.UpdateRecords;
import com.opentext.rest.model.insertContent;
import com.vignette.idm.common.NameValue;
import com.vignette.idm.server.AnnotationsFormatException;
import com.vignette.idm.server.AppFieldDef;
import com.vignette.idm.server.AppList;
import com.vignette.idm.server.CheckOutDetail;
import com.vignette.idm.server.CheckedOutException;
import com.vignette.idm.server.DatabaseException;
import com.vignette.idm.server.GeneralException;
import com.vignette.idm.server.IDMRepositoryException;
import com.vignette.idm.server.InvalidCredentialsException;
import com.vignette.idm.server.InvalidParameterException;
import com.vignette.idm.server.LicenseException;
import com.vignette.idm.server.NoAvailableLicenceException;
import com.vignette.idm.server.NotCheckedOutException;
import com.vignette.idm.server.NotLibAppException;
import com.vignette.idm.server.NotOwnerException;
import com.vignette.idm.server.PasswordExpiredException;
import com.vignette.idm.server.PermissionException;
import com.vignette.idm.server.QueueConnectionException;
import com.vignette.idm.server.QueueEntry;
import com.vignette.idm.server.QueueEntryLockFailureException;
import com.vignette.idm.server.QueueEntryLockedException;
import com.vignette.idm.server.QueueEntryMovedException;
import com.vignette.idm.server.QueueEntryNotLockedException;
import com.vignette.idm.server.QueueEntryUnknownException;
import com.vignette.idm.server.QueueException;
import com.vignette.idm.server.QueueIDUnknownException;
import com.vignette.idm.server.QueueInfo;
import com.vignette.idm.server.QueueSelectionUnknownException;
import com.vignette.idm.server.QueueVariableUnknownException;
import com.vignette.idm.server.RenditionDetail;
import com.vignette.idm.server.SearchResult;
import com.vignette.idm.server.TemplateDef;
import com.vignette.idm.server.UnknownAppException;
import com.vignette.idm.server.UnknownConfigVarException;
import com.vignette.idm.server.UnknownDocIDException;
import com.vignette.idm.server.UnknownEventException;
import com.vignette.idm.server.UnknownEventFieldException;
import com.vignette.idm.server.UnknownOptionNameException;
import com.vignette.idm.server.UnknownOptionValueException;
import com.vignette.idm.server.UnknownPageException;
import com.vignette.idm.server.UnknownPageInRangeException;
import com.vignette.idm.server.UnknownRenditionTypeException;
import com.vignette.idm.server.UnknownSubPageException;
import com.vignette.idm.server.UnknownTemplateException;
import com.vignette.idm.server.UnknownVarNamePatternException;

@Path("/rest")

public class IdmRestController extends IdmServiceInitializer {

	public static java.util.logging.Logger mLogger = java.util.logging.Logger.getLogger("Rest Controller");

	@Context
	HttpServletRequest m_request;

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// System Services
	//
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////

	@POST
	@Path("/getConfigVar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getConfigVar(GetConfigVar configVar) throws InvalidParameterException, LicenseException,
			UnknownConfigVarException, IDMRepositoryException, GeneralException {

		String varValue = sysServices.getConfigVar(configVar.getLicense(), configVar.getVarName());
		return varValue;

	}

	@POST
	@Path("/getConfigVars")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<NameValue> getConfigVars(GetConfigVars configVars) throws InvalidParameterException, LicenseException,
			UnknownVarNamePatternException, IDMRepositoryException, GeneralException {

		List<NameValue> vars = sysServices.getConfigVars(configVars.getLicense(), configVars.getVarNamePattern());
		return vars;

	}

	@POST
	@Path("/isValid")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean isValid(String license) throws InvalidParameterException, IDMRepositoryException, GeneralException {

		return sysServices.isValid(license);

	}

	@POST
	@Path("/logError")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String logError(LogError logErr)
			throws InvalidParameterException, LicenseException, IDMRepositoryException, GeneralException {

		String license = sysServices.logError(logErr.getLicense(), logErr.getMessage(), logErr.getReason());

		return license;
	}

	@POST
	@Path("/logEvent")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String logEvent(LogEvent logEve) throws InvalidParameterException, LicenseException, UnknownEventException,
			UnknownEventFieldException, IDMRepositoryException, GeneralException {

		String license = sysServices.logEvent(logEve.getLicense(), logEve.getCategory(), logEve.getName(),
				logEve.getFields());

		return license;
	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String login(Login login) throws InvalidParameterException, UnknownOptionNameException,
			UnknownOptionValueException, InvalidCredentialsException, NoAvailableLicenceException,
			PasswordExpiredException, IDMRepositoryException, GeneralException {
		mLogger.info("Came To LOGIN");
		String license = sysServices.login(login.getUserID(), login.getPassword(), login.getOptions());

		return license;
	}

	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout(Logout logout)
			throws InvalidParameterException, LicenseException, IDMRepositoryException, GeneralException {

		try {
			sysServices.logout(logout.getLicense());
			return Response.status(200).entity("Logged Out").build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// Application Services
	//
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////

	@POST
	@Path("/countRecords")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public long countRecords(CountRecords countRec)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException, LicenseException,
			UnknownAppException, PermissionException, DatabaseException, IDMRepositoryException, GeneralException {

		long count = appServices.countRecords(countRec.getLicense(), countRec.getAppName(), countRec.getCondition(),
				countRec.getOptions(), countRec.getNumRecords());
		return count;
	}

	@POST
	@Path("/deleteRecords")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Long deleteRecords(DeleteRecords deleteRecords)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException, LicenseException,
			UnknownAppException, PermissionException, DatabaseException, IDMRepositoryException, GeneralException {
		return appServices.deleteRecords(deleteRecords.getLicense(), deleteRecords.getAppName(),
				deleteRecords.getCondition(), deleteRecords.getOptions(), deleteRecords.getNumDeleted());
	}

	@POST
	@Path("/getAppList")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List getAppList(GetAppList appList)
			throws InvalidParameterException, LicenseException, IDMRepositoryException, GeneralException {
		mLogger.info(appList.getLicense());

		AppList apps = appServices.getAppList(appList.getLicense());
		System.out.println(apps.getAppDefs());
		return apps.getAppDefs();
	}

	@POST
	@Path("/getFieldList")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List getFieldList(GetFieldList getList) throws InvalidParameterException, LicenseException,
			UnknownAppException, IDMRepositoryException, GeneralException {

		List<AppFieldDef> fieldList = appServices.getFieldList(getList.getLicense(), getList.getAppName());
		return fieldList;

	}

	@POST
	@Path("/getTemplate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TemplateDef getTemplate(GetTemplate getTemplate) throws InvalidParameterException,
			UnknownOptionNameException, UnknownOptionValueException, LicenseException, UnknownAppException,
			PermissionException, DatabaseException, IDMRepositoryException, GeneralException, UnknownTemplateException {
		TemplateDef def = appServices.getTemplate(getTemplate.getLicense(), getTemplate.getAppName(),
				getTemplate.getTemplateName(), getTemplate.getOptions(), getTemplate.getTemplateDefs());
		return def;

	}

	@POST
	@Path("/insertContent")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Integer insertContent(insertContent insertContent)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException, LicenseException,
			UnknownAppException, PermissionException, DatabaseException, IDMRepositoryException, GeneralException {

		return appServices.insertContent(insertContent.getLicense(), insertContent.getAppName(),
				insertContent.getFields(), insertContent.getContentType(), insertContent.getContent(),
				insertContent.getOptions(), insertContent.getDocID(), insertContent.getNumPages());

	}

	@POST
	@Path("/insertContentMTOM")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Integer insertContentMTOM(InsertContentMTOM insertContentMTOM)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException, LicenseException,
			UnknownAppException, PermissionException, DatabaseException, IDMRepositoryException, GeneralException {

		return appServices.insertContentMTOM(insertContentMTOM.getLicense(), insertContentMTOM.getAppName(),
				insertContentMTOM.getFields(), insertContentMTOM.getContentType(), insertContentMTOM.getContent(),
				insertContentMTOM.getOptions(), insertContentMTOM.getDocID(), insertContentMTOM.getNumPages());

	}

	@POST
	@Path("/insertRecord")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertRecord(InsertRecord insertRec) throws InvalidParameterException, LicenseException,
			UnknownAppException, PermissionException, DatabaseException, IDMRepositoryException, GeneralException {
		try {
			appServices.insertRecord(insertRec.getLicense(), insertRec.getAppName(), insertRec.getFields());
			return Response.status(200).entity("Inserted Successfully").build();

		} catch (Exception e) {
			return Response.status(200).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/searchRecords")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SearchResult searchRecords(SearchRecords searchRec) throws InvalidParameterException, LicenseException,
			UnknownAppException, PermissionException, DatabaseException, IDMRepositoryException, GeneralException {
		return appServices.searchRecords(searchRec.getLicense(), searchRec.getAppName(), searchRec.getFieldNames(),
				searchRec.getCondition(), searchRec.getOrderBy(), searchRec.getOffset(), searchRec.getMaxRecords(),
				searchRec.getRecords());
	}

	@POST
	@Path("/updateContent")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Integer updateContent(UpdateContent updateContent) throws InvalidParameterException,
			UnknownOptionNameException, UnknownOptionValueException, LicenseException, UnknownAppException,
			PermissionException, DatabaseException, IDMRepositoryException, GeneralException, UnknownDocIDException {
		return appServices.updateContent(updateContent.getLicense(), updateContent.getAppName(),
				updateContent.getDocID(), updateContent.getFields(), updateContent.getCondition(),
				updateContent.getContentType(), updateContent.getContent(), updateContent.getOptions(),
				updateContent.getNewDocID(), updateContent.getNumPages());
	}

	@POST
	@Path("/updateContentMTOM")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Integer updateContentMTOM(UpdateContentMTOM updateContent) throws InvalidParameterException,
			UnknownOptionNameException, UnknownOptionValueException, LicenseException, UnknownAppException,
			PermissionException, DatabaseException, IDMRepositoryException, GeneralException, UnknownDocIDException {
		return appServices.updateContentMTOM(updateContent.getLicense(), updateContent.getAppName(),
				updateContent.getDocID(), updateContent.getFields(), updateContent.getCondition(),
				updateContent.getContentType(), updateContent.getContent(), updateContent.getOptions(),
				updateContent.getNewDocID(), updateContent.getNumPages());
	}

	@POST
	@Path("/updateRecords")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Long updateRecords(UpdateRecords updateRecords)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException, LicenseException,
			UnknownAppException, PermissionException, DatabaseException, IDMRepositoryException, GeneralException {

		return appServices.updateRecords(updateRecords.getLicense(), updateRecords.getAppName(),
				updateRecords.getFields(), updateRecords.getCondition(), updateRecords.getNumUpdated());

	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// Library Services
	//
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////

	@POST
	@Path("/cancelCheckOut")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cancelCheckOut( CancelCheckOut cancelCheckOut)
			throws InvalidParameterException, LicenseException, NotLibAppException, UnknownDocIDException,
			NotCheckedOutException, NotOwnerException, PermissionException, IDMRepositoryException, GeneralException {

		try {
			libServices.cancelCheckOut(cancelCheckOut.getLicense(), cancelCheckOut.getAppName(), cancelCheckOut.getDocID());
			return Response.status(200).entity("Cancelled").build();
		} catch (Exception e) {
			return Response.status(200).entity(e.getMessage()).build();
		}

	};

	@POST
	@Path("/checkInRevision")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Long checkInRevision( CheckInRevision checkInRevision)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException, LicenseException,
			NotLibAppException, UnknownDocIDException, NotCheckedOutException, NotOwnerException, PermissionException,
			IDMRepositoryException, GeneralException {

		return libServices.checkInRevision(checkInRevision.getLicense(), checkInRevision.getAppName(), checkInRevision.getDocID(),
				checkInRevision.getStatus(), checkInRevision.getContentType(), checkInRevision.getContent(),
				checkInRevision.getOptions(), checkInRevision.getNumPages());
	}

	@POST
	@Path("/checkOutRevision")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> checkOutRevision(
			CheckOutRevision checkOutRevision) throws InvalidParameterException, UnknownOptionNameException,
			UnknownOptionValueException, LicenseException, NotLibAppException, UnknownDocIDException,
			CheckedOutException, PermissionException, IDMRepositoryException, GeneralException {

		return libServices.checkOutRevision(checkOutRevision.getLicense(), checkOutRevision.getAppName(), checkOutRevision.getDocID(),
				checkOutRevision.getComment(), checkOutRevision.getOptions(), checkOutRevision.getContent(),
				checkOutRevision.getContentTyp());
	};

	@POST
	@Path("/createRevision")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> createRevision( CreateRevision createRevision)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException, LicenseException,
			NotLibAppException, PermissionException, DatabaseException, IDMRepositoryException, GeneralException {

		return libServices.createRevision(createRevision.getLicense(), createRevision.getAppName(), createRevision.getFields(),
				createRevision.getStatus(), createRevision.getContentType(), createRevision.getContent(),
				createRevision.getOptions(), createRevision.getDocID(), createRevision.getNumPages());

	};

	@POST
	@Path("/getCheckOutDetail")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CheckOutDetail getCheckOutDetail(GetCheckOutDetail getCheckOutDetail)
			throws InvalidParameterException, LicenseException, NotLibAppException, UnknownDocIDException,
			NotCheckedOutException, PermissionException, IDMRepositoryException, GeneralException {

		return libServices.getCheckOutDetail(getCheckOutDetail.getLicense(), getCheckOutDetail.getAppName(),
				getCheckOutDetail.getDocID());

	}

	@POST
	@Path("/listCheckedOutRevisions/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> listCheckedOutRevisions(ListCheckedOutRevisions listCheckedOutRevisions)
			throws InvalidParameterException, LicenseException, NotLibAppException, PermissionException,
			IDMRepositoryException, GeneralException {
		return libServices.listCheckedOutRevisions(listCheckedOutRevisions.getLicense(),
				listCheckedOutRevisions.getAppName(), listCheckedOutRevisions.isAll());
	}

	@POST
	@Path("/publishRevision")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> publishRevision( PublishRevision publishRevision)
			throws InvalidParameterException, LicenseException, NotLibAppException, UnknownDocIDException,
			CheckedOutException, PermissionException, IDMRepositoryException, GeneralException {
		return libServices.publishRevision(publishRevision.getLicense(), publishRevision.getAppName(), publishRevision.getDocID(),
				publishRevision.getNumPages());
	}

	@POST
	@Path("/searchRevisionHistory")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SearchResult searchRevisionHistory(
			SearchRevisionHistory searchRevisionHistory)
			throws InvalidParameterException, LicenseException, NotLibAppException, UnknownDocIDException,
			PermissionException, DatabaseException, IDMRepositoryException, GeneralException {

		return libServices.searchRevisionHistory(searchRevisionHistory.getLicense(), searchRevisionHistory.getAppName(),
				searchRevisionHistory.getDocID(), searchRevisionHistory.getFieldNames(),
				searchRevisionHistory.getCondition(), searchRevisionHistory.getOrderBy(),
				searchRevisionHistory.getOffset(), searchRevisionHistory.getMaxRecords(),
				searchRevisionHistory.getRevisionHistor());
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// Queue Services
	//
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	@POST
	@Path("/addEntry")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Long addEntry( AddEntry addEntry)
			throws InvalidParameterException, LicenseException, QueueException, QueueConnectionException,
			QueueIDUnknownException, QueueVariableUnknownException, GeneralException {
		return queueServices.addEntry(addEntry.getLicense(), addEntry.getQueueServer(), addEntry.getQueueServiceName(),
				addEntry.getQueueID(), addEntry.getEntryVariables(), addEntry.getEntryID());
	}

	@POST
	@Path("/freeSelectedResults")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response freeSelectedResults( FreeSelectedResults freeSelectedResults)
			throws InvalidParameterException, LicenseException, QueueConnectionException,
			QueueSelectionUnknownException, GeneralException {
		try {
			queueServices.freeSelectedResults(freeSelectedResults.getLicense(), freeSelectedResults.getQueueServer(),
					freeSelectedResults.getQueueServiceName(), freeSelectedResults.getSelectionID());
			return Response.status(200).entity("Success").build();
		} catch (Exception e) {
			return Response.status(200).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/getQueueInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public QueueInfo getQueueInfo(GetQueueInfo getQueueInfo)
			throws InvalidParameterException, LicenseException, QueueException, QueueConnectionException,
			QueueIDUnknownException, GeneralException {
		return queueServices.getQueueInfo(getQueueInfo.getLicense(), getQueueInfo.getQueueServer(), getQueueInfo.getQueueServiceName(),
				getQueueInfo.getQueueID(), getQueueInfo.getQueueInfo());

	}

	@POST
	@Path("/getSelectedEntries")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<QueueEntry> getSelectedEntries(
			GetSelectedEntries getSelectedEntries) throws InvalidParameterException, LicenseException, QueueException,
			QueueConnectionException, QueueSelectionUnknownException, GeneralException {
		return queueServices.getSelectedEntries(getSelectedEntries.getLicense(), getSelectedEntries.getQueueServer(),
				getSelectedEntries.getQueueServiceName(), getSelectedEntries.getSelectionID(),
				getSelectedEntries.getOffset(), getSelectedEntries.getMaxEntries(),
				getSelectedEntries.getQueueEntries());
	}

	@POST
	@Path("/getSelectedQueueInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<QueueInfo> getSelectedQueueInfo(
			GetSelectedQueueInfo getSelectedQueueInfo) throws InvalidParameterException, LicenseException,
			QueueException, QueueConnectionException, QueueSelectionUnknownException, GeneralException {
		return queueServices.getSelectedQueueInfo(getSelectedQueueInfo.getLicense(), getSelectedQueueInfo.getQueueServer(),
				getSelectedQueueInfo.getQueueServiceName(), getSelectedQueueInfo.getSelectionID(),
				getSelectedQueueInfo.getOffset(), getSelectedQueueInfo.getMaxQueueInfos(),
				getSelectedQueueInfo.getQueueInfo());
	}

	@POST
	@Path("/getSelectedResults")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SearchResult getSelectedResults(
			GetSelectedResults getSelectedResults) throws InvalidParameterException, LicenseException, QueueException,
			QueueConnectionException, QueueSelectionUnknownException, GeneralException {
		return queueServices.getSelectedResults(getSelectedResults.getLicense(), getSelectedResults.getQueueServer(),
				getSelectedResults.getQueueServiceName(), getSelectedResults.getSelectionID(),
				getSelectedResults.getOffset(), getSelectedResults.getMaxRows(), getSelectedResults.getValues());
	}

	@POST
	@Path("/lockEntry")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public QueueEntry lockEntry( LockEntry lockEntry)
			throws InvalidParameterException, LicenseException, QueueException, QueueConnectionException,
			QueueIDUnknownException, QueueEntryUnknownException, QueueEntryLockedException,
			QueueEntryLockFailureException, QueueEntryMovedException, GeneralException {
		return queueServices.lockEntry(lockEntry.getLicense(), lockEntry.getQueueServer(), lockEntry.getQueueServiceName(),
				lockEntry.getQueueID(), lockEntry.getEntryID(), lockEntry.getQueueEntry());
	}

	@POST
	@Path("/lockNextEntry")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public QueueEntry lockNextEntry( LockNextEntry lockNextEntry)
			throws InvalidParameterException, LicenseException, QueueException, QueueConnectionException,
			QueueIDUnknownException, QueueEntryLockFailureException, GeneralException {
		return queueServices.lockNextEntry(lockNextEntry.getLicense(), lockNextEntry.getQueueServer(), lockNextEntry.getQueueServiceName(),
				lockNextEntry.getQueueIDs(), lockNextEntry.getQueueEntry());
	}

	@POST
	@Path("/releaseEntry")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response releaseEntry( ReleaseEntry releaseEntry)
			throws InvalidParameterException, LicenseException, QueueException, QueueConnectionException,
			QueueEntryNotLockedException, GeneralException {
		try {
			queueServices.releaseEntry(releaseEntry.getLicense(), releaseEntry.getQueueServer(), releaseEntry.getQueueServiceName(),
					releaseEntry.getEntryID(), releaseEntry.getEntryVariables());
			return Response.status(200).entity("Released Entry").build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/searchEntries")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SearchResult searchEntries( SearchEntries searchEntries)
			throws InvalidParameterException, LicenseException, QueueException, QueueConnectionException,
			QueueIDUnknownException, GeneralException {
		return queueServices.searchEntries(searchEntries.getLicense(), searchEntries.getQueueServer(), searchEntries.getQueueServiceName(),
				searchEntries.getQueueID(), searchEntries.getVarNames(), searchEntries.getCondition(),
				searchEntries.getOrderBy(), searchEntries.getOffset(), searchEntries.getMaxEntries(),
				searchEntries.getVariables());
	}

	@POST
	@Path("/searchEntryRange")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> searchEntryRange(
			SearchEntryRange searchEntryRange) throws InvalidParameterException, LicenseException, QueueException,
			QueueConnectionException, QueueIDUnknownException, GeneralException {
		return queueServices.searchEntryRange(searchEntryRange.getLicense(), searchEntryRange.getQueueServer(),
				searchEntryRange.getQueueServiceName(), searchEntryRange.getQueueID(), searchEntryRange.getVarNames(),
				searchEntryRange.getCondition(), searchEntryRange.getOrderBy(), searchEntryRange.getOffset(),
				searchEntryRange.getRangeSize(), searchEntryRange.getVariables(), searchEntryRange.getOffset0(),
				searchEntryRange.getTotalNumMatched());
	}

	@POST
	@Path("/searchQueueInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SearchResult searchQueueInfo( SearchQueueInfo searchQueueInfo)
			throws InvalidParameterException, LicenseException, QueueException, QueueConnectionException,
			QueueIDUnknownException, GeneralException {
		return queueServices.searchQueueInfo(searchQueueInfo.getLicense(), searchQueueInfo.getQueueServer(),
				searchQueueInfo.getQueueServiceName(), searchQueueInfo.getPropNames(), searchQueueInfo.getCondition(),
				searchQueueInfo.getOrderBy(), searchQueueInfo.getOffset(), searchQueueInfo.getMaxQueueInfos(),
				searchQueueInfo.getProperties());
	}

	@POST
	@Path("/selectEntries")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> selectEntries( SelectEntries selectEntries)
			throws InvalidParameterException, LicenseException, QueueException, QueueConnectionException,
			QueueIDUnknownException, GeneralException {
		return queueServices.selectEntries(selectEntries.getLicense(), selectEntries.getQueueServer(), selectEntries.getQueueServiceName(),
				selectEntries.getQueueID(), selectEntries.getVarNames(), selectEntries.getCondition(),
				selectEntries.getOrderBy(), selectEntries.getSelectionID(), selectEntries.getNumSelected());
	}

	@POST
	@Path("/selectQueueInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response selectQueueInfo( SelectQueueInfo selectQueueInfo)
			throws InvalidParameterException, LicenseException, QueueException, QueueConnectionException,
			QueueIDUnknownException, GeneralException {
		try {
			queueServices.selectQueueInfo(selectQueueInfo.getLicense(), selectQueueInfo.getQueueServer(),
					selectQueueInfo.getQueueServiceName(), selectQueueInfo.getVarNames(),
					selectQueueInfo.getCondition(), selectQueueInfo.getOrderBy(), selectQueueInfo.getSelectionID(),
					selectQueueInfo.getNumSelected());
			return Response.status(200).entity("Selected").build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/unlockEntry")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response unlockEntry( UnlockEntry unlockEntry)
			throws InvalidParameterException, LicenseException, QueueException, QueueConnectionException,
			QueueEntryNotLockedException, GeneralException {
		try {
			queueServices.unlockEntry(unlockEntry.getLicense(), unlockEntry.getQueueServer(), unlockEntry.getQueueServiceName(),
					unlockEntry.getEntryID());
			return Response.status(200).entity("unlocked Successfully").build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/updateEntry")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEntry( UpdateEntry updateEntry)
			throws InvalidParameterException, LicenseException, QueueException, QueueConnectionException,
			QueueEntryNotLockedException, QueueVariableUnknownException, GeneralException {
		try {
			queueServices.updateEntry(updateEntry.getLicense(), updateEntry.getQueueServer(), updateEntry.getQueueServiceName(),
					updateEntry.getEntryID(), updateEntry.getEntryVariables());
			return Response.status(200).entity("Updated Successfully").build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// Doc Services
	//
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(Delete delete) throws InvalidParameterException, LicenseException, UnknownAppException,
			UnknownDocIDException, PermissionException, IDMRepositoryException, GeneralException {
		try {
			docServices.delete(delete.getLicense(), delete.getAppName(), delete.getDocID());
			return Response.status(200).entity("Deleted").build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/getAnnotations")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getAnnotations(GetAnnotations getAnnotations)
			throws InvalidParameterException, LicenseException, UnknownAppException, UnknownDocIDException,
			UnknownPageException, PermissionException, IDMRepositoryException, GeneralException {
		return docServices.getAnnotations(getAnnotations.getLicense(), getAnnotations.getAppName(),
				getAnnotations.getDocID(), getAnnotations.getPageNum(), getAnnotations.getSubPageNum());
	}

	@POST
	@Path("/getDocument")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDocument(GetDocument getDocument)
			throws InvalidParameterException, LicenseException, UnknownAppException, UnknownDocIDException,
			UnknownPageInRangeException, PermissionException, IDMRepositoryException, GeneralException {
		return Response.status(200).entity(docServices.getDocument(getDocument.getLicense(), getDocument.getAppName(),
				getDocument.getDocID(), getDocument.getPages())).build();
	}

	@POST
	@Path("/getDocumentMTOM")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getDocumentMTOM(GetDocumentMTOM getDocumentMTOM)
			throws InvalidParameterException, LicenseException, UnknownAppException, UnknownDocIDException,
			UnknownPageInRangeException, PermissionException, IDMRepositoryException, GeneralException {
		return docServices.getDocumentMTOM(getDocumentMTOM.getLicense(), getDocumentMTOM.getAppName(),
				getDocumentMTOM.getDocID(), getDocumentMTOM.getPages());
	}

	@POST
	@Path("/getNumPages")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Long getNumPages(GetNumPages getNumPage) throws InvalidParameterException, LicenseException,
			UnknownAppException, UnknownDocIDException, PermissionException, IDMRepositoryException, GeneralException {
		return docServices.getNumPages(getNumPage.getLicense(), getNumPage.getAppName(), getNumPage.getDocID());
	}

	@POST
	@Path("/getRendition")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getRendition( GetRendition getRendition)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException, LicenseException,
			UnknownAppException, UnknownDocIDException, UnknownPageException, UnknownSubPageException,
			UnknownRenditionTypeException, PermissionException, IDMRepositoryException, GeneralException {
		return docServices.getRendition(getRendition.getLicense(), getRendition.getAppName(), getRendition.getDocID(),
				getRendition.getPageNum(), getRendition.getSubPageNum(), getRendition.getRenditionType(),
				getRendition.getOptions(), getRendition.getRendition(), getRendition.getHasNextSubPage(),
				getRendition.getContentType(), getRendition.getMimeType());
	}

	@POST
	@Path("/getRenditionDetails")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<RenditionDetail> getRenditionDetails(GetRenditionDetails getRenditionDetails)
			throws InvalidParameterException, LicenseException, UnknownAppException, UnknownDocIDException,
			UnknownPageException, PermissionException, IDMRepositoryException, GeneralException {
		return docServices.getRenditionDetails(getRenditionDetails.getLicense(), getRenditionDetails.getAppName(),
				getRenditionDetails.getDocID(), getRenditionDetails.getPageNum());
	}

	@POST
	@Path("/getRenditionMTOM")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getRenditionMTOM(
			GetRenditionMTOM getRenditionMTOM)
			throws GeneralException, IDMRepositoryException, InvalidParameterException, LicenseException,
			PermissionException, UnknownAppException, UnknownDocIDException, UnknownOptionNameException,
			UnknownOptionValueException, UnknownPageException, UnknownRenditionTypeException, UnknownSubPageException {
		return docServices.getRenditionMTOM(getRenditionMTOM.getLicense(), getRenditionMTOM.getAppName(), getRenditionMTOM.getDocID(),
				getRenditionMTOM.getPageNum(), getRenditionMTOM.getSubPageNum(), getRenditionMTOM.getRenditionType(),
				getRenditionMTOM.getOptions(), getRenditionMTOM.getRendition(), getRenditionMTOM.getHasNextSubPage(),
				getRenditionMTOM.getContentType(), getRenditionMTOM.getMimeType());
	}

	@POST
	@Path("/setAnnotations")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response setAnnotations( SetAnnotations setAnnotations)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException, LicenseException,
			UnknownAppException, UnknownDocIDException, AnnotationsFormatException, PermissionException,
			IDMRepositoryException, GeneralException {
		try {
			docServices.setAnnotations(setAnnotations.getLicense(), setAnnotations.getAppName(), setAnnotations.getDocID(),
					setAnnotations.getAnnotations(), setAnnotations.getOptions());
			return Response.status(200).entity("Annotation added successfully").build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

}
