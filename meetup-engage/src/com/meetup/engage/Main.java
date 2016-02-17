package com.meetup.engage;

import java.util.ArrayList;
import java.util.List;

// Utilize streams

public class Main {
	public static void main(String[] args) {
		final String KEYFILE = "C:/Users/Duckie/workspace/Test/keyfile.txt";
		final String SCHEME = "http";
		final String HOST = "api.meetup.com";
		final int SLEEPSEC = 5;
		
		final String groupURL = "Meetup-API-Testing";
		
		try {
			KeyGetter KeyHolder = new KeyGetter(KEYFILE);
			String key = KeyHolder.readKey();
			
			// build a list of a group's events
			// http://www.meetup.com/meetup_api/docs/2/events/
			List<Event> listEvents = new ArrayList<Event>();			
			URIMaker getEvents = new URIMaker(SCHEME, HOST, key);
			getEvents.setPath("/2/events");
			getEvents.setParam(new String[] {"group_urlname","status"}, new String[] {groupURL,"past,upcoming"});
			getEvents.setParam("only", "id,name,time,utc_offset");		
			@SuppressWarnings("unused")
			EventLoader events = new EventLoader(getEvents.getResponse(), listEvents);
						
			// load comments for each event
			// http://www.meetup.com/meetup_api/docs/:urlname/events/:event_id/comments/#list
			List<Comment> listComments = new ArrayList<Comment>();
			int i = 0;
			for (Event e : listEvents) {
				URIMaker getEventCom = new URIMaker(SCHEME, HOST, key);
				getEventCom.setPath("/" + groupURL + "/events/" + e.getID().toString() + "/comments");
				getEventCom.setParam("only", "created,id");
				new CommentLoader(getEventCom.getResponse(), listComments, e.getName(), e.getID(), e.getEpoch());				
				Thread.sleep(SLEEPSEC * 1000);
				i++;
				if (i>2) {
					break;
				}
			}
			
			// An alternative to the methods above would be to use the following API.  Original requirements
			// had required at event level ID first.
			// https://secure.meetup.com/meetup_api/docs/2/event_comments/
						
			// load comments for the group
			// https://secure.meetup.com/meetup_api/docs/comments/
			URIMaker getGroupCom = new URIMaker(SCHEME, HOST, key);
			getGroupCom.setPath("/comments");
			getGroupCom.setParam(new String[] {"group_urlname"}, new String[] {groupURL});
			getGroupCom.setParam("only", "created,comment,name");
			new CommentGrpLoader(getGroupCom.getResponse(), listComments, true);
			
			Streamer chart = new Streamer(listComments);
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}		
	}

}
