package TTANNOTATIONS;

/**
 *	Generated from IDL definition of struct "ANNOT_Subpage_t"
 *	@author JacORB IDL compiler 
 */

public final class ANNOT_Subpage_tHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTANNOTATIONS.ANNOT_Subpage_t value;

	public ANNOT_Subpage_tHolder ()
	{
	}
	public ANNOT_Subpage_tHolder(final TTANNOTATIONS.ANNOT_Subpage_t initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTANNOTATIONS.ANNOT_Subpage_tHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTANNOTATIONS.ANNOT_Subpage_tHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTANNOTATIONS.ANNOT_Subpage_tHelper.write(_out, value);
	}
}
