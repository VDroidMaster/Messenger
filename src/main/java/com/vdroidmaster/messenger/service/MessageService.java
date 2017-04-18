package com.vdroidmaster.messenger.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.NavigableMap;

import com.vdroidmaster.messenger.database.DatabaseClass;
import com.vdroidmaster.messenger.model.Message;

public class MessageService {

	private static MessageService messageService = new MessageService();
	private NavigableMap<Long, Message> messages = DatabaseClass.getMessages();

	private MessageService() {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Message m1 = null;
		Message m2 = null;
		Message m3 = null;
		Message m4 = null;
		Message m5 = null;
		
		try {
			m1 = new Message("Hi this is Tom.", "Tom", dateFormat.parse("2012-09-03"));
			m2 = new Message("Hi this is Jerry.", "Jerry", dateFormat.parse("2013-06-05"));
			m3 = new Message("Hi this is Pluto.", "Pluto", dateFormat.parse("2012-12-05"));
			m4 = new Message("Hi this is Minnie.", "Minnie", dateFormat.parse("2012-10-09"));
			m5 = new Message("Hi this is Mickey.", "Mickey", dateFormat.parse("2015-11-08"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		addMessage(m1);
		addMessage(m2);
		addMessage(m3);
		addMessage(m4);
		addMessage(m5);
	}
	
	public static MessageService getInstance() {
		return messageService;
	}

	public List<Message> getMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getMessagesForYear(Integer year) {
		
		List<Message> messagesForYear = new ArrayList<Message>();
		Calendar calendar = Calendar.getInstance();
		
		for(Message message : messages.values()) {
			
			calendar.setTime(message.getCreated());
			
			if (calendar.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	public List<Message> getMessagesPaginated(Integer start, Integer size) {
		
		List<Message> filteredMessages = new ArrayList<Message>(messages.values());
		
		if (start + size > filteredMessages.size()) {
			return new ArrayList<Message>();
		}
		return filteredMessages.subList(start, start + size);
	}

	public Message getMessage(Long id) {
		
		if (id <= 0) {
			return null;
		}
		return messages.get(id);
	}

	public Message addMessage(Message message) {
		
		Long lastMessageId = Long.valueOf(0);
		
		if (!messages.isEmpty()) {
			lastMessageId = messages.lastKey();
		}
		
		message.setId((Long.valueOf(lastMessageId + 1)));
		
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		
		if (message.getId() <= 0) {
			return null;
		}
		
		if (messages.replace(message.getId(), message) != null) {
			return message;
		} else {
			return null;
		}
	}

	public void removeMessage(Long id) {
		
		if (id <= 0) {
			return;
		}
		
		messages.remove(id);
	}
}
