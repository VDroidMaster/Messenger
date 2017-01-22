package com.vdroidmaster.messenger.service;

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
		
		Message m1 = new Message("Hello World 1", "Droid Master 1");
		Message m2 = new Message("Hello World 2", "Droid Master 2");
		
		addMessage(m1);
		addMessage(m2);
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
