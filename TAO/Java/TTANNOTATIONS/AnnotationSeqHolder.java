package TTANNOTATIONS;

import TTANNOTATIONS.AnnotationSeqHelper;

/**
 *	Generated from IDL definition of alias "AnnotationSeq"
 *	@author JacORB IDL compiler 
 */

public final class AnnotationSeqHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTANNOTATIONS.ANNOT_Annotation_t[] value;

	public AnnotationSeqHolder ()
	{
	}
	public AnnotationSeqHolder (final TTANNOTATIONS.ANNOT_Annotation_t[] initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return AnnotationSeqHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = AnnotationSeqHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		AnnotationSeqHelper.write (out,value);
	}
}
