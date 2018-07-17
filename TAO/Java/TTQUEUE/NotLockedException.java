package TTQUEUE;

/**
 *	Generated from IDL definition of exception "NotLockedException"
 *	@author JacORB IDL compiler 
 */

public final class NotLockedException
	extends org.omg.CORBA.UserException
{
	public NotLockedException()
	{
		super(TTQUEUE.NotLockedExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public NotLockedException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTQUEUE.NotLockedExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public NotLockedException(int errorCode, java.lang.String errorDescription)
	{
		super(TTQUEUE.NotLockedExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
