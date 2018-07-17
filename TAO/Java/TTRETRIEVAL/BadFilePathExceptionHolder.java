package TTRETRIEVAL;

/**
 *	Generated from IDL definition of exception "BadFilePathException"
 *	@author JacORB IDL compiler 
 */

public final class BadFilePathExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTRETRIEVAL.BadFilePathException value;

	public BadFilePathExceptionHolder ()
	{
	}
	public BadFilePathExceptionHolder(final TTRETRIEVAL.BadFilePathException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTRETRIEVAL.BadFilePathExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTRETRIEVAL.BadFilePathExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTRETRIEVAL.BadFilePathExceptionHelper.write(_out, value);
	}
}
