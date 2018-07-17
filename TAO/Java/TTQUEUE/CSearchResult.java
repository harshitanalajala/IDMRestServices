package TTQUEUE;

/**
 *	Generated from IDL definition of struct "CSearchResult"
 *	@author JacORB IDL compiler 
 */

public final class CSearchResult
	implements org.omg.CORBA.portable.IDLEntity
{
	public CSearchResult(){}
	public java.lang.String[] fieldNames;
	public TTQUEUE.CNameValue[][] rows;
	public CSearchResult(java.lang.String[] fieldNames, TTQUEUE.CNameValue[][] rows)
	{
		this.fieldNames = fieldNames;
		this.rows = rows;
	}
}
