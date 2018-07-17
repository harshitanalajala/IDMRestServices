package TTQUEUE;

/**
 *	Generated from IDL definition of alias "CNameValueSeq"
 *	@author JacORB IDL compiler 
 */

public final class CNameValueSeqHelper
{
	private static org.omg.CORBA.TypeCode _type = null;

	public static void insert (org.omg.CORBA.Any any, TTQUEUE.CNameValue[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static TTQUEUE.CNameValue[] extract (final org.omg.CORBA.Any any)
	{
		return read (any.create_input_stream ());
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_alias_tc(TTQUEUE.CNameValueSeqHelper.id(), "CNameValueSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, TTQUEUE.CNameValueHelper.type()));
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:TTQUEUE/CNameValueSeq:1.0";
	}
	public static TTQUEUE.CNameValue[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		TTQUEUE.CNameValue[] _result;
		int _l_result2 = _in.read_long();
		_result = new TTQUEUE.CNameValue[_l_result2];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=TTQUEUE.CNameValueHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, TTQUEUE.CNameValue[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			TTQUEUE.CNameValueHelper.write(_out,_s[i]);
		}

	}
}
