package TTIMPORT;

import TTIMPORT.TypeMethod;
import TTIMPORT.TypeMethodHelper;

/**
 *	Generated from IDL definition of enum "TypeMethod"
 *	@author JacORB IDL compiler 
 */

public final class TypeMethodHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TypeMethod value;

	public TypeMethodHolder ()
	{
	}
	public TypeMethodHolder (final TypeMethod initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TypeMethodHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = TypeMethodHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		TypeMethodHelper.write (out,value);
	}
}
