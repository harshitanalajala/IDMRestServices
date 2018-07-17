package TTQUEUE;

/**
 *	Generated from IDL definition of exception "MovedException"
 *	@author JacORB IDL compiler 
 */

public final class MovedExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTQUEUE.MovedException value;

	public MovedExceptionHolder ()
	{
	}
	public MovedExceptionHolder(final TTQUEUE.MovedException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTQUEUE.MovedExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTQUEUE.MovedExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTQUEUE.MovedExceptionHelper.write(_out, value);
	}
}
