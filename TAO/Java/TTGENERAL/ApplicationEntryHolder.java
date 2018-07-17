package TTGENERAL;

/**
 *	Generated from IDL definition of struct "ApplicationEntry"
 *	@author JacORB IDL compiler 
 */

public final class ApplicationEntryHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTGENERAL.ApplicationEntry value;

	public ApplicationEntryHolder ()
	{
	}
	public ApplicationEntryHolder(final TTGENERAL.ApplicationEntry initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTGENERAL.ApplicationEntryHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTGENERAL.ApplicationEntryHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTGENERAL.ApplicationEntryHelper.write(_out, value);
	}
}
