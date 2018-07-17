////////////////////////////////////////////////////////////////////////////////
//	Title		:	TicketMap.java
//
//	Description	:	Class which keeps track of ticket mappings to Queue CORBA
//					server references.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;

import com.towertechnology.common.errorhandling.TTException;
import com.vignette.idm.common.StringBufferS;

public class TicketMap
{
	private static final String START = "Start: ";
	private static final String COMPLETE = "Complete: ";

	private static java.util.logging.Logger mLogger =
		java.util.logging.Logger.getLogger
		(
			"com.vignette.idm.services.TicketMap"
		);

	private static TicketMap mTicketMapObj = null;
	private HashMap mTicketMap = null;

	private TicketMap()
	{
		mTicketMap = new HashMap();
	}

	public static TicketMap getInstance()
	{
		if (mTicketMapObj == null)
		{
			mTicketMapObj = new TicketMap();
		}
		return mTicketMapObj;
	}

	synchronized public void addTicketMapping(String aTicket, String aServerRef)
	{
		if (mTicketMap.containsKey(aTicket) == false)
		{
			mTicketMap.put(aTicket, aServerRef);
		}
	}

	synchronized public void removeTicketMapping(String aTicket)
	{
		if (mTicketMap.containsKey(aTicket) == true)
		{
			mTicketMap.remove(aTicket);
		}
	}

	synchronized public String getValue(String aTicket)
	{
		if (mTicketMap.containsKey(aTicket) == true)
		{
			return (String)mTicketMap.get(aTicket);
		}
		return null;
	}

	synchronized public void cleanupQueueSessions() throws TTException
	{
		mLogger.info(START);

		Set allKeys = mTicketMap.keySet();

		Iterator iterator = allKeys.iterator();
		while (iterator.hasNext() == true)
		{
			String ticket = (String)iterator.next();

			if (mLogger.isLoggable(Level.FINE))
			{
				StringBufferS msg = new StringBufferS("Ticket=" + ticket);
				mLogger.fine(msg.toString());
			}

			boolean expired = false;
			IdmRepImpl repository = null;

			try
			{
				repository = new IdmRepImpl(ticket);
				expired = !repository.isValid();
			}
			catch (Exception e)
			{
				expired = true;
			}

			if (expired == true)
			{
				String serverRef = (String)mTicketMap.get(ticket);

				try
				{
					Queue qObj = new Queue(repository);
					qObj.unregisterSession(serverRef);
				}
				catch (Exception e)
				{
					// If there is a problem with CORBA log message and
					// remove the expired ticket from the map
					mLogger.severe(e.toString());
				}

				iterator.remove();

				mTicketMap.remove(ticket);

				mLogger.fine("Removed an expired queue session");
			}
		}

		mLogger.info(COMPLETE);
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
