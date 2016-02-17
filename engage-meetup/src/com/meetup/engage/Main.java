package com.meetup.engage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		final String KEYFILE = "C:/Users/Duckie/workspace-meetup/engage-meetup/keyfile.txt";
		final String SCHEME = "http";
		final String HOST = "api.meetup.com";
		final int SLEEPSEC = 3;		
		final String GROUP_URL = "Meetup-API-Testing";
		
		try {
			KeyGetter keyHolder = new KeyGetter(KEYFILE);
			String key = keyHolder.readKey();
			
			// build a list of a group's events
			// http://www.meetup.com/meetup_api/docs/2/events/
			List<Event> listEvents = new ArrayList<Event>();			
			URIMaker getEvents = new URIMaker(SCHEME, HOST, key);
			getEvents.setPath("/2/events");
			getEvents.setParam(new String[] {"group_urlname","status"}, new String[] {GROUP_URL,"past,upcoming"});
			getEvents.setParam("only", "id,name,time,utc_offset");		
			EventLoader.load(getEvents.getResponse(), listEvents);
						
			// Get comments for each event
			// http://www.meetup.com/meetup_api/docs/:urlname/events/:event_id/comments/#list
			List<Comment> listComments = new ArrayList<Comment>();
			
			for (Event e : listEvents) {
				URIMaker getEventCom = new URIMaker(SCHEME, HOST, key);
				getEventCom.setPath("/" + GROUP_URL + "/events/" + e.getID().toString() + "/comments");
				getEventCom.setParam("only", "created,id");
				CmntEvntLoader.load(getEventCom.getResponse(), listComments, e.getName(), e.getID(), e.getEpoch());				
				Thread.sleep(SLEEPSEC * 1000);
			}
			
			// An alternative to the methods above would be to use the following API.  Original requirements
			// had required at event level ID first.
			// https://secure.meetup.com/meetup_api/docs/2/event_comments/
						
			// Get comments for the group
			// https://secure.meetup.com/meetup_api/docs/comments/
			URIMaker getGroupCom = new URIMaker(SCHEME, HOST, key);
			getGroupCom.setPath("/comments");
			getGroupCom.setParam(new String[] {"group_urlname"}, new String[] {GROUP_URL});
			getGroupCom.setParam("only", "created,comment,name");
			CmntGrpLoader.load(getGroupCom.getResponse(), listComments, true);
			
			
			Streamer stream = new Streamer();
			
			// Incorrect implementation of JavaFX being is tightly coupled and terrible implementation.
			// To do: Implement FXML to inject the data
			String evntArrStr = "";			
			for (Map.Entry<String, Long> entry : stream.getEvntComCnts(listComments).entrySet()) {
				evntArrStr = evntArrStr + "|" + entry.getKey() + "," + entry.getValue();
			}
			String grpArrStr = "";			
			for (Map.Entry<String, Long> entry : stream.getGrpComCnts(listComments).entrySet()) {
				grpArrStr = grpArrStr + "|" + entry.getKey() + "," + entry.getValue();
			}
			SALineCharter.launch(SALineCharter.class, evntArrStr, grpArrStr);			
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}		
	}

}
