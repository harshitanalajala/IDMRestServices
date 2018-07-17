package TTGENERAL;

/**
 *	Generated from IDL interface "MiscFunctions"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */


public interface MiscFunctionsOperations
{
	/* constants */
	/* operations  */
	int getDocPageCount(java.lang.String aTicket, java.lang.String anIfn) throws TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.InvalidDSException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	boolean isApplication(java.lang.String aTicket, java.lang.String anAppName) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	java.lang.String getConfigVar(java.lang.String aTicket, java.lang.String aVarName) throws TTGENERAL.InvalidConfigVar,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	TTGENERAL.NameValue[] getConfigVars(java.lang.String aTicket, java.lang.String aVarPattern) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	TTGENERAL.ApplicationEntry[] getAppList(java.lang.String aTicket) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	void logMessage(java.lang.String aTicket, java.lang.String aTraceSeverity, java.lang.String aMessage, java.lang.String aFilename) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	void logError(java.lang.String aTicket, java.lang.String aMessage, java.lang.String aReason) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	void logEvent(java.lang.String aTicket, java.lang.String aCategory, java.lang.String aName, java.lang.String[] aNameValues) throws TTGENERAL.UnknownCategoryOrEvent,TTEXCEPTIONS.AuthenticationException,TTGENERAL.UnknownField,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	java.lang.String selectDiskset(java.lang.String aTicket, java.lang.String aTabName, TTGENERAL.FieldAttr[] aFieldValues) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
}
