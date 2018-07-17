////////////////////////////////////////////////////////////////////////////////
//	Title		:	Retrieval.java
//
//	Description	:	Class which handles rendition/document retrieval.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.services;

import java.util.logging.Level;

import TTRETRIEVAL.BadFilePathException;
import TTRETRIEVAL.BadPagesException;
import TTRETRIEVAL.BadParamException;
import TTRETRIEVAL.OBJECT_ID;
import TTRETRIEVAL.POA_NAME;
import TTRETRIEVAL.Param;
import TTRETRIEVAL.ParamDataHolder;
import TTRETRIEVAL.RenditionType;
import TTRETRIEVAL.RenditionTypeDataHolder;
import TTRETRIEVAL.UnknownSubpageException;

import TTEXCEPTIONS.AuthenticationException;
import TTEXCEPTIONS.BadDocIdException;
import TTEXCEPTIONS.DocPermException;
import TTEXCEPTIONS.GenericException;
import TTEXCEPTIONS.InvalidDSException;
import TTEXCEPTIONS.TTlibException;
import TTEXCEPTIONS.UnknownDocException;
import TTEXCEPTIONS.UnknownPageException;

import com.towertechnology.common.environment.EnvironmentClassServer;

import com.towertechnology.common.errorhandling.TTCommonErrors;
import com.towertechnology.common.errorhandling.TTException;
import com.towertechnology.common.errorhandling.TTRepositoryErrors;

import com.vignette.idm.common.IDMConfiguration;
import com.vignette.idm.common.Image;
import com.vignette.idm.common.NameValue;
import com.vignette.idm.common.RenditionDetail;
import com.vignette.idm.common.StringBufferS;

public class Retrieval
{
	private static final String START = "Start: ";
	private static final String COMPLETE = "Complete: ";

	private static java.util.logging.Logger mLogger =
		java.util.logging.Logger.getLogger
		(
			"com.vignette.idm.services.Retrieval"
		);

	private static IDMConfiguration mConfig = null;
	private IdmRepImpl mRepository = null;

	static
	{
		mConfig = (IDMConfiguration)EnvironmentClassServer.getClass
		(
			IDMConfiguration.class
		);
	}

	/**
	 *	Initialises the newly created retrieval object.
	 *
	 *	@param aRepository contains the ticket required to access the repository
	 *
	 */
	public Retrieval(IdmRepImpl aRepository)
	{
		mRepository = aRepository;
	}

