package TTRETRIEVAL;

import TTRETRIEVAL.ParamDataHelper;

/**
 *	Generated from IDL definition of alias "ParamData"
 *	@author JacORB IDL compiler 
 */

public final class ParamDataHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTRETRIEVAL.Param[] value;

	public ParamDataHolder ()
	{
	}
	public ParamDataHolder (final TTRETRIEVAL.Param[] initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return ParamDataHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ParamDataHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ParamDataHelper.write (out,value);
	}
}
