package TTQUEUE;

import TTQUEUE.CStringSeqHelper;

/**
 *	Generated from IDL definition of alias "CStringSeq"
 *	@author JacORB IDL compiler 
 */

public final class CStringSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public java.lang.String[] value;

	public CStringSeqHolder ()
	{
	}
	public CStringSeqHolder (final java.lang.String[] initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return CStringSeqHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CStringSeqHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		CStringSeqHelper.write (out,value);
	}
}
