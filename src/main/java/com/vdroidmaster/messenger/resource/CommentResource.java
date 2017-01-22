package com.vdroidmaster.messenger.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.vdroidmaster.messenger.model.Comment;
import com.vdroidmaster.messenger.service.CommentService;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

	private CommentService commentService = CommentService.getInstance();

	@GET
	public List<Comment> getComments(@PathParam("messageId") Long messageId) {
		return commentService.getComments(messageId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") Long messageId, 
							  @PathParam("commentId") Long commentId) {
		return commentService.getComment(messageId, commentId);
	}

	@POST
	public Comment addComment(@PathParam("messageId") Long messageId, Comment comment) {
		return commentService.addComment(messageId, comment);
	}

	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId") Long messageId, 
								 @PathParam("commentId") Long commentId,
								 Comment comment) {
		comment.setId(commentId);
		return commentService.updateComment(messageId, comment);
	}

	@DELETE
	@Path("/{commentId}")
	public void removeComment(@PathParam("messageId") Long messageId, 
							  @PathParam("commentId") Long commentId) {
		commentService.removeComment(messageId, commentId);
	}
}
