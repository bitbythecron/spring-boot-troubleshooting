# spring-boot-troubleshooting

## Requirements
- [ ] Java 8
- [ ] MySQL v8

### Installing & Configuring MySQL
```
brew update
brew install mysql
unset TMPDIR
mysql -uroot
CREATE DATABASE IF NOT EXISTS troubleshooting_db CHARACTER SET utf8 COLLATE utf8_general_ci;
SET default_storage_engine=INNODB;
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '12345';
```


## Building & Running
### Building
```
./gradlew clean build
```

This builds a Spring Boot fat jar.

### Running
```
./gradlew clean build && java -Dspring.config=. -jar build/libs/spring-boot-troubleshooting.jar
```

This builds and runs the app. When it starts up, Liquibase **does not excute/engage** and so Hibernate JPA validation fails
because its looking for a table that doesn't exist (because Liquibase never kicked in and did its job!):

```
Caused by: org.hibernate.tool.schema.spi.SchemaManagementException: Schema-validation: missing table [metric_range_categories]
	at org.hibernate.tool.schema.internal.SchemaValidatorImpl.validateTable(SchemaValidatorImpl.java:67)
	at org.hibernate.tool.schema.internal.SchemaValidatorImpl.doValidation(SchemaValidatorImpl.java:50)
	at org.hibernate.tool.hbm2ddl.SchemaValidator.validate(SchemaValidator.java:91)
	at org.hibernate.internal.SessionFactoryImpl.<init>(SessionFactoryImpl.java:475)
	at org.hibernate.boot.internal.SessionFactoryBuilderImpl.build(SessionFactoryBuilderImpl.java:444)
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:879)
	... 29 common frames omitted
```