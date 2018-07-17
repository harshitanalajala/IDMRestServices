package TTEXCEPTIONS;

/**
 *	Generated from IDL definition of exception "GenericException"
 *	@author JacORB IDL compiler 
 */

public final class GenericExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTEXCEPTIONS.GenericException value;

	public GenericExceptionHolder ()
	{
	}
	public GenericExceptionHolder(final TTEXCEPTIONS.GenericException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTEXCEPTIONS.GenericExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTEXCEPTIONS.GenericExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTEXCEPTIONS.GenericExceptionHelper.write(_out, value);
	}
}
