package TTQUEUE;

import TTQUEUE.CQueueEntrySeqHelper;

/**
 *	Generated from IDL definition of alias "CQueueEntrySeq"
 *	@author JacORB IDL compiler 
 */

public final class CQueueEntrySeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTQUEUE.CQueueEntry[] value;

	public CQueueEntrySeqHolder ()
	{
	}
	public CQueueEntrySeqHolder (final TTQUEUE.CQueueEntry[] initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return CQueueEntrySeqHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CQueueEntrySeqHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		CQueueEntrySeqHelper.write (out,value);
	}
}
