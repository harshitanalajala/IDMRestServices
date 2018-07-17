package TTQUEUE;

import TTQUEUE.CQueueVarSeqHelper;

/**
 *	Generated from IDL definition of alias "CQueueVarSeq"
 *	@author JacORB IDL compiler 
 */

public final class CQueueVarSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTQUEUE.CQueueVarDef[] value;

	public CQueueVarSeqHolder ()
	{
	}
	public CQueueVarSeqHolder (final TTQUEUE.CQueueVarDef[] initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return CQueueVarSeqHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CQueueVarSeqHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		CQueueVarSeqHelper.write (out,value);
	}
}
