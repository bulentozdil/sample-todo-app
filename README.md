# Getting Started

This is an example of to-do list application. This application consists of two part , first one is the api service written in java and the other part is database that using a couchbase db.
For more detaila about couchbase db , you can visit clicking [this link](https://www.couchbase.com/)

# Prerequisities

Minimum requirements to clone ,build ,test and run this application, you'll need these installed on your local computer.
These are like following

* [Git](http://www.git-scm.com) - _More details_
* [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) - _11<=Current version_
* [Maven](https://maven.apache.org/install.html) - _To test and build_
* [Docker and Docker-Compose](https://docs.docker.com/get-docker/) - _More details_

# How To Use

To clone and run this application, from your command line
```git
# Clone this repository
git clone https://github.com/bulentozdil/sample-todo-app.git

# Go into the repository
cd sample-todo-app
```

# How To Test
```git
# Run all the test classes
mvn test

# Run a single test class
mvn -Dtest=UserTest test
```
# How To Build
```git
# Build a jar file with all tests
mvn clean install

# Build a jar file without tests
mvn clean install -DskipTests=true
```

# How To Run
```git
java - jar todoapp-1.0.0.0.jar
```

# Deploy

You can deploy this application from your local computer to local docker instance.

To do deploying, open your terminal and
```git

# Build dockerfile
docker-compose build --no-cache

# Run services defined on docker-compose.yml file
docker-compose up -d

# Stop services
docker-compose stop

# Stop and remove containers, networks, images, and volumes
docker-compose down
```
