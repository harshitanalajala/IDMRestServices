package TTANNOTATIONS;

/**
 *	Generated from IDL interface "Annot"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */


public interface AnnotOperations
{
	/* constants */
	/* operations  */
	void getAnnotations(java.lang.String aTicket, java.lang.String anIfn, int aPageNo, java.lang.String aSubpageNos, TTANNOTATIONS.PageAnnotationsHolder aPageAnnotations) throws TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.InvalidDSException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.UnknownPageException,TTEXCEPTIONS.TTlibException;
	void setAnnotations(java.lang.String aTicket, boolean aForceWrite, TTANNOTATIONS.PageAnnotationsHolder aPageAnnotations) throws TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.InvalidDSException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException,TTANNOTATIONS.AnnotationException;
}
