package TTGENERAL;

import TTGENERAL.GenFunctions;

/**
 *	Generated from IDL interface "GenFunctions"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class GenFunctionsHelper
{
	public static void insert (final org.omg.CORBA.Any any, final TTGENERAL.GenFunctions s)
	{
			any.insert_Object(s);
	}
	public static TTGENERAL.GenFunctions extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static org.omg.CORBA.TypeCode type()
	{
		return org.omg.CORBA.ORB.init().create_interface_tc("IDL:TTGENERAL/GenFunctions:1.0", "GenFunctions");
	}
	public static String id()
	{
		return "IDL:TTGENERAL/GenFunctions:1.0";
	}
	public static GenFunctions read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object());
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final TTGENERAL.GenFunctions s)
	{
		_out.write_Object(s);
	}
	public static TTGENERAL.GenFunctions narrow(final java.lang.Object obj)
	{
		if (obj instanceof TTGENERAL.GenFunctions)
		{
			return (TTGENERAL.GenFunctions)obj;
		}
		else if (obj instanceof org.omg.CORBA.Object)
		{
			return narrow((org.omg.CORBA.Object)obj);
		}
		throw new org.omg.CORBA.BAD_PARAM("Failed to narrow in helper");
	}
	public static TTGENERAL.GenFunctions narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (TTGENERAL.GenFunctions)obj;
		}
		catch (ClassCastException c)
		{
			if (obj._is_a("IDL:TTGENERAL/GenFunctions:1.0"))
			{
				TTGENERAL._GenFunctionsStub stub;
				stub = new TTGENERAL._GenFunctionsStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
			}
		}
		throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
	}
	public static TTGENERAL.GenFunctions unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (TTGENERAL.GenFunctions)obj;
		}
		catch (ClassCastException c)
		{
				TTGENERAL._GenFunctionsStub stub;
				stub = new TTGENERAL._GenFunctionsStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
		}
	}
}
