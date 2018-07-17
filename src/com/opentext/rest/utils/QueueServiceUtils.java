package com.opentext.rest.utils;
import java.util.ArrayList;
import java.util.List;

import com.vignette.idm.common.QDataType;
import com.vignette.idm.common.QNameValue;
import com.vignette.idm.common.QueueEntry;
import com.vignette.idm.common.QueueInfo;
import com.vignette.idm.common.SelectionResult;
import com.vignette.idm.server.ArrayOfResultRow;
import com.vignette.idm.server.GeneralExceptionFaultDetail;
import com.vignette.idm.server.InvalidParameterException;
import com.vignette.idm.server.InvalidParameterExceptionFaultDetail;
import com.vignette.idm.common.QueueVarDef;
import com.vignette.idm.server.ResultRow;
import com.vignette.idm.server.SearchResult;
import com.vignette.idm.server.common.Converter;
import com.vignette.idm.server.common.ExceptionMessagesFormatter;
import com.vignette.idm.server.common.Validator;
import com.vignette.idm.server.common.olf.FormatterException;


public class QueueServiceUtils {
	
	private static final String COMPLETE			= "Complete: ";
	private static final String CONDITION			= "condition";
	private static final String ENTRYID				= "entryID";
	private static final String ENTRYVARIABLES		= "entryVariables";
	private static final String FIELDNAMES			= "fieldNames";
	private static final String HASMORERECORDS		= "hasMoreRecords";
	private static final String LICENSE				= "license";
	private static final String MAXENTRIES			= "maxEntries";
	private static final String MAXQUEUEINFOS		= "maxQueueInfos";
	private static final String MAXROWS				= "maxRows";
	private static final String NUMSELECTED			= "numSelected";
	private static final String OFFSET				= "offset";
	private static final String ORDERBY				= "orderBy";
	private static final String PARAMETERS			= "parameters";
	private static final String PROPNAMES			= "propNames";
	private static final String QSERVER				= "qServer";
	private static final String QSERVICENAME		= "qServiceName";
	private static final String QUEUEENTRIES		= "queueEntries";
	private static final String QUEUEENTRY			= "queueEntry";
	private static final String QUEUEID				= "queueID";
	private static final String QUEUEIDS			= "queueIDs";
	private static final String QUEUEINFO			= "queueInfo";
	private static final String RANGESIZE			= "rangeSize";
	private static final String ROWS				= "rows";
	private static final String SELECTIONID			= "selectionID";
	private static final String SERVICENAME			= "QueueServices";
	private static final String START				= "Start: ";
	private static final String TOTALNUMMATCHED		= "totalNumMatched";
	private static final String UNKNOWN				= "unknown";
	private static final String VARNAMES			= "varNames";
	
	 static ExceptionMessagesFormatter mFormatter;
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
	public static QNameValue[] transformEntryVariables
	(
		List<com.vignette.idm.server.NameValue>	aEntryVariables
	)
	{
		if (aEntryVariables == null)
		{
			return null;
		}
		else
		{
			QNameValue[] entryVariables = 
				new QNameValue[aEntryVariables.size()];
			for (int i = 0; i < aEntryVariables.size(); i++)
			{
				entryVariables[i] = new QNameValue
				(
					aEntryVariables.get(i).getName(),
					aEntryVariables.get(i).getValue()
				);
			}
			return entryVariables;
		}
	}
	public static SearchResult transformSelectionResult
	(
		SelectionResult	aSelectionResult,
		int				aMaxRows
	)
	{
		ArrayList<ResultRow> valuesList = new ArrayList<ResultRow>();
		boolean hasMoreRecords = false;
		if (aSelectionResult != null && aSelectionResult.m_Rows != null)
		{
			for (int i = 0; i < aSelectionResult.m_Rows.length; i++)
			{
				if (i >= aMaxRows)
				{
					hasMoreRecords = true;
					break;
				}
				
				ArrayList<String> rowData = new ArrayList<String>(aSelectionResult.m_Rows[i].length);
				for (int idx = 0; idx < aSelectionResult.m_Rows[i].length; idx++)
				{
					rowData.add(aSelectionResult.m_Rows[i][idx]);
				}

				ResultRow row = new ResultRow();
				row.setDotNetWa(null);				
				row.getFieldValues().addAll(rowData);
				
				valuesList.add(row);
			}
		}
		String[] fieldNames = aSelectionResult.m_FieldNames;
		ArrayList<String> fldNameList = new ArrayList<String>(fieldNames.length);
		for (int idx = 0; idx < fieldNames.length; idx++)
		{
			fldNameList.add(fieldNames[idx]);
		}
		
		ArrayOfResultRow rows = new ArrayOfResultRow();
		rows.getValue().addAll(valuesList);
		
		SearchResult srchResult = new SearchResult();
		srchResult.setHasMoreRecords(hasMoreRecords);
		srchResult.setRows(rows);
		srchResult.getFieldNames().addAll(fldNameList);
		
		return srchResult;
	}

