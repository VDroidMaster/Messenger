package com.vdroidmaster.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

import com.vdroidmaster.messenger.database.DatabaseClass;
import com.vdroidmaster.messenger.model.Comment;
import com.vdroidmaster.messenger.model.Message;

public class CommentService {

	private static CommentService commentService = new CommentService();
	private NavigableMap<Long, Message> messages = DatabaseClass.getMessages();
	
	private CommentService() {
	
		Comment c1 = new Comment("Comment1", "Droid Master1");
		Comment c2 = new Comment("Comment2", "Droid Master2");
		
		addComment(Long.valueOf(1), c1);
		addComment(Long.valueOf(2), c2);
	}
	
	public static CommentService getInstance() {
		return commentService;
	}
	
	public List<Comment> getComments(Long messageId) {
		
		if (messages.containsKey(messageId)) {
			return new ArrayList<Comment>(messages.get(messageId).getComments().values());
		}
		return new ArrayList<Comment>();
	}
	
	public Comment getComment(Long messageId, Long commentId) {
		
		if (messages.containsKey(messageId)) {
			if (messages.get(messageId).getComments().containsKey(commentId)) {
				return messages.get(messageId).getComments().get(commentId);
			}
		}
		return null;
	}
	
	public Comment addComment(Long messageId, Comment comment) {
		
		if (messages.containsKey(messageId)) {
			
			NavigableMap<Long, Comment> comments = messages.get(messageId).getComments();
			Long lastCommentId = Long.valueOf(0);
			
			if (!comments.isEmpty()) {
				lastCommentId = comments.lastKey();
			}
			
			comment.setId((Long.valueOf(lastCommentId + 1)));
			
			messages.get(messageId).getComments().put(comment.getId(), comment);
		}
		return comment;
	}
	
	public Comment updateComment(Long messageId, Comment comment) {
		
		if (messages.containsKey(messageId) && comment.getId() > 0 &&
				messages.get(messageId).getComments().containsKey(comment.getId())) {
			
			if (messages.get(messageId).getComments().replace(comment.getId(), comment) != null) {
				return comment;
			} else {
				return null;
			}
		}
		return null;
	}
	
	public void removeComment(Long messageId, Long commentId) {
		
		if (messages.containsKey(messageId) && commentId > 0 &&
				messages.get(messageId).getComments().containsKey(commentId)) {
			
			messages.get(messageId).getComments().remove(commentId);
		}
	}
}
