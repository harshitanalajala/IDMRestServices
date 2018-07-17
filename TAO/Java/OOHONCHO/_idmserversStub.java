package OOHONCHO;

import OOHONCHO.idmserversOperations;

/**
 *	Generated from IDL interface "idmservers"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public class _idmserversStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements OOHONCHO.idmservers
{
	private String[] ids = {"IDL:OOHONCHO/idmservers:1.0"};
	@Override
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = OOHONCHO.idmserversOperations.class;
	@Override
	public int releasePOA(java.lang.String aRef) throws OOHONCHO.NoSuchPOAInstance,TTEXCEPTIONS.GenericException,OOHONCHO.NoSuchPOAtypeException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "releasePOA", true);
				_os.write_string(aRef);
				_is = _invoke(_os);
				int _result = _is.read_long();
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:OOHONCHO/NoSuchPOAInstance:1.0"))
				{
					throw OOHONCHO.NoSuchPOAInstanceHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:OOHONCHO/NoSuchPOAtypeException:1.0"))
				{
					throw OOHONCHO.NoSuchPOAtypeExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "releasePOA", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			idmserversOperations _localServant = (idmserversOperations)_so.servant;
			int _result;			try
			{
			_result = _localServant.releasePOA(aRef);
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
	public java.lang.String getPOAname(java.lang.String aPoaPostfix, org.omg.CORBA.StringHolder aRef) throws OOHONCHO.NoSuchPOAInstance,TTEXCEPTIONS.GenericException,OOHONCHO.NoSuchPOAtypeException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "getPOAname", true);
				_os.write_string(aPoaPostfix);
				_os.write_string(aRef.value);
				_is = _invoke(_os);
				java.lang.String _result = _is.read_string();
				aRef.value = _is.read_string();
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:OOHONCHO/NoSuchPOAInstance:1.0"))
				{
					throw OOHONCHO.NoSuchPOAInstanceHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:OOHONCHO/NoSuchPOAtypeException:1.0"))
				{
					throw OOHONCHO.NoSuchPOAtypeExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "getPOAname", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			idmserversOperations _localServant = (idmserversOperations)_so.servant;
			java.lang.String _result;			try
			{
			_result = _localServant.getPOAname(aPoaPostfix,aRef);
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
