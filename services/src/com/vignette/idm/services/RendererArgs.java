////////////////////////////////////////////////////////////////////////////////
//	Title		:	RendererArgs.java
//
//	Description	:	Class which stores information required for on-the-fly
//					conversion.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.services;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.zip.CRC32;

import com.towertechnology.common.environment.EnvironmentClassServer;
import com.vignette.idm.common.IDMConfiguration;
import com.vignette.idm.common.StringBufferS;

import TTRETRIEVAL.Param;

class RendererArgs
{
	private static java.util.logging.Logger mLogger =
		java.util.logging.Logger.getLogger
		(
			"com.vignette.idm.services.RendererArgs"
		);

	private static IDMConfiguration mConfig = null;

	private String mRendererType = null;
	private int mSubPageNum = 1;
	private byte[] mData = null;
	private String mInParamsStr = "";
	private String[] mInParams = null;
	private String mFileName = null;
	private String mExtension = null;
	private int mSubPageCount = 0;

	static
	{
		mConfig = (IDMConfiguration)EnvironmentClassServer.getClass
		(
			IDMConfiguration.class
		);
	}

	/**
	 *	Creates an object of this class using the specified parameters.
	 *
	 *	@param aRendererType is name of file extension based renderer
	 *	@param aSubPageNum is number of subpage to render
	 *	@param aInParams is the list of parameters to use for rendering
	 *	@param aFileName is identifier used for logging
	 *	@param aExtension is the output file format
	 */
	public RendererArgs
	(
		String	aRendererType,
		int		aSubPageNum,
		Param[]	aInParams,
		String	aFileName,
		String	aExtension
	)
	{
		mRendererType	= aRendererType;
		mSubPageNum		= aSubPageNum;
		mFileName		= aFileName;
		mExtension		= aExtension;

		convInParams(aInParams);
	}

	/**
	 *	Converts the array of rendering parameter name/value pairs into
	 *	a single string and an array of strings.
	 *
	 *	@param aInParams is the array of rendering parameter name/value pairs
	 */
	private void convInParams(Param[] aInParams)
	{
		// Go through the rendering parameters array.
		String paramName = null;
		ArrayList params = new ArrayList();
		for (int i = 0; i < aInParams.length; i++)
		{
			// Convert parameter name to its equivalent name.
			paramName = mConfig.getConfigurationValue(aInParams[i].mParamName);
			if (paramName == null)
			{
				continue;
			}

			// Add the parameter to the string and to the list.  Note that
			// values of boolean parameters are translated from Y/N to 1/0.
			if (params.size() != 0)
			{
				mInParamsStr += ",";
			}
			String paramValue = aInParams[i].mParamValue;
			if (paramValue.equals("Y"))
			{
				paramValue = "1";
			}
			else if (paramValue.equals("N"))
			{
				paramValue = "0";
			}
			mInParamsStr += paramName + "," + paramValue;
			params.add(paramName);
			params.add(paramValue);
		}

		mInParams = new String[params.size()];
		if (params.size() != 0)
		{
			params.toArray(mInParams);
		}

		if (mLogger.isLoggable(Level.FINE))
		{
			StringBufferS msg = new StringBufferS
			(
				"Rendering parameters = " + mInParamsStr
			);
			mLogger.fine(msg.toString());
		}
	}

	/**
	 *	Obtains the renderer type.
	 *
	 *	@return Renderer type
	 *
	 */
	public String getRendererType()
	{
		return mRendererType;
	}

	/**
	 *	Obtains the number of the subpage to render.
	 *
	 *	@return Subpage number
	 *
	 */
	public int getSubPageNum()
	{
		return mSubPageNum;
	}

	/**
	 *	Sets the stream of bytes to be converted.
	 *
	 *	@param aData is the byte stream to be converted
	 *
	 */
	public void setData(byte[] aData)
	{
		mData = aData;
	}

	/**
	 *	Obtains the stream of bytes to be converted.
	 *
	 *	@return Byte stream to be converted
	 *
	 */
	public byte[] getData()
	{
		return mData;
	}

	/**
	 *	Obtains the string of rendering parameters.
	 *
	 *	@return String of rendering parameters
	 *
	 */
	public String getInParamsStr()
	{
		return mInParamsStr;
	}

	/**
	 *	Obtains the string array of rendering parameters.
	 *
	 *	@return Array of strings of rendering parameters
	 *
	 */
	public String[] getInParams()
	{
		return mInParams;
	}

	/**
	 *	Calculates the CRC-32 of the rendering parameters and generates a
	 *	string which contains the hexadecimal representation of the CRC-32.
	 *
	 *	@return CRC-32 of the rendering parameters as a hexadecimal string
	 *
	 */
	public String getInParamsCrcStr()
	{
		// Generate the CRC-32 of the rendering parameters.
		CRC32 crc32 = new CRC32();
		crc32.update(mInParamsStr.getBytes());

		// Convert the CRC-32 into a hexadecimal string.
		return Long.toHexString(crc32.getValue());
	}

	/**
	 *	Obtains the logging identifier.
	 *
	 *	@return Identifier used for logging
	 *
	 */
	public String getFileName()
	{
		return mFileName;
	}

	/**
	 *	Sets the output file format.
	 *
	 *	@param aExtension is output file format
	 *
	 */
	public void setExtension(String aExtension)
	{
		mExtension = aExtension;
	}

	/**
	 *	Obtains the output file format.
	 *
	 *	@return Output file format
	 *
	 */
	public String getExtension()
	{
		return mExtension;
	}

	/**
	 *	Sets the subpage count.
	 *
	 *	@param aSubPageCount is the number of subpages.
	 *
	 */
	public void setSubPageCount(int aSubPageCount)
	{
		mSubPageCount = aSubPageCount;
	}

	/**
	 *	Obtains the number of subpages.
	 *
	 *	@return Number of subpages.
	 *
	 */
	public int getSubPageCount()
	{
		return mSubPageCount;
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
