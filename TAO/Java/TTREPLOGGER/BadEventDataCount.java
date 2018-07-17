package TTREPLOGGER;

/**
 *	Generated from IDL definition of exception "BadEventDataCount"
 *	@author JacORB IDL compiler 
 */

public final class BadEventDataCount
	extends org.omg.CORBA.UserException
{
	public BadEventDataCount()
	{
		super(TTREPLOGGER.BadEventDataCountHelper.id());
	}

	public int mEventDataCount;
	public java.lang.String mErrorDescription = "";
	public BadEventDataCount(java.lang.String _reason,int mEventDataCount, java.lang.String mErrorDescription)
	{
		super(TTREPLOGGER.BadEventDataCountHelper.id()+ " " + _reason);
		this.mEventDataCount = mEventDataCount;
		this.mErrorDescription = mErrorDescription;
	}
	public BadEventDataCount(int mEventDataCount, java.lang.String mErrorDescription)
	{
		super(TTREPLOGGER.BadEventDataCountHelper.id());
		this.mEventDataCount = mEventDataCount;
		this.mErrorDescription = mErrorDescription;
	}
}
