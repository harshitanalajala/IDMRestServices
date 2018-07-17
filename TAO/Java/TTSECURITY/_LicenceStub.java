package TTSECURITY;

import TTSECURITY.LicenceOperations;

/**
 *	Generated from IDL interface "Licence"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public class _LicenceStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements TTSECURITY.Licence
{
	private String[] ids = {"IDL:TTSECURITY/Licence:1.0"};
	@Override
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = TTSECURITY.LicenceOperations.class;
	@Override
	public void cleanupExpired() throws TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "cleanupExpired", true);
				_is = _invoke(_os);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "cleanupExpired", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			LicenceOperations _localServant = (LicenceOperations)_so.servant;
			try
			{
			_localServant.cleanupExpired();
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
	public java.lang.String login(java.lang.String aSessionID, java.lang.String aClientIP, java.lang.String aUserID, java.lang.String aPasswd, java.lang.String aNewPasswd, java.lang.String aLicenceType, java.lang.String aForLog) throws TTSECURITY.CredentialException,TTSECURITY.ExpiredException,TTEXCEPTIONS.GenericException,TTSECURITY.NoAvailableException,TTEXCEPTIONS.TTlibException,TTSECURITY.BadLicenseTypeException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "login", true);
				_os.write_string(aSessionID);
				_os.write_string(aClientIP);
				_os.write_string(aUserID);
				_os.write_string(aPasswd);
				_os.write_string(aNewPasswd);
				_os.write_string(aLicenceType);
				_os.write_string(aForLog);
				_is = _invoke(_os);
				java.lang.String _result = _is.read_string();
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTSECURITY/CredentialException:1.0"))
				{
					throw TTSECURITY.CredentialExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTSECURITY/ExpiredException:1.0"))
				{
					throw TTSECURITY.ExpiredExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTSECURITY/NoAvailableException:1.0"))
				{
					throw TTSECURITY.NoAvailableExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/TTlibException:1.0"))
				{
					throw TTEXCEPTIONS.TTlibExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTSECURITY/BadLicenseTypeException:1.0"))
				{
					throw TTSECURITY.BadLicenseTypeExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "login", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			LicenceOperations _localServant = (LicenceOperations)_so.servant;
			java.lang.String _result;			try
			{
			_result = _localServant.login(aSessionID,aClientIP,aUserID,aPasswd,aNewPasswd,aLicenceType,aForLog);
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
	public int userCount(java.lang.String aTicket) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "userCount", true);
				_os.write_string(aTicket);
				_is = _invoke(_os);
				int _result = _is.read_ulong();
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "userCount", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			LicenceOperations _localServant = (LicenceOperations)_so.servant;
			int _result;			try
			{
			_result = _localServant.userCount(aTicket);
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
	public void refresh(java.lang.String aTicket) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "refresh", true);
				_os.write_string(aTicket);
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "refresh", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			LicenceOperations _localServant = (LicenceOperations)_so.servant;
			try
			{
			_localServant.refresh(aTicket);
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
	public boolean isValid(java.lang.String aTicket) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "isValid", true);
				_os.write_string(aTicket);
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "isValid", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			LicenceOperations _localServant = (LicenceOperations)_so.servant;
			boolean _result;			try
			{
			_result = _localServant.isValid(aTicket);
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
	public TTSECURITY.UserInfo getUserInfo(java.lang.String aTicket) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "getUserInfo", true);
				_os.write_string(aTicket);
				_is = _invoke(_os);
				TTSECURITY.UserInfo _result = TTSECURITY.UserInfoHelper.read(_is);
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "getUserInfo", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			LicenceOperations _localServant = (LicenceOperations)_so.servant;
			TTSECURITY.UserInfo _result;			try
			{
			_result = _localServant.getUserInfo(aTicket);
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
	public void logout(java.lang.String aTicket) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "logout", true);
				_os.write_string(aTicket);
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "logout", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			LicenceOperations _localServant = (LicenceOperations)_so.servant;
			try
			{
			_localServant.logout(aTicket);
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
