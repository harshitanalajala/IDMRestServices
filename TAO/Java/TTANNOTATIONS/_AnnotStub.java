package TTANNOTATIONS;

import TTANNOTATIONS.AnnotOperations;

/**
 *	Generated from IDL interface "Annot"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */

public class _AnnotStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements TTANNOTATIONS.Annot
{
	private String[] ids = {"IDL:TTANNOTATIONS/Annot:1.0"};
	@Override
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = TTANNOTATIONS.AnnotOperations.class;
	@Override
	public void getAnnotations(java.lang.String aTicket, java.lang.String anIfn, int aPageNo, java.lang.String aSubpageNos, TTANNOTATIONS.PageAnnotationsHolder aPageAnnotations) throws TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.InvalidDSException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.UnknownPageException,TTEXCEPTIONS.TTlibException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "getAnnotations", true);
				_os.write_string(aTicket);
				_os.write_string(anIfn);
				_os.write_ulong(aPageNo);
				_os.write_string(aSubpageNos);
				_is = _invoke(_os);
				aPageAnnotations.value = TTANNOTATIONS.PageAnnotationsHelper.read(_is);
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "getAnnotations", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			AnnotOperations _localServant = (AnnotOperations)_so.servant;
			try
			{
			_localServant.getAnnotations(aTicket,anIfn,aPageNo,aSubpageNos,aPageAnnotations);
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
	public void setAnnotations(java.lang.String aTicket, boolean aForceWrite, TTANNOTATIONS.PageAnnotationsHolder aPageAnnotations) throws TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.InvalidDSException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException,TTANNOTATIONS.AnnotationException
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "setAnnotations", true);
				_os.write_string(aTicket);
				_os.write_boolean(aForceWrite);
				TTANNOTATIONS.PageAnnotationsHelper.write(_os,aPageAnnotations.value);
				_is = _invoke(_os);
				aPageAnnotations.value = TTANNOTATIONS.PageAnnotationsHelper.read(_is);
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
				else if( _id.equals("IDL:TTANNOTATIONS/AnnotationException:1.0"))
				{
					throw TTANNOTATIONS.AnnotationExceptionHelper.read(_ax.getInputStream());
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "setAnnotations", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			AnnotOperations _localServant = (AnnotOperations)_so.servant;
			try
			{
			_localServant.setAnnotations(aTicket,aForceWrite,aPageAnnotations);
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
