#  ✨M50JOfJava✨
## SISTEMA CRM


I rebuilt the CRM system project from scratch using the microservices architecture and creating API paths for each current CLI command.

## Instalación
- Clone the repository on your own computer or download a zip file with the project.
- In the resource folder of each microservice you will find a .json file with the paths. Import the .json file into Postman.
- A .sql file is provided if you need to create the tables required by the CRM on your system.


### SalesRep Service

REST | Route | name  |
| ------| ------ | ------ |
GET | localhost:8081/salesreps | Get all salesReps |
GET | localhost:8081/salesrep/1/id | Get sales reps id |
POST | localhost:8081/salesrep |Create new salesrep|


### Lead Service

REST | Route | name  |
| ------| ------ | ------ |
GET | localhost:8080/leads | Get all leads |
GET | localhost:8080/lead/2 | Get all lead by id |
GET | localhost:8080/leads/bysalesrep/1 | Get all the leads of a salesRep|
GET | localhost:8080/leads/count/bysalesrep | Count list by salesRep|
POST | localhost:8080/lead| Create a lead |
DELETE | localhost:8080/lead/1 | Delete a lead|


### Contact Service
REST | Route | name  |
| ------| ------ | ------ |
GET | localhost:8082/contact/2 | Get contact by id|
POST| localhost:8082/contact/lead/1/account/1 | Create a contact |
PATCH | localhost:8082/contact/6/account-id | Update account id|


### Account Service
REST | Route | name  |
| ------| ------ | ------ |
POST| localhost:8084/account | Create an account |
GET | localhost:8084/account/id | Get account by id |
GET | localhost:8084/accounts | Get accounts|
GET | localhost:8084/accounts/country/germany | Id of accouts by country|
GET | localhost:8084/accounts/city/Helsinki| Id of accouts by city |
GET | localhost:8084/accounts/industry/Produce | Id of accouts by industry|
DELETE | localhost:8084/accounts | Delete all accounts|



### opportunity Service
REST | Route | name  |
| ------| ------ | ------ |
GET | localhost:8083/opportunities | Get all opportunities|
GET | localhost:8083/opportunities?salesrep-id=1 | Get a list with opportunities by sales rep|
GET | localhost:8083/opportunities?salesrep-id=1&status=open | Get a list with opportunities by sales rep and status|
GET | localhost:8083/opportunities?country=spain | Opportunities by country|
GET | localhost:8083/opportunities?city=Oviedo | Opportunities by city |
GET | localhost:8083/opportunities?status=OPEN&industry=MEDICAL | Id of accouts by industry|
GET | localhost:8083/opportunity/3 | Get all opportunity by id|
POST | localhost:8083/opportunity | Get all opportunity by id|
PATCH | localhost:8083/opportunity/3/status | Update status |
PATCH | localhost:8083/opportunity/3/account-id | Update Account id|

Markdown is a lightweight markup language based on the formatting conventions
that people naturally use in email.

### Statistics Service
REST | Route | name  |
| ------| ------ | ------ |
GET | localhost:8083/stats/mean/quantity | Mean of quantity|
GET | localhost:8083/stats/mean/opportunities | Mean of opportunities|
GET | localhost:8083/stats/max/opportunities | Max of opportunities|
GET | localhost:8083/stats/max/quantity | Max quantity |
GET | localhost:8083/stats/min/quantity | Min quantity |
GET | localhost:8083/stats/median/quantity | Median quantity|
GET | localhost:8083/stats/median/opportunities | Median opportunitie|
GET | localhost:8083/opportunities/count/by-salesRep| Opportunities by salesRep|
GET | localhost:8083/opportunities/count/by-salesRep/OPEN | Opportunities by salesRep and status |
GET | localhost:8083/opportunities/count/by-industry/OPEN | Opportunities by industry and status |
GET | localhost:8083/opportunities/count/by-industry | Opportunities by industry|
GET | localhost:8083/opportunities/count/by-city | Opportunities by city |
GET | localhost:8083/opportunities/count/by-city/OPEN| Opportunities by city and status |
GET | localhost:8083/opportunities/count/by-country/OPEN | Opportunities by country and status |
GET | localhost:8083/opportunities/count/by-country | Opportunities by country|
GET | localhost:8083/stats/mean/quantity | Mean of quantity |
GET | localhost:8083/stats/mean/opportunities | Mean of opportunities|
GET | localhost:8083/stats/max/opportunities | Max of opportunities |
GET | localhost:8083/stats/max/quantity | Max quantity opportunities |
GET | localhost:8083/stats/min/quantity | Min quantity opportunities |
GET | localhost:8083/stats/min/opportunities | Min opportunities|
GET | localhost:8083/stats/median/quantity | Median quantity opportunities|
GET | localhost:8083/opportunities/count/by-salesRep | Count opportunities by salesRep|
GET | localhost:8083/opportunities/count/by-salesRep/OPEN | Count opportunities by sales rep and status|
GET | localhost:8083/opportunities/count/by-industry/OPEN| Count opportunities by industry and status |
GET | localhost:8083/opportunities/count/by-industry | Count opportunities by industry|
GET | localhost:8083/opportunities/count/by-city | Count opportunities by city|
GET | localhost:8083/opportunities/count/by-city/OPEN | Count opportunities by city and status |
GET | localhost:8083/opportunities/count/by-country/OPEN| Count opportunities by country and status|
GET | localhost:8083/opportunities/count/by-country | Count opportunities by country|


## Microservices diagram and their interrelationships
![Class Diagram](doc/CRMMicroservices.png)

> TEAM: `Antonio Navarro, Carolina Siguiri, Julia Garcia, Rubén Navarro, Aliany Crespo` 



