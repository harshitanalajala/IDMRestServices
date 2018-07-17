package TTQUEUE;


/**
 *	Generated from IDL definition of struct "CQueueInfo"
 *	@author JacORB IDL compiler 
 */

public final class CQueueInfoHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(TTQUEUE.CQueueInfoHelper.id(),"CQueueInfo",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("queueId", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("type", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("createTime", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("accessTime", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("modifyTime", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("deleteTime", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("numEntries", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("requiredVarDefs", TTQUEUE.CQueueVarSeqHelper.type(), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTQUEUE.CQueueInfo s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTQUEUE.CQueueInfo extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTQUEUE/CQueueInfo:1.0";
	}
	public static TTQUEUE.CQueueInfo read (final org.omg.CORBA.portable.InputStream in)
	{
		TTQUEUE.CQueueInfo result = new TTQUEUE.CQueueInfo();
		result.queueId=in.read_ulong();
		result.name=in.read_string();
		result.type=in.read_string();
		result.createTime=in.read_string();
		result.accessTime=in.read_string();
		result.modifyTime=in.read_string();
		result.deleteTime=in.read_string();
		result.numEntries=in.read_ulong();
		result.requiredVarDefs = TTQUEUE.CQueueVarSeqHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTQUEUE.CQueueInfo s)
	{
		out.write_ulong(s.queueId);
		out.write_string(s.name);
		out.write_string(s.type);
		out.write_string(s.createTime);
		out.write_string(s.accessTime);
		out.write_string(s.modifyTime);
		out.write_string(s.deleteTime);
		out.write_ulong(s.numEntries);
		TTQUEUE.CQueueVarSeqHelper.write(out,s.requiredVarDefs);
	}
}
