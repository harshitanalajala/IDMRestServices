package TTLIBRARY;

/**
 *	Generated from IDL definition of exception "NotOwnerException"
 *	@author JacORB IDL compiler 
 */

public final class NotOwnerExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTLIBRARY.NotOwnerException value;

	public NotOwnerExceptionHolder ()
	{
	}
	public NotOwnerExceptionHolder(final TTLIBRARY.NotOwnerException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTLIBRARY.NotOwnerExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTLIBRARY.NotOwnerExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTLIBRARY.NotOwnerExceptionHelper.write(_out, value);
	}
}
