package TTQUEUE;

/**
 *	Generated from IDL definition of exception "BadFieldNamesException"
 *	@author JacORB IDL compiler 
 */

public final class BadFieldNamesExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTQUEUE.BadFieldNamesException value;

	public BadFieldNamesExceptionHolder ()
	{
	}
	public BadFieldNamesExceptionHolder(final TTQUEUE.BadFieldNamesException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTQUEUE.BadFieldNamesExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTQUEUE.BadFieldNamesExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTQUEUE.BadFieldNamesExceptionHelper.write(_out, value);
	}
}
