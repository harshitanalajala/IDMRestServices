package TTLIBRARY;

/**
 *	Generated from IDL definition of struct "CheckOutDetail"
 *	@author JacORB IDL compiler 
 */

public final class CheckOutDetail
	implements org.omg.CORBA.portable.IDLEntity
{
	public CheckOutDetail(){}
	public java.lang.String mUserID = "";
	public java.lang.String mDate = "";
	public java.lang.String mTime = "";
	public java.lang.String mComment = "";
	public CheckOutDetail(java.lang.String mUserID, java.lang.String mDate, java.lang.String mTime, java.lang.String mComment)
	{
		this.mUserID = mUserID;
		this.mDate = mDate;
		this.mTime = mTime;
		this.mComment = mComment;
	}
}
