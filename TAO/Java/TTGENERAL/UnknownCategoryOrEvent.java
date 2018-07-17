package TTGENERAL;

/**
 *	Generated from IDL definition of exception "UnknownCategoryOrEvent"
 *	@author JacORB IDL compiler 
 */

public final class UnknownCategoryOrEvent
	extends org.omg.CORBA.UserException
{
	public UnknownCategoryOrEvent()
	{
		super(TTGENERAL.UnknownCategoryOrEventHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public UnknownCategoryOrEvent(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTGENERAL.UnknownCategoryOrEventHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public UnknownCategoryOrEvent(int errorCode, java.lang.String errorDescription)
	{
		super(TTGENERAL.UnknownCategoryOrEventHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
