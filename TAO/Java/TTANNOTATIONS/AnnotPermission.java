package TTANNOTATIONS;

import TTANNOTATIONS.AnnotPermission;

/**
 *	Generated from IDL definition of enum "AnnotPermission"
 *	@author JacORB IDL compiler 
 */

public final class AnnotPermission
	implements org.omg.CORBA.portable.IDLEntity
{
	private int value = -1;
	public static final int _C_ANNOT_HIDDEN = 0;
	public static final AnnotPermission C_ANNOT_HIDDEN = new AnnotPermission(_C_ANNOT_HIDDEN);
	public static final int _C_ANNOT_READ_ONLY = 1;
	public static final AnnotPermission C_ANNOT_READ_ONLY = new AnnotPermission(_C_ANNOT_READ_ONLY);
	public static final int _C_ANNOT_MODIFIABLE = 2;
	public static final AnnotPermission C_ANNOT_MODIFIABLE = new AnnotPermission(_C_ANNOT_MODIFIABLE);
	public static final int _C_ANNOT_DELETABLE = 3;
	public static final AnnotPermission C_ANNOT_DELETABLE = new AnnotPermission(_C_ANNOT_DELETABLE);
	public int value()
	{
		return value;
	}
	public static AnnotPermission from_int(int value)
	{
		switch (value) {
			case _C_ANNOT_HIDDEN: return C_ANNOT_HIDDEN;
			case _C_ANNOT_READ_ONLY: return C_ANNOT_READ_ONLY;
			case _C_ANNOT_MODIFIABLE: return C_ANNOT_MODIFIABLE;
			case _C_ANNOT_DELETABLE: return C_ANNOT_DELETABLE;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected AnnotPermission(int i)
	{
		value = i;
	}
	java.lang.Object readResolve()
	throws java.io.ObjectStreamException
	{
		return from_int(value());
	}
}
