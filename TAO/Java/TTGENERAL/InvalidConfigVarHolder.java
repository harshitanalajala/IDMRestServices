package TTGENERAL;

/**
 *	Generated from IDL definition of exception "InvalidConfigVar"
 *	@author JacORB IDL compiler 
 */

public final class InvalidConfigVarHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTGENERAL.InvalidConfigVar value;

	public InvalidConfigVarHolder ()
	{
	}
	public InvalidConfigVarHolder(final TTGENERAL.InvalidConfigVar initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTGENERAL.InvalidConfigVarHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTGENERAL.InvalidConfigVarHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTGENERAL.InvalidConfigVarHelper.write(_out, value);
	}
}
