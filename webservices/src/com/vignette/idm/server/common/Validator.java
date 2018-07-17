////////////////////////////////////////////////////////////////////////////////
//	Title		:	Validator.java
//
//	Description	:	Class containing various validation functions for IWSTU.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.server.common;

import java.util.List;
import java.util.ListIterator;

import com.towertechnology.common.errorhandling.TTException;
import com.vignette.idm.server.InvalidParameterException;
import com.vignette.idm.server.InvalidParameterExceptionFaultDetail;
import com.vignette.idm.server.LicenseException;
import com.vignette.idm.server.LicenseExceptionFaultDetail;
import com.vignette.idm.server.NameValue;
import com.vignette.idm.server.UnknownAppException;
import com.vignette.idm.server.UnknownAppExceptionFaultDetail;
import com.vignette.idm.server.UnknownOptionValueException;
import com.vignette.idm.server.UnknownOptionValueExceptionFaultDetail;
import com.vignette.idm.services.RecordCollection;

public class Validator
{
	private static LicenceFactory mLF;

	static
	{
		mLF = LicenceFactory.getInstance();
	}

	public static String validateString
	(
		String	aServiceName,
		String	aMethodName,
		String	aStringName,
		String	aStringValue,
		boolean	aNillable,
		boolean	aBlankable
	) throws InvalidParameterException
	{
		// Check for null if null is not allowed.
		if (aNillable == false && aStringValue == null)
		{
			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setParameterName(aStringName);
			faultDetail.setParameterValue(aStringValue);
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(aServiceName);
			
			throw new InvalidParameterException(null, faultDetail);
		}
		if (aStringValue != null)
		{
			// Check for blank if blank is not allowed.
			aStringValue = aStringValue.trim();
			if (aBlankable == false && aStringValue.length() == 0)
			{
				InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
				faultDetail.setCallStack(null);
				faultDetail.setMessage(null);
				faultDetail.setParameterName(aStringName);
				faultDetail.setParameterValue(aStringValue);
				faultDetail.setWsMethodName(aMethodName);
				faultDetail.setWsName(aServiceName);
				throw new InvalidParameterException(null, faultDetail);
			}
		}
		return aStringValue;
	}

	public static void validateStringArray
	(
		String		aServiceName,
		String		aMethodName,
		String		aArrayName,
		String[]	aArray,
		boolean		aNillable,
		boolean		aEmptiable,
		boolean		aNillableElement,
		boolean		aBlankableElement
	) throws InvalidParameterException
	{
		// Check for null if null is not allowed.
		if (aNillable == false && aArray == null)
		{
			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setParameterName(aArrayName);
			faultDetail.setParameterValue(null);
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(aServiceName);
			
			throw new InvalidParameterException(null, faultDetail);
		}

		// Check for empty if empty is not allowed.
		if (aEmptiable == false && aArray != null && aArray.length == 0)
		{
			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setParameterName(aArrayName);
			faultDetail.setParameterValue("empty");
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(aServiceName);
			
			throw new InvalidParameterException(null, faultDetail);
		}

		if (aArray != null && aArray.length != 0)
		{
			// Check each element of the array.
			for (int i = 0; i < aArray.length; i++)
			{
				aArray[i] = validateString
				(
					aServiceName,
					aMethodName,
					aArrayName + "[" + i + "]",
					aArray[i],
					aNillableElement,
					aBlankableElement
				);
			}
		}
	}

	public static void validateStringList
	(
		String		aServiceName,
		String		aMethodName,
		String		aArrayName,
		List<String>	aList,
		boolean		aNillable,
		boolean		aEmptiable,
		boolean		aNillableElement,
		boolean		aBlankableElement
	) throws InvalidParameterException
	{
		// Check for null if null is not allowed.
		if (aNillable == false && aList == null)
		{
			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setParameterName(aArrayName);
			faultDetail.setParameterValue(null);
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(aServiceName);
			
			throw new InvalidParameterException(null, faultDetail);
		}

		// Check for empty if empty is not allowed.
		if (aEmptiable == false && aList != null && aList.size() == 0)
		{
			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setParameterName(aArrayName);
			faultDetail.setParameterValue("empty");
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(aServiceName);
			
			throw new InvalidParameterException(null, faultDetail);
		}

		if (aList != null && aList.size() != 0)
		{
			// Check each element of the array.
			for (int i = 0; i < aList.size(); i++)
			{
				aList.set(i, validateString
							(
								aServiceName,
								aMethodName,
								aArrayName + "[" + i + "]",
								aList.get(i),
								aNillableElement,
								aBlankableElement
							)
					);
			}
		}
	}

