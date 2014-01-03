package com.veasmkii.jib.tree.node;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import com.veasmkii.jib.gui.desktop.JibDesktop;
import com.veasmkii.jib.gui.desktop.JibWindow;
import com.veasmkii.jib.tree.JibTree;
import com.veasmkii.jib.utils.Images;

public class Server extends ContextNode {

	private static final long serialVersionUID = 486955655174083657L;

	private final String serverName;
	private final int port;
	private final List<Channel> channels;

	private JMenuItem nameItem;
	private JMenuItem addItem;

	private final JibWindow window;

	public Server( final JibDesktop desktop, final String serverName, final int port ) {
		this.serverName = serverName;
		this.port = port;
		this.channels = new ArrayList<Channel>();

		this.window = new JibWindow( this, desktop );
		window.show();
	}

	@Override
	public String toString() {
		return serverName;
	}

	public String getName() {
		return serverName;
	}

	public int getPort() {
		return port;
	}

	@Override
	public JPopupMenu getMenu() {
		if ( super.menu == null ) {
			super.menu = new JPopupMenu();
			nameItem = new JMenuItem();
			nameItem.setArmed( false );
			nameItem.setEnabled( false );
			super.menu.add( nameItem );

			addItem = new JMenuItem( "Add Channel" );
			addItem.setIcon( new ImageIcon( Images.ADD.getImage() ) );
			addItem.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed( final ActionEvent e ) {
					addItemPerformed();
				}
			} );

			super.menu.add( addItem );
		}

		new LinkedList<>();
		new ArrayList<>();

		nameItem.setText( serverName );

		return super.menu;
	}

	private void addItemPerformed() {
		final String channelName = JOptionPane.showInputDialog( null, "Channel Name:" );

		if ( ( channelName == null ) || channelName.trim().equalsIgnoreCase( "" ) )
			return;

		final Channel channel = new Channel( channelName );

		channels.add( channel );

		JibTree.treeModel.insertNodeInto( channel, this, this.getChildCount() );

	}

	@Override
	public JibWindow getWindow() {
		return window;
	}

	@Override
	public void quitting() {
		System.out.println( "Server:" + serverName + " quitting." );

		for ( final Channel channel : channels )
			channel.quitting();
	}

}
