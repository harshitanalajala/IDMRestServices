package TTQUEUE;

/**
 *	Generated from IDL definition of struct "CQueueVarDef"
 *	@author JacORB IDL compiler 
 */

public final class CQueueVarDefHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTQUEUE.CQueueVarDef value;

	public CQueueVarDefHolder ()
	{
	}
	public CQueueVarDefHolder(final TTQUEUE.CQueueVarDef initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTQUEUE.CQueueVarDefHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTQUEUE.CQueueVarDefHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTQUEUE.CQueueVarDefHelper.write(_out, value);
	}
}
