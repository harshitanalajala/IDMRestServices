package TTLIBRARY;

/**
 *	Generated from IDL definition of exception "NotCheckedOutException"
 *	@author JacORB IDL compiler 
 */

public final class NotCheckedOutException
	extends org.omg.CORBA.UserException
{
	public NotCheckedOutException()
	{
		super(TTLIBRARY.NotCheckedOutExceptionHelper.id());
	}

	public int mErrorCode;
	public java.lang.String mErrorDescription = "";
	public NotCheckedOutException(java.lang.String _reason,int mErrorCode, java.lang.String mErrorDescription)
	{
		super(TTLIBRARY.NotCheckedOutExceptionHelper.id()+ " " + _reason);
		this.mErrorCode = mErrorCode;
		this.mErrorDescription = mErrorDescription;
	}
	public NotCheckedOutException(int mErrorCode, java.lang.String mErrorDescription)
	{
		super(TTLIBRARY.NotCheckedOutExceptionHelper.id());
		this.mErrorCode = mErrorCode;
		this.mErrorDescription = mErrorDescription;
	}
}
