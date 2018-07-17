////////////////////////////////////////////////////////////////////////////////
//	Title		:	RendererUserCache.java
//
//	Description	:	Class which keeps track of the rendering cache for a
//					specific ticket.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.logging.Level;

import com.towertechnology.common.environment.EnvironmentClassServer;
import com.towertechnology.common.errorhandling.TTCommonErrors;
import com.towertechnology.common.errorhandling.TTException;
import com.vignette.idm.common.IDMConfiguration;
import com.vignette.idm.common.StringBufferS;

class RendererUserCache
{
	private static java.util.logging.Logger mLogger =
		java.util.logging.Logger.getLogger
		(
			"com.vignette.idm.services.RendererUserCache"
		);

	private static IDMConfiguration mConfig = null;

	private HashMap mCache = null;
	private String mCacheDirName = null;
	private String mKey = null;

	static
	{
		mConfig = (IDMConfiguration)EnvironmentClassServer.getClass
		(
			IDMConfiguration.class
		);
	}

	/**
	 *	Create an object of this class.
	 *
	 *	@param aRootCacheDirName contains root location for cache directories
	 *	@param aKey contains the ticket from IDM Server
	 *
	 */
	public RendererUserCache(String aRootCacheDirName, String aKey)
	{
		mCache	= new HashMap();
		mKey	= aKey;
		if (aRootCacheDirName != null)
		{
			mCacheDirName =
				aRootCacheDirName + File.separator + mKey.replace(':', '_');
		}
	}

	/**
	 *	Creates the cache directory for the current ticket.
	 *
	 */
	private void createCacheDir()
	{
		File cacheDir = new File(mCacheDirName);
		if (cacheDir.exists() == false)
		{
			cacheDir.deleteOnExit();
			cacheDir.mkdirs();

			if (mLogger.isLoggable(Level.FINE))
			{
				StringBufferS msg = new StringBufferS
				(
					"User cache directory = " + mCacheDirName
				);
				mLogger.fine(msg.toString());
			}
		}
	}

	/**
	 *	Checks if the specified document subpage is in the cache.
	 *
	 *	@param aSubPageKey contains IFN, page and subpage number as a string
	 *	@param aParams contains the rendering parameters for the IFN subpage
	 *
	 *	@return True is in cache or false if not.
	 *
	 */
	synchronized public boolean isSubPageInCache
	(
		String	aSubPageKey,
		String	aParams
	)
	{
		// If no cache directory is configured, caching is not in effect.
		if (mCacheDirName == null)
		{
			return false;
		}

		// Check if the required entries are in the hash map.
		if (mCache.containsKey(aSubPageKey) == false)
		{
			return false;
		}

		// Check if the required file exists.
		RendererUserCacheData cacheData =
			(RendererUserCacheData)mCache.get(aSubPageKey);
		File file = new File(cacheData.mCacheFileName);
		if (file.exists() == false)
		{
			return false;
		}

		return true;
	}

