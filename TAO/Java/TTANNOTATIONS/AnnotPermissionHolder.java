package TTANNOTATIONS;

import TTANNOTATIONS.AnnotPermission;
import TTANNOTATIONS.AnnotPermissionHelper;

/**
 *	Generated from IDL definition of enum "AnnotPermission"
 *	@author JacORB IDL compiler 
 */

public final class AnnotPermissionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public AnnotPermission value;

	public AnnotPermissionHolder ()
	{
	}
	public AnnotPermissionHolder (final AnnotPermission initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return AnnotPermissionHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = AnnotPermissionHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		AnnotPermissionHelper.write (out,value);
	}
}
