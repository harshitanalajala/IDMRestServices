package OOHONCHO;

import OOHONCHO.idmservers;
import OOHONCHO.idmserversHelper;

/**
 *	Generated from IDL interface "idmservers"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class idmserversHolder	implements org.omg.CORBA.portable.Streamable{
	 public idmservers value;
	public idmserversHolder()
	{
	}
	public idmserversHolder (final idmservers initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type()
	{
		return idmserversHelper.type();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = idmserversHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		idmserversHelper.write (_out,value);
	}
}
