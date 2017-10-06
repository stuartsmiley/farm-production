farm production:
========================
Technologies: CDI, JPA, EJB, JPA, JAX-RS, Bean Validation
Summary: Tracks farm production
Target Project: WildFly
Source: <https://github.com/wildfly/quickstart/>

What is it?
-----------

There is a tutorial for this quickstart in the [Getting Started Developing Applications Guide](https://github.com/wildfly/quickstart/guide/production/).

System requirements
-------------------

Java 8.0 (Java SDK 1.8) or better, Maven 3.1 or better.

The application this project produces is designed to be run on JBoss WildFly.


Start JBoss WildFly with the Web Profile
-------------------------

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server with the web profile:

        For Linux:   JBOSS_HOME/bin/standalone.sh

 
Build and Deploy the app
-------------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this app.
3. Type this command to build and deploy the archive:

        mvn clean package wildfly:deploy
        mvn wildfly:deploy -Dmilker.name=TheDude -Dskip.liquibase=true -Dmilker.password=abides
        
4. This will deploy `target/farm-production.war` to the running instance of the server.
 

Access the application 
---------------------

The application will be running at the following URL: <http://localhost:8080/farm-production/>.


Undeploy the Archive
--------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this app.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy


Debug the Application
------------------------------------

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the
following commands to pull them into your local repository. The IDE should then detect them.

    mvn dependency:sources
    mvn dependency:resolve -Dclassifier=javadoc



Accessing the Rest Endpoints
-----------------------------

List of all ProductionEvent entries
http://localhost:8080/farm-production/rest/production

List of all available Units options
http://localhost:8080/farm-production/rest/lists/units

List of all available Storage options
http://localhost:8080/farm-production/rest/lists/storage

List of goats in production
http://localhost:8080/farm-production/rest/lists/producers

Posting a new ProductionEvent example
curl -H "Content-Type: application/json" -X POST -d \
    '{"created":"2017-04-28T18:25:43.511Z","quantity":41,"units":{"id":1},"producers":9,"storage":{"id":1}}' \
    http://localhost:8080/farm-production/rest/production

Posting a new Sample
curl -H "Content-Type: application/json" -X POST -d \
    '{"liters":1.6,"sampleDate":"2017-07-01T18:25:43.511Z","goat":{"id":11,"nombre":"Carmen","producing":true}}' \
    http://localhost:8080/farm-production/rest/sample



curl -H "Content-Type: application/json" -X POST -d \
    '{"email":"TheDude","password":"abides"}' \
    -i http://localhost:8080/farm-production/token/login

https://abhirockzz.wordpress.com/2016/03/18/json-web-token-in-action-with-jax-rs/

Now go to
http://localhost:8080/farm-production/auth/token
And enter basic auth stuart/eatmorepossum
Grab the jwt out of the response header:
jwt:eyJhbGciOiJQUzI1NiJ9.eyJzdWIiOiJzdHVhcnQifQ.HwYdWOTpeC4qKhit-6iXAPxKMaD4YWEenkJNw_yx0JgpA-wQLUGNl0xV4B7VuW_-wBgKBqoFV7TWQCtG5IUO6Auvhte9lVNlQ9FhgDQOGmyiX00Nxo4MQW6PytH0N7Z99n1yXGhrBtypW4nb_PtW0RZtXqwRIsMvhm24jRWGSrYHru4Th_vEaxqG6U_ZQJcWgKC8QoT3cvmLn0w8Er4MrFVoavWkf4X1OAN6Xm5OYHE1QwxcAZ00T_oSaqmT0TxqaLO_hWTRt9Tr7lPciqk4UjWdHDWxlaj4DC7LA_GmTrG3vkZNgQzqSoPaSjDldFVI31QYlk5GcMuM1DfWf4aUbA
And use it:
curl -H "Content-Type: application/json" -H "Authorization: Bearer eyJhbGciOiJQUzI1NiJ9.eyJzdWIiOiJzdHVhcnQifQ.IDizRPCFJU0SUIBiuIBOqdECQd2YU8IXC8sHN2VTzYrulgzBw2VoQYQvo2naDYqC0DK8cJqI5V37lqmlS2sfb0RR02DkR9WTTd4m3Vl_oHnN8djQMV4YKM7WnYzWvqgNxS0llzvDuieztr1z-FsP_-r58hbqMkwKh038yZ-ShGDQbYOm8EtJ12CyPFAGQ-ZuBNFCwsz2DFnuXWp6KA-KNiF1tCiUpu_1bgSQjQ0LwW8L02mcpcKoscjYNbtE1MfgSKl49spD-vbVvZVubOaCQ28-5GfMewezBCY8jQ6xH33toq0o6C0Xea-mOs2DhM21aTYGGxXOnZLc4YocPg3v0g" \
-X POST -d \
'{"liters":1.6,"sampleDate":"2017-07-01T18:25:43.511Z","goat":{"id":11,"nombre":"Carmen","producing":true}}' \
    http://localhost:8080/farm-production/rest/sample

# When unauthenticated
$ curl -I http://localhost:8080/farm-production/rest/lists/producers
# returns    HTTP/1.1 401 Unauthorized


Create the database
create database caprichosa;
CREATE USER 'fincacaprichosa'@'localhost' IDENTIFIED BY 'milkinggoatstakestime';
GRANT ALL PRIVILEGES ON caprichosa.* TO 'fincacaprichosa'@'localhost';
CREATE USER 'fincacaprichosa'@'%' IDENTIFIED BY 'milkinggoatstakestime';
GRANT ALL PRIVILEGES ON caprichosa.* TO 'fincacaprichosa'@'%';


create the myql module
cd $JBOSS_HOME/modules/system/layers/base/com
mkdir -p mysql/driver/main
put mysql jar in $JBOSS_HOME/modules/system/layers/base/com/mysql/driver/main
vi $JBOSS_HOME/modules/system/layers/base/com/mysql/driver/main/module.xml
<module xmlns="urn:jboss:module:1.3" name="com.mysql.driver">
 <resources>
   <resource-root path="mysql-connector-java-5.1.44-bin.jar" />
   </resources>
   <dependencies>
     <module name="javax.api"/>
     <module name="javax.transaction.api"/>
   </dependencies>
</module>

from standalone.xml
<subsystem xmlns="urn:jboss:domain:datasources:4.0">
            <datasources>
                <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" use-java-context="true">
                    <connection-url>jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE</connection-url>
                    <driver>h2</driver>
                    <security>
                        <user-name>sa</user-name>
                        <password>sa</password>
                    </security>
                </datasource>
                <datasource jndi-name="java:jboss/datasources/caprichosaDS" pool-name="goatPool" enabled="true" use-java-context="true">
                    <connection-url>jdbc:mysql://localhost:3306/caprichosa</connection-url>
                    <driver>mysql</driver>
                    <pool>
                        <min-pool-size>1</min-pool-size>
                        <max-pool-size>20</max-pool-size>
                        <prefill>true</prefill>
                    </pool>
                    <security>
                        <user-name>fincacaprichosa</user-name>
                        <password>milkinggoatstakestime</password>
                    </security>
                </datasource>
                <drivers>
                    <driver name="h2" module="com.h2database.h2">
                        <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
                    </driver>
                    <driver name="mysql" module="com.mysql.driver">
                            <driver-class>com.mysql.jdbc.Driver</driver-class>
                    </driver>
                </drivers>
</datasources>

drop table DATABASECHANGELOG;
drop table DATABASECHANGELOGLOCK;
drop table production_event;
drop table sample;
drop table production_event;
drop table units;
drop table storage;
drop table goat;