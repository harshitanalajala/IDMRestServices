package TTDOCTYPE;

import TTDOCTYPE.DTAccessActions;
import TTDOCTYPE.DTAccessActionsHelper;

/**
 *	Generated from IDL definition of enum "DTAccessActions"
 *	@author JacORB IDL compiler 
 */

public final class DTAccessActionsHolder
	implements org.omg.CORBA.portable.Streamable
{
	public DTAccessActions value;

	public DTAccessActionsHolder ()
	{
	}
	public DTAccessActionsHolder (final DTAccessActions initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return DTAccessActionsHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DTAccessActionsHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		DTAccessActionsHelper.write (out,value);
	}
}
