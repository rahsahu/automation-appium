#!/bin/bash

echo "Starting Server"
/usr/bin/java -jar $1 -port $2 -host localhost -debug -log target/selenium-logs.log &

