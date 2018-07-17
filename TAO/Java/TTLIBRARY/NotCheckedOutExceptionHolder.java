package TTLIBRARY;

/**
 *	Generated from IDL definition of exception "NotCheckedOutException"
 *	@author JacORB IDL compiler 
 */

public final class NotCheckedOutExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTLIBRARY.NotCheckedOutException value;

	public NotCheckedOutExceptionHolder ()
	{
	}
	public NotCheckedOutExceptionHolder(final TTLIBRARY.NotCheckedOutException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTLIBRARY.NotCheckedOutExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTLIBRARY.NotCheckedOutExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTLIBRARY.NotCheckedOutExceptionHelper.write(_out, value);
	}
}
