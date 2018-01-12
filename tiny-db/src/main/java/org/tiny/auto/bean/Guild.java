package org.tiny.auto.bean;
//auto-gen file, do not edit anyway

public class Guild implements org.tiny.base.Bean {

	private long guildId;

	public long getGuildId() {
		return guildId;
	}

	public void setGuildId(long guildId) {
		this.guildId = guildId;
	}

	private String guildName = "";

	public String getGuildName() {
		return guildName;
	}

	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}

	@Override
	public Guild clone() throws CloneNotSupportedException {
		Guild clone = (Guild)super.clone();

		return clone;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Guild [");
		sb.append("guildId=" + guildId);
		sb.append(", ");
		sb.append("guildName=" + guildName);
		sb.append("]");

		return sb.toString();
	}
}