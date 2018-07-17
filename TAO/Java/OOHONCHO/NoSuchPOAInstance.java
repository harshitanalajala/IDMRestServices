package OOHONCHO;

/**
 *	Generated from IDL definition of exception "NoSuchPOAInstance"
 *	@author JacORB IDL compiler 
 */

public final class NoSuchPOAInstance
	extends org.omg.CORBA.UserException
{
	public NoSuchPOAInstance()
	{
		super(OOHONCHO.NoSuchPOAInstanceHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public NoSuchPOAInstance(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(OOHONCHO.NoSuchPOAInstanceHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public NoSuchPOAInstance(int errorCode, java.lang.String errorDescription)
	{
		super(OOHONCHO.NoSuchPOAInstanceHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
