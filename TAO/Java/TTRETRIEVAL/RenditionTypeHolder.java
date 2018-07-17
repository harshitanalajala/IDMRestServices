package TTRETRIEVAL;

/**
 *	Generated from IDL definition of struct "RenditionType"
 *	@author JacORB IDL compiler 
 */

public final class RenditionTypeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTRETRIEVAL.RenditionType value;

	public RenditionTypeHolder ()
	{
	}
	public RenditionTypeHolder(final TTRETRIEVAL.RenditionType initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTRETRIEVAL.RenditionTypeHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTRETRIEVAL.RenditionTypeHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTRETRIEVAL.RenditionTypeHelper.write(_out, value);
	}
}
