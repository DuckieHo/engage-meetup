package com.meetup.engage;

import java.util.List;

// Utilize streams

public class Main {
	public static void main(String[] args) {
		final String KEYFILE = "C:/Users/Duckie/workspace/Test/keyfile.txt";
		final String SCHEME = "http";
		final String HOST = "api.meetup.com";
		

		
		try {
			KeyGetter KeyHolder = new KeyGetter(KEYFILE);

			// build a list of a group's events
			final String path = "/2/events";
			final String parm = "group_urlname";
			//final String parmVal = "Meetup-API-Testing";
			final String parmVal = "ny-tech";	
			final String filterOnly = "only";
			final String filterVal = "id,name,time,utc_offset";
			
			URIMaker getEvents = new URIMaker(SCHEME, HOST, KeyHolder.readKey());
			getEvents.setPath(path);
			getEvents.setParam(parm, parmVal);
			getEvents.setParam(filterOnly, filterVal);		
			EventBuilder events = new EventBuilder(getEvents.getResponse());
			List<Event> listEvents = events.getEventList();
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}		
		// Metrics
		// Event Comments
		// Event Comment Likes
		// Attendence
		
		//Get Event
		//Event Comments List

		// Get Group Members
		//
		// make request
	}

}
