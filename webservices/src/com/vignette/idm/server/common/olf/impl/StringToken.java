/*
 * Created on 23/06/2005
 *
 */
package com.vignette.idm.server.common.olf.impl;

import com.vignette.idm.server.common.olf.Token;

/**
 * @author gpais
 *  
 */
public class StringToken implements Token {

	private String mToken;

	public StringToken(String token) {
		mToken = token;
	}

	@Override
	public String process(Object obj) {
		return mToken;
	}
}