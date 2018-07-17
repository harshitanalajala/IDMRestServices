////////////////////////////////////////////////////////////////////////////////
//	Title		:	Queue.java
//
//	Description	:	Class which encapsulates all queue system operations.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.services;

import java.util.logging.Level;

import com.towertechnology.common.errorhandling.TTCommonErrors;
import com.towertechnology.common.errorhandling.TTException;
import com.towertechnology.common.errorhandling.TTRepositoryErrors;
import com.vignette.idm.common.QDataType;
import com.vignette.idm.common.QNameValue;
import com.vignette.idm.common.QSelection;
import com.vignette.idm.common.QSelectionResult;
import com.vignette.idm.common.QueueEntry;
import com.vignette.idm.common.QueueInfo;
import com.vignette.idm.common.QueueVarDef;
import com.vignette.idm.common.SelectionResult;
import com.vignette.idm.common.StringBufferS;

import TTEXCEPTIONS.AuthenticationException;
import TTEXCEPTIONS.GenericException;
import TTEXCEPTIONS.TTlibException;
import TTQUEUE.BadFieldNamesException;
import TTQUEUE.BadNameValueException;
import TTQUEUE.BadOffsetException;
import TTQUEUE.BadSelectionIdException;
import TTQUEUE.BadWhereException;
import TTQUEUE.LockFailureException;
import TTQUEUE.MovedException;
import TTQUEUE.NotLockedException;
import TTQUEUE.OBJECT_ID;
import TTQUEUE.POA_NAME;
import TTQUEUE.QueueException;

public class Queue
{
	private static final String START = "Start: ";
	private static final String COMPLETE = "Complete: ";

	private static java.util.logging.Logger mLogger =
		java.util.logging.Logger.getLogger("com.vignette.idm.services.Queue");

	private IdmRepImpl	mRepository = null;

	public Queue(IdmRepImpl aRepository)
	{
		mRepository = aRepository;
	}

	/**
	 *	Check the ticket for the corresponding server reference
	 */
	private String getServerRef() throws Exception
	{
		String ticket = mRepository.getTicket();

		TicketMap tickMap = TicketMap.getInstance();

		String serverRef = tickMap.getValue(ticket);

		if (serverRef == null)
		{
			// Need to register session.
			// Note that we register with the idm ticket
			// but use the 'other' ticket for the ticket map.
			serverRef = registerSession();

			tickMap.addTicketMapping(ticket, serverRef);
		}

		return serverRef;
	}

	public String registerSession() throws TTException
	{
		try
		{
			mLogger.info(START);

			String[] aServerRef = new String[1];
			aServerRef[0] = null;

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
			mRepository.doRemoteMethod
			(
				"TTQUEUE.QueueHelper",
				TTQUEUE.POA_NAME.value,
				TTQUEUE.OBJECT_ID.value,
				aServerRef,
				null,
				"registerSession",
				formalArgs,
				actualArgs
			);

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("aServerRef[0]", aServerRef[0], "");
				mLogger.info(msg.toString());
			}

			return aServerRef[0];
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

	public void unregisterSession(String aServerRef) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aServerRef", aServerRef, "");
				mLogger.info(msg.toString());
			}

			String[] serverRef = new String[1];
			serverRef[0] = aServerRef;

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
			mRepository.doRemoteMethod
			(
				"TTQUEUE.QueueHelper",
				TTQUEUE.POA_NAME.value,
				TTQUEUE.OBJECT_ID.value,
				serverRef,
				null,
				"unregisterSession",
				formalArgs,
				actualArgs
			);

			mLogger.info(COMPLETE);

			return;
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

	public QueueInfo getQueueInfo
	(
		String	aQServer,
		String	aQServiceName,
		int		aQueueId
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aQServer", aQServer, ", ");
				msg.append("aQServiceName", aQServiceName, ", ");
				msg.append("aQueueId", aQueueId, "");
				mLogger.info(msg.toString());
			}

