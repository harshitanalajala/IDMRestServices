package TTQUEUE;

import TTQUEUE.CDataType;

/**
 *	Generated from IDL definition of enum "CDataType"
 *	@author JacORB IDL compiler 
 */

public final class CDataTypeHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_enum_tc(TTQUEUE.CDataTypeHelper.id(),"CDataType",new String[]{"QCHAR","INTEGER","SMALLINT","DATE","TIME","DATETIME","CUST00","CUST01","CUST02","CUST03","CUST04","CUST05"});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTQUEUE.CDataType s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTQUEUE.CDataType extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTQUEUE/CDataType:1.0";
	}
	public static CDataType read (final org.omg.CORBA.portable.InputStream in)
	{
		return CDataType.from_int(in.read_long());
	}

	public static void write (final org.omg.CORBA.portable.OutputStream out, final CDataType s)
	{
		out.write_long(s.value());
	}
}
