package TTREPLOGGER;

import TTREPLOGGER.EventTypes;
import TTREPLOGGER.EventTypesHelper;

/**
 *	Generated from IDL definition of enum "EventTypes"
 *	@author JacORB IDL compiler 
 */

public final class EventTypesHolder
	implements org.omg.CORBA.portable.Streamable
{
	public EventTypes value;

	public EventTypesHolder ()
	{
	}
	public EventTypesHolder (final EventTypes initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return EventTypesHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = EventTypesHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		EventTypesHelper.write (out,value);
	}
}
