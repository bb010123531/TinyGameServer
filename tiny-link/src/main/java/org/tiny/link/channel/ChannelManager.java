package org.tiny.link.channel;

import java.util.HashMap;
import java.util.Map;

import io.netty.channel.Channel;

public class ChannelManager {
	
	private ChannelManager () {}
	
	private static class SingletonFactory {
		private static final ChannelManager INSTANCE = new ChannelManager(); 
	}
	
	public static ChannelManager getInstance() {
		return SingletonFactory.INSTANCE;
	}
	
	// 假设只有一个GS 
	private Channel gs;
	
	private Map<String, Channel> id2ClientChannel = new HashMap<>();
	
	public Channel getGs() {
		return gs;
	}

	public void setGs(Channel gs) {
		this.gs = gs;
	}
	
	public void addToClientChannel (String channelId, Channel channel) {
		id2ClientChannel.put(channelId, channel);
	}
	
	public Channel getClientChannel (String channelId) {
		return id2ClientChannel.get(channelId);
	}
}
