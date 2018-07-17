package TTQUEUE;

import TTQUEUE.CDataType;
import TTQUEUE.CDataTypeHelper;

/**
 *	Generated from IDL definition of enum "CDataType"
 *	@author JacORB IDL compiler 
 */

public final class CDataTypeHolder
	implements org.omg.CORBA.portable.Streamable
{
	public CDataType value;

	public CDataTypeHolder ()
	{
	}
	public CDataTypeHolder (final CDataType initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return CDataTypeHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CDataTypeHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		CDataTypeHelper.write (out,value);
	}
}
