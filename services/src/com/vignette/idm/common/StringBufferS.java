////////////////////////////////////////////////////////////////////////////////
//	Title		:	StringBufferS.java
//
//	Description	:	Class which extends java.lang.StringBuffer to allow
//					appending of various objects to the string buffer.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.common;

import java.util.List;

import com.vignette.idm.common.ApplicationEntry;
import com.vignette.idm.common.NameValue;
import com.vignette.idm.common.QNameValue;
import com.vignette.idm.common.QueueEntry;
import com.vignette.idm.common.QueueInfo;
import com.vignette.idm.common.RenditionDetail;
import com.vignette.idm.common.SelectionResult;
import com.vignette.idm.services.ColumnDef;

public class StringBufferS
{
	private StringBuffer mStringBuffer;

	public StringBufferS()
	{
		mStringBuffer = new StringBuffer();
	}

	public StringBufferS(String aString)
	{
		mStringBuffer = new StringBuffer(aString);
	}

	public void append(String aDesc, String aString, String aComma)
	{
		mStringBuffer.append(aDesc + "=" + aString + aComma);
	}

	public void append(String aDesc, int aInt, String aComma)
	{
		mStringBuffer.append(aDesc + "=" + aInt + aComma);
	}

	public void append(String aDesc, long aLong, String aComma)
	{
		mStringBuffer.append(aDesc + "=" + aLong + aComma);
	}

	public void append(String aDesc, boolean aBoolean, String aComma)
	{
		mStringBuffer.append(aDesc + "=" + aBoolean + aComma);
	}

	@Override
	public String toString()
	{
		return mStringBuffer.toString();
	}

	public void append(String aDesc, byte[] aBytes, String aComma)
	{
		mStringBuffer.append(aDesc + "[]=");
		if (aBytes != null)
		{
			mStringBuffer.append("" + aBytes.length);
		}
		else
		{
			mStringBuffer.append("null");
		}
		mStringBuffer.append(aComma);
	}

	public void append(String aDesc, int[] aInts, String aComma)
	{
		mStringBuffer.append(aDesc + "[]=");
		if (aInts != null)
		{
			mStringBuffer.append("" + aInts.length);
		}
		else
		{
			mStringBuffer.append("null");
		}
		mStringBuffer.append(aComma);
	}

	public void append(String aDesc, String[] aStrings, String aComma)
	{
		mStringBuffer.append(aDesc + "[]=");
		if (aStrings != null)
		{
			mStringBuffer.append("" + aStrings.length);
		}
		else
		{
			mStringBuffer.append("null");
		}
		mStringBuffer.append(aComma);
	}

	public void append(String aDesc, NameValue[] aNameValues, String aComma)
	{
		mStringBuffer.append(aDesc + "[]=");
		if (aNameValues != null)
		{
			mStringBuffer.append("" + aNameValues.length);
		}
		else
		{
			mStringBuffer.append("null");
		}
		mStringBuffer.append(aComma);
	}

	public void append(String aDesc, List aNameValues, String aComma)
	{
		mStringBuffer.append(aDesc + "[]=");
		if (aNameValues != null)
		{
			mStringBuffer.append("" + aNameValues.size());
		}
		else
		{
			mStringBuffer.append("null");
		}
		mStringBuffer.append(aComma);
	}

	public void append(String aDesc, NameValue[][] aNameValues, String aComma)
	{
		mStringBuffer.append(aDesc + "[][]=");
		if (aNameValues != null)
		{
			mStringBuffer.append("" + aNameValues.length);
		}
		else
		{
			mStringBuffer.append("null");
		}
		mStringBuffer.append(aComma);
	}

	public void append
	(
		String				aDesc,
		ApplicationEntry[]	anApplicationEntries,
		String				aComma
	)
	{
		mStringBuffer.append(aDesc + "[]=");
		if (anApplicationEntries != null)
		{
			mStringBuffer.append("" + anApplicationEntries.length);
		}
		else
		{
			mStringBuffer.append("null");
		}
		mStringBuffer.append(aComma);
	}

