### How to run
The project is a gradle project written using IntelliJ.
Import it in any IDE you use and you should be able to run it using the IDE.
Alternatively you can use the command line.
* Navigate to the root folder `SkyParentalControl`
* Run `./gradlew bootRun`

The project should start on port 8080.

### How to use
Use a browser, or Postman, or the terminal and make the following **GET** request:
http://localhost:8080/v0/user/ce38a0b4-862f-456e-a08a-91f08181eb00/watch/c1ccf910-2ecc-4018-b476-5e8957b5ba00/permission
```
UserId                                  Rating Preference
ce38a0b4-862f-456e-a08a-91f08181eb00    U
ce38a0b4-862f-456e-a08a-91f08181eb10    PG
ce38a0b4-862f-456e-a08a-91f08181eb12    12
ce38a0b4-862f-456e-a08a-91f08181eb15    15
ce38a0b4-862f-456e-a08a-91f08181eb18    18

MovieId                                 Rating
c1ccf910-2ecc-4018-b476-5e8957b5ba00    U
c1ccf910-2ecc-4018-b476-5e8957b5ba10    PG
c1ccf910-2ecc-4018-b476-5e8957b5ba12    12
c1ccf910-2ecc-4018-b476-5e8957b5ba15    15
c1ccf910-2ecc-4018-b476-5e8957b5ba18    18

Non existing movie id
313aac09-c0cd-46e4-9a2d-f358f197ba39 or some other randomg UUID
```

### Assumptions and Random thoughts I had while solving it
It wasn't clear to me whether the `client` is a device that communicates with 
our service over a REST API. The request was just to implement the 
ParentalControlService API. So, I assumed that as most things today are over
the Internet.

Another assumption I made was that there are users registered with this
service so some kind of identification would be required and that this whole
process would be part of their journey. So instead of having an endpoint that
takes a user set rating and a movie id, it made more sense to me that a user
is requesting to watch a movie and the system would know how to retrieve their
profile from which their rating preference would be retrieved and then compare
it against the rating of the movie. For that purpose I created a very basic
repository class that has some hardcoded values for the purposes of this
solution.

The exercise stated that the movie service is being worked on by another team.
I made it so that the service is part of the solution with a very basic
implementation (hardcoded) just to showcase it but having the interface would
be enough to develop the solution as long as the interface was decided between 
the teams and nothing changed.

For the ids I used UUIDs. I kept the movie id needed in the movie service as
a string because that was the contract and wasn't sure how strict you want 
to be. In real life it would be a discussion topic and if it was different 
microservices it could be a discrepancy that needed to be noted in the 
communication.

A thought I had was that the movie service could be a different microservice
in an internal network of microservices that communicate so our development
would be same but instead of using an interface we would have another client
that would talk to the movie microservice. The interface given then would just
be the contract/API.