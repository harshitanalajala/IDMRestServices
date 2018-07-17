package TTANNOTATIONS;

/**
 *	Generated from IDL definition of exception "AnnotationException"
 *	@author JacORB IDL compiler 
 */

public final class AnnotationExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTANNOTATIONS.AnnotationException value;

	public AnnotationExceptionHolder ()
	{
	}
	public AnnotationExceptionHolder(final TTANNOTATIONS.AnnotationException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTANNOTATIONS.AnnotationExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTANNOTATIONS.AnnotationExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTANNOTATIONS.AnnotationExceptionHelper.write(_out, value);
	}
}
