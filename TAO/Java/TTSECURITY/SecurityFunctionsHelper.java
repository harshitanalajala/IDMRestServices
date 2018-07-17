package TTSECURITY;

import TTSECURITY.SecurityFunctions;

/**
 *	Generated from IDL interface "SecurityFunctions"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class SecurityFunctionsHelper
{
	public static void insert (final org.omg.CORBA.Any any, final TTSECURITY.SecurityFunctions s)
	{
			any.insert_Object(s);
	}
	public static TTSECURITY.SecurityFunctions extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static org.omg.CORBA.TypeCode type()
	{
		return org.omg.CORBA.ORB.init().create_interface_tc("IDL:TTSECURITY/SecurityFunctions:1.0", "SecurityFunctions");
	}
	public static String id()
	{
		return "IDL:TTSECURITY/SecurityFunctions:1.0";
	}
	public static SecurityFunctions read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object());
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final TTSECURITY.SecurityFunctions s)
	{
		_out.write_Object(s);
	}
	public static TTSECURITY.SecurityFunctions narrow(final java.lang.Object obj)
	{
		if (obj instanceof TTSECURITY.SecurityFunctions)
		{
			return (TTSECURITY.SecurityFunctions)obj;
		}
		else if (obj instanceof org.omg.CORBA.Object)
		{
			return narrow((org.omg.CORBA.Object)obj);
		}
		throw new org.omg.CORBA.BAD_PARAM("Failed to narrow in helper");
	}
	public static TTSECURITY.SecurityFunctions narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (TTSECURITY.SecurityFunctions)obj;
		}
		catch (ClassCastException c)
		{
			if (obj._is_a("IDL:TTSECURITY/SecurityFunctions:1.0"))
			{
				TTSECURITY._SecurityFunctionsStub stub;
				stub = new TTSECURITY._SecurityFunctionsStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
			}
		}
		throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
	}
	public static TTSECURITY.SecurityFunctions unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (TTSECURITY.SecurityFunctions)obj;
		}
		catch (ClassCastException c)
		{
				TTSECURITY._SecurityFunctionsStub stub;
				stub = new TTSECURITY._SecurityFunctionsStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
		}
	}
}
