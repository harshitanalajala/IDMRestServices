/*
 * Created on 23/06/2005
 *
 */
package com.vignette.idm.server.common.olf.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.vignette.idm.server.common.olf.FormatTemplate;
import com.vignette.idm.server.common.olf.FormatterException;
import com.vignette.idm.server.common.olf.ObjectLiteralFormatter;
import com.vignette.idm.server.common.olf.Token;

/**
 * @author gpais
 *  
 */
public class ObjectLiteralParserImpl implements
		com.vignette.idm.server.common.olf.ObjectLiteralParser {

	private TokenBuilder mTokenBuilder = null;

	public ObjectLiteralParserImpl() {
		this(null);
	}

	public ObjectLiteralParserImpl(TokenBuilder builder) {
		mTokenBuilder = builder != null ? builder : new TokenBuilder();
	}

	public HashMap parse(InputStream istream, ObjectLiteralFormatter formatter)
			throws FormatterException {
		return parse(istream, null, formatter);
	}

	@Override
	public HashMap parse(InputStream istream, String kind,
			ObjectLiteralFormatter formatter) throws FormatterException {
		Document document = null;
		try {
			DocumentBuilder builder = null;

			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			document = builder.parse(istream);

		} catch (Exception e) {
			throw new FormatterException(e);
		}

		assert document != null;

		HashMap objectTemplates = null;

		HashMap requiredFormatters = loadRequiredFormatters(document, kind,
			formatter);

		objectTemplates = parseForTemplates(document, kind, formatter);

		mergeFormatters(objectTemplates, requiredFormatters);

		return objectTemplates;
	}

	/**
	 * @param objectTemplates
	 * @param requiredFormatters
	 * @return
	 */
	private void mergeFormatters(HashMap objectTemplates,
			final HashMap requiredFormatters) throws FormatterException {
		Iterator iterator = requiredFormatters.keySet().iterator();
		while (iterator.hasNext()) {
			Object key = iterator.next();
			if (objectTemplates.containsKey(key))
				throw new FormatterException(
						"Duplicate definition was found for '" + key + "'");
		}
		objectTemplates.putAll(requiredFormatters);
	}

	protected HashMap loadRequiredFormatters(Document document, String kind,
			ObjectLiteralFormatter formatter) throws FormatterException {

		NodeList listOfRequiredFormatters = document
			.getElementsByTagName("depends");

		int length = listOfRequiredFormatters.getLength();
		HashMap resultMap = new HashMap();
		for (int i = 0; i < length; ++i) {
			Node dependNode = listOfRequiredFormatters.item(i);
			String neededFilename = getAttributeValue(dependNode, "on");

			if (neededFilename == null)
				throw new FormatterException("Object formatter has no name");

			try {
				HashMap templates = parse(
					getStreamForFormatterLoading(neededFilename), kind,
					formatter);

				mergeFormatters(resultMap, templates);
			} catch (FormatterException e) {
				throw new FormatterException("An exception occurred while '"
						+ neededFilename + "' was processed", e);
			}
		}
		return resultMap;
	}

	protected InputStream getStreamForFormatterLoading(String from)
			throws FormatterException {
		try {
			return new FileInputStream(from);
		} catch (Exception e) {
			throw new FormatterException(e);
		}
	}

	protected HashMap parseForTemplates(Document document, String kind,
			ObjectLiteralFormatter formatter) throws FormatterException {
		HashMap objectTemplates = new HashMap();
		NodeList listOfTemplates = document.getElementsByTagName("template");

		int propsLength = listOfTemplates.getLength();
		for (int i = 0; i < propsLength; ++i) {
			Node templateNode = listOfTemplates.item(i);
			String templateKind = getAttributeValue(templateNode, "kind");

			if (templateKind != null && isKindOf(templateKind, kind)) {
				Node parentNode = templateNode.getParentNode();
				assert parentNode != null;
				String objectName = getAttributeValue(parentNode, "type");
				HashMap patternsMap = (HashMap) objectTemplates.get(objectName);
				if (patternsMap == null) {
					patternsMap = new HashMap();
					objectTemplates.put(objectName, patternsMap);
				}
				patternsMap.put(templateKind, new FormatTemplateImpl(
						templateNode, formatter));
			}
		}
		return objectTemplates;
	}

	/**
	 * @param templateNode
	 * @return kind of the specified template
	 */
	public static String getAttributeValue(Node node, String attrName) {
		if (node == null)
			return null;
		NamedNodeMap attrs = node.getAttributes();
		Node type = null;
		if (attrs != null)
			type = attrs.getNamedItem(attrName);
		return type == null ? null : type.getNodeValue();
	}

	/**
	 * Checks whether a template is kind of the specified type.
	 * 
	 * @param templateKind
	 * @param kind
	 * @return true if the specified value of 'kind' is null or 'templateKind'
	 *         is equal to 'kind'
	 */
	protected boolean isKindOf(String templateKind, String kind) {
		// assert templateKind != null;
		if (kind == null)
			return true;
		return templateKind.equals(kind);
	}

	/**
	 * @author gpais
	 *  
	 */
	protected class FormatTemplateImpl extends FormatTemplate {
		LinkedList mList = new LinkedList();

		/**
		 * @param formatter
		 * @throws FormatterException
		 *  
		 */
		public FormatTemplateImpl(Node templateNode,
				ObjectLiteralFormatter formatter) throws FormatterException {
			assert mTokenBuilder != null;
			NodeList childNodes = templateNode.getChildNodes();
			int length = childNodes.getLength();
			for (int i = 0; i < length; ++i) {
				Token token = mTokenBuilder.createToken(childNodes.item(i),
					formatter);
				if (token != null)
					mList.add(token);
			}
		}

		@Override
		protected Iterator getTokens() {
			return mList.iterator();
		}

	}
}