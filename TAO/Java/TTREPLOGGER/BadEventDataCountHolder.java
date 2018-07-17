package TTREPLOGGER;

/**
 *	Generated from IDL definition of exception "BadEventDataCount"
 *	@author JacORB IDL compiler 
 */

public final class BadEventDataCountHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTREPLOGGER.BadEventDataCount value;

	public BadEventDataCountHolder ()
	{
	}
	public BadEventDataCountHolder(final TTREPLOGGER.BadEventDataCount initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTREPLOGGER.BadEventDataCountHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTREPLOGGER.BadEventDataCountHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTREPLOGGER.BadEventDataCountHelper.write(_out, value);
	}
}
