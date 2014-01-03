package com.veasmkii.jib.connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import com.veasmkii.jib.tree.node.Channel;
import com.veasmkii.jib.tree.node.Server;
import com.veasmkii.jib.utils.Utils;

public class Connection extends SwingWorker<String, String> {
	private Socket socket = null;
	private BufferedWriter writer = null;
	private BufferedReader reader = null;

	private final Server server;
	private final Login login;

	private String quitMessage;

	private JTextArea textArea;

	public Connection( final Server server, final Login login ) {
		this.server = server;
		this.login = login;
	}

	public void setQuitMessage( final String quitMessage ) {
		this.quitMessage = quitMessage;
	}

	public void setTextArea( final JTextArea textArea ) {
		this.textArea = textArea;
	}

	public void login() {
		try {
			socket = new Socket( server.getName(), server.getPort() );
			writer = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
			reader = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

			// Login
			writer.write( "NICK " + login.getNick() + Utils.msgEnd );
			writer.write( "USER " + login.getLoginName() + " 8 * : " + login.getInfo() + Utils.msgEnd );
			writer.flush();

			String line = null;
			while ( ( line = reader.readLine() ) != null )
				if ( line.indexOf( "004" ) >= 0 ) {
					append( "004 RPL_MYINFO <Already connected!>" );
					append( line );
					break;
				} else if ( line.indexOf( "433" ) >= 0 ) {
					append( "433 ERR_NICKNAMEINUSE <Nickname in use!>" );
					append( line );
					break;
				} else
					append( line );

		} catch ( final IOException e ) {
			e.printStackTrace();
		}
	}

	public void joinChannel( final Channel channel ) {
		write( "JOIN " + channel );
	}

	private final boolean running = true;

	@Override
	protected String doInBackground() throws Exception {
		// Thread here
		String line = null;
		try {
			while ( running )
				if ( ( line = reader.readLine() ) != null )
					publish( line );
		} catch ( final IOException ioe ) {
			ioe.printStackTrace();
		}

		return line;
	}

	@Override
	protected void process( final List<String> lines ) {
		for ( final String line : lines ) {
			pingResponder( line );
			append( line );
		}
	}

	private void append( final String line ) {
		System.out.println( "\n" + line );
		textArea.append( "\n" + line );
		textArea.setCaretPosition( textArea.getDocument().getLength() );
	}

	public synchronized void writeMessage( final String message ) {
		write( message );
	}

	private void write( final String message ) {
		Utils.write( writer, message );
		append( message );
	}

	private void pingResponder( final String line ) {
		// Respond to ping
		if ( line.startsWith( "PING " ) )
			write( "PONG " + line.substring( 5 ) );
	}

	public void quit() {
		if ( quitMessage != null )
			write( "QUIT :" + quitMessage );
		else
			write( "QUIT" );
	}
}
