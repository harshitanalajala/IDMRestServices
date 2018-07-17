package TTGENERAL;

import TTGENERAL.ApplicationTableHelper;

/**
 *	Generated from IDL definition of alias "ApplicationTable"
 *	@author JacORB IDL compiler 
 */

public final class ApplicationTableHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTGENERAL.ApplicationEntry[] value;

	public ApplicationTableHolder ()
	{
	}
	public ApplicationTableHolder (final TTGENERAL.ApplicationEntry[] initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return ApplicationTableHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ApplicationTableHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ApplicationTableHelper.write (out,value);
	}
}
