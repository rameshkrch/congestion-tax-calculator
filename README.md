# congestion-tax-calculator developed with Springboot REST Api service.

## Prerequisite:
Java 11
Maven
Postman

## REST Endpoint:
### API URL: http://localhost:8080/tax-service/tax
### API method: GET
### API Content Type: json

###Input json:
{
    "vehicleType": "Car",
    "dates": [
        "2013-02-08 06:27:00","2013-02-08 06:20:27",
        "2013-02-08 14:35:00","2013-02-08 15:29:00",
        "2013-02-08 15:47:00","2013-02-08 16:01:00",
        "2013-02-08 16:48:00","2013-02-08 17:49:00",
        "2013-02-08 18:29:00","2013-02-08 18:35:00"
    ]
}

### Output json:
{
    "tax": 60
}

## Application run and test:
Clone the repo.
Run mvn package.
Change directory to congestion-tax-calculator\target
Run java -jar target/congestion-tax-calculator-0.0.1-SNAPSHOT.jar
Use Postman to send Request body.

## Improvement and changes done:
Added entry point over HTTP.
Calculation bugs fixed.
Springboot framework implemented.
Microservice created for Tax calculation.
Rest Controller, Service and Request/Response objects added.
