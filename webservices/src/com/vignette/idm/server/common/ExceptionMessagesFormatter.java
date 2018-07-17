////////////////////////////////////////////////////////////////////////////////
//	Title		:	ExceptionMessagesFormatter.java
//
//	Description	:	Singleton class which loads ExceptionMessages.xml and
//					performs message formatting.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.server.common;

import java.io.File;
import java.io.FileInputStream;

import com.vignette.idm.server.common.olf.ObjectLiteralFormatter;

public class ExceptionMessagesFormatter extends ObjectLiteralFormatter
{
	private static final String TOWER_HOME = "tower.home";
	private static final String CONFIG = "config";
	private static final String FILENAME = "ExceptionMessages.xml";
	private static final String START = "Started: ";
	private static final String COMPLETE = "Complete: ";
	//private static final String EXCEPTION_MESSAGES = "C:/Users/styagadu/Desktop/vobs/source/core/ooidm/webservices/config/ExceptionMessages.xml";
	//home/tower/EAP-6.4.0/modules/system/layers/base/com/idm
	public static java.util.logging.Logger mLogger =
		java.util.logging.Logger.getLogger
		(
			"com.vignette.idm.server.impl.ExceptionMessagesFormatter"
		);

	private static ExceptionMessagesFormatter formatter = null;

	private ExceptionMessagesFormatter() throws Exception
	{
		super
		(
			new FileInputStream
			(
				System.getProperty(TOWER_HOME)+ File.separator + CONFIG +
				File.separator + FILENAME
			)
		);
	}

	public static synchronized ExceptionMessagesFormatter getInstance()
	throws Exception
	{
		mLogger.info(START);

		if (formatter == null)
		{
			mLogger.info("Creating instance");

			formatter = new ExceptionMessagesFormatter();
		}

		mLogger.info(COMPLETE);

		return formatter;
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
