## Car Rental Service - backend
Kodilla course - final project. WEB application of car rental service. This is my first made REST API-based application. This is a simple car rental service. 

- To get frontend go to:
https://github.com/HubertJarzynka/car-rental-frontend

### Technologies
- Spring Boot 
- Hibernate
- REST API
- JDBC
- JUnit5
- Mockito
- Vaadin (frontend)
- Lombok

### Features
- Test coverage > 70%
- Two external API's:
    - VinDecoder (for decode vin numbers)
    - EmailVerification (verifying correctness of email used during registration)
- Email sender scheduler (for admin daily information about actual rentals and cars)
- Design patterns:
    - Builder
    - Facade


The app allows users to create an account, log in, search for a car and rent it. 
The app runs on local server 8080. In order to launch the application, it is necessary to perform a few preparation steps:

Please create a MySQL database named car_rental_db and user with the below credentials:

USERNAME = car_admin

PASSWORD = car_password

Generate an API key for Email Verification API:

The API key can be found on this site: https://main.whoisxmlapi.com/ after creating an account.



