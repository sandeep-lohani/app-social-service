# app-social-service

#Introduction 
This is a web project to get,post,like and dislike a story on social site.

#Implementation details
	+ the backend of the application uses core Java SE8
	+ the project compiles on a machine with Maven 3.3 and Java SE8 
	+ the website runs on 8080 port of ‘localhost’ i.e. http://localhost:8080/, via embedded application server 
	+ In memory collection is used to persis the popularity count
  + Input/output data type is JSON
#Set-up
	+ Maven build project using command mvn:install which compiles the application, creates a jar and runs the Junit tests.
	+ Run java command to run this jar java –jar jarname from /target folder

#Usage
##Dependencies
Included dependency for junit 
##How to Run
> Services GET-http://localhost:8080/story/{id} POST-http://localhost:8080/story/{id} PUT-http://localhost:8080/story/{id}/like & http://localhost:8080/story/{id}/dislike
	+ Either hit any of the service URLs on the browser or run the Junit test under test package 
  + Input - Enter note id or leave blank in the search input box provided 
	+ or Hit create new note and enter note in the main area to create notes
	+ or Click on any of the notes in notes list to modify or delete notes 
	+ Result - The notes data is displayed in list format etc.
 
#To do
	+ More Junit tests can be included
	+ More exception handling can be put in place
  + Custome annotation can be created and bind to the service APIs which are now defined under an enum
  + XML support can also be included by passing the accept header and modifying methods to produce/consume cml messages

