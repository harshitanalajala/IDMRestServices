package TTEXCEPTIONS;

/**
 *	Generated from IDL definition of exception "UnknownDocException"
 *	@author JacORB IDL compiler 
 */

public final class UnknownDocExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTEXCEPTIONS.UnknownDocException value;

	public UnknownDocExceptionHolder ()
	{
	}
	public UnknownDocExceptionHolder(final TTEXCEPTIONS.UnknownDocException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTEXCEPTIONS.UnknownDocExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTEXCEPTIONS.UnknownDocExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTEXCEPTIONS.UnknownDocExceptionHelper.write(_out, value);
	}
}
