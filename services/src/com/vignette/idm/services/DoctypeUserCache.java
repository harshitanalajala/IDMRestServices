////////////////////////////////////////////////////////////////////////////////
//	Title		:	DoctypeUserCache.java
//
//	Description	:	Class which keeps track of document type cache for a
//					specific ticket.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.services;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;

import com.towertechnology.common.environment.EnvironmentClassServer;
import com.vignette.idm.common.IDMConfiguration;
import com.vignette.idm.common.StringBufferS;

class DoctypeUserCache
{
	private static java.util.logging.Logger mLogger =
		java.util.logging.Logger.getLogger
		(
			"com.vignette.idm.services.DoctypeUserCache"
		);

	// This is in seconds.
	private static final int EXPIRED_PERIOD = 300;

	private String mTicket;

	private HashMap mBooleanMap;
	private HashMap mStringMap;
	private HashMap mIntegerMap;

	private Calendar mTime;

	private static IDMConfiguration mConfig = null;
	private static int mCleanupInterval = 0;

	static
	{
		mConfig = (IDMConfiguration)EnvironmentClassServer.getClass
		(
			IDMConfiguration.class
		);

		String cleanupInterval = mConfig.getConfigurationValue
		(
			"Doctype.UserCleanupInterval"
		);

		if (cleanupInterval == null)
		{
			mCleanupInterval = EXPIRED_PERIOD;
		}
		else
		{
			try
			{
				mCleanupInterval = Integer.parseInt(cleanupInterval);
			}
			catch (NumberFormatException e)
			{
				mCleanupInterval = EXPIRED_PERIOD;
			}
		}

		if (mLogger.isLoggable(Level.FINE))
		{
			StringBufferS msg = new StringBufferS
			(
				"Cleanup interval=" +mCleanupInterval + " seconds"
			);
			mLogger.fine(msg.toString());
		}
	}

	/**
	 * Creates an instance of this class.
	 */
	public DoctypeUserCache(String ticket)
	{
		mTime = Calendar.getInstance();

		mTicket = ticket;

		mBooleanMap = new HashMap(10);
		mStringMap = new HashMap(10);
		mIntegerMap = new HashMap(10);
	}

	/**
	 * Get the user ticket.
	 *
	 * @return	a string contains the user ticket.
	 */
	synchronized public String getTicket()
	{
		return new String(mTicket);
	}

	/**
	 * Get a boolean variable value stored.
	 *
	 * @param	key a unique indentifier.
	 *
	 * @return	A boolean value;
	 */
	synchronized public Boolean getBoolean(String key)
	{
		return (Boolean)mBooleanMap.get(key);
	}

	/**
	 * Set a boolean variable value to cache.
	 *
	 * @param	key a unique indentifier.
	 * @param	val boolean value.
	 */
	synchronized public void setBoolean(String key, boolean val)
	{
		mBooleanMap.put(key, new Boolean(val));
	}

	/**
	 * Get a string variable value stored.
	 *
	 * @param	key a unique indentifier.
	 *
	 * @return	A string value.
	 */
	synchronized public String getString(String key)
	{
		return (String)mStringMap.get(key);
	}

	/**
	 * Set a string variable value to cache.
	 *
	 * @param	key a unique indentifier.
	 * @param	val string value.
	 */
	synchronized public void setString(String key, String val)
	{
		if (val == null)
		{
			mStringMap.put(key, new String(""));
		}
		else
		{
			mStringMap.put(key, new String(val));
		}
	}

	/**
	 * Get an integer variable value stored.
	 *
	 * @param	key a unique indentifier.
	 *
	 * @return	An integer value.
	 */
	synchronized public Integer getInteger(String key)
	{
		return (Integer)mIntegerMap.get(key);
	}

	/**
	 * Set an integer variable value to cache.
	 *
	 * @param	key a unique indentifier.
	 * @param	val intger value.
	 */
	synchronized public void setInteger(String key, int val)
	{
		mIntegerMap.put(key, new Integer(val));
	}

	/**
	 * Checks to see whether or not the user is stil valid or expired.
	 *
	 * @return	true if the user information is expired
	 *			false if user information is not expired.
	 */
	synchronized public boolean isExpired()
	{
		boolean expired;

		// Check if the ticket is still valid.
		try
		{
			IdmRepImpl rep = new IdmRepImpl(mTicket);
			expired = !rep.isValid();
		}
		catch (Exception e)
		{
			expired = true;
		}

		if (expired)
		{
			return true;
		}

		Calendar cal = Calendar.getInstance();

		int dayApart = cal.get(Calendar.DATE) - mTime.get(Calendar.DATE);
		int hourApart =
			cal.get(Calendar.HOUR_OF_DAY) - mTime.get(Calendar.HOUR_OF_DAY);
		int minuteApart = cal.get(Calendar.MINUTE) - mTime.get(Calendar.MINUTE);
		int secondApart = cal.get(Calendar.SECOND) - mTime.get(Calendar.SECOND);

		if (dayApart > 0)
		{
			// Compensate if it is already the next day.
			hourApart += 24;
		}

		int seconds = hourApart * 60 + minuteApart * 60 + secondApart;

		if (mLogger.isLoggable(Level.FINE))
		{
			StringBufferS msg = new StringBufferS
			(
				"Elapsed time=" + seconds + " seconds, " +
				"Cleanup interval=" + mCleanupInterval + " seconds"
			);
			mLogger.fine(msg.toString());
		}

		if (seconds > mCleanupInterval)
		{
			return true;
		}

		return false;
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
