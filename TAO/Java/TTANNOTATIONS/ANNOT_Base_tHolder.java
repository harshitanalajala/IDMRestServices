package TTANNOTATIONS;

import TTANNOTATIONS.ANNOT_Base_t;
import TTANNOTATIONS.ANNOT_Base_tHelper;

/**
 *	Generated from IDL definition of union "ANNOT_Base_t"
 *	@author JacORB IDL compiler 
 */

public final class ANNOT_Base_tHolder
	implements org.omg.CORBA.portable.Streamable
{
	public ANNOT_Base_t value;

	public ANNOT_Base_tHolder ()
	{
	}
	public ANNOT_Base_tHolder (final ANNOT_Base_t initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return ANNOT_Base_tHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ANNOT_Base_tHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ANNOT_Base_tHelper.write (out, value);
	}
}
