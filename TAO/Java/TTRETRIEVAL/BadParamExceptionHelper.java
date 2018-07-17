package TTRETRIEVAL;


/**
 *	Generated from IDL definition of exception "BadParamException"
 *	@author JacORB IDL compiler 
 */

public final class BadParamExceptionHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_exception_tc(TTRETRIEVAL.BadParamExceptionHelper.id(),"BadParamException",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("mErrorCode", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("mErrorDescription", org.omg.CORBA.ORB.init().create_string_tc(0), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTRETRIEVAL.BadParamException s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTRETRIEVAL.BadParamException extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTRETRIEVAL/BadParamException:1.0";
	}
	public static TTRETRIEVAL.BadParamException read (final org.omg.CORBA.portable.InputStream in)
	{
		TTRETRIEVAL.BadParamException result = new TTRETRIEVAL.BadParamException();
		if (!in.read_string().equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id");
		result.mErrorCode=in.read_long();
		result.mErrorDescription=in.read_string();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTRETRIEVAL.BadParamException s)
	{
		out.write_string(id());
		out.write_long(s.mErrorCode);
		out.write_string(s.mErrorDescription);
	}
}
