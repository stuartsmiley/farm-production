# farm production:
========================
* Technologies: CDI, JPA, EJB, JPA, JAX-RS, Bean Validation
* Summary: Tracks farm production
* Target Appserver: WildFly
* Source: <https://github.com/wildfly/quickstart/>

## Starting point
-----------

There is a tutorial for the wildfly quickstart in the [Getting Started Developing Applications Guide](https://github.com/wildfly/quickstart/guide/production/).

## System requirements
-------------------

Java 11 (Java SDK 11) or better, apache-maven-3.6.2 or better.

The application this project produces is designed to be run on JBoss WildFly.


## Start JBoss WildFly with the Web Profile
-------------------------

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server with the web profile:

        For Linux:   $JBOSS_HOME/bin/standalone.sh

 
## Build and Deploy the app
-------------------------

1. Make sure you have started the wildfly as described above.
2. Open a command line and navigate to the root directory of this app.
3. Type this command to build and deploy the archive:

        ```mvn clean wildfly:deploy -Dmilker.name=TheDude -Dmilker.password=abides```

4. This will deploy `target/farm-production.war` to the running instance of the server.
5. Add the following profile to your ~/.m2/settings.xml file to dispense with setting the command line params
```
<settings>
  <profiles>
    <profile>
      <id>fp</id>
      <properties>
        <milker.name>TheDude</milker.name>
        <milker.password>abides</milker.password>
      </properties>
    </profile>
  </profiles>
</settings>
```

Then you can redeploy with simply
```
mvn -Pfp clean wildfly:deploy
```

## Access the application
---------------------

The application will be running at the following URL: <http://localhost:8080/farm-production/>. But it is a rest
backend for the aurelie.io front end production-manager, so you won't see much.

## Undeploy the Archive
--------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this app.
3. When you are finished testing, type this command to undeploy the archive:
```
mvn wildfly:undeploy
```

## Debug the Application
------------------------------------

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the
following commands to pull them into your local repository. The IDE should then detect them.
```
mvn dependency:sources
mvn dependency:resolve -Dclassifier=javadoc
```

## Accessing the Rest Endpoints
-----------------------------
Get a jwt token so you can make one of the above requests
```
curl -H "Content-Type: application/json" -X POST -d \
    '{"email":"TheDude","password":"abides"}' \
    -i http://localhost:8080/farm-production/token/login
```
Insert a rain record
```markdown
curl -H "Content-Type: application/json" -H "Authorization: Bearer ey..."  \
 -X POST -d  '{"millimeters":26,"recordedOn":1525020664157}' -i http://localhost:8080/farm-production/rest/rain
```
List of goats in production
```markdown
curl -H "Content-Type: application/json" -H "Authorization: Bearer ey..."  \
http://localhost:8080/farm-production/rest/lists/producers
```
Posting a new Sample
```
curl -H "Content-Type: application/json" -X POST -d \
 '{"liters":1.6,"sampleDate":"2017-07-01T18:25:43.511Z","goat":{"id":10010,"nombre":"Carmen","producing":true}}' \
 http://localhost:8080/farm-production/rest/sample
```

The JWT authentication was added using some info from the following URL and other places
https://abhirockzz.wordpress.com/2016/03/18/json-web-token-in-action-with-jax-rs/

Grab the jwt out of the response header:
jwt:eyJhbGciOiJQUzI1NiJ9.eyJzdWIiOiJzdHVhcnQifQ.HwYdWOTpeC4qKhit-6iXAPxKMaD4YWEenkJNw_yx0JgpA-wQLUGNl0xV4B7VuW_-wBgKBqoFV7TWQCtG5IUO6Auvhte9lVNlQ9FhgDQOGmyiX00Nxo4MQW6PytH0N7Z99n1yXGhrBtypW4nb_PtW0RZtXqwRIsMvhm24jRWGSrYHru4Th_vEaxqG6U_ZQJcWgKC8QoT3cvmLn0w8Er4MrFVoavWkf4X1OAN6Xm5OYHE1QwxcAZ00T_oSaqmT0TxqaLO_hWTRt9Tr7lPciqk4UjWdHDWxlaj4DC7LA_GmTrG3vkZNgQzqSoPaSjDldFVI31QYlk5GcMuM1DfWf4aUbA
And use it as in:
```
curl -H "Content-Type: application/json" -H "Authorization: Bearer eyJhbGciOiJQUzI1NiJ9.eyJzdWIiOiJzdHVhcnQifQ.IDizRPCFJU0SUIBiuIBOqdECQd2YU8IXC8sHN2VTzYrulgzBw2VoQYQvo2naDYqC0DK8cJqI5V37lqmlS2sfb0RR02DkR9WTTd4m3Vl_oHnN8djQMV4YKM7WnYzWvqgNxS0llzvDuieztr1z-FsP_-r58hbqMkwKh038yZ-ShGDQbYOm8EtJ12CyPFAGQ-ZuBNFCwsz2DFnuXWp6KA-KNiF1tCiUpu_1bgSQjQ0LwW8L02mcpcKoscjYNbtE1MfgSKl49spD-vbVvZVubOaCQ28-5GfMewezBCY8jQ6xH33toq0o6C0Xea-mOs2DhM21aTYGGxXOnZLc4YocPg3v0g" \
-X POST -d \
'{"liters":1.6,"sampleDate":"2017-07-01T18:25:43.511Z","goat":{"id":10010,"nombre":"Carmen","producing":true}}' \
    http://localhost:8080/farm-production/rest/sample 
```

### When unauthenticated
`$ curl -i http://localhost:8080/farm-production/rest/lists/producers `
returns    HTTP/1.1 401 Unauthorized


## Create the database
Install mysql and create the database with the following with the mysql commandline:
```
create database caprichosa;
CREATE USER 'fincacaprichosa'@'localhost' IDENTIFIED BY 'milkinggoatstakestime';
GRANT ALL PRIVILEGES ON caprichosa.* TO 'fincacaprichosa'@'localhost';
CREATE USER 'fincacaprichosa'@'%' IDENTIFIED BY 'milkinggoatstakestime';
GRANT ALL PRIVILEGES ON caprichosa.* TO 'fincacaprichosa'@'%'; 
```

# Running on Wildfly 18.0.0.Final

## The mysql driver
Just drop the latest mysql driver jar, mysql-connector-java-8.0.18.jar, in a wildfly/standalone/deployments directory and then
use the driver in a datasource defined in the standalone.xml as show below.

## Add the datasource to standalone.xml
The datasource and replace the ExampleDS as (from standalone.xml)
```
    <subsystem xmlns="urn:jboss:domain:datasources:4.0">
            <datasources>
                <datasource jndi-name="java:jboss/datasources/caprichosaDS" pool-name="goatPool" enabled="true" use-java-context="true" 
                        statistics-enabled="${wildfly.datasources.statistics-enabled:${wildfly.statistics-enabled:false}}">
                    <connection-url>jdbc:mysql://localhost:3306/caprichosa?serverTimezone=UTC</connection-url>
                    <driver>mysql-connector-java-8.0.18.jar</driver>
                    <pool>
                        <min-pool-size>1</min-pool-size>
                        <max-pool-size>5</max-pool-size>
                        <prefill>true</prefill>
                    </pool>
                    <security>
                        <user-name>fincacaprichosa</user-name>
                        <password>milkinggoatstakestime</password>
                    </security>
                </datasource>
            </datasources>
    </subsystem>
```

Since you have replaced the `ExampleDS` with `caprichosaDS`, be sure to search for the following found later in the file 
`datasource="java:jboss/datasources/ExampleDS"`
and replace it with
`datasource="java:jboss/datasources/caprichosaDS"`

TODO: add a build goal to blow away the database. It can be done manually with:
```markdown
drop table DATABASECHANGELOG;
drop table DATABASECHANGELOGLOCK;
drop table production_event;
drop table sample;
drop table production_event;
drop table units;
drop table storage;
drop table goat;
drop table rain;
```

To skip running the liquibase migration in your build run
`mvn -Pfp wildfly:deploy -Dskip.liquibase=true`