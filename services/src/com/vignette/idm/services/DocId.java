////////////////////////////////////////////////////////////////////////////////
//	Title		:	DocId.java
//
//	Description	:	Class which holds an IFN.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.services;

public class DocId
{
	private long mIfnds;
	private long mIfnid;

	/**
	 *	Constructor accepting diskset and id
	 *	in numeric form
	 */
	public DocId(long anIfnds, long anIfnid)
	{
		mIfnds = anIfnds;
		mIfnid = anIfnid;
	}

	/**
	 *	Constructor accepting diskset and id
	 *	in String form
	 */
	public DocId(String anIfnds, String anIfnid)
	{
		try
		{
			mIfnds = Long.parseLong(anIfnds);
			mIfnid = Long.parseLong(anIfnid);
		}
		catch (NumberFormatException e)
		{
			mIfnds = 0;
			mIfnid = 0;
		}
	}

	/**
	 *	Constructor accepting IFN as a String
	 *	in the format SSN/DS-ID
	 */
	public DocId(String anIfn)
	{
		try
		{
			int indexOfSlash = anIfn.indexOf('/');
			int indexOfHyphen = anIfn.indexOf('-');

			String ssn = anIfn.substring(0, indexOfSlash);
			String ds = anIfn.substring(indexOfSlash + 1, indexOfHyphen);
			String id = anIfn.substring(indexOfHyphen + 1);

			mIfnds = (Long.parseLong(ssn) * 65536) + Long.parseLong(ds);
			mIfnid = Long.parseLong(id);
		}
		catch (Exception e)
		{
			mIfnds = 0;
			mIfnid = 0;
		}
	}

	/**
	 *	Return the diskset value
	 */
	public long getDiskset()
	{
		return mIfnds;
	}

	/**
	 *	Return the id value of the IFN
	 */
	public long getID()
	{
		return mIfnid;
	}

	/**
	 *	Return the IFN in SSN/DS-ID format
	 */
	@Override
	public String toString()
	{
		if (mIfnds == 0)
		{
			return new String("");
		}

		String SSN = String.valueOf(mIfnds / 65536);
		String DS = String.valueOf(mIfnds % 65536);

		String result = SSN + "/" + DS + "-" + mIfnid;

		return result;
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
