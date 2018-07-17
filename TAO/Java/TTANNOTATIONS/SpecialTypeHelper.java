package TTANNOTATIONS;

import TTANNOTATIONS.SpecialType;

/**
 *	Generated from IDL definition of enum "SpecialType"
 *	@author JacORB IDL compiler 
 */

public final class SpecialTypeHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_enum_tc(TTANNOTATIONS.SpecialTypeHelper.id(),"SpecialType",new String[]{"C_ANNOT_CHARACTER","C_ANNOT_POINT"});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTANNOTATIONS.SpecialType s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTANNOTATIONS.SpecialType extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTANNOTATIONS/SpecialType:1.0";
	}
	public static SpecialType read (final org.omg.CORBA.portable.InputStream in)
	{
		return SpecialType.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final SpecialType s)
	{
		out.write_long(s.value());
	}
}