	public static void validateNameValueArray
	(
		String		aServiceName,
		String		aMethodName,
		String		aArrayName,
		NameValue[]	aArray,
		boolean		aNillable,
		boolean		aEmptiable,
		boolean		aNillableValue,
		boolean		aBlankableValue
	) throws InvalidParameterException
	{
		// Check for null if null is not allowed.
		if (aNillable == false && aArray == null)
		{
			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setParameterName(aArrayName);
			faultDetail.setParameterValue(null);
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(aServiceName);
			
			throw new InvalidParameterException(null, faultDetail);
		}

		// Check for empty if empty is not allowed.
		if (aEmptiable == false && aArray != null && aArray.length == 0)
		{
			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setParameterName(aArrayName);
			faultDetail.setParameterValue("empty");
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(aServiceName);
			
			throw new InvalidParameterException(null, faultDetail);
		}

		if (aArray != null && aArray.length != 0)
		{
			// Check each element of the array.
			for (int i = 0; i < aArray.length; i++)
			{
				// Check for null element.
				if (aArray[i] == null)
				{
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(aArrayName + "[" + i + "]");
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(aMethodName);
					faultDetail.setWsName(aServiceName);
					
					throw new InvalidParameterException(null, faultDetail);
				}

				// Check the name of the element.
				aArray[i].setName
				(
					validateString
					(
						aServiceName,
						aMethodName,
						aArrayName + "[" + i + "].name",
						aArray[i].getName(),
						false,
						false
					)
				);

				// Check the value of the element.
				aArray[i].setValue
				(
					validateString
					(
						aServiceName,
						aMethodName,
						aArrayName + "[" + i + "].value",
						aArray[i].getValue(),
						aNillableValue,
						aBlankableValue
					)
				);
			}
		}
	}

	public static void validateNameValueList
	(
		String		aServiceName,
		String		aMethodName,
		String		aArrayName,
		List<NameValue>	aList,
		boolean		aNillable,
		boolean		aEmptiable,
		boolean		aNillableValue,
		boolean		aBlankableValue
	) throws InvalidParameterException
	{
		// Check for null if null is not allowed.
		if (aNillable == false && aList == null)
		{
			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setParameterName(aArrayName);
			faultDetail.setParameterValue(null);
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(aServiceName);			
			throw new InvalidParameterException(null, faultDetail);
		}

		// Check for empty if empty is not allowed.
		if (aEmptiable == false && aList != null && aList.size() == 0)
		{
			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setParameterName(aArrayName);
			faultDetail.setParameterValue("empty");
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(aServiceName);			
			throw new InvalidParameterException(null, faultDetail);
		}
		
		if (aList != null)
		{
			ListIterator<NameValue> itr = aList.listIterator();
			while (itr.hasNext())
			{
				NameValue nv = itr.next();

				// Check for null element.
				if (nv == null)
				{
					InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
					faultDetail.setCallStack(null);
					faultDetail.setMessage(null);
					faultDetail.setParameterName(aArrayName + "[" + itr.previousIndex() + "]");
					faultDetail.setParameterValue(null);
					faultDetail.setWsMethodName(aMethodName);
					faultDetail.setWsName(aServiceName);					
					throw new InvalidParameterException(null, faultDetail);
				}

				// Check the name of the element.
				nv.setName
				(
					validateString
					(
						aServiceName,
						aMethodName,
						aArrayName + "[" + itr.previousIndex() + "].name",
						nv.getName(),
						false,
						false
					)
				);

				// Check the value of the element.
				nv.setValue
				(
					validateString
					(
						aServiceName,
						aMethodName,
						aArrayName + "[" + itr.previousIndex() + "].value",
						nv.getValue(),
						aNillableValue,
						aBlankableValue
					)
				);
			}
		}
	}

