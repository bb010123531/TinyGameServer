package org.tiny.net.event;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.tiny.net.tool.CollectionUtil;

public class EventManager {
	private static EventManager instance = new EventManager();
	
	public static EventManager getInstance() {
		return instance;
	}
	
	Map<Class<? extends Event>, Set<EventHandler>> listenMap = new HashMap<>();
	
	public void init() {
		
	}
	
	public <T extends Event> void addListener(Class<T> c, EventHandler handler) {
		CollectionUtil.add2MapSet(listenMap, c, handler);
	}
	
	public void dispatchEvent(Event e) {
		Set<EventHandler> handlers = listenMap.get(e.getClass());
		
		for (EventHandler handler : handlers) {
			handler.onEvent(e);
		}
	}
}
