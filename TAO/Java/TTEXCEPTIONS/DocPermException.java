package TTEXCEPTIONS;

/**
 *	Generated from IDL definition of exception "DocPermException"
 *	@author JacORB IDL compiler 
 */

public final class DocPermException
	extends org.omg.CORBA.UserException
{
	public DocPermException()
	{
		super(TTEXCEPTIONS.DocPermExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public DocPermException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTEXCEPTIONS.DocPermExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public DocPermException(int errorCode, java.lang.String errorDescription)
	{
		super(TTEXCEPTIONS.DocPermExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
