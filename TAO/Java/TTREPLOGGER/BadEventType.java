package TTREPLOGGER;

/**
 *	Generated from IDL definition of exception "BadEventType"
 *	@author JacORB IDL compiler 
 */

public final class BadEventType
	extends org.omg.CORBA.UserException
{
	public BadEventType()
	{
		super(TTREPLOGGER.BadEventTypeHelper.id());
	}

	public int mEventType;
	public java.lang.String mErrorDescription = "";
	public BadEventType(java.lang.String _reason,int mEventType, java.lang.String mErrorDescription)
	{
		super(TTREPLOGGER.BadEventTypeHelper.id()+ " " + _reason);
		this.mEventType = mEventType;
		this.mErrorDescription = mErrorDescription;
	}
	public BadEventType(int mEventType, java.lang.String mErrorDescription)
	{
		super(TTREPLOGGER.BadEventTypeHelper.id());
		this.mEventType = mEventType;
		this.mErrorDescription = mErrorDescription;
	}
}
