#!/bin/bash

rm -rf *.jar
rm -rf bin/

mkdir bin/
hadoop com.sun.tools.javac.Main -d bin src/*.java
cd bin/

jar cf pbs.jar PBSTime*.class
jar cf oge.jar OGETime*.class

mv pbs.jar ../
mv oge.jar ../
