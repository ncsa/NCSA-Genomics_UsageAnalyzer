# UsageAnalyzer
UsageAnalyzer is a usage log analyzer for HPC schedulers that uses the Hadoop open source framework for data analysis.

## PBSTime.java
PBSTime.java will process PBS torque usage logs and will sum the time per group for all jobs that ended.

## OGETime.java
OGETime.java will process PBS torque usage logs and will sum the time per group for all jobs that ended.

## Submission Script Example
	#!/bin/bash                                                                                                                             
	#PBS -l nodes=1:ppn=24
	#PBS -l walltime=4:00:00
	#PBS -q normal

	cd ~/UsageAnalyzer/
	rm -rf ../output/
	module load /usr/local/apps/bioapps/modules/hadoop/hadoop-2.8.0
	hadoop jar pbs.jar AllTime ../input/ ../output/
