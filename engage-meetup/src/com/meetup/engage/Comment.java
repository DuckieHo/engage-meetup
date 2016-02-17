package com.meetup.engage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {  
	private String commentId = null;
	private Long created = null;
	private String eventName = null;
	private String eventId = null;
	private Long eventTime = null;
  	 
	
	public Comment (String commentId, Long created, String eventName, String eventId, Long everntTime) {	
		this.commentId = commentId;
		this.created = created;
		this.eventName = eventName;
		this.eventId = eventId;
		this.eventTime = everntTime;
	}
			
	public Long getCreated() {
		return created;
	}
	
	public Date getCreatedDate() {
		return new Date(created);
	}
	
	public String getCreatedyyyyMM() {
		Date datetime = new Date(created);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		String date_yyyyMM = formatter.format(datetime);
		return date_yyyyMM;
	}
	
	public String getCommentId() {
		return commentId;
	}
	
	public String getEventName() {
		return eventName;
	}
	
	public String getEventId() {
		return eventId;
	}
	
	public Long getEventTime() {
		return eventTime;
	}
}
