Game of three

pre request
1- java
2- rabbitmq server you can install it by "docker-compose up" you will find docker-composer.yml



how to run 
./mvnw clean install
or if you have maven
mvn clean install 

cd ./target 
player one
java -jar gameofthree-0.0.1-SNAPSHOT.jar --spring.profiles.active=playone

player two
java -jar gameofthree-0.0.1-SNAPSHOT.jar --spring.profiles.active=playtwo --server.port=8081

for unit test please run 
./mvnw test
or if u have maven  
mvn test

Results :

Tests run: 9, Failures: 0, Errors: 0, Skipped: 0


the result of the game will be saved in log terminal becuase i'm logging every game

