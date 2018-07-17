package TTANNOTATIONS;

import TTANNOTATIONS.StructType;
import TTANNOTATIONS.StructTypeHelper;

/**
 *	Generated from IDL definition of enum "StructType"
 *	@author JacORB IDL compiler 
 */

public final class StructTypeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public StructType value;

	public StructTypeHolder ()
	{
	}
	public StructTypeHolder (final StructType initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return StructTypeHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = StructTypeHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		StructTypeHelper.write (out,value);
	}
}
