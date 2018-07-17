package TTRETRIEVAL;

/**
 *	Generated from IDL definition of exception "BadParamException"
 *	@author JacORB IDL compiler 
 */

public final class BadParamExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTRETRIEVAL.BadParamException value;

	public BadParamExceptionHolder ()
	{
	}
	public BadParamExceptionHolder(final TTRETRIEVAL.BadParamException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTRETRIEVAL.BadParamExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTRETRIEVAL.BadParamExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTRETRIEVAL.BadParamExceptionHelper.write(_out, value);
	}
}
