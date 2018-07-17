////////////////////////////////////////////////////////////////////////////////
//	Title		:	Misc.java
//
//	Description	:	Class which performs short miscellaneous methods.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.services;

import java.util.logging.Level;

import com.towertechnology.common.errorhandling.TTCommonErrors;
import com.towertechnology.common.errorhandling.TTException;
import com.towertechnology.common.errorhandling.TTRepositoryErrors;
import com.vignette.idm.common.ApplicationEntry;
import com.vignette.idm.common.NameValue;
import com.vignette.idm.common.StringBufferS;

import TTCOMMON.NULL_VALUE;
import TTEXCEPTIONS.AuthenticationException;
import TTEXCEPTIONS.GenericException;
import TTEXCEPTIONS.TTlibException;
import TTGENERAL.InvalidConfigVar;
import TTGENERAL.OBJECT_ID;
import TTGENERAL.POA_NAME;
import TTGENERAL.UnknownCategoryOrEvent;
import TTGENERAL.UnknownField;

public class Misc
{
	private static final String START = "Start: ";
	private static final String COMPLETE = "Complete: ";

	private static java.util.logging.Logger mLogger =
		java.util.logging.Logger.getLogger("com.vignette.idm.services.Misc");

	private IdmRepImpl mRepository = null;

	/**
	 * Creates an instance of this class.
	 *
	 * @param aRepository a handle to the IDM Repository implementation.
	 */
	public Misc(IdmRepImpl aRepository)
	{
		mRepository = aRepository;
	}

