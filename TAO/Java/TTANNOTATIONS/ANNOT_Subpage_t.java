package TTANNOTATIONS;

/**
 *	Generated from IDL definition of struct "ANNOT_Subpage_t"
 *	@author JacORB IDL compiler 
 */

public final class ANNOT_Subpage_t
	implements org.omg.CORBA.portable.IDLEntity
{
	public ANNOT_Subpage_t(){}
	public short subpageId;
	public TTANNOTATIONS.ANNOT_Annotation_t[] annotSeq;
	public ANNOT_Subpage_t(short subpageId, TTANNOTATIONS.ANNOT_Annotation_t[] annotSeq)
	{
		this.subpageId = subpageId;
		this.annotSeq = annotSeq;
	}
}
