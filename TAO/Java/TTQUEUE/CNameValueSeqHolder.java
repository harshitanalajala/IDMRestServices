package TTQUEUE;

import TTQUEUE.CNameValueSeqHelper;

/**
 *	Generated from IDL definition of alias "CNameValueSeq"
 *	@author JacORB IDL compiler 
 */

public final class CNameValueSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTQUEUE.CNameValue[] value;

	public CNameValueSeqHolder ()
	{
	}
	public CNameValueSeqHolder (final TTQUEUE.CNameValue[] initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return CNameValueSeqHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CNameValueSeqHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		CNameValueSeqHelper.write (out,value);
	}
}
