package TTRETRIEVAL;

/**
 *	Generated from IDL definition of exception "BadParamException"
 *	@author JacORB IDL compiler 
 */

public final class BadParamException
	extends org.omg.CORBA.UserException
{
	public BadParamException()
	{
		super(TTRETRIEVAL.BadParamExceptionHelper.id());
	}

	public int mErrorCode;
	public java.lang.String mErrorDescription = "";
	public BadParamException(java.lang.String _reason,int mErrorCode, java.lang.String mErrorDescription)
	{
		super(TTRETRIEVAL.BadParamExceptionHelper.id()+ " " + _reason);
		this.mErrorCode = mErrorCode;
		this.mErrorDescription = mErrorDescription;
	}
	public BadParamException(int mErrorCode, java.lang.String mErrorDescription)
	{
		super(TTRETRIEVAL.BadParamExceptionHelper.id());
		this.mErrorCode = mErrorCode;
		this.mErrorDescription = mErrorDescription;
	}
}
