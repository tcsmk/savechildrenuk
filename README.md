# SaveChildren - Java Spring RESTful API Test

## Below are the list of tools/softwares used to develop the project

Java : 1.8
Spring Boot : 2.7.15
Junit : 4.13.2
database : h2 (in memory)
IDE : intellij
PlugIns : sonarlint, Lombok

## How to build
```mvn clean install```

## Below are the endpoints to test this API
# create new user
 API:  http://localhost:8083/user/createUser
 Method: POST
 Sample Json:
 {
 "firstName": "Ronald",
 "lastName": "Tim",
 "email": "Ronald.tim@outlook.com",
 "dateOfBirth": "1980-06-09",
 "gender": "M",
 "location": "London",
 "phoneNumber": "6755453897"
 }

# Get users
API:  http://localhost:8083/user/getUsers
Method: GET

# Get users By Id
API:  http://localhost:8083/user/getuserbyid/{userId}
Method: GET

# Delete user
API:  http://localhost:8083/user/deleteuser/{userId}
Method: DELETE

# Update User
API:  http://localhost:8083/user/updateuser
Method: PUT

## End to end Integration Test Class
package com.uk.savechidrenuk.controller.UserControllerIT





