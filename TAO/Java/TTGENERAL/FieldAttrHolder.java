package TTGENERAL;

/**
 *	Generated from IDL definition of struct "FieldAttr"
 *	@author JacORB IDL compiler 
 */

public final class FieldAttrHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTGENERAL.FieldAttr value;

	public FieldAttrHolder ()
	{
	}
	public FieldAttrHolder(final TTGENERAL.FieldAttr initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTGENERAL.FieldAttrHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTGENERAL.FieldAttrHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTGENERAL.FieldAttrHelper.write(_out, value);
	}
}
