package TTRETRIEVAL;


/**
 *	Generated from IDL definition of exception "UnknownSubpageException"
 *	@author JacORB IDL compiler 
 */

public final class UnknownSubpageExceptionHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_exception_tc(TTRETRIEVAL.UnknownSubpageExceptionHelper.id(),"UnknownSubpageException",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("mErrorCode", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("mErrorDescription", org.omg.CORBA.ORB.init().create_string_tc(0), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTRETRIEVAL.UnknownSubpageException s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTRETRIEVAL.UnknownSubpageException extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTRETRIEVAL/UnknownSubpageException:1.0";
	}
	public static TTRETRIEVAL.UnknownSubpageException read (final org.omg.CORBA.portable.InputStream in)
	{
		TTRETRIEVAL.UnknownSubpageException result = new TTRETRIEVAL.UnknownSubpageException();
		if (!in.read_string().equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id");
		result.mErrorCode=in.read_long();
		result.mErrorDescription=in.read_string();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTRETRIEVAL.UnknownSubpageException s)
	{
		out.write_string(id());
		out.write_long(s.mErrorCode);
		out.write_string(s.mErrorDescription);
	}
}
