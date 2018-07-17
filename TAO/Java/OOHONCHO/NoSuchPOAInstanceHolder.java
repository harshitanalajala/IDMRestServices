package OOHONCHO;

/**
 *	Generated from IDL definition of exception "NoSuchPOAInstance"
 *	@author JacORB IDL compiler 
 */

public final class NoSuchPOAInstanceHolder
	implements org.omg.CORBA.portable.Streamable
{
	public OOHONCHO.NoSuchPOAInstance value;

	public NoSuchPOAInstanceHolder ()
	{
	}
	public NoSuchPOAInstanceHolder(final OOHONCHO.NoSuchPOAInstance initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return OOHONCHO.NoSuchPOAInstanceHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = OOHONCHO.NoSuchPOAInstanceHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		OOHONCHO.NoSuchPOAInstanceHelper.write(_out, value);
	}
}
