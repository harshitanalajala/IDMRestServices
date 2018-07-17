package TTLIBRARY;

/**
 *	Generated from IDL definition of exception "NotLibAppException"
 *	@author JacORB IDL compiler 
 */

public final class NotLibAppExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTLIBRARY.NotLibAppException value;

	public NotLibAppExceptionHolder ()
	{
	}
	public NotLibAppExceptionHolder(final TTLIBRARY.NotLibAppException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTLIBRARY.NotLibAppExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTLIBRARY.NotLibAppExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTLIBRARY.NotLibAppExceptionHelper.write(_out, value);
	}
}
