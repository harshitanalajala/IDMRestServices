package TTSECURITY;

/**
 *	Generated from IDL definition of exception "NoAvailableException"
 *	@author JacORB IDL compiler 
 */

public final class NoAvailableExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTSECURITY.NoAvailableException value;

	public NoAvailableExceptionHolder ()
	{
	}
	public NoAvailableExceptionHolder(final TTSECURITY.NoAvailableException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTSECURITY.NoAvailableExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTSECURITY.NoAvailableExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTSECURITY.NoAvailableExceptionHelper.write(_out, value);
	}
}
