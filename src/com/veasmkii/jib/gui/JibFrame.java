package com.veasmkii.jib.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.veasmkii.jib.connection.Connection;
import com.veasmkii.jib.utils.Resources;

public class JibFrame extends JFrame implements ActionListener
{

	private static final long serialVersionUID = 7955379425076885461L;

	private final List<Connection> connections = new ArrayList<Connection>();

	final JCheckBoxMenuItem expandItem;
	final JibPanel jibPanel;

	public JibFrame()
	{
		setTitle( "Jibblet" );

		final JMenuBar menuBar = new JMenuBar();
		{
			final JMenu fileMenu = new JMenu( Resources.getString( "JibFrame.fileMenu" ) ); //$NON-NLS-1$
			final JMenuItem exitItem = new JMenuItem( Resources.getString( "JibFrame.exitItem" ) ); //$NON-NLS-1$
			exitItem.setActionCommand( Resources.getString( "JibFrame.exitItemCommand" ) ); //$NON-NLS-1$
			exitItem.addActionListener( this );

			expandItem = new JCheckBoxMenuItem( "Expand" );
			// expandItem.setSelected( true );
			expandItem.addActionListener( new ActionListener()
			{
				@Override
				public void actionPerformed( final ActionEvent e )
				{
					expandSelectAction();
				}
			} );

			fileMenu.add( expandItem );
			fileMenu.add( exitItem );
			menuBar.add( fileMenu );

		}
		setJMenuBar( menuBar );

		jibPanel = new JibPanel();
		setContentPane( jibPanel );

		// // -- Make Connection and add quit listener -- //
		// final Thread thread = new Thread( new Runnable()
		// {
		// @Override
		// public void run()
		// {
		// final Server server = new Server( "irc.freenode.net", 6667 );
		// final Login login = new Login( "exomor", "Ryan_Smith-Evans",
		// "Too cool for school" );
		// final Connection connection = panel.createConnection( server, login
		// );
		// connection.execute();
		//
		// connection.setQuitMessage( "Toasty" );
		// connection.writeMessage( "LIST #jib" );
		// connection.writeMessage( "AWAY beasty" );
		//
		// connections.add( connection );
		// }
		// } );
		// thread.start();
		//
		attachShutdownHook();

		expandSelectAction();

	}

	private void expandSelectAction()
	{
		if ( expandItem.isSelected() )
		{
			this.setExtendedState( JFrame.MAXIMIZED_BOTH );
		}
		else
		{
			this.setExtendedState( JFrame.NORMAL );
		}
	}

	private void attachShutdownHook()
	{
		final Thread shutDownHook = new Thread( new Runnable()
		{
			@Override
			public void run()
			{
				System.out.println( "Shutting down!" );
				jibPanel.quitting();

				for ( Connection connection : connections )
				{
					connection.quit();
				}
			}
		} );
		Runtime.getRuntime().addShutdownHook( shutDownHook );
	}

	@Override
	public void actionPerformed( ActionEvent actionEvent )
	{

		final String command = actionEvent.getActionCommand();

		if ( command.equals( Resources.getString( Resources
				.getString( "JibFrame.JibFrame.exitItemCommand" ) ) ) ) //$NON-NLS-1$
		{
			//@formatter:off
			final int option = JOptionPane.showConfirmDialog( this,
					Resources.getString( "JibFrame.confirmExitMsg" ), //$NON-NLS-1$
					Resources.getString( "JibFrame.confirmExit" ), //$NON-NLS-1$
					JOptionPane.YES_NO_OPTION, 
					JOptionPane.INFORMATION_MESSAGE );
			//@formatter:on

			if ( option == JOptionPane.YES_OPTION )
			{
				this.dispose();
			}
		}
	}

}
