package com.meetup.engage;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CommentLoader {
	
	public CommentLoader (String aJSON, List<Comment> comments) {
		this(aJSON, comments, "", "", 0L);	
	}

	public CommentLoader (String aJSON, List<Comment> comments, String eventName, String eventId, Long eventTime)  {
		JSONParser parser = new JSONParser();

		try {
			JSONArray results = (JSONArray) parser.parse(aJSON);
			
			int resSize = results.size();
			if (resSize == 0) {
				System.out.println("No comments for this event.");
			} else {				
				for(int i = 0 ; i < resSize; i++) {
					JSONObject row = (JSONObject) results.get(i);	
					System.out.println(row.toString());					
					comments.add(new Comment(row.get("id").toString(),
											Long.parseLong(row.get("created").toString()),
											eventName,
											eventId,
											eventTime)
					);
				}
			}			
		} catch (ParseException e) {
			e.printStackTrace();		
		}
	}
}
