package TTQUEUE;

/**
 *	Generated from IDL definition of exception "BadOffsetException"
 *	@author JacORB IDL compiler 
 */

public final class BadOffsetExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTQUEUE.BadOffsetException value;

	public BadOffsetExceptionHolder ()
	{
	}
	public BadOffsetExceptionHolder(final TTQUEUE.BadOffsetException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTQUEUE.BadOffsetExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTQUEUE.BadOffsetExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTQUEUE.BadOffsetExceptionHelper.write(_out, value);
	}
}
