////////////////////////////////////////////////////////////////////////////////
//	Title		:	General.java
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
import com.vignette.idm.common.NameValue;
import com.vignette.idm.common.StringBufferS;

import TTCOMMON.NULL_VALUE;
import TTEXCEPTIONS.AuthenticationException;
import TTEXCEPTIONS.BadDocIdException;
import TTEXCEPTIONS.DocPermException;
import TTEXCEPTIONS.GenericException;
import TTEXCEPTIONS.InvalidDSException;
import TTEXCEPTIONS.TTlibException;
import TTEXCEPTIONS.UnknownDocException;
import TTGENERAL.FieldAttr;
import TTGENERAL.OBJECT_ID;
import TTGENERAL.POA_NAME;

public class General
{
	private static final String START = "Start: ";
	private static final String COMPLETE = "Complete: ";

	private static java.util.logging.Logger mLogger =
		java.util.logging.Logger.getLogger("com.vignette.idm.services.General");

	private IdmRepImpl mRepository = null;

	public General(IdmRepImpl aRepository)
	{
		mRepository = aRepository;
	}

	public int getDocPageCount(String anIfn) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("anIfn", anIfn, "");
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
				anIfn
			};

			// Call remote method.
			Integer pages = (Integer)mRepository.doRemoteMethod
			(
				"TTGENERAL.GenFunctionsHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"getDocPageCount",
				formalArgs,
				actualArgs
			);

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("pages", pages.intValue(), "");
				mLogger.info(msg.toString());
			}

			return pages.intValue();
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
		catch (BadDocIdException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.DOCUMENT_ID_ILLEGAL
			);
		}
		catch (InvalidDSException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.DOCUMENT_UNKNOWN
			);
		}
		catch (DocPermException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.DOCUMENT_PERMISSION_DENIED
			);
		}
		catch (UnknownDocException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.DOCUMENT_UNKNOWN
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

	public String selectDiskset
	(
		String		aAppName,
		NameValue	aFieldValues[],
		ColumnDef	aAppDef
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aAppName", aAppName, ", ");
				msg.append("aFieldValues", aFieldValues, ", ");
				//msg.append("aAppDef", aAppDef, "");
				msg.append("aAppDef", aAppDef, "");
				mLogger.info(msg.toString());
			}

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				FieldAttr[].class
			};

			// Define input/output variables.
			FieldAttr[] fieldArr = new FieldAttr[aFieldValues.length];
			for (int i = 0; i < aFieldValues.length; i++)
			{
				ColAttr fieldDef =
					aAppDef.getColumnAttr(aFieldValues[i].getName());
				if (fieldDef == null)
				{
					String msg2 = "Field '" + aFieldValues[i].getName() +
						"' does not exist in '" + aAppName + "'";
					mLogger.severe(msg2);
					throw new TTException
					(
						msg2,
						TTRepositoryErrors.DATABASE_ERROR
					);
				}
				fieldArr[i] = new FieldAttr();
				fieldArr[i].mFieldName = aFieldValues[i].getName();
				if (aFieldValues[i].getValue() != null)
				{
					fieldArr[i].mFieldValue = aFieldValues[i].getValue();
				}
				else
				{
					fieldArr[i].mFieldValue = NULL_VALUE.value;
				}
				fieldArr[i].mFieldType = fieldDef.getFieldType();
				fieldArr[i].mFieldLen = fieldDef.getLength();
			}

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				aAppName,
				fieldArr
			};

			// Call remote method.
			String selDiskset = (String)mRepository.doRemoteMethod
			(
				"TTGENERAL.GenFunctionsHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"selectDiskset",
				formalArgs,
				actualArgs
			);

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("selDiskset", selDiskset, "");
				mLogger.info(msg.toString());
			}

			return selDiskset;
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
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
