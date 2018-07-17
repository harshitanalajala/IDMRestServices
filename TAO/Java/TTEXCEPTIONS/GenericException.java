package TTEXCEPTIONS;

/**
 *	Generated from IDL definition of exception "GenericException"
 *	@author JacORB IDL compiler 
 */

public final class GenericException
	extends org.omg.CORBA.UserException
{
	public GenericException()
	{
		super(TTEXCEPTIONS.GenericExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public GenericException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTEXCEPTIONS.GenericExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public GenericException(int errorCode, java.lang.String errorDescription)
	{
		super(TTEXCEPTIONS.GenericExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
