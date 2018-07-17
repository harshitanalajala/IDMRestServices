package OOHONCHO;

import OOHONCHO.idmservers;

/**
 *	Generated from IDL interface "idmservers"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class idmserversHelper
{
	public static void insert (final org.omg.CORBA.Any any, final OOHONCHO.idmservers s)
	{
			any.insert_Object(s);
	}
	public static OOHONCHO.idmservers extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static org.omg.CORBA.TypeCode type()
	{
		return org.omg.CORBA.ORB.init().create_interface_tc("IDL:OOHONCHO/idmservers:1.0", "idmservers");
	}
	public static String id()
	{
		return "IDL:OOHONCHO/idmservers:1.0";
	}
	public static idmservers read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object());
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final OOHONCHO.idmservers s)
	{
		_out.write_Object(s);
	}
	public static OOHONCHO.idmservers narrow(final java.lang.Object obj)
	{
		if (obj instanceof OOHONCHO.idmservers)
		{
			return (OOHONCHO.idmservers)obj;
		}
		else if (obj instanceof org.omg.CORBA.Object)
		{
			return narrow((org.omg.CORBA.Object)obj);
		}
		throw new org.omg.CORBA.BAD_PARAM("Failed to narrow in helper");
	}
	public static OOHONCHO.idmservers narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (OOHONCHO.idmservers)obj;
		}
		catch (ClassCastException c)
		{
			if (obj._is_a("IDL:OOHONCHO/idmservers:1.0"))
			{
				OOHONCHO._idmserversStub stub;
				stub = new OOHONCHO._idmserversStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
			}
		}
		throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
	}
	public static OOHONCHO.idmservers unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (OOHONCHO.idmservers)obj;
		}
		catch (ClassCastException c)
		{
				OOHONCHO._idmserversStub stub;
				stub = new OOHONCHO._idmserversStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
		}
	}
}