	public static String validateLicense
	(
		String	aServiceName,
		String	aMethodName,
		String	aLicenseName,
		String	aLicense
	) throws
		InvalidParameterException,
		LicenseException
	{
		// Check the license string.
		aLicense = validateString
		(
			aServiceName,
			aMethodName,
			aLicenseName,
			aLicense,
			false,
			false
		);

		// Check if the license exists in our table.
		if (mLF.getTicket(aLicense) == null)
		{
			LicenseExceptionFaultDetail faultDetail = new LicenseExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(aServiceName);
			
			throw new LicenseException(null, faultDetail);
		}

		return aLicense;
	}

	public static void validateLongLimit
	(
		String	aServiceName,
		String	aMethodName,
		String	aLongName,
		long	aLong,
		long	aLowerLimit
	) throws InvalidParameterException
	{
		if (aLong < aLowerLimit)
		{
			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setParameterName(aLongName);
			faultDetail.setParameterValue(Long.toString(aLong));
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(aServiceName);
			
			throw new InvalidParameterException(null, faultDetail);
		}
	}

	public static void validateLongRangeParam
	(
		String	aServiceName,
		String	aMethodName,
		String	aLongName,
		long	aLong,
		long	aLowerLimit,
		long	aUpperLimit
	) throws InvalidParameterException
	{
		if (aLong < aLowerLimit || aLong > aUpperLimit)
		{
			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setParameterName(aLongName);
			faultDetail.setParameterValue(Long.toString(aLong));
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(aServiceName);
			
			throw new InvalidParameterException(null, faultDetail);
		}
	}

	public static void validateLongRangeOption
	(
		String	aServiceName,
		String	aMethodName,
		String	aLongName,
		long	aLong,
		long	aLowerLimit,
		long	aUpperLimit
	) throws UnknownOptionValueException
	{
		if (aLong < aLowerLimit || aLong > aUpperLimit)
		{
			UnknownOptionValueExceptionFaultDetail faultDetail = new UnknownOptionValueExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setOptionName(aLongName);
			faultDetail.setOptionValue(Long.toString(aLong));
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(aServiceName);
			
			throw new UnknownOptionValueException(null, faultDetail);
		}
	}

	public static void validateByteArray
	(
		String	aServiceName,
		String	aMethodName,
		String	aArrayName,
		byte[]	aArray,
		boolean	aNillable,
		boolean	aEmptiable
	) throws InvalidParameterException
	{
		// Check for null if null is not allowed.
		if (aNillable == false && aArray == null)
		{
			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setParameterName(aArrayName);
			faultDetail.setParameterValue(null);
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(aServiceName);
			
			throw new InvalidParameterException(null, faultDetail);
		}

		// Check for empty if empty is not allowed.
		if (aEmptiable == false && aArray != null && aArray.length == 0)
		{
			InvalidParameterExceptionFaultDetail faultDetail = new InvalidParameterExceptionFaultDetail();
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setParameterName(aArrayName);
			faultDetail.setParameterValue("empty");
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(aServiceName);
			
			throw new InvalidParameterException(null, faultDetail);
		}
	}

	public static void checkApplication
	(
		String				aServiceName,
		String				aMethodName,
		RecordCollection	aCollection,
		String				aAppName
	) throws
		UnknownAppException,
		TTException
	{
		boolean isApp = aCollection.isApplication(aAppName);
		if (isApp == false)
		{
			UnknownAppExceptionFaultDetail faultDetail = new UnknownAppExceptionFaultDetail();
			faultDetail.setAppName(aAppName);
			faultDetail.setCallStack(null);
			faultDetail.setMessage(null);
			faultDetail.setWsMethodName(aMethodName);
			faultDetail.setWsName(aServiceName);
			
			throw new UnknownAppException(null, faultDetail);
		}
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
