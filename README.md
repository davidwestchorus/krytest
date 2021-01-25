# Kry code test

#### Technologies used
Java 8

Kotlin 1.4.21

[Ktor](https://ktor.io/) 1.5.0

[Exposed](https://github.com/JetBrains/Exposed) 0.8.5 (ORM framework) 

MySQL 8.0.22

(Intellij Idea 2020.3.1)
### Installation

Have a running mysql locally on standard port 3306 

Run these commands to setup the database:
```
CREATE DATABASE krytest;

CREATE USER 'krytest'@'localhost' IDENTIFIED WITH mysql_native_password BY 'krytest';

GRANT ALL PRIVILEGES ON krytest.* TO 'krytest'@'localhost';
```


### How it works

I tried doing this assignment in a more functional way with the fairly new framework Ktor. This is the first time i try it out.

`Application.kt`
Main application class with some configuration.

`Service.kt`
Both serves as the Entity to save in the database and also table definition for Exposed.

`ServiceDTO`
Data transfer object for Service-class.

`ServiceResponseDTO`
Data transfer object for a ServiceResponse.

`ServicesRoutes.kt`
The routes to call for getting servies, adding a service, and also the "mock"-endpoint for "answering services"

`DatabaseFuncs.kt`
Connection config for database as well as functions for saving a service, fetching all services and updating a service in regards to serviceresponse (UP/FAIL)

`PollingFuncs.kt`
These functions handles the polling of the services via an http-client.

`ServicesFuncs.kt`
Addservice forwards the service that needs to be persisted to databasefuncs and converts the answer to a DTO to send to the client.
GetAllServices gets the list of all services from databasefuncs, sorts them and maps them to DTO's ready to send to the client.

`HttpClient.kt`
Config for the httpclient.

`index.html`
A very rudimentary web page to list and add services :)

`script.js`
Where the magic happens (also very rudimentary)
