#!/bin/bash

hadoop com.sun.tools.javac.Main PBSTime.java
jar cf pbs.jar PBSTime*.class

hadoop com.sun.tools.javac.Main OGETime.java
jar cf oge.jar OGETime*.class
