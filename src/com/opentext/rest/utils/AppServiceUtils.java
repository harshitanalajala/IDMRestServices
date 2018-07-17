package com.opentext.rest.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.DataSource;

import com.towertechnology.common.errorhandling.TTException;
import com.vignette.idm.common.ApplicationEntry;
import com.vignette.idm.common.Defaults;
import com.vignette.idm.server.AppDataType;
import com.vignette.idm.server.AppDef;
import com.vignette.idm.server.AppFieldDef;
import com.vignette.idm.server.AppList;
import com.vignette.idm.server.ArrayOfResultRow;
import com.vignette.idm.server.ConversionFunction;
import com.vignette.idm.server.DisplayFieldDef;
import com.vignette.idm.server.EntryFieldDef;
import com.vignette.idm.server.EntryType;
import com.vignette.idm.server.InvalidParameterException;
import com.vignette.idm.server.InvalidParameterExceptionFaultDetail;
import com.vignette.idm.server.NameValue;
import com.vignette.idm.server.PermissionException;
import com.vignette.idm.server.PermissionExceptionFaultDetail;
import com.vignette.idm.server.ResultRow;
import com.vignette.idm.server.SearchResult;
import com.vignette.idm.server.TemplateDef;
import com.vignette.idm.server.UnknownOptionNameException;
import com.vignette.idm.server.UnknownOptionNameExceptionFaultDetail;
import com.vignette.idm.server.UnknownOptionValueException;
import com.vignette.idm.server.UnknownOptionValueExceptionFaultDetail;
import com.vignette.idm.server.common.Converter;
import com.vignette.idm.server.common.ExceptionMessagesFormatter;
import com.vignette.idm.server.common.LicenceFactory;
import com.vignette.idm.server.common.Validator;
import com.vignette.idm.services.ColAttr;
import com.vignette.idm.services.ColumnDef;
import com.vignette.idm.services.DocId;
import com.vignette.idm.services.DoctypeSecurity;
import com.vignette.idm.services.IdmRepImpl;
import com.vignette.idm.services.Misc;
import com.vignette.idm.services.SQLStatement;

public class AppServiceUtils {

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
	private static final String START = "Start: ";
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
	private static ExceptionMessagesFormatter mFormatter;
	private static String mDoctypePriv;
	private static boolean mNewDoc;
	private static String mDiskset;
	private static String mPool;

