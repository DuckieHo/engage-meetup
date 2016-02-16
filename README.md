# meetup-engage

# Assumptions:
The provided Java library has not been maintained and will not be used:  https://code.google.com/archive/p/meetup-java-client/

# Dependencies
Apache HTTP
JavaFX
JSON.simple
AxisDate since JavaFX does not support datetime axis: https://bitbucket.org/sco0ter/extfx/src/a61710e99c63bfa288672f0d99861c8fe8571293/src/main/java/extfx/scene/chart/DateAxis.java?at=0.3&fileviewer=file-view-default

# Purpose
Learn Java 8 features:

Streaming/MapReduce
JavaFX

New Date API



# Considerations
Did not implement dynamic throttling
Did not implement log4j/LogBack
Did not implement Maven/Graddle
Did not implement JUnit
Bar chart overlay of Meetup Attendence dropped.  Organizer permissions is required for the data. 


# Erratas
"GET /:urlname/events" is actually "POST /:urlname/events" => http://www.meetup.com/meetup_api/docs/:urlname/events/

"GET /comments" response has undocumented field of "ratings"

Keys sent via unprotected HTTP? 

No comment id for group comments.

Why is event comments JSON Array while group comments are JSON objects?

Are "Former Members" (with ID "0") removed?

Group comments is datetime while event comments is epoch