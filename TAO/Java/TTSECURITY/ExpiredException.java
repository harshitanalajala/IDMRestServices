package TTSECURITY;

/**
 *	Generated from IDL definition of exception "ExpiredException"
 *	@author JacORB IDL compiler 
 */

public final class ExpiredException
	extends org.omg.CORBA.UserException
{
	public ExpiredException()
	{
		super(TTSECURITY.ExpiredExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public ExpiredException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTSECURITY.ExpiredExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public ExpiredException(int errorCode, java.lang.String errorDescription)
	{
		super(TTSECURITY.ExpiredExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
