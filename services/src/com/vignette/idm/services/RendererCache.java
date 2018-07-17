////////////////////////////////////////////////////////////////////////////////
//	Title		:	RendererCache.java
//
//	Description	:	Class which keeps track of the rendering cache.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.services;

import java.io.File;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.logging.Level;

import com.towertechnology.common.environment.EnvironmentClassServer;
import com.towertechnology.common.errorhandling.TTException;
import com.vignette.idm.common.IDMConfiguration;
import com.vignette.idm.common.StringBufferS;

public class RendererCache
{
	private static java.util.logging.Logger mLogger =
		java.util.logging.Logger.getLogger
		(
			"com.vignette.idm.services.RendererCache"
		);

	private static RendererCache mCache = null;
	private HashMap mRootCache = null;
	private String mRootCacheDirName = null;

	/**
	 *	Creates an instance of this class.
	 *
	 */
	private RendererCache()
	{
		IDMConfiguration config =
			(IDMConfiguration)EnvironmentClassServer.getClass
			(
				IDMConfiguration.class
			);

		// Create a new cache HashMap
		mRootCache = new HashMap(100);

		// Obtain the root cache directory from configuration.
		// Create directory if it doesn't exists.
		mRootCacheDirName =
			config.getConfigurationValue("Renderer.CacheDirectory");
		File rootCacheDir = new File(mRootCacheDirName);
		if (rootCacheDir.exists() == false)
		{
			rootCacheDir.mkdirs();
		}

		if (mLogger.isLoggable(Level.FINE))
		{
			StringBufferS msg = new StringBufferS
			(
				"Root cache directory = " + mRootCacheDirName
			);
			mLogger.fine(msg.toString());
		}
	}

	/**
	* Static public method to retrieve the single instance of the
	* RendererCache class
	*/
	public static RendererCache getInstance()
	{
		if (mCache == null)
		{
			mCache = new RendererCache();
		}
		return mCache;
	}

	/**
	 *	Deletes files and directories contained in the specified directory.
	 *
	 *	@param aDir is name of directory whose contents are to be deleted
	 *
	 */
	private void cleanupDir(File aDir)
	{
		// Delete directories and files beneath the specified directory.
		File[] files = aDir.listFiles();
		if (files == null)
		{
			return;
		}

		for (int i = 0; i < files.length; i++)
		{
			if (files[i].isDirectory() == true)
			{
				// Delete directory and its contents.
				// This normally shouldn't be necessary but just in case
				// someone else wrote into our cache directories.
				cleanupDir(files[i]);
				if (files[i].delete() == false)
				{
					if (mLogger.isLoggable(Level.FINE))
					{
						StringBufferS msg = new StringBufferS
						(
							"Unable to delete directory " + files[i].getName()
						);
						mLogger.fine(msg.toString());
					}
				}
			}
			else
			{
				// Delete file.
				if (files[i].delete() == false)
				{
					if (mLogger.isLoggable(Level.FINE))
					{
						StringBufferS msg = new StringBufferS
						(
							"Unable to delete file " + files[i].getName()
						);
						mLogger.fine(msg.toString());
					}
				}
			}
		}
	}

	/**
	 *	Obtains the cache for the specified ticket.
	 *
	 *	@param aKey is the ticket from IDM Server
	 *
	 *	@throws TTException
	 *
	 */
	synchronized public RendererUserCache getUserCache(String aKey)
	throws TTException
	{
		if (mRootCache.containsKey(aKey) == false)
		{
			if (mLogger.isLoggable(Level.FINE))
			{
				StringBufferS msg = new StringBufferS
				(
					"Creating user cache for " + aKey
				);
				mLogger.fine(msg.toString());
			}

			RendererUserCache userCache =
				new RendererUserCache(mRootCacheDirName, aKey);
			mRootCache.put(aKey, userCache);
			return userCache;
		}
		else
		{
			return (RendererUserCache)mRootCache.get(aKey);
		}
	}

	/**
	 *	This method deletes the contents of the cache directory just in case
	 *	the JVM wasn't able to perform a proper cleanup when it terminated.
	 *	Ideally, this method must be executed at the beginning of a daemon-like
	 *	thread to make sure that the cache directory is free from unwanted
	 *	directories and files.
	 *
	 */
	synchronized public void cleanupCacheDir()
	{
		if (mRootCacheDirName != null)
		{
			mLogger.fine("Performing initial rendering cache cleanup");

			File rootCacheDir = new File(mRootCacheDirName);
			cleanupDir(rootCacheDir);
		}
	}

	/**
	 *	This method deletes entries in the cache when a user logs out
	 *
	 *	@param	userTicket a ticket that a user gets when logs in successfully
	 */
	synchronized public void cleanupUserCache(String userTicket)
	{
		if (mRootCacheDirName != null && mRootCache.containsKey(userTicket))
		{
			RendererUserCache userCache =
				(RendererUserCache)mRootCache.get(userTicket);

			// Remember the directory used by the cache for the ticket.
			String cacheDirName = userCache.getCacheDirName();

			// Delete the cache for the ticket.
			userCache.cleanup();
			mRootCache.remove(userTicket);

			// Delete the cache directory for the ticket.
			File directory = new File(cacheDirName);
			cleanupDir(directory);

			if (directory.delete() == false)
			{
				if (mLogger.isLoggable(Level.FINE))
				{
					StringBufferS msg = new StringBufferS
					(
						"Unable to delete directory " + directory.getName()
					);
					mLogger.fine(msg.toString());
				}
			}
		}
	}

	/**
	 *	This method deletes entries in the cache for tickets which have
	 *	expired.
	 */
	synchronized public void cleanupCache()
	{
		if (mRootCacheDirName != null)
		{
			mLogger.fine("Performing scheduled rendering cache cleanup");

			// Create a collection view of the tickets in the cache.
			Set tickets = mRootCache.keySet();

			// Go through the collection and remove expired tickets.
			Iterator iterator = tickets.iterator();
			while (iterator.hasNext() == true)
			{
				// Check the status of the ticket.
				String ticket = (String)iterator.next();
				boolean expired;
				try
				{
					IdmRepImpl repository = new IdmRepImpl(ticket);
					expired = !repository.isValid();
				}
				catch (Exception e)
				{
					expired = true;
				}

				// If the ticket is expired, delete the cache for the ticket.
				if (expired == true)
				{
					if (mLogger.isLoggable(Level.FINE))
					{
						StringBufferS msg = new StringBufferS
						(
							"Removing from cache " + ticket
						);
						mLogger.fine(msg.toString());
					}

					// Delete the cache directory for the ticket.
					RendererUserCache userCache =
						(RendererUserCache)mRootCache.get(ticket);
					File directory = new File(userCache.getCacheDirName());
					cleanupDir(directory);
					if (directory.delete() == false)
					{
						if (mLogger.isLoggable(Level.FINE))
						{
							StringBufferS msg = new StringBufferS
							(
								"Unable to delete directory " +
								directory.getName()
							);
							mLogger.fine(msg.toString());
						}
					}

					// Delete the cache entry for the ticket.
					userCache.cleanup();
					mRootCache.remove(ticket);
				}
				else
				{
					if (mLogger.isLoggable(Level.FINE))
					{
						StringBufferS msg = new StringBufferS
						(
							"Found " + ticket + " in the cache"
						);
						mLogger.fine(msg.toString());
					}
				}
			}
		}
	}

	/**
	 *	Obtains the number of entries in the hashmap.
	 *
	 *	@return Number of entries in the hashmap
	 *
	 */
	synchronized public int getSize()
	{
		return mRootCache.size();
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
