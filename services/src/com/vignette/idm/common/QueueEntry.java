////////////////////////////////////////////////////////////////////////////////
//	Title		:	QueueEntry.java
//
//	Description	:	Class which holds properties of a queue entry.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.common;

public class QueueEntry
{
	public int m_QueueID;
	public int m_EntryID;
	public int m_Priority;
	public String m_CreateTime;
	public String m_ModifyTime;
	public String m_EntryTime;
	public boolean m_LockNextEnabled;
	public String m_LockUser;
	public QNameValue[] m_Variables;

	public QueueEntry()
	{
		m_QueueID = 0;
		m_EntryID = 0;
		m_Priority = 0;
		m_CreateTime = null;
		m_ModifyTime = null;
		m_EntryTime = null;
		m_LockNextEnabled = false;
		m_LockUser = null;
		m_Variables = null;
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
