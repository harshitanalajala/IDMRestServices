package TTANNOTATIONS;


/**
 *	Generated from IDL definition of struct "PageAnnotations"
 *	@author JacORB IDL compiler 
 */

public final class PageAnnotationsHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(TTANNOTATIONS.PageAnnotationsHelper.id(),"PageAnnotations",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("userCanCreate", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null),new org.omg.CORBA.StructMember("imageIfn", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("imagePageNo", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("userId", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("fileVersion", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(4)), null),new org.omg.CORBA.StructMember("subSeq", TTANNOTATIONS.SubpageSeqHelper.type(), null),new org.omg.CORBA.StructMember("conflictCount", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTANNOTATIONS.PageAnnotations s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTANNOTATIONS.PageAnnotations extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTANNOTATIONS/PageAnnotations:1.0";
	}
	public static TTANNOTATIONS.PageAnnotations read (final org.omg.CORBA.portable.InputStream in)
	{
		TTANNOTATIONS.PageAnnotations result = new TTANNOTATIONS.PageAnnotations();
		result.userCanCreate=in.read_boolean();
		result.imageIfn=in.read_string();
		result.imagePageNo=in.read_ulong();
		result.userId=in.read_string();
		result.fileVersion=in.read_ushort();
		result.subSeq = TTANNOTATIONS.SubpageSeqHelper.read(in);
		result.conflictCount=in.read_ulong();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTANNOTATIONS.PageAnnotations s)
	{
		out.write_boolean(s.userCanCreate);
		out.write_string(s.imageIfn);
		out.write_ulong(s.imagePageNo);
		out.write_string(s.userId);
		out.write_ushort(s.fileVersion);
		TTANNOTATIONS.SubpageSeqHelper.write(out,s.subSeq);
		out.write_ulong(s.conflictCount);
	}
}
