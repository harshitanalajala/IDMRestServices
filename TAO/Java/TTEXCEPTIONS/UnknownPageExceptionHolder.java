package TTEXCEPTIONS;

/**
 *	Generated from IDL definition of exception "UnknownPageException"
 *	@author JacORB IDL compiler 
 */

public final class UnknownPageExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTEXCEPTIONS.UnknownPageException value;

	public UnknownPageExceptionHolder ()
	{
	}
	public UnknownPageExceptionHolder(final TTEXCEPTIONS.UnknownPageException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTEXCEPTIONS.UnknownPageExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTEXCEPTIONS.UnknownPageExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTEXCEPTIONS.UnknownPageExceptionHelper.write(_out, value);
	}
}
