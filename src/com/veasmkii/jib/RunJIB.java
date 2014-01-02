package com.veasmkii.jib;

import java.awt.Dimension;

import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.veasmkii.jib.gui.JibFrame;
import com.veasmkii.jib.utils.Images;

public class RunJIB
{

	public static void main( String[] args ) throws Exception
	{
		UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );

		final JibFrame jibFrame = new JibFrame();
		jibFrame.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		jibFrame.setPreferredSize( new Dimension( 800, 600 ) );
		jibFrame.setIconImage( Images.J.getImage() );
		jibFrame.pack();
		jibFrame.setVisible( true );

	}

}
