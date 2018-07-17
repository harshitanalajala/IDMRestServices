package TTLIBRARY;

/**
 *	Generated from IDL definition of exception "NotOwnerException"
 *	@author JacORB IDL compiler 
 */

public final class NotOwnerException
	extends org.omg.CORBA.UserException
{
	public NotOwnerException()
	{
		super(TTLIBRARY.NotOwnerExceptionHelper.id());
	}

	public int mErrorCode;
	public java.lang.String mErrorDescription = "";
	public NotOwnerException(java.lang.String _reason,int mErrorCode, java.lang.String mErrorDescription)
	{
		super(TTLIBRARY.NotOwnerExceptionHelper.id()+ " " + _reason);
		this.mErrorCode = mErrorCode;
		this.mErrorDescription = mErrorDescription;
	}
	public NotOwnerException(int mErrorCode, java.lang.String mErrorDescription)
	{
		super(TTLIBRARY.NotOwnerExceptionHelper.id());
		this.mErrorCode = mErrorCode;
		this.mErrorDescription = mErrorDescription;
	}
}
