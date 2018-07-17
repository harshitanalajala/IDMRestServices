/*
 * Created on 23/06/2005
 *
 */
package com.vignette.idm.server.common.olf.impl;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.vignette.idm.server.common.olf.FormatterException;
import com.vignette.idm.server.common.olf.ObjectLiteralFormatter;
import com.vignette.idm.server.common.olf.Token;

/**
 * @author gpais
 *  
 */
public class TokenBuilder {

	public TokenBuilder() {
	}

	/**
	 * @param formatter
	 * @return @throws
	 *         FormatterException
	 */
	public Token createToken(Node node, ObjectLiteralFormatter formatter)
			throws FormatterException {
		Token token = null;
		try {
			switch (node.getNodeType()) {
			case Node.TEXT_NODE:
				token = parseAndCreateStringToken(node, formatter);
				break;
			case Node.ELEMENT_NODE:
				token = parseAndCreateParamToken(node, formatter);
				break;
			}
		} catch (FormatterException e) {
			throw e;
		} catch (Exception e) {
			throw new FormatterException(e);
		}
		return token;
	}

	private Token parseAndCreateStringToken(Node node,
			ObjectLiteralFormatter formatter) throws FormatterException {
		return createStringToken(node.getNodeValue(), formatter);
	}

	protected Token parseAndCreateParamToken(Node node,
			ObjectLiteralFormatter formatter) throws FormatterException {
		NamedNodeMap attrsMap = node.getAttributes();
		// retrieve the required attribute 'name'
		String name = null;
		if (attrsMap != null) {
			name = ObjectLiteralParserImpl.getAttributeValue(node, "name");
		}
		if (name == null || name.length() == 0)
			throw new FormatterException("Missing 'name' attribute.");

		// retrieve the optional attribute 'kind'
		String kind = ObjectLiteralParserImpl.getAttributeValue(node, "kind");
		if (kind == null)
			return createParamToken(name, formatter);
		else
			return createRefParamToken(name, kind, formatter);

	}

	protected Token createRefParamToken(String name, String from,
			ObjectLiteralFormatter formatter) throws FormatterException {
		return new RefParamToken(name, from, formatter);
	}

	protected Token createStringToken(String str,
			ObjectLiteralFormatter formatter) throws FormatterException {
		return new StringToken(str);
	}

	protected Token createParamToken(String str,
			ObjectLiteralFormatter formatter) throws FormatterException {
		return new ParamToken(str);
	}
}