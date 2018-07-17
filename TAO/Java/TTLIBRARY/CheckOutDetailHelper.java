package TTLIBRARY;


/**
 *	Generated from IDL definition of struct "CheckOutDetail"
 *	@author JacORB IDL compiler 
 */

public final class CheckOutDetailHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(TTLIBRARY.CheckOutDetailHelper.id(),"CheckOutDetail",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("mUserID", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("mDate", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("mTime", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("mComment", org.omg.CORBA.ORB.init().create_string_tc(0), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTLIBRARY.CheckOutDetail s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTLIBRARY.CheckOutDetail extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTLIBRARY/CheckOutDetail:1.0";
	}
	public static TTLIBRARY.CheckOutDetail read (final org.omg.CORBA.portable.InputStream in)
	{
		TTLIBRARY.CheckOutDetail result = new TTLIBRARY.CheckOutDetail();
		result.mUserID=in.read_string();
		result.mDate=in.read_string();
		result.mTime=in.read_string();
		result.mComment=in.read_string();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTLIBRARY.CheckOutDetail s)
	{
		out.write_string(s.mUserID);
		out.write_string(s.mDate);
		out.write_string(s.mTime);
		out.write_string(s.mComment);
	}
}
