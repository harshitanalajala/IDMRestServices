package TTANNOTATIONS;

/**
 *	Generated from IDL definition of struct "ANNOT_StickyNote_t"
 *	@author JacORB IDL compiler 
 */

public final class ANNOT_StickyNote_tHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTANNOTATIONS.ANNOT_StickyNote_t value;

	public ANNOT_StickyNote_tHolder ()
	{
	}
	public ANNOT_StickyNote_tHolder(final TTANNOTATIONS.ANNOT_StickyNote_t initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTANNOTATIONS.ANNOT_StickyNote_tHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTANNOTATIONS.ANNOT_StickyNote_tHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTANNOTATIONS.ANNOT_StickyNote_tHelper.write(_out, value);
	}
}
