package com.veasmkii.jib;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class Test extends JPanel {
	private JInternalFrame internalFrame;
	private JInternalFrame internalFrame_1;

	public Test() {
		initLayout();
	}

	private void initLayout() {

		internalFrame = new JInternalFrame( "New JInternalFrame" );
		add( internalFrame );
		internalFrame.setVisible( true );

		internalFrame_1 = new JInternalFrame( "New JInternalFrame" );
		add( internalFrame_1 );
		internalFrame_1.setVisible( true );
	}

}
