package TTREPLOGGER;

import TTREPLOGGER.EventTypes;

/**
 *	Generated from IDL definition of enum "EventTypes"
 *	@author JacORB IDL compiler 
 */

public final class EventTypes
	implements org.omg.CORBA.portable.IDLEntity
{
	private int value = -1;
	public static final int _EV_UWSADDTITLE = 0;
	public static final EventTypes EV_UWSADDTITLE = new EventTypes(_EV_UWSADDTITLE);
	public static final int _EV_UWSDELTITLE = 1;
	public static final EventTypes EV_UWSDELTITLE = new EventTypes(_EV_UWSDELTITLE);
	public static final int _EV_UWSMODTITLE = 2;
	public static final EventTypes EV_UWSMODTITLE = new EventTypes(_EV_UWSMODTITLE);
	public int value()
	{
		return value;
	}
	public static EventTypes from_int(int value)
	{
		switch (value) {
			case _EV_UWSADDTITLE: return EV_UWSADDTITLE;
			case _EV_UWSDELTITLE: return EV_UWSDELTITLE;
			case _EV_UWSMODTITLE: return EV_UWSMODTITLE;
			default: throw new org.omg.CORBA.BAD_PARAM();
		}
	}
	protected EventTypes(int i)
	{
		value = i;
	}
	java.lang.Object readResolve()
	throws java.io.ObjectStreamException
	{
		return from_int(value());
	}
}
