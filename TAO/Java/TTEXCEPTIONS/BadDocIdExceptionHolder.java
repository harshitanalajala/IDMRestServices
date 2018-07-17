package TTEXCEPTIONS;

/**
 *	Generated from IDL definition of exception "BadDocIdException"
 *	@author JacORB IDL compiler 
 */

public final class BadDocIdExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTEXCEPTIONS.BadDocIdException value;

	public BadDocIdExceptionHolder ()
	{
	}
	public BadDocIdExceptionHolder(final TTEXCEPTIONS.BadDocIdException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTEXCEPTIONS.BadDocIdExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTEXCEPTIONS.BadDocIdExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTEXCEPTIONS.BadDocIdExceptionHelper.write(_out, value);
	}
}