	public static String validateCondition(String aMethodName, String aCondition, boolean aOptional,
			boolean aDocIdAllowed, boolean aRecIdAllowed) throws InvalidParameterException {
		// Perform general string validation.
		String condition = Validator.validateString(SERVICENAME, aMethodName, CONDITION, aCondition, aOptional, false);

		// Validate the content of the condition string.
		if (condition != null && condition.length() != 0) {
			StringTokenizer tokenizer = new StringTokenizer(condition, DELIMITERS_COND, true);
			String token;
			while (tokenizer.hasMoreTokens()) {
				token = tokenizer.nextToken();

				if (aDocIdAllowed && token.equalsIgnoreCase(HASH_DOCID)) {
					validateDocIdInCondition(aMethodName, tokenizer, aCondition);
				} else if (aRecIdAllowed && token.equalsIgnoreCase(RECID)) {
					System.out.println("Entered RecID");
					validateRecIdInCondition(aMethodName, tokenizer, aCondition);
				} else if (token.startsWith("#")) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(CONDITION);
					faultDetail.setParameterValue(aCondition);
					faultDetail.setWsMethodName(aMethodName);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}
			}
		}
		return condition;
	}

	public static void validateDocIdInCondition(String aMethodName, StringTokenizer aTokenizer, String aCondition)
			throws InvalidParameterException {
		// Check if a #DOCID# term is complete.
		String operator = "";
		String token;
		for (;;) {
			if (aTokenizer.hasMoreTokens() == false) {
				InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
				faultDetail.setCallStack(null);
				faultDetail.setMessage(null);
				faultDetail.setParameterName(CONDITION);
				faultDetail.setParameterValue(aCondition);
				faultDetail.setWsMethodName(aMethodName);
				faultDetail.setWsName(SERVICENAME);
				throw new InvalidParameterException(null, faultDetail);
			}

			token = aTokenizer.nextToken();

			if (token.equals(" ")) {
				continue;
			} else if (token.equalsIgnoreCase(IS) || token.equalsIgnoreCase(NOT) || token.equalsIgnoreCase(NULL)) {
				operator += " " + token;
				if (token.equalsIgnoreCase(NULL)) {
					break;
				}
			} else if (isDisallowedPredicates(token, aMethodName, aCondition)) {
			} else if (DELIMITERS_COND.indexOf(token) == -1) {
				DocId ifn = new DocId(token);
				if (ifn.toString().length() == 0) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(CONDITION);
					faultDetail.setParameterValue(aCondition);
					faultDetail.setWsMethodName(aMethodName);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}
				break;
			} else {
				operator += token;
			}
		}

		// Check for valid operators with #DOCID#.
		operator = operator.trim();
		if (operator.equals(EQ) || operator.equals(NEQ) || operator.equalsIgnoreCase(ISNULL)
				|| operator.equalsIgnoreCase(ISNOTNULL)) {
			return;
		}

		InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
		faultDetail.setCallStack(null);
		faultDetail.setMessage(null);
		faultDetail.setParameterName(CONDITION);
		faultDetail.setParameterValue(aCondition);
		faultDetail.setWsMethodName(aMethodName);
		faultDetail.setWsName(SERVICENAME);
		throw new InvalidParameterException(null, faultDetail);
	}

	public static void validateRecIdInCondition(String aMethodName, StringTokenizer aTokenizer, String aCondition)
			throws InvalidParameterException {
		// Check if a #RECID# term is complete.
		String operator = "";
		String token;
		for (;;) {
			if (aTokenizer.hasMoreTokens() == false) {
				InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
				faultDetail.setCallStack(null);
				faultDetail.setMessage(null);
				faultDetail.setParameterName(CONDITION);
				faultDetail.setParameterValue(aCondition);
				faultDetail.setWsMethodName(aMethodName);
				faultDetail.setWsName(SERVICENAME);
				throw new InvalidParameterException(null, faultDetail);
			}

			token = aTokenizer.nextToken();

			if (token.equals(" ")) {
				continue;
			} else if (token.equalsIgnoreCase(IS) || token.equalsIgnoreCase(NOT) || token.equalsIgnoreCase(NULL)) {
				operator += " " + token;
				if (token.equalsIgnoreCase(NULL)) {
					break;
				}
			} else if (isDisallowedPredicates(token, aMethodName, aCondition)) {
			} else if (DELIMITERS_COND.indexOf(token) == -1) {
				try {
					Integer.parseInt(token);
				} catch (NumberFormatException e) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(CONDITION);
					faultDetail.setParameterValue(aCondition);
					faultDetail.setWsMethodName(aMethodName);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}
				break;
			} else {
				operator += token;
			}
		}

		// Check for valid operators with #RECID#.
		operator = operator.trim();
		if (operator.equals(EQ) || operator.equals(NEQ) || operator.equals(LT) || operator.equals(LTE)
				|| operator.equals(GT) || operator.equals(GTE) || operator.equalsIgnoreCase(ISNULL)
				|| operator.equalsIgnoreCase(ISNOTNULL)) {
			return;
		}

		InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
		faultDetail.setCallStack(null);
		faultDetail.setMessage(null);
		faultDetail.setParameterName(CONDITION);
		faultDetail.setParameterValue(aCondition);
		faultDetail.setWsMethodName(aMethodName);
		faultDetail.setWsName(SERVICENAME);
		throw new InvalidParameterException(null, faultDetail);
	}

	public static boolean isDisallowedPredicates(String aToken, String aMethodName, String aCondition)
			throws InvalidParameterException {
		if (aToken.equalsIgnoreCase(BETWEEN) || aToken.equalsIgnoreCase(IN) || aToken.equalsIgnoreCase(LIKE)
				|| aToken.equalsIgnoreCase(ALL) || aToken.equalsIgnoreCase(SOME) || aToken.equalsIgnoreCase(ANY)
				|| aToken.equalsIgnoreCase(EXISTS) || aToken.equalsIgnoreCase(UNIQUE)
				|| aToken.equalsIgnoreCase(OVERLAPS) || aToken.equalsIgnoreCase(MATCH)) {
			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setParameterName(CONDITION);
			faultDetail.setParameterValue(aCondition);
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(SERVICENAME);
			throw new InvalidParameterException(null, faultDetail);
		}
		return false;
	}

	public static void validateNoOptions(String aMethodName, List<NameValue> aOptions)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException {
		// Perform general name/value pair validation.
		Validator.validateNameValueList(SERVICENAME, aMethodName, OPTIONS, aOptions, true, true, false, false);

		// Perform specific option validation.
		if (aOptions != null) {
			ListIterator<NameValue> itor = aOptions.listIterator();
			while (itor.hasNext()) {
				NameValue nv = itor.next();

				// Not a valid option.
				UnknownOptionNameExceptionFaultDetail faultDetail = new UnknownOptionNameExceptionFaultDetail();
				faultDetail.setCallStack(null);
				faultDetail.setMessage(null);
				faultDetail.setOptionName(nv.getName());
				faultDetail.setWsMethodName(aMethodName);
				faultDetail.setWsName(SERVICENAME);
				throw new UnknownOptionNameException(null, faultDetail);
			}
		}
	}

	public static SQLStatement constructSelect(String aTableName, List<String> aFields, String aCondition,
			String aDtWhereClause, String aOrderBy, ColumnDef aColumns) {
		SQLStatement statement = new SQLStatement(SELECT + " ");
		constructSelectFieldList(statement, aFields);
		statement.append(" " + FROM + " " + aTableName);
		constructWhereClause(statement, null, aCondition, aDtWhereClause, aColumns);
		constructOrderByClause(statement, aOrderBy);
		return statement;
	}

	public static void constructSelectFieldList(SQLStatement aStatement, List<String> aFields) {
		for (int i = 0; i < aFields.size(); i++) {
			String fld = aFields.get(i);

			if (i > 0) {
				aStatement.append(", ");
			}
			if (fld.equalsIgnoreCase(HASH_DOCID)) {
				aStatement.append(IFNDS + ", " + IFNID);
			} else if (fld.equalsIgnoreCase(RECID)) {
				aStatement.append(TOWID);
			} else {
				aStatement.append(fld);
			}
		}
	}

	public static void constructWhereClause(SQLStatement aStatement, String aDocID, String aCondition,
			String aDtWhereClause, ColumnDef aColumns) {
		boolean whereAdded = false;

		// If an IFN is specified, add it a the front of the where clause.
		if (aDocID != null) {
			DocId ifn = new DocId(aDocID);
			aStatement.append(" " + WHERE + " (" + IFNDS + " = " + ifn.getDiskset() + " " + AND + " " + IFNID + " = "
					+ ifn.getID() + ")");
			whereAdded = true;
		}

		// If condition is specified, add it to the where clause.
		if (aCondition != null && aCondition.length() != 0) {
			if (whereAdded) {
				aStatement.append(" " + AND + " ");
			} else {
				aStatement.append(" " + WHERE + " ");
				whereAdded = true;
			}
			parseCondition(aStatement, aCondition, aColumns);
		}

		// If a document type clause is specified, add it to the where clause.
		if (aDtWhereClause.length() != 0) {
			if (whereAdded) {
				aStatement.append(" " + AND + " " + aDtWhereClause);
			} else {
				aStatement.append(" " + WHERE + " " + aDtWhereClause);
				whereAdded = true;
			}
		}
	}

	public static void constructOrderByClause(SQLStatement aStatement, String aOrderBy) {
		if (aOrderBy != null && aOrderBy.length() != 0) {
			aStatement.append(" " + ORDER_BY + " ");
			parseOrderBy(aStatement, aOrderBy);
		}
	}

	public static void parseOrderBy(SQLStatement aStatement, String aOrderBy) {
		StringTokenizer tokenizer = new StringTokenizer(aOrderBy, DELIMITERS_ORDER, true);

		String token;
		while (tokenizer.hasMoreTokens()) {
			token = tokenizer.nextToken();

			if (token.equalsIgnoreCase(HASH_DOCID)) {
				parseDocIdInOrderBy(tokenizer, aStatement);
			} else if (token.equalsIgnoreCase(RECID)) {
				aStatement.append(TOWID);
			} else {
				aStatement.append(token);
			}
		}
	}

	public static void parseDocIdInOrderBy(StringTokenizer aTokenizer, SQLStatement aStatement) {
		// Analyse tokens until a COMMA or a SORT ORDER SPECIFIER is found
		// or until the end of the order-by string is reached.
		String direction = "";
		String comma = "";
		String token;
		while (aTokenizer.hasMoreTokens()) {
			token = aTokenizer.nextToken();

			if (token.equals(" ")) {
				// Ignore spaces.
				continue;
			} else if (token.equals(",")) {
				// We only encounter a comma if there was no sort order
				// specifier thus must remember to add the comma to the
				// resulting order-by clause.
				comma = token;
				break;
			} else if (DELIMITERS_ORDER.indexOf(token) == -1) {
				// This is expected to be a sort order specifier.
				direction = " " + token;
				break;
			}
		}

		// Add IFNDS and IFNID to the order-by clause.
		aStatement.append(IFNDS + direction + ", " + IFNID + direction + comma);
	}

	public static void parseCondition(SQLStatement aStatement, String aCondition, ColumnDef aColumns) {
		StringTokenizer tokenizer = new StringTokenizer(aCondition, DELIMITERS_COND, true);

		String colName = null;
		boolean isBetween = false;
		String token;
		while (tokenizer.hasMoreTokens()) {
			token = tokenizer.nextToken();

			if (token.equalsIgnoreCase(HASH_DOCID)) {
				parseDocIdInCondition(tokenizer, aStatement);
			} else if (token.equalsIgnoreCase(RECID)) {
				aStatement.append(TOWID);
			} else if (token.equalsIgnoreCase(AND)) {
				if (isBetween) {
					// AND used in a BETWEEN clause.
					isBetween = false;
				} else {
					// AND used to join clauses.
					colName = null;
				}
				aStatement.append(token);
			} else if (token.equalsIgnoreCase(OR)) {
				colName = null;
				aStatement.append(token);
			} else if (token.equalsIgnoreCase(BETWEEN)) {
				isBetween = true;
				aStatement.append(token);
			} else if (DELIMITERS_COND.indexOf(token) == -1) {
				// Field name or field value found.
				if (colName == null) {
					colName = token;
					aStatement.append(token);
				} else {
					int begin = token.indexOf('\'');
					int end = token.lastIndexOf('\'');
					if (begin == end) {
						aStatement.append(token);
					} else {
						aStatement.append(colName, token.substring(begin + 1, end), aColumns);
					}
				}
			} else {
				// Any other token.
				aStatement.append(token);
			}
		}
	}

	public static void parseDocIdInCondition(StringTokenizer aTokenizer, SQLStatement aStatement) {
		// Analyse tokens until an IFN or a NULL is found.
		String operator = "";
		String ifn = null;
		String token;
		for (;;) {
			token = aTokenizer.nextToken();

			if (token.equals("=") || token.equals("<") || token.equals(">")) {
				// Found part of = or <> operator.
				operator += token;
			} else if (token.equalsIgnoreCase(IS)) {
				// Found part of is null or is not null operator.
				operator = IS;
			} else if (token.equalsIgnoreCase(NOT)) {
				// Found part of is not null operator.
				operator += " " + NOT;
			} else if (token.equalsIgnoreCase(NULL)) {
				// Found part of is null or is not null operator.
				operator += " " + NULL;
				break;
			} else if (DELIMITERS_COND.indexOf(token) == -1) {
				// Found SSN/DS-ID
				ifn = token;
				break;
			}
		}

		// Add IFNDS and IFNID to where clause.
		if (ifn != null) {
			DocId docID = new DocId(ifn);
			aStatement.append("(" + IFNDS + " " + operator + " " + docID.getDiskset() + " " + AND + " " + IFNID + " "
					+ operator + " " + docID.getID() + ")");
		} else {
			aStatement.append("(" + IFNDS + " " + operator + " " + AND + " " + IFNID + " " + operator + ")");
		}
	}

	public static String checkAccessPermission(String aMethodName, IdmRepImpl aRepo, String aAppName, int aDocType)
			throws TTException {
		DoctypeSecurity docTypeSec = new DoctypeSecurity(aRepo);
		String dtWhereClause = docTypeSec.getDoctypeSQLClause(aAppName, aDocType);
		if (dtWhereClause.equalsIgnoreCase(NO_SECURITY)) {
			dtWhereClause = "";
		}
		return dtWhereClause;
	}

	public static AppList transformAppList(ApplicationEntry[] apps, String defaultApp) {
		AppList appList = new AppList();

		appList.setDefaultApp(defaultApp);
		for (int i = 0; i < apps.length; i++) {
			AppDef appDef = new AppDef();
			appDef.setDescription(apps[i].getLongName());
			appDef.setDiskset(apps[i].getDiskset());
			appDef.setFlags(apps[i].getFlags());
			appDef.setName(apps[i].getShortName());

			appList.getAppDefs().add(appDef);
		}
		return appList;
	}

	public static TemplateDef transformTemplateDef(ColumnDef aAppColumns,
			com.vignette.idm.common.NameValue[][] aFtblRows, ArrayList<String> aDocTypes) {
		// Find the IOTYPE field from the result.
		int ioTypePos = 0;
		for (int j = 0; j < aFtblRows[0].length; j++) {
			if (aFtblRows[0][j].getName().equalsIgnoreCase(IOTYPE)) {
				ioTypePos = j;
				break;
			}
		}

		// Perform the conversion.
		ArrayList<DisplayFieldDef> displayFields = transformDisplayFields(aAppColumns, aFtblRows, ioTypePos);
		ArrayList<EntryFieldDef> entryFields = transformEntryFields(aAppColumns, aFtblRows, ioTypePos, aDocTypes);

		TemplateDef templateDef = new TemplateDef();
		templateDef.getDisplayFields().addAll(displayFields);
		templateDef.getEntryFields().addAll(entryFields);

		return templateDef;
	}

	private static ArrayList<DisplayFieldDef> transformDisplayFields(ColumnDef aAppColumns,
			com.vignette.idm.common.NameValue[][] aFtblRows, int aIoTypePos) {
		ArrayList<DisplayFieldDef> displayFieldList = new ArrayList<DisplayFieldDef>();
		for (int i = 0; i < aFtblRows.length; i++) {
			if (aFtblRows[i][aIoTypePos].getValue().equals("1") == false) {
				continue;
			}
			DisplayFieldDef displayField = new DisplayFieldDef();
			for (int j = 0; j < aFtblRows[i].length; j++) {
				String colName = aFtblRows[i][j].getName();
				String colValue = aFtblRows[i][j].getValue();
				if (colName.equalsIgnoreCase(COL)) {
					displayField.setColPos(Converter.parseInt(colValue, 0));
				} else if (colName.equalsIgnoreCase(FROW) || colName.equalsIgnoreCase(ROW)) {
					displayField.setRowPos(Converter.parseInt(colValue, 0));
				} else if (colName.equalsIgnoreCase(LEN)) {
					displayField.setDisplayLength(Converter.parseInt(colValue, 0));
				} else if (colName.equalsIgnoreCase(FNUM)) {
					ColAttr colAttr = (ColAttr) aAppColumns.getColumnList().get(Converter.parseInt(colValue, 0));
					displayField.setAppFieldName(colAttr.getName());
				} else if (colName.equalsIgnoreCase(FDATA)) {
					displayField.setDisplayText(colValue);
				}
			}
			displayFieldList.add(displayField);
		}
		return displayFieldList;
	}

	private static ArrayList<EntryFieldDef> transformEntryFields(ColumnDef aAppColumns,
			com.vignette.idm.common.NameValue[][] aFtblRows, int aIoTypePos, ArrayList<String> aDocTypes) {
		ArrayList<EntryFieldDef> entryFieldList = new ArrayList<EntryFieldDef>();
		for (int i = 0; i < aFtblRows.length; i++) {
			if (aFtblRows[i][aIoTypePos].getValue().equals("0") == false) {
				continue;
			}
			EntryFieldDef entryField = new EntryFieldDef();
			for (int j = 0; j < aFtblRows[i].length; j++) {
				String colName = aFtblRows[i][j].getName();
				String colValue = aFtblRows[i][j].getValue();
				if (colName.equalsIgnoreCase(COL)) {
					entryField.setColPos(Converter.parseInt(colValue, 0));
				} else if (colName.equalsIgnoreCase(FROW) || colName.equalsIgnoreCase(ROW)) {
					entryField.setRowPos(Converter.parseInt(colValue, 0));
				} else if (colName.equalsIgnoreCase(LEN)) {
					entryField.setDisplayLength(Converter.parseInt(colValue, 0));
				} else if (colName.equalsIgnoreCase(FNUM)) {
					ColAttr colAttr = (ColAttr) aAppColumns.getColumnList().get(Converter.parseInt(colValue, 0));
					entryField.setAppFieldName(colAttr.getName());
				} else if (colName.equalsIgnoreCase(FTYPE)) {
					entryField.setDataType(transformDataType2(Converter.parseInt(colValue, 0)));
				} else if (colName.equalsIgnoreCase(VALIDFN)) {
					entryField.setValidationFunction(colValue);
				} else if (colName.equalsIgnoreCase(CONV)) {
					entryField.setConversionFunction(transformConvFunc(Converter.parseInt(colValue, 0)));
				} else if (colName.equalsIgnoreCase(PIC)) {
					entryField.setEntryType(transformEntryType(colValue));
					entryField.getValueList().addAll(transformValueList(entryField.getEntryType(), colValue));
				} else if (colName.equalsIgnoreCase(DFLTVAL)) {
					entryField.setDefaultValue(colValue);
				}
			}
			if (entryField.getConversionFunction().equals(ConversionFunction.CONVERT_DOCTYPE_TO_SLEVEL)) {
				entryField.setEntryType(EntryType.DOCTYPE_TO_SLEVEL);
				entryField.getValueList().addAll(aDocTypes);
				entryField.setDefaultValue("");
			}
			entryFieldList.add(entryField);
		}
		return entryFieldList;
	}

	private static AppDataType transformDataType2(int aFtype) {
		switch (aFtype) {
		case F_INT16:
		case F_UINT16:
			return AppDataType.SMALLINT;

		case F_INT32:
		case F_UINT32:
		case F_RECCOL:
			return AppDataType.INTEGER;

		case F_DATE:
			return AppDataType.DATE;

		case F_TIME:
			return AppDataType.TIME;

		default:
			return AppDataType.CHAR;
		}
	}

	private static ConversionFunction transformConvFunc(int aConv) {
		switch (aConv) {
		case 1:
			return ConversionFunction.CONVERT_DEFAULT_DATE;

		case 4:
			return ConversionFunction.CONVERT_AUTO_INCREMENT;

		case 14:
			return ConversionFunction.CONVERT_USER_ID;

		case 15:
			return ConversionFunction.CONVERT_FULL_USER_NAME;

		case 27:
			return ConversionFunction.CONVERT_DOCTYPE_TO_SLEVEL;

		case 28:
			return ConversionFunction.CONVERT_DEFAULT_AS_ENVIRONMENT_VAR;

		default:
			return ConversionFunction.CONVERT_NO_CONVERSION;
		}
	}

	private static EntryType transformEntryType(String aPic) {
		int entryType = 0;
		if (aPic != null) {
			entryType = Converter.parseInt(aPic.substring(0, 2), 0);
		}

		switch (entryType) {
		case 1:
			if (aPic.charAt(5) != '/') {
				return EntryType.DROPDOWN_LIST;
			} else {
				return EntryType.DROPDOWN_COMBO;
			}

		case 2:
			return EntryType.CHECK_BOX;

		case 3:
			return EntryType.DATE_PICKER;

		default:
			return EntryType.STD_EDIT_BOX;
		}
	}

	public static boolean getFlagValue(IdmRepImpl aRepo, String aFlagName) throws TTException {
		Misc misc = new Misc(aRepo);
		return Boolean.valueOf(misc.getConfigVar(aFlagName)).booleanValue();
	}

	public static SearchResult transformSearchResult(com.vignette.idm.common.NameValue[][] aResult, int aMaxRecords) {
		ArrayList<String> fieldNamesList = new ArrayList<String>();
		ArrayList<ResultRow> valuesList = new ArrayList<ResultRow>();
		boolean hasMoreRecords = false;

		if (aResult != null && aResult.length != 0 && aResult[0] != null) {
			// Obtain the field names from the first row with the assumption
			// that all rows will contain the same columns.
			for (int j = 0; j < aResult[0].length; j++) {
				if (aResult[0][j].getName().equalsIgnoreCase(IFNDS) && (j + 1) < aResult[0].length
						&& aResult[0][j + 1].getName().equalsIgnoreCase(IFNID)) {
					fieldNamesList.add(HASH_DOCID);
					j++;
				} else if (aResult[0][j].getName().equalsIgnoreCase(TOWID)) {
					fieldNamesList.add(RECID);
				} else {
					fieldNamesList.add(aResult[0][j].getName());
				}
			}

			// Obtain column values for each row.
			for (int i = 0; i < aResult.length; i++) {
				if (i >= aMaxRecords) {
					hasMoreRecords = true;
					break;
				}
				ArrayList<String> colList = new ArrayList<String>();
				if (aResult[i] != null) {
					for (int j = 0; j < aResult[i].length; j++) {
						if (aResult[i][j].getName().equalsIgnoreCase(IFNDS) && (j + 1) < aResult[i].length
								&& aResult[i][j + 1].getName().equalsIgnoreCase(IFNID)) {
							DocId ifn = new DocId(aResult[i][j].getValue(), aResult[i][j + 1].getValue());
							colList.add(ifn.toString());
							j++;
						} else {
							colList.add(aResult[i][j].getValue());
						}
					}
				}

				ResultRow row = new ResultRow();
				row.getFieldValues().addAll(colList);
				row.setDotNetWa(null);

				valuesList.add(row);
			}
		}

		// Return the converted search results.
		SearchResult srchResult = new SearchResult();

		srchResult.setHasMoreRecords(hasMoreRecords);
		srchResult.getFieldNames().addAll(fieldNamesList);

		ArrayOfResultRow resultRows = new ArrayOfResultRow();
		resultRows.getValue().addAll(valuesList);
		srchResult.setRows(resultRows);

		return srchResult;
	}

	public static String validateOrderBy(String aMethodName, String aOrderBy, boolean aDocIdAllowed,
			boolean aRecIdAllowed) throws InvalidParameterException {
		// Perform general string validation.
		String orderBy = Validator.validateString(SERVICENAME, aMethodName, ORDERBY, aOrderBy, true, false);

		// Validate the content of the condition string.
		if (orderBy != null && orderBy.length() != 0) {
			StringTokenizer tokenizer = new StringTokenizer(aOrderBy, DELIMITERS_ORDER, true);
			String token;
			while (tokenizer.hasMoreTokens()) {
				token = tokenizer.nextToken();

				if (aDocIdAllowed && token.equalsIgnoreCase(HASH_DOCID)) {
					;
				} else if (aRecIdAllowed && token.equalsIgnoreCase(RECID)) {
					;
				} else if (token.startsWith("#")) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(ORDERBY);
					faultDetail.setParameterValue(aOrderBy);
					faultDetail.setWsMethodName(aMethodName);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}
			}
		}
		return orderBy;
	}

	public static void validateFieldNames(String aMethodName, List<String> aFieldNames)
			throws InvalidParameterException {
		// Perform general string array validation.
		Validator.validateStringList(SERVICENAME, aMethodName, FIELDNAMES, aFieldNames, false, false, false, false);

		// Check individual fields.
		ListIterator<String> itor = aFieldNames.listIterator();
		while (itor.hasNext()) {
			String aFieldName = (String) itor.next();

			// Check that there is only one field when '*' is specified.
			if (aFieldName.equals("*") && aFieldNames.size() > 1) {
				InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
				faultDetail.setCallStack(null);
				faultDetail.setMessage(null);
				faultDetail.setParameterName(FIELDNAMES + "[" + itor.previousIndex() + "]");
				faultDetail.setParameterValue(aFieldName);
				faultDetail.setWsMethodName(aMethodName);
				faultDetail.setWsName(SERVICENAME);
				throw new InvalidParameterException(null, faultDetail);
			}
			// Check for unrecognised special field names.
			else if (aFieldName.startsWith("#") && aFieldName.equalsIgnoreCase(HASH_DOCID) == false
					&& aFieldName.equalsIgnoreCase(RECID) == false) {
				InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
				faultDetail.setCallStack(null);
				faultDetail.setMessage(null);
				faultDetail.setParameterName(FIELDNAMES + "[" + itor.previousIndex() + "]");
				faultDetail.setParameterValue(aFieldName);
				faultDetail.setWsMethodName(aMethodName);
				faultDetail.setWsName(SERVICENAME);
				throw new InvalidParameterException(null, faultDetail);
			}
		}
	}

	public static List<String> transformValueList(EntryType aEntryType, String aPic) {
		ArrayList<String> valList = new ArrayList<String>();
		String values = null;
		if (aEntryType.compareTo(EntryType.DROPDOWN_COMBO) == 0) {
			values = aPic.substring(6);
		} else if (aEntryType.compareTo(EntryType.DROPDOWN_LIST) == 0) {
			values = aPic.substring(5);
		}

		if (values != null) {
			try {
				StringTokenizer tokens = new StringTokenizer(values, "/~");
				String token;
				while (tokens.hasMoreTokens()) {
					token = tokens.nextToken().trim();
					if (token.length() != 0) {
						valList.add(token);
					}
				}
				return valList;
			} catch (Exception e) {
				return valList;
			}
		} else {
			return valList;
		}
	}

	public static ArrayList<AppFieldDef> transformFieldDefs(ColumnDef columns) {
		ArrayList<ColAttr> colsIn = columns.getColumnList();
		ArrayList<AppFieldDef> colsOut = new ArrayList<AppFieldDef>();
		for (int i = 0; i < colsIn.size(); i++) {
			AppFieldDef fieldDef;
			ColAttr col = colsIn.get(i);
			if (col.getName().equalsIgnoreCase(IFNDS)) {
				// Replace IFNDS with #DOCID#.
				fieldDef = new AppFieldDef();
				fieldDef.setDataType(transformDataType(ColAttr.SQL_VARCHAR));
				fieldDef.setDescription(HASH_DOCID_DESC);
				fieldDef.setLength(30);
				fieldDef.setName(HASH_DOCID);
				fieldDef.setPrecision(Defaults.FieldPrecision);
			} else if (col.getName().equalsIgnoreCase(IFNID)) {
				// Ignore IFNID.
				continue;
			} else if (col.getName().equalsIgnoreCase(TOWID)) {
				// Replace TOWID with #RECID#.
				fieldDef = new AppFieldDef();
				fieldDef.setDataType(transformDataType(col.getFieldType()));
				fieldDef.setDescription(col.getDescription());
				fieldDef.setLength(col.getLength());
				fieldDef.setName(RECID);
				fieldDef.setPrecision(col.getPrecision());
			} else {
				// Other columns.
				fieldDef = new AppFieldDef();
				fieldDef.setDataType(transformDataType(col.getFieldType()));
				fieldDef.setDescription(col.getDescription());
				fieldDef.setLength(col.getLength());
				fieldDef.setName(col.getName());
				fieldDef.setPrecision(col.getPrecision());
			}
			colsOut.add(fieldDef);
		}
		return colsOut;
	}

	public static AppDataType transformDataType(int dataType) {
		switch (dataType) {
		case ColAttr.SQL_NUMERIC:
		case ColAttr.SQL_DECIMAL:
		case ColAttr.SQL_INTEGER:
		case ColAttr.SQL_SMALLINT:
			return AppDataType.INTEGER;

		case ColAttr.SQL_CHAR:
		case ColAttr.SQL_VARCHAR:
			return AppDataType.CHAR;

		case ColAttr.SQL_DATE:
		case ColAttr.SQL_TIMESTAMP:
		case ColAttr.SQL_TYPE_DATE:
		case ColAttr.SQL_TYPE_TIMESTAMP:
			return AppDataType.DATE;

		case ColAttr.SQL_TIME:
		case ColAttr.SQL_TYPE_TIME:
			return AppDataType.TIME;

		default:
			return AppDataType.CHAR;
		}
	}

	public static com.vignette.idm.common.NameValue[] transformFields(List<NameValue> aFields) {
		com.vignette.idm.common.NameValue[] fields;
		if (aFields != null) {
			fields = new com.vignette.idm.common.NameValue[aFields.size()];
			for (int i = 0; i < aFields.size(); i++) {
				NameValue nv = aFields.get(i);
				fields[i] = new com.vignette.idm.common.NameValue();
				fields[i].setName(nv.getName());
				fields[i].setValue(nv.getValue());
			}
		} else {
			fields = new com.vignette.idm.common.NameValue[0];
		}
		return fields;
	}

	public static void validateContentOptions(String aMethodName, List<NameValue> aOptions, boolean aOnUpdate,
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

	public static SQLStatement constructUpdate(String aTableName, String aNewDocID, int aNewNumPages,
			List<NameValue> aFields, String aOldDocID, String aCondition, String aDtWhereClause, ColumnDef aColumns) {
		SQLStatement statement = new SQLStatement(UPDATE + " " + aTableName);
		constructSetList(statement, aNewDocID, aNewNumPages, aFields, aColumns);
		AppServiceUtils.constructWhereClause(statement, aOldDocID, aCondition, aDtWhereClause, aColumns);
		return statement;
	}

	public static void validateGetTemplateOptions(String aMethodName, List<NameValue> aOptions)
			throws InvalidParameterException, UnknownOptionNameException, UnknownOptionValueException {
		// Perform general name/value pair validation.
		mDoctypePriv = VIEW;
		Validator.validateNameValueList(SERVICENAME, aMethodName, OPTIONS, aOptions, true, true, false, false);

		// Perform specific option validation.
		if (aOptions != null && aOptions.size() != 0) {
			for (int i = 0; i < aOptions.size(); i++) {
				String optionName = aOptions.get(i).getName();
				String optionValue = aOptions.get(i).getValue();

				// Check for valid values of DoctypePriv option.
				if (optionName.equals(DOCTYPEPRIV)) {
					if (optionValue.equals(VIEW) == false && optionValue.equals(MODIFY) == false
							&& optionValue.equals(DELETE) == false && optionValue.equals(CREATE) == false) {
						UnknownOptionValueExceptionFaultDetail faultDetail = new UnknownOptionValueExceptionFaultDetail();
						faultDetail.setCallStack(null);
						faultDetail.setMessage(null);
						faultDetail.setOptionName(optionName);
						faultDetail.setOptionValue(optionValue);
						faultDetail.setWsMethodName(aMethodName);
						faultDetail.setWsName(SERVICENAME);
						throw new UnknownOptionValueException(null, faultDetail);
					}
					mDoctypePriv = optionValue;
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

	public static byte[] getByteArrayFromDataHandler(DataHandler dHandler) throws IOException {
		InputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		byte[] byteData = null;

		try {
			DataSource dataSource = (DataSource) dHandler.getDataSource();
			inputStream = dataSource.getInputStream();
			// put 8*1024 as a configuration variable
			outputStream = new ByteArrayOutputStream(8 * 1024);
			byte dsData[] = new byte[8 * 1024];
			int bytes_read;

			while ((bytes_read = inputStream.read(dsData)) > 0) {
				outputStream.write(dsData, 0, bytes_read);
			}

			byteData = outputStream.toByteArray();
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}

			if (outputStream != null) {
				outputStream.close();
			}
		}

		return byteData;
	}

	public static SQLStatement constructDelete(String aTableName, String aCondition, String aDtWhereClause,
			ColumnDef aColumns) {
		System.out.println("table Name" + aTableName);
		SQLStatement statement = new SQLStatement(DELETE_FROM + " " + aTableName);
		constructWhereClause(statement, null, aCondition, aDtWhereClause, aColumns);
		return statement;
	}

	public static String validateDocID(String aMethodName, String aDocID) throws InvalidParameterException {
		// Perform general string validation.
		String docID = Validator.validateString(SERVICENAME, aMethodName, DOCID, aDocID, false, false);

		// Perform document identifier specific validation.
		DocId ifn = new DocId(docID);
		if (ifn.toString().length() == 0) {
			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setParameterName(DOCID);
			faultDetail.setParameterValue(aDocID);
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(SERVICENAME);
			throw new InvalidParameterException(null, faultDetail);
		}
		return docID;
	}

	public static void constructSetList(SQLStatement aStatement, String aDocID, int aNumPages, List<NameValue> aFields,
			ColumnDef aColumns) {
		aStatement.append(" " + SET + " ");
		boolean addComma = false;
		if (aDocID != null) {
			DocId ifn = new DocId(aDocID);
			aStatement.append(IFNDS + " = " + ifn.getDiskset() + ", " + IFNID + " = " + ifn.getID() + ", " + NPAGES
					+ " = " + aNumPages);
			addComma = true;
		}
		if (aFields != null) {
			ListIterator<NameValue> itor = aFields.listIterator();
			while (itor.hasNext()) {
				NameValue fld = itor.next();
				if (addComma) {
					aStatement.append(", ");
				}
				String fieldName = fld.getName();
				String fieldValue = fld.getValue();
				if (fieldName.equalsIgnoreCase(HASH_DOCID)) {
					if (fieldValue != null) {
						DocId ifn = new DocId(fieldValue);
						aStatement.append(IFNDS + " = " + ifn.getDiskset() + ", " + IFNID + " = " + ifn.getID());
					} else {
						aStatement.append(IFNDS + " = null, " + IFNID + " = null");
					}
				} else {
					aStatement.append(fieldName + " = ");
					aStatement.append(fieldName, fieldValue, aColumns);
				}
				addComma = true;
			}
		}
	}

	public static void checkInsertPermission(String aMethodName, List<NameValue> aFields, IdmRepImpl aRepo,
			String aAppName) throws PermissionException, TTException {
		// Determine the slevel.
		int slevel = 0;
		if (aFields != null) {
			ListIterator<NameValue> itor = aFields.listIterator();
			while (itor.hasNext()) {
				NameValue fld = itor.next();
				if (fld.getName().equalsIgnoreCase(SLEVEL)) {
					slevel = Converter.parseInt(fld.getValue(), 0);
				}
			}
		}

		// Perform the permission check.
		DoctypeSecurity docTypeSec = new DoctypeSecurity(aRepo);
		boolean permission = docTypeSec.validateIfnDoctypeAccess(CREATE_ACTION, aAppName, Integer.toString(slevel));
		if (permission == false) {
			PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
			faultDetail.setAppName(aAppName);
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setUserName(aRepo.getUserId());
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(SERVICENAME);
			throw new PermissionException(null, faultDetail);
		}
	}

	public static SQLStatement constructInsert(String aAppName, String aDocID, int aNumPages, List<NameValue> aFields,
			ColumnDef aColumns) {
		SQLStatement statement = new SQLStatement(INSERT_INTO + " " + aAppName + " (");
		constructInsertFieldList(statement, aDocID, aFields);
		statement.append(") " + VALUES + " (");
		constructValueList(statement, aDocID, aNumPages, aFields, aColumns);
		statement.append(")");
		return statement;
	}

	public static void constructInsertFieldList(SQLStatement aStatement, String aDocID, List<NameValue> aFields) {
		// DocID is not null when called from insertContent().
		boolean addComma = false;
		if (aDocID != null) {
			aStatement.append(IFNDS + ", " + IFNID + ", " + NPAGES);
			addComma = true;
		}

		// Add specified fields to the field list.
		if (aFields != null) {
			ListIterator<NameValue> itor = aFields.listIterator();
			while (itor.hasNext()) {
				NameValue fld = itor.next();
				if (addComma) {
					aStatement.append(", ");
				}
				if (fld.getName().equalsIgnoreCase(HASH_DOCID)) {
					aStatement.append(IFNDS + ", " + IFNID);
				} else {
					aStatement.append(fld.getName());
				}
				addComma = true;
			}
		}
	}

	public static void constructValueList(SQLStatement aStatement, String aDocID, int aNumPages,
			List<NameValue> aFields, ColumnDef aColumns) {
		// DocID is not null when called from insertContent().
		boolean addComma = false;
		if (aDocID != null) {
			DocId ifn = new DocId(aDocID);
			aStatement.append(ifn.getDiskset() + ", " + ifn.getID() + ", " + aNumPages);
			addComma = true;
		}

		// Add specified fields to the field list.
		if (aFields != null) {
			ListIterator<NameValue> itor = aFields.listIterator();
			while (itor.hasNext()) {
				NameValue fld = itor.next();
				if (addComma) {
					aStatement.append(", ");
				}
				if (fld.getName().equalsIgnoreCase(HASH_DOCID)) {
					if (fld.getValue() != null) {
						DocId ifn = new DocId(fld.getValue());
						aStatement.append(ifn.getDiskset() + ", " + ifn.getID());
					} else {
						aStatement.append("null, null");
					}
				} else {
					aStatement.append(fld.getName(), fld.getValue(), aColumns);
				}
				addComma = true;
			}
		}
	}

	public static void validateFields(String aMethodName, List<NameValue> aFields, boolean aHasContent)
			throws InvalidParameterException {
		// Perform general name/value pair validation.
		Validator.validateNameValueList(SERVICENAME, aMethodName, FIELDS, aFields, aHasContent, aHasContent, true,
				true);

		// Check individual fields.
		if (aFields != null && aFields.size() != 0) {
			for (int i = 0; i < aFields.size(); i++) {
				String fieldName = aFields.get(i).getName();
				String fieldValue = aFields.get(i).getValue();

				// Check if special field names are allowed.
				if (aHasContent && (fieldName.equalsIgnoreCase(HASH_DOCID) || fieldName.equalsIgnoreCase(IFNDS)
						|| fieldName.equalsIgnoreCase(IFNID) || fieldName.equalsIgnoreCase(NPAGES))) {
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(FIELDS + "[" + i + "]");
					faultDetail.setParameterValue(fieldName);
					faultDetail.setWsMethodName(aMethodName);
					faultDetail.setWsName(SERVICENAME);
					throw new InvalidParameterException(null, faultDetail);
				}

				// Check for unrecognised field names.
				if (fieldName.startsWith("#")) {
					if (fieldName.equalsIgnoreCase(HASH_DOCID)) {
						DocId ifn = new DocId(fieldValue);
						if (ifn.toString().length() == 0) {
							InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
							faultDetail.setCallStack(null);
							faultDetail.setMessage(null);
							faultDetail.setParameterName(FIELDS + "[" + i + "]");
							faultDetail.setParameterValue(fieldName);
							faultDetail.setWsMethodName(aMethodName);
							faultDetail.setWsName(SERVICENAME);
							throw new InvalidParameterException(null, faultDetail);
						}
					} else {
						InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
						faultDetail.setCallStack(null);
						faultDetail.setMessage(null);
						faultDetail.setParameterName(FIELDS + "[" + i + "]");
						faultDetail.setParameterValue(fieldName);
						faultDetail.setWsMethodName(aMethodName);
						faultDetail.setWsName(SERVICENAME);
						throw new InvalidParameterException(null, faultDetail);
					}
				}
			}
		}
	}

}
