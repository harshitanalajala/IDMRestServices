/*
 * Created on 23/06/2005
 *
 */
package com.vignette.idm.server.common.olf;

/**
 * @author gpais
 *
 */
public interface Token {
	String process (Object obj) throws FormatterException;
}
