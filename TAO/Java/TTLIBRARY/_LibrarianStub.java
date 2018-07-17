package TTLIBRARY;

import TTLIBRARY.LibrarianOperations;

/**
 *	Generated from IDL interface "Librarian"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public class _LibrarianStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements TTLIBRARY.Librarian
{
	private String[] ids = {"IDL:TTLIBRARY/Librarian:1.0"};
	@Override
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = TTLIBRARY.LibrarianOperations.class;
	@Override
	public void createRevision(java.lang.String aTicket, java.lang.String aAppName, TTLIBRARY.NameValue[] aFields, java.lang.String aStatus, java.lang.String aContentType, byte[] aContent, java.lang.String aDiskset, java.lang.String aPoolName, org.omg.CORBA.StringHolder aIfn, org.omg.CORBA.IntHolder aNumPages) throws TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.InvalidDSException,TTLIBRARY.NotLibAppException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTLIBRARY.DatabaseException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "createRevision", true);
				_os.write_string(aTicket);
				_os.write_string(aAppName);
				TTLIBRARY.NameValueSeqHelper.write(_os,aFields);
				_os.write_string(aStatus);
				_os.write_string(aContentType);
				TTLIBRARY.ContentHelper.write(_os,aContent);
				_os.write_string(aDiskset);
				_os.write_string(aPoolName);
				_is = _invoke(_os);
				aIfn.value = _is.read_string();
				aNumPages.value = _is.read_ulong();
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
				else if( _id.equals("IDL:TTLIBRARY/NotLibAppException:1.0"))
				{
					throw TTLIBRARY.NotLibAppExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/BadDocIdException:1.0"))
				{
					throw TTEXCEPTIONS.BadDocIdExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTLIBRARY/DatabaseException:1.0"))
				{
					throw TTLIBRARY.DatabaseExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "createRevision", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			LibrarianOperations _localServant = (LibrarianOperations)_so.servant;
			try
			{
			_localServant.createRevision(aTicket,aAppName,aFields,aStatus,aContentType,aContent,aDiskset,aPoolName,aIfn,aNumPages);
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
	public void checkInRevision(java.lang.String aTicket, java.lang.String aAppName, org.omg.CORBA.StringHolder aIfn, java.lang.String aStatus, java.lang.String aContentType, byte[] aContent, org.omg.CORBA.IntHolder aNumPages) throws TTEXCEPTIONS.GenericException,TTEXCEPTIONS.BadDocIdException,TTLIBRARY.NotOwnerException,TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTLIBRARY.NotLibAppException,TTLIBRARY.NotCheckedOutException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "checkInRevision", true);
				_os.write_string(aTicket);
				_os.write_string(aAppName);
				_os.write_string(aIfn.value);
				_os.write_string(aStatus);
				_os.write_string(aContentType);
				TTLIBRARY.ContentHelper.write(_os,aContent);
				_is = _invoke(_os);
				aIfn.value = _is.read_string();
				aNumPages.value = _is.read_ulong();
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
				else if( _id.equals("IDL:TTEXCEPTIONS/BadDocIdException:1.0"))
				{
					throw TTEXCEPTIONS.BadDocIdExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTLIBRARY/NotOwnerException:1.0"))
				{
					throw TTLIBRARY.NotOwnerExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/DocPermException:1.0"))
				{
					throw TTEXCEPTIONS.DocPermExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/UnknownDocException:1.0"))
				{
					throw TTEXCEPTIONS.UnknownDocExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTLIBRARY/NotLibAppException:1.0"))
				{
					throw TTLIBRARY.NotLibAppExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTLIBRARY/NotCheckedOutException:1.0"))
				{
					throw TTLIBRARY.NotCheckedOutExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "checkInRevision", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			LibrarianOperations _localServant = (LibrarianOperations)_so.servant;
			try
			{
			_localServant.checkInRevision(aTicket,aAppName,aIfn,aStatus,aContentType,aContent,aNumPages);
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
	public void cancelCheckOut(java.lang.String aTicket, java.lang.String aAppName, java.lang.String aIfn) throws TTEXCEPTIONS.GenericException,TTEXCEPTIONS.BadDocIdException,TTLIBRARY.NotOwnerException,TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTLIBRARY.NotLibAppException,TTLIBRARY.NotCheckedOutException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "cancelCheckOut", true);
				_os.write_string(aTicket);
				_os.write_string(aAppName);
				_os.write_string(aIfn);
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
				else if( _id.equals("IDL:TTEXCEPTIONS/BadDocIdException:1.0"))
				{
					throw TTEXCEPTIONS.BadDocIdExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTLIBRARY/NotOwnerException:1.0"))
				{
					throw TTLIBRARY.NotOwnerExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/DocPermException:1.0"))
				{
					throw TTEXCEPTIONS.DocPermExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/UnknownDocException:1.0"))
				{
					throw TTEXCEPTIONS.UnknownDocExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTLIBRARY/NotLibAppException:1.0"))
				{
					throw TTLIBRARY.NotLibAppExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTLIBRARY/NotCheckedOutException:1.0"))
				{
					throw TTLIBRARY.NotCheckedOutExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "cancelCheckOut", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			LibrarianOperations _localServant = (LibrarianOperations)_so.servant;
			try
			{
			_localServant.cancelCheckOut(aTicket,aAppName,aIfn);
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
	public TTLIBRARY.CheckOutDetail getCheckOutDetail(java.lang.String aTicket, java.lang.String aAppName, java.lang.String aIfn) throws TTEXCEPTIONS.DocPermException,TTLIBRARY.NotLibAppException,TTEXCEPTIONS.BadDocIdException,TTLIBRARY.NotCheckedOutException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "getCheckOutDetail", true);
				_os.write_string(aTicket);
				_os.write_string(aAppName);
				_os.write_string(aIfn);
				_is = _invoke(_os);
				TTLIBRARY.CheckOutDetail _result = TTLIBRARY.CheckOutDetailHelper.read(_is);
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
				else if( _id.equals("IDL:TTLIBRARY/NotLibAppException:1.0"))
				{
					throw TTLIBRARY.NotLibAppExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/BadDocIdException:1.0"))
				{
					throw TTEXCEPTIONS.BadDocIdExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTLIBRARY/NotCheckedOutException:1.0"))
				{
					throw TTLIBRARY.NotCheckedOutExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "getCheckOutDetail", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			LibrarianOperations _localServant = (LibrarianOperations)_so.servant;
			TTLIBRARY.CheckOutDetail _result;			try
			{
			_result = _localServant.getCheckOutDetail(aTicket,aAppName,aIfn);
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
	public void listCheckedOutRevisionPermission(java.lang.String aTicket, java.lang.String aAppName, boolean aAll) throws TTLIBRARY.NotLibAppException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.AppPermException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "listCheckedOutRevisionPermission", true);
				_os.write_string(aTicket);
				_os.write_string(aAppName);
				_os.write_boolean(aAll);
				_is = _invoke(_os);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTLIBRARY/NotLibAppException:1.0"))
				{
					throw TTLIBRARY.NotLibAppExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/AppPermException:1.0"))
				{
					throw TTEXCEPTIONS.AppPermExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "listCheckedOutRevisionPermission", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			LibrarianOperations _localServant = (LibrarianOperations)_so.servant;
			try
			{
			_localServant.listCheckedOutRevisionPermission(aTicket,aAppName,aAll);
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
	public java.lang.String searchRevisionHistoryPermission(java.lang.String aTicket, java.lang.String aAppName, java.lang.String aIfn) throws TTEXCEPTIONS.DocPermException,TTLIBRARY.NotLibAppException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "searchRevisionHistoryPermission", true);
				_os.write_string(aTicket);
				_os.write_string(aAppName);
				_os.write_string(aIfn);
				_is = _invoke(_os);
				java.lang.String _result = _is.read_string();
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
				else if( _id.equals("IDL:TTLIBRARY/NotLibAppException:1.0"))
				{
					throw TTLIBRARY.NotLibAppExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "searchRevisionHistoryPermission", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			LibrarianOperations _localServant = (LibrarianOperations)_so.servant;
			java.lang.String _result;			try
			{
			_result = _localServant.searchRevisionHistoryPermission(aTicket,aAppName,aIfn);
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
	public void checkOutRevision(java.lang.String aTicket, java.lang.String aAppName, java.lang.String aIfn, java.lang.String aComment, TTLIBRARY.ContentHolder aContent, org.omg.CORBA.StringHolder aContentType) throws TTEXCEPTIONS.DocPermException,TTLIBRARY.NotLibAppException,TTEXCEPTIONS.BadDocIdException,TTLIBRARY.CheckedOutException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "checkOutRevision", true);
				_os.write_string(aTicket);
				_os.write_string(aAppName);
				_os.write_string(aIfn);
				_os.write_string(aComment);
				_is = _invoke(_os);
				aContent.value = TTLIBRARY.ContentHelper.read(_is);
				aContentType.value = _is.read_string();
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
				else if( _id.equals("IDL:TTLIBRARY/NotLibAppException:1.0"))
				{
					throw TTLIBRARY.NotLibAppExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/BadDocIdException:1.0"))
				{
					throw TTEXCEPTIONS.BadDocIdExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTLIBRARY/CheckedOutException:1.0"))
				{
					throw TTLIBRARY.CheckedOutExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "checkOutRevision", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			LibrarianOperations _localServant = (LibrarianOperations)_so.servant;
			try
			{
			_localServant.checkOutRevision(aTicket,aAppName,aIfn,aComment,aContent,aContentType);
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
	public void publishRevision(java.lang.String aTicket, java.lang.String aAppName, org.omg.CORBA.StringHolder aIfn, org.omg.CORBA.IntHolder aNumPages) throws TTEXCEPTIONS.DocPermException,TTLIBRARY.NotLibAppException,TTEXCEPTIONS.BadDocIdException,TTLIBRARY.CheckedOutException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "publishRevision", true);
				_os.write_string(aTicket);
				_os.write_string(aAppName);
				_os.write_string(aIfn.value);
				_is = _invoke(_os);
				aIfn.value = _is.read_string();
				aNumPages.value = _is.read_ulong();
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
				else if( _id.equals("IDL:TTLIBRARY/NotLibAppException:1.0"))
				{
					throw TTLIBRARY.NotLibAppExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/BadDocIdException:1.0"))
				{
					throw TTEXCEPTIONS.BadDocIdExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTLIBRARY/CheckedOutException:1.0"))
				{
					throw TTLIBRARY.CheckedOutExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "publishRevision", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			LibrarianOperations _localServant = (LibrarianOperations)_so.servant;
			try
			{
			_localServant.publishRevision(aTicket,aAppName,aIfn,aNumPages);
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
