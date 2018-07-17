package TTIMPORT;

import TTIMPORT.DocumentDataHelper;

/**
 *	Generated from IDL definition of alias "DocumentData"
 *	@author JacORB IDL compiler 
 */

public final class DocumentDataHolder
	implements org.omg.CORBA.portable.Streamable
{
	public byte[] value;

	public DocumentDataHolder ()
	{
	}
	public DocumentDataHolder (final byte[] initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return DocumentDataHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DocumentDataHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		DocumentDataHelper.write (out,value);
	}
}
