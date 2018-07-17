package TTQUEUE;

/**
 *	Generated from IDL definition of exception "NotLockedException"
 *	@author JacORB IDL compiler 
 */

public final class NotLockedExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTQUEUE.NotLockedException value;

	public NotLockedExceptionHolder ()
	{
	}
	public NotLockedExceptionHolder(final TTQUEUE.NotLockedException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTQUEUE.NotLockedExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTQUEUE.NotLockedExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTQUEUE.NotLockedExceptionHelper.write(_out, value);
	}
}
