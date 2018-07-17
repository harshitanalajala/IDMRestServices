package TTRETRIEVAL;

import TTRETRIEVAL.DocReaderOperations;

/**
 *	Generated from IDL interface "DocReader"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public class _DocReaderStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements TTRETRIEVAL.DocReader
{
	private String[] ids = {"IDL:TTRETRIEVAL/DocReader:1.0"};
	@Override
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = TTRETRIEVAL.DocReaderOperations.class;
	@Override
	public java.lang.String getMimeType(java.lang.String aTicket, java.lang.String aFileExtn) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "getMimeType", true);
				_os.write_string(aTicket);
				_os.write_string(aFileExtn);
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "getMimeType", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			DocReaderOperations _localServant = (DocReaderOperations)_so.servant;
			java.lang.String _result;			try
			{
			_result = _localServant.getMimeType(aTicket,aFileExtn);
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
	public void getDocumentInfo(java.lang.String aTicket, java.lang.String anIfn, java.lang.String aPages, org.omg.CORBA.StringHolder aContentType, org.omg.CORBA.StringHolder aFilePath, org.omg.CORBA.IntHolder aFileLength, org.omg.CORBA.IntHolder aChunkLength) throws TTEXCEPTIONS.GenericException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.InvalidDSException,TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.UnknownPageException,TTRETRIEVAL.BadPagesException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "getDocumentInfo", true);
				_os.write_string(aTicket);
				_os.write_string(anIfn);
				_os.write_string(aPages);
				_is = _invoke(_os);
				aContentType.value = _is.read_string();
				aFilePath.value = _is.read_string();
				aFileLength.value = _is.read_ulong();
				aChunkLength.value = _is.read_ulong();
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
				else if( _id.equals("IDL:TTEXCEPTIONS/InvalidDSException:1.0"))
				{
					throw TTEXCEPTIONS.InvalidDSExceptionHelper.read(_ax.getInputStream());
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
				else if( _id.equals("IDL:TTEXCEPTIONS/UnknownPageException:1.0"))
				{
					throw TTEXCEPTIONS.UnknownPageExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTRETRIEVAL/BadPagesException:1.0"))
				{
					throw TTRETRIEVAL.BadPagesExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "getDocumentInfo", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			DocReaderOperations _localServant = (DocReaderOperations)_so.servant;
			try
			{
			_localServant.getDocumentInfo(aTicket,anIfn,aPages,aContentType,aFilePath,aFileLength,aChunkLength);
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
	public void getRenditionDataIntoTempFile(java.lang.String aTicket, java.lang.String anIfn, int aPageNum, org.omg.CORBA.StringHolder serverTmpFilename, org.omg.CORBA.IntHolder aFileLength, org.omg.CORBA.IntHolder aChunkLength) throws TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.InvalidDSException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.UnknownPageException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "getRenditionDataIntoTempFile", true);
				_os.write_string(aTicket);
				_os.write_string(anIfn);
				_os.write_ulong(aPageNum);
				_is = _invoke(_os);
				serverTmpFilename.value = _is.read_string();
				aFileLength.value = _is.read_ulong();
				aChunkLength.value = _is.read_ulong();
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
				else if( _id.equals("IDL:TTEXCEPTIONS/UnknownPageException:1.0"))
				{
					throw TTEXCEPTIONS.UnknownPageExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "getRenditionDataIntoTempFile", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			DocReaderOperations _localServant = (DocReaderOperations)_so.servant;
			try
			{
			_localServant.getRenditionDataIntoTempFile(aTicket,anIfn,aPageNum,serverTmpFilename,aFileLength,aChunkLength);
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
	public boolean getRenditionIfn(java.lang.String aTicket, java.lang.String anInIfn, int anInPageNum, int aSubPageNum, TTRETRIEVAL.ParamDataHolder aParams, org.omg.CORBA.StringHolder anOutIfn, org.omg.CORBA.IntHolder anOutPageNum, org.omg.CORBA.IntHolder aSubPageCount, org.omg.CORBA.StringHolder aDocDosExt, org.omg.CORBA.StringHolder aDocDosExtOrig) throws TTEXCEPTIONS.GenericException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.InvalidDSException,TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTRETRIEVAL.UnknownSubpageException,TTEXCEPTIONS.UnknownPageException,TTRETRIEVAL.BadParamException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "getRenditionIfn", true);
				_os.write_string(aTicket);
				_os.write_string(anInIfn);
				_os.write_ulong(anInPageNum);
				_os.write_ulong(aSubPageNum);
				TTRETRIEVAL.ParamDataHelper.write(_os,aParams.value);
				_is = _invoke(_os);
				boolean _result = _is.read_boolean();
				aParams.value = TTRETRIEVAL.ParamDataHelper.read(_is);
				anOutIfn.value = _is.read_string();
				anOutPageNum.value = _is.read_ulong();
				aSubPageCount.value = _is.read_ulong();
				aDocDosExt.value = _is.read_string();
				aDocDosExtOrig.value = _is.read_string();
				return _result;
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
				else if( _id.equals("IDL:TTEXCEPTIONS/InvalidDSException:1.0"))
				{
					throw TTEXCEPTIONS.InvalidDSExceptionHelper.read(_ax.getInputStream());
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
				else if( _id.equals("IDL:TTRETRIEVAL/UnknownSubpageException:1.0"))
				{
					throw TTRETRIEVAL.UnknownSubpageExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/UnknownPageException:1.0"))
				{
					throw TTEXCEPTIONS.UnknownPageExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTRETRIEVAL/BadParamException:1.0"))
				{
					throw TTRETRIEVAL.BadParamExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "getRenditionIfn", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			DocReaderOperations _localServant = (DocReaderOperations)_so.servant;
			boolean _result;			try
			{
			_result = _localServant.getRenditionIfn(aTicket,anInIfn,anInPageNum,aSubPageNum,aParams,anOutIfn,anOutPageNum,aSubPageCount,aDocDosExt,aDocDosExtOrig);
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
	public void getRenditionTypes(java.lang.String aTicket, java.lang.String anIfn, int aPageNum, TTRETRIEVAL.RenditionTypeDataHolder aRenditionTypes) throws TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.InvalidDSException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.UnknownPageException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "getRenditionTypes", true);
				_os.write_string(aTicket);
				_os.write_string(anIfn);
				_os.write_ulong(aPageNum);
				_is = _invoke(_os);
				aRenditionTypes.value = TTRETRIEVAL.RenditionTypeDataHelper.read(_is);
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
				else if( _id.equals("IDL:TTEXCEPTIONS/UnknownPageException:1.0"))
				{
					throw TTEXCEPTIONS.UnknownPageExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "getRenditionTypes", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			DocReaderOperations _localServant = (DocReaderOperations)_so.servant;
			try
			{
			_localServant.getRenditionTypes(aTicket,anIfn,aPageNum,aRenditionTypes);
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
	public void closeDocument(java.lang.String aFilePath) throws TTRETRIEVAL.BadFilePathException,TTEXCEPTIONS.GenericException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "closeDocument", true);
				_os.write_string(aFilePath);
				_is = _invoke(_os);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTRETRIEVAL/BadFilePathException:1.0"))
				{
					throw TTRETRIEVAL.BadFilePathExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "closeDocument", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			DocReaderOperations _localServant = (DocReaderOperations)_so.servant;
			try
			{
			_localServant.closeDocument(aFilePath);
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
	public byte[] getRenditionData(java.lang.String aTicket, java.lang.String anIfn, int aPageNum) throws TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.InvalidDSException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.UnknownPageException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "getRenditionData", true);
				_os.write_string(aTicket);
				_os.write_string(anIfn);
				_os.write_ulong(aPageNum);
				_is = _invoke(_os);
				byte[] _result = TTRETRIEVAL.ImageDataHelper.read(_is);
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
				else if( _id.equals("IDL:TTEXCEPTIONS/UnknownPageException:1.0"))
				{
					throw TTEXCEPTIONS.UnknownPageExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "getRenditionData", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			DocReaderOperations _localServant = (DocReaderOperations)_so.servant;
			byte[] _result;			try
			{
			_result = _localServant.getRenditionData(aTicket,anIfn,aPageNum);
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
	public byte[] getDocumentChunk(java.lang.String aFilePath, int aLength, int anOffset) throws TTRETRIEVAL.BadFilePathException,TTEXCEPTIONS.GenericException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "getDocumentChunk", true);
				_os.write_string(aFilePath);
				_os.write_ulong(aLength);
				_os.write_ulong(anOffset);
				_is = _invoke(_os);
				byte[] _result = TTRETRIEVAL.ImageDataHelper.read(_is);
				return _result;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				if( _id.equals("IDL:TTRETRIEVAL/BadFilePathException:1.0"))
				{
					throw TTRETRIEVAL.BadFilePathExceptionHelper.read(_ax.getInputStream());
				}
				else if( _id.equals("IDL:TTEXCEPTIONS/GenericException:1.0"))
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "getDocumentChunk", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			DocReaderOperations _localServant = (DocReaderOperations)_so.servant;
			byte[] _result;			try
			{
			_result = _localServant.getDocumentChunk(aFilePath,aLength,anOffset);
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
