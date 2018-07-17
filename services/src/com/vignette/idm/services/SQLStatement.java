////////////////////////////////////////////////////////////////////////////////
//	Title		:	SQLStatement.java
//
//	Description	:	Class which stores/formats SQL statement.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.services;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import java.util.ArrayList;

public class SQLStatement
{
	/**
	 * The SQL String
	 */
	private StringBuffer mSqlString;
	/**
	 * A list of parameters corresponding to the SQL statement
	 */
	private ArrayList mParameterList;

	/**
	 * Constuctor
	 * Create the new parameter list
	 */
	public SQLStatement()
	{
		mSqlString = new StringBuffer();
		mParameterList = new ArrayList();
	}

	/**
	 * Constuctor
	 * Create the new parameter list and initialise the SQL String
	 *
	 * @params aStatement - initialisation string
	 */
	public SQLStatement(String aStatement)
	{
		mSqlString = new StringBuffer(aStatement);
		mParameterList = new ArrayList();
	}

	/**
	 * Returns the SQL statement.
	 *
	 * @return string component of the SQL statement
	 */
	public String getSQLStatement()
	{
		return mSqlString.toString();
	}

	/**
	 * Returns the parameter list of the corresponding SQL statement.
	 *
	 * @return the parameters component of the SQL statement
	 */
	public ArrayList getParameters()
	{
		return mParameterList;
	}

	/**
	 * Returns a boolean value to indicated if the parameter list
	 * of this SQL statement is empty.
	 *
	 * @return true if the list is empty, otherwise return false
	 */
	public boolean isEmptyParameter()
	{
		return mParameterList.isEmpty();
	}

	/**
 	 * Appends the string to this SQL statement.
	 *
 	 * @params aStatement - string to be appended
 	 */
	public void append(String aStatement)
	{
		if (aStatement != null)
		{
			mSqlString.append(aStatement);
		}
	}

	/**
 	 * Appends the parameter value to this sql statement object.
	 *
 	 * @params aColName - column name of the parameter
 	 * @params aColValue - String value of the parameter
 	 * @params aColumns - column attributes of all the parameters
 	 */
	public void append(String aColName, String aColValue, ColumnDef aColumns)
	{
		String value = aColumns.formatValue(aColName, aColValue);
		if (!value.equalsIgnoreCase("null"))
		{
			ColAttr columnAttr = aColumns.getColumnAttr(aColName);
			if (columnAttr != null)
			{
				ParameterItem pObj = null;
				switch (columnAttr.getFieldType())
				{
				case ColAttr.SQL_DATE:
				case ColAttr.SQL_TYPE_DATE:
					pObj = new ParameterItem
					(
						ColAttr.SQL_DATE,
						Date.valueOf(value)
					);
					mParameterList.add(pObj);
					value = "?";
					break;

				case ColAttr.SQL_TIME:
				case ColAttr.SQL_TYPE_TIME:
					pObj = new ParameterItem
					(
						ColAttr.SQL_TIME,
						Time.valueOf(value)
					);
					mParameterList.add(pObj);
					value = "?";
					break;

				case ColAttr.SQL_TIMESTAMP:
				case ColAttr.SQL_TYPE_TIMESTAMP:
					Date date = Date.valueOf(value);
					Timestamp tmStamp = new Timestamp(date.getTime());
					pObj = new ParameterItem
					(
						ColAttr.SQL_TIMESTAMP,
						tmStamp
					);
					mParameterList.add(pObj);
					value = "?";
					break;
				}
			}
		}
		this.append(value);
	}
}

/**
 *	Description
 *	<p>
 * The <code>ParameterItem</code> class is used by the <code>SQLStatement</code>
 * class to hold the parameter value of the SQL Statement within
 * its parameter list.
 * The <code>ParameterItem</code> class is a friendly class and is only
 * avaliable within the <code>com.towertechnology.idm.services</code> package.
 *	</p>
 */
class ParameterItem
{
	/**
	 * The generic SQL types of the parameter.
	 * The actual type constant values are equivalent to those in XOPEN.
	 */
	private int mParameterType;

	/**
	 * The generic value of the parameter.
	 */
	private Object mParameterValue;

	/**
	 * Constuctor
	 * initialise the Parameter's type and value
	 *
	 * @params aParamType - parameter type
	 * @params aParamValue - parameter value
	 */
	public ParameterItem(int aParamType, Object aParamValue)
	{
		mParameterType = aParamType;
		mParameterValue = aParamValue;
	}

	/**
	 * Returns the generic SQL of the parameter
	 *
	 * @return SQL type equivalent to those in XOPEN
	 */
	public int getParameterType()
	{
		return mParameterType;
	}

	/**
	 * Returns the value of the parameter
	 *
	 * @return java base Object
	 */
	public Object getParameterValue()
	{
		return mParameterValue;
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
