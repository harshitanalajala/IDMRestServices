////////////////////////////////////////////////////////////////////////////////
//	Title		:	IDMConfiguration.java
//
//	Description	:	Class which contains IDM Repository configuration.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.common;

import java.util.Hashtable;

import com.towertechnology.common.environment.IEnvironmentClass;
import com.vignette.idm.common.NameValue;

public class IDMConfiguration implements IEnvironmentClass 
{
	private Hashtable ConfigurationTable;

    /** Flag to switch trace on or off. */
    private boolean DebugFlag = false;

    public void setConfigurationTable (Hashtable aConfigurationTable) 
    {
        ConfigurationTable = aConfigurationTable;
    }

    public Hashtable getConfigurationTable () 
    {
        return ConfigurationTable;
    }

	public String getConfigurationValue(String aName)
	{
		if (ConfigurationTable == null)
		{
			return null;
		}
		NameValue config = (NameValue) ConfigurationTable.get(aName);
		return (config == null) ? null : config.getValue(); 
	}

	public String getConfigurationValue(String aName, String defaultValue)
	{
		String value = getConfigurationValue(aName);
		if (value == null && defaultValue != null)
		{
			value = defaultValue;
		}
		return value; 
	}

	public boolean setConfigurationValue(String aName, String aValue)
	{
		if (ConfigurationTable == null)
		{
			return false;
		}
		NameValue config = (NameValue) ConfigurationTable.get(aName);
		if (config == null)
		{
			config = new NameValue(aName, aValue);
		}
		return true;
	}
	
	public void setDebugFlag(Boolean value) 
	{
		DebugFlag = value.booleanValue();
	}

	/** Method to retrieve the TRACE_ON value. */
	public boolean getDebugFlag() 
	{
		return DebugFlag;
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
