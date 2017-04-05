#!/bin/bash

hadoop com.sun.tools.javac.Main PBSTime.java
jar cf pbs.jar PBSTime*.class
