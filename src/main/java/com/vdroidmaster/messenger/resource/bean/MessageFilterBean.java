package com.vdroidmaster.messenger.resource.bean;

import javax.ws.rs.QueryParam;

public class MessageFilterBean {

	private @QueryParam("year") Integer year;
	private @QueryParam("start") Integer start;
	private @QueryParam("size") Integer size;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
}
