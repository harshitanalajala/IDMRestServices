package TTEXCEPTIONS;

/**
 *	Generated from IDL definition of exception "AuthenticationException"
 *	@author JacORB IDL compiler 
 */

public final class AuthenticationException
	extends org.omg.CORBA.UserException
{
	public AuthenticationException()
	{
		super(TTEXCEPTIONS.AuthenticationExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public AuthenticationException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTEXCEPTIONS.AuthenticationExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public AuthenticationException(int errorCode, java.lang.String errorDescription)
	{
		super(TTEXCEPTIONS.AuthenticationExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
