package TTQUEUE;

/**
 *	Generated from IDL definition of exception "BadNameValueException"
 *	@author JacORB IDL compiler 
 */

public final class BadNameValueExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTQUEUE.BadNameValueException value;

	public BadNameValueExceptionHolder ()
	{
	}
	public BadNameValueExceptionHolder(final TTQUEUE.BadNameValueException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTQUEUE.BadNameValueExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTQUEUE.BadNameValueExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTQUEUE.BadNameValueExceptionHelper.write(_out, value);
	}
}
