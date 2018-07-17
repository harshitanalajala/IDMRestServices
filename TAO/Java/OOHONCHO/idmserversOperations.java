package OOHONCHO;

/**
 *	Generated from IDL interface "idmservers"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */


public interface idmserversOperations
{
	/* constants */
	/* operations  */
	java.lang.String getPOAname(java.lang.String aPoaPostfix, org.omg.CORBA.StringHolder aRef) throws OOHONCHO.NoSuchPOAInstance,TTEXCEPTIONS.GenericException,OOHONCHO.NoSuchPOAtypeException;
	int releasePOA(java.lang.String aRef) throws OOHONCHO.NoSuchPOAInstance,TTEXCEPTIONS.GenericException,OOHONCHO.NoSuchPOAtypeException;
}
