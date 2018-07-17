package TTIMPORT;

import TTIMPORT.TypeMethod;

/**
 *	Generated from IDL definition of enum "TypeMethod"
 *	@author JacORB IDL compiler 
 */

public final class TypeMethodHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_enum_tc(TTIMPORT.TypeMethodHelper.id(),"TypeMethod",new String[]{"DOSEXT","MIME","FORMATKEY"});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTIMPORT.TypeMethod s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTIMPORT.TypeMethod extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTIMPORT/TypeMethod:1.0";
	}
	public static TypeMethod read (final org.omg.CORBA.portable.InputStream in)
	{
		return TypeMethod.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final TypeMethod s)
	{
		out.write_long(s.value());
	}
}
