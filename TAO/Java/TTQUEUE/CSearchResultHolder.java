package TTQUEUE;

/**
 *	Generated from IDL definition of struct "CSearchResult"
 *	@author JacORB IDL compiler 
 */

public final class CSearchResultHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTQUEUE.CSearchResult value;

	public CSearchResultHolder ()
	{
	}
	public CSearchResultHolder(final TTQUEUE.CSearchResult initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTQUEUE.CSearchResultHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTQUEUE.CSearchResultHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTQUEUE.CSearchResultHelper.write(_out, value);
	}
}
