MyRetail Restful Service

The repository contains source code for MyRetail RESTFUL service. The service provides GET and PUT functionality .

GET product id, name, and pricing for an id passed as input
UPDATE pricing for an id passed as an input along with response body in database

Technlogies Used:

Spring Boot
MongoDb
Gradle
Java8
Git
Intellij
Postman

DataBase Setup

The mongo shell is an interactive JavaScript interface to MongoDB. You can use the mongo shell to query and update data as well as perform administrative operations.
There is also a tool called Compass which can be used to Insert/update data to the database.

To start the mongo shell and connect to your MongoDB instance running on localhost with default port:

At a prompt in a terminal window (or a command prompt for Windows), go to your : cd <mongodb installation dir>

Type ./bin/mongo to start mongo: ./bin/mongo

Type the following command to create a new database - myRetaildb use myRetaildb

Create pricing document and insert the follwoing data :-

 db.pricing.insert({ item: "13860428", value: 50, currency_code: "USD" })
		 ]
		 )

STEPS TO RUN THE APPLICATION:

* Run the application through IDE
* Start the Mongo Service
* For GET request in Postman, please use the following URL : http://localhost:8080/products/13860428
  In the Header tab , give the content type as Application/json and hit the send button.
 * It should return the JSON in the body.

 {
     "id": "13860428",
     "name": "The Big Lebowski (Blu-ray)",
     "current_price": {
         "value": 50,
         "currency_code": "USD"
     }
 }

 * For PUT request in postman use the following URL : http://localhost:8080/products/13860428
 * Provide the following data in body and hit the send button.

 {
     "id": "13860428",
     "name": "The Big Lebowski (Blu-ray)",
     "current_price": {
         "value": 30,
         "currency_code": "USD"
     }
 }

 The value should get updated accordingly in db.
