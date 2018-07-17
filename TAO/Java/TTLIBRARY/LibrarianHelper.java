package TTLIBRARY;

import TTLIBRARY.Librarian;

/**
 *	Generated from IDL interface "Librarian"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class LibrarianHelper
{
	public static void insert (final org.omg.CORBA.Any any, final TTLIBRARY.Librarian s)
	{
			any.insert_Object(s);
	}
	public static TTLIBRARY.Librarian extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static org.omg.CORBA.TypeCode type()
	{
		return org.omg.CORBA.ORB.init().create_interface_tc("IDL:TTLIBRARY/Librarian:1.0", "Librarian");
	}
	public static String id()
	{
		return "IDL:TTLIBRARY/Librarian:1.0";
	}
	public static Librarian read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object());
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final TTLIBRARY.Librarian s)
	{
		_out.write_Object(s);
	}
	public static TTLIBRARY.Librarian narrow(final java.lang.Object obj)
	{
		if (obj instanceof TTLIBRARY.Librarian)
		{
			return (TTLIBRARY.Librarian)obj;
		}
		else if (obj instanceof org.omg.CORBA.Object)
		{
			return narrow((org.omg.CORBA.Object)obj);
		}
		throw new org.omg.CORBA.BAD_PARAM("Failed to narrow in helper");
	}
	public static TTLIBRARY.Librarian narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (TTLIBRARY.Librarian)obj;
		}
		catch (ClassCastException c)
		{
			if (obj._is_a("IDL:TTLIBRARY/Librarian:1.0"))
			{
				TTLIBRARY._LibrarianStub stub;
				stub = new TTLIBRARY._LibrarianStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
			}
		}
		throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
	}
	public static TTLIBRARY.Librarian unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
			return null;
		try
		{
			return (TTLIBRARY.Librarian)obj;
		}
		catch (ClassCastException c)
		{
				TTLIBRARY._LibrarianStub stub;
				stub = new TTLIBRARY._LibrarianStub();
				stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
				return stub;
		}
	}
}
