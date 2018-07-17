package TTANNOTATIONS;

import TTANNOTATIONS.SpecialType;
import TTANNOTATIONS.SpecialTypeHelper;

/**
 *	Generated from IDL definition of enum "SpecialType"
 *	@author JacORB IDL compiler 
 */

public final class SpecialTypeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public SpecialType value;

	public SpecialTypeHolder ()
	{
	}
	public SpecialTypeHolder (final SpecialType initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return SpecialTypeHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = SpecialTypeHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		SpecialTypeHelper.write (out,value);
	}
}
