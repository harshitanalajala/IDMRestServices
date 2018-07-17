package OOHONCHO;

/**
 *	Generated from IDL definition of exception "NoSuchPOAtypeException"
 *	@author JacORB IDL compiler 
 */

public final class NoSuchPOAtypeException
	extends org.omg.CORBA.UserException
{
	public NoSuchPOAtypeException()
	{
		super(OOHONCHO.NoSuchPOAtypeExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public NoSuchPOAtypeException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(OOHONCHO.NoSuchPOAtypeExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public NoSuchPOAtypeException(int errorCode, java.lang.String errorDescription)
	{
		super(OOHONCHO.NoSuchPOAtypeExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
