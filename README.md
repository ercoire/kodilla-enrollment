### Background

This solo project has been submitted as the final project for Kodilla bootcamp September 2023. The application manages enrollment for classes for a dance school named Happy Steps.

##### Prerequisites
- Java 17 or higher
- MySQL database with user having all privileges granted

##### Other technologies used:
- Spring Boot
- Hibernate
- Vaadin
- Lombok
- JUnit


### Installation and running
Before running the application you need to set the following environment variables: 

DATABASE_NAME, DB_USERNAME, DB_PASSWORD
MAIL_USERNAME, MAIL_PASSWORD



##### Backend: 
`git clone git@github.com:ercoire/kodilla-enrollment.git`


#### Frontend: 
`git clone git@github.com:ercoire/kodilla-enrollment-frontend.git`


Repository also available in [kodilla-enrollment-frontend](https://github.com/ercoire/kodilla-enrollment-frontend)


#### Database: 
* To add database audit use scripts available in Trigger scripts directory


#### Run
* To START application

  - `./gradlew bootRun` for backend 

  - `./gradlew bootRun` for frontend

* Access the application at http://localhost:3000

### External API

The app is integrated with [World Swing Dance Council](https://www.worldsdc.com/registry-points/) that fetches data regarding dance levels for West Coast Swing dancers. 
WCS is main dance style offered in Happy Steps and students are advised for the most suitable level based on their record in WSDC.

### Contact
For questions or comment please drop me a line at [k.gierasimczuk@gmail.com]()