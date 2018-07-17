package TTANNOTATIONS;


/**
 *	Generated from IDL definition of struct "ANNOT_Annotation_t"
 *	@author JacORB IDL compiler 
 */

public final class ANNOT_Annotation_tHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(TTANNOTATIONS.ANNOT_Annotation_tHelper.id(),"ANNOT_Annotation_t",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("status", TTANNOTATIONS.StatusTypeHelper.type(), null),new org.omg.CORBA.StructMember("access", TTANNOTATIONS.AnnotPermissionHelper.type(), null),new org.omg.CORBA.StructMember("creatorUser", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("creatorGroup", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("creationTimestamp", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("lastModifiedUser", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("lastModifiedGroup", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("lastModifiedTimestamp", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("colour", TTANNOTATIONS.ANNOT_Colour_tHelper.type(), null),new org.omg.CORBA.StructMember("annotation", TTANNOTATIONS.ANNOT_Base_tHelper.type(), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTANNOTATIONS.ANNOT_Annotation_t s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTANNOTATIONS.ANNOT_Annotation_t extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTANNOTATIONS/ANNOT_Annotation_t:1.0";
	}
	public static TTANNOTATIONS.ANNOT_Annotation_t read (final org.omg.CORBA.portable.InputStream in)
	{
		TTANNOTATIONS.ANNOT_Annotation_t result = new TTANNOTATIONS.ANNOT_Annotation_t();
		result.status=TTANNOTATIONS.StatusTypeHelper.read(in);
		result.access=TTANNOTATIONS.AnnotPermissionHelper.read(in);
		result.creatorUser=in.read_string();
		result.creatorGroup=in.read_string();
		result.creationTimestamp=in.read_long();
		result.lastModifiedUser=in.read_string();
		result.lastModifiedGroup=in.read_string();
		result.lastModifiedTimestamp=in.read_long();
		result.colour=TTANNOTATIONS.ANNOT_Colour_tHelper.read(in);
		result.annotation=TTANNOTATIONS.ANNOT_Base_tHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTANNOTATIONS.ANNOT_Annotation_t s)
	{
		TTANNOTATIONS.StatusTypeHelper.write(out,s.status);
		TTANNOTATIONS.AnnotPermissionHelper.write(out,s.access);
		out.write_string(s.creatorUser);
		out.write_string(s.creatorGroup);
		out.write_long(s.creationTimestamp);
		out.write_string(s.lastModifiedUser);
		out.write_string(s.lastModifiedGroup);
		out.write_long(s.lastModifiedTimestamp);
		TTANNOTATIONS.ANNOT_Colour_tHelper.write(out,s.colour);
		TTANNOTATIONS.ANNOT_Base_tHelper.write(out,s.annotation);
	}
}
