package com.opentext.rest.utils;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import javax.annotation.Resource;
import javax.jws.HandlerChain;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceContext;

import com.towertechnology.common.errorhandling.ITTErrorDef;
import com.towertechnology.common.errorhandling.TTCommonErrors;
import com.towertechnology.common.errorhandling.TTException;
import com.towertechnology.common.errorhandling.TTRepositoryErrors;

import com.vignette.idm.common.Image;
import com.vignette.idm.common.NameValue;
import com.vignette.idm.services.ColumnDef;
import com.vignette.idm.services.DocId;
import com.vignette.idm.services.IdmRepImpl;
import com.vignette.idm.services.Library;
import com.vignette.idm.services.RecordCollection;
import com.vignette.idm.services.SQLStatement;

import com.vignette.idm.server.common.Converter;
import com.vignette.idm.server.common.ExceptionMessagesFormatter;
import com.vignette.idm.server.common.LicenceFactory;
import com.vignette.idm.server.common.StringBufferWS;
import com.vignette.idm.server.common.Validator;
import com.vignette.idm.server.common.olf.FormatterException;

import com.vignette.idm.server.ArrayOfResultRow;
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
import com.vignette.idm.server.ResultRow;
import com.vignette.idm.server.SearchResult;
import com.vignette.idm.server.UnknownDocIDException;
import com.vignette.idm.server.UnknownDocIDExceptionFaultDetail;
import com.vignette.idm.server.UnknownOptionNameException;
import com.vignette.idm.server.UnknownOptionNameExceptionFaultDetail;
import com.vignette.idm.server.UnknownOptionValueException;
import com.vignette.idm.server.UnknownOptionValueExceptionFaultDetail;

public class LibServiceUtils {
	
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
			.getLogger("com.vignette.idm.server.impl.LibServicesImpl");

	////////////////////////////////////////////////////////////////////////////
	// Instance Member Variables
	////////////////////////////////////////////////////////////////////////////
	private LicenceFactory mLF;
	private static ExceptionMessagesFormatter mFormatter;
	private static String mDiskset;
	private static String mPool;
	
	////////////////////////////////////////////////////////////////////////////
	//	Private functions
	////////////////////////////////////////////////////////////////////////////

	public static String format(GeneralExceptionFaultDetail e)
	{
		try
		{
			return mFormatter.format(e);
		}
		catch (FormatterException fe)
		{
			return e.getMessage();
		}
	}

	public static void validateFields
	(
		String								aMethodName,
		List<com.vignette.idm.server.NameValue>	aFields
	) throws InvalidParameterException
	{
		// Perform general name/value pair validation.
		Validator.validateNameValueList
		(
			SERVICENAME,
			aMethodName,
			FIELDS,
			aFields,
			true,
			true,
			true,
			true
		);

		// Check individual fields.
		if (aFields != null)
		{
			ListIterator<com.vignette.idm.server.NameValue> itor = aFields.listIterator();
			while (itor.hasNext())
			{
				com.vignette.idm.server.NameValue fld = itor.next();
				String fieldName = fld.getName();

				// Check if special field names are allowed.
				if
				(
					fieldName.equalsIgnoreCase(HASH_DOCID)
					||
					fieldName.equalsIgnoreCase(RECID)
					||
					fieldName.equalsIgnoreCase(IFNDS)
					||
					fieldName.equalsIgnoreCase(IFNID)
					||
					fieldName.equalsIgnoreCase(NPAGES)
					||
					fieldName.equalsIgnoreCase(DMID)
					||
					fieldName.equalsIgnoreCase(DMVNUM)
					||
					fieldName.equalsIgnoreCase(DMRNUM)
					||
					fieldName.equalsIgnoreCase(DMREVISE)
					||
					fieldName.equalsIgnoreCase(DMCO)
					||
					fieldName.equalsIgnoreCase(DMCOUSER)
					||
					fieldName.equalsIgnoreCase(DMCODATE)
					||
					fieldName.equalsIgnoreCase(DMCOTIME)
					||
					fieldName.equalsIgnoreCase(DMSTATUS)
				)
				{
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(FIELDS + "[" + itor.previousIndex() + "]");
					faultDetail.setParameterValue(fieldName);
					faultDetail.setWsMethodName(aMethodName);
					faultDetail.setWsName(SERVICENAME);					
					throw new InvalidParameterException(null, faultDetail);
				}
			}
		}
	}

