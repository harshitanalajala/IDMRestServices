package TTGENERAL;


/**
 *	Generated from IDL definition of struct "FieldAttr"
 *	@author JacORB IDL compiler 
 */

public final class FieldAttrHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(TTGENERAL.FieldAttrHelper.id(),"FieldAttr",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("mFieldName", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("mFieldValue", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("mFieldType", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("mFieldLen", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTGENERAL.FieldAttr s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTGENERAL.FieldAttr extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTGENERAL/FieldAttr:1.0";
	}
	public static TTGENERAL.FieldAttr read (final org.omg.CORBA.portable.InputStream in)
	{
		TTGENERAL.FieldAttr result = new TTGENERAL.FieldAttr();
		result.mFieldName=in.read_string();
		result.mFieldValue=in.read_string();
		result.mFieldType=in.read_long();
		result.mFieldLen=in.read_long();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTGENERAL.FieldAttr s)
	{
		out.write_string(s.mFieldName);
		out.write_string(s.mFieldValue);
		out.write_long(s.mFieldType);
		out.write_long(s.mFieldLen);
	}
}
