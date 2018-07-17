package TTANNOTATIONS;


/**
 *	Generated from IDL definition of struct "ANNOT_FreeHand_t"
 *	@author JacORB IDL compiler 
 */

public final class ANNOT_FreeHand_tHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(TTANNOTATIONS.ANNOT_FreeHand_tHelper.id(),"ANNOT_FreeHand_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("x", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("y", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("penWidth", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("lineStyle", TTANNOTATIONS.PenStyleHelper.type(), null),new org.omg.CORBA.StructMember("dataType", TTANNOTATIONS.SpecialTypeHelper.type(), null),new org.omg.CORBA.StructMember("points", TTANNOTATIONS.PointSeqHelper.type(), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTANNOTATIONS.ANNOT_FreeHand_t s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTANNOTATIONS.ANNOT_FreeHand_t extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTANNOTATIONS/ANNOT_FreeHand_t:1.0";
	}
	public static TTANNOTATIONS.ANNOT_FreeHand_t read (final org.omg.CORBA.portable.InputStream in)
	{
		TTANNOTATIONS.ANNOT_FreeHand_t result = new TTANNOTATIONS.ANNOT_FreeHand_t();
		result.x=in.read_long();
		result.y=in.read_long();
		result.penWidth=in.read_long();
		result.lineStyle=TTANNOTATIONS.PenStyleHelper.read(in);
		result.dataType=TTANNOTATIONS.SpecialTypeHelper.read(in);
		result.points = TTANNOTATIONS.PointSeqHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTANNOTATIONS.ANNOT_FreeHand_t s)
	{
		out.write_long(s.x);
		out.write_long(s.y);
		out.write_long(s.penWidth);
		TTANNOTATIONS.PenStyleHelper.write(out,s.lineStyle);
		TTANNOTATIONS.SpecialTypeHelper.write(out,s.dataType);
		TTANNOTATIONS.PointSeqHelper.write(out,s.points);
	}
}
