package com.opentext.rest.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.activation.DataHandler;
import javax.activation.DataSource;

import com.towertechnology.common.errorhandling.TTException;
import com.vignette.idm.common.NameValue;
import com.vignette.idm.common.RenditionDetail;
import com.vignette.idm.services.DoctypeSecurity;
import com.vignette.idm.services.IdmRepImpl;
import com.vignette.idm.server.common.ExceptionMessagesFormatter;
import com.vignette.idm.server.common.Validator;
import com.vignette.idm.server.common.olf.FormatterException;

import com.vignette.idm.server.GeneralExceptionFaultDetail;
import com.vignette.idm.server.InvalidParameterException;
import com.vignette.idm.server.InvalidParameterExceptionFaultDetail;
import com.vignette.idm.server.PermissionException;
import com.vignette.idm.server.PermissionExceptionFaultDetail;
import com.vignette.idm.server.UnknownOptionNameException;
import com.vignette.idm.server.UnknownOptionNameExceptionFaultDetail;
import com.vignette.idm.server.UnknownOptionValueException;
import com.vignette.idm.server.UnknownOptionValueExceptionFaultDetail;

public class DocServiceUtils {
	

	private static final String COLORON				= "ColorOn";
	private static final String CONVTYPE			= "ConvType";
	private static final String FALSE				= "false";
	private static final String FONTSCALINGFACTOR	= "FontScalingFactor";
	private static final String FORCE				= "Force";
	private static final String FORCE8BITS			= "Force8Bits";
	private static final String FORMAT				= "Format";
	private static final String HEIGHT				= "Height";
	private static final String HWOVERWRITE			= "HeightWidthOverwrite";
	private static final String OPTIONS				= "options";
	private static final String PARAMID				= "ParamID";
	private static final String RENDITIONTYPE		= "renditionType";
	private static final String SERVICENAME			= "DocServices";
	private static final String TRUE				= "true";
	private static final String WIDTH				= "Width";
	private static final String ZOOMFACTOR			= "ZoomFactor";

	private static ExceptionMessagesFormatter mFormatter;
	private static String mFormat;
	private static String mParamID;
	private static int mHeight;
	private static int mWidth;
	private static int mZoomFactor;
	private static String mForce8Bits;
	private static int mConvType;
	private static String mColorOn;
	private static int mFontScalingFactor;
	private static String mHeightWidthOverwrite;
	
	public static String format(GeneralExceptionFaultDetail e)
	{
		try
		{
			return mFormatter.format(e);
		}
		catch (FormatterException fe)
		{
			return e.getMessage();
		}
	}

	public static void checkPermission
	(
		String		aMethodName,
		IdmRepImpl	aRepo,
		String		aAppName,
		String		aDocID,
		int			aAction
	) throws
		PermissionException,
		TTException
	{
		DoctypeSecurity docTypeSec = new DoctypeSecurity(aRepo);
		boolean perm = docTypeSec.validateIfnDoctypeAccess
		(
			aAction,
			aAppName,
			aDocID
		);
		if (perm == false)
		{
			PermissionExceptionFaultDetail faultDetail = new PermissionExceptionFaultDetail();
			faultDetail.setAppName(aAppName);
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setUserName(aRepo.getUserId());
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(SERVICENAME);					
			throw new PermissionException(null, faultDetail);
		}
	}

	public static ArrayList<com.vignette.idm.server.RenditionDetail> transformRenditionDetails
	(
		RenditionDetail[]	rends
	)
	{
		ArrayList<com.vignette.idm.server.RenditionDetail> details =
			new ArrayList<com.vignette.idm.server.RenditionDetail>(rends.length);
		for (int i = 0; i < rends.length; i++)
		{
			com.vignette.idm.server.RenditionDetail rendDetail = new com.vignette.idm.server.RenditionDetail();
			rendDetail.setMimeType(rends[i].getMIMEType());
			rendDetail.setRenditionType(rends[i].getRenditionType());
			rendDetail.setZoomFactor(rends[i].getZoomFactor());
			
			details.add(rendDetail);
		}
		return details;
	}

	public static String validateRenditionType
	(
		String	aMethodName,
		String	aRenditionType
	) throws InvalidParameterException
	{
		// Perform general string validation.
		String renditionType = Validator.validateString
		(
			SERVICENAME,
			aMethodName,
			RENDITIONTYPE,
			aRenditionType,
			true,
			true
		);

		// Perform specific rendition type validation.
		mFormat = null;
		mParamID = null;
		if (renditionType != null && renditionType.length() != 0)
		{
			String[] formatParamID = renditionType.split("-", 2);
			if (formatParamID[0].length() > 0)
			{
				mFormat = formatParamID[0];
			}
			if (formatParamID.length > 1 && formatParamID[1].length() > 0)
			{
				try
				{
					Integer.parseInt(formatParamID[1]);
					mParamID = formatParamID[1];
				}
				catch (NumberFormatException e)
				{
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(RENDITIONTYPE);
					faultDetail.setParameterValue(aRenditionType);
					faultDetail.setWsMethodName(aMethodName);
					faultDetail.setWsName(SERVICENAME);					
					throw new InvalidParameterException(null, faultDetail);
				}
			}
		}
		return renditionType;
	}

