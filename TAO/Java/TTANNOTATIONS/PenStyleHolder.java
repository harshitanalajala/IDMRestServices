package TTANNOTATIONS;

import TTANNOTATIONS.PenStyle;
import TTANNOTATIONS.PenStyleHelper;

/**
 *	Generated from IDL definition of enum "PenStyle"
 *	@author JacORB IDL compiler 
 */

public final class PenStyleHolder
	implements org.omg.CORBA.portable.Streamable
{
	public PenStyle value;

	public PenStyleHolder ()
	{
	}
	public PenStyleHolder (final PenStyle initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return PenStyleHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = PenStyleHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		PenStyleHelper.write (out,value);
	}
}
