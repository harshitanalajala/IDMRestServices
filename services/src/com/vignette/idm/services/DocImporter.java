////////////////////////////////////////////////////////////////////////////////
//	Title		:	DocImporter.java
//
//	Description	:	Class which imports/removes documents.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.services;

import java.util.logging.Level;

import com.towertechnology.common.errorhandling.TTCommonErrors;
import com.towertechnology.common.errorhandling.TTException;
import com.towertechnology.common.errorhandling.TTRepositoryErrors;
import com.vignette.idm.common.StringBufferS;

import TTCOMMON.NULL_VALUE;
import TTEXCEPTIONS.AuthenticationException;
import TTEXCEPTIONS.BadDocIdException;
import TTEXCEPTIONS.DocPermException;
import TTEXCEPTIONS.GenericException;
import TTEXCEPTIONS.InvalidDSException;
import TTEXCEPTIONS.TTlibException;
import TTEXCEPTIONS.UnknownDocException;
import TTIMPORT.InvalidAppException;
import TTIMPORT.OBJECT_ID;
import TTIMPORT.POA_NAME;
import TTIMPORT.TypeMethod;

public class DocImporter
{
	private static final String START = "Start: ";
	private static final String COMPLETE = "Complete: ";

	private static java.util.logging.Logger mLogger =
		java.util.logging.Logger.getLogger
		(
			"com.vignette.idm.services.DocImporter"
		);

	private IdmRepImpl mRepository;

	public DocImporter(IdmRepImpl aRepository)
	{
		mRepository = aRepository;
	}

	// Import a document using a document extension.
	public int importDocumentExt
	(
		org.omg.CORBA.StringHolder	anIfn,
		String						anAppName,
		String						aPoolName,
		byte[]						aContent,
		String						anExt
	) throws TTException
	{
		return importDocument
		(
			anIfn,
			anAppName,
			aPoolName,
			aContent,
			anExt,
			TypeMethod.DOSEXT
		);
	}

	// Import a document using a MIME type.
	public int importDocumentMime
	(
		org.omg.CORBA.StringHolder	anIfn,
		String						anAppName,
		String						aPoolName,
		byte[]						aContent,
		String						aMimeType
	) throws TTException
	{
		return importDocument
		(
			anIfn,
			anAppName,
			aPoolName,
			aContent,
			aMimeType,
			TypeMethod.MIME
		);
	}

	// Import a document using a rendition type.
	public int importDocumentRendition
	(
		org.omg.CORBA.StringHolder	anIfn,
		String						anAppName,
		String						aPoolName,
		byte[]						aContent,
		String						aRendition
	) throws TTException
	{
		return importDocument
		(
			anIfn,
			anAppName,
			aPoolName,
			aContent,
			aRendition,
			TypeMethod.FORMATKEY
		);
	}

	private int importDocument
	(
		org.omg.CORBA.StringHolder	anIfn,
		String						anAppName,
		String						aPoolName,
		byte[]						aContent,
		String						aType,
		TypeMethod					aTypeMeth
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("anIfn", anIfn.value, ", ");
				msg.append("anAppName", anAppName, ", ");
				msg.append("aPoolName", aPoolName, ", ");
				msg.append("aContent", aContent, ", ");
				msg.append("aType", aType, ", ");
				msg.append("aTypeMeth", aTypeMeth.value(), "");
				mLogger.info(msg.toString());
			}

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				String.class,
				byte[].class,
				TypeMethod.class,
				String.class,
				org.omg.CORBA.StringHolder.class
			};

			// Define input/output variables.
			if (anIfn.value == null)
			{
				anIfn.value = "";
			}

			// Define actual arguments.
			java.lang.Object[] args = new java.lang.Object[]
			{
				mRepository.getIdmTicket(),
				(anAppName == null) ? NULL_VALUE.value : anAppName,
				aType,
				aContent,
				aTypeMeth,
				aPoolName,
				anIfn
			};

			// Call remote method.
			Integer	pgs_imported = (Integer)mRepository.doRemoteMethod
			(
				"TTIMPORT.ImporterHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"importDoc",
				formalArgs,
				args
			);

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("anIfn", anIfn.value, ", ");
				msg.append("pgs_imported", pgs_imported.intValue(), "");
				mLogger.info(msg.toString());
			}

			return pgs_imported.intValue();
		}
		catch (InvalidAppException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.COLLECTION_UNKNOWN
			);
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

	public void removeDocument(String anIfn) throws TTException
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
			java.lang.Object[] args = new java.lang.Object[]
			{
				mRepository.getIdmTicket(),
				anIfn
			};

			// Call remote method.
			mRepository.doRemoteMethod
			(
				"TTIMPORT.ImporterHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"removeDoc",
				formalArgs,
				args
			);

			// Remove cached pages for the specified IFN.
			RendererCache cache = RendererCache.getInstance();
			RendererUserCache userCache = cache.getUserCache
			(
				mRepository.getTicket()
			);
			userCache.removeFromCache(anIfn);

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
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
