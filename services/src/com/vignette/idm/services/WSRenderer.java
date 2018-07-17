////////////////////////////////////////////////////////////////////////////////
//	Title		:	WSRenderer.java
//
//	Description	:	Class which performs on-the-fly rendering by invoking
//					methods from VRS.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.services;

import java.net.URL;
import java.util.List;
import java.util.logging.Level;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;

import com.towertechnology.common.environment.EnvironmentClassServer;
import com.towertechnology.common.errorhandling.TTCommonErrors;
import com.towertechnology.common.errorhandling.TTException;
import com.vignette.idm.common.IDMConfiguration;
import com.vignette.idm.common.StringBufferS;
import com.vignette.idm.render.agent.RenderAgentSoapService;
import com.vignette.idm.render.agent.RenderAgentSoapServiceSoap;
import com.vignette.idm.render.agent.RenderDocumentResponse.PpPagesData;
import com.vignette.idm.render.agent.RenderDocumentResponse.PpParams;
import com.vignette.idm.render.manager.RenderManagerService;
import com.vignette.idm.render.manager.RenderManagerServiceSoap;

class WSRenderer
{
	private static final String START = "Start: ";
	private static final String COMPLETE = "Complete: ";

	private static final String RENDERMANAGER_URL = "RenderManager.url";
	private static final String RENDERMANAGER_RETRY = "RenderManager.retry";
	private static final String RENDERMANAGER_SLEEP = "RenderManager.sleep";

	private static java.util.logging.Logger mLogger =
		java.util.logging.Logger.getLogger
		(
			"com.vignette.idm.services.WSRenderer"
		);

	private static IDMConfiguration mConfig = null;
	private static String mRenderManagerUrl = null;
	private static int mRetry = 0;
	private static int mSleep = 0;

	private RendererArgs mRendererArgs = null;
	private RenderManagerServiceSoap mManagerStub = null;
	private String mServerId = null;
	private String mRendererRef = null;
	private String mRendererProgId = null;

	static
	{
		// Obtain references to configuration object.
		mConfig = (IDMConfiguration)EnvironmentClassServer.getClass
		(
			IDMConfiguration.class
		);

		// Obtain required configuration information.
		mRenderManagerUrl = mConfig.getConfigurationValue(RENDERMANAGER_URL);
		try
		{
			mRetry = Integer.parseInt
			(
				mConfig.getConfigurationValue(RENDERMANAGER_RETRY)
			);
		}
		catch (NumberFormatException e)
		{
			mRetry = 5;
		}
		try
		{
			mSleep = Integer.parseInt
			(
				mConfig.getConfigurationValue(RENDERMANAGER_SLEEP)
			);
		}
		catch (NumberFormatException e)
		{
			mSleep = 1;
		}
	}

