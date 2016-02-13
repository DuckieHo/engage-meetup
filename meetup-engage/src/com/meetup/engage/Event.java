package com.meetup.engage;

import java.util.Date;

public class Event {  
	private String name = "";
	private String id = "";
	private Long time = null;
  
	public Event (String aName, String aID, Long aTime) {
		this.name = aName;
		this.id = aID;
		this.time = aTime;
	}
  
    public String getName() {  
        return name;  
    }
    
    public String getID() {
    	return id;
    }
  
    public long getEpoch() {
    	return time;
    }
    
    public Date getDate() {
    	return new Date(time);
    }
}
