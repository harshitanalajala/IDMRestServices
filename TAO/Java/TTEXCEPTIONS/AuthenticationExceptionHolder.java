package TTEXCEPTIONS;

/**
 *	Generated from IDL definition of exception "AuthenticationException"
 *	@author JacORB IDL compiler 
 */

public final class AuthenticationExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTEXCEPTIONS.AuthenticationException value;

	public AuthenticationExceptionHolder ()
	{
	}
	public AuthenticationExceptionHolder(final TTEXCEPTIONS.AuthenticationException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTEXCEPTIONS.AuthenticationExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTEXCEPTIONS.AuthenticationExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTEXCEPTIONS.AuthenticationExceptionHelper.write(_out, value);
	}
}
