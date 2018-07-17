package TTANNOTATIONS;

/**
 *	Generated from IDL definition of struct "ANNOT_MarginComment_t"
 *	@author JacORB IDL compiler 
 */

public final class ANNOT_MarginComment_t
	implements org.omg.CORBA.portable.IDLEntity
{
	public ANNOT_MarginComment_t(){}
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	public int rotation;
	public int fontSize;
	public int fontStyle;
	public java.lang.String fontFamily = "";
	public TTANNOTATIONS.SpecialType dataType;
	public int dataLength;
	public java.lang.String data = "";
	public ANNOT_MarginComment_t(int x1, int y1, int x2, int y2, int rotation, int fontSize, int fontStyle, java.lang.String fontFamily, TTANNOTATIONS.SpecialType dataType, int dataLength, java.lang.String data)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.rotation = rotation;
		this.fontSize = fontSize;
		this.fontStyle = fontStyle;
		this.fontFamily = fontFamily;
		this.dataType = dataType;
		this.dataLength = dataLength;
		this.data = data;
	}
}
