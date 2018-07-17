package TTRETRIEVAL;


/**
 *	Generated from IDL definition of struct "RenditionType"
 *	@author JacORB IDL compiler 
 */

public final class RenditionTypeHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(TTRETRIEVAL.RenditionTypeHelper.id(),"RenditionType",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("mRenditionName", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("mMimeType", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("mZoomFactor", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTRETRIEVAL.RenditionType s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTRETRIEVAL.RenditionType extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTRETRIEVAL/RenditionType:1.0";
	}
	public static TTRETRIEVAL.RenditionType read (final org.omg.CORBA.portable.InputStream in)
	{
		TTRETRIEVAL.RenditionType result = new TTRETRIEVAL.RenditionType();
		result.mRenditionName=in.read_string();
		result.mMimeType=in.read_string();
		result.mZoomFactor=in.read_ushort();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTRETRIEVAL.RenditionType s)
	{
		out.write_string(s.mRenditionName);
		out.write_string(s.mMimeType);
		out.write_ushort(s.mZoomFactor);
	}
}
