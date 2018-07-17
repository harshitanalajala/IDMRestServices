package TTGENERAL;

import TTGENERAL.DataRowHelper;

/**
 *	Generated from IDL definition of alias "DataRow"
 *	@author JacORB IDL compiler 
 */

public final class DataRowHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTGENERAL.FieldAttr[] value;

	public DataRowHolder ()
	{
	}
	public DataRowHolder (final TTGENERAL.FieldAttr[] initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return DataRowHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DataRowHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		DataRowHelper.write (out,value);
	}
}
