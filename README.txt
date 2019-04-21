
To run:
mvn clean dependency:copy-dependencies package && 
java -cp target/task3-1.0-SNAPSHOT.jar;target/dependency/* Main
(Use ; as separator on Windows, : otherwise)