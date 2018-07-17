package TTANNOTATIONS;

/**
 *	Generated from IDL definition of struct "ANNOT_FreeHand_t"
 *	@author JacORB IDL compiler 
 */

public final class ANNOT_FreeHand_t
	implements org.omg.CORBA.portable.IDLEntity
{
	public ANNOT_FreeHand_t(){}
	public int x;
	public int y;
	public int penWidth;
	public TTANNOTATIONS.PenStyle lineStyle;
	public TTANNOTATIONS.SpecialType dataType;
	public TTANNOTATIONS.ANNOT_Point_t[] points;
	public ANNOT_FreeHand_t(int x, int y, int penWidth, TTANNOTATIONS.PenStyle lineStyle, TTANNOTATIONS.SpecialType dataType, TTANNOTATIONS.ANNOT_Point_t[] points)
	{
		this.x = x;
		this.y = y;
		this.penWidth = penWidth;
		this.lineStyle = lineStyle;
		this.dataType = dataType;
		this.points = points;
	}
}
