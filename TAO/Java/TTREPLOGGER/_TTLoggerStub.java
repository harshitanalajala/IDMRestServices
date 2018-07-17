package TTREPLOGGER;

import TTREPLOGGER.TTLoggerOperations;

/**
 *	Generated from IDL interface "TTLogger"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public class _TTLoggerStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements TTREPLOGGER.TTLogger
{
	private String[] ids = {"IDL:TTREPLOGGER/TTLogger:1.0"};
	@Override
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = TTREPLOGGER.TTLoggerOperations.class;
	@Override
	public void eventLog(int anEventType, java.lang.String[] anEventData) throws TTREPLOGGER.BadEventType,TTEXCEPTIONS.GenericException,TTREPLOGGER.BadEventDataCount,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "eventLog", true);
				_os.write_long(anEventType);
				TTREPLOGGER.EventDataHelper.write(_os,anEventData);
				_is = _invoke(_os);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTREPLOGGER/BadEventType:1.0"))
				{
					throw TTREPLOGGER.BadEventTypeHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTREPLOGGER/BadEventDataCount:1.0"))
				{
					throw TTREPLOGGER.BadEventDataCountHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/TTlibException:1.0"))
				{
					throw TTEXCEPTIONS.TTlibExceptionHelper.read(_ax.getInputStream());
				}
				else 
					throw new RuntimeException("Unexpected exception " + _id );
			}
			finally
			{
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "eventLog", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			TTLoggerOperations _localServant = (TTLoggerOperations)_so.servant;
			try
			{
			_localServant.eventLog(anEventType,anEventData);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return;
		}

		}

	}

}
