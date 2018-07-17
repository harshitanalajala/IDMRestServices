package TTSECURITY;

/**
 *	Generated from IDL definition of exception "CredentialException"
 *	@author JacORB IDL compiler 
 */

public final class CredentialException
	extends org.omg.CORBA.UserException
{
	public CredentialException()
	{
		super(TTSECURITY.CredentialExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public CredentialException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTSECURITY.CredentialExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public CredentialException(int errorCode, java.lang.String errorDescription)
	{
		super(TTSECURITY.CredentialExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
