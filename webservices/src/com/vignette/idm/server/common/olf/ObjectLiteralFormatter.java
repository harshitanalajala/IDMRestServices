/*
 * Created on 23/06/2005
 *
 */
package com.vignette.idm.server.common.olf;

import java.io.InputStream;
import java.util.HashMap;

import com.vignette.idm.server.common.olf.impl.ObjectLiteralParserImpl;

/**
 * This class formats object into a literal representation. The templates for
 * formatting are stored in XML file.
 * 
 * @author gpais
 *  
 */
public class ObjectLiteralFormatter {

	private ObjectLiteralParser mParser = null;

	private HashMap mFormatTemplates = new HashMap();

	public ObjectLiteralFormatter(InputStream istream)
			throws FormatterException {
		this(istream, null, null);
	}

	/**
	 * Constructor
	 * @param istream
	 * @param kind Kind of template to load. If null is passed instead then all
	 * templates will be loaded. 
	 * @throws FormatterException
	 */
	public ObjectLiteralFormatter(InputStream istream, String kind)
			throws FormatterException {
		this(istream, kind, null);
	}

	/**
	 * Constructor
	 * @param istream Input stream to read the XML formatted templates for 
	 * objects representation.
	 * @param kind Kind of template to load. If null is passed instead then all
	 * templates will be loaded.
	 * @param parser Parser to process the input stream
	 * @throws FormatterException
	 */
	public ObjectLiteralFormatter(InputStream istream, String kind,
			ObjectLiteralParser parser) throws FormatterException {
		mParser = (ObjectLiteralParser) (parser != null ? parser : new ObjectLiteralParserImpl());

		mFormatTemplates = mParser.parse(istream, kind, this);
	}

	/**
	 * Serialize an object by appropriate template. The template is choosen by
	 * name of the object's class name without a package name. Since kind of
	 * the template is not defined it should be only one possible template 
	 * loaded for the object's type. See Constructor.
	 *     
	 * @param obj Object to serialize into string.
	 * @return Literal formatted representation of the object.
	 * @throws FormatterException Thrown if object's classname is unknown. Or
	 * more than one template is defined for the given object's.
	 */
	public String format(Object obj) throws FormatterException {
		return format(obj, null);
	}

	/**
	 * Serialize an object by appropriate template. The template is choosen by
	 * name of the object's class name without a package name and by specified
	 * kind of template.
	 *  
	 * @param obj Object to serialize into string.
	 * @param kind Template's qualifier. If there are some templates defined for
	 * the given object's class this parameter specifies which template should 
	 * be used to serialize the object.
	 * 
	 * @return Literal formatted representation of the object.
	 * @throws FormatterException Thrown if object's classname or kind of 
	 * template is unknown.
	 */
	public String format(Object obj, String kind) throws FormatterException {
		if (obj == null)
			return null;
		String className = truncateName(obj.getClass().getName());

		HashMap templates = (HashMap) mFormatTemplates.get(className);
		if (templates != null && !templates.isEmpty()) {
			FormatTemplate template = null;
			if (kind == null) {
				if (templates.size() > 1)
					throw new FormatterException(
							"There's no default template defined");

				if (templates.size() == 1)
					template = (FormatTemplate) templates.get(templates
						.keySet().iterator().next());
			} else
				template = (FormatTemplate) templates.get(kind);

			if (template != null)
				return template.process(obj);
		}
		throw new FormatterException("Undefined "
				+ (kind == null ? "default" : "'" + kind + "'")
				+ " template for object of type '" + className + "'");
	}

	/**
	 * @param name
	 * @return
	 */
	protected String truncateName(String name) {
		assert name != null;
		return name.substring(name.lastIndexOf('.') + 1);
	}
}