package TTANNOTATIONS;

import TTANNOTATIONS.StatusType;
import TTANNOTATIONS.StatusTypeHelper;

/**
 *	Generated from IDL definition of enum "StatusType"
 *	@author JacORB IDL compiler 
 */

public final class StatusTypeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public StatusType value;

	public StatusTypeHolder ()
	{
	}
	public StatusTypeHolder (final StatusType initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return StatusTypeHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = StatusTypeHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		StatusTypeHelper.write (out,value);
	}
}
