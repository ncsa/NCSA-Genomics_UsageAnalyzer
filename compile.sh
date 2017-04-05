#!/bin/bash

hadoop com.sun.tools.javac.Main -d ./ src/*.java
jar cf pbs.jar PBSTime*.class
jar cf oge.jar OGETime*.class
