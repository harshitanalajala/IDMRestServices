package TTANNOTATIONS;

/**
 *	Generated from IDL definition of alias "SubpageSeq"
 *	@author JacORB IDL compiler 
 */

public final class SubpageSeqHelper
{
	private static org.omg.CORBA.TypeCode _type = null;

	public static void insert (org.omg.CORBA.Any any, TTANNOTATIONS.ANNOT_Subpage_t[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static TTANNOTATIONS.ANNOT_Subpage_t[] extract (final org.omg.CORBA.Any any)
	{
		return read (any.create_input_stream ());
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			_type = org.omg.CORBA.ORB.init().create_alias_tc(TTANNOTATIONS.SubpageSeqHelper.id(), "SubpageSeq",org.omg.CORBA.ORB.init().create_sequence_tc(0, TTANNOTATIONS.ANNOT_Subpage_tHelper.type()));
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:TTANNOTATIONS/SubpageSeq:1.0";
	}
	public static TTANNOTATIONS.ANNOT_Subpage_t[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		TTANNOTATIONS.ANNOT_Subpage_t[] _result;
		int _l_result3 = _in.read_long();
		_result = new TTANNOTATIONS.ANNOT_Subpage_t[_l_result3];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=TTANNOTATIONS.ANNOT_Subpage_tHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, TTANNOTATIONS.ANNOT_Subpage_t[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			TTANNOTATIONS.ANNOT_Subpage_tHelper.write(_out,_s[i]);
		}

	}
}
