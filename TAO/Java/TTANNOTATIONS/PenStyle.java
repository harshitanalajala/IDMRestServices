package TTANNOTATIONS;

import TTANNOTATIONS.PenStyle;

/**
 *	Generated from IDL definition of enum "PenStyle"
 *	@author JacORB IDL compiler 
 */

public final class PenStyle
	implements org.omg.CORBA.portable.IDLEntity
{
	private int value = -1;
	public static final int _C_ANNOT_PEN_NULL = 0;
	public static final PenStyle C_ANNOT_PEN_NULL = new PenStyle(_C_ANNOT_PEN_NULL);
	public static final int _C_ANNOT_PEN_SOLID = 1;
	public static final PenStyle C_ANNOT_PEN_SOLID = new PenStyle(_C_ANNOT_PEN_SOLID);
	public static final int _C_ANNOT_PEN_DASH = 2;
	public static final PenStyle C_ANNOT_PEN_DASH = new PenStyle(_C_ANNOT_PEN_DASH);
	public static final int _C_ANNOT_PEN_DOT = 3;
	public static final PenStyle C_ANNOT_PEN_DOT = new PenStyle(_C_ANNOT_PEN_DOT);
	public static final int _C_ANNOT_PEN_DASHDOT = 4;
	public static final PenStyle C_ANNOT_PEN_DASHDOT = new PenStyle(_C_ANNOT_PEN_DASHDOT);
	public static final int _C_ANNOT_PEN_DASHDOTDOT = 5;
	public static final PenStyle C_ANNOT_PEN_DASHDOTDOT = new PenStyle(_C_ANNOT_PEN_DASHDOTDOT);
	public int value()
	{
		return value;
	}
	public static PenStyle from_int(int value)
	{
		switch (value) {
			case _C_ANNOT_PEN_NULL: return C_ANNOT_PEN_NULL;
			case _C_ANNOT_PEN_SOLID: return C_ANNOT_PEN_SOLID;
			case _C_ANNOT_PEN_DASH: return C_ANNOT_PEN_DASH;
			case _C_ANNOT_PEN_DOT: return C_ANNOT_PEN_DOT;
			case _C_ANNOT_PEN_DASHDOT: return C_ANNOT_PEN_DASHDOT;
			case _C_ANNOT_PEN_DASHDOTDOT: return C_ANNOT_PEN_DASHDOTDOT;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected PenStyle(int i)
	{
		value = i;
	}
	java.lang.Object readResolve()
	throws java.io.ObjectStreamException
	{
		return from_int(value());
	}
}
