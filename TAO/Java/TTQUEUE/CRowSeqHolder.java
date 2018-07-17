package TTQUEUE;

import TTQUEUE.CRowSeqHelper;

/**
 *	Generated from IDL definition of alias "CRowSeq"
 *	@author JacORB IDL compiler 
 */

public final class CRowSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTQUEUE.CNameValue[][] value;

	public CRowSeqHolder ()
	{
	}
	public CRowSeqHolder (final TTQUEUE.CNameValue[][] initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return CRowSeqHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CRowSeqHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		CRowSeqHelper.write (out,value);
	}
}
