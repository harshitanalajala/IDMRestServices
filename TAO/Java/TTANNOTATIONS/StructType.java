package TTANNOTATIONS;

import TTANNOTATIONS.StructType;

/**
 *	Generated from IDL definition of enum "StructType"
 *	@author JacORB IDL compiler 
 */

public final class StructType
	implements org.omg.CORBA.portable.IDLEntity
{
	private int value = -1;
	public static final int _C_ANNOT_NULL_OBJECT = 0;
	public static final StructType C_ANNOT_NULL_OBJECT = new StructType(_C_ANNOT_NULL_OBJECT);
	public static final int _C_ANNOT_FILE = 1;
	public static final StructType C_ANNOT_FILE = new StructType(_C_ANNOT_FILE);
	public static final int _C_ANNOT_SUBPAGE = 2;
	public static final StructType C_ANNOT_SUBPAGE = new StructType(_C_ANNOT_SUBPAGE);
	public static final int _C_ANNOT_ANNOTATION = 3;
	public static final StructType C_ANNOT_ANNOTATION = new StructType(_C_ANNOT_ANNOTATION);
	public static final int _C_ANNOT_HIGHLIGHT = 4;
	public static final StructType C_ANNOT_HIGHLIGHT = new StructType(_C_ANNOT_HIGHLIGHT);
	public static final int _C_ANNOT_STICKYNOTE = 5;
	public static final StructType C_ANNOT_STICKYNOTE = new StructType(_C_ANNOT_STICKYNOTE);
	public static final int _C_ANNOT_UNDERLINE = 6;
	public static final StructType C_ANNOT_UNDERLINE = new StructType(_C_ANNOT_UNDERLINE);
	public static final int _C_ANNOT_MARGINCOMMENT = 7;
	public static final StructType C_ANNOT_MARGINCOMMENT = new StructType(_C_ANNOT_MARGINCOMMENT);
	public static final int _C_ANNOT_FREEHAND = 8;
	public static final StructType C_ANNOT_FREEHAND = new StructType(_C_ANNOT_FREEHAND);
	public static final int _C_ANNOT_ELLIPSE = 9;
	public static final StructType C_ANNOT_ELLIPSE = new StructType(_C_ANNOT_ELLIPSE);
	public int value()
	{
		return value;
	}
	public static StructType from_int(int value)
	{
		switch (value) {
			case _C_ANNOT_NULL_OBJECT: return C_ANNOT_NULL_OBJECT;
			case _C_ANNOT_FILE: return C_ANNOT_FILE;
			case _C_ANNOT_SUBPAGE: return C_ANNOT_SUBPAGE;
			case _C_ANNOT_ANNOTATION: return C_ANNOT_ANNOTATION;
			case _C_ANNOT_HIGHLIGHT: return C_ANNOT_HIGHLIGHT;
			case _C_ANNOT_STICKYNOTE: return C_ANNOT_STICKYNOTE;
			case _C_ANNOT_UNDERLINE: return C_ANNOT_UNDERLINE;
			case _C_ANNOT_MARGINCOMMENT: return C_ANNOT_MARGINCOMMENT;
			case _C_ANNOT_FREEHAND: return C_ANNOT_FREEHAND;
			case _C_ANNOT_ELLIPSE: return C_ANNOT_ELLIPSE;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected StructType(int i)
	{
		value = i;
	}
	java.lang.Object readResolve()
	throws java.io.ObjectStreamException
	{
		return from_int(value());
	}
}
