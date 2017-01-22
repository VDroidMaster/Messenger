package com.vdroidmaster.messenger.database;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import com.vdroidmaster.messenger.model.Message;
import com.vdroidmaster.messenger.model.Profile;

public class DatabaseClass {

	private static Map<String, Profile> profiles = new TreeMap<String, Profile>();
	private static NavigableMap<Long, Message> messages = new TreeMap<Long, Message>();

	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
	
	public static NavigableMap<Long, Message> getMessages() {
		return messages;
	}

	public static void clearDatabase() {
		
		profiles = new TreeMap<String, Profile>();
		messages = new TreeMap<Long, Message>();
	}
}
