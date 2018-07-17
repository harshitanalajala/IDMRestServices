package TTLIBRARY;

/**
 *	Generated from IDL definition of exception "CheckedOutException"
 *	@author JacORB IDL compiler 
 */

public final class CheckedOutException
	extends org.omg.CORBA.UserException
{
	public CheckedOutException()
	{
		super(TTLIBRARY.CheckedOutExceptionHelper.id());
	}

	public int mErrorCode;
	public java.lang.String mErrorDescription = "";
	public CheckedOutException(java.lang.String _reason,int mErrorCode, java.lang.String mErrorDescription)
	{
		super(TTLIBRARY.CheckedOutExceptionHelper.id()+ " " + _reason);
		this.mErrorCode = mErrorCode;
		this.mErrorDescription = mErrorDescription;
	}
	public CheckedOutException(int mErrorCode, java.lang.String mErrorDescription)
	{
		super(TTLIBRARY.CheckedOutExceptionHelper.id());
		this.mErrorCode = mErrorCode;
		this.mErrorDescription = mErrorDescription;
	}
}
