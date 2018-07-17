package TTLIBRARY;

/**
 *	Generated from IDL definition of alias "NameValueSeq"
 *	@author JacORB IDL compiler 
 */

public final class NameValueSeqHelper
{
	private static org.omg.CORBA.TypeCode _type = null;

	public static void insert (org.omg.CORBA.Any any, TTLIBRARY.NameValue[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static TTLIBRARY.NameValue[] extract (final org.omg.CORBA.Any any)
	{
		return read (any.create_input_stream ());
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_alias_tc(TTLIBRARY.NameValueSeqHelper.id(), "NameValueSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, TTLIBRARY.NameValueHelper.type()));
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:TTLIBRARY/NameValueSeq:1.0";
	}
	public static TTLIBRARY.NameValue[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		TTLIBRARY.NameValue[] _result;
		int _l_result0 = _in.read_long();
		_result = new TTLIBRARY.NameValue[_l_result0];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=TTLIBRARY.NameValueHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, TTLIBRARY.NameValue[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			TTLIBRARY.NameValueHelper.write(_out,_s[i]);
		}

	}
}
