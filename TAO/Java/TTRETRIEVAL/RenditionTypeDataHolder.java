package TTRETRIEVAL;

import TTRETRIEVAL.RenditionTypeDataHelper;

/**
 *	Generated from IDL definition of alias "RenditionTypeData"
 *	@author JacORB IDL compiler 
 */

public final class RenditionTypeDataHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTRETRIEVAL.RenditionType[] value;

	public RenditionTypeDataHolder ()
	{
	}
	public RenditionTypeDataHolder (final TTRETRIEVAL.RenditionType[] initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return RenditionTypeDataHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = RenditionTypeDataHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		RenditionTypeDataHelper.write (out,value);
	}
}
