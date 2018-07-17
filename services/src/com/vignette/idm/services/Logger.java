////////////////////////////////////////////////////////////////////////////////
//	Title		:	Logger.java
//
//	Description	:	Class which logs standard events to IDM Repository.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.services;

import java.util.logging.Level;

import com.towertechnology.common.errorhandling.TTCommonErrors;
import com.towertechnology.common.errorhandling.TTException;
import com.towertechnology.common.errorhandling.TTRepositoryErrors;
import com.vignette.idm.common.StringBufferS;

import TTEXCEPTIONS.GenericException;
import TTEXCEPTIONS.TTlibException;
import TTGENERAL.OBJECT_ID;
import TTGENERAL.POA_NAME;
import TTREPLOGGER.BadEventDataCount;
import TTREPLOGGER.BadEventType;

public class Logger
{
	private static final String START = "Start: ";
	private static final String COMPLETE = "Complete: ";

	private static java.util.logging.Logger mLogger =
		java.util.logging.Logger.getLogger("com.vignette.idm.services.Logger");

	private IdmRepImpl mRepository = null;

	public Logger(IdmRepImpl sess)
	{
		mRepository = sess;
	}

	public void eventLog(int anEventType, String[] anEventData)
	throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("anEventType", anEventType, ", ");
				msg.append("anEventData", anEventData, "");
				mLogger.info(msg.toString());
			}

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				Integer.TYPE,
				String[].class
			};

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				new Integer(anEventType),
				anEventData
			};

			// Call remote method.
			mRepository.doRemoteMethod
			(
				"TTREPLOGGER.TTLoggerHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"eventLog",
				formalArgs,
				actualArgs
			);

			mLogger.info(COMPLETE);

			return;
		}
		catch (BadEventType e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTRepositoryErrors.BAD_EVENT_TYPE
			);
		}
		catch (BadEventDataCount e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTRepositoryErrors.BAD_EVENT_DATA_COUNT
			);
		}
		catch (TTlibException e)
		{
			mLogger.severe(e.errorDescription);
			String[] ctlerrno = new String[]
			{
				Integer.toString(e.errorCode),
				e.errorCodeStr
			};
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION,
				ctlerrno
			);
		}
		catch (GenericException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
		catch (TTException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			mLogger.severe(e.toString());
			throw new TTException
			(
				e.toString(),
				TTCommonErrors.LOW_LEVEL_EXCEPTION
			);
		}
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
