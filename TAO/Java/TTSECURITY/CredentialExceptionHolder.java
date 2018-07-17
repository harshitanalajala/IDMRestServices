package TTSECURITY;

/**
 *	Generated from IDL definition of exception "CredentialException"
 *	@author JacORB IDL compiler 
 */

public final class CredentialExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public TTSECURITY.CredentialException value;

	public CredentialExceptionHolder ()
	{
	}
	public CredentialExceptionHolder(final TTSECURITY.CredentialException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return TTSECURITY.CredentialExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = TTSECURITY.CredentialExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		TTSECURITY.CredentialExceptionHelper.write(_out, value);
	}
}
