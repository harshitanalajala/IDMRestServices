package TTLIBRARY;

import TTLIBRARY.Librarian;
import TTLIBRARY.LibrarianHelper;

/**
 *	Generated from IDL interface "Librarian"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public final class LibrarianHolder	implements org.omg.CORBA.portable.Streamable{
	 public Librarian value;
	public LibrarianHolder()
	{
	}
	public LibrarianHolder (final Librarian initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type()
	{
		return LibrarianHelper.type();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = LibrarianHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		LibrarianHelper.write (_out,value);
	}
}
