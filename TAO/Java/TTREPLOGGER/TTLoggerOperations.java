package TTREPLOGGER;

/**
 *	Generated from IDL interface "TTLogger"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */


public interface TTLoggerOperations
{
	/* constants */
	/* operations  */
	void eventLog(int anEventType, java.lang.String[] anEventData) throws TTREPLOGGER.BadEventType,TTEXCEPTIONS.GenericException,TTREPLOGGER.BadEventDataCount,TTEXCEPTIONS.TTlibException;
}
