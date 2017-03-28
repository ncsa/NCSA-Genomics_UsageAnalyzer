#!bin/bash

hadoop com.sun.tools.javac.Main AllTime.java
hdfs dfs -rmr /user/rchui2/output/
jar cf at.jar AllTime*.class
hadoop jar at.jar AllTime /user/rchui2/input/ /user/rchui2/output/
