package TTANNOTATIONS;


/**
 *	Generated from IDL definition of struct "ANNOT_StickyNote_t"
 *	@author JacORB IDL compiler 
 */

public final class ANNOT_StickyNote_tHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(TTANNOTATIONS.ANNOT_StickyNote_tHelper.id(),"ANNOT_StickyNote_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("x1", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("y1", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("x2", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("y2", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("rotation", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("shape", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("dataLength", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("dataType", TTANNOTATIONS.SpecialTypeHelper.type(), null),new org.omg.CORBA.StructMember("data", org.omg.CORBA.ORB.init().create_string_tc(0), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTANNOTATIONS.ANNOT_StickyNote_t s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTANNOTATIONS.ANNOT_StickyNote_t extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTANNOTATIONS/ANNOT_StickyNote_t:1.0";
	}
	public static TTANNOTATIONS.ANNOT_StickyNote_t read (final org.omg.CORBA.portable.InputStream in)
	{
		TTANNOTATIONS.ANNOT_StickyNote_t result = new TTANNOTATIONS.ANNOT_StickyNote_t();
		result.x1=in.read_long();
		result.y1=in.read_long();
		result.x2=in.read_long();
		result.y2=in.read_long();
		result.rotation=in.read_long();
		result.shape=in.read_long();
		result.dataLength=in.read_long();
		result.dataType=TTANNOTATIONS.SpecialTypeHelper.read(in);
		result.data=in.read_string();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTANNOTATIONS.ANNOT_StickyNote_t s)
	{
		out.write_long(s.x1);
		out.write_long(s.y1);
		out.write_long(s.x2);
		out.write_long(s.y2);
		out.write_long(s.rotation);
		out.write_long(s.shape);
		out.write_long(s.dataLength);
		TTANNOTATIONS.SpecialTypeHelper.write(out,s.dataType);
		out.write_string(s.data);
	}
}
