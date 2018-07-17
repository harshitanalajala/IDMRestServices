package TTLIBRARY;

/**
 *	Generated from IDL definition of exception "NotLibAppException"
 *	@author JacORB IDL compiler 
 */

public final class NotLibAppException
	extends org.omg.CORBA.UserException
{
	public NotLibAppException()
	{
		super(TTLIBRARY.NotLibAppExceptionHelper.id());
	}

	public int mErrorCode;
	public java.lang.String mErrorDescription = "";
	public NotLibAppException(java.lang.String _reason,int mErrorCode, java.lang.String mErrorDescription)
	{
		super(TTLIBRARY.NotLibAppExceptionHelper.id()+ " " + _reason);
		this.mErrorCode = mErrorCode;
		this.mErrorDescription = mErrorDescription;
	}
	public NotLibAppException(int mErrorCode, java.lang.String mErrorDescription)
	{
		super(TTLIBRARY.NotLibAppExceptionHelper.id());
		this.mErrorCode = mErrorCode;
		this.mErrorDescription = mErrorDescription;
	}
}
