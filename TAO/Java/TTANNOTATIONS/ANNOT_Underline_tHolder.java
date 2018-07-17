package TTANNOTATIONS;

/**
 *	Generated from IDL definition of struct "ANNOT_Underline_t"
 *	@author JacORB IDL compiler 
 */

public final class ANNOT_Underline_tHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTANNOTATIONS.ANNOT_Underline_t value;

	public ANNOT_Underline_tHolder ()
	{
	}
	public ANNOT_Underline_tHolder(final TTANNOTATIONS.ANNOT_Underline_t initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTANNOTATIONS.ANNOT_Underline_tHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTANNOTATIONS.ANNOT_Underline_tHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTANNOTATIONS.ANNOT_Underline_tHelper.write(_out, value);
	}
}
