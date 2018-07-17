package TTGENERAL;

import TTGENERAL.MiscFunctionsOperations;

/**
 *	Generated from IDL interface "MiscFunctions"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public class _MiscFunctionsStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements TTGENERAL.MiscFunctions
{
	private String[] ids = {"IDL:TTGENERAL/MiscFunctions:1.0"};
	@Override
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = TTGENERAL.MiscFunctionsOperations.class;
	@Override
	public void logError(java.lang.String aTicket, java.lang.String aMessage, java.lang.String aReason) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "logError", true);
				_os.write_string(aTicket);
				_os.write_string(aMessage);
				_os.write_string(aReason);
				_is = _invoke(_os);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "logError", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			MiscFunctionsOperations _localServant = (MiscFunctionsOperations)_so.servant;
			try
			{
			_localServant.logError(aTicket,aMessage,aReason);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return;
		}

		}

	}

	@Override
	public void logMessage(java.lang.String aTicket, java.lang.String aTraceSeverity, java.lang.String aMessage, java.lang.String aFilename) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "logMessage", true);
				_os.write_string(aTicket);
				_os.write_string(aTraceSeverity);
				_os.write_string(aMessage);
				_os.write_string(aFilename);
				_is = _invoke(_os);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "logMessage", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			MiscFunctionsOperations _localServant = (MiscFunctionsOperations)_so.servant;
			try
			{
			_localServant.logMessage(aTicket,aTraceSeverity,aMessage,aFilename);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return;
		}

		}

	}

	@Override
	public boolean isApplication(java.lang.String aTicket, java.lang.String anAppName) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "isApplication", true);
				_os.write_string(aTicket);
				_os.write_string(anAppName);
				_is = _invoke(_os);
				boolean _result = _is.read_boolean();
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "isApplication", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			MiscFunctionsOperations _localServant = (MiscFunctionsOperations)_so.servant;
			boolean _result;			try
			{
			_result = _localServant.isApplication(aTicket,anAppName);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return _result;
		}

		}

	}

	@Override
	public java.lang.String selectDiskset(java.lang.String aTicket, java.lang.String aTabName, TTGENERAL.FieldAttr[] aFieldValues) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "selectDiskset", true);
				_os.write_string(aTicket);
				_os.write_string(aTabName);
				TTGENERAL.DataRowHelper.write(_os,aFieldValues);
				_is = _invoke(_os);
				java.lang.String _result = _is.read_string();
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "selectDiskset", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			MiscFunctionsOperations _localServant = (MiscFunctionsOperations)_so.servant;
			java.lang.String _result;			try
			{
			_result = _localServant.selectDiskset(aTicket,aTabName,aFieldValues);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return _result;
		}

		}

	}

	@Override
	public int getDocPageCount(java.lang.String aTicket, java.lang.String anIfn) throws TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.InvalidDSException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "getDocPageCount", true);
				_os.write_string(aTicket);
				_os.write_string(anIfn);
				_is = _invoke(_os);
				int _result = _is.read_ulong();
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTEXCEPTIONS/DocPermException:1.0"))
				{
					throw TTEXCEPTIONS.DocPermExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/InvalidDSException:1.0"))
				{
					throw TTEXCEPTIONS.InvalidDSExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/BadDocIdException:1.0"))
				{
					throw TTEXCEPTIONS.BadDocIdExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/UnknownDocException:1.0"))
				{
					throw TTEXCEPTIONS.UnknownDocExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "getDocPageCount", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			MiscFunctionsOperations _localServant = (MiscFunctionsOperations)_so.servant;
			int _result;			try
			{
			_result = _localServant.getDocPageCount(aTicket,anIfn);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return _result;
		}

		}

	}

	@Override
	public TTGENERAL.ApplicationEntry[] getAppList(java.lang.String aTicket) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "getAppList", true);
				_os.write_string(aTicket);
				_is = _invoke(_os);
				TTGENERAL.ApplicationEntry[] _result = TTGENERAL.ApplicationTableHelper.read(_is);
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "getAppList", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			MiscFunctionsOperations _localServant = (MiscFunctionsOperations)_so.servant;
			TTGENERAL.ApplicationEntry[] _result;			try
			{
			_result = _localServant.getAppList(aTicket);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return _result;
		}

		}

	}

	@Override
	public void logEvent(java.lang.String aTicket, java.lang.String aCategory, java.lang.String aName, java.lang.String[] aNameValues) throws TTGENERAL.UnknownCategoryOrEvent,TTEXCEPTIONS.AuthenticationException,TTGENERAL.UnknownField,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "logEvent", true);
				_os.write_string(aTicket);
				_os.write_string(aCategory);
				_os.write_string(aName);
				TTGENERAL.StringSeqHelper.write(_os,aNameValues);
				_is = _invoke(_os);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTGENERAL/UnknownCategoryOrEvent:1.0"))
				{
					throw TTGENERAL.UnknownCategoryOrEventHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTGENERAL/UnknownField:1.0"))
				{
					throw TTGENERAL.UnknownFieldHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "logEvent", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			MiscFunctionsOperations _localServant = (MiscFunctionsOperations)_so.servant;
			try
			{
			_localServant.logEvent(aTicket,aCategory,aName,aNameValues);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return;
		}

		}

	}

	@Override
	public java.lang.String getConfigVar(java.lang.String aTicket, java.lang.String aVarName) throws TTGENERAL.InvalidConfigVar,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "getConfigVar", true);
				_os.write_string(aTicket);
				_os.write_string(aVarName);
				_is = _invoke(_os);
				java.lang.String _result = _is.read_string();
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTGENERAL/InvalidConfigVar:1.0"))
				{
					throw TTGENERAL.InvalidConfigVarHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "getConfigVar", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			MiscFunctionsOperations _localServant = (MiscFunctionsOperations)_so.servant;
			java.lang.String _result;			try
			{
			_result = _localServant.getConfigVar(aTicket,aVarName);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return _result;
		}

		}

	}

	@Override
	public TTGENERAL.NameValue[] getConfigVars(java.lang.String aTicket, java.lang.String aVarPattern) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "getConfigVars", true);
				_os.write_string(aTicket);
				_os.write_string(aVarPattern);
				_is = _invoke(_os);
				TTGENERAL.NameValue[] _result = TTGENERAL.NameValueSeqHelper.read(_is);
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "getConfigVars", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			MiscFunctionsOperations _localServant = (MiscFunctionsOperations)_so.servant;
			TTGENERAL.NameValue[] _result;			try
			{
			_result = _localServant.getConfigVars(aTicket,aVarPattern);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return _result;
		}

		}

	}

}
