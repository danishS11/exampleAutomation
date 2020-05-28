# Example Automation

## Introduction
This is a barebones example framework. To show the capabilities that can be enhanced and scaled for a bigger testing effort. 
The framework has adopted the industry standard PageObject model using Selenium WebDriver with Java and TestNG framework. This repo is also dockerized, i.e. tests are run using docker container for a better maintainability. 

## Instructions to run the tests
Please follow the instructions below to run the tests
Pre-reqs:
1. Docker is installed on the machine
2. Maven and Java are installed and in the system Path

Run Steps:
1. Clone/Fork this repo to your machine
2. From root folder of the cloned repo type `mvn clean package -DskipTests` 
  - This command will compile the code via maven and generate 2 jar files and place it in `target` folder
3. From same folder as step 2 run command `docker build -t=qa-docker .`
  - This will build the docker image with all the relevant dependencies
4. Once step 3 successfully completes, type 
  - On windows: `docker-compose up | findstr functional-tests`
  - On Mac/Linux: `docker-compose up | grep funcitonal-tests`
  
