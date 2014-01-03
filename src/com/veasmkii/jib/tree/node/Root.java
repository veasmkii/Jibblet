package com.veasmkii.jib.tree.node;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import com.veasmkii.jib.gui.desktop.JibDesktop;
import com.veasmkii.jib.gui.desktop.JibWindow;
import com.veasmkii.jib.tree.JibTree;
import com.veasmkii.jib.utils.Images;

public class Root extends ContextNode {

	private static final long serialVersionUID = 3977387783968060480L;

	private final List<Server> servers;
	private final JibDesktop desktop;
	private final String rootName;
	private final JibWindow window;

	private JMenuItem addItem;

	public Root( final JibDesktop desktop, final String rootName ) {
		this.desktop = desktop;
		this.rootName = rootName;
		this.servers = new ArrayList<Server>();

		this.window = new JibWindow( this, desktop );

	}

	@Override
	public String toString() {
		return rootName;
	}

	@Override
	public JPopupMenu getMenu() {
		if ( super.menu == null ) {
			super.menu = new JPopupMenu();
			addItem = new JMenuItem( "Add Server" );
			addItem.setIcon( new ImageIcon( Images.ADD.getImage() ) );
			addItem.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed( final ActionEvent e ) {
					addItemPerformed();
				}
			} );
			super.menu.add( addItem );
		}

		return super.menu;
	}

	private void addItemPerformed() {
		final String nodeName = JOptionPane.showInputDialog( null, "Enter the server name:" );

		if ( ( nodeName == null ) && nodeName.trim().equalsIgnoreCase( "" ) )
			return;

		final Server server = new Server( desktop, nodeName, 6667 );

		servers.add( server );

		JibTree.treeModel.insertNodeInto( server, this, this.getChildCount() );
	}

	@Override
	public JibWindow getWindow() {
		return window;
	}

	@Override
	public void quitting() {
		System.out.println( "Root quitting." );

		for ( final Server server : servers )
			server.quitting();
	}

}