	/**
	 *
	 */
	private byte[] fetchData(String anIfn, int aPageNum) throws Exception
	{
		String discardFilePath = "";

		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("anIfn", anIfn, ", ");
				msg.append("aPageNum", aPageNum, "");
				mLogger.info(msg.toString());
			}

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				Integer.TYPE,
				org.omg.CORBA.StringHolder.class,
				org.omg.CORBA.IntHolder.class,
				org.omg.CORBA.IntHolder.class
			};

			// Define input/output variables.
			org.omg.CORBA.StringHolder filePath =
				new org.omg.CORBA.StringHolder();
			org.omg.CORBA.IntHolder fileLength =
				new org.omg.CORBA.IntHolder();
			org.omg.CORBA.IntHolder fileChunk =
				new org.omg.CORBA.IntHolder();

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				anIfn,
				new Integer(aPageNum),
				filePath,
				fileLength,
				fileChunk
			};

			// Call remote method.
			mRepository.doRemoteMethod
			(
				"TTRETRIEVAL.DocReaderHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"getRenditionDataIntoTempFile",
				formalArgs,
				actualArgs
			);

			discardFilePath = filePath.value;
			byte[] data = new byte[fileLength.value];

			if (mLogger.isLoggable(Level.FINE))
			{
				StringBufferS msg = new StringBufferS();
				msg.append("filePath", filePath.value, ", ");
				msg.append("fileLength", fileLength.value, ", ");
				msg.append("fileChunk", fileChunk.value, "");
				mLogger.fine(msg.toString());
			}

			// Retrieving the file in chunks.
			for (int ii = 0; ii < fileLength.value; ii += fileChunk.value)
			{
				byte[] portionData = fetchChunk
				(
					filePath.value,
					fileChunk.value,
					ii
				);

				System.arraycopy
				(
					portionData,
					0,
					data,
					ii,
					portionData.length
				);
			}

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("data", data, "");
				mLogger.info(msg.toString());
			}

			return data;
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
		catch (UnknownPageException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.SECTION_UNKNOWN
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
		finally
		{
			if (discardFilePath.length() > 0)
			{
				discardFile(discardFilePath);
			}
		}
	}

	/**
	 *
	 */
	private byte[] fetchChunk
	(
		String 	aFilePath,
		int		aLength,
		int 	anOffset
	) throws Exception
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS();
				msg.append("aFilePath", aFilePath, ", ");
				msg.append("aLength", aLength, ", ");
				msg.append("anOffset", anOffset, "");
				mLogger.info(msg.toString());
			}

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				Integer.TYPE,
				Integer.TYPE
			};

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				aFilePath,
				new Integer(aLength),
				new Integer(anOffset)
			};

			// Call remote method.
			byte[] data = (byte[])mRepository.doRemoteMethod
			(
				"TTRETRIEVAL.DocReaderHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"getDocumentChunk",
				formalArgs,
				actualArgs
			);

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("data", data, "");
				mLogger.info(msg.toString());
			}

			return data;
		}
		catch (BadFilePathException e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTCommonErrors.INVALID_ARGUMENT
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
	 *
	 */
	private void discardFile(String aFilePath) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aFilePath", aFilePath, "");
				mLogger.info(msg.toString());
			}

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class
			};

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				aFilePath
			};

			// Call remote method.
			mRepository.doRemoteMethod
			(
				"TTRETRIEVAL.DocReaderHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"closeDocument",
				formalArgs,
				actualArgs
			);

			mLogger.info(COMPLETE);

			return;
		}
		catch (BadFilePathException e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTCommonErrors.INVALID_ARGUMENT
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
	 *	Retrieves the possible rendition types for a given document page.
	 *
	 *	@param anIfn identifies the document
	 *	@param aPageNum identifies the specific page in the document
	 *
	 *	@return Array of possible rendition types
	 *
	 *	@throws TTException
	 *
	 */
	public RenditionDetail[] getRenditionTypes(String anIfn, int aPageNum)
	throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("anIfn", anIfn, ", ");
				msg.append("aPageNum", aPageNum, "");
				mLogger.info(msg.toString());
			}

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				Integer.TYPE,
				RenditionTypeDataHolder.class
			};

			// Define input/output variables.
			RenditionTypeDataHolder renditionTypes =
				new RenditionTypeDataHolder();

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				anIfn,
				new Integer(aPageNum),
				renditionTypes
			};

			// Call remote method.
			mRepository.doRemoteMethod
			(
				"TTRETRIEVAL.DocReaderHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"getRenditionTypes",
				formalArgs,
				actualArgs
			);

			// Convert result.
			RenditionDetail[] rendDet =
				new RenditionDetail[renditionTypes.value.length];
			for (int i = 0; i < renditionTypes.value.length; i++)
			{
				rendDet[i] = new RenditionDetail
				(
					renditionTypes.value[i].mRenditionName,
					renditionTypes.value[i].mMimeType,
					renditionTypes.value[i].mZoomFactor
				);
			}

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("rendDet", rendDet, "");
				mLogger.info(msg.toString());
			}

			return rendDet;
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
		catch (UnknownPageException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.SECTION_UNKNOWN
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

	/**
	 *	Retrieves the image for a specific document page/subpage.
	 *
	 *	@param anIfn identifies the document
	 *	@param aPageNum identifies the specific page in the document
	 *	@param aSubPageNum identifies the specific subpage of the document page
	 *	@param aParams optional array of rendition parameters
	 *
	 *	@return Structure containing the retrieved image and info about it
	 *
	 *	@throws TTException
	 *
	 */
	public Image getImageData
	(
		String		anIfn,
		int			aPageNum,
		int			aSubPageNum,
		NameValue[]	aParams
	) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("anIfn", anIfn, ", ");
				msg.append("aPageNum", aPageNum, ", ");
				msg.append("aSubPageNum", aSubPageNum, ", ");
				msg.append("aParams", aParams, "");
				mLogger.info(msg.toString());
			}

			// Create a filename using the IFN number.
			String fileName = anIfn.replace('/', '-') + "-" + aPageNum;

			// Convert parameters.
			Param[] parameters = new Param[aParams.length];
			for (int i = 0; i < aParams.length; i++)
			{
				parameters[i] = new Param
				(
					aParams[i].getName(),
					aParams[i].getValue()
				);
			}

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				Integer.TYPE,
				Integer.TYPE,
				ParamDataHolder.class,
				org.omg.CORBA.StringHolder.class,
				org.omg.CORBA.IntHolder.class,
				org.omg.CORBA.IntHolder.class,
				org.omg.CORBA.StringHolder.class,
				org.omg.CORBA.StringHolder.class
			};

			// Define input/output variables.
			ParamDataHolder params =
				new ParamDataHolder(parameters);
			org.omg.CORBA.StringHolder ifn =
				new org.omg.CORBA.StringHolder();
			org.omg.CORBA.IntHolder pageNum =
				new org.omg.CORBA.IntHolder();
			org.omg.CORBA.IntHolder subPageCount =
				new org.omg.CORBA.IntHolder();
			org.omg.CORBA.StringHolder ext =
				new org.omg.CORBA.StringHolder();
			org.omg.CORBA.StringHolder extOrig =
				new org.omg.CORBA.StringHolder();

			// Extract the format from the options parameters to send to
			// TTMT_Search2() or for rendition on the fly.
			for (int i = 0; i < parameters.length; i++)
			{
				if (parameters[i].mParamName.trim().equalsIgnoreCase("FORMAT"))
				{
					ext.value = parameters[i].mParamValue.toUpperCase();
				}
			}

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				anIfn,
				new Integer(aPageNum),
				new Integer(aSubPageNum),
				params,
				ifn,
				pageNum,
				subPageCount,
				ext,
				extOrig
			};

			// Call remote method.
			Boolean found = (Boolean)mRepository.doRemoteMethod
			(
				"TTRETRIEVAL.DocReaderHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"getRenditionIfn",
				formalArgs,
				actualArgs
			);

			// Determine which renderer to use based on file extension of
			// the original document page.
			String rendererType = mConfig.getConfigurationValue
			(
				"Renderer." + extOrig.value
			);

			// Setup the information required to perform the rendering.
			RendererArgs rendererArgs = new RendererArgs
			(
				rendererType,
				aSubPageNum,
				params.value,
				fileName + "." + extOrig.value,
				ext.value
			);

			// Check if the specified document subpage is already in the cache.
			// If it is, simply return the data from the cache.
			// Note that the subpage entries for the cache are made unique
			// for different sets of parameters for the same subpage by
			// calculating the CRC-32 of the rendering parameter string and
			// appending it to the subpage key to be used for the cache.
			ImageData imageData = null;
			RendererCache cache = RendererCache.getInstance();
			RendererUserCache userCache = cache.getUserCache
			(
				mRepository.getTicket()
			);
			String subPageKey = fileName + "-" + aSubPageNum + "-" +
				rendererArgs.getInParamsCrcStr() + "." + ext.value;
			if
			(
				userCache.isSubPageInCache
				(
					subPageKey,
					rendererArgs.getInParamsStr()
				) == true
			)
			{
				if (mLogger.isLoggable(Level.FINE))
				{
					StringBufferS msg = new StringBufferS
					(
						"Fetching cached image, subPageKey = " + subPageKey
					);
					mLogger.fine(msg.toString());
				}

				imageData = userCache.fetchSubPageFromCache(subPageKey);
			}
			else
			{
				// Check whether a pre-converted image is available or we
				// must fetch an image which does not require conversion.
				imageData = new ImageData();
				if (found.booleanValue() == true)
				{
					// If the requested format is the same as the original
					// format then fetch original image.
					if (extOrig.value.equalsIgnoreCase(ext.value))
					{
						byte[] bytesOrig = null;
						if (userCache.isPageInCache(fileName) == true)
						{
							if (mLogger.isLoggable(Level.FINE))
							{
								StringBufferS msg = new StringBufferS
								(
									"Fetching original image from cache, " +
									"pageKey=" + fileName
								);
								mLogger.fine(msg.toString());
							}

							// Fetch original image from cache.
							bytesOrig = userCache.getPageData(fileName);
						}
						else
						{
							if (mLogger.isLoggable(Level.FINE))
							{
								StringBufferS msg = new StringBufferS
								(
									"Fetching original image: IFN=" + anIfn +
									", Page=" + aPageNum
								);
								mLogger.fine(msg.toString());
							}

							// Fetch original image from IDM Server.
							bytesOrig = fetchData(anIfn, aPageNum);

							if (mLogger.isLoggable(Level.FINE))
							{
								StringBufferS msg = new StringBufferS
								(
									"Caching original image, pageKey=" +
									fileName
								);
								mLogger.fine(msg.toString());
							}

							// Add original image to cache.
							userCache.addPageToCache
							(
								fileName,
								extOrig.value,
								bytesOrig
							);
						}

						// Create the object we are going to return.
						imageData.mParams = params.value;
						imageData.mSubPageCount = 0;
						imageData.mData = bytesOrig;
						imageData.mExtension = ext.value;
						imageData.mContentType =
							getMimeType(imageData.mExtension);
					}
					else
					{
						// Fetch pre-converted image.
						if (mLogger.isLoggable(Level.FINE))
						{
							StringBufferS msg = new StringBufferS
							(
								"Fetching pre-converted image: " +
								"IFN=" + ifn.value + ", Page=" + pageNum.value
							);
							mLogger.fine(msg.toString());
						}

						// Fetch the pre-converted image.
						byte[] bytes = fetchData(ifn.value, pageNum.value);

						// Create the object we are going to return.
						imageData.mParams = params.value;
						imageData.mSubPageCount = subPageCount.value;
						imageData.mData = bytes;
						imageData.mExtension = ext.value;
						imageData.mContentType =
							getMimeType(imageData.mExtension);

						if (mLogger.isLoggable(Level.FINE))
						{
							StringBufferS msg = new StringBufferS
							(
								"Caching pre-converted image, subPageKey=" +
								subPageKey
							);
							mLogger.fine(msg.toString());
						}

						// Add the IFN subpage into the cache.
						userCache.addSubPageToCache(subPageKey, imageData);
					}
				}
				else
				{
					// Perform on the fly conversion.
					// Check if the original IFN page is already in the cache.
					// If it is simply load it from the cache instead of
					// fetching it from IDM.
					byte[] bytesOrig = null;
					if (userCache.isPageInCache(fileName) == true)
					{
						if (mLogger.isLoggable(Level.FINE))
						{
							StringBufferS msg = new StringBufferS
							(
								"Fetching original image from cache for " +
								"conversion, pageKey=" + fileName
							);
							mLogger.fine(msg.toString());
						}

						// Fetch original image from cache.
						bytesOrig = userCache.getPageData(fileName);
					}
					else
					{
						if (mLogger.isLoggable(Level.FINE))
						{
							StringBufferS msg = new StringBufferS
							(
								"Fetching original image for conversion: IFN=" +
								anIfn + ", Page=" + aPageNum
							);
							mLogger.fine(msg.toString());
						}

						// Fetch original image from IDM Server.
						bytesOrig = fetchData(anIfn, aPageNum);

						if (mLogger.isLoggable(Level.FINE))
						{
							StringBufferS msg = new StringBufferS
							(
								"Caching original image for conversion, " +
								"pageKey=" + fileName
							);
							mLogger.fine(msg.toString());
						}

						// Add original image to cache.
						userCache.addPageToCache
						(
							fileName,
							extOrig.value,
							bytesOrig
						);
					}

					// Check if a converter has been selected.
					if (rendererType == null)
					{
						String msg2 = "No converter has been selected";
						mLogger.severe(msg2);
						throw new TTException
						(
							msg2,
							TTCommonErrors.LOW_LEVEL_EXCEPTION
						);
					}

					if (mLogger.isLoggable(Level.FINE))
					{
						StringBufferS msg = new StringBufferS
						(
							"Selected converter=" + rendererType
						);
						mLogger.fine(msg.toString());
					}

					// Perform the conversion.
					rendererArgs.setData(bytesOrig);
					WSRenderer renderer = new WSRenderer(rendererArgs);
					byte[] bytesRendered = renderer.render();

					// Create the object we are going to return.
					imageData.mParams = params.value;
					imageData.mSubPageCount	= rendererArgs.getSubPageCount();
					imageData.mData = bytesRendered;
					imageData.mExtension = rendererArgs.getExtension();
					imageData.mContentType = getMimeType(imageData.mExtension);

					if (mLogger.isLoggable(Level.FINE))
					{
						StringBufferS msg = new StringBufferS
						(
							"Caching converted image, subPageKey=" + subPageKey
						);
						mLogger.fine(msg.toString());
					}

					// Add the IFN subpage into the cache.
					userCache.addSubPageToCache(subPageKey, imageData);
				}
			}

			Image image = new Image();
			image.mData = imageData.mData;
			image.mLastKnownSubPage = imageData.mSubPageCount;
			image.mExtension = imageData.mExtension;
			image.mContentType = imageData.mContentType;

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("mData", image.mData, ", ");
				msg.append("mLastKnownSubPage", image.mLastKnownSubPage, ", ");
				msg.append("mExtension", image.mExtension, ", ");
				msg.append("mContentType", image.mContentType, "");
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
		catch (UnknownPageException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.SECTION_UNKNOWN
			);
		}
		catch (UnknownSubpageException e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTRepositoryErrors.SUBSECTION_UNKNOWN
			);
		}
		catch (BadParamException e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTCommonErrors.INVALID_ARGUMENT
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

	/**
	 *	Retrieves one or more pages of a document
	 *
	 *	@param anIfn identifies the document
	 *	@param anAllPages identifies that all the pages are to be retrieve
	 *	@param aStartPage identifies the page to start retrieving from
	 *	@param anEndPage identifies the page to stop retrieving
	 *
	 *	@return Structure containing the retrieved image and info about it
	 *
	 *	@throws TTException
	 *
	 */
	public Image getDocumentData(String anIfn, String aPages) throws TTException
	{
		String discardFilePath = "";

		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("anIfn", anIfn, ", ");
				msg.append("aPages", aPages, "");
				mLogger.info(msg.toString());
			}

			// Define formal arguments.
			Class[] formalArgs = new Class[]
			{
				String.class,
				String.class,
				String.class,
				org.omg.CORBA.StringHolder.class,
				org.omg.CORBA.StringHolder.class,
				org.omg.CORBA.IntHolder.class,
				org.omg.CORBA.IntHolder.class
			};

			// Define input/output variables.
			org.omg.CORBA.StringHolder contentType =
				new org.omg.CORBA.StringHolder();
			org.omg.CORBA.StringHolder filePath =
				new org.omg.CORBA.StringHolder();
			org.omg.CORBA.IntHolder fileLength =
				new org.omg.CORBA.IntHolder();
			org.omg.CORBA.IntHolder fileChunk =
				new org.omg.CORBA.IntHolder();

			// Define actual arguments.
			Object[] actualArgs = new Object[]
			{
				mRepository.getIdmTicket(),
				anIfn,
				aPages,
				contentType,
				filePath,
				fileLength,
				fileChunk
			};

			// Call remote method.
			mRepository.doRemoteMethod
			(
				"TTRETRIEVAL.DocReaderHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"getDocumentInfo",
				formalArgs,
				actualArgs
			);

			discardFilePath = filePath.value;
			Image image = new Image();
			image.mData = new byte[fileLength.value];
			image.mExtension = contentType.value;

			if (mLogger.isLoggable(Level.FINE))
			{
				StringBufferS msg = new StringBufferS();
				msg.append("filePath", filePath.value, ", ");
				msg.append("fileLength", fileLength.value, ", ");
				msg.append("fileChunk", fileChunk.value, "");
				mLogger.fine(msg.toString());
			}

			// Retrieving the file in chunks.
			for (int ii = 0; ii < fileLength.value; ii += fileChunk.value)
			{
				byte[] portionData = fetchChunk
				(
					filePath.value,
					fileChunk.value,
					ii
				);

				System.arraycopy
				(
					portionData,
					0,
					image.mData,
					ii,
					portionData.length
				);
			}

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
		catch (BadPagesException e)
		{
			mLogger.severe(e.mErrorDescription);
			throw new TTException
			(
				e.mErrorDescription,
				TTRepositoryErrors.SECTION_ILLEGAL
			);
		}
		catch (UnknownPageException e)
		{
			mLogger.severe(e.errorDescription);
			throw new TTException
			(
				e.errorDescription,
				TTRepositoryErrors.SECTION_UNKNOWN
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
		finally
		{
			if (discardFilePath.length() > 0)
			{
				discardFile(discardFilePath);
			}
		}
	}

	/**
	 *	Obtains corresponding content-type for given file extension.
	 *
	 *	@param aFileExtn identifies file extension to get content-type for
	 *
	 *	@return Content-type for given file extension
	 *
	 *	@throws TTException
	 *
	 */
	private String getMimeType(String aFileExtn) throws TTException
	{
		try
		{
			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(START);
				msg.append("aFileExtn", aFileExtn, "");
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
				aFileExtn
			};

			// Call remote method.
			String mimeType = (String)mRepository.doRemoteMethod
			(
				"TTRETRIEVAL.DocReaderHelper",
				POA_NAME.value,
				OBJECT_ID.value,
				null,
				"",
				"getMimeType",
				formalArgs,
				actualArgs
			);

			if (mLogger.isLoggable(Level.INFO))
			{
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("mimeType", mimeType, "");
				mLogger.info(msg.toString());
			}

			return mimeType;
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
