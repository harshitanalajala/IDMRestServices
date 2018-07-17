package TTDOCTYPE;

/**
 *	Generated from IDL interface "DocTypeSecurity"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */


public interface DocTypeSecurityOperations
{
	/* constants */
	/* operations  */
	int getDoctypeAccess(java.lang.String aTicket, java.lang.String aApplication, int aDoctype) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	boolean getApplicationAccess(java.lang.String aTicket, java.lang.String aApplication) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	boolean getACLAdminAccess(java.lang.String aTicket, java.lang.String aApplication) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	java.lang.String getDoctypeNames(java.lang.String aTicket, java.lang.String aApplication) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	java.lang.String getDoctypeName(java.lang.String aTicket, java.lang.String aApplication, int aDoctype) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	int getDoctypeCode(java.lang.String aTicket, java.lang.String aApplication, java.lang.String aDoctypeName) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	java.lang.String getDoctypeSQLClause(java.lang.String aTicket, java.lang.String aApplication, int aAccessMap) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.AppPermException,TTEXCEPTIONS.TTlibException;
	boolean validateIfnDoctypeAccess(java.lang.String aTicket, TTDOCTYPE.DTAccessActions aAction, java.lang.String aApplication, java.lang.String aData) throws TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
}
