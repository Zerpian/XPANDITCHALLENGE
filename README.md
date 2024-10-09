# XPANDITCHALLENGE

This REST API base was created with Spring Initializr, with the options
of Maven Project, Spring Boot 3.3.4 and the java version is 23.

### API Architecture

This API has 3 layers, the controller layer,
the service layer, and the model layer.

The controller layer is responsible for receiving and routing
each request as well as creating the appropriate response to
each request.

The service layer is responsible for fetching any information
from the database, with the help of the repository class, as
well as calling MovieModelAssembler that is a class that wraps Movie
in a EntityModel Class which wraps and encapsulates Movie class and adds hypermedia links to it.

The domain layer is the layer that contain the Movie class, this
class contains the business logic and data,both data and logic
bundled together create the object Movie.

## To Run The Application
First of all, make sure you have the right java version (23). 
You can either open it on one IDE of your choice (I used Intellij) 
and run the main class (XpandItMoviesApplication.java).
You can also run the project by running the jar file that I provided in
"\XpandITChallenge\out\artifacts\XpandITChallenge_jar".
To run it you just need to open the cmd and navigate to the directory specified before.
After you reach the right directory you will run this code:
`java -jar XpandITChallenge.jar`.
To test the application if you don't have some way of making request to it
I also provide it. Make sure you have Postman installed, then you are going to
import the following collection `https://api.postman.com/collections/38777248-5d4d3ee3-053a-4c88-b856-d06885a5e856?access_key=PMAT-01J9RYDM4FDQ85MP040GXSXZVN`.

## Final Thoughts
In the making of this application I also implemented some unit tests, you can find them in the 'test' directory.
I thought about making the Movie an aggregate, however it didn't make sense because the aggregate would be ideal if the movie
also interacted with another model class, but because it doesn't it doesn't make sense to add aggregates.
I also thought about implementing DTO's, however because we don't have aggregates and because we already have a
class that encapsulates the Model class (EntityModel), I decided it was better not to implement DTO's in order to keep things simple.
