package com.vdroidmaster.messenger.resource;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.vdroidmaster.messenger.model.Message;
import com.vdroidmaster.messenger.resource.bean.MessageFilterBean;
import com.vdroidmaster.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	private MessageService messageService = MessageService.getInstance();

	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
		
		Integer year = filterBean.getYear();
		Integer start = filterBean.getStart();
		Integer size = filterBean.getSize();
		
		if (year != null && year > 0) {
			return messageService.getMessagesForYear(year);
		}
		
		if ((start != null && size != null) && start >= 0 && size >= 0) {
			return messageService.getMessagesPaginated(start, size);
		}
		
		return messageService.getMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") Long messageId) {
		return messageService.getMessage(messageId);
	}
	
	@POST
	public Message addMessage(Message message) {
		return messageService.addMessage(message);
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") Long messageId, Message message) {
		message.setId(messageId);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void removeMessage(@PathParam("messageId") Long messageId) {
		messageService.removeMessage(messageId);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}
