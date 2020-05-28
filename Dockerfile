FROM openjdk:8u191-jre-alpine3.8

RUN apk update && apk add jq curl

WORKDIR /usr/share/QA

ADD target/SampleUIAutomation.jar 			SampleUIAutomation.jar
ADD target/SampleUIAutomation-tests.jar 	SampleUIAutomation-tests.jar 
ADD TestNG.xml								TestNG.xml
ADD healthcheck.sh 							healthcheck.sh
ADD target/libs								libs

ENTRYPOINT sh healthcheck.sh $HUB_HOST
