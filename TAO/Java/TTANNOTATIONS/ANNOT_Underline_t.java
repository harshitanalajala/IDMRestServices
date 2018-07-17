package TTANNOTATIONS;

/**
 *	Generated from IDL definition of struct "ANNOT_Underline_t"
 *	@author JacORB IDL compiler 
 */

public final class ANNOT_Underline_t
	implements org.omg.CORBA.portable.IDLEntity
{
	public ANNOT_Underline_t(){}
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	public int penWidth;
	public TTANNOTATIONS.PenStyle lineStyle;
	public ANNOT_Underline_t(int x1, int y1, int x2, int y2, int penWidth, TTANNOTATIONS.PenStyle lineStyle)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.penWidth = penWidth;
		this.lineStyle = lineStyle;
	}
}
