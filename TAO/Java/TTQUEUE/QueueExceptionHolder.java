package TTQUEUE;

/**
 *	Generated from IDL definition of exception "QueueException"
 *	@author JacORB IDL compiler 
 */

public final class QueueExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTQUEUE.QueueException value;

	public QueueExceptionHolder ()
	{
	}
	public QueueExceptionHolder(final TTQUEUE.QueueException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTQUEUE.QueueExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTQUEUE.QueueExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTQUEUE.QueueExceptionHelper.write(_out, value);
	}
}
