# bank-service

### Overview

This is a multi-module system, consisting **core**, **account**, and **transaction** modules.
The diagram below depicts the service architecture.

![Service architecture][service-architecture]

### Prerequisites
You need to have Maven installed on your machine.

### Installation
1. Clone the repo.
   ```sh
   git clone https://github.com/zeinab-dashti/account-service.git
   ```
   
2. Build the project (from the parent module directory). This command compiles the project and creates JAR packages.
   ```sh
   mvn clean install -Dmaven.test.skip=true
   ```
    
3. Start transaction service (from the parent module directory). This will start transaction RESTful API on 
**port 8081**.
   ```sh
   mvn -f transaction-service/pom.xml spring-boot:run
   ```
   
4. Run tests and start account service (from the parent module directory). This will run the tests (against 
a [predefined test data set]() on H2 database) and starts account RESTful API on **port 8080** only if tests are passed.
   ```sh
   mvn -f account-service/pom.xml test && mvn -f account-service/pom.xml spring-boot:run
   ```
   
## API Specification
Once the account service is up and running, you can find Swagger UI at ```http://localhost:8080/swagger-ui/```.

## Improvement ideas
**Security**:
since transaction service is not designed to be publicly exposed, it should be deployed in a private network safeguarded 
by firewall, and also we should apply strict authentication (for example mTLS) between account service and transaction 
service. 


[service-architecture]: docs/images/architecture.png
