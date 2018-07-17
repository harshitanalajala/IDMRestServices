package TTEXCEPTIONS;

/**
 *	Generated from IDL definition of exception "UnknownDocException"
 *	@author JacORB IDL compiler 
 */

public final class UnknownDocException
	extends org.omg.CORBA.UserException
{
	public UnknownDocException()
	{
		super(TTEXCEPTIONS.UnknownDocExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public UnknownDocException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTEXCEPTIONS.UnknownDocExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public UnknownDocException(int errorCode, java.lang.String errorDescription)
	{
		super(TTEXCEPTIONS.UnknownDocExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
