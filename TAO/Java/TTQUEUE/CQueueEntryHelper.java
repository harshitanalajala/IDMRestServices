package TTQUEUE;


/**
 *	Generated from IDL definition of struct "CQueueEntry"
 *	@author JacORB IDL compiler 
 */

public final class CQueueEntryHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(TTQUEUE.CQueueEntryHelper.id(),"CQueueEntry",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("queueId", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("entryId", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("priority", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(5)), null),new org.omg.CORBA.StructMember("createTime", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("modifyTime", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("entryTime", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("lockNextEnabled", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(8)), null),new org.omg.CORBA.StructMember("lockUser", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("varList", TTQUEUE.CNameValueSeqHelper.type(), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTQUEUE.CQueueEntry s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTQUEUE.CQueueEntry extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTQUEUE/CQueueEntry:1.0";
	}
	public static TTQUEUE.CQueueEntry read (final org.omg.CORBA.portable.InputStream in)
	{
		TTQUEUE.CQueueEntry result = new TTQUEUE.CQueueEntry();
		result.queueId=in.read_ulong();
		result.entryId=in.read_ulong();
		result.priority=in.read_ulong();
		result.createTime=in.read_string();
		result.modifyTime=in.read_string();
		result.entryTime=in.read_string();
		result.lockNextEnabled=in.read_boolean();
		result.lockUser=in.read_string();
		result.varList = TTQUEUE.CNameValueSeqHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTQUEUE.CQueueEntry s)
	{
		out.write_ulong(s.queueId);
		out.write_ulong(s.entryId);
		out.write_ulong(s.priority);
		out.write_string(s.createTime);
		out.write_string(s.modifyTime);
		out.write_string(s.entryTime);
		out.write_boolean(s.lockNextEnabled);
		out.write_string(s.lockUser);
		TTQUEUE.CNameValueSeqHelper.write(out,s.varList);
	}
}
