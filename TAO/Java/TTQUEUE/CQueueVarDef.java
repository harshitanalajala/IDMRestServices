package TTQUEUE;

/**
 *	Generated from IDL definition of struct "CQueueVarDef"
 *	@author JacORB IDL compiler 
 */

public final class CQueueVarDef
	implements org.omg.CORBA.portable.IDLEntity
{
	public CQueueVarDef(){}
	public java.lang.String name = "";
	public java.lang.String heading = "";
	public java.lang.String defaultValue = "";
	public TTQUEUE.CDataType dataType;
	public CQueueVarDef(java.lang.String name, java.lang.String heading, java.lang.String defaultValue, TTQUEUE.CDataType dataType)
	{
		this.name = name;
		this.heading = heading;
		this.defaultValue = defaultValue;
		this.dataType = dataType;
	}
}
