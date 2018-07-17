package TTQUEUE;

import TTQUEUE.CULongSeqHelper;

/**
 *	Generated from IDL definition of alias "CULongSeq"
 *	@author JacORB IDL compiler 
 */

public final class CULongSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public int[] value;

	public CULongSeqHolder ()
	{
	}
	public CULongSeqHolder (final int[] initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return CULongSeqHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CULongSeqHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		CULongSeqHelper.write (out,value);
	}
}