	public static void validateOptions
	(
		String								aMethodName,
		List<com.vignette.idm.server.NameValue>	aOptions
	) throws
		InvalidParameterException,
		UnknownOptionNameException,
		UnknownOptionValueException
	{
		// Perform general name/value pair validation.
		Validator.validateNameValueList
		(
			SERVICENAME,
			aMethodName,
			OPTIONS,
			aOptions,
			true,
			true,
			false,
			false
		);

		// Perform specific option validation.
		mDiskset = "";
		mPool = OUTPUT_POOL;
		if (aOptions != null)
		{
			ListIterator<com.vignette.idm.server.NameValue> itor = aOptions.listIterator();
			while (itor.hasNext())
			{
				com.vignette.idm.server.NameValue opt = itor.next();
				String	optionName = opt.getName();
				String	optionValue = opt.getValue();

				if (optionName.equals(DISKSET))
				{
					DocId diskset = new DocId(optionValue + "-0");
					if (diskset.toString().length() == 0)
					{
						InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
						faultDetail.setCallStack(null);
						faultDetail.setMessage(null);
						faultDetail.setParameterName(optionName);
						faultDetail.setParameterValue(optionValue);
						faultDetail.setWsMethodName(aMethodName);
						faultDetail.setWsName(SERVICENAME);					
						throw new InvalidParameterException(null, faultDetail);
					}
					mDiskset = optionValue;
				}
				// Check for valid values of Pool option.
				else if (optionName.equals(POOL))
				{
					if
					(
						optionValue.equals(SCAN_POOL) == false
						&&
						optionValue.equals(PRINT_POOL) == false
						&&
						optionValue.equals(HOLDING_POOL) == false
						&&
						optionValue.equals(COPYIN_POOL) == false
						&&
						optionValue.equals(COPYOUT_POOL) == false
						&&
						optionValue.equals(OUTPUT_POOL) == false
					)
					{
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
				else
				{
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

	public static NameValue[] transformFields
	(
		List<com.vignette.idm.server.NameValue>	aFields
	)
	{
		if (aFields != null)
		{
			int i = 0;
			NameValue[] fields = new NameValue[aFields.size()];
			ListIterator<com.vignette.idm.server.NameValue> itor = aFields.listIterator();
			while (itor.hasNext())
			{
				com.vignette.idm.server.NameValue fld = itor.next();
				fields[i] = new NameValue();
				fields[i].setName(fld.getName());
				fields[i].setValue(fld.getValue());
				i++;
			}
			return fields;
		}
		else
		{
			return new NameValue[0];
		}
	}

	public static void validateNoOptions
	(
		String								aMethodName,
		List<com.vignette.idm.server.NameValue>	aOptions
	) throws
		InvalidParameterException,
		UnknownOptionNameException,
		UnknownOptionValueException
	{
		// Perform general name/value pair validation.
		Validator.validateNameValueList
		(
			SERVICENAME,
			aMethodName,
			OPTIONS,
			aOptions,
			true,
			true,
			false,
			false
		);

		// Perform specific option validation.
		if (aOptions != null)
		{
			ListIterator<com.vignette.idm.server.NameValue> itor = aOptions.listIterator();
			while (itor.hasNext())
			{
				String	optionName = itor.next().getName();

				// Not a valid option.
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

	public static ArrayList<String> transformDocIDs(NameValue[][] aRows)
	{
		ArrayList<String> docIDs = new ArrayList<String>(aRows.length);
		for (int i = 0; i < aRows.length; i++)
		{
			DocId ifn =
				new DocId(aRows[i][0].getValue(), aRows[i][1].getValue());
			docIDs.add(ifn.toString());
		}
		return docIDs;
	}

	public static void validateFieldNames
	(
		String		aMethodName,
		List<String>	aFieldNames
	) throws InvalidParameterException
	{
		// Perform general string array validation.
		Validator.validateStringList
		(
			SERVICENAME,
			aMethodName,
			FIELDNAMES,
			aFieldNames,
			false,
			false,
			false,
			false
		);

		// Check individual fields.
		ListIterator<String> itor = aFieldNames.listIterator();
		while (itor.hasNext())
		{			
			String fldName = itor.next();
			if 
			(
				// Check that there is only one field when '*' is specified.
				fldName.equals("*") && aFieldNames.size() > 1
				||
				// Check for unrecognised special field names.
				(
					fldName.startsWith("#")
					&&
					fldName.equalsIgnoreCase(HASH_DOCID) == false
				)
			)
			{
				InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
				faultDetail.setCallStack(null);
				faultDetail.setMessage(null);
				faultDetail.setParameterName(FIELDNAMES + "[" + itor.previousIndex() + "]");
				faultDetail.setParameterValue(fldName);
				faultDetail.setWsMethodName(aMethodName);
				faultDetail.setWsName(SERVICENAME);					
				throw new InvalidParameterException(null, faultDetail);
			}
		}
	}

	public static String validateCondition
	(
		String	aMethodName,
		String	aCondition
	) throws InvalidParameterException
	{
		// Perform general string validation.
		String condition = Validator.validateString
		(
			SERVICENAME,
			aMethodName,
			CONDITION,
			aCondition,
			true,
			false
		);

		// Validate the content of the condition string.
		if (condition != null && condition.length() != 0)
		{
			StringTokenizer tokenizer =
				new StringTokenizer(condition, DELIMITERS_COND, true);
			String token;
			while (tokenizer.hasMoreTokens())
			{
				token = tokenizer.nextToken();

				if (token.equalsIgnoreCase(HASH_DOCID))
				{
					validateDocIdInCondition
					(
						aMethodName,
						tokenizer,
						aCondition
					);
				}
				else if
				(
					token.startsWith("#")
					||
					token.equalsIgnoreCase(DMID)
				)
				{
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

	public static void validateDocIdInCondition
	(
		String			aMethodName,
		StringTokenizer	aTokenizer,
		String			aCondition
	) throws InvalidParameterException
	{
		// Check if a #DOCID# term is complete.
		String operator = "";
		String token;
		for (;;)
		{
			if (aTokenizer.hasMoreTokens() == false)
			{
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

			if (token.equals(" "))
			{
				continue;
			}
			else if (token.equalsIgnoreCase(IS))
			{
				operator = IS;
			}
			else if (token.equalsIgnoreCase(NOT))
			{
				operator += " " + NOT;
			}
			else if (token.equalsIgnoreCase(NULL))
			{
				operator += " " + NULL;
				break;
			}
			else if (DELIMITERS_COND.indexOf(token) == -1)
			{
				DocId ifn = new DocId(token);
				if (ifn.toString().length() == 0)
				{
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
			}
			else
			{
				operator += token;
			}
		}

		// Check for valid operators with #DOCID#.
		if
		(
			operator.equals(EQ)
			||
			operator.equals(NEQ)
			||
			operator.equalsIgnoreCase(ISNULL)
			||
			operator.equalsIgnoreCase(ISNOTNULL)
		)
		{
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

	public static String validateOrderBy
	(
		String	aMethodName,
		String	aOrderBy
	) throws InvalidParameterException
	{
		// Perform general string validation.
		String orderBy = Validator.validateString
		(
			SERVICENAME,
			aMethodName,
			ORDERBY,
			aOrderBy,
			true,
			false
		);

		// Validate the content of the condition string.
		if (orderBy != null && orderBy.length() != 0)
		{
			StringTokenizer tokenizer =
				new StringTokenizer(aOrderBy, DELIMITERS_ORDER, true);
			String token;
			while (tokenizer.hasMoreTokens())
			{
				token = tokenizer.nextToken();

				if (token.equalsIgnoreCase(HASH_DOCID))
				{
					;
				}
				else if (token.startsWith("#"))
				{
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

	public static String getDMID
	(
		RecordCollection	aCollection,
		String				aAppName,
		String				aDocID
	) throws TTException
	{
		DocId ifn = new DocId(aDocID);
		SQLStatement statement = new SQLStatement
		(
			SELECT + " " + DMID + " " + FROM + " " + aAppName + " " +
			WHERE + " " + IFNDS + " = " + ifn.getDiskset() + " " +
			AND + " " + IFNID + " = " + ifn.getID()
		);
		NameValue[][] rows = aCollection.executeQuery(statement, 0, 0);
		return rows[0][0].getValue();
	}

	public static SQLStatement constructSelect
	(
		String		aTableName,
		List<String>	aFields,
		String		aDMID,
		String		aCondition,
		String		aOrderBy,
		ColumnDef	aColumns
	)
	{
		SQLStatement statement = new SQLStatement(SELECT + " ");
		constructSelectFieldList(statement, aFields);
		statement.append(" " + FROM + " " + aTableName);
		constructWhereClause
		(
			statement,
			aDMID,
			aCondition,
			aColumns
		);
		constructOrderByClause(statement, aOrderBy);
		return statement;
	}

	public static void constructSelectFieldList
	(
		SQLStatement	aStatement,
		List<String>	aFields
	)
	{
		ListIterator<String> itor = aFields.listIterator();
		while (itor.hasNext())
		{
			String fld = itor.next();
			if (itor.previousIndex() > 0)
			{
				aStatement.append(", ");
			}
			if (fld.equalsIgnoreCase(HASH_DOCID))
			{
				aStatement.append(IFNDS + ", " + IFNID);
			}
			else
			{
				aStatement.append(fld);
			}
		}
	}

	public static void constructWhereClause
	(
		SQLStatement	aStatement,
		String			aDMID,
		String			aCondition,
		ColumnDef		aColumns
	)
	{
		// Add DMID at the front of the where clause.
		aStatement.append(" " + WHERE + " " + DMID + " = '" + aDMID + "'");

		// If condition is specified, add it to the where clause.
		if (aCondition != null && aCondition.length() != 0)
		{
			aStatement.append(" " + AND + " ");
			parseCondition(aStatement, aCondition, aColumns);
		}
	}

	public static void parseCondition
	(
		SQLStatement	aStatement,
		String			aCondition,
		ColumnDef		aColumns
	)
	{
		StringTokenizer tokenizer =
			new StringTokenizer(aCondition, DELIMITERS_COND, true);

		String colName = null;
		boolean isBetween = false;
		String token;
		while (tokenizer.hasMoreTokens())
		{
			token = tokenizer.nextToken();

			if (token.equalsIgnoreCase(HASH_DOCID))
			{
				parseDocIdInCondition(tokenizer, aStatement);
			}
			else if (token.equalsIgnoreCase(AND))
			{
				if (isBetween)
				{
					// AND used in a BETWEEN clause.
					isBetween = false;
				}
				else
				{
					// AND used to join clauses.
					colName = null;
				}
				aStatement.append(token);
			}
			else if (token.equalsIgnoreCase(OR))
			{
				colName = null;
				aStatement.append(token);
			}
			else if (token.equalsIgnoreCase(BETWEEN))
			{
				isBetween = true;
				aStatement.append(token);
			}
			else if (DELIMITERS_COND.indexOf(token) == -1)
			{
				// Field name or field value found.
				if (colName == null)
				{
					colName = token;
					aStatement.append(token);
				}
				else
				{
					int begin = token.indexOf('\'');
					int	end = token.lastIndexOf('\'');
					if (begin == end)
					{
						aStatement.append(token);
					}
					else
					{
						aStatement.append
						(
							colName,
							token.substring(begin + 1, end),
							aColumns
						);
					}
				}
			}
			else
			{
				// Any other token.
				aStatement.append(token);
			}
		}
	}

	public static void parseDocIdInCondition
	(
		StringTokenizer	aTokenizer,
		SQLStatement	aStatement
	)
	{
		// Analyse tokens until an IFN or a NULL is found.
		String operator = "";
		String ifn = null;
		String token;
		for (;;)
		{
			token = aTokenizer.nextToken();

			if (token.equals("=") || token.equals("<") || token.equals(">"))
			{
				// Found part of = or <> operator.
				operator += token;
			}
			else if (token.equalsIgnoreCase(IS))
			{
				// Found part of is null or is not null operator.
				operator = IS;
			}
			else if (token.equalsIgnoreCase(NOT))
			{
				// Found part of is not null operator.
				operator += " " + NOT;
			}
			else if (token.equalsIgnoreCase(NULL))
			{
				// Found part of is null or is not null operator.
				operator += " " + NULL;
				break;
			}
			else if (DELIMITERS_COND.indexOf(token) == -1)
			{
				// Found SSN/DS-ID
				ifn = token;
				break;
			}
		}

		// Add IFNDS and IFNID to where clause.
		if (ifn != null)
		{
			DocId docID = new DocId(ifn);
			aStatement.append
			(
				"(" + IFNDS + " " + operator + " " + docID.getDiskset() + " " +
				AND + " " + IFNID + " " + operator + " " + docID.getID() + ")"
			);
		}
		else
		{
			aStatement.append
			(
				"(" + IFNDS + " " + operator + " " +
				AND + " " + IFNID + " " + operator + ")"
			);
		}
	}

	public static void constructOrderByClause
	(
		SQLStatement	aStatement,
		String			aOrderBy
	)
	{
		if (aOrderBy != null && aOrderBy.length() != 0)
		{
			aStatement.append(" " + ORDER_BY + " ");
			parseOrderBy(aStatement, aOrderBy);
		}
	}

	public static void parseOrderBy
	(
		SQLStatement	aStatement,
		String			aOrderBy
	)
	{
		StringTokenizer tokenizer =
			new StringTokenizer(aOrderBy, DELIMITERS_ORDER, true);

		String token;
		while (tokenizer.hasMoreTokens())
		{
			token = tokenizer.nextToken();

			if (token.equalsIgnoreCase(HASH_DOCID))
			{
				parseDocIdInOrderBy(tokenizer, aStatement);
			}
			else
			{
				aStatement.append(token);
			}
		}
	}

	public static void parseDocIdInOrderBy
	(
		StringTokenizer	aTokenizer,
		SQLStatement	aStatement
	)
	{
		// Analyse tokens until a COMMA or a SORT ORDER SPECIFIER is found
		// or until the end of the order-by string is reached.
		String direction = "";
		String comma = "";
		String token;
		while (aTokenizer.hasMoreTokens())
		{
			token = aTokenizer.nextToken();

			if (token.equals(" "))
			{
				// Ignore spaces.
				continue;
			}
			else if (token.equals(","))
			{
				// We only encounter a comma if there was no sort order
				// specifier thus must remember to add the comma to the
				// resulting order-by clause.
				comma = token;
				break;
			}
			else if (DELIMITERS_ORDER.indexOf(token) == -1)
			{
				// This is expected to be a sort order specifier.
				direction = " " + token;
				break;
			}
		}

		// Add IFNDS and IFNID to the order-by clause.
		aStatement.append(IFNDS + direction + ", " + IFNID + direction + comma);
	}

	public static SearchResult transformSearchResult
	(
		NameValue[][]	aResult,
		int				aMaxRecords
	)
	{
		ArrayList<String> fieldNamesList = new ArrayList<String>();
		ArrayList<ResultRow> valuesList = new ArrayList<ResultRow>();
		boolean hasMoreRecords = false;
		if (aResult != null && aResult.length != 0 && aResult[0] != null)
		{
			// Obtain the field names from the first row with the assumption
			// that all rows will contain the same columns.
			for (int j = 0; j < aResult[0].length; j++)
			{
				if
				(
					aResult[0][j].getName().equalsIgnoreCase(IFNDS)
					&&
					(j + 1) < aResult[0].length
					&&
					aResult[0][j + 1].getName().equalsIgnoreCase(IFNID)
				)
				{
					fieldNamesList.add(HASH_DOCID);
					j++;
				}
				else
				{
					fieldNamesList.add(aResult[0][j].getName());
				}
			}

			// Obtain column values for each row.
			for (int i = 0; i < aResult.length; i++)
			{
				if (i >= aMaxRecords)
				{
					hasMoreRecords = true;
					break;
				}
				ArrayList<String> colList = new ArrayList<String>();
				if (aResult[i] != null)
				{
					for (int j = 0; j < aResult[i].length; j++)
					{
						if
						(
							aResult[i][j].getName().equalsIgnoreCase(IFNDS)
							&&
							(j + 1) < aResult[i].length
							&&
							aResult[i][j + 1].getName().equalsIgnoreCase(IFNID)
						)
						{
							DocId ifn = new DocId
							(
								aResult[i][j].getValue(),
								aResult[i][j + 1].getValue()
							);
							colList.add(ifn.toString());
							j++;
						}
						else
						{
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
		ArrayOfResultRow rows = new ArrayOfResultRow();
		rows.getValue().addAll(valuesList);
		
		SearchResult srchResult = new SearchResult();
		srchResult.setHasMoreRecords(hasMoreRecords);
		srchResult.getFieldNames().addAll(fieldNamesList);
		srchResult.setRows(rows);
		
		return srchResult;
	}


}
