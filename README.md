# iDetect
 
This repository contains my BSc project, which is to detect phishing and hacked websites. The project was written in 2010 (12 years ago at the time of writing this). So I had to run it using old software and tools (e.g., NetBeans 7). 

## Running the program
1. Install MySql server (veriosn 5.6.6 this is **important**) as the database along with jdbc driver ([MySql Installer and J connector](https://dev.mysql.com/downloads/connector/j/ "MySql Installer and J connector")).
2. Run the [Sql Commands](https://github.com/altherwy/iDetect/blob/main/iDetectDBScript.sql "Sql Commands") to create the database (idetect) and required tables. You can use MySql Workbench to run the script.
3. Install NetBeans 7 along with JDK 6 (again this project is written 12 years ago :)). The links are [NetBeans 7](https://netbeans-ide.informer.com/7.0/ "NetBeans 7") and [JDK 6](https://www.oracle.com/sa/java/technologies/javase-java-archive-javase6-downloads.html "JDK 6").
4. When you open the project in NetBeans, it will asks you to resolve reference problems, choose yes. You have to resolve all the reference problems (except for junit and junit_4, which is not important as those are for testing purposes). To resolve the reference problems you'll find all required JAR files in the [dist/lib folder](https://github.com/altherwy/iDetect/tree/main/dist/lib "dist folder").
5. Repopulate the [Configuration file](https://github.com/altherwy/iDetect/blob/main/dist/Configuration.txt "Configuration file") with the user name, password, and port number you chose when installing the MySql server.
6. Run the program.

