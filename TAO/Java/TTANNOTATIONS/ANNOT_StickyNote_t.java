package TTANNOTATIONS;

/**
 *	Generated from IDL definition of struct "ANNOT_StickyNote_t"
 *	@author JacORB IDL compiler 
 */

public final class ANNOT_StickyNote_t
	implements org.omg.CORBA.portable.IDLEntity
{
	public ANNOT_StickyNote_t(){}
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	public int rotation;
	public int shape;
	public int dataLength;
	public TTANNOTATIONS.SpecialType dataType;
	public java.lang.String data = "";
	public ANNOT_StickyNote_t(int x1, int y1, int x2, int y2, int rotation, int shape, int dataLength, TTANNOTATIONS.SpecialType dataType, java.lang.String data)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.rotation = rotation;
		this.shape = shape;
		this.dataLength = dataLength;
		this.dataType = dataType;
		this.data = data;
	}
}
