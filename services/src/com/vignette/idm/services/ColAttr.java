////////////////////////////////////////////////////////////////////////////////
//	Title		:	ColAttr.java
//
//	Description	:	Class which stores attributes of a database table column.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.services;

public class ColAttr
{
	/**
	 * 	Definitions of ODBC SQL data types.
	 *	Straight mapping of types from sql.h and sqlext.h.
	 */
	public static final int SQL_CHAR			=  1;
	public static final int SQL_NUMERIC			=  2;
	public static final int SQL_DECIMAL			=  3;
	public static final int SQL_INTEGER			=  4;
	public static final int SQL_SMALLINT		=  5;
	public static final int SQL_FLOAT			=  6;
	public static final int SQL_DATE			=  9;
	public static final int SQL_TIME			= 10;
	public static final int SQL_TIMESTAMP		= 11;
	public static final int SQL_VARCHAR			= 12;
	public static final int SQL_TYPE_DATE		= 91;
	public static final int SQL_TYPE_TIME		= 92;
	public static final int SQL_TYPE_TIMESTAMP	= 93;

	/**
	 *	The list of attributes we are interested in about a column.
	 */
	private String	mName;
	private String	mFdesc;
	private int		mFtype;
	private int		mFlen;
	private int		mFprecision;
	private String	mFdefault;
	private boolean	mFrequired;
	private boolean	mFupdateable;
	private boolean	mFindexed;
	private boolean	mFsearchable;

	/**
	 *	Class constructor accepting all attributes of the column.
	 */
	ColAttr
	(
		String	name,
		String	desc,
		int		ftype,
		int		flen,
		int		fprecision,
		String	fdefault,
		boolean	frequired,
		boolean	fupdateable,
		boolean	findexed,
		boolean	fsearchable
	)
	{
		mName = name;
		mFdesc = desc;
		mFtype = ftype;
		mFlen = flen;
		mFdefault = fdefault;
		mFrequired = frequired;
		mFprecision = fprecision;
		mFupdateable = fupdateable;
		mFindexed = findexed;
		mFsearchable = fsearchable;
	}

	/**
	 *	Obtain the column name as it appears in the database table.
	 */
	public String getName()
	{
		return mName;
	}

	/**
	 *	Obtain a short description of the column.
	 *	Commonly referred to as the long name.
	 */
	public String getDescription()
	{
		return mFdesc;
	}

	/**
	 *	Obtain the ODBC SQL type of the column.
	 */
	public int getFieldType()
	{
		return mFtype;
	}

	/**
	 *	Obtain the display size of the column.
	 *	It is the number of characters required to
	 *	display a value of this column.
	 */
	public int getLength()
	{
		return mFlen;
	}

	/**
	 *	Obtain the precision or the number of decimal places of the column.
	 *	The IDM repository does not support decimal numbers,
	 *	therefore the return value is always zero.
	 */
	public int getPrecision()
	{
		return mFprecision;
	}

	/**
	 *	Obtain the default value of the column.
	 *	A blank string means the default value is a null.
	 */
	public String getDefaultValue()
	{
		return mFdefault;
	}

	/**
	 *	Whether a value is mandatory in insert statements.
	 *	This is a case of a column allowing nulls or not.
	 */
	public boolean getRequired()
	{
		return mFrequired;
	}

	/**
	 *	Whether the values in this column can be updated.
	 */
	public boolean getUpdateable()
	{
		return mFupdateable;
	}

	/**
	 *	Whether the column is indexed.
	 */
	public boolean getIndexed()
	{
		return mFindexed;
	}

	/**
	 *	Whether the column can be used in a select statement for searching.
	 */
	public boolean getSearchable()
	{
		return mFsearchable;
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
