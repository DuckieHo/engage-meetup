# meetup-engage


# Purpose
Build a REST interface to get multiple data sources and merge.  Present a graphical representation of data for analytic insight to the coordinator.

A full implementation would ingest all available user interactions (attendance, RSVPs, payments, locations, time, dates, comments, likes, etc) could be weighted, related, and extrapolated.  Coordinators may find it useful to understand what meetups and topics have generated the most communities activity to plan accordingly.  

This particular implementation is in no way complete, correct, efficient, reusable, well-architectured, well factored, etc... but it works... for the most part.

On a personal level, it provided a project for me to spend time with Java 8 features.  Some aspects included are:
1. Implement some streaming/MapReduce functions
2. Utilize core Java's new date library
3. Implement JavaFX components for charting

# Dependencies
1. Apache HTTP: https://httpd.apache.org/download.cgi
2. JavaFX: Included in Java8 but must be added to classpath: .../Java/jre1.8.0_XX/lib/ext/jfxrt.jar
3. JSON.simple: https://code.google.com/archive/p/json-simple/downloads

# Considerations
1. The provided Meetup Java library has not been maintained and will not be used:  https://code.google.com/archive/p/meetup-java-client/
2. Did not implement JUnit
3. Did not implement log4j/LogBack
4. Did not implement Maven/Graddle
5. Hard coded values for KEYFILE, SCHEME, HOST, SLEEPSEC, GROUP_URL, YEAR_BEGIN, YEAR_END
6. Did not implement dynamic throttling of API calls
7. Bar chart overlay of Attendence not implemented.  Organizer permissions is required for the data.
8. Did not include Discussion Boards counts.  Did not realize that group comments and boards were different interfaces.
9. Did not implement preloader or MVC pattern with JavaFX 
 

# Issues
1. The link for "v3 events 'GET /:urlname/events' (last one)" is actually for "POST /:urlname/events"
2. "GET /comments" response has undocumented field of "ratings"
3. Meetup keys are allowed to be passed via unencrypted HTTP 
4. No comment id for group comments.
5. Why is event comments JSON Array while group comments are JSON objects?
6. Are "Former Members" (with ID "0") internally removed?  Having the actual member name would be useful for analytics.
7. Group comments is datetime while event comments is epoch