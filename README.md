# WebService

Configuration
---
1. The following environment variables need to be set in your local .zshrc file to enable database connection:
```
DB_USERNAME = ""
DB_PASSWORD = ""
DB_HOST = ""
DB_NAME = ""
```
Prerequisites
---
1. Project version of java must be java 11 (typical error is to do with mockito)
2. Running terminal must also be using Java 11, java --version to check


How To Start The WebService Application From IDE
---

1. mvn clean install in terminal or from maven in the IDE
2. Go to the WebserviceApplication.java in com.kainos.ea
1. modify run configuration on the classes main method by right-clicking the green arrow
2. Ensure java 11 is selected as your version on the project level and in the configuration window
3. add the text 'server' as the cli argument (without quotes)
4. apply and run

How To Start The WebService Application From The Terminal
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/java-backend-api-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

How To Install And Use Java 11
---
1. run /usr/libexec/java_home -V you will see java11 is not present, if so do brew uninstall java11
2. brew install java11 to install
2. after installation there will be commands suggested to apply like echo ..., run all of these
3. after executing these suggested commands run /usr/libexec/java_home -V to see java11 on your system
4. run in the terminal export JAVA_HOME=`/usr/libexec/java_home -v ${java version seen from doing previous step}`
5. run java --version to see that java11 is now being tracked as your default version to use

How To Run the Tests
---
1. run mvn test in the terminal or navigate to the test classes and run from there

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
