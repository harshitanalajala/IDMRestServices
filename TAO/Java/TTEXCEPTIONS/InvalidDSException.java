package TTEXCEPTIONS;

/**
 *	Generated from IDL definition of exception "InvalidDSException"
 *	@author JacORB IDL compiler 
 */

public final class InvalidDSException
	extends org.omg.CORBA.UserException
{
	public InvalidDSException()
	{
		super(TTEXCEPTIONS.InvalidDSExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public InvalidDSException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTEXCEPTIONS.InvalidDSExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public InvalidDSException(int errorCode, java.lang.String errorDescription)
	{
		super(TTEXCEPTIONS.InvalidDSExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
