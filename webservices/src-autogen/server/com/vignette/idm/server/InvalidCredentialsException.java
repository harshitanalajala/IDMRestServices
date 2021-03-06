
package com.vignette.idm.server;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3-b02-
 * Generated source version: 2.0
 * 
 */
public class InvalidCredentialsException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private InvalidCredentialsExceptionFaultDetail faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public InvalidCredentialsException(String message, InvalidCredentialsExceptionFaultDetail faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public InvalidCredentialsException(String message, InvalidCredentialsExceptionFaultDetail faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.vignette.idm.server.InvalidCredentialsExceptionFaultDetail
     */
    public InvalidCredentialsExceptionFaultDetail getFaultInfo() {
        return faultInfo;
    }

}
