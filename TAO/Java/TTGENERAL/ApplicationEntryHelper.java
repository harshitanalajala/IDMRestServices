package TTGENERAL;


/**
 *	Generated from IDL definition of struct "ApplicationEntry"
 *	@author JacORB IDL compiler 
 */

public final class ApplicationEntryHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(TTGENERAL.ApplicationEntryHelper.id(),"ApplicationEntry",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("mName", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("mShortName", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("mDiskset", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("mAclIndex", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("mFlags", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTGENERAL.ApplicationEntry s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTGENERAL.ApplicationEntry extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTGENERAL/ApplicationEntry:1.0";
	}
	public static TTGENERAL.ApplicationEntry read (final org.omg.CORBA.portable.InputStream in)
	{
		TTGENERAL.ApplicationEntry result = new TTGENERAL.ApplicationEntry();
		result.mName=in.read_string();
		result.mShortName=in.read_string();
		result.mDiskset=in.read_string();
		result.mAclIndex=in.read_long();
		result.mFlags=in.read_long();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTGENERAL.ApplicationEntry s)
	{
		out.write_string(s.mName);
		out.write_string(s.mShortName);
		out.write_string(s.mDiskset);
		out.write_long(s.mAclIndex);
		out.write_long(s.mFlags);
	}
}
