package TTQUEUE;

/**
 *	Generated from IDL definition of struct "CQueueEntry"
 *	@author JacORB IDL compiler 
 */

public final class CQueueEntry
	implements org.omg.CORBA.portable.IDLEntity
{
	public CQueueEntry(){}
	public int queueId;
	public int entryId;
	public int priority;
	public java.lang.String createTime = "";
	public java.lang.String modifyTime = "";
	public java.lang.String entryTime = "";
	public boolean lockNextEnabled;
	public java.lang.String lockUser = "";
	public TTQUEUE.CNameValue[] varList;
	public CQueueEntry(int queueId, int entryId, int priority, java.lang.String createTime, java.lang.String modifyTime, java.lang.String entryTime, boolean lockNextEnabled, java.lang.String lockUser, TTQUEUE.CNameValue[] varList)
	{
		this.queueId = queueId;
		this.entryId = entryId;
		this.priority = priority;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.entryTime = entryTime;
		this.lockNextEnabled = lockNextEnabled;
		this.lockUser = lockUser;
		this.varList = varList;
	}
}
