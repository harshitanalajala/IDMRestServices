package TTQUEUE;


/**
 *	Generated from IDL definition of struct "CQueueVarDef"
 *	@author JacORB IDL compiler 
 */

public final class CQueueVarDefHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(TTQUEUE.CQueueVarDefHelper.id(),"CQueueVarDef",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("heading", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("defaultValue", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("dataType", TTQUEUE.CDataTypeHelper.type(), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTQUEUE.CQueueVarDef s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTQUEUE.CQueueVarDef extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTQUEUE/CQueueVarDef:1.0";
	}
	public static TTQUEUE.CQueueVarDef read (final org.omg.CORBA.portable.InputStream in)
	{
		TTQUEUE.CQueueVarDef result = new TTQUEUE.CQueueVarDef();
		result.name=in.read_string();
		result.heading=in.read_string();
		result.defaultValue=in.read_string();
		result.dataType=TTQUEUE.CDataTypeHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTQUEUE.CQueueVarDef s)
	{
		out.write_string(s.name);
		out.write_string(s.heading);
		out.write_string(s.defaultValue);
		TTQUEUE.CDataTypeHelper.write(out,s.dataType);
	}
}
