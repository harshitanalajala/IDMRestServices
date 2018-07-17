package TTIMPORT;

/**
 *	Generated from IDL definition of exception "InvalidAppException"
 *	@author JacORB IDL compiler 
 */

public final class InvalidAppExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTIMPORT.InvalidAppException value;

	public InvalidAppExceptionHolder ()
	{
	}
	public InvalidAppExceptionHolder(final TTIMPORT.InvalidAppException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTIMPORT.InvalidAppExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTIMPORT.InvalidAppExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTIMPORT.InvalidAppExceptionHelper.write(_out, value);
	}
}
