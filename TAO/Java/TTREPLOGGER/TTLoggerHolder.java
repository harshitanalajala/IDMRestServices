package TTREPLOGGER;

import TTREPLOGGER.TTLogger;
import TTREPLOGGER.TTLoggerHelper;

/**
 *	Generated from IDL interface "TTLogger"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class TTLoggerHolder	implements org.omg.CORBA.portable.Streamable{
	 public TTLogger value;
	public TTLoggerHolder()
	{
	}
	public TTLoggerHolder (final TTLogger initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type()
	{
		return TTLoggerHelper.type();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = TTLoggerHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		TTLoggerHelper.write (_out,value);
	}
}
