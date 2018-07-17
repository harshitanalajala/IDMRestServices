package TTLIBRARY;

/**
 *	Generated from IDL definition of struct "NameValue"
 *	@author JacORB IDL compiler 
 */

public final class NameValueHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTLIBRARY.NameValue value;

	public NameValueHolder ()
	{
	}
	public NameValueHolder(final TTLIBRARY.NameValue initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTLIBRARY.NameValueHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTLIBRARY.NameValueHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTLIBRARY.NameValueHelper.write(_out, value);
	}
}
