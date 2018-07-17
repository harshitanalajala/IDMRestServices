package TTIMPORT;

import TTIMPORT.ImporterOperations;

/**
 *	Generated from IDL interface "Importer"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public class _ImporterStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements TTIMPORT.Importer
{
	private String[] ids = {"IDL:TTIMPORT/Importer:1.0"};
	@Override
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = TTIMPORT.ImporterOperations.class;
	@Override
	public int importDoc(java.lang.String aTicket, java.lang.String anAppName, java.lang.String aDocType, byte[] aDocDataStream, TTIMPORT.TypeMethod aTypeMeth, java.lang.String aPoolName, org.omg.CORBA.StringHolder anIfn) throws TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.InvalidDSException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.AuthenticationException,TTIMPORT.InvalidAppException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "importDoc", true);
				_os.write_string(aTicket);
				_os.write_string(anAppName);
				_os.write_string(aDocType);
				TTIMPORT.DocumentDataHelper.write(_os,aDocDataStream);
				TTIMPORT.TypeMethodHelper.write(_os,aTypeMeth);
				_os.write_string(aPoolName);
				_os.write_string(anIfn.value);
				_is = _invoke(_os);
				int _result = _is.read_long();
				anIfn.value = _is.read_string();
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
				else if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTIMPORT/InvalidAppException:1.0"))
				{
					throw TTIMPORT.InvalidAppExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "importDoc", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			ImporterOperations _localServant = (ImporterOperations)_so.servant;
			int _result;			try
			{
			_result = _localServant.importDoc(aTicket,anAppName,aDocType,aDocDataStream,aTypeMeth,aPoolName,anIfn);
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
	public void removeDoc(java.lang.String aTicket, java.lang.String anIfn) throws TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.InvalidDSException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "removeDoc", true);
				_os.write_string(aTicket);
				_os.write_string(anIfn);
				_is = _invoke(_os);
				return;
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "removeDoc", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			ImporterOperations _localServant = (ImporterOperations)_so.servant;
			try
			{
			_localServant.removeDoc(aTicket,anIfn);
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
