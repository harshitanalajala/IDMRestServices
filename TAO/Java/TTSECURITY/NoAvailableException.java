package TTSECURITY;

/**
 *	Generated from IDL definition of exception "NoAvailableException"
 *	@author JacORB IDL compiler 
 */

public final class NoAvailableException
	extends org.omg.CORBA.UserException
{
	public NoAvailableException()
	{
		super(TTSECURITY.NoAvailableExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public NoAvailableException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTSECURITY.NoAvailableExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public NoAvailableException(int errorCode, java.lang.String errorDescription)
	{
		super(TTSECURITY.NoAvailableExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
