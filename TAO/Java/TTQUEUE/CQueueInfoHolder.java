package TTQUEUE;

/**
 *	Generated from IDL definition of struct "CQueueInfo"
 *	@author JacORB IDL compiler 
 */

public final class CQueueInfoHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTQUEUE.CQueueInfo value;

	public CQueueInfoHolder ()
	{
	}
	public CQueueInfoHolder(final TTQUEUE.CQueueInfo initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTQUEUE.CQueueInfoHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTQUEUE.CQueueInfoHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTQUEUE.CQueueInfoHelper.write(_out, value);
	}
}
