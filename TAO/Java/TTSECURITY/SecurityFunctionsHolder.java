package TTSECURITY;

import TTSECURITY.SecurityFunctions;
import TTSECURITY.SecurityFunctionsHelper;

/**
 *	Generated from IDL interface "SecurityFunctions"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class SecurityFunctionsHolder	implements org.omg.CORBA.portable.Streamable{
	 public SecurityFunctions value;
	public SecurityFunctionsHolder()
	{
	}
	public SecurityFunctionsHolder (final SecurityFunctions initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type()
	{
		return SecurityFunctionsHelper.type();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = SecurityFunctionsHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		SecurityFunctionsHelper.write (_out,value);
	}
}
