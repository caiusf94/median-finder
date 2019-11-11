#!/usr/bin/env bash
export JAVA_PROGRAM_ARGS=`echo "$@"`
mvn -q exec:java -Dexec.mainClass="ro.caiusf94.main.Main" -Dexec.args="$JAVA_PROGRAM_ARGS" -e