package com.veasmkii.jib.connection;

public class Login
{
	private String nick;
	private String loginName;
	private String info;

	public Login( final String nick, final String login, final String info )
	{
		this.nick = nick;
		this.loginName = login;
		this.info = info;
	}

	public String getNick()
	{
		return nick;
	}

	public String getLoginName()
	{
		return loginName;
	}

	public String getInfo()
	{
		return info;
	}

}
