package TTANNOTATIONS;

import TTANNOTATIONS.SecurityInfoHelper;

/**
 *	Generated from IDL definition of alias "SecurityInfo"
 *	@author JacORB IDL compiler 
 */

public final class SecurityInfoHolder
	implements org.omg.CORBA.portable.Streamable
{
	public byte[] value;

	public SecurityInfoHolder ()
	{
	}
	public SecurityInfoHolder (final byte[] initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return SecurityInfoHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = SecurityInfoHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		SecurityInfoHelper.write (out,value);
	}
}
