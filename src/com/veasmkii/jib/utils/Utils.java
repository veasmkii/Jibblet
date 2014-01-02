package com.veasmkii.jib.utils;

import java.io.BufferedWriter;
import java.io.IOException;

public final class Utils
{

	public final static String msgEnd = "\r\n";

	public static void close( final BufferedWriter writer )
	{
		try
		{
			if ( writer != null )
			{
				writer.close();
			}
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}

	public static void write( final BufferedWriter writer, final String line )
	{
		try
		{
			writer.write( line + Utils.msgEnd );
			writer.flush();
		}
		catch ( IOException ioe )
		{
			ioe.printStackTrace();
		}
	}
}
