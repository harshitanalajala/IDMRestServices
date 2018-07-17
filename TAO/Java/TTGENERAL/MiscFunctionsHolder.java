package TTGENERAL;

import TTGENERAL.MiscFunctions;
import TTGENERAL.MiscFunctionsHelper;

/**
 *	Generated from IDL interface "MiscFunctions"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class MiscFunctionsHolder	implements org.omg.CORBA.portable.Streamable{
	 public MiscFunctions value;
	public MiscFunctionsHolder()
	{
	}
	public MiscFunctionsHolder (final MiscFunctions initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type()
	{
		return MiscFunctionsHelper.type();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = MiscFunctionsHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		MiscFunctionsHelper.write (_out,value);
	}
}
