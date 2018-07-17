package TTANNOTATIONS;


/**
 *	Generated from IDL definition of struct "ANNOT_Point_t"
 *	@author JacORB IDL compiler 
 */

public final class ANNOT_Point_tHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(TTANNOTATIONS.ANNOT_Point_tHelper.id(),"ANNOT_Point_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("x", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("y", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTANNOTATIONS.ANNOT_Point_t s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTANNOTATIONS.ANNOT_Point_t extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTANNOTATIONS/ANNOT_Point_t:1.0";
	}
	public static TTANNOTATIONS.ANNOT_Point_t read (final org.omg.CORBA.portable.InputStream in)
	{
		TTANNOTATIONS.ANNOT_Point_t result = new TTANNOTATIONS.ANNOT_Point_t();
		result.x=in.read_long();
		result.y=in.read_long();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTANNOTATIONS.ANNOT_Point_t s)
	{
		out.write_long(s.x);
		out.write_long(s.y);
	}
}
