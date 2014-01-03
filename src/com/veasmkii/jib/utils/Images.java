package com.veasmkii.jib.utils;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public enum Images {
	JIBBLET( "J_128" ),
	ROOT( "hostname" ),
	SERVER( "server" ),
	CHANNEL( "comment" ),
	USER( "user_16" ),
	OPERATOR( "operator" ),
	ADD( "add" );

	private String iconName;

	private Images( final String iconName ) {
		this.iconName = iconName;
	}

	public Image getImage() {
		return Toolkit.getDefaultToolkit().getImage(
				Resources.class.getResource( "/com/veasmkii/jib/icons/" + iconName + ".png" ) );
	}

	public ImageIcon getImageIcon() {
		return new ImageIcon( "/com/veasmkii/jib/icons/" + iconName + ".png" );
	}

}
