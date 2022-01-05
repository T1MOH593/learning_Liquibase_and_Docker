FROM tomcat
COPY . /app
ENTRYPOINT ["java","-jar","/app/build/libs/liquibase_starter-0.0.1-SNAPSHOT.jar"]