	public static void validateGetRenditionOptions
	(
		String								aMethodName,
		List<com.vignette.idm.server.NameValue>	aOptions
	) throws
		InvalidParameterException,
		UnknownOptionNameException,
		UnknownOptionValueException
	{
		// Perform general name/value pair validation.
		Validator.validateNameValueList
		(
			SERVICENAME,
			aMethodName,
			OPTIONS,
			aOptions,
			true,
			true,
			false,
			false
		);

		// Perform specific option validation.
		mHeight = -1;
		mWidth = -1;
		mZoomFactor = -1;
		mForce8Bits = null;
		mConvType = -1;
		mColorOn = null;
		mFontScalingFactor = -1;
		mHeightWidthOverwrite = null;
		if (aOptions != null)
		{
			ListIterator<com.vignette.idm.server.NameValue> itor = aOptions.listIterator();
			while (itor.hasNext())
			{
				com.vignette.idm.server.NameValue opt = itor.next();
				String	optionName = opt.getName();
				String	optionValue = opt.getValue();

				// Check for valid values of Height option.
				if (optionName.equals(HEIGHT))
				{
					try
					{
						mHeight = Integer.parseInt(optionValue);
					}
					catch (NumberFormatException e)
					{
						InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
						faultDetail.setCallStack(null);
						faultDetail.setMessage(e.getMessage());
						faultDetail.setParameterName(optionName);
						faultDetail.setParameterValue(optionValue);
						faultDetail.setWsMethodName(aMethodName);
						faultDetail.setWsName(SERVICENAME);					
						throw new InvalidParameterException(null, faultDetail);
					}
					Validator.validateLongRangeParam
					(
						SERVICENAME,
						aMethodName,
						optionName,
						mHeight,
						0,
						32767
					);
				}
				// Check for valid values of Width option.
				else if (optionName.equals(WIDTH))
				{
					try
					{
						mWidth = Integer.parseInt(optionValue);
					}
					catch (NumberFormatException e)
					{
						InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
						faultDetail.setCallStack(null);
						faultDetail.setMessage(e.getMessage());
						faultDetail.setParameterName(optionName);
						faultDetail.setParameterValue(optionValue);
						faultDetail.setWsMethodName(aMethodName);
						faultDetail.setWsName(SERVICENAME);					
						throw new InvalidParameterException(null, faultDetail);
					}
					Validator.validateLongRangeParam
					(
						SERVICENAME,
						aMethodName,
						optionName,
						mWidth,
						0,
						32767
					);
				}
				// Check for valid values of ZoomFactor option.
				else if (optionName.equals(ZOOMFACTOR))
				{
					try
					{
						mZoomFactor = Integer.parseInt(optionValue);
					}
					catch (NumberFormatException e)
					{
						InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
						faultDetail.setCallStack(null);
						faultDetail.setMessage(e.getMessage());
						faultDetail.setParameterName(optionName);
						faultDetail.setParameterValue(optionValue);
						faultDetail.setWsMethodName(aMethodName);
						faultDetail.setWsName(SERVICENAME);					
						throw new InvalidParameterException(null, faultDetail);
					}
					Validator.validateLongRangeParam
					(
						SERVICENAME,
						aMethodName,
						optionName,
						mZoomFactor,
						0,
						9999
					);
				}
				// Check for valid values of Force8Bits option.
				else if (optionName.equals(FORCE8BITS))
				{
					if
					(
						optionValue.equalsIgnoreCase("Y") == false
						&&
						optionValue.equalsIgnoreCase("N") == false
					)
					{
						UnknownOptionValueExceptionFaultDetail faultDetail = new UnknownOptionValueExceptionFaultDetail();
						faultDetail.setCallStack(null);
						faultDetail.setMessage(null);
						faultDetail.setOptionName(optionName);
						faultDetail.setOptionValue(optionValue);
						faultDetail.setWsMethodName(aMethodName);
						faultDetail.setWsName(SERVICENAME);						
						throw new UnknownOptionValueException(null, faultDetail);
					}
					mForce8Bits = optionValue.toUpperCase();
				}
				// Check for valid values of ConvType option.
				else if (optionName.equals(CONVTYPE))
				{
					try
					{
						mConvType = Integer.parseInt(optionValue);
					}
					catch (NumberFormatException e)
					{
						InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
						faultDetail.setCallStack(null);
						faultDetail.setMessage(e.getMessage());
						faultDetail.setParameterName(optionName);
						faultDetail.setParameterValue(optionValue);
						faultDetail.setWsMethodName(aMethodName);
						faultDetail.setWsName(SERVICENAME);					
						throw new InvalidParameterException(null, faultDetail);
					}
					Validator.validateLongRangeParam
					(
						SERVICENAME,
						aMethodName,
						optionName,
						mConvType,
						0,
						2
					);
				}
				// Check for valid values of ColorOn option.
				else if (optionName.equals(COLORON))
				{
					if
					(
						optionValue.equalsIgnoreCase("Y") == false
						&&
						optionValue.equalsIgnoreCase("N") == false
					)
					{
						UnknownOptionValueExceptionFaultDetail faultDetail = new UnknownOptionValueExceptionFaultDetail();
						faultDetail.setCallStack(null);
						faultDetail.setMessage(null);
						faultDetail.setOptionName(optionName);
						faultDetail.setOptionValue(optionValue);
						faultDetail.setWsMethodName(aMethodName);
						faultDetail.setWsName(SERVICENAME);						
						throw new UnknownOptionValueException(null, faultDetail);
					}
					mColorOn = optionValue.toUpperCase();
				}
				// Check for valid values of FontScalingFactor option.
				else if (optionName.equals(FONTSCALINGFACTOR))
				{
					try
					{
						mFontScalingFactor = Integer.parseInt(optionValue);
					}
					catch (NumberFormatException e)
					{
						InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
						faultDetail.setCallStack(null);
						faultDetail.setMessage(e.getMessage());
						faultDetail.setParameterName(optionName);
						faultDetail.setParameterValue(optionValue);
						faultDetail.setWsMethodName(aMethodName);
						faultDetail.setWsName(SERVICENAME);					
						throw new InvalidParameterException(null, faultDetail);
					}
					Validator.validateLongRangeParam
					(
						SERVICENAME,
						aMethodName,
						optionName,
						mFontScalingFactor,
						0,
						999
					);
				}
				// Check for valid values of HeightWidthOverwrite option.
				else if (optionName.equals(HWOVERWRITE))
				{
					if
					(
						optionValue.equalsIgnoreCase("Y") == false
						&&
						optionValue.equalsIgnoreCase("N") == false
					)
					{
						UnknownOptionValueExceptionFaultDetail faultDetail = new UnknownOptionValueExceptionFaultDetail();
						faultDetail.setCallStack(null);
						faultDetail.setMessage(null);
						faultDetail.setOptionName(optionName);
						faultDetail.setOptionValue(optionValue);
						faultDetail.setWsMethodName(aMethodName);
						faultDetail.setWsName(SERVICENAME);						
						throw new UnknownOptionValueException(null, faultDetail);
					}
					mHeightWidthOverwrite = optionValue.toUpperCase();
				}
				// Not a valid option.
				else
				{
					UnknownOptionNameExceptionFaultDetail faultDetail = new UnknownOptionNameExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setOptionName(optionName);
					faultDetail.setWsMethodName(aMethodName);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownOptionNameException(null, faultDetail);
				}
			}
		}

		// Height, width and zoom factor cannot all be zero.
		if (mHeight == 0 && mWidth == 0 && mZoomFactor == 0)
		{
			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setParameterName(OPTIONS + "[" + HEIGHT + "|" + WIDTH + "|" + ZOOMFACTOR + "]");
			faultDetail.setParameterValue("[" + mHeight + "|" + mWidth + "|" + mZoomFactor + "]");
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(SERVICENAME);					
			throw new InvalidParameterException(null, faultDetail);
		}

		// If height is specified, width must also be specified.
		// And vice-versa.
		if ((mHeight > 0 && mWidth <= 0) || (mWidth > 0 && mHeight <= 0))
		{
			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setParameterName(OPTIONS + "[" + HEIGHT + "|" + WIDTH + "]");
			faultDetail.setParameterValue("[" + mHeight + "|" + mWidth + "]");
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(SERVICENAME);					
			throw new InvalidParameterException(null, faultDetail);
		}

		// If zoom factor is specified, height and width must not be
		// specified.  And vice-versa.
		if (mZoomFactor > 0 && (mHeight > 0 || mWidth > 0))
		{
			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setParameterName(OPTIONS + "[" + HEIGHT + "|" + WIDTH + "|" + ZOOMFACTOR + "]");
			faultDetail.setParameterValue("[" + mHeight + "|" + mWidth + "|" + mZoomFactor + "]");
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(SERVICENAME);					
			throw new InvalidParameterException(null, faultDetail);
		}

		// If zoom factor is specified, height and width must be zero.
		if (mZoomFactor > 0)
		{
			mHeight = 0;
			mWidth = 0;
		}

		// If height and width are specified, zoom factor must be zero.
		if (mHeight > 0 && mWidth > 0)
		{
			mZoomFactor = 0;
		}
	}

