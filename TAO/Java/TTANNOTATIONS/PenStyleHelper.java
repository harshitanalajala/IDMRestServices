package TTANNOTATIONS;

import TTANNOTATIONS.PenStyle;

/**
 *	Generated from IDL definition of enum "PenStyle"
 *	@author JacORB IDL compiler 
 */

public final class PenStyleHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_enum_tc(TTANNOTATIONS.PenStyleHelper.id(),"PenStyle",new String[]{"C_ANNOT_PEN_NULL","C_ANNOT_PEN_SOLID","C_ANNOT_PEN_DASH","C_ANNOT_PEN_DOT","C_ANNOT_PEN_DASHDOT","C_ANNOT_PEN_DASHDOTDOT"});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTANNOTATIONS.PenStyle s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTANNOTATIONS.PenStyle extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTANNOTATIONS/PenStyle:1.0";
	}
	public static PenStyle read (final org.omg.CORBA.portable.InputStream in)
	{
		return PenStyle.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final PenStyle s)
	{
		out.write_long(s.value());
	}
}
