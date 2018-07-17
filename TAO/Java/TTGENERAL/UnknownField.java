package TTGENERAL;

/**
 *	Generated from IDL definition of exception "UnknownField"
 *	@author JacORB IDL compiler 
 */

public final class UnknownField
	extends org.omg.CORBA.UserException
{
	public UnknownField()
	{
		super(TTGENERAL.UnknownFieldHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public UnknownField(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTGENERAL.UnknownFieldHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public UnknownField(int errorCode, java.lang.String errorDescription)
	{
		super(TTGENERAL.UnknownFieldHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
