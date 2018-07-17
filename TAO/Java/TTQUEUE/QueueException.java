package TTQUEUE;

/**
 *	Generated from IDL definition of exception "QueueException"
 *	@author JacORB IDL compiler 
 */

public final class QueueException
	extends org.omg.CORBA.UserException
{
	public QueueException()
	{
		super(TTQUEUE.QueueExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public QueueException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTQUEUE.QueueExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public QueueException(int errorCode, java.lang.String errorDescription)
	{
		super(TTQUEUE.QueueExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
