package TTQUEUE;

/**
 *	Generated from IDL definition of exception "BadWhereException"
 *	@author JacORB IDL compiler 
 */

public final class BadWhereException
	extends org.omg.CORBA.UserException
{
	public BadWhereException()
	{
		super(TTQUEUE.BadWhereExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public BadWhereException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTQUEUE.BadWhereExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public BadWhereException(int errorCode, java.lang.String errorDescription)
	{
		super(TTQUEUE.BadWhereExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
