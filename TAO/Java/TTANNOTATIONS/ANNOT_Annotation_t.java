package TTANNOTATIONS;

/**
 *	Generated from IDL definition of struct "ANNOT_Annotation_t"
 *	@author JacORB IDL compiler 
 */

public final class ANNOT_Annotation_t
	implements org.omg.CORBA.portable.IDLEntity
{
	public ANNOT_Annotation_t(){}
	public TTANNOTATIONS.StatusType status;
	public TTANNOTATIONS.AnnotPermission access;
	public java.lang.String creatorUser = "";
	public java.lang.String creatorGroup = "";
	public int creationTimestamp;
	public java.lang.String lastModifiedUser = "";
	public java.lang.String lastModifiedGroup = "";
	public int lastModifiedTimestamp;
	public TTANNOTATIONS.ANNOT_Colour_t colour;
	public TTANNOTATIONS.ANNOT_Base_t annotation;
	public ANNOT_Annotation_t(TTANNOTATIONS.StatusType status, TTANNOTATIONS.AnnotPermission access, java.lang.String creatorUser, java.lang.String creatorGroup, int creationTimestamp, java.lang.String lastModifiedUser, java.lang.String lastModifiedGroup, int lastModifiedTimestamp, TTANNOTATIONS.ANNOT_Colour_t colour, TTANNOTATIONS.ANNOT_Base_t annotation)
	{
		this.status = status;
		this.access = access;
		this.creatorUser = creatorUser;
		this.creatorGroup = creatorGroup;
		this.creationTimestamp = creationTimestamp;
		this.lastModifiedUser = lastModifiedUser;
		this.lastModifiedGroup = lastModifiedGroup;
		this.lastModifiedTimestamp = lastModifiedTimestamp;
		this.colour = colour;
		this.annotation = annotation;
	}
}