	public static ArrayList<com.vignette.idm.server.QueueEntry> transformQueueEntryList
	(
		QueueEntry[]	aQueueEntryList
	)
	{
		ArrayList<com.vignette.idm.server.QueueEntry> queueEntryList =
			new ArrayList<com.vignette.idm.server.QueueEntry>(aQueueEntryList.length);
		for (int i = 0; i < aQueueEntryList.length; i++)
		{
			queueEntryList.add(transformQueueEntry(aQueueEntryList[i]));
		}
		return queueEntryList;
	}
	public static com.vignette.idm.server.QueueEntry transformQueueEntry
	(
		QueueEntry	aQueueEntry
	)
	{
		ArrayList<com.vignette.idm.server.NameValue> variables = new
		ArrayList<com.vignette.idm.server.NameValue>(aQueueEntry.m_Variables.length);
		for (int i = 0; i < aQueueEntry.m_Variables.length; i++)
		{
			com.vignette.idm.server.NameValue var = new com.vignette.idm.server.NameValue();
			var.setName(aQueueEntry.m_Variables[i].name);
			var.setValue(aQueueEntry.m_Variables[i].value);
		}

		com.vignette.idm.server.QueueEntry qEntry = new com.vignette.idm.server.QueueEntry();
		qEntry.setCreateTime(aQueueEntry.m_CreateTime);
		qEntry.setEntryID(Converter.convertUnsignedInt(aQueueEntry.m_EntryID));
		qEntry.setEntryTime(aQueueEntry.m_EntryTime);
		qEntry.setLockNextEnabled(aQueueEntry.m_LockNextEnabled);
		qEntry.setLockUser(aQueueEntry.m_LockUser);
		qEntry.setModifyTime(aQueueEntry.m_ModifyTime);
		qEntry.setPriority(Converter.convertUnsignedInt(aQueueEntry.m_Priority));
		qEntry.setQueueID(Converter.convertUnsignedInt(aQueueEntry.m_QueueID));
		qEntry.getVariables().addAll(variables);
		
		return qEntry;
	}
	
public static com.vignette.idm.server.QueueInfo transformQueueInfo
	(
		QueueInfo	aQueueInfo
	)
	{
		int queueVarDefsLen = aQueueInfo.m_RequiredVarDefs.length;
		ArrayList<com.vignette.idm.server.QueueVarDef> queueVarDefs =
			new ArrayList<com.vignette.idm.server.QueueVarDef>(queueVarDefsLen);
		for (int i = 0; i < queueVarDefsLen; i++)
		{
			QueueVarDef queueVarDef = aQueueInfo.m_RequiredVarDefs[i];			
			com.vignette.idm.server.QueueVarDef svrQVarDef = new com.vignette.idm.server.QueueVarDef();
			svrQVarDef.setDataType(transformDataType(queueVarDef.m_DataType));
			svrQVarDef.setDefaultValue(queueVarDef.m_DefaultValue);
			svrQVarDef.setHeading(queueVarDef.m_Heading);
			svrQVarDef.setName(queueVarDef.m_Name);
			
			queueVarDefs.add(svrQVarDef);
		}

		com.vignette.idm.server.QueueInfo queueInfo = new com.vignette.idm.server.QueueInfo();
		queueInfo.setAccessTime(aQueueInfo.m_AccessTime);
		queueInfo.setCreateTime(aQueueInfo.m_CreateTime);
		queueInfo.setDeleteTime(aQueueInfo.m_DeleteTime);
		queueInfo.setModifyTime(aQueueInfo.m_ModifyTime);
		queueInfo.setName(aQueueInfo.m_Name);
		queueInfo.setNumEntries(Converter.convertUnsignedInt(aQueueInfo.m_NumEntries));
		queueInfo.setQueueID(Converter.convertUnsignedInt(aQueueInfo.m_QueueID));
		queueInfo.setType(aQueueInfo.m_Type);
		queueInfo.getRequiredVarDefs().addAll(queueVarDefs);
		
		return queueInfo;
	}
public static ArrayList<com.vignette.idm.server.QueueInfo> transformQueueInfoList
(
	QueueInfo[]	aQueueInfoList
)
{
	ArrayList<com.vignette.idm.server.QueueInfo> queueInfoList =
		new ArrayList<com.vignette.idm.server.QueueInfo>(aQueueInfoList.length);
	for (int i = 0; i < aQueueInfoList.length; i++)
	{
		queueInfoList.add(transformQueueInfo(aQueueInfoList[i]));
	}
	return queueInfoList;
}
public static void validateQueueIDs
(
	String	aMethodName,
	List<Long>	aQueueIDs
) throws InvalidParameterException
{
	if (aQueueIDs == null || aQueueIDs.size() == 0)
	{
		InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
		faultDetail.setCallStack(null);
		faultDetail.setMessage(null);
		faultDetail.setParameterName(QUEUEIDS);
		faultDetail.setParameterValue(null);
		faultDetail.setWsMethodName(aMethodName);
		faultDetail.setWsName(SERVICENAME);					
		throw new InvalidParameterException(null, faultDetail);
	}
	for (int i = 0; i < aQueueIDs.size(); i++)
	{
		Validator.validateLongRangeParam
		(
			SERVICENAME,
			aMethodName,
			QUEUEIDS + "[" + i + "]",
			aQueueIDs.get(i),
			1,
			((long)Integer.MAX_VALUE) * 2 + 1   // Unsigned int max
		);
	}
}

public static int[] transformQueueIDs(List<Long> aQueueIDs)
{
	int[] queueIDs = new int[aQueueIDs.size()];
	for (int i = 0; i < aQueueIDs.size(); i++)
	{
		queueIDs[i] = aQueueIDs.get(i).intValue();
	}
	return queueIDs;
}
public static com.vignette.idm.server.QueueDataType transformDataType
(
	int	aDataType
)
{
	if (aDataType == QDataType.CHAR)
	{
		return com.vignette.idm.server.QueueDataType.CHAR;
	}
	else if (aDataType == QDataType.INTEGER)
	{
		return com.vignette.idm.server.QueueDataType.INTEGER;
	}
	else if (aDataType == QDataType.SMALLINT)
	{
		return com.vignette.idm.server.QueueDataType.SMALLINT;
	}
	else if (aDataType == QDataType.DATE)
	{
		return com.vignette.idm.server.QueueDataType.DATE;
	}
	else if (aDataType == QDataType.TIME)
	{
		return com.vignette.idm.server.QueueDataType.TIME;
	}
	else if (aDataType == QDataType.DATETIME)
	{
		return com.vignette.idm.server.QueueDataType.DATETIME;
	}
	else if (aDataType == QDataType.CUST00)
	{
		return com.vignette.idm.server.QueueDataType.CUST_00;
	}
	else if (aDataType == QDataType.CUST01)
	{
		return com.vignette.idm.server.QueueDataType.CUST_01;
	}
	else if (aDataType == QDataType.CUST02)
	{
		return com.vignette.idm.server.QueueDataType.CUST_02;
	}
	else if (aDataType == QDataType.CUST03)
	{
		return com.vignette.idm.server.QueueDataType.CUST_03;
	}
	else if (aDataType == QDataType.CUST04)
	{
		return com.vignette.idm.server.QueueDataType.CUST_04;
	}
	else if (aDataType == QDataType.CUST05)
	{
		return com.vignette.idm.server.QueueDataType.CUST_05;
	}
	else
	{
		return com.vignette.idm.server.QueueDataType.CHAR;
	}
}

}

