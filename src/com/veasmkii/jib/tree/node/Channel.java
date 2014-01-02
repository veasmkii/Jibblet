package com.veasmkii.jib.tree.node;

import javax.swing.JPopupMenu;

import com.veasmkii.jib.gui.desktop.JibWindow;

public class Channel extends ContextNode
{

	private static final long serialVersionUID = 8935405423645206275L;

	private String channelName;

	public Channel( final String channelName )
	{
		this.channelName = channelName;
	}

	@Override
	public String toString()
	{
		return channelName;
	}

	public String getName()
	{
		return channelName;
	}

	public void setName( final String channelName )
	{
		this.channelName = channelName;
	}

	@Override
	public JPopupMenu getMenu()
	{
		return null;
	}

	@Override
	public JibWindow getWindow()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void quitting()
	{
		System.out.println( "Channel:" + channelName + " quitting." );

		// TODO Auto-generated method stub
	}

}
