package TTREPLOGGER;

import TTREPLOGGER.EventTypes;

/**
 *	Generated from IDL definition of enum "EventTypes"
 *	@author JacORB IDL compiler 
 */

public final class EventTypesHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_enum_tc(TTREPLOGGER.EventTypesHelper.id(),"EventTypes",new String[]{"EV_UWSADDTITLE","EV_UWSDELTITLE","EV_UWSMODTITLE"});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTREPLOGGER.EventTypes s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTREPLOGGER.EventTypes extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTREPLOGGER/EventTypes:1.0";
	}
	public static EventTypes read (final org.omg.CORBA.portable.InputStream in)
	{
		return EventTypes.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final EventTypes s)
	{
		out.write_long(s.value());
	}
}
