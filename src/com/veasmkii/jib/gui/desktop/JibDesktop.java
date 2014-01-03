package com.veasmkii.jib.gui.desktop;

import java.awt.Color;

import javax.swing.JDesktopPane;

public class JibDesktop extends JDesktopPane {

	private static final long serialVersionUID = 6187955572599516724L;

	public JibDesktop() {
		setBackground( Color.gray );
	}

	public void addWindow( final JibWindow window ) {
		add( window );
	}

	public void quitting() {
		System.out.println( "Desktop quitting." );
	}
}