	public static NameValue[] transformOptions()
	{
		ArrayList<NameValue> optionsList = new ArrayList<NameValue>();
		NameValue option;
		if (mFormat != null)
		{
			option = new NameValue(FORMAT, mFormat);
			optionsList.add(option);
		}
		if (mParamID != null)
		{
			option = new NameValue(PARAMID.toUpperCase(), mParamID);
			optionsList.add(option);
		}
		if (mHeight >= 0)
		{
			option = new NameValue
			(
				HEIGHT.toUpperCase(),
				Integer.toString(mHeight)
			);
			optionsList.add(option);
		}
		if (mWidth >= 0)
		{
			option = new NameValue
			(
				WIDTH.toUpperCase(),
				Integer.toString(mWidth)
			);
			optionsList.add(option);
		}
		if (mZoomFactor >= 0)
		{
			option = new NameValue
			(
				ZOOMFACTOR.toUpperCase(),
				Integer.toString(mZoomFactor)
			);
			optionsList.add(option);
		}
		if (mForce8Bits != null)
		{
			option = new NameValue(FORCE8BITS.toUpperCase(), mForce8Bits);
			optionsList.add(option);
		}
		if (mConvType >= 0)
		{
			option = new NameValue
			(
				CONVTYPE.toUpperCase(),
				Integer.toString(mConvType)
			);
			optionsList.add(option);
		}
		if (mColorOn != null)
		{
			option = new NameValue(COLORON.toUpperCase(), mColorOn);
			optionsList.add(option);
		}
		if (mFontScalingFactor >= 0)
		{
			option = new NameValue
			(
				FONTSCALINGFACTOR.toUpperCase(),
				Integer.toString(mFontScalingFactor)
			);
			optionsList.add(option);
		}
		if (mHeightWidthOverwrite != null)
		{
			option = new NameValue
			(
				HWOVERWRITE.toUpperCase(),
				mHeightWidthOverwrite
			);
			optionsList.add(option);
		}
		NameValue[] options = new NameValue[optionsList.size()];
		optionsList.toArray(options);
		return options;
	}

