package TTRETRIEVAL;

/**
 *	Generated from IDL definition of exception "UnknownSubpageException"
 *	@author JacORB IDL compiler 
 */

public final class UnknownSubpageExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTRETRIEVAL.UnknownSubpageException value;

	public UnknownSubpageExceptionHolder ()
	{
	}
	public UnknownSubpageExceptionHolder(final TTRETRIEVAL.UnknownSubpageException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTRETRIEVAL.UnknownSubpageExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTRETRIEVAL.UnknownSubpageExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTRETRIEVAL.UnknownSubpageExceptionHelper.write(_out, value);
	}
}
