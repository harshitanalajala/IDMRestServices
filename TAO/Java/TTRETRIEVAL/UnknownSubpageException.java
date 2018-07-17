package TTRETRIEVAL;

/**
 *	Generated from IDL definition of exception "UnknownSubpageException"
 *	@author JacORB IDL compiler 
 */

public final class UnknownSubpageException
	extends org.omg.CORBA.UserException
{
	public UnknownSubpageException()
	{
		super(TTRETRIEVAL.UnknownSubpageExceptionHelper.id());
	}

	public int mErrorCode;
	public java.lang.String mErrorDescription = "";
	public UnknownSubpageException(java.lang.String _reason,int mErrorCode, java.lang.String mErrorDescription)
	{
		super(TTRETRIEVAL.UnknownSubpageExceptionHelper.id()+ " " + _reason);
		this.mErrorCode = mErrorCode;
		this.mErrorDescription = mErrorDescription;
	}
	public UnknownSubpageException(int mErrorCode, java.lang.String mErrorDescription)
	{
		super(TTRETRIEVAL.UnknownSubpageExceptionHelper.id());
		this.mErrorCode = mErrorCode;
		this.mErrorDescription = mErrorDescription;
	}
}
