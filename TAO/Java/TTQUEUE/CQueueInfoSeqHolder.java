package TTQUEUE;

import TTQUEUE.CQueueInfoSeqHelper;

/**
 *	Generated from IDL definition of alias "CQueueInfoSeq"
 *	@author JacORB IDL compiler 
 */

public final class CQueueInfoSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTQUEUE.CQueueInfo[] value;

	public CQueueInfoSeqHolder ()
	{
	}
	public CQueueInfoSeqHolder (final TTQUEUE.CQueueInfo[] initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return CQueueInfoSeqHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CQueueInfoSeqHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		CQueueInfoSeqHelper.write (out,value);
	}
}