			String[] serverRef = new String[1];
			serverRef[0] = getServerRef();

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				String.class,
				Integer.TYPE
			};

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				aQServer,
				aQServiceName,
				new Integer(aQueueId)
			};

			// Call remote method.
			TTQUEUE.CQueueInfo qi =
				(TTQUEUE.CQueueInfo)mRepository.doRemoteMethod
				(
					"TTQUEUE.QueueHelper",
					TTQUEUE.POA_NAME.value,
					TTQUEUE.OBJECT_ID.value,
					serverRef,
					null,
					"getQueueInfo",
					formalArgs,
					actualArgs
				);

			QueueInfo qInfo = transformCQueueInfo(qi);

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("qInfo", qInfo, "");
				mLogger.info(msg.toString());
			}

			return qInfo;
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
		catch (QueueException e)
		{
			throw transformQueueException(e);
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

	public QSelectionResult searchQueueInfo
	(
		String		aQServer,
		String		aQServiceName,
		String[]	aPropNames,
		String		aWhere,
		String		anOrderBy,
		int			anOffset,
		int			aMaxRows
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aQServer", aQServer, ", ");
				msg.append("aQServiceName", aQServiceName, ", ");
				msg.append("aPropNames", aPropNames, ", ");
				msg.append("aWhere", aWhere, ", ");
				msg.append("anOrderBy", anOrderBy, ", ");
				msg.append("anOffset", anOffset, ", ");
				msg.append("aMaxRows", aMaxRows, "");
				mLogger.info(msg.toString());
			}

			String[] serverRef = new String[1];
			serverRef[0] = getServerRef();

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				String.class,
				String[].class,
				String.class,
				String.class,
				Integer.TYPE,
				Integer.TYPE,
				org.omg.CORBA.IntHolder.class
			};

			// Define input/output variables.
			org.omg.CORBA.IntHolder numMatched = new org.omg.CORBA.IntHolder();

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				aQServer,
				aQServiceName,
				aPropNames,
				(aWhere == null) ? "" : aWhere,
				(anOrderBy == null) ? "" : anOrderBy,
				new Integer(anOffset),
				new Integer(aMaxRows),
				numMatched
			};

			// Call remote method.
			TTQUEUE.CSearchResult sRes =
				(TTQUEUE.CSearchResult)mRepository.doRemoteMethod
				(
					"TTQUEUE.QueueHelper",
					TTQUEUE.POA_NAME.value,
					TTQUEUE.OBJECT_ID.value,
					serverRef,
					null,
					"searchQueueInfo",
					formalArgs,
					actualArgs
				);

			QSelectionResult srchRes = new QSelectionResult();

			srchRes.m_Results = transformCSearchResult(sRes);
			srchRes.m_NumMatched = numMatched.value;

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("m_Results", srchRes.m_Results, ", ");
				msg.append("m_NumMatched", srchRes.m_NumMatched, "");
				mLogger.info(msg.toString());
			}

			return srchRes;
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
		catch (BadFieldNamesException e)
		{
			mLogger.severe(e.errorDescription);
			String[] index = new String[]
			{
				Integer.toString(e.errorCode)
			};
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_FIELD_NAMES,
				index
			);
		}
		catch (BadWhereException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_WHERE
			);
		}
		catch (BadSelectionIdException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_SELECTION_ID
			);
		}
		catch (BadOffsetException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_OFFSET
			);
		}
		catch (QueueException e)
		{
			throw transformQueueException(e);
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

	public QSelection selectQueueInfo
	(
		String		aQServer,
		String		aQServiceName,
		String[]	aPropNames,
		String		aWhere,
		String		anOrderBy
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aQServer", aQServer, ", ");
				msg.append("aQServiceName", aQServiceName, ", ");
				msg.append("aPropNames", aPropNames, ", ");
				msg.append("aWhere", aWhere, ", ");
				msg.append("anOrderBy", anOrderBy, "");
				mLogger.info(msg.toString());
			}

			String[] serverRef = new String[1];
			serverRef[0] = getServerRef();

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				String.class,
				String[].class,
				String.class,
				String.class,
				org.omg.CORBA.IntHolder.class
			};

			// Define input/output variables.
			org.omg.CORBA.IntHolder numSelected = new org.omg.CORBA.IntHolder();

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				aQServer,
				aQServiceName,
				aPropNames,
				(aWhere == null) ? "" : aWhere,
				(anOrderBy == null) ? "" : anOrderBy,
				numSelected
			};

			// Call remote method.
			String selectionId = (String)mRepository.doRemoteMethod
			(
				"TTQUEUE.QueueHelper",
				TTQUEUE.POA_NAME.value,
				TTQUEUE.OBJECT_ID.value,
				serverRef,
				null,
				"selectQueueInfo",
				formalArgs,
				actualArgs
			);

			QSelection qSel = new QSelection();
			qSel.m_SelectionId = selectionId;
			qSel.m_NumSelected = numSelected.value;

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("m_SelectionId", qSel.m_SelectionId, ", ");
				msg.append("m_NumSelected", qSel.m_NumSelected, "");
				mLogger.info(msg.toString());
			}

			return qSel;
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
		catch (BadFieldNamesException e)
		{
			mLogger.severe(e.errorDescription);
			String[] index = new String[]
			{
				Integer.toString(e.errorCode)
			};
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_FIELD_NAMES,
				index
			);
		}
		catch (BadWhereException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_WHERE
			);
		}
		catch (QueueException e)
		{
			throw transformQueueException(e);
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

	public QueueInfo[] getSelectedQueueInfo
	(
		String	aQServer,
		String	aQServiceName,
		String	aSelectionId,
		int		anOffset,
		int		aMaxQueues
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aQServer", aQServer, ", ");
				msg.append("aQServiceName", aQServiceName, ", ");
				msg.append("aSelectionId", aSelectionId, ", ");
				msg.append("anOffset", anOffset, ", ");
				msg.append("aMaxQueues", aMaxQueues, "");
				mLogger.info(msg.toString());
			}

			String[] serverRef = new String[1];
			serverRef[0] = getServerRef();

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				String.class,
				String.class,
				Integer.TYPE,
				Integer.TYPE
			};

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				aQServer,
				aQServiceName,
				(aSelectionId == null) ? "" : aSelectionId,
				new Integer(anOffset),
				new Integer(aMaxQueues)
			};

			// Call remote method.
			TTQUEUE.CQueueInfo[] qi_list =
				(TTQUEUE.CQueueInfo[])mRepository.doRemoteMethod
				(
					"TTQUEUE.QueueHelper",
					TTQUEUE.POA_NAME.value,
					TTQUEUE.OBJECT_ID.value,
					serverRef,
					null,
					"getSelectedQueueInfo",
					formalArgs,
					actualArgs
				);

			QueueInfo[] qInfo_list = new QueueInfo[qi_list.length];
			for (int i = 0; i < qi_list.length; i++)
			{
				qInfo_list[i] = transformCQueueInfo(qi_list[i]);
			}

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("qInfo_list", qInfo_list, "");
				mLogger.info(msg.toString());
			}

			return qInfo_list;
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
		catch (BadSelectionIdException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_SELECTION_ID
			);
		}
		catch (BadOffsetException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_OFFSET
			);
		}
		catch (QueueException e)
		{
			throw transformQueueException(e);
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

	public QSelectionResult searchEntries
	(
		String		aQServer,
		String		aQServiceName,
		int			aQueueId,
		String[]	aVarNames,
		String		aWhere,
		String		anOrderBy,
		int			anOffset,
		int			aMaxRows
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aQServer", aQServer, ", ");
				msg.append("aQServiceName", aQServiceName, ", ");
				msg.append("aQueueId", aQueueId, ", ");
				msg.append("aVarNames", aVarNames, ", ");
				msg.append("aWhere", aWhere, ", ");
				msg.append("anOrderBy", anOrderBy, ", ");
				msg.append("anOffset", anOffset, ", ");
				msg.append("aMaxRows", aMaxRows, "");
				mLogger.info(msg.toString());
			}

			String[] serverRef = new String[1];
			serverRef[0] = getServerRef();

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				String.class,
				Integer.TYPE,
				String[].class,
				String.class,
				String.class,
				Integer.TYPE,
				Integer.TYPE,
				org.omg.CORBA.IntHolder.class
			};

			// Define input/output variables.
			org.omg.CORBA.IntHolder numMatched = new org.omg.CORBA.IntHolder();

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				aQServer,
				aQServiceName,
				new Integer(aQueueId),
				(aVarNames == null) ? new String[0] : aVarNames,
				(aWhere == null) ? "" : aWhere,
				(anOrderBy == null) ? "" : anOrderBy,
				new Integer(anOffset),
				new Integer(aMaxRows),
				numMatched
			};

			// Call remote method.
			TTQUEUE.CSearchResult sRes =
				(TTQUEUE.CSearchResult)mRepository.doRemoteMethod
				(
					"TTQUEUE.QueueHelper",
					TTQUEUE.POA_NAME.value,
					TTQUEUE.OBJECT_ID.value,
					serverRef,
					null,
					"searchEntries",
					formalArgs,
					actualArgs
				);

			QSelectionResult srchRes = new QSelectionResult();
			srchRes.m_Results = transformCSearchResult(sRes);
			srchRes.m_NumMatched = numMatched.value;

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("m_Results", srchRes.m_Results, ", ");
				msg.append("m_NumMatched", srchRes.m_NumMatched, "");
				mLogger.info(msg.toString());
			}

			return srchRes;
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
		catch (BadFieldNamesException e)
		{
			mLogger.severe(e.errorDescription);
			String[] index = new String[]
			{
				Integer.toString(e.errorCode)
			};
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_FIELD_NAMES,
				index
			);
		}
		catch (BadSelectionIdException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_SELECTION_ID
			);
		}
		catch (BadOffsetException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_OFFSET
			);
		}
		catch (QueueException e)
		{
			throw transformQueueException(e);
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

	public QSelectionResult searchEntryRange
	(
		String		aQServer,
		String		aQServiceName,
		int			aQueueId,
		String[]	aVarNames,
		String		aWhere,
		String		anOrderBy,
		int			anOffset,
		int			aRangeSize
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aQServer", aQServer, ", ");
				msg.append("aQServiceName", aQServiceName, ", ");
				msg.append("aQueueId", aQueueId, ", ");
				msg.append("aVarNames", aVarNames, ", ");
				msg.append("aWhere", aWhere, ", ");
				msg.append("anOrderBy", anOrderBy, ", ");
				msg.append("anOffset", anOffset, ", ");
				msg.append("aRangeSize", aRangeSize, "");
				mLogger.info(msg.toString());
			}

			String[] serverRef = new String[1];
			serverRef[0] = getServerRef();

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				String.class,
				Integer.TYPE,
				String[].class,
				String.class,
				String.class,
				org.omg.CORBA.IntHolder.class,
				Integer.TYPE,
				org.omg.CORBA.IntHolder.class
			};

			// Define input/output variables.
			org.omg.CORBA.IntHolder totalNumMatched =
				new org.omg.CORBA.IntHolder();
			org.omg.CORBA.IntHolder offsetHolder =
				new org.omg.CORBA.IntHolder(anOffset);

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				aQServer,
				aQServiceName,
				new Integer(aQueueId),
				(aVarNames == null) ? new String[0] : aVarNames,
				(aWhere == null) ? "" : aWhere,
				(anOrderBy == null) ? "" : anOrderBy,
				offsetHolder,
				new Integer(aRangeSize),
				totalNumMatched
			};

			// Call remote method.
			TTQUEUE.CSearchResult sRes =
				(TTQUEUE.CSearchResult)mRepository.doRemoteMethod
				(
					"TTQUEUE.QueueHelper",
					TTQUEUE.POA_NAME.value,
					TTQUEUE.OBJECT_ID.value,
					serverRef,
					null,
					"searchEntryRange",
					formalArgs,
					actualArgs
				);

			QSelectionResult srchRes = new QSelectionResult();
			srchRes.m_Results = transformCSearchResult(sRes);
			srchRes.m_NumMatched = totalNumMatched.value;
			srchRes.m_Offset = offsetHolder.value;

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("m_Results", srchRes.m_Results, ", ");
				msg.append("m_NumMatched", srchRes.m_NumMatched, ", ");
				msg.append("m_Offset", srchRes.m_Offset, "");
				mLogger.info(msg.toString());
			}

			return srchRes;
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
		catch (BadFieldNamesException e)
		{
			mLogger.severe(e.errorDescription);
			String[] index = new String[]
			{
				Integer.toString(e.errorCode)
			};
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_FIELD_NAMES,
				index
			);
		}
		catch (BadSelectionIdException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_SELECTION_ID
			);
		}
		catch (BadOffsetException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_OFFSET
			);
		}
		catch (QueueException e)
		{
			throw transformQueueException(e);
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

	public QSelection selectEntries
	(
		String		aQServer,
		String		aQServiceName,
		int			aQueueId,
		String[]	aVarNames,
		String		aWhere,
		String		anOrderBy
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aQServer", aQServer, ", ");
				msg.append("aQServiceName", aQServiceName, ", ");
				msg.append("aQueueId", aQueueId, ", ");
				msg.append("aVarNames", aVarNames, ", ");
				msg.append("aWhere", aWhere, ", ");
				msg.append("anOrderBy", anOrderBy, "");
				mLogger.info(msg.toString());
			}

			String[] serverRef = new String[1];
			serverRef[0] = getServerRef();

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				String.class,
				Integer.TYPE,
				String[].class,
				String.class,
				String.class,
				org.omg.CORBA.IntHolder.class
			};

			// Define input/output variables.
			org.omg.CORBA.IntHolder numSelected = new org.omg.CORBA.IntHolder();

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				aQServer,
				aQServiceName,
				new Integer(aQueueId),
				(aVarNames == null) ? new String[0] : aVarNames,
				(aWhere == null) ? "" : aWhere,
				(anOrderBy == null) ? "" : anOrderBy,
				numSelected
			};

			// Call remote method.
			String selectionId = (String)mRepository.doRemoteMethod
			(
				"TTQUEUE.QueueHelper",
				TTQUEUE.POA_NAME.value,
				TTQUEUE.OBJECT_ID.value,
				serverRef,
				null,
				"selectEntries",
				formalArgs,
				actualArgs
			);

			QSelection qSel = new QSelection();
			qSel.m_SelectionId = selectionId;
			qSel.m_NumSelected = numSelected.value;

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("m_SelectionId", qSel.m_SelectionId, ", ");
				msg.append("m_NumSelected", qSel.m_NumSelected, "");
				mLogger.info(msg.toString());
			}

			return qSel;
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
		catch (BadFieldNamesException e)
		{
			mLogger.severe(e.errorDescription);
			String[] index = new String[]
			{
				Integer.toString(e.errorCode)
			};
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_FIELD_NAMES,
				index
			);
		}
		catch (QueueException e)
		{
			throw transformQueueException(e);
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

	public QueueEntry[] getSelectedEntries
	(
		String	aQServer,
		String	aQServiceName,
		String	aSelectionId,
		int		anOffset,
		int		aMaxEntries
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aQServer", aQServer, ", ");
				msg.append("aQServiceName", aQServiceName, ", ");
				msg.append("aSelectionId", aSelectionId, ", ");
				msg.append("anOffset", anOffset, ", ");
				msg.append("aMaxEntries", aMaxEntries, "");
				mLogger.info(msg.toString());
			}

			String[] serverRef = new String[1];
			serverRef[0] = getServerRef();

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				String.class,
				String.class,
				Integer.TYPE,
				Integer.TYPE
			};

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				aQServer,
				aQServiceName,
				(aSelectionId == null) ? "" : aSelectionId,
				new Integer(anOffset),
				new Integer(aMaxEntries)
			};

			// Call remote method.
			TTQUEUE.CQueueEntry[] qe_list =
				(TTQUEUE.CQueueEntry[])mRepository.doRemoteMethod
				(
					"TTQUEUE.QueueHelper",
					TTQUEUE.POA_NAME.value,
					TTQUEUE.OBJECT_ID.value,
					serverRef,
					null,
					"getSelectedEntries",
					formalArgs,
					actualArgs
				);

			QueueEntry[] qEntry_list = new QueueEntry[qe_list.length];
			for (int i = 0; i < qe_list.length; i++)
			{
				qEntry_list[i] = transformCQueueEntry(qe_list[i]);
			}

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("qEntry_list", qEntry_list, "");
				mLogger.info(msg.toString());
			}

			return qEntry_list;
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
		catch (BadSelectionIdException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_SELECTION_ID
			);
		}
		catch (BadOffsetException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_OFFSET
			);
		}
		catch (QueueException e)
		{
			throw transformQueueException(e);
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

	public SelectionResult getSelectedResults
	(
		String	aQServer,
		String	aQServiceName,
		String	aSelectionId,
		int		anOffset,
		int		aMaxEntries
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aQServer", aQServer, ", ");
				msg.append("aQServiceName", aQServiceName, ", ");
				msg.append("aSelectionId", aSelectionId, ", ");
				msg.append("anOffset", anOffset, ", ");
				msg.append("aMaxEntries", aMaxEntries, "");
				mLogger.info(msg.toString());
			}

			String[] serverRef = new String[1];
			serverRef[0] = getServerRef();

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				String.class,
				String.class,
				Integer.TYPE,
				Integer.TYPE
			};

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				aQServer,
				aQServiceName,
				(aSelectionId == null) ? "" : aSelectionId,
				new Integer(anOffset),
				new Integer(aMaxEntries)
			};

			// Call remote method.
			TTQUEUE.CSearchResult sRes =
				(TTQUEUE.CSearchResult)mRepository.doRemoteMethod
				(
					"TTQUEUE.QueueHelper",
					TTQUEUE.POA_NAME.value,
					TTQUEUE.OBJECT_ID.value,
					serverRef,
					null,
					"getSelectedResults",
					formalArgs,
					actualArgs
				);

			SelectionResult srchRes = transformCSearchResult(sRes);

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("srchRes", srchRes, "");
				mLogger.info(msg.toString());
			}

			return srchRes;
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
		catch (BadSelectionIdException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_SELECTION_ID
			);
		}
		catch (BadOffsetException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_OFFSET
			);
		}
		catch (QueueException e)
		{
			throw transformQueueException(e);
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

	public void freeSelectedResults
	(
		String	aQServer,
		String	aQServiceName,
		String	aSelectionId
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aQServer", aQServer, ", ");
				msg.append("aQServiceName", aQServiceName, ", ");
				msg.append("aSelectionId", aSelectionId, "");
				mLogger.info(msg.toString());
			}

			String[] serverRef = new String[1];
			serverRef[0] = getServerRef();

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
				aQServer,
				aQServiceName,
				(aSelectionId == null) ? "" : aSelectionId
			};

			// Call remote method.
			mRepository.doRemoteMethod
			(
				"TTQUEUE.QueueHelper",
				TTQUEUE.POA_NAME.value,
				TTQUEUE.OBJECT_ID.value,
				serverRef,
				null,
				"freeSelectedResults",
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
		catch (BadSelectionIdException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_SELECTION_ID
			);
		}
		catch (QueueException e)
		{
			throw transformQueueException(e);
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

	public int addEntry
	(
		String			aQServer,
		String			aQServiceName,
		int				aQueueId,
		QNameValue[]	anEntryVariables
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aQServer", aQServer, ", ");
				msg.append("aQServiceName", aQServiceName, ", ");
				msg.append("aQueueId", aQueueId, ", ");
				msg.append("anEntryVariables", anEntryVariables, "");
				mLogger.info(msg.toString());
			}

			String[] serverRef = new String[1];
			serverRef[0] = getServerRef();

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				String.class,
				Integer.TYPE,
				TTQUEUE.CNameValue[].class
			};

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				aQServer,
				aQServiceName,
				new Integer(aQueueId),
				transformQNameValue(anEntryVariables)
			};

			// Call remote method.
			Integer intVal = (Integer)mRepository.doRemoteMethod
			(
				"TTQUEUE.QueueHelper",
				TTQUEUE.POA_NAME.value,
				TTQUEUE.OBJECT_ID.value,
				serverRef,
				null,
				"addEntry",
				formalArgs,
				actualArgs
			);

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("intVal", intVal.intValue(), "");
				mLogger.info(msg.toString());
			}

			return intVal.intValue();
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
		catch (BadNameValueException e)
		{
			mLogger.severe(e.errorDescription);
			String[] index = new String[]
			{
				Integer.toString(e.errorCode)
			};
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_NAME_VALUE,
				index
			);
		}
		catch (QueueException e)
		{
			throw transformQueueException(e);
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

	public QueueEntry lockEntry
	(
		String	aQServer,
		String	aQServiceName,
		int		aQueueId,
		int		anEntryId
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aQServer", aQServer, ", ");
				msg.append("aQServiceName", aQServiceName, ", ");
				msg.append("aQueueId", aQueueId, ", ");
				msg.append("anEntryId", anEntryId, "");
				mLogger.info(msg.toString());
			}

			String[] serverRef = new String[1];
			serverRef[0] = getServerRef();

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				String.class,
				Integer.TYPE,
				Integer.TYPE
			};

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				aQServer,
				aQServiceName,
				new Integer(aQueueId),
				new Integer(anEntryId)
			};

			// Call remote method.
			TTQUEUE.CQueueEntry qe =
				(TTQUEUE.CQueueEntry)mRepository.doRemoteMethod
				(
					"TTQUEUE.QueueHelper",
					TTQUEUE.POA_NAME.value,
					TTQUEUE.OBJECT_ID.value,
					serverRef,
					null,
					"lockEntry",
					formalArgs,
					actualArgs
				);

			QueueEntry qEntry = transformCQueueEntry(qe);

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("qEntry", qEntry, "");
				mLogger.info(msg.toString());
			}

			return qEntry;
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
		catch (LockFailureException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_LOCK_FAILURE
			);
		}
		catch (MovedException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_ENTRY_MOVED
			);
		}
		catch (QueueException e)
		{
			throw transformQueueException(e);
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

	public QueueEntry lockNextEntry
	(
		String	aQServer,
		String	aQServiceName,
		int[]	aQueueIds
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aQServer", aQServer, ", ");
				msg.append("aQServiceName", aQServiceName, ", ");
				msg.append("aQueueIds", aQueueIds, "");
				mLogger.info(msg.toString());
			}

			String[] serverRef = new String[1];
			serverRef[0] = getServerRef();

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				String.class,
				int[].class
			};

			// Define input/output variables.
			Integer tmpArr[] = new Integer[aQueueIds.length];
			for (int i = 0; i < aQueueIds.length; i++)
			{
				tmpArr[i] = new Integer(aQueueIds[i]);
			}

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				aQServer,
				aQServiceName,
				aQueueIds
			};

			// Call remote method.
			TTQUEUE.CQueueEntry qe =
				(TTQUEUE.CQueueEntry)mRepository.doRemoteMethod
				(
					"TTQUEUE.QueueHelper",
					TTQUEUE.POA_NAME.value,
					TTQUEUE.OBJECT_ID.value,
					serverRef,
					null,
					"lockNextEntry",
					formalArgs,
					actualArgs
				);

			QueueEntry qEntry = transformCQueueEntry(qe);

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("qEntry", qEntry, "");
				mLogger.info(msg.toString());
			}

			return qEntry;
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
		catch (LockFailureException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_LOCK_FAILURE
			);
		}
		catch (QueueException e)
		{
			throw transformQueueException(e);
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

	public void releaseEntry
	(
		String			aQServer,
		String			aQServiceName,
		int				anEntryId,
		QNameValue[]	anEntryVariables
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aQServer", aQServer, ", ");
				msg.append("aQServiceName", aQServiceName, ", ");
				msg.append("anEntryId", anEntryId, ", ");
				msg.append("anEntryVariables", anEntryVariables, "");
				mLogger.info(msg.toString());
			}

			String[] serverRef = new String[1];
			serverRef[0] = getServerRef();

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				String.class,
				Integer.TYPE,
				TTQUEUE.CNameValue[].class
			};

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				aQServer,
				aQServiceName,
				new Integer(anEntryId),
				(anEntryVariables == null) ?
				new TTQUEUE.CNameValue[0] :
				transformQNameValue(anEntryVariables)
			};

			// Call remote method.
			mRepository.doRemoteMethod
			(
				"TTQUEUE.QueueHelper",
				TTQUEUE.POA_NAME.value,
				TTQUEUE.OBJECT_ID.value,
				serverRef,
				null,
				"releaseEntry",
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
		catch (BadNameValueException e)
		{
			mLogger.severe(e.errorDescription);
			String[] index = new String[]
			{
				Integer.toString(e.errorCode)
			};
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_NAME_VALUE,
				index
			);
		}
		catch (NotLockedException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_ENTRY_NOT_LOCKED
			);
		}
		catch (QueueException e)
		{
			throw transformQueueException(e);
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

	public void unlockEntry
	(
		String	aQServer,
		String	aQServiceName,
		int		anEntryId
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aQServer", aQServer, ", ");
				msg.append("aQServiceName", aQServiceName, ", ");
				msg.append("anEntryId", anEntryId, "");
				mLogger.info(msg.toString());
			}

			String[] serverRef = new String[1];
			serverRef[0] = getServerRef();

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				String.class,
				Integer.TYPE
			};

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				aQServer,
				aQServiceName,
				new Integer(anEntryId)
			};

			// Call remote method.
			mRepository.doRemoteMethod
			(
				"TTQUEUE.QueueHelper",
				TTQUEUE.POA_NAME.value,
				TTQUEUE.OBJECT_ID.value,
				serverRef,
				null,
				"unlockEntry",
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
		catch (NotLockedException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_ENTRY_NOT_LOCKED
			);
		}
		catch (QueueException e)
		{
			throw transformQueueException(e);
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

	public void updateEntry
	(
		String			aQServer,
		String			aQServiceName,
		int				anEntryId,
		QNameValue[]	anEntryVariables
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aQServer", aQServer, ", ");
				msg.append("aQServiceName", aQServiceName, ", ");
				msg.append("anEntryId", anEntryId, ", ");
				msg.append("anEntryVariables", anEntryVariables, "");
				mLogger.info(msg.toString());
			}

			String[] serverRef = new String[1];
			serverRef[0] = getServerRef();

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				String.class,
				Integer.TYPE,
				TTQUEUE.CNameValue[].class
			};

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				aQServer,
				aQServiceName,
				new Integer(anEntryId),
				(anEntryVariables == null) ?
				new TTQUEUE.CNameValue[0] :
				transformQNameValue(anEntryVariables)
			};

			// Call remote method.
			mRepository.doRemoteMethod
			(
				"TTQUEUE.QueueHelper",
				TTQUEUE.POA_NAME.value,
				TTQUEUE.OBJECT_ID.value,
				serverRef,
				null,
				"updateEntry",
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
		catch (NotLockedException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_ENTRY_NOT_LOCKED
			);
		}
		catch (BadNameValueException e)
		{
			mLogger.severe(e.errorDescription);
			String[] index = new String[]
			{
				Integer.toString(e.errorCode)
			};
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.QUEUE_BAD_NAME_VALUE,
				index
			);
		}
		catch (QueueException e)
		{
			throw transformQueueException(e);
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

	private TTException transformQueueException(QueueException anException)
	{
		mLogger.severe(anException.errorDescription);
		switch (anException.errorCode)
		{
		case -2:
			return new TTException
			(
				anException.errorDescription,
				TTRepositoryErrors.QUEUE_CONNECTION_ERROR
			);

		case -3:
			return new TTException
			(
				anException.errorDescription,
				TTRepositoryErrors.UNKNOWN_QUEUE
			);

		case -4:
			return new TTException
			(
				anException.errorDescription,
				TTRepositoryErrors.UNKNOWN_QUEUE_ENTRY
			);

		case -5:
			return new TTException
			(
				anException.errorDescription,
				TTRepositoryErrors.UNKNOWN_QUEUE_VARIABLE
			);

		case -6:
			return new TTException
			(
				anException.errorDescription,
				TTRepositoryErrors.UNKNOWN_QUEUE_LIST_ID
			);

		case -7:
			return new TTException
			(
				anException.errorDescription,
				TTRepositoryErrors.QUEUE_ENTRY_LOCKED
			);

		case -8:
			return new TTException
			(
				anException.errorDescription,
				TTRepositoryErrors.NO_QUEUE_ENTRY
			);

		case -9:
			return new TTException
			(
				anException.errorDescription,
				TTRepositoryErrors.UNKNOWN_QUEUE_CLIENT
			);

		case -1:
		default:
			return new TTException
			(
				anException.errorDescription,
				TTRepositoryErrors.QUEUE_ERROR
			);
		}
	}

	private int transformDataType(TTQUEUE.CDataType aDType)
	{
		if (aDType == TTQUEUE.CDataType.QCHAR)
		{
			return QDataType.CHAR;
		}
		else if (aDType == TTQUEUE.CDataType.INTEGER)
		{
			return QDataType.INTEGER;
		}
		else if (aDType == TTQUEUE.CDataType.SMALLINT)
		{
			return QDataType.SMALLINT;
		}
		else if (aDType == TTQUEUE.CDataType.DATE)
		{
			return QDataType.DATE;
		}
		else if (aDType == TTQUEUE.CDataType.TIME)
		{
			return QDataType.TIME;
		}
		else if (aDType == TTQUEUE.CDataType.DATETIME)
		{
			return QDataType.DATETIME;
		}
		else if (aDType == TTQUEUE.CDataType.CUST00)
		{
			return QDataType.CUST00;
		}
		else if (aDType == TTQUEUE.CDataType.CUST01)
		{
			return QDataType.CUST01;
		}
		else if (aDType == TTQUEUE.CDataType.CUST02)
		{
			return QDataType.CUST02;
		}
		else if (aDType == TTQUEUE.CDataType.CUST03)
		{
			return QDataType.CUST03;
		}
		else if (aDType == TTQUEUE.CDataType.CUST04)
		{
			return QDataType.CUST04;
		}
		else if (aDType == TTQUEUE.CDataType.CUST05)
		{
			return QDataType.CUST05;
		}
		else
		{
			return QDataType.CHAR;
		}
	}

	private QueueInfo transformCQueueInfo(TTQUEUE.CQueueInfo aQInfo)
	{
		QueueInfo qInfo = new QueueInfo();

		qInfo.m_QueueID = aQInfo.queueId;
		qInfo.m_Name = aQInfo.name;
		qInfo.m_Type = aQInfo.type;
		qInfo.m_CreateTime = aQInfo.createTime;
		qInfo.m_AccessTime = aQInfo.accessTime;
		qInfo.m_ModifyTime = aQInfo.modifyTime;
		qInfo.m_DeleteTime = aQInfo.deleteTime;
		qInfo.m_NumEntries = aQInfo.numEntries;
		qInfo.m_RequiredVarDefs =
			new QueueVarDef[aQInfo.requiredVarDefs.length];

		for (int i = 0; i < aQInfo.requiredVarDefs.length; i++)
		{
			TTQUEUE.CQueueVarDef CVarDef = aQInfo.requiredVarDefs[i];
			QueueVarDef varDef = new QueueVarDef();

			varDef.m_Name = CVarDef.name;
			varDef.m_Heading = CVarDef.heading;
			varDef.m_DefaultValue = CVarDef.defaultValue;
			varDef.m_DataType = transformDataType(CVarDef.dataType);

			qInfo.m_RequiredVarDefs[i] = varDef;
		}

		return qInfo;
	}

	private QueueEntry transformCQueueEntry(TTQUEUE.CQueueEntry aQEntry)
	{
		QueueEntry qEntry = new QueueEntry();

		qEntry.m_QueueID = aQEntry.queueId;
		qEntry.m_EntryID = aQEntry.entryId;
		qEntry.m_Priority = aQEntry.priority;
		qEntry.m_CreateTime = aQEntry.createTime;
		qEntry.m_ModifyTime = aQEntry.modifyTime;
		qEntry.m_EntryTime = aQEntry.entryTime;
		qEntry.m_LockNextEnabled = aQEntry.lockNextEnabled;
		qEntry.m_LockUser = aQEntry.lockUser;
		qEntry.m_Variables = new QNameValue[aQEntry.varList.length];

		for (int i = 0; i < aQEntry.varList.length; i++)
		{
			qEntry.m_Variables[i] = new QNameValue
			(
				aQEntry.varList[i].name,
				aQEntry.varList[i].value
			);
		}

		return qEntry;
	}

	private SelectionResult transformCSearchResult
	(
		TTQUEUE.CSearchResult	aSrchRes
	)
	{
		SelectionResult srchRes = new SelectionResult();

		if (aSrchRes.rows.length != 0)
		{
			srchRes.m_FieldNames = aSrchRes.fieldNames;
			srchRes.m_Rows =
				new String[aSrchRes.rows.length][aSrchRes.rows[0].length];

			for (int i = 0; i < aSrchRes.rows.length; i++)
			{
				for (int j = 0; j < aSrchRes.rows[0].length; j++)
				{
					srchRes.m_Rows[i][j] =
						new String(aSrchRes.rows[i][j].value);
				}
			}
		}
		else
		{
			srchRes.m_FieldNames = new String[0];
			srchRes.m_Rows = new String[0][0];
		}

		return srchRes;
	}

	private TTQUEUE.CNameValue[] transformQNameValue(QNameValue[] aNameValue)
	{
		TTQUEUE.CNameValue[] cNv = new TTQUEUE.CNameValue[aNameValue.length];

		for (int i = 0; i < aNameValue.length; i++)
		{
			cNv[i] = new TTQUEUE.CNameValue();

			cNv[i].name = new String(aNameValue[i].name);
			cNv[i].value = new String(aNameValue[i].value);
		}

		return cNv;
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
