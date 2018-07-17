package TTANNOTATIONS;

import TTANNOTATIONS.Annot;

/**
 *	Generated from IDL interface "Annot"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class AnnotHelper
{
	public static void insert (final org.omg.CORBA.Any any, final TTANNOTATIONS.Annot s)
	{
			any.insert_Object(s);
	}
	public static TTANNOTATIONS.Annot extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static org.omg.CORBA.TypeCode type()
	{
		return org.omg.CORBA.ORB.init().create_interface_tc("IDL:TTANNOTATIONS/Annot:1.0", "Annot");
	}
	public static String id()
	{
		return "IDL:TTANNOTATIONS/Annot:1.0";
	}
	public static Annot read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object());
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final TTANNOTATIONS.Annot s)
	{
		_out.write_Object(s);
	}
	public static TTANNOTATIONS.Annot narrow(final java.lang.Object obj)
	{
		if (obj instanceof TTANNOTATIONS.Annot)
		{
			return (TTANNOTATIONS.Annot)obj;
		}
		else if (obj instanceof org.omg.CORBA.Object)
		{
			return narrow((org.omg.CORBA.Object)obj);
		}
		throw new org.omg.CORBA.BAD_PARAM("Failed to narrow in helper");
	}
	public static TTANNOTATIONS.Annot narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (TTANNOTATIONS.Annot)obj;
		}
		catch (ClassCastException c)
		{
			if (obj._is_a("IDL:TTANNOTATIONS/Annot:1.0"))
			{
				TTANNOTATIONS._AnnotStub stub;
				stub = new TTANNOTATIONS._AnnotStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
			}
		}
		throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
	}
	public static TTANNOTATIONS.Annot unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (TTANNOTATIONS.Annot)obj;
		}
		catch (ClassCastException c)
		{
				TTANNOTATIONS._AnnotStub stub;
				stub = new TTANNOTATIONS._AnnotStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
		}
	}
}
