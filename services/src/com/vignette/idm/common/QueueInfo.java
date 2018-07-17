////////////////////////////////////////////////////////////////////////////////
//	Title		:	QueueInfo.java
//
//	Description	:	Class which holds properties of a queue.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.common;

public class QueueInfo
{
	public int m_QueueID;
	public String m_Name;
	public String m_Type;
	public String m_CreateTime;
	public String m_AccessTime;
	public String m_ModifyTime;
	public String m_DeleteTime;
	public int m_NumEntries;
	public QueueVarDef[] m_RequiredVarDefs;

	public QueueInfo()
	{
		m_QueueID = 0;
		m_Name = null;
		m_Type = null;
		m_CreateTime = null;
		m_AccessTime = null;
		m_ModifyTime = null;
		m_DeleteTime = null;
		m_NumEntries = 0;
		m_RequiredVarDefs = null;
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
