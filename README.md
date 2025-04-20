## Run
The Service can be run by using the flowing shell script

```shell script
./mvnw quarkus:dev
```
The server will start on port 8080. 

## How to hit the endpoints
When the server is running, then the endpoints can be reached on localhost:8080. 
I have added a postman collection bankdata-account-service.postman_collection.json 
which can be imported in postman for more details. 

## Run tests
Tests can be run by running the maven test task.

## Future development
The service is implemented with the goal of having low coupling - making it easier to replace parts 
with a different implementation. As an example consider if we wanted to replace the Accounts with 
a transaction-based account implementation. Because the AccountEntity is isolated to the AccountService, 
can this be done by reimplementing the entity and the AccountService. 
Because the interface of the AccountService does not change, 
can this be done without changing tests or implementation of the resource. 
