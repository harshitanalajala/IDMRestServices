////////////////////////////////////////////////////////////////////////////////
//	Title		:	LicenceFactory.java
//
//	Description	:	Creates and updates licences which are then mapped to IDM
//					Repository tickets.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.server.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.server.UID;
import java.util.Hashtable;

public class LicenceFactory
{

	private static LicenceFactory factory = null;

	private Hashtable licences;
	private Hashtable singleSignOn;

	/**
	 *	Obtains single instance of licence factory.  Creates instance if it
	 *	does not exists.
	 */
	public static synchronized LicenceFactory getInstance()
	{
		if (factory == null)
		{
			factory = new LicenceFactory();
		}
		return factory;
	}

	/**
	 *	Private default constructor to prevent creation of instances outside
	 *	of this class.
	 */
	private LicenceFactory()
	{
		licences = new Hashtable();
		singleSignOn = new Hashtable();
	}

	/**
	 *  Creates a licence that corresponds to the given ticket.
	 */
	public String newLicence
	(
		String aUsername,
		String aTicket,
		boolean aDynamicTicket
	)
	{
		Ticket ticket = new Ticket
		(
			aTicket,
			aUsername,
			aDynamicTicket
		);

		UID uid = new UID();
		String licence = uid.toString();
		try
		{
			licence += InetAddress.getLocalHost().getHostAddress();
		}
		catch (UnknownHostException e)
		{
		}
		licences.put(licence, ticket);
		singleSignOn.put(aUsername, licence);
		return licence;
	}

	/**
	 *	Creates a licence that corresponds to the given ticket.
	 */
	public String newLicence(String aTicket, boolean aDynamicTicket)
	{
		Ticket ticket = new Ticket(aTicket, aDynamicTicket);
		UID uid = new UID();
		String licence = uid.toString();
		try
		{
			licence += InetAddress.getLocalHost().getHostAddress();
		}
		catch (UnknownHostException e)
		{
		}
		licences.put(licence, ticket);
		return licence;
	}

	/**
	 *	Replaces the given licence with a new licence.
	 */
	public String updateLicence(String aLicence)
	{
		// Save the original ticket.
		Ticket ticket = (Ticket)licences.get(aLicence);
		if (ticket != null)
		{
			if (ticket.getDynamicTicket() == true)
			{
				// Dynamic licencing, discard the old licence.
				deleteLicence(aLicence);

				// Create a new licence for the original ticket.
				return newLicence
				(
					ticket.getTicket(),
					ticket.getDynamicTicket()
				);
			}
			else
			{
				// Non-dynamic licencing, return the old licence.
				return aLicence;
			}
		}
		else
		{
			// Invalid licence.
			return null;
		}
	}

	/**
	 *	Returns the ticket that corresponds to the given licence.
	 */
	public String getTicket(String aLicence)
	{
		Ticket ticket = (Ticket)licences.get(aLicence);
		if (ticket != null)
		{
			return ticket.getTicket();
		}
		else
		{
			return null;
		}
	}

	/**
	 *  Returns the licence that corresponds to the given username.
	 */
	public String getLicence(String aUsername)
	{
		return (String)singleSignOn.get(aUsername);
	}

	/**
	 *	Deletes the given licence.
	 */
	public void deleteLicence(String aLicence)
	{
		if (aLicence != null)
		{
			Ticket ticket = (Ticket)licences.get(aLicence);

			if (ticket != null && ticket.getUsername() != null)
			{
				singleSignOn.remove(ticket.getUsername());
			}
			licences.remove(aLicence);
		}
	}

}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
