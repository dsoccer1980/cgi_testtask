
# CGI testtask

Used stack: Jdk 1.8, Spring Boot, Spring Data JPA, PostgreSql, JUnit5<br><br>


Before program is executed, database in PostgreSql should be created. <br>
Currently specified settings: <br>
`database name:cgi_db` <br>
`username: postgres` <br>
`password: postgres` <br>
They can be changed in file `application.yml` <br>

To create tables for the first launch, please put `initialization-mode: always` instead of `initialization-mode: never` in `application.yml`.<br>
Or you can use database dumb cgi.backup, that is attached to this project.<br><br>
If you don't have PostgreSql and would like to look, how program works, <br>
please uncomment in file `application.yml` rows after `#To use embedded H2 database` and 
comment rows after `#To use PostgreSql database`



##### Build program
```bash
mvn clean install -DskipTests
```
Move to the folder, where the file `cgi-testtask.jar` was created. `cd target`

##### Run program
```bash
java -jar cgi-testtask.jar
```

Run browser and type address http://localhost:8080. <br>
Now you can type your name and click button Submit. <br>
At next page you can see your stack, push a number to the stack, pop, reset.