	/**
	 *	Create renderer object.
	 *
	 *	@param anArgs contains info required to perform on-the-fly rendering
	 *
	 */
	public WSRenderer(RendererArgs aRendererArgs) throws TTException
	{
		try
		{
			mLogger.info(START);

			mRendererArgs = aRendererArgs;

			if (mLogger.isLoggable(Level.FINE))
			{
				StringBufferS msg = new StringBufferS
				(
					"mRenderManagerUrl=" + mRenderManagerUrl
				);
				mLogger.fine(msg.toString());
			}

			URL serviceURL = new URL(mRenderManagerUrl);
			QName serviceName = new QName
			(
				"urn:RenderManagerService", 
				"RenderManagerService"
			);
			
			RenderManagerService managerLocator = new RenderManagerService(serviceURL, serviceName);
			
			mLogger.fine("RenderManagerService created properly");
			
			mManagerStub = (RenderManagerServiceSoap)managerLocator.getRenderManagerServiceSoap();

			mServerId = new String();
			mRendererRef = new String();
			mRendererProgId = new String();

			mLogger.info(COMPLETE);
		}
		catch (Exception e)
		{
			String msg = "Unable to obtain renderer manager stub (" +
				e.toString() + ")";
			mLogger.severe(msg);
			throw new TTException(msg, TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}
	}

	/**
	 *	Perform on-the-fly rendering.
	 *
	 *	@return Result of on-the-fly rendering
	 *
	 */
	public byte[] render() throws TTException
	{
		mLogger.info(START);

		// Obtain a renderer URL from the renderer manager.
		int retry;
		int retval;
		String resultMsg = new String();
		for (retry = 0; retry <= mRetry; retry++)
		{
			try
			{
				String msg = "Calling getRendererRef(): renderer Type=[" + mRendererArgs.getRendererType()
							+ "]";
				mLogger.info(msg);
				
				retval = mManagerStub.getRendererRef
				(
					mRendererArgs.getRendererType(),
					"genWSDL",
					mServerId,
					mRendererRef,
					mRendererProgId,
					resultMsg
				);
			}
			catch (Exception e)
			{
				String msg = "Unable to obtain renderer URL (" +
					e.getMessage() + ")";
				mLogger.severe(msg);
				throw new TTException(msg, TTCommonErrors.LOW_LEVEL_EXCEPTION);
			}

			// We have a valid renderer URL.
			if (retval == 0)
			{
				break;
			}

			// Sleep before attempting again.
			try
			{
				Thread.sleep(mSleep * 1000);
			}
			catch (InterruptedException e)
			{
			}
		}

		// Check if we have a valid renderer URL.
		if (retry > mRetry)
		{
			String msg = "Unable to obtain renderer URL after " + mRetry +
				" retries: " + resultMsg;
			mLogger.severe(msg);
			throw new TTException(msg, TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}

		// Perform the rendering.
		byte[] result = null;
		try
		{
			result = doRender();
		}
		catch (TTException e)
		{
			throw e;
		}
		finally
		{
			try
			{
				retval = mManagerStub.releaseRendererRef
				(
					mRendererArgs.getRendererType(),
					mServerId,
					resultMsg
				);
				
				if (retval != 0)
				{
					String msg = "Unable to release renderer URL: " +
						resultMsg;
					mLogger.severe(msg);
					throw new TTException
					(
						msg,
						TTCommonErrors.LOW_LEVEL_EXCEPTION
					);
				}
			}
			catch (TTException ttex)
			{
				throw ttex;
			}
			catch (Exception e)
			{
				String msg = 
					"Unable to release renderer URL due to exception (" +
					e.getMessage() + ")";
				mLogger.severe(msg);
				throw new TTException(msg, TTCommonErrors.LOW_LEVEL_EXCEPTION);
			}
		}

		mLogger.info(COMPLETE);

		// Return the resulting rendition.
		return result;
	}

	/**
	 *	Ask agent to perform rendering.
	 *
	 *	@result The resulting rendition
	 *
	 */
	private byte[] doRender() throws TTException
	{
		mLogger.info(START);

		// Create a stub to the agent.
		RenderAgentSoapServiceSoap agentStub = null;
		try
		{
			if (mLogger.isLoggable(Level.FINE))
			{
				StringBufferS msg = new StringBufferS
				(
					"mRendererRef=" + mRendererRef
				);
				mLogger.fine(msg.toString());
			}

			URL serviceURL = new URL(mRendererRef);
			QName serviceName = new QName
			(
				"urn:RenderAgentSoapService", 
				"RenderAgentSoapService"
			);
			
			RenderAgentSoapService agentLocator = new RenderAgentSoapService(serviceURL, serviceName);
			agentStub = (RenderAgentSoapServiceSoap)agentLocator.getRenderAgentSoapServiceSoap();

		}
		catch (Exception e)
		{
			String msg = "Unable to obtain renderer agent stub (" +
				e.toString() + ")";
			mLogger.severe(msg);
			throw new TTException(msg, TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}

		// Call the agent method.
		try
		{
			// Input parameters.
			Integer beginPage = new Integer(mRendererArgs.getSubPageNum());
			Integer endPage = new Integer(mRendererArgs.getSubPageNum());
			String imageType = new String(mRendererArgs.getExtension());						
			com.vignette.idm.render.agent.RenderDocument.PpParams inPpParams = null;						
			String[] paramArray = mRendererArgs.getInParams();
			
			if (paramArray.length > 0)
			{
				inPpParams = new com.vignette.idm.render.agent.RenderDocument.PpParams();
				List<String> inParams = inPpParams.getString();				
				for (int i = 0; i < paramArray.length; i++)
				{								
					inParams.add(paramArray[i]);
				}
			}	
			
			if (mLogger.isLoggable(Level.FINE))
			{
				StringBufferS msg = new StringBufferS
				(
					"mRendererProgId=[" + mRendererProgId + 
					"], fileName=[" + mRendererArgs.getFileName() + "], " +
					"beginPage=[" + beginPage + "], " +
					"endPage=[" + endPage+ "], " +
					"imageType=[" + imageType + "], " +
					"ppParams=[" + mRendererArgs.getInParamsStr() + "]"
				);
				mLogger.fine(msg.toString());
			}
			
			// Output parameters.
			int retval;
			PpParams outPpParams = new PpParams();
	        PpPagesData ppPagesData = new PpPagesData();			
			String errMsg = new String();

			// Stub method call.			
 			retval = agentStub.renderDocument
			(
				mRendererProgId,
				mRendererArgs.getData(),
				mRendererArgs.getFileName(),
				beginPage,
				endPage,
				imageType,
				inPpParams,
				outPpParams,
				ppPagesData,
				errMsg
			);			

			StringBufferS tmp = new StringBufferS
			( 
				"Returned from renderDocument(), imageType=[" + imageType
				+ "], retval=[" + retval + "]"
			);
			mLogger.fine(tmp.toString());

			if (retval != 0)
			{
				String msg = "Unable to perform rendering: " + errMsg;
				mLogger.severe(msg);
 				
				throw new TTException(msg, TTCommonErrors.LOW_LEVEL_EXCEPTION);
			}
			mRendererArgs.setSubPageCount(getSubPageCount(outPpParams.getString()));
			mRendererArgs.setSubPageCount(1);
			mRendererArgs.setExtension(imageType.toUpperCase());

			mLogger.info(COMPLETE);

			return ppPagesData.getBase64Binary().get(0);
		}
		catch (Exception e)
		{
			String msg = "Unable to perform rendering due to exception (" +
				e.toString() + ")";
			mLogger.severe(msg);
			
			StackTraceElement[] stack = e.getStackTrace();
			msg = "stacktrace=";
			for (int i = 0; i < stack.length; i++)
			{
				msg += stack[i].toString();
				msg += "\n";
			}
			mLogger.severe(msg);
			
			throw new TTException(msg, TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}
	}

	/**
	 *	Search the given list of output parameters for the subpage count.
	 *
	 *	@param aParams array of output parameters
	 *
	 *	@return Number of subpages
	 *
	 */
	private int getSubPageCount(List<String> aParams)
	{
		mLogger.info(START);

		int total = 0;
		int upperLimit = 0;
		for (int i = 0; i < aParams.size(); i += 2)
		{
			if (aParams.get(i).equalsIgnoreCase("total"))
			{
				try
				{
					total = Integer.parseInt(aParams.get(i + 1));
				}
				catch (NumberFormatException e)
				{
					total = 0;
				}
			}
			if (aParams.get(i).equalsIgnoreCase("upperlimit"))
			{
				try
				{
					upperLimit = Integer.parseInt(aParams.get(i + 1));
				}
				catch (NumberFormatException e)
				{
					upperLimit = 0;
				}
			}
		}

		mLogger.info(COMPLETE);

		return ((total > 0) ? total : upperLimit);
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////