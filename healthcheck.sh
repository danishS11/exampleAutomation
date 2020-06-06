#!/usr/bin/env bash
echo "Checking if hub is ready - $1"
while [ "$( curl -s http://$1:4444/wd/hub/status | jq -r .value.ready)" != "true" ]
do
   sleep 1
done

java -DHUB-HOST=$1 -cp SampleUIAutomation.jar:SampleUIAutomation-tests.jar:libs/* org.testng.TestNG testng.xml
