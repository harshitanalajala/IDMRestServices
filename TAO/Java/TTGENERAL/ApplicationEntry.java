package TTGENERAL;

/**
 *	Generated from IDL definition of struct "ApplicationEntry"
 *	@author JacORB IDL compiler 
 */

public final class ApplicationEntry
	implements org.omg.CORBA.portable.IDLEntity
{
	public ApplicationEntry(){}
	public java.lang.String mName = "";
	public java.lang.String mShortName = "";
	public java.lang.String mDiskset = "";
	public int mAclIndex;
	public int mFlags;
	public ApplicationEntry(java.lang.String mName, java.lang.String mShortName, java.lang.String mDiskset, int mAclIndex, int mFlags)
	{
		this.mName = mName;
		this.mShortName = mShortName;
		this.mDiskset = mDiskset;
		this.mAclIndex = mAclIndex;
		this.mFlags = mFlags;
	}
}
