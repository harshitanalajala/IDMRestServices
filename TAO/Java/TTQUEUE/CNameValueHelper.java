package TTQUEUE;


/**
 *	Generated from IDL definition of struct "CNameValue"
 *	@author JacORB IDL compiler 
 */

public final class CNameValueHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(TTQUEUE.CNameValueHelper.id(),"CNameValue",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().create_string_tc(0), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTQUEUE.CNameValue s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTQUEUE.CNameValue extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTQUEUE/CNameValue:1.0";
	}
	public static TTQUEUE.CNameValue read (final org.omg.CORBA.portable.InputStream in)
	{
		TTQUEUE.CNameValue result = new TTQUEUE.CNameValue();
		result.name=in.read_string();
		result.value=in.read_string();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTQUEUE.CNameValue s)
	{
		out.write_string(s.name);
		out.write_string(s.value);
	}
}
