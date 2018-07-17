package TTQUEUE;

import TTQUEUE.QueueOperations;

/**
 *	Generated from IDL interface "Queue"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public class _QueueStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements TTQUEUE.Queue
{
	private String[] ids = {"IDL:TTQUEUE/Queue:1.0"};
	@Override
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = TTQUEUE.QueueOperations.class;
	@Override
	public TTQUEUE.CQueueEntry lockEntry(java.lang.String aTicket, java.lang.String aQServer, java.lang.String aQServiceName, int aQueueId, int aEntryId) throws TTQUEUE.MovedException,TTQUEUE.QueueException,TTEXCEPTIONS.AuthenticationException,TTQUEUE.LockFailureException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "lockEntry", true);
				_os.write_string(aTicket);
				_os.write_string(aQServer);
				_os.write_string(aQServiceName);
				_os.write_ulong(aQueueId);
				_os.write_ulong(aEntryId);
				_is = _invoke(_os);
				TTQUEUE.CQueueEntry _result = TTQUEUE.CQueueEntryHelper.read(_is);
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTQUEUE/MovedException:1.0"))
				{
					throw TTQUEUE.MovedExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/QueueException:1.0"))
				{
					throw TTQUEUE.QueueExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/LockFailureException:1.0"))
				{
					throw TTQUEUE.LockFailureExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "lockEntry", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			QueueOperations _localServant = (QueueOperations)_so.servant;
			TTQUEUE.CQueueEntry _result;			try
			{
			_result = _localServant.lockEntry(aTicket,aQServer,aQServiceName,aQueueId,aEntryId);
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
	public TTQUEUE.CSearchResult searchEntryRange(java.lang.String aTicket, java.lang.String aQServer, java.lang.String aQServiceName, int aQueueId, java.lang.String[] aVarNames, java.lang.String aWhere, java.lang.String aOrderBy, org.omg.CORBA.IntHolder aOffset, int aRangeSize, org.omg.CORBA.IntHolder aTotalNumMatched) throws TTQUEUE.QueueException,TTQUEUE.BadSelectionIdException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTQUEUE.BadOffsetException,TTEXCEPTIONS.TTlibException,TTQUEUE.BadFieldNamesException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "searchEntryRange", true);
				_os.write_string(aTicket);
				_os.write_string(aQServer);
				_os.write_string(aQServiceName);
				_os.write_ulong(aQueueId);
				TTQUEUE.CStringSeqHelper.write(_os,aVarNames);
				_os.write_string(aWhere);
				_os.write_string(aOrderBy);
				_os.write_long(aOffset.value);
				_os.write_ulong(aRangeSize);
				_is = _invoke(_os);
				TTQUEUE.CSearchResult _result = TTQUEUE.CSearchResultHelper.read(_is);
				aOffset.value = _is.read_long();
				aTotalNumMatched.value = _is.read_ulong();
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTQUEUE/QueueException:1.0"))
				{
					throw TTQUEUE.QueueExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/BadSelectionIdException:1.0"))
				{
					throw TTQUEUE.BadSelectionIdExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/BadOffsetException:1.0"))
				{
					throw TTQUEUE.BadOffsetExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/TTlibException:1.0"))
				{
					throw TTEXCEPTIONS.TTlibExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/BadFieldNamesException:1.0"))
				{
					throw TTQUEUE.BadFieldNamesExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "searchEntryRange", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			QueueOperations _localServant = (QueueOperations)_so.servant;
			TTQUEUE.CSearchResult _result;			try
			{
			_result = _localServant.searchEntryRange(aTicket,aQServer,aQServiceName,aQueueId,aVarNames,aWhere,aOrderBy,aOffset,aRangeSize,aTotalNumMatched);
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
	public void unregisterSession(java.lang.String ticket) throws TTEXCEPTIONS.GenericException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "unregisterSession", true);
				_os.write_string(ticket);
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "unregisterSession", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			QueueOperations _localServant = (QueueOperations)_so.servant;
			try
			{
			_localServant.unregisterSession(ticket);
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
	public java.lang.String selectQueueInfo(java.lang.String aTicket, java.lang.String aQServer, java.lang.String aQServiceName, java.lang.String[] aPropNames, java.lang.String aWhere, java.lang.String aOrderBy, org.omg.CORBA.IntHolder aNumSelected) throws TTQUEUE.QueueException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException,TTQUEUE.BadWhereException,TTQUEUE.BadFieldNamesException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "selectQueueInfo", true);
				_os.write_string(aTicket);
				_os.write_string(aQServer);
				_os.write_string(aQServiceName);
				TTQUEUE.CStringSeqHelper.write(_os,aPropNames);
				_os.write_string(aWhere);
				_os.write_string(aOrderBy);
				_is = _invoke(_os);
				java.lang.String _result = _is.read_string();
				aNumSelected.value = _is.read_ulong();
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTQUEUE/QueueException:1.0"))
				{
					throw TTQUEUE.QueueExceptionHelper.read(_ax.getInputStream());
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
				else if( _id.equals("IDL:TTQUEUE/BadWhereException:1.0"))
				{
					throw TTQUEUE.BadWhereExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/BadFieldNamesException:1.0"))
				{
					throw TTQUEUE.BadFieldNamesExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "selectQueueInfo", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			QueueOperations _localServant = (QueueOperations)_so.servant;
			java.lang.String _result;			try
			{
			_result = _localServant.selectQueueInfo(aTicket,aQServer,aQServiceName,aPropNames,aWhere,aOrderBy,aNumSelected);
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
	public void unlockEntry(java.lang.String aTicket, java.lang.String aQServer, java.lang.String aQServiceName, int aEntryId) throws TTQUEUE.QueueException,TTQUEUE.NotLockedException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "unlockEntry", true);
				_os.write_string(aTicket);
				_os.write_string(aQServer);
				_os.write_string(aQServiceName);
				_os.write_ulong(aEntryId);
				_is = _invoke(_os);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTQUEUE/QueueException:1.0"))
				{
					throw TTQUEUE.QueueExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/NotLockedException:1.0"))
				{
					throw TTQUEUE.NotLockedExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "unlockEntry", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			QueueOperations _localServant = (QueueOperations)_so.servant;
			try
			{
			_localServant.unlockEntry(aTicket,aQServer,aQServiceName,aEntryId);
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
	public void updateEntry(java.lang.String aTicket, java.lang.String aQServer, java.lang.String aQServiceName, int aEntryId, TTQUEUE.CNameValue[] aEntryVariables) throws TTQUEUE.QueueException,TTQUEUE.NotLockedException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTQUEUE.BadNameValueException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "updateEntry", true);
				_os.write_string(aTicket);
				_os.write_string(aQServer);
				_os.write_string(aQServiceName);
				_os.write_ulong(aEntryId);
				TTQUEUE.CNameValueSeqHelper.write(_os,aEntryVariables);
				_is = _invoke(_os);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTQUEUE/QueueException:1.0"))
				{
					throw TTQUEUE.QueueExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/NotLockedException:1.0"))
				{
					throw TTQUEUE.NotLockedExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/BadNameValueException:1.0"))
				{
					throw TTQUEUE.BadNameValueExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "updateEntry", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			QueueOperations _localServant = (QueueOperations)_so.servant;
			try
			{
			_localServant.updateEntry(aTicket,aQServer,aQServiceName,aEntryId,aEntryVariables);
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
	public TTQUEUE.CQueueEntry[] getSelectedEntries(java.lang.String aTicket, java.lang.String aQServer, java.lang.String aQServiceName, java.lang.String aSelectionId, int aOffset, int aMaxEntries) throws TTQUEUE.QueueException,TTQUEUE.BadSelectionIdException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTQUEUE.BadOffsetException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "getSelectedEntries", true);
				_os.write_string(aTicket);
				_os.write_string(aQServer);
				_os.write_string(aQServiceName);
				_os.write_string(aSelectionId);
				_os.write_ulong(aOffset);
				_os.write_ulong(aMaxEntries);
				_is = _invoke(_os);
				TTQUEUE.CQueueEntry[] _result = TTQUEUE.CQueueEntrySeqHelper.read(_is);
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTQUEUE/QueueException:1.0"))
				{
					throw TTQUEUE.QueueExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/BadSelectionIdException:1.0"))
				{
					throw TTQUEUE.BadSelectionIdExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/BadOffsetException:1.0"))
				{
					throw TTQUEUE.BadOffsetExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "getSelectedEntries", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			QueueOperations _localServant = (QueueOperations)_so.servant;
			TTQUEUE.CQueueEntry[] _result;			try
			{
			_result = _localServant.getSelectedEntries(aTicket,aQServer,aQServiceName,aSelectionId,aOffset,aMaxEntries);
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
	public TTQUEUE.CQueueEntry lockNextEntry(java.lang.String aTicket, java.lang.String aQServer, java.lang.String aQServiceName, int[] aQueueIds) throws TTQUEUE.QueueException,TTEXCEPTIONS.AuthenticationException,TTQUEUE.LockFailureException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "lockNextEntry", true);
				_os.write_string(aTicket);
				_os.write_string(aQServer);
				_os.write_string(aQServiceName);
				TTQUEUE.CULongSeqHelper.write(_os,aQueueIds);
				_is = _invoke(_os);
				TTQUEUE.CQueueEntry _result = TTQUEUE.CQueueEntryHelper.read(_is);
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTQUEUE/QueueException:1.0"))
				{
					throw TTQUEUE.QueueExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/LockFailureException:1.0"))
				{
					throw TTQUEUE.LockFailureExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "lockNextEntry", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			QueueOperations _localServant = (QueueOperations)_so.servant;
			TTQUEUE.CQueueEntry _result;			try
			{
			_result = _localServant.lockNextEntry(aTicket,aQServer,aQServiceName,aQueueIds);
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
	public java.lang.String selectEntries(java.lang.String aTicket, java.lang.String aQServer, java.lang.String aQServiceName, int aQueueId, java.lang.String[] aVarNames, java.lang.String aWhere, java.lang.String aOrderBy, org.omg.CORBA.IntHolder aNumSelected) throws TTQUEUE.QueueException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException,TTQUEUE.BadFieldNamesException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "selectEntries", true);
				_os.write_string(aTicket);
				_os.write_string(aQServer);
				_os.write_string(aQServiceName);
				_os.write_ulong(aQueueId);
				TTQUEUE.CStringSeqHelper.write(_os,aVarNames);
				_os.write_string(aWhere);
				_os.write_string(aOrderBy);
				_is = _invoke(_os);
				java.lang.String _result = _is.read_string();
				aNumSelected.value = _is.read_ulong();
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTQUEUE/QueueException:1.0"))
				{
					throw TTQUEUE.QueueExceptionHelper.read(_ax.getInputStream());
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
				else if( _id.equals("IDL:TTQUEUE/BadFieldNamesException:1.0"))
				{
					throw TTQUEUE.BadFieldNamesExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "selectEntries", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			QueueOperations _localServant = (QueueOperations)_so.servant;
			java.lang.String _result;			try
			{
			_result = _localServant.selectEntries(aTicket,aQServer,aQServiceName,aQueueId,aVarNames,aWhere,aOrderBy,aNumSelected);
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
	public TTQUEUE.CSearchResult getSelectedResults(java.lang.String aTicket, java.lang.String aQServer, java.lang.String aQServiceName, java.lang.String aSelectionId, int aOffset, int aMaxEntries) throws TTQUEUE.QueueException,TTQUEUE.BadSelectionIdException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTQUEUE.BadOffsetException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "getSelectedResults", true);
				_os.write_string(aTicket);
				_os.write_string(aQServer);
				_os.write_string(aQServiceName);
				_os.write_string(aSelectionId);
				_os.write_ulong(aOffset);
				_os.write_ulong(aMaxEntries);
				_is = _invoke(_os);
				TTQUEUE.CSearchResult _result = TTQUEUE.CSearchResultHelper.read(_is);
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTQUEUE/QueueException:1.0"))
				{
					throw TTQUEUE.QueueExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/BadSelectionIdException:1.0"))
				{
					throw TTQUEUE.BadSelectionIdExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/BadOffsetException:1.0"))
				{
					throw TTQUEUE.BadOffsetExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "getSelectedResults", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			QueueOperations _localServant = (QueueOperations)_so.servant;
			TTQUEUE.CSearchResult _result;			try
			{
			_result = _localServant.getSelectedResults(aTicket,aQServer,aQServiceName,aSelectionId,aOffset,aMaxEntries);
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
	public void registerSession(java.lang.String ticket) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "registerSession", true);
				_os.write_string(ticket);
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "registerSession", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			QueueOperations _localServant = (QueueOperations)_so.servant;
			try
			{
			_localServant.registerSession(ticket);
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
	public void releaseEntry(java.lang.String aTicket, java.lang.String aQServer, java.lang.String aQServiceName, int aEntryId, TTQUEUE.CNameValue[] aEntryVariables) throws TTQUEUE.QueueException,TTQUEUE.NotLockedException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTQUEUE.BadNameValueException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "releaseEntry", true);
				_os.write_string(aTicket);
				_os.write_string(aQServer);
				_os.write_string(aQServiceName);
				_os.write_ulong(aEntryId);
				TTQUEUE.CNameValueSeqHelper.write(_os,aEntryVariables);
				_is = _invoke(_os);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTQUEUE/QueueException:1.0"))
				{
					throw TTQUEUE.QueueExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/NotLockedException:1.0"))
				{
					throw TTQUEUE.NotLockedExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/BadNameValueException:1.0"))
				{
					throw TTQUEUE.BadNameValueExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "releaseEntry", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			QueueOperations _localServant = (QueueOperations)_so.servant;
			try
			{
			_localServant.releaseEntry(aTicket,aQServer,aQServiceName,aEntryId,aEntryVariables);
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
	public TTQUEUE.CSearchResult searchEntries(java.lang.String aTicket, java.lang.String aQServer, java.lang.String aQServiceName, int aQueueId, java.lang.String[] aVarNames, java.lang.String aWhere, java.lang.String aOrderBy, int aOffset, int aMaxRows, org.omg.CORBA.IntHolder aNumMatched) throws TTQUEUE.QueueException,TTQUEUE.BadSelectionIdException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTQUEUE.BadOffsetException,TTEXCEPTIONS.TTlibException,TTQUEUE.BadFieldNamesException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "searchEntries", true);
				_os.write_string(aTicket);
				_os.write_string(aQServer);
				_os.write_string(aQServiceName);
				_os.write_ulong(aQueueId);
				TTQUEUE.CStringSeqHelper.write(_os,aVarNames);
				_os.write_string(aWhere);
				_os.write_string(aOrderBy);
				_os.write_ulong(aOffset);
				_os.write_ulong(aMaxRows);
				_is = _invoke(_os);
				TTQUEUE.CSearchResult _result = TTQUEUE.CSearchResultHelper.read(_is);
				aNumMatched.value = _is.read_ulong();
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTQUEUE/QueueException:1.0"))
				{
					throw TTQUEUE.QueueExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/BadSelectionIdException:1.0"))
				{
					throw TTQUEUE.BadSelectionIdExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/BadOffsetException:1.0"))
				{
					throw TTQUEUE.BadOffsetExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/TTlibException:1.0"))
				{
					throw TTEXCEPTIONS.TTlibExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/BadFieldNamesException:1.0"))
				{
					throw TTQUEUE.BadFieldNamesExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "searchEntries", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			QueueOperations _localServant = (QueueOperations)_so.servant;
			TTQUEUE.CSearchResult _result;			try
			{
			_result = _localServant.searchEntries(aTicket,aQServer,aQServiceName,aQueueId,aVarNames,aWhere,aOrderBy,aOffset,aMaxRows,aNumMatched);
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
	public TTQUEUE.CQueueInfo getQueueInfo(java.lang.String aTicket, java.lang.String aQServer, java.lang.String aQServiceName, int aQueueId) throws TTQUEUE.QueueException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "getQueueInfo", true);
				_os.write_string(aTicket);
				_os.write_string(aQServer);
				_os.write_string(aQServiceName);
				_os.write_ulong(aQueueId);
				_is = _invoke(_os);
				TTQUEUE.CQueueInfo _result = TTQUEUE.CQueueInfoHelper.read(_is);
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTQUEUE/QueueException:1.0"))
				{
					throw TTQUEUE.QueueExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "getQueueInfo", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			QueueOperations _localServant = (QueueOperations)_so.servant;
			TTQUEUE.CQueueInfo _result;			try
			{
			_result = _localServant.getQueueInfo(aTicket,aQServer,aQServiceName,aQueueId);
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
	public int addEntry(java.lang.String aTicket, java.lang.String aQServer, java.lang.String aQServiceName, int aQueueId, TTQUEUE.CNameValue[] aEntryVariables) throws TTQUEUE.QueueException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTQUEUE.BadNameValueException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "addEntry", true);
				_os.write_string(aTicket);
				_os.write_string(aQServer);
				_os.write_string(aQServiceName);
				_os.write_ulong(aQueueId);
				TTQUEUE.CNameValueSeqHelper.write(_os,aEntryVariables);
				_is = _invoke(_os);
				int _result = _is.read_ulong();
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTQUEUE/QueueException:1.0"))
				{
					throw TTQUEUE.QueueExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/BadNameValueException:1.0"))
				{
					throw TTQUEUE.BadNameValueExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "addEntry", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			QueueOperations _localServant = (QueueOperations)_so.servant;
			int _result;			try
			{
			_result = _localServant.addEntry(aTicket,aQServer,aQServiceName,aQueueId,aEntryVariables);
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
	public TTQUEUE.CQueueInfo[] getSelectedQueueInfo(java.lang.String aTicket, java.lang.String aQServer, java.lang.String aQServiceName, java.lang.String aSelectionId, int aOffset, int aMaxQueues) throws TTQUEUE.QueueException,TTQUEUE.BadSelectionIdException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTQUEUE.BadOffsetException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "getSelectedQueueInfo", true);
				_os.write_string(aTicket);
				_os.write_string(aQServer);
				_os.write_string(aQServiceName);
				_os.write_string(aSelectionId);
				_os.write_ulong(aOffset);
				_os.write_ulong(aMaxQueues);
				_is = _invoke(_os);
				TTQUEUE.CQueueInfo[] _result = TTQUEUE.CQueueInfoSeqHelper.read(_is);
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTQUEUE/QueueException:1.0"))
				{
					throw TTQUEUE.QueueExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/BadSelectionIdException:1.0"))
				{
					throw TTQUEUE.BadSelectionIdExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/BadOffsetException:1.0"))
				{
					throw TTQUEUE.BadOffsetExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "getSelectedQueueInfo", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			QueueOperations _localServant = (QueueOperations)_so.servant;
			TTQUEUE.CQueueInfo[] _result;			try
			{
			_result = _localServant.getSelectedQueueInfo(aTicket,aQServer,aQServiceName,aSelectionId,aOffset,aMaxQueues);
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
	public void freeSelectedResults(java.lang.String aTicket, java.lang.String aQServer, java.lang.String aQServiceName, java.lang.String aSelectionId) throws TTQUEUE.QueueException,TTQUEUE.BadSelectionIdException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "freeSelectedResults", true);
				_os.write_string(aTicket);
				_os.write_string(aQServer);
				_os.write_string(aQServiceName);
				_os.write_string(aSelectionId);
				_is = _invoke(_os);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTQUEUE/QueueException:1.0"))
				{
					throw TTQUEUE.QueueExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/BadSelectionIdException:1.0"))
				{
					throw TTQUEUE.BadSelectionIdExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "freeSelectedResults", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			QueueOperations _localServant = (QueueOperations)_so.servant;
			try
			{
			_localServant.freeSelectedResults(aTicket,aQServer,aQServiceName,aSelectionId);
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
	public TTQUEUE.CSearchResult searchQueueInfo(java.lang.String aTicket, java.lang.String aQServer, java.lang.String aQServiceName, java.lang.String[] aPropNames, java.lang.String aWhere, java.lang.String aOrderBy, int aOffset, int aMaxRows, org.omg.CORBA.IntHolder aNumMatched) throws TTQUEUE.QueueException,TTQUEUE.BadSelectionIdException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTQUEUE.BadOffsetException,TTEXCEPTIONS.TTlibException,TTQUEUE.BadWhereException,TTQUEUE.BadFieldNamesException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "searchQueueInfo", true);
				_os.write_string(aTicket);
				_os.write_string(aQServer);
				_os.write_string(aQServiceName);
				TTQUEUE.CStringSeqHelper.write(_os,aPropNames);
				_os.write_string(aWhere);
				_os.write_string(aOrderBy);
				_os.write_ulong(aOffset);
				_os.write_ulong(aMaxRows);
				_is = _invoke(_os);
				TTQUEUE.CSearchResult _result = TTQUEUE.CSearchResultHelper.read(_is);
				aNumMatched.value = _is.read_ulong();
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTQUEUE/QueueException:1.0"))
				{
					throw TTQUEUE.QueueExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/BadSelectionIdException:1.0"))
				{
					throw TTQUEUE.BadSelectionIdExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/AuthenticationException:1.0"))
				{
					throw TTEXCEPTIONS.AuthenticationExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
				{
					throw TTEXCEPTIONS.GenericExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/BadOffsetException:1.0"))
				{
					throw TTQUEUE.BadOffsetExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/TTlibException:1.0"))
				{
					throw TTEXCEPTIONS.TTlibExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/BadWhereException:1.0"))
				{
					throw TTQUEUE.BadWhereExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTQUEUE/BadFieldNamesException:1.0"))
				{
					throw TTQUEUE.BadFieldNamesExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "searchQueueInfo", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			QueueOperations _localServant = (QueueOperations)_so.servant;
			TTQUEUE.CSearchResult _result;			try
			{
			_result = _localServant.searchQueueInfo(aTicket,aQServer,aQServiceName,aPropNames,aWhere,aOrderBy,aOffset,aMaxRows,aNumMatched);
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
