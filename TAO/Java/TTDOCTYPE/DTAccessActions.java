package TTDOCTYPE;

import TTDOCTYPE.DTAccessActions;

/**
 *	Generated from IDL definition of enum "DTAccessActions"
 *	@author JacORB IDL compiler 
 */

public final class DTAccessActions
	implements org.omg.CORBA.portable.IDLEntity
{
	private int value = -1;
	public static final int _C_DTAA_CREATE = 0;
	public static final DTAccessActions C_DTAA_CREATE = new DTAccessActions(_C_DTAA_CREATE);
	public static final int _C_DTAA_READ = 1;
	public static final DTAccessActions C_DTAA_READ = new DTAccessActions(_C_DTAA_READ);
	public static final int _C_DTAA_WRITE = 2;
	public static final DTAccessActions C_DTAA_WRITE = new DTAccessActions(_C_DTAA_WRITE);
	public static final int _C_DTAA_DELETE = 3;
	public static final DTAccessActions C_DTAA_DELETE = new DTAccessActions(_C_DTAA_DELETE);
	public int value()
	{
		return value;
	}
	public static DTAccessActions from_int(int value)
	{
		switch (value) {
			case _C_DTAA_CREATE: return C_DTAA_CREATE;
			case _C_DTAA_READ: return C_DTAA_READ;
			case _C_DTAA_WRITE: return C_DTAA_WRITE;
			case _C_DTAA_DELETE: return C_DTAA_DELETE;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected DTAccessActions(int i)
	{
		value = i;
	}
	java.lang.Object readResolve()
	throws java.io.ObjectStreamException
	{
		return from_int(value());
	}
}
