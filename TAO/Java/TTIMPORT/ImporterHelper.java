package TTIMPORT;

import TTIMPORT.Importer;

/**
 *	Generated from IDL interface "Importer"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class ImporterHelper
{
	public static void insert (final org.omg.CORBA.Any any, final TTIMPORT.Importer s)
	{
			any.insert_Object(s);
	}
	public static TTIMPORT.Importer extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static org.omg.CORBA.TypeCode type()
	{
		return org.omg.CORBA.ORB.init().create_interface_tc("IDL:TTIMPORT/Importer:1.0", "Importer");
	}
	public static String id()
	{
		return "IDL:TTIMPORT/Importer:1.0";
	}
	public static Importer read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object());
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final TTIMPORT.Importer s)
	{
		_out.write_Object(s);
	}
	public static TTIMPORT.Importer narrow(final java.lang.Object obj)
	{
		if (obj instanceof TTIMPORT.Importer)
		{
			return (TTIMPORT.Importer)obj;
		}
		else if (obj instanceof org.omg.CORBA.Object)
		{
			return narrow((org.omg.CORBA.Object)obj);
		}
		throw new org.omg.CORBA.BAD_PARAM("Failed to narrow in helper");
	}
	public static TTIMPORT.Importer narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (TTIMPORT.Importer)obj;
		}
		catch (ClassCastException c)
		{
			if (obj._is_a("IDL:TTIMPORT/Importer:1.0"))
			{
				TTIMPORT._ImporterStub stub;
				stub = new TTIMPORT._ImporterStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
			}
		}
		throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
	}
	public static TTIMPORT.Importer unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (TTIMPORT.Importer)obj;
		}
		catch (ClassCastException c)
		{
				TTIMPORT._ImporterStub stub;
				stub = new TTIMPORT._ImporterStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
		}
	}
}
