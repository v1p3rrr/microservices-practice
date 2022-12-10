# Microservices practice
Microservices backend mock-ish apps for the practice with api gateway (**krakend**)  

Made with **Spring Boot/Hibernate**  
*Service Alpha* simulates providing Taxi Orders information  
*Service Beta* simulates providing Payment information  

## Build and run
To build and run docker compose containers:
```
mvnw package && java -jar target/service-alpha-microservices.jar
mvnw package && java -jar target/service-beta-microservices.jar
docker-compose up 
```

## How to use

Send **POST** requests to `http://localhost:8081/addorder` and `http://localhost:8082/addpayment` for _**order**_ and _**payment**_ respectively. The request will return you an *id* of created object  
Example of required JSON body:  
### Order:  
```json
{
    "startAddress": "Moscow",
    "startLongitude": 1.0412,
    "startLatitude": 34.002,
    "destinationAddress": "St. Petersburg",
    "destinationLongitude": 53.344,
    "destinationLatitude": 324.8689
}
```
### Payment:  
```json
{
    "cost": 133.65,
    "currency": "rub",
    "paymentType": "sberbank"
}
```

### To access the API Gateway:

> Data for order and payment by **specific id** 

Open `http://localhost:8080/v1/orderpayment?id=1` to get all the data for specific *id* that corresponds to both **_payment_** and **_*order*_** *id*  

In case you need to check backend **GET** responses directly: `http://localhost:8081/order?id=1` and `http://localhost:8082/payment?id=1`

> Data for **all existing** orders and payments

Visit `http://localhost:8080/v1/getall` to get all the data. If you want to get direct response from each backend: `http://localhost:8081/allorders` and `http://localhost:8082/allpayments`