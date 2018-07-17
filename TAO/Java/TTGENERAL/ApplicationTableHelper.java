package TTGENERAL;

/**
 *	Generated from IDL definition of alias "ApplicationTable"
 *	@author JacORB IDL compiler 
 */

public final class ApplicationTableHelper
{
	private static org.omg.CORBA.TypeCode _type = null;

	public static void insert (org.omg.CORBA.Any any, TTGENERAL.ApplicationEntry[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static TTGENERAL.ApplicationEntry[] extract (final org.omg.CORBA.Any any)
	{
		return read (any.create_input_stream ());
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_alias_tc(TTGENERAL.ApplicationTableHelper.id(), "ApplicationTable",org.omg.CORBA.ORB.init().create_sequence_tc(0, TTGENERAL.ApplicationEntryHelper.type()));
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:TTGENERAL/ApplicationTable:1.0";
	}
	public static TTGENERAL.ApplicationEntry[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		TTGENERAL.ApplicationEntry[] _result;
		int _l_result1 = _in.read_long();
		_result = new TTGENERAL.ApplicationEntry[_l_result1];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=TTGENERAL.ApplicationEntryHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, TTGENERAL.ApplicationEntry[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			TTGENERAL.ApplicationEntryHelper.write(_out,_s[i]);
		}

	}
}
