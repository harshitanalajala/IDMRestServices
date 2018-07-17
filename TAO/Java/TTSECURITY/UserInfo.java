package TTSECURITY;

/**
 *	Generated from IDL definition of struct "UserInfo"
 *	@author JacORB IDL compiler 
 */

public final class UserInfo
	implements org.omg.CORBA.portable.IDLEntity
{
	public UserInfo(){}
	public java.lang.String mUserID = "";
	public java.lang.String mSessionID = "";
	public java.lang.String mForLog = "";
	public int mLoginTime;
	public int mLastAccessTime;
	public int mTimeoutTime;
	public UserInfo(java.lang.String mUserID, java.lang.String mSessionID, java.lang.String mForLog, int mLoginTime, int mLastAccessTime, int mTimeoutTime)
	{
		this.mUserID = mUserID;
		this.mSessionID = mSessionID;
		this.mForLog = mForLog;
		this.mLoginTime = mLoginTime;
		this.mLastAccessTime = mLastAccessTime;
		this.mTimeoutTime = mTimeoutTime;
	}
}
