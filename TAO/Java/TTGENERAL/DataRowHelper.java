package TTGENERAL;

/**
 *	Generated from IDL definition of alias "DataRow"
 *	@author JacORB IDL compiler 
 */

public final class DataRowHelper
{
	private static org.omg.CORBA.TypeCode _type = null;

	public static void insert (org.omg.CORBA.Any any, TTGENERAL.FieldAttr[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static TTGENERAL.FieldAttr[] extract (final org.omg.CORBA.Any any)
	{
		return read (any.create_input_stream ());
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_alias_tc(TTGENERAL.DataRowHelper.id(), "DataRow",org.omg.CORBA.ORB.init().create_sequence_tc(0, TTGENERAL.FieldAttrHelper.type()));
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:TTGENERAL/DataRow:1.0";
	}
	public static TTGENERAL.FieldAttr[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		TTGENERAL.FieldAttr[] _result;
		int _l_result3 = _in.read_long();
		_result = new TTGENERAL.FieldAttr[_l_result3];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=TTGENERAL.FieldAttrHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, TTGENERAL.FieldAttr[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			TTGENERAL.FieldAttrHelper.write(_out,_s[i]);
		}

	}
}
