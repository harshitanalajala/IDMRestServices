package TTLIBRARY;

/**
 *	Generated from IDL definition of exception "DatabaseException"
 *	@author JacORB IDL compiler 
 */

public final class DatabaseExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTLIBRARY.DatabaseException value;

	public DatabaseExceptionHolder ()
	{
	}
	public DatabaseExceptionHolder(final TTLIBRARY.DatabaseException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTLIBRARY.DatabaseExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTLIBRARY.DatabaseExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTLIBRARY.DatabaseExceptionHelper.write(_out, value);
	}
}
