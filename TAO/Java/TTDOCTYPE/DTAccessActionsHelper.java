package TTDOCTYPE;

import TTDOCTYPE.DTAccessActions;

/**
 *	Generated from IDL definition of enum "DTAccessActions"
 *	@author JacORB IDL compiler 
 */

public final class DTAccessActionsHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_enum_tc(TTDOCTYPE.DTAccessActionsHelper.id(),"DTAccessActions",new String[]{"C_DTAA_CREATE","C_DTAA_READ","C_DTAA_WRITE","C_DTAA_DELETE"});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTDOCTYPE.DTAccessActions s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTDOCTYPE.DTAccessActions extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTDOCTYPE/DTAccessActions:1.0";
	}
	public static DTAccessActions read (final org.omg.CORBA.portable.InputStream in)
	{
		return DTAccessActions.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final DTAccessActions s)
	{
		out.write_long(s.value());
	}
}
