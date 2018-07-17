package TTANNOTATIONS;


/**
 *	Generated from IDL definition of struct "ANNOT_Colour_t"
 *	@author JacORB IDL compiler 
 */

public final class ANNOT_Colour_tHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(TTANNOTATIONS.ANNOT_Colour_tHelper.id(),"ANNOT_Colour_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("red", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(2)), null),new org.omg.CORBA.StructMember("green", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(2)), null),new org.omg.CORBA.StructMember("blue", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(2)), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTANNOTATIONS.ANNOT_Colour_t s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTANNOTATIONS.ANNOT_Colour_t extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTANNOTATIONS/ANNOT_Colour_t:1.0";
	}
	public static TTANNOTATIONS.ANNOT_Colour_t read (final org.omg.CORBA.portable.InputStream in)
	{
		TTANNOTATIONS.ANNOT_Colour_t result = new TTANNOTATIONS.ANNOT_Colour_t();
		result.red=in.read_short();
		result.green=in.read_short();
		result.blue=in.read_short();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTANNOTATIONS.ANNOT_Colour_t s)
	{
		out.write_short(s.red);
		out.write_short(s.green);
		out.write_short(s.blue);
	}
}
