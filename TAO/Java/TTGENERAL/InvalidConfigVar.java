package TTGENERAL;

/**
 *	Generated from IDL definition of exception "InvalidConfigVar"
 *	@author JacORB IDL compiler 
 */

public final class InvalidConfigVar
	extends org.omg.CORBA.UserException
{
	public InvalidConfigVar()
	{
		super(TTGENERAL.InvalidConfigVarHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public InvalidConfigVar(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTGENERAL.InvalidConfigVarHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public InvalidConfigVar(int errorCode, java.lang.String errorDescription)
	{
		super(TTGENERAL.InvalidConfigVarHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
