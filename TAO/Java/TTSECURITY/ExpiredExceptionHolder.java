package TTSECURITY;

/**
 *	Generated from IDL definition of exception "ExpiredException"
 *	@author JacORB IDL compiler 
 */

public final class ExpiredExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTSECURITY.ExpiredException value;

	public ExpiredExceptionHolder ()
	{
	}
	public ExpiredExceptionHolder(final TTSECURITY.ExpiredException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTSECURITY.ExpiredExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTSECURITY.ExpiredExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTSECURITY.ExpiredExceptionHelper.write(_out, value);
	}
}
