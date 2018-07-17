////////////////////////////////////////////////////////////////////////////////
//	Title		:	ColumnDef.java
//
//	Description	:	Class which stores all column attributes of a database
//					table.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.services;

import java.util.logging.Level;

import com.vignette.idm.common.StringBufferS;

import java.util.ArrayList;
import java.util.Hashtable;

public class ColumnDef
{
	/**
	 *	We use this variable to provide a mapping of column name
	 *	to array index of column details. This is so we can maintain
	 *	the same order of columns as retrieved from the database. This
	 *	is not possible if they were stored in a hashtable or map.
	 */
	private Hashtable colAttrList;

	/**
	 *	A list of columns and their attributes stored in an array
	 */
	private ArrayList colArrayList;

	private static java.util.logging.Logger mLogger =
		java.util.logging.Logger.getLogger
		(
			"com.vignette.idm.services.ColumnDef"
		);

	/**
	 *	Create a new column definition list
	 */
	public ColumnDef()
	{
		colAttrList = new Hashtable();
		colArrayList = new ArrayList();
	}

	/**
	 *	Checks the input string for single quotes
	 *	and insert an extra quote for each instance found
	 */
	private String fixStr(String strToFix)
	{
		StringBuffer	sb = new StringBuffer();
		char			c;

		for (int i = 0; i < strToFix.length(); i++)
		{
			c = strToFix.charAt(i);
			if (c == '\'')
			{
				sb.append(c);
			}
			sb.append(c);
		}

		return sb.toString();
	}

	/**
	 *	Print the list of columns in the table to standard output
	 */
	public void print()
	{
		for (int i = 0; i < colArrayList.size(); i++)
		{
			ColAttr ca = (ColAttr)colArrayList.get(i);
			System.out.print(ca.getName());
			System.out.print("\t\t");
			System.out.print(ca.getFieldType());
			System.out.print("\t\t");
			System.out.print(ca.getLength());
			System.out.print("\t\t");
			System.out.print(ca.getDefaultValue());
			System.out.print("\t\t");
			System.out.print(ca.getRequired());
			System.out.print("\n");
		}
	}

	/**
	 *	Returns an ArrayList of ColAttr objects
	 *	Query each ColAttr object for column attributes
	 */
	public ArrayList getColumnList()
	{
		return colArrayList;
	}

	/**
	 *	Add a column and its attributes to the list
	 */
	public void addColAttr
	(
		String	fname,
		String	fdesc,
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
		if (mLogger.isLoggable(Level.INFO))
		{
			StringBufferS msg = new StringBufferS("addColAttr() ");
			msg.append("fname=", fname, " ");
			msg.append("fdesc=", fdesc, " ");
			msg.append("ftype=", ftype, " ");
			mLogger.info(msg.toString());
		}

		// Note that the hash key is based on the lowercase version of fname
		// but when storing it in ColAttr, fname can be in lowercase or
		// uppercase.
		ColAttr ca = new ColAttr
		(
			fname,
			fdesc,
			ftype,
			flen,
			fprecision,
			fdefault,
			frequired,
			fupdateable,
			findexed,
			fsearchable
		);

		if (!colAttrList.containsKey(fname.toLowerCase()))
		{
			colAttrList.put
			(
				fname.toLowerCase(), new Integer(colArrayList.size())
			);
			colArrayList.add(ca);
		}
	}

	/**
	 *	Format a value of a column with respect to the column type
	 *	This is required when constructing an SQL statement
	 *	For example, string, date and time values return with single
	 *	quotes enclosed.
	 */
	public String formatValue(String fname, String value)
	{
		if (mLogger.isLoggable(Level.INFO))
		{
			StringBufferS msg = new StringBufferS("formatValue() ");
			msg.append("fname=", fname, " ");
			msg.append("value=", value, " ");
			mLogger.info(msg.toString());
		}

		if (value == null)
		{
			return "null";
		}

		String modifiedValue = value;
		Integer arrayIndex = (Integer)colAttrList.get(fname.toLowerCase());
		if (arrayIndex == null)
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS("fname ");
				msg.append("fname not in colAttrList", "", " ");
				mLogger.info(msg.toString());
			}

			return modifiedValue;
		}
		ColAttr ca = (ColAttr)colArrayList.get(arrayIndex.intValue());
		if (ca != null)
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS("fieldType ");
				msg.append("ca.getFieldType()=", ca.getFieldType(), "");
				msg.append("value=", value, "");
				mLogger.info(msg.toString());
			}

			switch (ca.getFieldType())
			{
				case ColAttr.SQL_NUMERIC:
				case ColAttr.SQL_DECIMAL:
				case ColAttr.SQL_INTEGER:
				case ColAttr.SQL_SMALLINT:
				case ColAttr.SQL_FLOAT:
				case ColAttr.SQL_DATE:
				case ColAttr.SQL_TIME:
				case ColAttr.SQL_TIMESTAMP:
				case ColAttr.SQL_TYPE_DATE:
				case ColAttr.SQL_TYPE_TIME:
				case ColAttr.SQL_TYPE_TIMESTAMP:
					modifiedValue = value;
					break;
				default:
					modifiedValue = "'" + fixStr(value) + "'";
					break;
			}
		}
		if (mLogger.isLoggable(Level.INFO))
		{
			StringBufferS msg = new StringBufferS(" ");
			msg.append("modifiedValue=", modifiedValue, "");
			mLogger.info(msg.toString());
		}
		return modifiedValue;
	}

	/**
	 *	Returns an ColAttr objects from the Column list
	 *	based on the given column name
	 *	otherwise it will return a null.
	 */
	public ColAttr getColumnAttr(String fname)
	{
		ColAttr ca = null;

		Integer arrayIndex = (Integer)colAttrList.get(fname.toLowerCase());
		if (arrayIndex != null)
		{
			ca = (ColAttr)colArrayList.get(arrayIndex.intValue());
		}
		return ca;
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
