package TTSECURITY;

/**
 *	Generated from IDL interface "Licence"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */


public interface LicenceOperations
{
	/* constants */
	/* operations  */
	java.lang.String login(java.lang.String aSessionID, java.lang.String aClientIP, java.lang.String aUserID, java.lang.String aPasswd, java.lang.String aNewPasswd, java.lang.String aLicenceType, java.lang.String aForLog) throws TTSECURITY.CredentialException,TTSECURITY.ExpiredException,TTEXCEPTIONS.GenericException,TTSECURITY.NoAvailableException,TTEXCEPTIONS.TTlibException,TTSECURITY.BadLicenseTypeException;
	void logout(java.lang.String aTicket) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	boolean isValid(java.lang.String aTicket) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	TTSECURITY.UserInfo getUserInfo(java.lang.String aTicket) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	void refresh(java.lang.String aTicket) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	int userCount(java.lang.String aTicket) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
	void cleanupExpired() throws TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
}
