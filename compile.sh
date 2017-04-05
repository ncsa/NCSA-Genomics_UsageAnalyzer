#!/bin/bash

hadoop com.sun.tools.javac.Main PBSTime.java
jar cf at.jar PBSTime*.class
