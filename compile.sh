#!/bin/bash

hadoop com.sun.tools.javac.Main src/PBSTime.java
jar cf pbs.jar PBSTime*.class

hadoop com.sun.tools.javac.Main src/OGETime.java
jar cf oge.jar OGETime*.class
