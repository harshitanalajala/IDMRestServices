////////////////////////////////////////////////////////////////////////////////
//	Title		:	Library.java
//
//	Description	:	Class which handles library services.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.services;

import java.util.logging.Level;

import com.towertechnology.common.errorhandling.TTCommonErrors;
import com.towertechnology.common.errorhandling.TTException;
import com.towertechnology.common.errorhandling.TTRepositoryErrors;
import com.vignette.idm.common.Image;
import com.vignette.idm.common.NameValue;
import com.vignette.idm.common.StringBufferS;

import TTCOMMON.NULL_VALUE;
import TTEXCEPTIONS.AppPermException;
import TTEXCEPTIONS.AuthenticationException;
import TTEXCEPTIONS.BadDocIdException;
import TTEXCEPTIONS.DocPermException;
import TTEXCEPTIONS.GenericException;
import TTEXCEPTIONS.InvalidDSException;
import TTEXCEPTIONS.TTlibException;
import TTEXCEPTIONS.UnknownDocException;
import TTLIBRARY.CheckOutDetail;
import TTLIBRARY.CheckedOutException;
import TTLIBRARY.ContentHolder;
import TTLIBRARY.DatabaseException;
import TTLIBRARY.NameValueSeqHolder;
import TTLIBRARY.NotCheckedOutException;
import TTLIBRARY.NotLibAppException;
import TTLIBRARY.NotOwnerException;
import TTLIBRARY.OBJECT_ID;
import TTLIBRARY.POA_NAME;

public class Library
{
	private static final String START = "Start: ";
	private static final String COMPLETE = "Complete: ";

	private static java.util.logging.Logger mLogger =
		java.util.logging.Logger.getLogger
		(
			"com.vignette.idm.services.Library"
		);

	private IdmRepImpl mRepository = null;

	public Library(IdmRepImpl aRepository)
	{
		mRepository = aRepository;
	}

