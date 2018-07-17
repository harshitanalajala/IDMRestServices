package TTANNOTATIONS;

/**
 *	Generated from IDL definition of struct "ANNOT_Annotation_t"
 *	@author JacORB IDL compiler 
 */

public final class ANNOT_Annotation_tHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTANNOTATIONS.ANNOT_Annotation_t value;

	public ANNOT_Annotation_tHolder ()
	{
	}
	public ANNOT_Annotation_tHolder(final TTANNOTATIONS.ANNOT_Annotation_t initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTANNOTATIONS.ANNOT_Annotation_tHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTANNOTATIONS.ANNOT_Annotation_tHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTANNOTATIONS.ANNOT_Annotation_tHelper.write(_out, value);
	}
}
