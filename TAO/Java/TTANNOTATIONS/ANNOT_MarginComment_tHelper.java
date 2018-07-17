package TTANNOTATIONS;


/**
 *	Generated from IDL definition of struct "ANNOT_MarginComment_t"
 *	@author JacORB IDL compiler 
 */

public final class ANNOT_MarginComment_tHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(TTANNOTATIONS.ANNOT_MarginComment_tHelper.id(),"ANNOT_MarginComment_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("x1", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("y1", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("x2", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("y2", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("rotation", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("fontSize", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("fontStyle", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("fontFamily", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("dataType", TTANNOTATIONS.SpecialTypeHelper.type(), null),new org.omg.CORBA.StructMember("dataLength", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("data", org.omg.CORBA.ORB.init().create_string_tc(0), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTANNOTATIONS.ANNOT_MarginComment_t s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTANNOTATIONS.ANNOT_MarginComment_t extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTANNOTATIONS/ANNOT_MarginComment_t:1.0";
	}
	public static TTANNOTATIONS.ANNOT_MarginComment_t read (final org.omg.CORBA.portable.InputStream in)
	{
		TTANNOTATIONS.ANNOT_MarginComment_t result = new TTANNOTATIONS.ANNOT_MarginComment_t();
		result.x1=in.read_long();
		result.y1=in.read_long();
		result.x2=in.read_long();
		result.y2=in.read_long();
		result.rotation=in.read_long();
		result.fontSize=in.read_long();
		result.fontStyle=in.read_long();
		result.fontFamily=in.read_string();
		result.dataType=TTANNOTATIONS.SpecialTypeHelper.read(in);
		result.dataLength=in.read_long();
		result.data=in.read_string();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTANNOTATIONS.ANNOT_MarginComment_t s)
	{
		out.write_long(s.x1);
		out.write_long(s.y1);
		out.write_long(s.x2);
		out.write_long(s.y2);
		out.write_long(s.rotation);
		out.write_long(s.fontSize);
		out.write_long(s.fontStyle);
		out.write_string(s.fontFamily);
		TTANNOTATIONS.SpecialTypeHelper.write(out,s.dataType);
		out.write_long(s.dataLength);
		out.write_string(s.data);
	}
}
