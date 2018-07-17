package TTANNOTATIONS;

import TTANNOTATIONS.StatusType;

/**
 *	Generated from IDL definition of enum "StatusType"
 *	@author JacORB IDL compiler 
 */

public final class StatusType
	implements org.omg.CORBA.portable.IDLEntity
{
	private int value = -1;
	public static final int _C_ORIGINAL = 0;
	public static final StatusType C_ORIGINAL = new StatusType(_C_ORIGINAL);
	public static final int _C_NEW = 1;
	public static final StatusType C_NEW = new StatusType(_C_NEW);
	public static final int _C_MODIFIED = 2;
	public static final StatusType C_MODIFIED = new StatusType(_C_MODIFIED);
	public static final int _C_DELETED = 3;
	public static final StatusType C_DELETED = new StatusType(_C_DELETED);
	public int value()
	{
		return value;
	}
	public static StatusType from_int(int value)
	{
		switch (value) {
			case _C_ORIGINAL: return C_ORIGINAL;
			case _C_NEW: return C_NEW;
			case _C_MODIFIED: return C_MODIFIED;
			case _C_DELETED: return C_DELETED;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected StatusType(int i)
	{
		value = i;
	}
	java.lang.Object readResolve()
	throws java.io.ObjectStreamException
	{
		return from_int(value());
	}
}
