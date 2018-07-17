package TTEXCEPTIONS;

/**
 *	Generated from IDL definition of exception "DocPermException"
 *	@author JacORB IDL compiler 
 */

public final class DocPermExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTEXCEPTIONS.DocPermException value;

	public DocPermExceptionHolder ()
	{
	}
	public DocPermExceptionHolder(final TTEXCEPTIONS.DocPermException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTEXCEPTIONS.DocPermExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTEXCEPTIONS.DocPermExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTEXCEPTIONS.DocPermExceptionHelper.write(_out, value);
	}
}
