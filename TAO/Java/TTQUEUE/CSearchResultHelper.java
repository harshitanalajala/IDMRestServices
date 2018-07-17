package TTQUEUE;


/**
 *	Generated from IDL definition of struct "CSearchResult"
 *	@author JacORB IDL compiler 
 */

public final class CSearchResultHelper
{
	private static org.omg.CORBA.TypeCode _type = null;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_struct_tc(TTQUEUE.CSearchResultHelper.id(),"CSearchResult",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("fieldNames", TTQUEUE.CStringSeqHelper.type(), null),new org.omg.CORBA.StructMember("rows", TTQUEUE.CRowSeqHelper.type(), null)});
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final TTQUEUE.CSearchResult s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTQUEUE.CSearchResult extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTQUEUE/CSearchResult:1.0";
	}
	public static TTQUEUE.CSearchResult read (final org.omg.CORBA.portable.InputStream in)
	{
		TTQUEUE.CSearchResult result = new TTQUEUE.CSearchResult();
		result.fieldNames = TTQUEUE.CStringSeqHelper.read(in);
		result.rows = TTQUEUE.CRowSeqHelper.read(in);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final TTQUEUE.CSearchResult s)
	{
		TTQUEUE.CStringSeqHelper.write(out,s.fieldNames);
		TTQUEUE.CRowSeqHelper.write(out,s.rows);
	}
}
