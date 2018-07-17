package TTQUEUE;

/**
 *	Generated from IDL definition of alias "CQueueEntrySeq"
 *	@author JacORB IDL compiler 
 */

public final class CQueueEntrySeqHelper
{
	private static org.omg.CORBA.TypeCode _type = null;

	public static void insert (org.omg.CORBA.Any any, TTQUEUE.CQueueEntry[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static TTQUEUE.CQueueEntry[] extract (final org.omg.CORBA.Any any)
	{
		return read (any.create_input_stream ());
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_alias_tc(TTQUEUE.CQueueEntrySeqHelper.id(), "CQueueEntrySeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, TTQUEUE.CQueueEntryHelper.type()));
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:TTQUEUE/CQueueEntrySeq:1.0";
	}
	public static TTQUEUE.CQueueEntry[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		TTQUEUE.CQueueEntry[] _result;
		int _l_result6 = _in.read_long();
		_result = new TTQUEUE.CQueueEntry[_l_result6];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=TTQUEUE.CQueueEntryHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, TTQUEUE.CQueueEntry[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			TTQUEUE.CQueueEntryHelper.write(_out,_s[i]);
		}

	}
}
