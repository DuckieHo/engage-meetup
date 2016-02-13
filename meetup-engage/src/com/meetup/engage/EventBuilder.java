package com.meetup.engage;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class EventBuilder {
	List<Event> listEvents = new ArrayList<Event>();
	
	public EventBuilder (String aJSON) {
		try{
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(aJSON);
			JSONArray results = (JSONArray) obj.get("results");
			 			
			Iterator<?> i = results.iterator(); 
			while(i.hasNext()){
				JSONObject response = (JSONObject) i.next();
				System.out.println(response.get("name") + " | " + response.get("id") + " | " + response.get("time"));			
				listEvents.add(
						new Event(response.get("name").toString(),
								  response.get("id").toString(),
								  Long.parseLong(response.get("time").toString())
						)
				);
				i.next();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Event> getEventList () {
		return listEvents;
	}
	
}
