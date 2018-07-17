package TTANNOTATIONS;

import TTANNOTATIONS.SubpageSeqHelper;

/**
 *	Generated from IDL definition of alias "SubpageSeq"
 *	@author JacORB IDL compiler 
 */

public final class SubpageSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTANNOTATIONS.ANNOT_Subpage_t[] value;

	public SubpageSeqHolder ()
	{
	}
	public SubpageSeqHolder (final TTANNOTATIONS.ANNOT_Subpage_t[] initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return SubpageSeqHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = SubpageSeqHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		SubpageSeqHelper.write (out,value);
	}
}
