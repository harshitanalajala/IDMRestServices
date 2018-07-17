package TTLIBRARY;

/**
 *	Generated from IDL definition of struct "CheckOutDetail"
 *	@author JacORB IDL compiler 
 */

public final class CheckOutDetailHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTLIBRARY.CheckOutDetail value;

	public CheckOutDetailHolder ()
	{
	}
	public CheckOutDetailHolder(final TTLIBRARY.CheckOutDetail initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTLIBRARY.CheckOutDetailHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTLIBRARY.CheckOutDetailHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTLIBRARY.CheckOutDetailHelper.write(_out, value);
	}
}
