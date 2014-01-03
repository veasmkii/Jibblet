package com.veasmkii.jib.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import com.veasmkii.jib.connection.Connection;
import com.veasmkii.jib.connection.Login;
import com.veasmkii.jib.gui.desktop.JibDesktop;
import com.veasmkii.jib.tree.JibTree;
import com.veasmkii.jib.tree.node.Channel;
import com.veasmkii.jib.tree.node.Server;

public class JibPanel extends JPanel {

	private static final long serialVersionUID = 4989697423305371396L;

	public static final JibDesktop desktop = new JibDesktop();
	public static final JibTree tree = new JibTree( desktop );

	public JibPanel() {
		initComponents();
	}

	private void initComponents() {
		setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		setLayout( new BorderLayout( 0, 0 ) );

		final JSplitPane splitPane = new JSplitPane();
		add( splitPane, BorderLayout.CENTER );

		splitPane.setLeftComponent( tree );
		splitPane.setRightComponent( desktop );

		splitPane.setDividerLocation( 200 );

	}

	public Connection createConnection( final Server server, final Login login ) {
		final Connection connection = new Connection( server, login );

		connection.setTextArea( null );
		connection.login();
		connection.joinChannel( new Channel( "#jib" ) );

		return connection;

	}

	public void quitting() {
		tree.quitting();
		desktop.quitting();
	}

}
