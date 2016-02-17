package com.meetup.engage;

import java.util.HashMap;
import java.util.List;

import java.util.stream.Collectors;

public class Streamer {

	//trivial stream for fun	
	public HashMap<String, Long> getGrpComCnts(List<Comment> listComment) {				
		HashMap<String, Long> grpComCnts =
				(HashMap<String, Long>) listComment.stream()
					.sorted((d1, d2) -> Long.compare(d2.getCreated(), d1.getCreated()))
					.filter(g -> g.getEventId().length() != 0)
					.collect(Collectors.groupingBy(Comment::getCreatedyyyyMM, Collectors.counting()));
		
		return grpComCnts;
	}
	
	public HashMap<String, Long> getEvntComCnts(List<Comment> listComment) {				
		HashMap<String, Long> evntComCnts =
				(HashMap<String, Long>) listComment.stream()
					.sorted((d1, d2) -> Long.compare(d2.getCreated(), d1.getCreated()))
					.filter(g -> g.getEventId().isEmpty())
					.collect(Collectors.groupingBy(Comment::getCreatedyyyyMM, Collectors.counting()));
		
		return evntComCnts;
	}

}
