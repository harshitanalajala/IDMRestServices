package TTSECURITY;

/**
 *	Generated from IDL definition of exception "BadLicenseTypeException"
 *	@author JacORB IDL compiler 
 */

public final class BadLicenseTypeException
	extends org.omg.CORBA.UserException
{
	public BadLicenseTypeException()
	{
		super(TTSECURITY.BadLicenseTypeExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public BadLicenseTypeException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTSECURITY.BadLicenseTypeExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public BadLicenseTypeException(int errorCode, java.lang.String errorDescription)
	{
		super(TTSECURITY.BadLicenseTypeExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
