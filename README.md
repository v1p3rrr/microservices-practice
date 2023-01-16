# Microservices practice
Microservices backend mock-ish apps for the practice with api gateway (**krakend**)  

Made with **Spring Boot**  
*Service Alpha* simulates providing Taxi Orders information  
*Service Beta* simulates providing Payment information  

## Build and run
To build and run docker compose containers use commands below or or simply run ```build_images.bat``` file and type `docker-compose up -d` after it finishes its work.
```
cd ServiceAlpha
mvnw package && java -jar target/service-alpha-microservices.jar
cd ../ServiceBeta
mvnw package && java -jar target/service-beta-microservices.jar
cd ..
docker-compose down
docker image rm -f microservices-practice-alpha
docker image rm -f microservices-practice-beta
docker-compose up -d
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
(need to manually specify payment id which corresponds to generated to taxiorder id in order to make it marked as paid) 
```json
{
    "id": 1,
    "cost": 132.65,
    "currency": "rub",
    "paymentType": "sberbank",
    "commissionPercent": 10
}
```

### To access the API Gateway:

> Data for order and payment by **specific id** 

Open `http://localhost:8080/v1/orderpayment?id=1` to get all the data for specific *id* that corresponds to both **_payment_** and **_*order*_** *id*  

In case you need to check backend **GET** responses directly: `http://localhost:8081/order?id=1` and `http://localhost:8082/payment?id=1`

> Data for **all existing** orders and payments

Visit `http://localhost:8080/v1/getall` to get all the data. If you want to get direct response from each backend: `http://localhost:8081/allorders` and `http://localhost:8082/allpayments`  

## Monitoring

Prometheus is available at `http://localhost:9090`, Graphana is available at `http://localhost:3000`  

Jaeger currently only catches traces of krakend, need to set up services out of containers for it to see them. It's avaiable at `http://localhost:16686`  

Zabbix is available at `http://localhost:80`, it is used only for healthchecking purpose. Just add new host and web scenario with a steps of calling certain (`/actuator/health` or `/v1/healthcheck` for krakend) endpoints at aforementioned services ports. 

ELK, just as Jaeger, doesn't see services inside the containers, so you have to run them directly from IDE in order to collect logs. Kibana web interface is at `http://localhost:5601`, logstash is at `http://localhost:9600`, elasticsearch is at `http://localhost:9600`  

## Authorization

Keycloak web interface is available at `http://localhost:8443/auth/`, although at its first start you have to open its container terminal and run these commands:
```
cd opt/jboss/keycloak/bin
bash add-user-keycloak.sh -u admin -p admin
```
Then restart the container.  
After that go to the web interface and sign in, press **Add realm** in top left corner, **Import** `realm-export.json` file from project directory `config/keycloak`. Then open **Users**, press **Add new user**, specify its name, save it. Then open **credentials** tab, specify the password, uncheck **Temporary** flag (or remove **change password** tag in user properties if you forgot to uncheck it). In the end open **Realms settings**, in the **Endpoints** section of **General** tab press on the **OpenID Endpoint Configuration** text and copy the link in  `token_endpoint`. 

Now  you open Postman, open **Authorization tab**, choose **OAuth2** type, grant type - **Password credentials**, paste the link you copied one step before to the **Access token url** field. Specify username and password for the user you have set earlier, type `openid` in **scope** field. Now you can press to use this token, and type your request to the krakend endpoint you closed for authorized access. You can also try to call same request from the adjacent tab without specifying the access token, you should get `401 error code`.

Note: to secure your krakend endpoint with authorization access, you need to add this extra config inside your endpoint object, where in `jwk_url` field you put down the link from the json field `jwks_uri` you get in **OpenID Endpoint Configuration** as it was mentioned above and change `localhost` to `host.docker.internal` (tried to change it to the keycloack service name from docker-compose.yaml but it didn't work).
```
"endpoint": "...",
"backend": [ ... ],
"extra_config": {
    "auth/validator": {
        "alg": "RS256",
        "jwk_url": "http://host.docker.internal:8443/auth/realms/krakend/protocol/openid-connect/certs",
        "disable_jwk_security": true,
        "operation_debug": true
    }
    }
```