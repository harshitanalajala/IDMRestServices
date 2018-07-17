package TTQUEUE;

/**
 *	Generated from IDL definition of exception "LockFailureException"
 *	@author JacORB IDL compiler 
 */

public final class LockFailureException
	extends org.omg.CORBA.UserException
{
	public LockFailureException()
	{
		super(TTQUEUE.LockFailureExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public LockFailureException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTQUEUE.LockFailureExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public LockFailureException(int errorCode, java.lang.String errorDescription)
	{
		super(TTQUEUE.LockFailureExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
