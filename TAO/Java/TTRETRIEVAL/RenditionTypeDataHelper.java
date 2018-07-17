package TTRETRIEVAL;

/**
 *	Generated from IDL definition of alias "RenditionTypeData"
 *	@author JacORB IDL compiler 
 */

public final class RenditionTypeDataHelper
{
	private static org.omg.CORBA.TypeCode _type = null;

	public static void insert (org.omg.CORBA.Any any, TTRETRIEVAL.RenditionType[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static TTRETRIEVAL.RenditionType[] extract (final org.omg.CORBA.Any any)
	{
		return read (any.create_input_stream ());
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_alias_tc(TTRETRIEVAL.RenditionTypeDataHelper.id(), "RenditionTypeData",org.omg.CORBA.ORB.init().create_sequence_tc(0, TTRETRIEVAL.RenditionTypeHelper.type()));
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:TTRETRIEVAL/RenditionTypeData:1.0";
	}
	public static TTRETRIEVAL.RenditionType[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		TTRETRIEVAL.RenditionType[] _result;
		int _l_result0 = _in.read_long();
		_result = new TTRETRIEVAL.RenditionType[_l_result0];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=TTRETRIEVAL.RenditionTypeHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, TTRETRIEVAL.RenditionType[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			TTRETRIEVAL.RenditionTypeHelper.write(_out,_s[i]);
		}

	}
}
