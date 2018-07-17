package TTRETRIEVAL;

/**
 *	Generated from IDL interface "DocReader"
 *	@author JacORB IDL compiler V 2.2.2, 1-Jun-2005
 */


public interface DocReaderOperations
{
	/* constants */
	/* operations  */
	void getRenditionTypes(java.lang.String aTicket, java.lang.String anIfn, int aPageNum, TTRETRIEVAL.RenditionTypeDataHolder aRenditionTypes) throws TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.InvalidDSException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.UnknownPageException,TTEXCEPTIONS.TTlibException;
	boolean getRenditionIfn(java.lang.String aTicket, java.lang.String anInIfn, int anInPageNum, int aSubPageNum, TTRETRIEVAL.ParamDataHolder aParams, org.omg.CORBA.StringHolder anOutIfn, org.omg.CORBA.IntHolder anOutPageNum, org.omg.CORBA.IntHolder aSubPageCount, org.omg.CORBA.StringHolder aDocDosExt, org.omg.CORBA.StringHolder aDocDosExtOrig) throws TTEXCEPTIONS.GenericException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.InvalidDSException,TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTRETRIEVAL.UnknownSubpageException,TTEXCEPTIONS.UnknownPageException,TTRETRIEVAL.BadParamException,TTEXCEPTIONS.TTlibException;
	byte[] getRenditionData(java.lang.String aTicket, java.lang.String anIfn, int aPageNum) throws TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.InvalidDSException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.UnknownPageException,TTEXCEPTIONS.TTlibException;
	void getRenditionDataIntoTempFile(java.lang.String aTicket, java.lang.String anIfn, int aPageNum, org.omg.CORBA.StringHolder serverTmpFilename, org.omg.CORBA.IntHolder aFileLength, org.omg.CORBA.IntHolder aChunkLength) throws TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.InvalidDSException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.UnknownPageException,TTEXCEPTIONS.TTlibException;
	void getDocumentInfo(java.lang.String aTicket, java.lang.String anIfn, java.lang.String aPages, org.omg.CORBA.StringHolder aContentType, org.omg.CORBA.StringHolder aFilePath, org.omg.CORBA.IntHolder aFileLength, org.omg.CORBA.IntHolder aChunkLength) throws TTEXCEPTIONS.GenericException,TTEXCEPTIONS.BadDocIdException,TTEXCEPTIONS.InvalidDSException,TTEXCEPTIONS.DocPermException,TTEXCEPTIONS.UnknownDocException,TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.UnknownPageException,TTRETRIEVAL.BadPagesException,TTEXCEPTIONS.TTlibException;
	byte[] getDocumentChunk(java.lang.String aFilePath, int aLength, int anOffset) throws TTRETRIEVAL.BadFilePathException,TTEXCEPTIONS.GenericException;
	void closeDocument(java.lang.String aFilePath) throws TTRETRIEVAL.BadFilePathException,TTEXCEPTIONS.GenericException;
	java.lang.String getMimeType(java.lang.String aTicket, java.lang.String aFileExtn) throws TTEXCEPTIONS.AuthenticationException,TTEXCEPTIONS.GenericException,TTEXCEPTIONS.TTlibException;
}
