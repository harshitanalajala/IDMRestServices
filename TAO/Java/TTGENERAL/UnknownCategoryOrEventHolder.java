package TTGENERAL;

/**
 *	Generated from IDL definition of exception "UnknownCategoryOrEvent"
 *	@author JacORB IDL compiler 
 */

public final class UnknownCategoryOrEventHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTGENERAL.UnknownCategoryOrEvent value;

	public UnknownCategoryOrEventHolder ()
	{
	}
	public UnknownCategoryOrEventHolder(final TTGENERAL.UnknownCategoryOrEvent initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTGENERAL.UnknownCategoryOrEventHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTGENERAL.UnknownCategoryOrEventHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTGENERAL.UnknownCategoryOrEventHelper.write(_out, value);
	}
}
