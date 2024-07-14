Pre-requisites:

  *  JDK version 11 or later
  *  Maven build management tool configured in the System Environment variable
  *  Internet connection to download dependency from the maven repository

Steps to execute the project:

  *  Clone the project to the local using command `git clone git@github.com:iamlivinsunny/fancode-sdet-14-07.git`
  *  Switch to the main branch `git switch main`
  *  Inside project root folder open command line
  *  Execute command `mvn clean test`
  *  The execution result will be in the folder `src\test\resources\execution-reports`

Major Libraries Userd:
  *  Java - Programming Language
  *  Maven - Build Management tool
  *  Cucumber - BDD Model automation
  *  RestAssured - Extracting Rest API response

Folder structure:

![image](https://github.com/user-attachments/assets/9abe8e79-b921-4f13-a666-5e4c62e84ee5)
  
  *  Runner class : src/test/java/org.assignment.CucumberTestRunner.java
  *  Feature file Location : src/test/resources/feature/TodoTaskCompletionVerification.feature
  *  Execution report : src/test/resources/execution-reports/
  *  API response file : src/test/resources/api-response/
  *  Step definition location :  src/test/java/org.assignment.step_definition.TodoTaskCompletion.java
  *  Package contains end point specific classes : src/main/java/org.assignment.api.endpoint
  *  Classes used to abstract rest assured function calls : src/main/java/org.assignment.api.request.GetRequest, src/main/java/org.assignment.api.request.response.RestAPIResponse
  *  Package containing utility packages : src/main/java/org.assignment.api.utilities

 

