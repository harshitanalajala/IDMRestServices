/*
 * Created on 24/06/2005
 *
 */
package com.vignette.idm.server.common.olf;

import java.io.InputStream;
import java.util.HashMap;

/**
 * @author gpais
 * 
 */
public interface ObjectLiteralParser {
	public HashMap parse(InputStream istream, String kind,
			ObjectLiteralFormatter formatter) throws FormatterException;
}
