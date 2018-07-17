package TTREPLOGGER;

/**
 *	Generated from IDL definition of exception "BadEventType"
 *	@author JacORB IDL compiler 
 */

public final class BadEventTypeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTREPLOGGER.BadEventType value;

	public BadEventTypeHolder ()
	{
	}
	public BadEventTypeHolder(final TTREPLOGGER.BadEventType initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTREPLOGGER.BadEventTypeHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTREPLOGGER.BadEventTypeHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTREPLOGGER.BadEventTypeHelper.write(_out, value);
	}
}
