package TTGENERAL;

import TTGENERAL.GenFunctions;
import TTGENERAL.GenFunctionsHelper;

/**
 *	Generated from IDL interface "GenFunctions"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class GenFunctionsHolder	implements org.omg.CORBA.portable.Streamable{
	 public GenFunctions value;
	public GenFunctionsHolder()
	{
	}
	public GenFunctionsHolder (final GenFunctions initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type()
	{
		return GenFunctionsHelper.type();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = GenFunctionsHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		GenFunctionsHelper.write (_out,value);
	}
}