	public int createRevision
	(
		String						aAppName,
		NameValue[]					aFields,
		String						aStatus,
		String						aContentType,
		byte[]						aContent,
		String						aDiskset,
		String						aPoolName,
		org.omg.CORBA.StringHolder	aNewIfn
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aAppName", aAppName, ", ");
				msg.append("aFields", aFields, ", ");
				msg.append("aStatus", aStatus, ", ");
				msg.append("aContentType", aContentType, ", ");
				msg.append("aContent", aContent, ", ");
				msg.append("aDiskset", aDiskset, ", ");
				msg.append("aPoolName", aPoolName, "");
				mLogger.info(msg.toString());
			}

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				TTLIBRARY.NameValue[].class,
				String.class,
				String.class,
				byte[].class,
				String.class,
				String.class,
				org.omg.CORBA.StringHolder.class,
				org.omg.CORBA.IntHolder.class
			};

			// Convert input variables.
			TTLIBRARY.NameValue[] fields =
				new TTLIBRARY.NameValue[aFields.length];
			for (int i = 0; i < fields.length; i++)
			{
				String fieldValue = aFields[i].getValue();
				fields[i] = new TTLIBRARY.NameValue
				(
					aFields[i].getName(),
					(fieldValue == null) ? NULL_VALUE.value : fieldValue
				);
			}

			// Define output variables.
			org.omg.CORBA.IntHolder numPages = new org.omg.CORBA.IntHolder();

			// Define actual arguments.
			java.lang.Object[] args = new java.lang.Object[]
			{
				mRepository.getIdmTicket(),
				aAppName,
				fields,
				aStatus,
				aContentType,
				aContent,
				aDiskset,
				aPoolName,
				aNewIfn,
				numPages
			};

			// Call remote method.
			mRepository.doRemoteMethod
			(
				"TTLIBRARY.LibrarianHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"createRevision",
				formalArgs,
				args
			);

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("aNewIfn", aNewIfn.value, ", ");
				msg.append("numPages", numPages.value, "");
				mLogger.info(msg.toString());
			}

			return numPages.value;
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
		catch (NotLibAppException e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTRepositoryErrors.COLLECTION_NOT_MANAGED
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
		catch (DatabaseException e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTRepositoryErrors.DATABASE_ERROR
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

	public Image checkOutRevision
	(
		String	aAppName,
		String	aIfn,
		String	aComment
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aAppName", aAppName, ", ");
				msg.append("aIfn", aIfn, ", ");
				msg.append("aComment", aComment, "");
				mLogger.info(msg.toString());
			}

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				String.class,
				String.class,
				ContentHolder.class,
				org.omg.CORBA.StringHolder.class
			};

			// Define output variables.
			ContentHolder content = new ContentHolder();
			org.omg.CORBA.StringHolder contentType =
				new org.omg.CORBA.StringHolder();

			// Define actual arguments.
			java.lang.Object[] args = new java.lang.Object[]
			{
				mRepository.getIdmTicket(),
				aAppName,
				aIfn,
				aComment,
				content,
				contentType
			};

			// Call remote method.
			mRepository.doRemoteMethod
			(
				"TTLIBRARY.LibrarianHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"checkOutRevision",
				formalArgs,
				args
			);

			Image image = new Image();
			image.mData = content.value;
			image.mExtension = contentType.value;

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("mData", image.mData, ", ");
				msg.append("mExtension", image.mExtension, "");
				mLogger.info(msg.toString());
			}

			return image;
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
		catch (NotLibAppException e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTRepositoryErrors.COLLECTION_NOT_MANAGED
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
		catch (CheckedOutException e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTRepositoryErrors.DOCUMENT_ALREADY_CHECKOUT
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

	public void cancelCheckOut
	(
		String	aAppName,
		String	aIfn
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aAppName", aAppName, ", ");
				msg.append("aIfn", aIfn, "");
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
			java.lang.Object[] args = new java.lang.Object[]
			{
				mRepository.getIdmTicket(),
				aAppName,
				aIfn
			};

			// Call remote method.
			mRepository.doRemoteMethod
			(
				"TTLIBRARY.LibrarianHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"cancelCheckOut",
				formalArgs,
				args
			);

			mLogger.info(COMPLETE);
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
		catch (NotLibAppException e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTRepositoryErrors.COLLECTION_NOT_MANAGED
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
		catch (NotOwnerException e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTRepositoryErrors.DOCUMENT_ALREADY_CHECKOUT
			);
		}
		catch (NotCheckedOutException e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTRepositoryErrors.DOCUMENT_NOT_CHECKOUT
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

	public void getCheckOutDetail
	(
		String						aAppName,
		String						aIfn,
		org.omg.CORBA.StringHolder	aUserID,
		org.omg.CORBA.StringHolder	aDate,
		org.omg.CORBA.StringHolder	aTime,
		org.omg.CORBA.StringHolder	aComment
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aAppName", aAppName, ", ");
				msg.append("aIfn", aIfn, "");
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
			java.lang.Object[] args = new java.lang.Object[]
			{
				mRepository.getIdmTicket(),
				aAppName,
				aIfn
			};

			// Call remote method.
			CheckOutDetail checkOutDetail =
				(CheckOutDetail)mRepository.doRemoteMethod
				(
					"TTLIBRARY.LibrarianHelper",
					POA_NAME.value,
					OBJECT_ID.value,
					null,
					"",
					"getCheckOutDetail",
					formalArgs,
					args
				);

			// Fill in output parameters.
			aUserID.value = checkOutDetail.mUserID;
			aDate.value = checkOutDetail.mDate;
			aTime.value = checkOutDetail.mTime;
			aComment.value = checkOutDetail.mComment;

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("aUserID", aUserID.value, ", ");
				msg.append("aDate", aDate.value, ", ");
				msg.append("aTime", aTime.value, ", ");
				msg.append("aComment", aComment.value, "");
				mLogger.info(msg.toString());
			}
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
		catch (NotLibAppException e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTRepositoryErrors.COLLECTION_NOT_MANAGED
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
		catch (NotCheckedOutException e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTRepositoryErrors.DOCUMENT_NOT_CHECKOUT
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

	public void listCheckedOutRevisionPermission
	(
		String	aAppName,
		boolean	aAll
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aAppName", aAppName, ", ");
				msg.append("aAll", aAll, "");
				mLogger.info(msg.toString());
			}

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				Boolean.TYPE
			};

			// Define actual arguments.
			java.lang.Object[] args = new java.lang.Object[]
			{
				mRepository.getIdmTicket(),
				aAppName,
				new Boolean(aAll)
			};

			// Call remote method.
			mRepository.doRemoteMethod
			(
				"TTLIBRARY.LibrarianHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"listCheckedOutRevisionPermission",
				formalArgs,
				args
			);

			mLogger.info(COMPLETE);
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
		catch (NotLibAppException e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTRepositoryErrors.COLLECTION_NOT_MANAGED
			);
		}
		catch (AppPermException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.COLLECTION_PERMISSION_DENIED
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

	public int checkInRevision
	(
		String						aAppName,
		String						aIfn,
		String						aStatus,
		String						aContentType,
		byte[]						aContent,
		org.omg.CORBA.StringHolder	aNewIfn
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aAppName", aAppName, ", ");
				msg.append("aIfn", aIfn, ", ");
				msg.append("aStatus", aStatus, ", ");
				msg.append("aContentType", aContentType, ", ");
				msg.append("aContent", aContent, "");
				mLogger.info(msg.toString());
			}

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				org.omg.CORBA.StringHolder.class,
				String.class,
				String.class,
				byte[].class,
				org.omg.CORBA.IntHolder.class
			};

			// Define output variables.
			aNewIfn.value = aIfn;
			org.omg.CORBA.IntHolder numPages = new org.omg.CORBA.IntHolder();

			// Define actual arguments.
			java.lang.Object[] args = new java.lang.Object[]
			{
				mRepository.getIdmTicket(),
				aAppName,
				aNewIfn,
				aStatus,
				aContentType,
				aContent,
				numPages
			};

			// Call remote method.
			mRepository.doRemoteMethod
			(
				"TTLIBRARY.LibrarianHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"checkInRevision",
				formalArgs,
				args
			);

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("aNewIfn", aNewIfn.value, ", ");
				msg.append("numPages", numPages.value, "");
				mLogger.info(msg.toString());
			}

			return numPages.value;
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
		catch (NotLibAppException e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTRepositoryErrors.COLLECTION_NOT_MANAGED
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
		catch (NotOwnerException e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTRepositoryErrors.DOCUMENT_ALREADY_CHECKOUT
			);
		}
		catch (NotCheckedOutException e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTRepositoryErrors.DOCUMENT_NOT_CHECKOUT
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

	public int publishRevision
	(
		String						aAppName,
		String						aIfn,
		org.omg.CORBA.StringHolder	aNewIfn
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aAppName", aAppName, ", ");
				msg.append("aIfn", aIfn, "");
				mLogger.info(msg.toString());
			}

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				org.omg.CORBA.StringHolder.class,
				org.omg.CORBA.IntHolder.class
			};

			// Define output variables.
			aNewIfn.value = aIfn;
			org.omg.CORBA.IntHolder numPages = new org.omg.CORBA.IntHolder();

			// Define actual arguments.
			java.lang.Object[] args = new java.lang.Object[]
			{
				mRepository.getIdmTicket(),
				aAppName,
				aNewIfn,
				numPages
			};

			// Call remote method.
			mRepository.doRemoteMethod
			(
				"TTLIBRARY.LibrarianHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"publishRevision",
				formalArgs,
				args
			);

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("aNewIfn", aNewIfn.value, ", ");
				msg.append("numPages", numPages.value, "");
				mLogger.info(msg.toString());
			}

			return numPages.value;
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
		catch (NotLibAppException e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTRepositoryErrors.COLLECTION_NOT_MANAGED
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
		catch (CheckedOutException e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTRepositoryErrors.DOCUMENT_ALREADY_CHECKOUT
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

	public String searchRevisionHistoryPermission
	(
		String	aAppName,
		String	aIfn
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aAppName", aAppName, ", ");
				msg.append("aIfn", aIfn, "");
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
			java.lang.Object[] args = new java.lang.Object[]
			{
				mRepository.getIdmTicket(),
				aAppName,
				aIfn
			};

			// Call remote method.
			String histName = (String)mRepository.doRemoteMethod
			(
				"TTLIBRARY.LibrarianHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"searchRevisionHistoryPermission",
				formalArgs,
				args
			);

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("histName", histName, "");
				mLogger.info(msg.toString());
			}

			return histName;
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
		catch (NotLibAppException e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTRepositoryErrors.COLLECTION_NOT_MANAGED
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
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
