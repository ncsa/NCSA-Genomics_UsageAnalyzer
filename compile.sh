#!/bin/bash

hadoop com.sun.tools.javac.Main AllTime.java
jar cf at.jar AllTime*.class
