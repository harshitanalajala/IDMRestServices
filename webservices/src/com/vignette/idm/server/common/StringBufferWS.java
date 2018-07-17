////////////////////////////////////////////////////////////////////////////////
//	Title		:	StringBufferWS.java
//
//	Description	:	Class which extends java.lang.StringBuffer to allow
//					appending of various objects to the string buffer.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.server.common;

import java.util.List;

import com.vignette.idm.server.AppDef;
import com.vignette.idm.server.DMStatus;
import com.vignette.idm.server.DisplayFieldDef;
import com.vignette.idm.server.EntryFieldDef;
import com.vignette.idm.server.NameValue;
import com.vignette.idm.server.QueueEntry;
import com.vignette.idm.server.QueueInfo;
import com.vignette.idm.server.RenditionDetail;
import com.vignette.idm.server.ResultRow;
import com.vignette.idm.server.TraceSeverity;

public class StringBufferWS
{
	private StringBuffer mStringBuffer;

	public StringBufferWS()
	{
		mStringBuffer = new StringBuffer();
	}

	public StringBufferWS(String aString)
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

	public void append
	(
		String			aDesc,
		TraceSeverity	aTraceSeverity,
		String			aComma
	)
	{
		mStringBuffer.append(aDesc + "=" + aTraceSeverity.value() + aComma);
	}

	public void append(String aDesc, AppDef[] aAppDefs, String aComma)
	{
		mStringBuffer.append(aDesc + "[]=");
		if (aAppDefs != null)
		{
			mStringBuffer.append("" + aAppDefs.length);
		}
		else
		{
			mStringBuffer.append("null");
		}
		mStringBuffer.append(aComma);
	}

	public void append(String aDesc, List aAppFieldDefs, String aComma)
	{
		mStringBuffer.append(aDesc + "[]=");
		if (aAppFieldDefs != null)
		{
			mStringBuffer.append("" + aAppFieldDefs.size());
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
		DisplayFieldDef[]	aDisplayFieldDefs,
		String				aComma)
	{
		mStringBuffer.append(aDesc + "[]=");
		if (aDisplayFieldDefs != null)
		{
			mStringBuffer.append("" + aDisplayFieldDefs.length);
		}
		else
		{
			mStringBuffer.append("null");
		}
		mStringBuffer.append(aComma);
	}

	public void append
	(
		String			aDesc,
		EntryFieldDef[]	aEntryFieldDefs,
		String			aComma
	)
	{
		mStringBuffer.append(aDesc + "[]=");
		if (aEntryFieldDefs != null)
		{
			mStringBuffer.append("" + aEntryFieldDefs.length);
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

	public void append(String aDesc, ResultRow[] aResultRows, String aComma)
	{
		mStringBuffer.append(aDesc + "[]=");
		if (aResultRows != null)
		{
			mStringBuffer.append("" + aResultRows.length);
		}
		else
		{
			mStringBuffer.append("null");
		}
		mStringBuffer.append(aComma);
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

	public void append(String aDesc, QueueInfo aQueueInfo, String aComma)
	{
		mStringBuffer.append
		(
			aDesc + "=(" +
			"queueID=" + aQueueInfo.getQueueID() + ", " +
			"name=" + aQueueInfo.getName() + ", " +
			"type=" + aQueueInfo.getType() + ", " +
			"createTime=" + aQueueInfo.getCreateTime() + ", " +
			"accessTime=" + aQueueInfo.getAccessTime() + ", " +
			"modifyTime=" + aQueueInfo.getModifyTime() + ", " +
			"deleteTime=" + aQueueInfo.getDeleteTime() + ", " +
			"numEntries=" + aQueueInfo.getNumEntries() + ", " +
			"requiredVarDefs[]=" + aQueueInfo.getRequiredVarDefs().size() +
			")" + aComma
		);
	}

	public void append(String aDesc, QueueInfo[] aQueueInfos, String aComma)
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

	public void append(String aDesc, QueueEntry[] aQueueEntries, String aComma)
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

	public void append(String aDesc, long[] aLongs, String aComma)
	{
		mStringBuffer.append(aDesc + "[]=");
		if (aLongs != null)
		{
			mStringBuffer.append("" + aLongs.length);
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
			"queueID=" + aQueueEntry.getQueueID() + ", " +
			"entryID=" + aQueueEntry.getEntryID() + ", " +
			"priority=" + aQueueEntry.getPriority() + ", " +
			"createTime=" + aQueueEntry.getCreateTime() + ", " +
			"modifyTime=" + aQueueEntry.getModifyTime() + ", " +
			"entryTime=" + aQueueEntry.getEntryTime() + ", " +
			"lockNextEnabled=" + aQueueEntry.isLockNextEnabled() + ", " +
			"lockUser=" + aQueueEntry.getLockUser() + ", " +
			"variables[]=" + aQueueEntry.getVariables().size() +
			")" + aComma
		);
	}

	public void append(String aDesc, DMStatus aDMStatus, String aComma)
	{
		mStringBuffer.append(aDesc + "=" + aDMStatus.value() + aComma);
	}

	@Override
	public String toString()
	{
		return mStringBuffer.toString();
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
