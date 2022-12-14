#!/bin/bash
cp ressources/* classes/ -r
cd classes
export CLASSPATH=`find ../lib -name "*.jar" | tr '\n' ':'`
java -cp ${CLASSPATH}:. $@
cd ..

