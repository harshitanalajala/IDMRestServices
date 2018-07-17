package TTRETRIEVAL;

/**
 *	Generated from IDL definition of alias "ParamData"
 *	@author JacORB IDL compiler 
 */

public final class ParamDataHelper
{
	private static org.omg.CORBA.TypeCode _type = null;

	public static void insert (org.omg.CORBA.Any any, TTRETRIEVAL.Param[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static TTRETRIEVAL.Param[] extract (final org.omg.CORBA.Any any)
	{
		return read (any.create_input_stream ());
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_alias_tc(TTRETRIEVAL.ParamDataHelper.id(), "ParamData",org.omg.CORBA.ORB.init().create_sequence_tc(0, TTRETRIEVAL.ParamHelper.type()));
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:TTRETRIEVAL/ParamData:1.0";
	}
	public static TTRETRIEVAL.Param[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		TTRETRIEVAL.Param[] _result;
		int _l_result1 = _in.read_long();
		_result = new TTRETRIEVAL.Param[_l_result1];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=TTRETRIEVAL.ParamHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, TTRETRIEVAL.Param[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			TTRETRIEVAL.ParamHelper.write(_out,_s[i]);
		}

	}
}
