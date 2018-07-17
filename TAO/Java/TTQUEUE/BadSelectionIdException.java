package TTQUEUE;

/**
 *	Generated from IDL definition of exception "BadSelectionIdException"
 *	@author JacORB IDL compiler 
 */

public final class BadSelectionIdException
	extends org.omg.CORBA.UserException
{
	public BadSelectionIdException()
	{
		super(TTQUEUE.BadSelectionIdExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public BadSelectionIdException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTQUEUE.BadSelectionIdExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public BadSelectionIdException(int errorCode, java.lang.String errorDescription)
	{
		super(TTQUEUE.BadSelectionIdExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
