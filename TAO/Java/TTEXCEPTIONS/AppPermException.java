package TTEXCEPTIONS;

/**
 *	Generated from IDL definition of exception "AppPermException"
 *	@author JacORB IDL compiler 
 */

public final class AppPermException
	extends org.omg.CORBA.UserException
{
	public AppPermException()
	{
		super(TTEXCEPTIONS.AppPermExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public AppPermException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTEXCEPTIONS.AppPermExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public AppPermException(int errorCode, java.lang.String errorDescription)
	{
		super(TTEXCEPTIONS.AppPermExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
