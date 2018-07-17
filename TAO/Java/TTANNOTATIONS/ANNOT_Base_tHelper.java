package TTANNOTATIONS;

import TTANNOTATIONS.ANNOT_Base_t;

/**
 *	Generated from IDL definition of union "ANNOT_Base_t"
 *	@author JacORB IDL compiler 
 */

public final class ANNOT_Base_tHelper
{
	private static org.omg.CORBA.TypeCode _type;
	public static void insert (final org.omg.CORBA.Any any, final TTANNOTATIONS.ANNOT_Base_t s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static TTANNOTATIONS.ANNOT_Base_t extract (final org.omg.CORBA.Any any)
	{
		return read(any.create_input_stream());
	}

	public static String id()
	{
		return "IDL:TTANNOTATIONS/ANNOT_Base_t:1.0";
	}
	public static ANNOT_Base_t read (org.omg.CORBA.portable.InputStream in)
	{
		ANNOT_Base_t result = new ANNOT_Base_t ();
		int disc=in.read_long();
		switch (disc)
		{
			case 4:
			{
				TTANNOTATIONS.ANNOT_Highlight_t _var;
				_var=TTANNOTATIONS.ANNOT_Highlight_tHelper.read(in);
				result.hl (_var);
				break;
			}
			case 5:
			{
				TTANNOTATIONS.ANNOT_StickyNote_t _var;
				_var=TTANNOTATIONS.ANNOT_StickyNote_tHelper.read(in);
				result.sn (_var);
				break;
			}
			case 6:
			{
				TTANNOTATIONS.ANNOT_Underline_t _var;
				_var=TTANNOTATIONS.ANNOT_Underline_tHelper.read(in);
				result.ul (_var);
				break;
			}
			case 7:
			{
				TTANNOTATIONS.ANNOT_MarginComment_t _var;
				_var=TTANNOTATIONS.ANNOT_MarginComment_tHelper.read(in);
				result.mc (_var);
				break;
			}
			case 8:
			{
				TTANNOTATIONS.ANNOT_FreeHand_t _var;
				_var=TTANNOTATIONS.ANNOT_FreeHand_tHelper.read(in);
				result.fh (_var);
				break;
			}
			case 9:
			{
				TTANNOTATIONS.ANNOT_Ellipse_t _var;
				_var=TTANNOTATIONS.ANNOT_Ellipse_tHelper.read(in);
				result.el (_var);
				break;
			}
			default: result.__default (disc);
		}
		return result;
	}
	public static void write (org.omg.CORBA.portable.OutputStream out, ANNOT_Base_t s)
	{
		out.write_long(s.discriminator ());
		switch (s.discriminator ())
		{
			case 4:
			{
				TTANNOTATIONS.ANNOT_Highlight_tHelper.write(out,s.hl ());
				break;
			}
			case 5:
			{
				TTANNOTATIONS.ANNOT_StickyNote_tHelper.write(out,s.sn ());
				break;
			}
			case 6:
			{
				TTANNOTATIONS.ANNOT_Underline_tHelper.write(out,s.ul ());
				break;
			}
			case 7:
			{
				TTANNOTATIONS.ANNOT_MarginComment_tHelper.write(out,s.mc ());
				break;
			}
			case 8:
			{
				TTANNOTATIONS.ANNOT_FreeHand_tHelper.write(out,s.fh ());
				break;
			}
			case 9:
			{
				TTANNOTATIONS.ANNOT_Ellipse_tHelper.write(out,s.el ());
				break;
			}
		}
	}
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			org.omg.CORBA.UnionMember[] members = new org.omg.CORBA.UnionMember[6];
			org.omg.CORBA.Any label_any;
			label_any = org.omg.CORBA.ORB.init().create_any ();
			label_any.insert_long (4);
			members[5] = new org.omg.CORBA.UnionMember ("hl", label_any, TTANNOTATIONS.ANNOT_Highlight_tHelper.type(),null);
			label_any = org.omg.CORBA.ORB.init().create_any ();
			label_any.insert_long (5);
			members[4] = new org.omg.CORBA.UnionMember ("sn", label_any, TTANNOTATIONS.ANNOT_StickyNote_tHelper.type(),null);
			label_any = org.omg.CORBA.ORB.init().create_any ();
			label_any.insert_long (6);
			members[3] = new org.omg.CORBA.UnionMember ("ul", label_any, TTANNOTATIONS.ANNOT_Underline_tHelper.type(),null);
			label_any = org.omg.CORBA.ORB.init().create_any ();
			label_any.insert_long (7);
			members[2] = new org.omg.CORBA.UnionMember ("mc", label_any, TTANNOTATIONS.ANNOT_MarginComment_tHelper.type(),null);
			label_any = org.omg.CORBA.ORB.init().create_any ();
			label_any.insert_long (8);
			members[1] = new org.omg.CORBA.UnionMember ("fh", label_any, TTANNOTATIONS.ANNOT_FreeHand_tHelper.type(),null);
			label_any = org.omg.CORBA.ORB.init().create_any ();
			label_any.insert_long (9);
			members[0] = new org.omg.CORBA.UnionMember ("el", label_any, TTANNOTATIONS.ANNOT_Ellipse_tHelper.type(),null);
			 _type = org.omg.CORBA.ORB.init().create_union_tc(id(),"ANNOT_Base_t",org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), members);
		}
		return _type;
	}
}
