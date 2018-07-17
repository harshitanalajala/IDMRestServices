////////////////////////////////////////////////////////////////////////////////
//	Title		:	ApplicationEntry.java
//
//	Description	:	Class which holds data for an application table entry.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.common;

public class ApplicationEntry
{
	private String mLongName;
	private String mShortName;
	private String mDiskset;
	private int mAclIndex;
	private int mFlags;

	/**
  	 * This constructor takes all details of an application entry. 
	 * and initializes the private members.
  	 */
	public ApplicationEntry
	(
		String	aLongName,
		String	aShortName,
		String	aDiskset,
		int		aAclIndex,
		int		aFlags
	)
	{
		mLongName = aLongName;
		mShortName = aShortName;
		mDiskset = aDiskset;
		mAclIndex = aAclIndex;
		mFlags = aFlags;
	}

 	/**
  	 * Gets the description of this application entry.
  	 * @return Description of an application.
  	 */
	public String getLongName()
	{
		return mLongName;
	}

	/**
  	 * Gets the short name of an application
  	 * @return short name of an application
  	 */
	public String getShortName()
	{
		return mShortName;
	}

	/**
  	 * Gets the diskset that the application is link to.
  	 * @return a 32-bit integer number representing a diskset.
  	 */
	public String getDiskset()
	{
		return mDiskset;
	}

	/**
  	 * Gets the index of the ACL that the application is assigned to.
  	 * @return an integer value representing the index of the ACL.
  	 */
	public int getAclIndex()
	{
		return mAclIndex;
	}

	/**
	 * Gets appplication flags.
	 * @return application flags
	 */
	public int getFlags()
	{
		return mFlags;
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
