package TTANNOTATIONS;

/**
 *	Generated from IDL definition of struct "PageAnnotations"
 *	@author JacORB IDL compiler 
 */

public final class PageAnnotations
	implements org.omg.CORBA.portable.IDLEntity
{
	public PageAnnotations(){}
	public boolean userCanCreate;
	public java.lang.String imageIfn = "";
	public int imagePageNo;
	public java.lang.String userId = "";
	public short fileVersion;
	public TTANNOTATIONS.ANNOT_Subpage_t[] subSeq;
	public int conflictCount;
	public PageAnnotations(boolean userCanCreate, java.lang.String imageIfn, int imagePageNo, java.lang.String userId, short fileVersion, TTANNOTATIONS.ANNOT_Subpage_t[] subSeq, int conflictCount)
	{
		this.userCanCreate = userCanCreate;
		this.imageIfn = imageIfn;
		this.imagePageNo = imagePageNo;
		this.userId = userId;
		this.fileVersion = fileVersion;
		this.subSeq = subSeq;
		this.conflictCount = conflictCount;
	}
}
