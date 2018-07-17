package TTSECURITY;


/**
 *	Generated from IDL definition of struct "UserInfo"
 *	@author JacORB IDL compiler 
 */

public final class UserInfoHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(TTSECURITY.UserInfoHelper.id(),"UserInfo",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("mUserID", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("mSessionID", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("mForLog", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("mLoginTime", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("mLastAccessTime", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("mTimeoutTime", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTSECURITY.UserInfo s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTSECURITY.UserInfo extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTSECURITY/UserInfo:1.0";
	}
	public static TTSECURITY.UserInfo read (final org.omg.CORBA.portable.InputStream in)
	{
		TTSECURITY.UserInfo result = new TTSECURITY.UserInfo();
		result.mUserID=in.read_string();
		result.mSessionID=in.read_string();
		result.mForLog=in.read_string();
		result.mLoginTime=in.read_long();
		result.mLastAccessTime=in.read_long();
		result.mTimeoutTime=in.read_long();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTSECURITY.UserInfo s)
	{
		out.write_string(s.mUserID);
		out.write_string(s.mSessionID);
		out.write_string(s.mForLog);
		out.write_long(s.mLoginTime);
		out.write_long(s.mLastAccessTime);
		out.write_long(s.mTimeoutTime);
	}
}