	public static void validateSetAnnotationOptions
	(
		String								aMethodName,
		List<com.vignette.idm.server.NameValue>	aOptions
	) throws
		InvalidParameterException,
		UnknownOptionNameException,
		UnknownOptionValueException
	{
		// Perform general name/value pair validation.
		Validator.validateNameValueList
		(
			SERVICENAME,
			aMethodName,
			OPTIONS,
			aOptions,
			true,
			true,
			false,
			false
		);

		if (aOptions != null)
		{
			ListIterator<com.vignette.idm.server.NameValue> itor = aOptions.listIterator();	
			while (itor.hasNext())
			{
				com.vignette.idm.server.NameValue opt = itor.next();
				String	optionName = opt.getName();
				String	optionValue = opt.getValue();

				// Check for valid values of Height option.
				if (optionName.equals(FORCE))
				{
					if
					(
						optionValue.equalsIgnoreCase(TRUE) == false
						&&
						optionValue.equalsIgnoreCase(FALSE) == false
					)
					{
						UnknownOptionValueExceptionFaultDetail faultDetail = new UnknownOptionValueExceptionFaultDetail();
						faultDetail.setCallStack(null);
						faultDetail.setMessage(null);
						faultDetail.setOptionName(optionName);
						faultDetail.setOptionValue(optionValue);
						faultDetail.setWsMethodName(aMethodName);
						faultDetail.setWsName(SERVICENAME);						
						throw new UnknownOptionValueException(null, faultDetail);
					}
					Boolean.valueOf(optionValue).booleanValue();
				}
				// Not a valid option.
				else
				{
					UnknownOptionNameExceptionFaultDetail faultDetail = new UnknownOptionNameExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setOptionName(optionName);
					faultDetail.setWsMethodName(aMethodName);
					faultDetail.setWsName(SERVICENAME);
					throw new UnknownOptionNameException(null, faultDetail);
				}
			}
		}
	}
	public static byte[] getByteArrayFromDataHandler(DataHandler dHandler) throws IOException {
		InputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		byte[] byteData = null;

		try {
			DataSource dataSource = (DataSource) dHandler.getDataSource();
			inputStream = dataSource.getInputStream();
			// put 8*1024 as a configuration variable
			outputStream = new ByteArrayOutputStream(8 * 1024);
			byte dsData[] = new byte[8 * 1024];
			int bytes_read;

			while ((bytes_read = inputStream.read(dsData)) > 0) {
				outputStream.write(dsData, 0, bytes_read);
			}

			byteData = outputStream.toByteArray();
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}

			if (outputStream != null) {
				outputStream.close();
			}
		}

		return byteData;
	}

}
