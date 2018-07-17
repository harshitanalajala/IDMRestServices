package TTRETRIEVAL;

/**
 *	Generated from IDL definition of struct "RenditionType"
 *	@author JacORB IDL compiler 
 */

public final class RenditionType
	implements org.omg.CORBA.portable.IDLEntity
{
	public RenditionType(){}
	public java.lang.String mRenditionName = "";
	public java.lang.String mMimeType = "";
	public short mZoomFactor;
	public RenditionType(java.lang.String mRenditionName, java.lang.String mMimeType, short mZoomFactor)
	{
		this.mRenditionName = mRenditionName;
		this.mMimeType = mMimeType;
		this.mZoomFactor = mZoomFactor;
	}
}
