package com.opentext.rest.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

import com.vignette.idm.server.NameValue;

public class SysServiceUtils {

	public static ArrayList<com.vignette.idm.common.NameValue> transformConfigVars(String aMethodName,
			String aVarNamePattern, com.vignette.idm.common.NameValue[] aVars) {
		ArrayList<com.vignette.idm.common.NameValue> vars = new ArrayList<com.vignette.idm.common.NameValue>(aVars.length);
		for (int i = 0; i < aVars.length; i++) {
			com.vignette.idm.common.NameValue nv = new com.vignette.idm.common.NameValue();
			nv.setName(aVars[i].getName());
			nv.setValue(aVars[i].getValue());
			vars.add(nv);
		}
		return vars;
	}

	public static Properties transformOptions(List<NameValue> aOptions) {
		Properties props = new Properties();
		if (aOptions != null) {
			ListIterator<NameValue> itor = aOptions.listIterator();
			while (itor.hasNext()) {
				NameValue nv = itor.next();
				props.setProperty(nv.getName(), nv.getValue());
			}
		}
		return props;
	}
	
	public static String[] tranformFields(List<com.vignette.idm.server.NameValue> aFields)
	{
		String[] nvPair = new String[aFields.size() * 2];
		int nvIndex = 0;
		
		ListIterator<com.vignette.idm.server.NameValue> itor = aFields.listIterator();
		while (itor.hasNext())
		{
			com.vignette.idm.server.NameValue nv = itor.next();
			nvPair[nvIndex++] = nv.getName();
			nvPair[nvIndex++] = nv.getValue();
		}
		return nvPair;
	}

}
