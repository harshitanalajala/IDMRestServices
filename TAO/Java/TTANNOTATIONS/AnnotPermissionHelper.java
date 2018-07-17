package TTANNOTATIONS;

import TTANNOTATIONS.AnnotPermission;

/**
 *	Generated from IDL definition of enum "AnnotPermission"
 *	@author JacORB IDL compiler 
 */

public final class AnnotPermissionHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_enum_tc(TTANNOTATIONS.AnnotPermissionHelper.id(),"AnnotPermission",new String[]{"C_ANNOT_HIDDEN","C_ANNOT_READ_ONLY","C_ANNOT_MODIFIABLE","C_ANNOT_DELETABLE"});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTANNOTATIONS.AnnotPermission s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTANNOTATIONS.AnnotPermission extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTANNOTATIONS/AnnotPermission:1.0";
	}
	public static AnnotPermission read (final org.omg.CORBA.portable.InputStream in)
	{
		return AnnotPermission.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final AnnotPermission s)
	{
		out.write_long(s.value());
	}
}
