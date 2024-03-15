#!/bin/sh

set -e

BASE_PATH=/var/kafka-demo-app \
CONFIG_PATH=$BASE_PATH/conf \
EXECUTABLE_JAR=$BASE_PATH/lib/kafka-demo-app-0.0.1-SNAPSHOT.jar \
LOG_DIR=$BASE_PATH/log \
DEBUG=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005

sleep 90
java ${DEBUG} -Dloader.path=${CONFIG_PATH} -DLOG_DIR=$LOG_DIR -DAPP_NAME=KafkaDemoApp -jar ${EXECUTABLE_JAR} &

while true
do
	sleep 1
done
