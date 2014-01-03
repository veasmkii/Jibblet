package com.veasmkii.jib.connection;

public class Login {
	private final String nick;
	private final String loginName;
	private final String info;

	public Login( final String nick, final String login, final String info ) {
		this.nick = nick;
		this.loginName = login;
		this.info = info;
	}

	public String getNick() {
		return nick;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getInfo() {
		return info;
	}

}
