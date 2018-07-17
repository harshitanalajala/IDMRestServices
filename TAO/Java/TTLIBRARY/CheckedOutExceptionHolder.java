package TTLIBRARY;

/**
 *	Generated from IDL definition of exception "CheckedOutException"
 *	@author JacORB IDL compiler 
 */

public final class CheckedOutExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTLIBRARY.CheckedOutException value;

	public CheckedOutExceptionHolder ()
	{
	}
	public CheckedOutExceptionHolder(final TTLIBRARY.CheckedOutException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTLIBRARY.CheckedOutExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTLIBRARY.CheckedOutExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTLIBRARY.CheckedOutExceptionHelper.write(_out, value);
	}
}
