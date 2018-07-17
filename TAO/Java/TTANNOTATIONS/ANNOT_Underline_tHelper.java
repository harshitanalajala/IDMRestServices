package TTANNOTATIONS;


/**
 *	Generated from IDL definition of struct "ANNOT_Underline_t"
 *	@author JacORB IDL compiler 
 */

public final class ANNOT_Underline_tHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(TTANNOTATIONS.ANNOT_Underline_tHelper.id(),"ANNOT_Underline_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("x1", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("y1", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("x2", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("y2", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("penWidth", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("lineStyle", TTANNOTATIONS.PenStyleHelper.type(), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTANNOTATIONS.ANNOT_Underline_t s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTANNOTATIONS.ANNOT_Underline_t extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTANNOTATIONS/ANNOT_Underline_t:1.0";
	}
	public static TTANNOTATIONS.ANNOT_Underline_t read (final org.omg.CORBA.portable.InputStream in)
	{
		TTANNOTATIONS.ANNOT_Underline_t result = new TTANNOTATIONS.ANNOT_Underline_t();
		result.x1=in.read_long();
		result.y1=in.read_long();
		result.x2=in.read_long();
		result.y2=in.read_long();
		result.penWidth=in.read_long();
		result.lineStyle=TTANNOTATIONS.PenStyleHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTANNOTATIONS.ANNOT_Underline_t s)
	{
		out.write_long(s.x1);
		out.write_long(s.y1);
		out.write_long(s.x2);
		out.write_long(s.y2);
		out.write_long(s.penWidth);
		TTANNOTATIONS.PenStyleHelper.write(out,s.lineStyle);
	}
}
