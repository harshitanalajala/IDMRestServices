package TTIMPORT;

/**
 *	Generated from IDL definition of exception "InvalidAppException"
 *	@author JacORB IDL compiler 
 */

public final class InvalidAppException
	extends org.omg.CORBA.UserException
{
	public InvalidAppException()
	{
		super(TTIMPORT.InvalidAppExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public InvalidAppException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTIMPORT.InvalidAppExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public InvalidAppException(int errorCode, java.lang.String errorDescription)
	{
		super(TTIMPORT.InvalidAppExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
