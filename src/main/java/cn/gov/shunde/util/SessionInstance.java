package cn.gov.shunde.util;

import java.util.HashMap;
import java.util.Map;

public class SessionInstance {


	private static Map<String, Object> session;

	public SessionInstance() {
		session = new HashMap<>();
	}

	public synchronized void putValue(String key, Object value) {
		session.put(key, value);
	}

	public synchronized Object getValue(String key) {
		return session.get(key);
	}

}
