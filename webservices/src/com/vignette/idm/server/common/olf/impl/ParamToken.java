/*
 * Created on 23/06/2005
 *
 */
package com.vignette.idm.server.common.olf.impl;

import java.lang.reflect.Method;

import com.vignette.idm.server.common.olf.FormatterException;
import com.vignette.idm.server.common.olf.Token;

/**
 * @author gpais
 *  
 */
public class ParamToken implements Token {

	private String mParamName;

	/**
	 * @throws FormatterException
	 *  
	 */
	public ParamToken(String token) {
		assert token != null;
		mParamName = "get" + 
					 String.valueOf(token.charAt(0)).toUpperCase() + 
					 token.substring(token.length() - (token.length() - 1));
	}

	@Override
	public String process(Object obj) throws FormatterException {
		assert mParamName != null;
		try {
			Method method = obj.getClass().getMethod(mParamName, null);
			if (method == null)
				throw new FormatterException("Object: '"
						+ obj.getClass().getName() + "' doesn't have method: '"
						+ mParamName + "'");
			return "" + method.invoke(obj, null);
		} catch (FormatterException e) {
			throw e;
		} catch (Exception e) {
			throw new FormatterException(e);
		}
	}

	/**
	 * @return Returns the paramName.
	 */
	public String getParamName() {
		return mParamName;
	}
}