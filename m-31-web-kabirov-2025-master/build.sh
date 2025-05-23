echo "building jar file"
mvn clean package

echo "copy jar file"
cp target/student-1.0-SNAPSHOT.jar app.jar

echo "start app"
java -jar app.jar