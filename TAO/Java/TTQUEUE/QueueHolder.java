package TTQUEUE;

import TTQUEUE.Queue;
import TTQUEUE.QueueHelper;

/**
 *	Generated from IDL interface "Queue"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class QueueHolder	implements org.omg.CORBA.portable.Streamable{
	 public Queue value;
	public QueueHolder()
	{
	}
	public QueueHolder (final Queue initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type()
	{
		return QueueHelper.type();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = QueueHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		QueueHelper.write (_out,value);
	}
}
