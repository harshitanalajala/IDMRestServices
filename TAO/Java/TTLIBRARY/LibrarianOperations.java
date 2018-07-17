package TTLIBRARY;

/**
 *	Generated from IDL interface "Librarian"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */


public interface LibrarianOperations
{
	/* constants */
	/* operations  */
	void createRevision(java.lang.String aTicket, java.lang.String aAppName, TTLIBRARY.NameValue[] aFields, java.lang.String aStatus, java.lang.String aContentType, byte[] aContent, java.lang.String aDiskset, java.lang.String aPoolName, org.omg.CORBA.StringHolder aIfn, org.omg.CORBA.IntHolder aNumPages) throws TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.InvalidDSException,TTLIBRARY.NotLibAppException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTLIBRARY.DatabaseException,TTEXCEPTIONS.TTlibException;
	void checkOutRevision(java.lang.String aTicket, java.lang.String aAppName, java.lang.String aIfn, java.lang.String aComment, TTLIBRARY.ContentHolder aContent, org.omg.CORBA.StringHolder aContentType) throws TTEXCEPTIONS.DocPermException,TTLIBRARY.NotLibAppException,TTEXCEPTIONS.BadDocIdException,TTLIBRARY.CheckedOutException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	void cancelCheckOut(java.lang.String aTicket, java.lang.String aAppName, java.lang.String aIfn) throws TTEXCEPTIONS.GenericException,TTEXCEPTIONS.BadDocIdException,TTLIBRARY.NotOwnerException,TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTLIBRARY.NotLibAppException,TTLIBRARY.NotCheckedOutException,TTEXCEPTIONS.TTlibException;
	TTLIBRARY.CheckOutDetail getCheckOutDetail(java.lang.String aTicket, java.lang.String aAppName, java.lang.String aIfn) throws TTEXCEPTIONS.DocPermException,TTLIBRARY.NotLibAppException,TTEXCEPTIONS.BadDocIdException,TTLIBRARY.NotCheckedOutException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	void listCheckedOutRevisionPermission(java.lang.String aTicket, java.lang.String aAppName, boolean aAll) throws TTLIBRARY.NotLibAppException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.AppPermException,TTEXCEPTIONS.TTlibException;
	void checkInRevision(java.lang.String aTicket, java.lang.String aAppName, org.omg.CORBA.StringHolder aIfn, java.lang.String aStatus, java.lang.String aContentType, byte[] aContent, org.omg.CORBA.IntHolder aNumPages) throws TTEXCEPTIONS.GenericException,TTEXCEPTIONS.BadDocIdException,TTLIBRARY.NotOwnerException,TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTLIBRARY.NotLibAppException,TTLIBRARY.NotCheckedOutException,TTEXCEPTIONS.TTlibException;
	void publishRevision(java.lang.String aTicket, java.lang.String aAppName, org.omg.CORBA.StringHolder aIfn, org.omg.CORBA.IntHolder aNumPages) throws TTEXCEPTIONS.DocPermException,TTLIBRARY.NotLibAppException,TTEXCEPTIONS.BadDocIdException,TTLIBRARY.CheckedOutException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	java.lang.String searchRevisionHistoryPermission(java.lang.String aTicket, java.lang.String aAppName, java.lang.String aIfn) throws TTEXCEPTIONS.DocPermException,TTLIBRARY.NotLibAppException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
}
