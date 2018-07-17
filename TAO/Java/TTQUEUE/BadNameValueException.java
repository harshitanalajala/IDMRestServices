package TTQUEUE;

/**
 *	Generated from IDL definition of exception "BadNameValueException"
 *	@author JacORB IDL compiler 
 */

public final class BadNameValueException
	extends org.omg.CORBA.UserException
{
	public BadNameValueException()
	{
		super(TTQUEUE.BadNameValueExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public BadNameValueException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTQUEUE.BadNameValueExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public BadNameValueException(int errorCode, java.lang.String errorDescription)
	{
		super(TTQUEUE.BadNameValueExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
