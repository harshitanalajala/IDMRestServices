package TTANNOTATIONS;

import TTANNOTATIONS.PointSeqHelper;

/**
 *	Generated from IDL definition of alias "PointSeq"
 *	@author JacORB IDL compiler 
 */

public final class PointSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTANNOTATIONS.ANNOT_Point_t[] value;

	public PointSeqHolder ()
	{
	}
	public PointSeqHolder (final TTANNOTATIONS.ANNOT_Point_t[] initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return PointSeqHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = PointSeqHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		PointSeqHelper.write (out,value);
	}
}
