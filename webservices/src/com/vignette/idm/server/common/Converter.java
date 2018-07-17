////////////////////////////////////////////////////////////////////////////////
//	Title		:	Converter.java
//
//	Description	:	Class which performs various data conversions.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.server.common;

import java.util.List;

import com.towertechnology.common.environment.EnvironmentClassServer;
import com.vignette.idm.common.IDMConfiguration;
import com.vignette.idm.server.StackTrace;

public class Converter
{
	private static final String CALLSTACK_DEPTH = "CallStack.Depth";
	private static final int DEFAULT_CALLSTACK_DEPTH = 0;

	private static IDMConfiguration mConfig = null;
	private static int mCallStackDepth;

	static
	{
		mConfig = (IDMConfiguration)EnvironmentClassServer.getClass
		(
			IDMConfiguration.class
		);
		try
		{
			mCallStackDepth = Integer.parseInt
			(
				mConfig.getConfigurationValue(CALLSTACK_DEPTH)
			);
		}
		catch (NumberFormatException e)
		{
			mCallStackDepth = DEFAULT_CALLSTACK_DEPTH;
		}
	}

	/**
	 *	Converts a string to its corresponding integer with a default value
	 *	in case the conversion fails, i.e. the string is not numeric.
	 */
	public static int parseInt(String aErrorNum, int aDefault)
	{
		try
		{
			return Integer.parseInt(aErrorNum);
		}
		catch (NumberFormatException e)
		{
			return aDefault;
		}
	}

	/**
	 *	Converts a string to its corresponding long integer with a default value
	 *	in case the conversion fails, i.e. the string is not numeric.
	 */
	public static long parseLong(String aErrorNum, long aDefault)
	{
		try
		{
			return Long.parseLong(aErrorNum);
		}
		catch (NumberFormatException e)
		{
			return aDefault;
		}
	}

	/**
	 *	Converts the call stack in an exception into an array of strings.
	 */
	public static StackTrace getCallStack(Exception aException)
	{
		StackTrace stackTrace = new StackTrace();
		List<String> traceInfo = stackTrace.getTraceInfo();		
		StackTraceElement[] trace = aException.getStackTrace();
		int depth = mCallStackDepth;
		
		for (int i = 0; i < trace.length && depth > 0; i++, depth--)
		{
			traceInfo.add(trace[i].toString());
		}
		return stackTrace;
	}

	/**
	 *	Converts an integer which must be treated as an unsigned integer
	 *	into a long.
	 */
	public static long convertUnsignedInt(int aNumber)
	{
		// Expression below is equivalent to "return 0xFFFFFFFFL & aNumber".
		return (((long)Integer.MAX_VALUE) * 2 + 1) & aNumber;
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
