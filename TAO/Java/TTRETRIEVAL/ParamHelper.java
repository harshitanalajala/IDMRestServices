package TTRETRIEVAL;


/**
 *	Generated from IDL definition of struct "Param"
 *	@author JacORB IDL compiler 
 */

public final class ParamHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(TTRETRIEVAL.ParamHelper.id(),"Param",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("mParamName", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("mParamValue", org.omg.CORBA.ORB.init().create_string_tc(0), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTRETRIEVAL.Param s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTRETRIEVAL.Param extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTRETRIEVAL/Param:1.0";
	}
	public static TTRETRIEVAL.Param read (final org.omg.CORBA.portable.InputStream in)
	{
		TTRETRIEVAL.Param result = new TTRETRIEVAL.Param();
		result.mParamName=in.read_string();
		result.mParamValue=in.read_string();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTRETRIEVAL.Param s)
	{
		out.write_string(s.mParamName);
		out.write_string(s.mParamValue);
	}
}
