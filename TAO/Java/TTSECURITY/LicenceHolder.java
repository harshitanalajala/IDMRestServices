package TTSECURITY;

import TTSECURITY.Licence;
import TTSECURITY.LicenceHelper;

/**
 *	Generated from IDL interface "Licence"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class LicenceHolder	implements org.omg.CORBA.portable.Streamable{
	 public Licence value;
	public LicenceHolder()
	{
	}
	public LicenceHolder (final Licence initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type()
	{
		return LicenceHelper.type();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = LicenceHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		LicenceHelper.write (_out,value);
	}
}
