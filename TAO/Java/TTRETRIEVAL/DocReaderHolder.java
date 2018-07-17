package TTRETRIEVAL;

import TTRETRIEVAL.DocReader;
import TTRETRIEVAL.DocReaderHelper;

/**
 *	Generated from IDL interface "DocReader"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class DocReaderHolder	implements org.omg.CORBA.portable.Streamable{
	 public DocReader value;
	public DocReaderHolder()
	{
	}
	public DocReaderHolder (final DocReader initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type()
	{
		return DocReaderHelper.type();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DocReaderHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		DocReaderHelper.write (_out,value);
	}
}
