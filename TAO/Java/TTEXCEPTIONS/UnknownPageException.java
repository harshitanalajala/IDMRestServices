package TTEXCEPTIONS;

/**
 *	Generated from IDL definition of exception "UnknownPageException"
 *	@author JacORB IDL compiler 
 */

public final class UnknownPageException
	extends org.omg.CORBA.UserException
{
	public UnknownPageException()
	{
		super(TTEXCEPTIONS.UnknownPageExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public UnknownPageException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTEXCEPTIONS.UnknownPageExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public UnknownPageException(int errorCode, java.lang.String errorDescription)
	{
		super(TTEXCEPTIONS.UnknownPageExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
