////////////////////////////////////////////////////////////////////////////////
//	Title		:	NameValue.java
//
//	Description	:	Class which holds a name/value pair.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.common;

public class NameValue
{
	private String  Name;
	private String  Value;

 /**
  * A constructor that accepts name and value.
  *
  * @param aName The name of the object.
  * @param aValue The value of the object.
  */
	public NameValue(String name, String value)
	{
		this.Name = name;
		this.Value = value;
	}

 /**
  * Constructs a simple version of NameValue.
  */
	public NameValue()
	{
	}

 /**
  * Sets the stored name.
  *
  * @param aName The new name.
  */
	public void setName(String name)
	{
		this.Name = name;
	}

 /**
  * Sets the stored value.
  *
  * @param aValue The new value.
  */
	public void setValue(String value)
	{
		this.Value = value;
	}

 /**
  * Returns the stored name.
  *
  * @return The stored name.
  */
	public String getName()
	{
		return Name;
	}

 /**
  * Returns the stored value.
  *
  * @return The stored value.
  */
	public String getValue()
	{
		return Value;
	}
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
