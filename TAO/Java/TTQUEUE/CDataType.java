package TTQUEUE;

import TTQUEUE.CDataType;

/**
 *	Generated from IDL definition of enum "CDataType"
 *	@author JacORB IDL compiler 
 */

public final class CDataType
	implements org.omg.CORBA.portable.IDLEntity
{
	private int value = -1;
	public static final int _QCHAR = 0;
	public static final CDataType QCHAR = new CDataType(_QCHAR);
	public static final int _INTEGER = 1;
	public static final CDataType INTEGER = new CDataType(_INTEGER);
	public static final int _SMALLINT = 2;
	public static final CDataType SMALLINT = new CDataType(_SMALLINT);
	public static final int _DATE = 3;
	public static final CDataType DATE = new CDataType(_DATE);
	public static final int _TIME = 4;
	public static final CDataType TIME = new CDataType(_TIME);
	public static final int _DATETIME = 5;
	public static final CDataType DATETIME = new CDataType(_DATETIME);
	public static final int _CUST00 = 6;
	public static final CDataType CUST00 = new CDataType(_CUST00);
	public static final int _CUST01 = 7;
	public static final CDataType CUST01 = new CDataType(_CUST01);
	public static final int _CUST02 = 8;
	public static final CDataType CUST02 = new CDataType(_CUST02);
	public static final int _CUST03 = 9;
	public static final CDataType CUST03 = new CDataType(_CUST03);
	public static final int _CUST04 = 10;
	public static final CDataType CUST04 = new CDataType(_CUST04);
	public static final int _CUST05 = 11;
	public static final CDataType CUST05 = new CDataType(_CUST05);
	public int value()
	{
		return value;
	}
	public static CDataType from_int(int value)
	{
		switch (value) {
			case _QCHAR: return QCHAR;
			case _INTEGER: return INTEGER;
			case _SMALLINT: return SMALLINT;
			case _DATE: return DATE;
			case _TIME: return TIME;
			case _DATETIME: return DATETIME;
			case _CUST00: return CUST00;
			case _CUST01: return CUST01;
			case _CUST02: return CUST02;
			case _CUST03: return CUST03;
			case _CUST04: return CUST04;
			case _CUST05: return CUST05;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected CDataType(int i)
	{
		value = i;
	}
	java.lang.Object readResolve()
	throws java.io.ObjectStreamException
	{
		return from_int(value());
	}
}
