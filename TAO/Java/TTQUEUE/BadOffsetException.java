package TTQUEUE;

/**
 *	Generated from IDL definition of exception "BadOffsetException"
 *	@author JacORB IDL compiler 
 */

public final class BadOffsetException
	extends org.omg.CORBA.UserException
{
	public BadOffsetException()
	{
		super(TTQUEUE.BadOffsetExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public BadOffsetException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTQUEUE.BadOffsetExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public BadOffsetException(int errorCode, java.lang.String errorDescription)
	{
		super(TTQUEUE.BadOffsetExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
