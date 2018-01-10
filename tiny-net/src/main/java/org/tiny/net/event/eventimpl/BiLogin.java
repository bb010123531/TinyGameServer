package org.tiny.net.event.eventimpl;

import org.tiny.net.event.Event;

public class BiLogin extends Event{
	
	private String userName;
	private long loginTime;
	
	public BiLogin(long roleId, String name, long time) {
		super(roleId);
		this.userName = name;
		this.loginTime = time;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}
}
