package TTANNOTATIONS;

/**
 *	Generated from IDL definition of union "ANNOT_Base_t"
 *	@author JacORB IDL compiler 
 */

public final class ANNOT_Base_t
	implements org.omg.CORBA.portable.IDLEntity
{
	private int discriminator;
	private TTANNOTATIONS.ANNOT_Highlight_t hl;
	private TTANNOTATIONS.ANNOT_StickyNote_t sn;
	private TTANNOTATIONS.ANNOT_Underline_t ul;
	private TTANNOTATIONS.ANNOT_MarginComment_t mc;
	private TTANNOTATIONS.ANNOT_FreeHand_t fh;
	private TTANNOTATIONS.ANNOT_Ellipse_t el;

	public ANNOT_Base_t ()
	{
	}

	public int discriminator ()
	{
		return discriminator;
	}

	public TTANNOTATIONS.ANNOT_Highlight_t hl ()
	{
		if (discriminator != 4)
			throw new org.omg.CORBA.BAD_OPERATION();
		return hl;
	}

	public void hl (TTANNOTATIONS.ANNOT_Highlight_t _x)
	{
		discriminator = 4;
		hl = _x;
	}

	public TTANNOTATIONS.ANNOT_StickyNote_t sn ()
	{
		if (discriminator != 5)
			throw new org.omg.CORBA.BAD_OPERATION();
		return sn;
	}

	public void sn (TTANNOTATIONS.ANNOT_StickyNote_t _x)
	{
		discriminator = 5;
		sn = _x;
	}

	public TTANNOTATIONS.ANNOT_Underline_t ul ()
	{
		if (discriminator != 6)
			throw new org.omg.CORBA.BAD_OPERATION();
		return ul;
	}

	public void ul (TTANNOTATIONS.ANNOT_Underline_t _x)
	{
		discriminator = 6;
		ul = _x;
	}

	public TTANNOTATIONS.ANNOT_MarginComment_t mc ()
	{
		if (discriminator != 7)
			throw new org.omg.CORBA.BAD_OPERATION();
		return mc;
	}

	public void mc (TTANNOTATIONS.ANNOT_MarginComment_t _x)
	{
		discriminator = 7;
		mc = _x;
	}

	public TTANNOTATIONS.ANNOT_FreeHand_t fh ()
	{
		if (discriminator != 8)
			throw new org.omg.CORBA.BAD_OPERATION();
		return fh;
	}

	public void fh (TTANNOTATIONS.ANNOT_FreeHand_t _x)
	{
		discriminator = 8;
		fh = _x;
	}

	public TTANNOTATIONS.ANNOT_Ellipse_t el ()
	{
		if (discriminator != 9)
			throw new org.omg.CORBA.BAD_OPERATION();
		return el;
	}

	public void el (TTANNOTATIONS.ANNOT_Ellipse_t _x)
	{
		discriminator = 9;
		el = _x;
	}

	public void __default ()
	{
		discriminator = 0;
	}
	public void __default (int _discriminator)
	{
		discriminator = _discriminator;
	}
}
