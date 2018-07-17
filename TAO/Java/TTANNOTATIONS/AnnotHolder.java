package TTANNOTATIONS;

import TTANNOTATIONS.Annot;
import TTANNOTATIONS.AnnotHelper;

/**
 *	Generated from IDL interface "Annot"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class AnnotHolder	implements org.omg.CORBA.portable.Streamable{
	 public Annot value;
	public AnnotHolder()
	{
	}
	public AnnotHolder (final Annot initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type()
	{
		return AnnotHelper.type();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = AnnotHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		AnnotHelper.write (_out,value);
	}
}
