package TTRETRIEVAL;

import TTRETRIEVAL.DocReader;

/**
 *	Generated from IDL interface "DocReader"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class DocReaderHelper
{
	public static void insert (final org.omg.CORBA.Any any, final TTRETRIEVAL.DocReader s)
	{
			any.insert_Object(s);
	}
	public static TTRETRIEVAL.DocReader extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static org.omg.CORBA.TypeCode type()
	{
		return org.omg.CORBA.ORB.init().create_interface_tc("IDL:TTRETRIEVAL/DocReader:1.0", "DocReader");
	}
	public static String id()
	{
		return "IDL:TTRETRIEVAL/DocReader:1.0";
	}
	public static DocReader read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object());
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final TTRETRIEVAL.DocReader s)
	{
		_out.write_Object(s);
	}
	public static TTRETRIEVAL.DocReader narrow(final java.lang.Object obj)
	{
		if (obj instanceof TTRETRIEVAL.DocReader)
		{
			return (TTRETRIEVAL.DocReader)obj;
		}
		else if (obj instanceof org.omg.CORBA.Object)
		{
			return narrow((org.omg.CORBA.Object)obj);
		}
		throw new org.omg.CORBA.BAD_PARAM("Failed to narrow in helper");
	}
	public static TTRETRIEVAL.DocReader narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (TTRETRIEVAL.DocReader)obj;
		}
		catch (ClassCastException c)
		{
			if (obj._is_a("IDL:TTRETRIEVAL/DocReader:1.0"))
			{
				TTRETRIEVAL._DocReaderStub stub;
				stub = new TTRETRIEVAL._DocReaderStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
			}
		}
		throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
	}
	public static TTRETRIEVAL.DocReader unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (TTRETRIEVAL.DocReader)obj;
		}
		catch (ClassCastException c)
		{
				TTRETRIEVAL._DocReaderStub stub;
				stub = new TTRETRIEVAL._DocReaderStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
		}
	}
}
