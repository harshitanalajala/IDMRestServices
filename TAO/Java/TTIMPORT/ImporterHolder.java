package TTIMPORT;

import TTIMPORT.Importer;
import TTIMPORT.ImporterHelper;

/**
 *	Generated from IDL interface "Importer"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class ImporterHolder	implements org.omg.CORBA.portable.Streamable{
	 public Importer value;
	public ImporterHolder()
	{
	}
	public ImporterHolder (final Importer initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type()
	{
		return ImporterHelper.type();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ImporterHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ImporterHelper.write (_out,value);
	}
}
