package TTRETRIEVAL;

import TTRETRIEVAL.ImageDataHelper;

/**
 *	Generated from IDL definition of alias "ImageData"
 *	@author JacORB IDL compiler 
 */

public final class ImageDataHolder
	implements org.omg.CORBA.portable.Streamable
{
	public byte[] value;

	public ImageDataHolder ()
	{
	}
	public ImageDataHolder (final byte[] initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return ImageDataHelper.type ();
	}
	@Override
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ImageDataHelper.read (in);
	}
	@Override
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		ImageDataHelper.write (out,value);
	}
}
