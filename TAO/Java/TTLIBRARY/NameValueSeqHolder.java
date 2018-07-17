package TTLIBRARY;

import TTLIBRARY.NameValueSeqHelper;

/**
 *	Generated from IDL definition of alias "NameValueSeq"
 *	@author JacORB IDL compiler 
 */

public final class NameValueSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTLIBRARY.NameValue[] value;

	public NameValueSeqHolder ()
	{
	}
	public NameValueSeqHolder (final TTLIBRARY.NameValue[] initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return NameValueSeqHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = NameValueSeqHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		NameValueSeqHelper.write (out,value);
	}
}
