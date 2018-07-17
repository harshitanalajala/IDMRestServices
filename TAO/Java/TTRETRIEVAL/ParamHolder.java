package TTRETRIEVAL;

/**
 *	Generated from IDL definition of struct "Param"
 *	@author JacORB IDL compiler 
 */

public final class ParamHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTRETRIEVAL.Param value;

	public ParamHolder ()
	{
	}
	public ParamHolder(final TTRETRIEVAL.Param initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTRETRIEVAL.ParamHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTRETRIEVAL.ParamHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTRETRIEVAL.ParamHelper.write(_out, value);
	}
}
