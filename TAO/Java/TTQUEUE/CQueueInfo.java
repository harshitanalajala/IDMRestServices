package TTQUEUE;

/**
 *	Generated from IDL definition of struct "CQueueInfo"
 *	@author JacORB IDL compiler 
 */

public final class CQueueInfo
	implements org.omg.CORBA.portable.IDLEntity
{
	public CQueueInfo(){}
	public int queueId;
	public java.lang.String name = "";
	public java.lang.String type = "";
	public java.lang.String createTime = "";
	public java.lang.String accessTime = "";
	public java.lang.String modifyTime = "";
	public java.lang.String deleteTime = "";
	public int numEntries;
	public TTQUEUE.CQueueVarDef[] requiredVarDefs;
	public CQueueInfo(int queueId, java.lang.String name, java.lang.String type, java.lang.String createTime, java.lang.String accessTime, java.lang.String modifyTime, java.lang.String deleteTime, int numEntries, TTQUEUE.CQueueVarDef[] requiredVarDefs)
	{
		this.queueId = queueId;
		this.name = name;
		this.type = type;
		this.createTime = createTime;
		this.accessTime = accessTime;
		this.modifyTime = modifyTime;
		this.deleteTime = deleteTime;
		this.numEntries = numEntries;
		this.requiredVarDefs = requiredVarDefs;
	}
}
