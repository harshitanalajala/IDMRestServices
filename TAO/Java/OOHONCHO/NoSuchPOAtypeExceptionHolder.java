package OOHONCHO;

/**
 *	Generated from IDL definition of exception "NoSuchPOAtypeException"
 *	@author JacORB IDL compiler 
 */

public final class NoSuchPOAtypeExceptionHolder
	implements org.omg.CORBA.portable.Streamable
{
	public OOHONCHO.NoSuchPOAtypeException value;

	public NoSuchPOAtypeExceptionHolder ()
	{
	}
	public NoSuchPOAtypeExceptionHolder(final OOHONCHO.NoSuchPOAtypeException initial)
	{
		value = initial;
	}
	@Override
	public org.omg.CORBA.TypeCode _type ()
	{
		return OOHONCHO.NoSuchPOAtypeExceptionHelper.type ();
	}
	@Override
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = OOHONCHO.NoSuchPOAtypeExceptionHelper.read(_in);
	}
	@Override
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		OOHONCHO.NoSuchPOAtypeExceptionHelper.write(_out, value);
	}
}
