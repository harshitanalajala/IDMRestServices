package TTLIBRARY;

/**
 *	Generated from IDL definition of exception "DatabaseException"
 *	@author JacORB IDL compiler 
 */

public final class DatabaseException
	extends org.omg.CORBA.UserException
{
	public DatabaseException()
	{
		super(TTLIBRARY.DatabaseExceptionHelper.id());
	}

	public int mErrorCode;
	public java.lang.String mErrorDescription = "";
	public DatabaseException(java.lang.String _reason,int mErrorCode, java.lang.String mErrorDescription)
	{
		super(TTLIBRARY.DatabaseExceptionHelper.id()+ " " + _reason);
		this.mErrorCode = mErrorCode;
		this.mErrorDescription = mErrorDescription;
	}
	public DatabaseException(int mErrorCode, java.lang.String mErrorDescription)
	{
		super(TTLIBRARY.DatabaseExceptionHelper.id());
		this.mErrorCode = mErrorCode;
		this.mErrorDescription = mErrorDescription;
	}
}
