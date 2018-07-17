package TTANNOTATIONS;

import TTANNOTATIONS.StatusType;

/**
 *	Generated from IDL definition of enum "StatusType"
 *	@author JacORB IDL compiler 
 */

public final class StatusTypeHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_enum_tc(TTANNOTATIONS.StatusTypeHelper.id(),"StatusType",new String[]{"C_ORIGINAL","C_NEW","C_MODIFIED","C_DELETED"});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTANNOTATIONS.StatusType s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTANNOTATIONS.StatusType extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTANNOTATIONS/StatusType:1.0";
	}
	public static StatusType read (final org.omg.CORBA.portable.InputStream in)
	{
		return StatusType.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final StatusType s)
	{
		out.write_long(s.value());
	}
}