	/**
	 * Get the value of a configuration variable
	 * by calling the c++ corba objects on the server.
	 *
	 * @param	aVarName name of a configuration variable.
	 * @return 	value of a variable in string.
	 * @throws	TTException.
	 */
	public String getConfigVar(String aVarName) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aVarName", aVarName, "");
				mLogger.info(msg.toString());
			}

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class
			};

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				aVarName
			};

			// Call remote method.
			String varValue = (String)mRepository.doRemoteMethod
			(
				"TTGENERAL.GenFunctionsHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"getConfigVar",
				formalArgs,
				actualArgs
			);

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("varValue", varValue, "");
				mLogger.info(msg.toString());
			}

			return varValue;
		}
		catch (AuthenticationException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LICENSE_INVALID
			);
		}
		catch (InvalidConfigVar e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.INVALID_ARGUMENT
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

	/**
	 * Get the name and value of configuration variables, by matching the
	 * the beginning of the configuration variable name with the pattern,
	 * by calling the c++ corba objects on the server.
	 *
	 * @param	aVarPattern the beginning pattern of configuration variables.
	 * @return 	a list of name value pairs.
	 * @throws	TTException.
	 */
	public NameValue[] getConfigVars(String aVarPattern) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aVarPattern", aVarPattern, "");
				mLogger.info(msg.toString());
			}

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class
			};

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				aVarPattern
			};

			// Call remote method.
			TTGENERAL.NameValue[] result = (TTGENERAL.NameValue [])
				mRepository.doRemoteMethod
				(
					"TTGENERAL.GenFunctionsHelper",
					POA_NAME.value,
					OBJECT_ID.value,
					null,
					"",
					"getConfigVars",
					formalArgs,
					actualArgs
				);

			// Check result.
			if (result.length == 0)
			{
				throw new TTException
				(
					"No matches for specified variable pattern",
					TTCommonErrors.INVALID_ARGUMENT
				);
			}

			// Convert result.
			NameValue[] res = new NameValue[result.length];
			for (int i = 0; i < result.length; i++)
			{
				res[i] = new NameValue(result[i].mName, result[i].mValue);
			}

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("res", res, "");
				mLogger.info(msg.toString());
			}

			return res;
		}
		catch (AuthenticationException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LICENSE_INVALID
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

	/**
	 * Get all the application entries viewable by a particular user
	 * by calling the c++ corba objects on the server.
	 *
	 * @param	None.
	 * @return 	a list of application entries.
	 * @throws	TTException.
	 */
	public ApplicationEntry[] getAppList() throws TTException
	{
		try
		{
			mLogger.info(START);

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class
			};

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket()
			};

			// Call remote method.
			TTGENERAL.ApplicationEntry[] applications =
				(TTGENERAL.ApplicationEntry [])mRepository.doRemoteMethod
			(
				"TTGENERAL.GenFunctionsHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"getAppList",
				formalArgs,
				actualArgs
			);

			// Convert result.
			ApplicationEntry[] apps = new ApplicationEntry[applications.length];
			for (int i = 0; i < applications.length; i++)
			{
				apps[i] = new ApplicationEntry
				(
					applications[i].mName,
					applications[i].mShortName,
					applications[i].mDiskset,
					applications[i].mAclIndex,
					applications[i].mFlags
				);
			}

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("apps", apps, "");
				mLogger.info(msg.toString());
			}

			return apps;
		}
		catch (AuthenticationException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LICENSE_INVALID
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

	/**
	 * Log a message to the external trace log
	 * by calling the c++ corba objects on the server.
	 *
	 * @param	aTraceSeverity the severity of this trace message.
	 * @param	aMessage the message to be trace.
	 * @param	aFilename the name of the file to write messages to.
	 * @return 	None.
	 * @throws	TTException.
	 */
	public void logMessage
	(
		String	aTraceSeverity,
		String	aMessage,
		String	aFilename
	)
	throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aTraceSeverity", aTraceSeverity, ", ");
				msg.append("aMessage", aMessage, ", ");
				msg.append("aFilename", aFilename, "");
				mLogger.info(msg.toString());
			}

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				String.class,
				String.class
			};

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				aTraceSeverity,
				aMessage,
				aFilename
			};

			// Call remote method.
			mRepository.doRemoteMethod
			(
				"TTGENERAL.GenFunctionsHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"logMessage",
				formalArgs,
				actualArgs
			);

			mLogger.info(COMPLETE);

			return;
		}
		catch (AuthenticationException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LICENSE_INVALID
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

	/**
	 * Log a message to the errorlog
	 * by calling the c++ corba objects on the server.
	 *
	 * @param	aMessage the message to be trace.
	 * @param	aReason the reason why this error occurred.
	 * @return 	None.
	 * @throws	TTException.
	 */
	public void logError(String aMessage, String aReason) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aMessage", aMessage, ", ");
				msg.append("aReason", aReason, "");
				mLogger.info(msg.toString());
			}

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				String.class
			};

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				aMessage,
				(aReason == null) ? NULL_VALUE.value : aReason
			};

			// Call remote method.
			mRepository.doRemoteMethod
			(
				"TTGENERAL.GenFunctionsHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"logError",
				formalArgs,
				actualArgs
			);

			mLogger.info(COMPLETE);

			return;
		}
		catch (AuthenticationException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LICENSE_INVALID
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

	/**
	 * Log an event
	 * by calling the c++ corba objects on the server.
	 *
	 * @param	aCategory	The short name of category of the event to be logged
	 * @param	aName		The short name of the event being logged
	 * @param	aNameValues	<name=value> pairs that are required for this event
	 * @return 	None.
	 * @throws	TTException.
	 */
	public void logEvent
	(
		String		aCategory,
		String		aName,
		String[]	aNameValues
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aCategory", aCategory, ", ");
				msg.append("aName", aName, ", ");
				msg.append("aNameValues", aNameValues, "");
				mLogger.info(msg.toString());
			}

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				String.class,
				String[].class
			};

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				aCategory,
				aName,
				aNameValues
			};

			// Call remote method.
			mRepository.doRemoteMethod
			(
				"TTGENERAL.GenFunctionsHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"logEvent",
				formalArgs,
				actualArgs
			);

			mLogger.info(COMPLETE);

			return;
		}
		catch (AuthenticationException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTCommonErrors.LICENSE_INVALID
			);
		}
		catch (UnknownCategoryOrEvent e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.UNKNOWN_EVENT_CATEGORY_OR_NAME
			);
		}
		catch (UnknownField e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.UNKNOWN_EVENT_FIELD
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
