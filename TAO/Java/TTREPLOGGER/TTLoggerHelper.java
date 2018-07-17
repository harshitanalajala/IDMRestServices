package TTREPLOGGER;

import TTREPLOGGER.TTLogger;

/**
 *	Generated from IDL interface "TTLogger"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class TTLoggerHelper
{
	public static void insert (final org.omg.CORBA.Any any, final TTREPLOGGER.TTLogger s)
	{
			any.insert_Object(s);
	}
	public static TTREPLOGGER.TTLogger extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static org.omg.CORBA.TypeCode type()
	{
		return org.omg.CORBA.ORB.init().create_interface_tc("IDL:TTREPLOGGER/TTLogger:1.0", "TTLogger");
	}
	public static String id()
	{
		return "IDL:TTREPLOGGER/TTLogger:1.0";
	}
	public static TTLogger read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object());
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final TTREPLOGGER.TTLogger s)
	{
		_out.write_Object(s);
	}
	public static TTREPLOGGER.TTLogger narrow(final java.lang.Object obj)
	{
		if (obj instanceof TTREPLOGGER.TTLogger)
		{
			return (TTREPLOGGER.TTLogger)obj;
		}
		else if (obj instanceof org.omg.CORBA.Object)
		{
			return narrow((org.omg.CORBA.Object)obj);
		}
		throw new org.omg.CORBA.BAD_PARAM("Failed to narrow in helper");
	}
	public static TTREPLOGGER.TTLogger narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (TTREPLOGGER.TTLogger)obj;
		}
		catch (ClassCastException c)
		{
			if (obj._is_a("IDL:TTREPLOGGER/TTLogger:1.0"))
			{
				TTREPLOGGER._TTLoggerStub stub;
				stub = new TTREPLOGGER._TTLoggerStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
			}
		}
		throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
	}
	public static TTREPLOGGER.TTLogger unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (TTREPLOGGER.TTLogger)obj;
		}
		catch (ClassCastException c)
		{
				TTREPLOGGER._TTLoggerStub stub;
				stub = new TTREPLOGGER._TTLoggerStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
		}
	}
}
