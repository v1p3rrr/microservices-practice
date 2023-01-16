cd ServiceAlpha
call mvnw package && java -jar target/service-alpha-microservices.jar
cd ../ServiceBeta
call mvnw package && java -jar target/service-beta-microservices.jar
cd ..
docker-compose down
docker image rm -f microservices-practice-alpha
docker image rm -f microservices-practice-beta
docker-compose build

pause