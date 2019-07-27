
# CGI testtask

Used stack: Jdk 1.8, Spring Boot, Spring Data JPA, PostgreSql, JUnit5<br><br>


Before program is executed, database in PostgreSql should be created. <br>
Currently specified settings: <br>
database name:cgi_db <br>
username: postgres <br>
password: postgres <br>
They can be changed in file application.yml <br>
To create tables for the first launch, please put `initialization-mode: always` in application.yml.<br>
Or you can use database dumb cgi.backup, that is attached to this project.
 <br><br>

##### Build
```bash
mvn clean install -DskipTests
```

##### Run
```bash
java -jar cgi-testtask.jar
```

Run browser and type address http://localhost:8080. <br>
Now you can type your name and click button Submit. <br>
At next page you can see your stack, push a number to the stack, pop, reset.