#!/bin/bash

rm -rf *.jar
rm -rf bin/

mkdir bin/
hadoop com.sun.tools.javac.Main -d bin src/*.java
jar cf pbs.jar bin/PBSTime*.class
jar cf oge.jar bin/OGETime*.class
