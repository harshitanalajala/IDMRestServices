package TTRETRIEVAL;

/**
 *	Generated from IDL definition of exception "BadFilePathException"
 *	@author JacORB IDL compiler 
 */

public final class BadFilePathException
	extends org.omg.CORBA.UserException
{
	public BadFilePathException()
	{
		super(TTRETRIEVAL.BadFilePathExceptionHelper.id());
	}

	public int mErrorCode;
	public java.lang.String mErrorDescription = "";
	public BadFilePathException(java.lang.String _reason,int mErrorCode, java.lang.String mErrorDescription)
	{
		super(TTRETRIEVAL.BadFilePathExceptionHelper.id()+ " " + _reason);
		this.mErrorCode = mErrorCode;
		this.mErrorDescription = mErrorDescription;
	}
	public BadFilePathException(int mErrorCode, java.lang.String mErrorDescription)
	{
		super(TTRETRIEVAL.BadFilePathExceptionHelper.id());
		this.mErrorCode = mErrorCode;
		this.mErrorDescription = mErrorDescription;
	}
}
