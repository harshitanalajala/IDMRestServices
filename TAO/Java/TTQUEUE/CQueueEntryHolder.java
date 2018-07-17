package TTQUEUE;

/**
 *	Generated from IDL definition of struct "CQueueEntry"
 *	@author JacORB IDL compiler 
 */

public final class CQueueEntryHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTQUEUE.CQueueEntry value;

	public CQueueEntryHolder ()
	{
	}
	public CQueueEntryHolder(final TTQUEUE.CQueueEntry initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTQUEUE.CQueueEntryHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTQUEUE.CQueueEntryHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTQUEUE.CQueueEntryHelper.write(_out, value);
	}
}
