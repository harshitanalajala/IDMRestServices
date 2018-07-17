package TTGENERAL;

/**
 *	Generated from IDL definition of exception "UnknownField"
 *	@author JacORB IDL compiler 
 */

public final class UnknownFieldHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTGENERAL.UnknownField value;

	public UnknownFieldHolder ()
	{
	}
	public UnknownFieldHolder(final TTGENERAL.UnknownField initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTGENERAL.UnknownFieldHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTGENERAL.UnknownFieldHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTGENERAL.UnknownFieldHelper.write(_out, value);
	}
}
