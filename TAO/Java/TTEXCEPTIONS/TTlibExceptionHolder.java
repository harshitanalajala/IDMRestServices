package TTEXCEPTIONS;

/**
 *	Generated from IDL definition of exception "TTlibException"
 *	@author JacORB IDL compiler 
 */

public final class TTlibExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTEXCEPTIONS.TTlibException value;

	public TTlibExceptionHolder ()
	{
	}
	public TTlibExceptionHolder(final TTEXCEPTIONS.TTlibException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTEXCEPTIONS.TTlibExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTEXCEPTIONS.TTlibExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTEXCEPTIONS.TTlibExceptionHelper.write(_out, value);
	}
}
