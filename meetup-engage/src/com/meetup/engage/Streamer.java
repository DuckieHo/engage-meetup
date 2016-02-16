package com.meetup.engage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Streamer {

	public Streamer(List<Comment> listComment) {
		
		System.out.println("TEST after");
		for (Comment c : listComment) {
			System.out.println("ID: "+c.getCommentId());
			System.out.println("Created: "+c.getCreated());
			System.out.println("EName: "+c.getEventName());
			System.out.println("EID: "+c.getEventId());
			System.out.println("ETime: "+c.getEventTime());
			
		}
				
		HashMap<String, Object> grpComCounts =
				listComment.parallelStream()
							.sorted((d1, d2) -> Long.compare(d1.getCreated(), d2.getCreated()))
							//.filter(g -> g.getEventId().isEmpty())
							//.forEachOrdered(g -> System.out.println(g.getCommentId()));
							.collect(Collectors.groupingBy(Comment::getCreated, Collectors.counting(Comment::getCommentId)));
		
	}

}
