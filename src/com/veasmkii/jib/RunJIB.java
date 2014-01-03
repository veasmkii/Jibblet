package com.veasmkii.jib;

import java.awt.Dimension;

import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.veasmkii.jib.gui.JibFrame;
import com.veasmkii.jib.utils.Images;

public class RunJIB {

	public static void main( final String[] args ) throws Exception {
		UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );

		final JibFrame frame = new JibFrame();
		frame.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		frame.setPreferredSize( new Dimension( 800, 600 ) );
		frame.setIconImage( Images.JIBBLET.getImage() );
		frame.pack();
		frame.setLocationRelativeTo( null );
		frame.setVisible( true );

	}

}
