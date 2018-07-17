package TTANNOTATIONS;

/**
 *	Generated from IDL definition of exception "AnnotationException"
 *	@author JacORB IDL compiler 
 */

public final class AnnotationException
	extends org.omg.CORBA.UserException
{
	public AnnotationException()
	{
		super(TTANNOTATIONS.AnnotationExceptionHelper.id());
	}

	public int errorCode;
	public java.lang.String errorDescription = "";
	public AnnotationException(java.lang.String _reason,int errorCode, java.lang.String errorDescription)
	{
		super(TTANNOTATIONS.AnnotationExceptionHelper.id()+ " " + _reason);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public AnnotationException(int errorCode, java.lang.String errorDescription)
	{
		super(TTANNOTATIONS.AnnotationExceptionHelper.id());
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
}
