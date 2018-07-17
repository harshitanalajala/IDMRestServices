package TTSECURITY;

/**
 *	Generated from IDL definition of struct "UserInfo"
 *	@author JacORB IDL compiler 
 */

public final class UserInfoHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTSECURITY.UserInfo value;

	public UserInfoHolder ()
	{
	}
	public UserInfoHolder(final TTSECURITY.UserInfo initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTSECURITY.UserInfoHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTSECURITY.UserInfoHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTSECURITY.UserInfoHelper.write(_out, value);
	}
}