	public void append(String aDesc, QueueInfo aQueueInfo, String aComma)
	{
		mStringBuffer.append
		(
			aDesc + "=(" +
			"m_QueueID=" + aQueueInfo.m_QueueID + ", " +
			"m_Name=" + aQueueInfo.m_Name + ", " +
			"m_Type=" + aQueueInfo.m_Type + ", " +
			"m_CreateTime=" + aQueueInfo.m_CreateTime + ", " +
			"m_AccessTime=" + aQueueInfo.m_AccessTime + ", " +
			"m_ModifyTime=" + aQueueInfo.m_ModifyTime + ", " +
			"m_DeleteTime=" + aQueueInfo.m_DeleteTime + ", " +
			"m_NumEntries=" + aQueueInfo.m_NumEntries + ", " +
			"m_RequiredVarDefs[]=" + aQueueInfo.m_RequiredVarDefs.length +
			")" + aComma
		);
	}

	public void append
	(
		String			aDesc,
		SelectionResult	aSelectionResult,
		String			aComma
	)
	{
		mStringBuffer.append
		(
			aDesc + "=(" +
			"m_FieldNames[]=" + aSelectionResult.m_FieldNames.length + ", " +
			"m_Rows[]=" + aSelectionResult.m_Rows.length +
			")" + aComma
		);
	}

	public void append
	(
		String		aDesc,
		QueueInfo[]	aQueueInfos,
		String		aComma
	)
	{
		mStringBuffer.append(aDesc + "[]=");
		if (aQueueInfos != null)
		{
			mStringBuffer.append("" + aQueueInfos.length);
		}
		else
		{
			mStringBuffer.append("null");
		}
		mStringBuffer.append(aComma);
	}

	public void append(String aDesc, QueueEntry aQueueEntry, String aComma)
	{
		mStringBuffer.append
		(
			aDesc + "=(" +
			"m_QueueID=" + aQueueEntry.m_QueueID + ", " +
			"m_EntryID=" + aQueueEntry.m_EntryID + ", " +
			"m_Priority=" + aQueueEntry.m_Priority + ", " +
			"m_CreateTime=" + aQueueEntry.m_CreateTime + ", " +
			"m_ModifyTime=" + aQueueEntry.m_ModifyTime + ", " +
			"m_EntryTime=" + aQueueEntry.m_EntryTime + ", " +
			"m_LockNextEnabled=" + aQueueEntry.m_LockNextEnabled + ", " +
			"m_LockUser=" + aQueueEntry.m_LockUser + ", " +
			"m_Variables[]=" + aQueueEntry.m_Variables.length +
			")" + aComma
		);
	}

	public void append
	(
		String			aDesc,
		QueueEntry[]	aQueueEntries,
		String			aComma
	)
	{
		mStringBuffer.append(aDesc + "[]=");
		if (aQueueEntries != null)
		{
			mStringBuffer.append("" + aQueueEntries.length);
		}
		else
		{
			mStringBuffer.append("null");
		}
		mStringBuffer.append(aComma);
	}

	public void append(String aDesc, QNameValue[] aQNameValues, String aComma)
	{
		mStringBuffer.append(aDesc + "[]=");
		if (aQNameValues != null)
		{
			mStringBuffer.append("" + aQNameValues.length);
		}
		else
		{
			mStringBuffer.append("null");
		}
		mStringBuffer.append(aComma);
	}

	public void append
	(
		String				aDesc,
		RenditionDetail[]	aRenditionDetails,
		String				aComma
	)
	{
		mStringBuffer.append(aDesc + "[]=");
		if (aRenditionDetails != null)
		{
			mStringBuffer.append("" + aRenditionDetails.length);
		}
		else
		{
			mStringBuffer.append("null");
		}
		mStringBuffer.append(aComma);
	}

	public void append(String aDesc, ColumnDef aColumnDef, String aComma)
	{
		mStringBuffer.append
		(
			aDesc + "[]=" + aColumnDef.getColumnList().size() + aComma
		);
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
