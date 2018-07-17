package TTQUEUE;

/**
 *	Generated from IDL definition of exception "LockFailureException"
 *	@author JacORB IDL compiler 
 */

public final class LockFailureExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTQUEUE.LockFailureException value;

	public LockFailureExceptionHolder ()
	{
	}
	public LockFailureExceptionHolder(final TTQUEUE.LockFailureException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTQUEUE.LockFailureExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTQUEUE.LockFailureExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTQUEUE.LockFailureExceptionHelper.write(_out, value);
	}
}
