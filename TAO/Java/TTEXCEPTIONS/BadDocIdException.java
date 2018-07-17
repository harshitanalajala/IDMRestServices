package TTEXCEPTIONS;

/**
 *	Generated from IDL definition of exception "BadDocIdException"
 *	@author JacORB IDL compiler 
 */

public final class BadDocIdException
	extends org.omg.CORBA.UserException
{
	public BadDocIdException()
	{
		super(TTEXCEPTIONS.BadDocIdExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public BadDocIdException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTEXCEPTIONS.BadDocIdExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public BadDocIdException(int errorCode, java.lang.String errorDescription)
	{
		super(TTEXCEPTIONS.BadDocIdExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
