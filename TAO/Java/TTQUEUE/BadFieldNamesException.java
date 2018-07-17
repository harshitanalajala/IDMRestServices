package TTQUEUE;

/**
 *	Generated from IDL definition of exception "BadFieldNamesException"
 *	@author JacORB IDL compiler 
 */

public final class BadFieldNamesException
	extends org.omg.CORBA.UserException
{
	public BadFieldNamesException()
	{
		super(TTQUEUE.BadFieldNamesExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public BadFieldNamesException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTQUEUE.BadFieldNamesExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public BadFieldNamesException(int errorCode, java.lang.String errorDescription)
	{
		super(TTQUEUE.BadFieldNamesExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
