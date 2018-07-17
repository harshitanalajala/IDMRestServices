package TTANNOTATIONS;

import TTANNOTATIONS.StructType;

/**
 *	Generated from IDL definition of enum "StructType"
 *	@author JacORB IDL compiler 
 */

public final class StructTypeHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_enum_tc(TTANNOTATIONS.StructTypeHelper.id(),"StructType",new String[]{"C_ANNOT_NULL_OBJECT","C_ANNOT_FILE","C_ANNOT_SUBPAGE","C_ANNOT_ANNOTATION","C_ANNOT_HIGHLIGHT","C_ANNOT_STICKYNOTE","C_ANNOT_UNDERLINE","C_ANNOT_MARGINCOMMENT","C_ANNOT_FREEHAND","C_ANNOT_ELLIPSE"});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTANNOTATIONS.StructType s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTANNOTATIONS.StructType extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTANNOTATIONS/StructType:1.0";
	}
	public static StructType read (final org.omg.CORBA.portable.InputStream in)
	{
		return StructType.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final StructType s)
	{
		out.write_long(s.value());
	}
}
