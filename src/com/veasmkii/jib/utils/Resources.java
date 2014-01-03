package com.veasmkii.jib.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Resources {
	private static final String BUNDLE_NAME = "com.veasmkii.jib.utils.Resources"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle( BUNDLE_NAME );

	private Resources() {}

	public static String getString( final String key ) {
		try {
			return RESOURCE_BUNDLE.getString( key );
		} catch ( final MissingResourceException e ) {
			return '!' + key + '!';
		}
	}
}
