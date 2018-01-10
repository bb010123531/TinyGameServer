package org.tiny.net.event;

public abstract class Event {
	
	protected long roleId;
	
	public Event(long roleId) {
		this.roleId = roleId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
}
