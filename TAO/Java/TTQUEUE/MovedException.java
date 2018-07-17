package TTQUEUE;

/**
 *	Generated from IDL definition of exception "MovedException"
 *	@author JacORB IDL compiler 
 */

public final class MovedException
	extends org.omg.CORBA.UserException
{
	public MovedException()
	{
		super(TTQUEUE.MovedExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public MovedException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTQUEUE.MovedExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public MovedException(int errorCode, java.lang.String errorDescription)
	{
		super(TTQUEUE.MovedExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
