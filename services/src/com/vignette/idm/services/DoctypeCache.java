////////////////////////////////////////////////////////////////////////////////
//	Title		:	DoctypeCache.java
//
//	Description	:	Class which keeps track of the document type cache.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;

import com.vignette.idm.common.StringBufferS;

public class DoctypeCache
{
	private static java.util.logging.Logger mLogger =
		java.util.logging.Logger.getLogger
		(
			"com.vignette.idm.services.DoctypeCache"
		);

	private static DoctypeCache mCache = null;

	private HashMap mUserCache = null;

	private DoctypeCache()
	{
		mUserCache = new HashMap(20);
	}

	public static DoctypeCache getInstance()
	{
		if (mCache == null)
		{
			mCache = new DoctypeCache();
		}
		return mCache;
	}

	synchronized public DoctypeUserCache getUserCache(String aTicket)
	{
		return (DoctypeUserCache)mUserCache.get(aTicket);
	}

	synchronized public void setUserCache
	(
		String				aTicket,
		DoctypeUserCache	aUCache
	)
	{
		mUserCache.put(aTicket, aUCache);

		if (mLogger.isLoggable(Level.FINE))
		{
			StringBufferS msg = new StringBufferS
			(
				"Add <" + aTicket + "> user ticket to doctype cache"
			);
			mLogger.fine(msg.toString());
		}
	}

	synchronized public void removeUser(String aTicket)
	{
		if (mUserCache.isEmpty())
		{
			return;
		}

		mUserCache.remove(aTicket);

		if (mLogger.isLoggable(Level.FINE))
		{
			StringBufferS msg = new StringBufferS
			(
				"Removing <" + aTicket + "> user ticket from doctype cache"
			);
			mLogger.fine(msg.toString());
		}

		Collection userCache = mUserCache.values();
		Iterator itr = userCache.iterator();

		// Delete any cache that is expired.
		while (itr.hasNext() == true)
		{
			DoctypeUserCache uCache = (DoctypeUserCache)itr.next();
			if (uCache.isExpired())
			{
				if (mLogger.isLoggable(Level.FINE))
				{
					StringBufferS msg = new StringBufferS
					(
						"Removing <" + uCache.getTicket() +
						"> user ticket from doctype cache"
					);
					mLogger.fine(msg.toString());
				}
				itr.remove();
			}
		}
	}

	synchronized public void cleanupCache()
	{
		if (mUserCache.isEmpty())
		{
			return;
		}
		mUserCache.clear();

		mLogger.fine("Removing document type security cache");
	}

	synchronized public int getSize()
	{
		return mUserCache.size();
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
