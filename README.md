# sample-accounting-app
Simple accounting application

This is a spring boot application which uses H2 in-memory database as its storage.
To start the app, just clone the repository, open it in an IDE and run the main class or alternatively deploy the jarboot on an application server.
endpoints:
  POST localhost:8080/persons
  POST localhost:8080/accounts
  PUT localhost:8080/persons/{personId}
  POST localhost:8080/money-transfer
