package TTGENERAL;

import TTGENERAL.MiscFunctions;

/**
 *	Generated from IDL interface "MiscFunctions"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class MiscFunctionsHelper
{
	public static void insert (final org.omg.CORBA.Any any, final TTGENERAL.MiscFunctions s)
	{
			any.insert_Object(s);
	}
	public static TTGENERAL.MiscFunctions extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static org.omg.CORBA.TypeCode type()
	{
		return org.omg.CORBA.ORB.init().create_interface_tc("IDL:TTGENERAL/MiscFunctions:1.0", "MiscFunctions");
	}
	public static String id()
	{
		return "IDL:TTGENERAL/MiscFunctions:1.0";
	}
	public static MiscFunctions read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object());
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final TTGENERAL.MiscFunctions s)
	{
		_out.write_Object(s);
	}
	public static TTGENERAL.MiscFunctions narrow(final java.lang.Object obj)
	{
		if (obj instanceof TTGENERAL.MiscFunctions)
		{
			return (TTGENERAL.MiscFunctions)obj;
		}
		else if (obj instanceof org.omg.CORBA.Object)
		{
			return narrow((org.omg.CORBA.Object)obj);
		}
		throw new org.omg.CORBA.BAD_PARAM("Failed to narrow in helper");
	}
	public static TTGENERAL.MiscFunctions narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (TTGENERAL.MiscFunctions)obj;
		}
		catch (ClassCastException c)
		{
			if (obj._is_a("IDL:TTGENERAL/MiscFunctions:1.0"))
			{
				TTGENERAL._MiscFunctionsStub stub;
				stub = new TTGENERAL._MiscFunctionsStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
			}
		}
		throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
	}
	public static TTGENERAL.MiscFunctions unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (TTGENERAL.MiscFunctions)obj;
		}
		catch (ClassCastException c)
		{
				TTGENERAL._MiscFunctionsStub stub;
				stub = new TTGENERAL._MiscFunctionsStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
		}
	}
}
