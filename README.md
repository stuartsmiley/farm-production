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

Posting a new ProductionEvent example
curl -H "Content-Type: application/json" -X POST -d \
    '{"created":"2017-04-28T18:25:43.511Z","quantity":41,"units":{"id":1},"producers":9,"storage":{"id":1}}' \
    http://localhost:8080/farm-production/rest/production
    
