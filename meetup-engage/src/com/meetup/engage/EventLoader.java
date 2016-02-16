package com.meetup.engage;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class EventLoader {
	
	public EventLoader (String aJSON, List<Event> events ) {
		JSONParser parser = new JSONParser();
		try{
			JSONObject obj = (JSONObject) parser.parse(aJSON);
			JSONArray results = (JSONArray) obj.get("results");
			 			
			Iterator<?> i = results.iterator(); 
			while(i.hasNext()){
				JSONObject response = (JSONObject) i.next();				
				System.out.println(response.toString());			
				events.add(new Event(response.get("name").toString(),
									response.get("id").toString(),
									Long.parseLong(response.get("time").toString()))
				);
				i.next();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
}
