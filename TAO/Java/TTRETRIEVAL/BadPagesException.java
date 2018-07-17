package TTRETRIEVAL;

/**
 *	Generated from IDL definition of exception "BadPagesException"
 *	@author JacORB IDL compiler 
 */

public final class BadPagesException
	extends org.omg.CORBA.UserException
{
	public BadPagesException()
	{
		super(TTRETRIEVAL.BadPagesExceptionHelper.id());
	}

	public int mErrorCode;
	public java.lang.String mErrorDescription = "";
	public BadPagesException(java.lang.String _reason,int mErrorCode, java.lang.String mErrorDescription)
	{
		super(TTRETRIEVAL.BadPagesExceptionHelper.id()+ " " + _reason);
		this.mErrorCode = mErrorCode;
		this.mErrorDescription = mErrorDescription;
	}
	public BadPagesException(int mErrorCode, java.lang.String mErrorDescription)
	{
		super(TTRETRIEVAL.BadPagesExceptionHelper.id());
		this.mErrorCode = mErrorCode;
		this.mErrorDescription = mErrorDescription;
	}
}
