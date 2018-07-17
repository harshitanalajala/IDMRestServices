package TTQUEUE;


/**
 *	Generated from IDL definition of exception "BadNameValueException"
 *	@author JacORB IDL compiler 
 */

public final class BadNameValueExceptionHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_exception_tc(TTQUEUE.BadNameValueExceptionHelper.id(),"BadNameValueException",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("errorCode", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("errorDescription", org.omg.CORBA.ORB.init().create_string_tc(0), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTQUEUE.BadNameValueException s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTQUEUE.BadNameValueException extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTQUEUE/BadNameValueException:1.0";
	}
	public static TTQUEUE.BadNameValueException read (final org.omg.CORBA.portable.InputStream in)
	{
		TTQUEUE.BadNameValueException result = new TTQUEUE.BadNameValueException();
		if (!in.read_string().equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id");
		result.errorCode=in.read_long();
		result.errorDescription=in.read_string();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTQUEUE.BadNameValueException s)
	{
		out.write_string(id());
		out.write_long(s.errorCode);
		out.write_string(s.errorDescription);
	}
}
