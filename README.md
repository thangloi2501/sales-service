# Demo Sales Service

## Scenario
My assumptions are:
- We are building Demo Sales microservice
- There is an Order REST API that supports importing CSV files via POST method: `api/v1/orders/import`
- This is an internal business service, so there will be a reverse proxy/API gateway to control security and authentication.
- Our database is SQL Server
- Columns: [Category, SubCategory, Segment, ShipMode] in csv file are enum type

## Requirements
For building and running the application you need:
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Tech stacks
- Java 8
- Maven
- Spring Boot
- Hibernate (to support later if we want to go with MySQL or Oracle)
- Spock/Groovy for unit test
- Lombok, MapStruct, StreamEx, OpenCSV

## Running the application
Update `spring.datasource` from configuration file: `src/main/resources/application.yaml` to connect to your SQL Server and your credentials. Currently, the configuration set `ddl-auto: update` to make sure the database table is synchronized with the app. Update `server.port` or `format.date` if needed.

Update logging configuration in `src/main/resources/logback.xml` if needed. Currently the log file is under `log` folder.

There are 2 ways to run the service:

1. Build and run standalone service
Go to project folder and run `./build-jar.sh`, then run `./run-service.sh`

2. Build docker image
Go to project folder and run `./build-docker-image.sh` (make sure to expose the service port and configure db connection)

## Test the service
`POST http://localhost:8080/api/v1/orders/import`

Request: 
  request param: `file` - MultipartFile
  
Response:
```
{
    "version": "1.0",
    "timestamp": 1633195269740,
    "data": {
        "successRows": [
            {
                "rowId": 1,
                "orderId": "CA-2016-152156"
            },
            ...
        ],
        "errorRows": [
            {
                "rowId": 2,
                "orderId": "CA-2016-152156",
                "error": "ValidationError(name=productId, message=Field must be provided.)"
            },
            ...
        ]
     }
}                
```

CURL: `curl --location --request POST 'http://localhost:8080/api/v1/orders/import' --form 'file=@"/path_to_file/sales.csv"'`


## Contact
Loi Nguyen - thangloi2501@gmail.com
