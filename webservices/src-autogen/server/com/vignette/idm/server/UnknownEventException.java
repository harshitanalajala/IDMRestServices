
package com.vignette.idm.server;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3-b02-
 * Generated source version: 2.0
 * 
 */
public class UnknownEventException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private UnknownEventExceptionFaultDetail faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public UnknownEventException(String message, UnknownEventExceptionFaultDetail faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public UnknownEventException(String message, UnknownEventExceptionFaultDetail faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.vignette.idm.server.UnknownEventExceptionFaultDetail
     */
    public UnknownEventExceptionFaultDetail getFaultInfo() {
        return faultInfo;
    }

}
