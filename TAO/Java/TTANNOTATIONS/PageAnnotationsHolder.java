package TTANNOTATIONS;

/**
 *	Generated from IDL definition of struct "PageAnnotations"
 *	@author JacORB IDL compiler 
 */

public final class PageAnnotationsHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTANNOTATIONS.PageAnnotations value;

	public PageAnnotationsHolder ()
	{
	}
	public PageAnnotationsHolder(final TTANNOTATIONS.PageAnnotations initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTANNOTATIONS.PageAnnotationsHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTANNOTATIONS.PageAnnotationsHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTANNOTATIONS.PageAnnotationsHelper.write(_out, value);
	}
}
