package TTEXCEPTIONS;

/**
 *	Generated from IDL definition of exception "AppPermException"
 *	@author JacORB IDL compiler 
 */

public final class AppPermExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTEXCEPTIONS.AppPermException value;

	public AppPermExceptionHolder ()
	{
	}
	public AppPermExceptionHolder(final TTEXCEPTIONS.AppPermException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTEXCEPTIONS.AppPermExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTEXCEPTIONS.AppPermExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTEXCEPTIONS.AppPermExceptionHelper.write(_out, value);
	}
}
