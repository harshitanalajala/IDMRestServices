package TTDOCTYPE;

import TTDOCTYPE.DocTypeSecurity;
import TTDOCTYPE.DocTypeSecurityHelper;

/**
 *	Generated from IDL interface "DocTypeSecurity"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class DocTypeSecurityHolder	implements org.omg.CORBA.portable.Streamable{
	 public DocTypeSecurity value;
	public DocTypeSecurityHolder()
	{
	}
	public DocTypeSecurityHolder (final DocTypeSecurity initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type()
	{
		return DocTypeSecurityHelper.type();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = DocTypeSecurityHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		DocTypeSecurityHelper.write (_out,value);
	}
}
