package TTEXCEPTIONS;

/**
 *	Generated from IDL definition of exception "TTlibException"
 *	@author JacORB IDL compiler 
 */

public final class TTlibException
	extends org.omg.CORBA.UserException
{
	public TTlibException()
	{
		super(TTEXCEPTIONS.TTlibExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorCodeStr = "";
	public java.lang.String errorDescription = "";
	public TTlibException(java.lang.String _reason,int errorCode, java.lang.String errorCodeStr, java.lang.String errorDescription)
	{
		super(TTEXCEPTIONS.TTlibExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorCodeStr = errorCodeStr;
		this.errorDescription = errorDescription;
	}
	public TTlibException(int errorCode, java.lang.String errorCodeStr, java.lang.String errorDescription)
	{
		super(TTEXCEPTIONS.TTlibExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorCodeStr = errorCodeStr;
		this.errorDescription = errorDescription;
	}
}
