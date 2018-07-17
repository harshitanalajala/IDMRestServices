package TTGENERAL;

/**
 *	Generated from IDL definition of struct "FieldAttr"
 *	@author JacORB IDL compiler 
 */

public final class FieldAttr
	implements org.omg.CORBA.portable.IDLEntity
{
	public FieldAttr(){}
	public java.lang.String mFieldName = "";
	public java.lang.String mFieldValue = "";
	public int mFieldType;
	public int mFieldLen;
	public FieldAttr(java.lang.String mFieldName, java.lang.String mFieldValue, int mFieldType, int mFieldLen)
	{
		this.mFieldName = mFieldName;
		this.mFieldValue = mFieldValue;
		this.mFieldType = mFieldType;
		this.mFieldLen = mFieldLen;
	}
}