	/**
	 *	Fetches IFN subpage data from the cache.
	 *
	 *	@param aSubPageKey
	 *
	 *	@return IFN subpage data
	 *
	 *	@throws TTException
	 *
	 */
	synchronized public ImageData fetchSubPageFromCache(String aSubPageKey)
	throws TTException
	{
		try
		{
			// Fetch data from the hash map.
			RendererUserCacheData cacheData =
				(RendererUserCacheData)mCache.get(aSubPageKey);
			ImageData imageData = new ImageData();
			imageData.mParams = cacheData.mParams;
			imageData.mSubPageCount = cacheData.mSubPageCount;
			imageData.mExtension = cacheData.mExtension;
			imageData.mContentType = cacheData.mContentType;

			// Open the cache file and read its contents into a byte array.
			File cacheFile = new File(cacheData.mCacheFileName);
			FileInputStream cacheStream = new FileInputStream(cacheFile);
			int byteNum = cacheStream.available();
			imageData.mData = new byte[byteNum];
			cacheStream.read(imageData.mData, 0, byteNum);
			cacheStream.close();

			// Return the IFN subpage data.
			return imageData;
		}

		// Catch various exceptions and translate if required.
		catch (Exception e)
		{
			String msg =
				"Unable to load subpage from cache for " +
				mKey + " and " + aSubPageKey;
			mLogger.severe(msg);
			throw new TTException(msg, TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}
	}

	/**
	 *	Adds IFN subpage into the cache.
	 *
	 *	@param aSubPageKey contains IFN, page and subpage number as a string
	 *	@param aImageData contains data to be cached
	 *
	 *	@throws TTException
	 *
	 */
	synchronized public void addSubPageToCache
	(
		String		aSubPageKey,
		ImageData	aImageData
	) throws TTException
	{
		try
		{
			// If no cache directory is configured, caching is not in effect.
			if (mCacheDirName == null)
			{
				return;
			}

			// Create cache directory if it does not exists.
			createCacheDir();

			// Create image file.
			String cacheFileName = mCacheDirName + File.separator + aSubPageKey;
			File cacheFile = new File(cacheFileName);
			cacheFile.deleteOnExit();
			FileOutputStream cacheStream = new FileOutputStream(cacheFile);
			cacheStream.write(aImageData.mData, 0, aImageData.mData.length);
			cacheStream.close();

			if (mLogger.isLoggable(Level.FINE))
			{
				StringBufferS msg = new StringBufferS
				(
					"Added subpage to cache = " + cacheFileName
				);
				mLogger.fine(msg.toString());
			}

			// Save data in the hash map.
			RendererUserCacheData cacheData = new RendererUserCacheData();
			cacheData.mCacheFileName = cacheFileName;
			cacheData.mParams = aImageData.mParams;
			cacheData.mSubPageCount = aImageData.mSubPageCount;
			cacheData.mExtension = aImageData.mExtension;
			cacheData.mContentType = aImageData.mContentType;
			mCache.put(aSubPageKey, cacheData);
		}

		// Catch various exceptions and translate if required.
		catch (Exception e)
		{
			String msg =
				"Unable to save subpage to cache for " +
				mKey + " and " + aSubPageKey;
			mLogger.severe(msg);
			throw new TTException(msg, TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}
	}

	/**
	 *	Checks if the specified IFN page is in the cache.
	 *
	 *	@param aPageKey contains the IFN and page number as a string
	 *
	 *	@return True is in cache or false if not.
	 *
	 */
	synchronized public boolean isPageInCache(String aPageKey)
	{
		// If no cache directory is specified, caching is not in effect.
		if (mCacheDirName == null)
		{
			return false;
		}

		// Check if the required entries are in the hash map.
		if ( mCache.containsKey(aPageKey) == false)
		{
			return false;
		}

		// Check if the required file exists.
		String fileName = (String)mCache.get(aPageKey);
		File file = new File(fileName);
		if (file.exists() == false)
		{
			return false;
		}

		return true;
	}

	/**
	 *	Obtains the byte stream for the specified IFN page from the cache.
	 *
	 *	@param aPageKey contains the IFN and page number as a string
	 *
	 *	@return IFN page byte stream obtained from the cache.
	 *
	 */
	synchronized public byte[] getPageData(String aPageKey) throws TTException
	{
		try
		{
			// Determine the name of the file from the cache.
			String cacheFileName = (String)mCache.get(aPageKey);

			// Open the cache file and read its contents into a byte array.
			File cacheFile = new File(cacheFileName);
			FileInputStream cacheStream = new FileInputStream(cacheFile);
			int byteNum = cacheStream.available();
			byte[] buf = new byte[byteNum];
			cacheStream.read(buf, 0, byteNum);
			cacheStream.close();

			// Return the byte array.
			return buf;
		}

		// Catch various exceptions and translate if required.
		catch (Exception e)
		{
			String msg =
				"Unable to load page image from cache for " +
				mKey + " and " + aPageKey;
			mLogger.severe(msg);
			throw new TTException(msg, TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}
	}

	/**
	 *	Adds the specified IFN page byte stream to the cache.
	 *
	 *	@param aPageKey contains the IFN and page number as a string
	 *	@param aExtension is the file extension to use for the file in cache
	 *	@param aData is the byte stream to store in the cache
	 *
	 *	@throws TTException
	 *
	 */
	synchronized public void addPageToCache
	(
		String	aPageKey,
		String	aExtension,
		byte[]	aData
	) throws TTException
	{
		try
		{
			// If no cache directory is configured, caching is not in effect.
			if (mCacheDirName == null)
			{
				return;
			}

			// Create cache directory if it does not exists.
			createCacheDir();

			// Create image file.
			String cacheFileName =
				mCacheDirName + File.separator + aPageKey + "." + aExtension;
			File cacheFile = new File(cacheFileName);
			cacheFile.deleteOnExit();
			FileOutputStream cacheStream = new FileOutputStream(cacheFile);
			cacheStream.write(aData, 0, aData.length);
			cacheStream.close();

			if (mLogger.isLoggable(Level.FINE))
			{
				StringBufferS msg = new StringBufferS
				(
					"Added page to cache = " + cacheFileName
				);
				mLogger.fine(msg.toString());
			}

			// Add the entries in the hash map.
			mCache.put(aPageKey, cacheFileName);
		}

		// Catch various exceptions and translate if required.
		catch (Exception e)
		{
			String msg =
				"Unable to save page image to cache for " +
				mKey + " and " + aPageKey;
			mLogger.severe(msg);
			throw new TTException(msg, TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}
	}

	/**
	 *	Removes all cache files and entries relating to specified IFN.
	 *
	 *	@param	anIfn - IFN to delete cache files and entries for
	 */
	synchronized public void removeFromCache(String anIfn)
	{
		// Create corresponding key for IFN.
		String template = anIfn.replace('/', '-') + "-";

		// Delete all cache files and entries for specified IFN.
		Set keys = mCache.keySet();
		Iterator iterator = keys.iterator();
		while (iterator.hasNext() == true)
		{
			String key = (String)iterator.next();
			if (key.startsWith(template))
			{
				// Delete cache file.  Note that there are two types of keys
				// thus need to check the key type when interpreting the
				// data.
				String cacheFileName;
				if (key.indexOf("-", template.length()) == -1)
				{
					cacheFileName = (String)mCache.get(key);
				}
				else
				{
					RendererUserCacheData cacheData =
						(RendererUserCacheData)mCache.get(key);
					cacheFileName = cacheData.mCacheFileName;
				}
				File imageFile = new File(cacheFileName);
				if (imageFile.delete() == false)
				{
					if (mLogger.isLoggable(Level.FINE))
					{
						StringBufferS msg = new StringBufferS
						(
							"Unable to delete file " + imageFile.getName()
						);
						mLogger.fine(msg.toString());
					}
				}

				// Delete cache entry.
				mCache.remove(key);
			}
		}
	}

	/**
	 *	Returns the directory for this cache.
	 *
	 *	@return Cache directory name
	 *
	 */
	synchronized public String getCacheDirName()
	{
		return mCacheDirName;
	}

	/**
	 *	Deletes the entries in this cache.
	 *
	 */
	synchronized public void cleanup()
	{
		mCache.clear();
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
