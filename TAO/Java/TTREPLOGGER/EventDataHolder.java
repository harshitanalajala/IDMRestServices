package TTREPLOGGER;

import TTREPLOGGER.EventDataHelper;

/**
 *	Generated from IDL definition of alias "EventData"
 *	@author JacORB IDL compiler 
 */

public final class EventDataHolder
	implements org.omg.CORBA.portable.Streamable
{
	public java.lang.String[] value;

	public EventDataHolder ()
	{
	}
	public EventDataHolder (final java.lang.String[] initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return EventDataHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = EventDataHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		EventDataHelper.write (out,value);
	}
}
