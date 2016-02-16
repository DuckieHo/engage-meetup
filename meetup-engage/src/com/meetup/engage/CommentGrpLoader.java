package com.meetup.engage;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CommentGrpLoader {
	
	public CommentGrpLoader (String aJSON, List<Comment> comments, Boolean noID) {
		this(aJSON, comments, "GROUP", "", 0L, noID);	
	}


	public CommentGrpLoader (String aJSON, List<Comment> comments, String eventName, String eventId, Long eventTime, Boolean noID)  {
		JSONParser parser = new JSONParser();		

		try {
			JSONObject obj = (JSONObject) parser.parse(aJSON);
			JSONArray results = (JSONArray) obj.get("results");
			
			Iterator<?> i = results.iterator(); 
			while(i.hasNext()){
				String id = null;
				JSONObject response = (JSONObject) i.next();				
				System.out.println(response.toString());			
				
				if (noID) {
					id =  Integer.toString((response.get("created").toString() + response.get("name").toString() + response.get("comment").toString()).hashCode());
				} else {
					id = response.get("id").toString();
				}				
				
				// Sample date: Thu Nov 19 20:10:58 EST 2015
		        TemporalAccessor tAcc  = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy").parse(response.get("created").toString());
		        LocalDateTime ldt = LocalDateTime.from(tAcc);
		        Long epoch = ldt.toEpochSecond(ZoneOffset.UTC);
								
				comments.add(new Comment(id,
										epoch,
										eventName,
										eventId,
										eventTime)						
				);
				i.next();
			}
		} catch (ParseException e) {
			e.printStackTrace();		
		}
	}
}
