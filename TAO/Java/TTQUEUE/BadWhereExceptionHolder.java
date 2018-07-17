package TTQUEUE;

/**
 *	Generated from IDL definition of exception "BadWhereException"
 *	@author JacORB IDL compiler 
 */

public final class BadWhereExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTQUEUE.BadWhereException value;

	public BadWhereExceptionHolder ()
	{
	}
	public BadWhereExceptionHolder(final TTQUEUE.BadWhereException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTQUEUE.BadWhereExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTQUEUE.BadWhereExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTQUEUE.BadWhereExceptionHelper.write(_out, value);
	}
}
