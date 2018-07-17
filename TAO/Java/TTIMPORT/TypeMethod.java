package TTIMPORT;

import TTIMPORT.TypeMethod;

/**
 *	Generated from IDL definition of enum "TypeMethod"
 *	@author JacORB IDL compiler 
 */

public final class TypeMethod
	implements org.omg.CORBA.portable.IDLEntity
{
	private int value = -1;
	public static final int _DOSEXT = 0;
	public static final TypeMethod DOSEXT = new TypeMethod(_DOSEXT);
	public static final int _MIME = 1;
	public static final TypeMethod MIME = new TypeMethod(_MIME);
	public static final int _FORMATKEY = 2;
	public static final TypeMethod FORMATKEY = new TypeMethod(_FORMATKEY);
	public int value()
	{
		return value;
	}
	public static TypeMethod from_int(int value)
	{
		switch (value) {
			case _DOSEXT: return DOSEXT;
			case _MIME: return MIME;
			case _FORMATKEY: return FORMATKEY;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected TypeMethod(int i)
	{
		value = i;
	}
	java.lang.Object readResolve()
	throws java.io.ObjectStreamException
	{
		return from_int(value());
	}
}
