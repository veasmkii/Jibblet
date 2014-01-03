package com.veasmkii.jib;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JTextField;
import javax.swing.Timer;

public final class TickerField extends JTextField {
	private static final long serialVersionUID = -7737508481542369298L;

	private final Timer timer;
	private final String text;
	private String scrollText;

	public TickerField( final String text ) {
		this.text = text;
		this.scrollText = text;

		this.setText( text );

		this.setEditable( false );

		// Create Ticker timer
		timer = new Timer( 250, new ActionListener() {

			@Override
			public void actionPerformed( final ActionEvent e ) {
				tick( true );
			}
		} );

		// Add ticker mouseOver stop/start
		this.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseEntered( final MouseEvent e ) {
				timer.stop();
			}

			@Override
			public void mouseExited( final MouseEvent e ) {
				timer.start();
			}
		} );

		// Add wheel scroll
		this.addMouseWheelListener( new MouseWheelListener() {
			@Override
			public void mouseWheelMoved( final MouseWheelEvent e ) {
				if ( e.getWheelRotation() > 0 )
					tick( true );
				else
					tick( false );
			}
		} );

		// Start timer
		timer.start();
	}

	/**
	 * Moves the ticker alone one character in the given direction
	 * 
	 * @param positive
	 */
	private void tick( final boolean positive ) {
		if ( getWidth() < getTextWidth( scrollText ) ) {
			if ( positive ) {
				final char ch = scrollText.charAt( 0 );
				scrollText = scrollText.substring( 1, scrollText.length() );
				scrollText += ch;
			} else {
				final char ch = scrollText.charAt( scrollText.length() - 1 );
				scrollText = scrollText.substring( 0, scrollText.length() - 1 );
				scrollText = ch + scrollText;
			}
		} else
			scrollText = text;

		this.setText( scrollText );
	}

	/**
	 * Calculate the actual String display width
	 * 
	 * @param string
	 * @return
	 */
	private int getTextWidth( final String string ) {
		final Graphics graphics = getGraphics();

		if ( graphics != null ) {
			final FontMetrics metrics = graphics.getFontMetrics( getFont() );

			return metrics.stringWidth( string );
		}
		return 0;
	}

	/**
	 * Terminates the timer.
	 */
	public void quitting() {
		System.out.println( "Timer stopping." );
		timer.stop();
	}
}
