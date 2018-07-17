/*
 * Created on 23/06/2005
 *
 */
package com.vignette.idm.server.common.olf;

/**
 * @author gpais
 *
 */
public class FormatterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8075942067361802592L;

	/**
	 * 
	 */
	public FormatterException() {
		super();
	}

	/**
	 * @param arg0
	 */
	public FormatterException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public FormatterException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public FormatterException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
