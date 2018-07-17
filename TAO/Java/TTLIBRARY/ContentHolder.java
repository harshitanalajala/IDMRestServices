package TTLIBRARY;

import TTLIBRARY.ContentHelper;

/**
 *	Generated from IDL definition of alias "Content"
 *	@author JacORB IDL compiler 
 */

public final class ContentHolder
	implements org.omg.CORBA.portable.Streamable
{
	public byte[] value;

	public ContentHolder ()
	{
	}
	public ContentHolder (final byte[] initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return ContentHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ContentHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ContentHelper.write (out,value);
	}
}
