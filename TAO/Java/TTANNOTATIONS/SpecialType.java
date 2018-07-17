package TTANNOTATIONS;

import TTANNOTATIONS.SpecialType;

/**
 *	Generated from IDL definition of enum "SpecialType"
 *	@author JacORB IDL compiler 
 */

public final class SpecialType
	implements org.omg.CORBA.portable.IDLEntity
{
	private int value = -1;
	public static final int _C_ANNOT_CHARACTER = 0;
	public static final SpecialType C_ANNOT_CHARACTER = new SpecialType(_C_ANNOT_CHARACTER);
	public static final int _C_ANNOT_POINT = 1;
	public static final SpecialType C_ANNOT_POINT = new SpecialType(_C_ANNOT_POINT);
	public int value()
	{
		return value;
	}
	public static SpecialType from_int(int value)
	{
		switch (value) {
			case _C_ANNOT_CHARACTER: return C_ANNOT_CHARACTER;
			case _C_ANNOT_POINT: return C_ANNOT_POINT;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected SpecialType(int i)
	{
		value = i;
	}
	java.lang.Object readResolve()
	throws java.io.ObjectStreamException
	{
		return from_int(value());
	}
}
