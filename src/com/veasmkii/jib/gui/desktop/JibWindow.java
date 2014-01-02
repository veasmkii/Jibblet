package com.veasmkii.jib.gui.desktop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.veasmkii.jib.TickerField;
import com.veasmkii.jib.tree.node.ContextNode;

public class JibWindow extends JInternalFrame
{

	private final JScrollPane scrollPane;
	private final JTextArea textArea;
	private final JTextField textField;
	private final TickerField topicField;

	public JibWindow( final ContextNode node, final JibDesktop desktop )
	{
		super( node.toString() );

		desktop.add( this );

		addInternalFrameListener( new InternalFrameAdapter()
		{
			@Override
			public void internalFrameClosing( InternalFrameEvent e )
			{
				quitting();
			}
		} );

		topicField = new TickerField( node.toString() );

		scrollPane = new JScrollPane();
		textArea = new JTextArea();
		textArea.setLineWrap( true );
		textArea.setWrapStyleWord( true );
		textArea.setFont( new Font( "Monospaced", Font.PLAIN, 12 ) );
		textArea.setEditable( false );
		scrollPane.setViewportView( textArea );

		textField = new JTextField();

		this.setLayout( new BorderLayout() );

		this.add( topicField, BorderLayout.NORTH );
		this.add( scrollPane, BorderLayout.CENTER );
		this.add( textField, BorderLayout.SOUTH );

		this.setLocation( 87, 108 );
		this.setPreferredSize( new Dimension( 200, 150 ) );
		this.setResizable( true );
		this.setMaximizable( true );
		this.setClosable( true );
		this.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		this.pack();
	}
	
	@Override
	public void show()
	{
		if (super.isShowing())
		{
			super.moveToFront();
		}
		else
		{
			super.show();
		}
	}

	public void quitting()
	{
		topicField.quitting();
	}

}
