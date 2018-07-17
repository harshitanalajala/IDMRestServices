////////////////////////////////////////////////////////////////////////////////
//	Title		:	Ticket.java
//
//	Description	:	Class used to hold an IDM ticket and dynamic ticketing flag.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.server.common;

public class Ticket
{
	private String mTicket;
	private String mUsername;
	private boolean mDynamicTicket;

	public Ticket(String aTicket, boolean aDynamicTicket)
	{
		mUsername = null;
		mTicket = aTicket;
		mDynamicTicket = aDynamicTicket;
	}

	public Ticket(String aTicket, String aUsername, boolean aDynamicTicket)
	{
		mTicket = aTicket;
		mUsername = aUsername;
		mDynamicTicket = aDynamicTicket;
	}

	public String getTicket()
	{
		return mTicket;
	}

	public String getUsername()
	{
		return mUsername;
	}

	public boolean getDynamicTicket()
	{
		return mDynamicTicket;
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
