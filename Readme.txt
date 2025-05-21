//combine source code
mvn clean install

//package into .jar
mvn clean package

//deploy ActiveMQ in the local

//run (example)
java -jar .\target\MQTTSubscriber-1.0-SNAPSHOT.jar  -IP localhost -Port 1883 -Topic V5008Upload/#


