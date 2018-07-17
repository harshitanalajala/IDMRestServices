package TTRETRIEVAL;

/**
 *	Generated from IDL definition of exception "BadPagesException"
 *	@author JacORB IDL compiler 
 */

public final class BadPagesExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTRETRIEVAL.BadPagesException value;

	public BadPagesExceptionHolder ()
	{
	}
	public BadPagesExceptionHolder(final TTRETRIEVAL.BadPagesException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTRETRIEVAL.BadPagesExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTRETRIEVAL.BadPagesExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTRETRIEVAL.BadPagesExceptionHelper.write(_out, value);
	}
}
