package com.vignette.idm.server.common.olf.impl;

import java.lang.reflect.Method;

import com.vignette.idm.server.common.olf.FormatterException;
import com.vignette.idm.server.common.olf.ObjectLiteralFormatter;

public class RefParamToken extends ParamToken {

	ObjectLiteralFormatter mFormatter = null;
	String mKind = null;

	public RefParamToken(String name, String kind,
			ObjectLiteralFormatter formatter) throws FormatterException {
		super(name);
		assert kind != null;
		mKind = kind;
		mFormatter = formatter;
	}

	@Override
	public String process(Object obj) throws FormatterException {
		assert mFormatter != null;
		assert getParamName() != null;
		try {
			Method method = obj.getClass().getMethod(getParamName(), null);
			if (method == null)
				throw new FormatterException("Object: '"
						+ obj.getClass().getName() + "' doesn't have method: '"
						+ getParamName() + "'");
			return mFormatter.format(method.invoke(obj, null), mKind);
		} catch (FormatterException e) {
			throw e;
		} catch (Exception e) {
			throw new FormatterException(e);
		}
	}

}
