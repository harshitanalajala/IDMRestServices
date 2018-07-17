package TTQUEUE;

/**
 *	Generated from IDL definition of alias "CQueueVarSeq"
 *	@author JacORB IDL compiler 
 */

public final class CQueueVarSeqHelper
{
	private static org.omg.CORBA.TypeCode _type = null;

	public static void insert (org.omg.CORBA.Any any, TTQUEUE.CQueueVarDef[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static TTQUEUE.CQueueVarDef[] extract (final org.omg.CORBA.Any any)
	{
		return read (any.create_input_stream ());
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_alias_tc(TTQUEUE.CQueueVarSeqHelper.id(), "CQueueVarSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, TTQUEUE.CQueueVarDefHelper.type()));
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:TTQUEUE/CQueueVarSeq:1.0";
	}
	public static TTQUEUE.CQueueVarDef[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		TTQUEUE.CQueueVarDef[] _result;
		int _l_result4 = _in.read_long();
		_result = new TTQUEUE.CQueueVarDef[_l_result4];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=TTQUEUE.CQueueVarDefHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, TTQUEUE.CQueueVarDef[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			TTQUEUE.CQueueVarDefHelper.write(_out,_s[i]);
		}

	}
}
