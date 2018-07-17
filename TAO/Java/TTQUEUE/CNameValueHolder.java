package TTQUEUE;

/**
 *	Generated from IDL definition of struct "CNameValue"
 *	@author JacORB IDL compiler 
 */

public final class CNameValueHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTQUEUE.CNameValue value;

	public CNameValueHolder ()
	{
	}
	public CNameValueHolder(final TTQUEUE.CNameValue initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTQUEUE.CNameValueHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTQUEUE.CNameValueHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTQUEUE.CNameValueHelper.write(_out, value);
	}
}
