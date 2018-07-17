package TTEXCEPTIONS;

/**
 *	Generated from IDL definition of exception "InvalidDSException"
 *	@author JacORB IDL compiler 
 */

public final class InvalidDSExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTEXCEPTIONS.InvalidDSException value;

	public InvalidDSExceptionHolder ()
	{
	}
	public InvalidDSExceptionHolder(final TTEXCEPTIONS.InvalidDSException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTEXCEPTIONS.InvalidDSExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTEXCEPTIONS.InvalidDSExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTEXCEPTIONS.InvalidDSExceptionHelper.write(_out, value);
	}
}
