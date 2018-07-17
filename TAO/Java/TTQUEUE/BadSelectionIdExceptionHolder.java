package TTQUEUE;

/**
 *	Generated from IDL definition of exception "BadSelectionIdException"
 *	@author JacORB IDL compiler 
 */

public final class BadSelectionIdExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTQUEUE.BadSelectionIdException value;

	public BadSelectionIdExceptionHolder ()
	{
	}
	public BadSelectionIdExceptionHolder(final TTQUEUE.BadSelectionIdException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTQUEUE.BadSelectionIdExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTQUEUE.BadSelectionIdExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTQUEUE.BadSelectionIdExceptionHelper.write(_out, value);
	}
}
