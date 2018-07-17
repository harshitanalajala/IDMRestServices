package TTIMPORT;

/**
 *	Generated from IDL interface "Importer"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */


public interface ImporterOperations
{
	/* constants */
	/* operations  */
	int importDoc(java.lang.String aTicket, java.lang.String anAppName, java.lang.String aDocType, byte[] aDocDataStream, TTIMPORT.TypeMethod aTypeMeth, java.lang.String aPoolName, org.omg.CORBA.StringHolder anIfn) throws TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.InvalidDSException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.AuthenticationException,TTIMPORT.InvalidAppException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	void removeDoc(java.lang.String aTicket, java.lang.String anIfn) throws TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.InvalidDSException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
}
