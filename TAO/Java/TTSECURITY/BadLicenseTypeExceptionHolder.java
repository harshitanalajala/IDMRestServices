package TTSECURITY;

/**
 *	Generated from IDL definition of exception "BadLicenseTypeException"
 *	@author JacORB IDL compiler 
 */

public final class BadLicenseTypeExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTSECURITY.BadLicenseTypeException value;

	public BadLicenseTypeExceptionHolder ()
	{
	}
	public BadLicenseTypeExceptionHolder(final TTSECURITY.BadLicenseTypeException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTSECURITY.BadLicenseTypeExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTSECURITY.BadLicenseTypeExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTSECURITY.BadLicenseTypeExceptionHelper.write(_out, value);
	}
}
