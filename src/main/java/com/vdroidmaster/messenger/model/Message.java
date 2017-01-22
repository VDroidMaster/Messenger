package com.vdroidmaster.messenger.model;

import java.util.Date;
import java.util.NavigableMap;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Message {

	private Long id;
	private String message;
	private String author;
	private Date created;
	private NavigableMap<Long, Comment> comments;

	public Message() {
	}

	public Message(String message, String author) {
		this.message = message;
		this.author = author;
		this.created = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@XmlTransient
	public NavigableMap<Long, Comment> getComments() {
		
		if(this.comments == null) {
			this.comments = new TreeMap<Long, Comment>();
		}
		return this.comments;
	}

	public void setComments(NavigableMap<Long, Comment> comments) {
		this.comments = comments;
	}
}
