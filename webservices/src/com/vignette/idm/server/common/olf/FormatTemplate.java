/*
 * Created on 23/06/2005
 *
 */
package com.vignette.idm.server.common.olf;

import java.util.Iterator;

/**
 * @author gpais
 *
 */
public abstract class FormatTemplate {

	/**
	 * 
	 */
	public FormatTemplate() {
		super();
	}
	
	abstract protected Iterator getTokens();
	
	public String process (Object obj) throws FormatterException {
		StringBuffer sb = new StringBuffer();
		Iterator iter = getTokens();
		while (iter.hasNext()) {
			Token token = (Token) iter.next();
			sb.append(token.process(obj));
		}
		return sb.toString();
	}

}
