# UsageAnalyzer
UsageAnalyzer is a usage log analyzer for HPC schedulers that uses the Hadoop open source framework for data analysis.

## PBSTime.java
PBSTime.java will process PBS torque usage logs and will sum by month the time per group for all jobs that ended.

## OGETime.java
OGETime.java will process OGE/SGE usage logs and will sum by month the time per group for all jobs that ended.

## Submission Script Example
'''
# PBS.run.sub
#!/bin/bash                                                                                                                             
#PBS -l nodes=1:ppn=24
#PBS -l walltime=4:00:00
#PBS -q normal

cd ~/UsageAnalyzer/
rm -rf ../PBS.output/
module load $(HADOOP_HOME)/hadoop-x.x.x
hadoop jar pbs.jar PBSTime ../PBS.input/ ../PBS.output/
'''
## Usage
Remember to add hadoop binaries and libraries to your paths before compiling. Copy and edit the example script for your environment.

UsageAnalyzer expects the following file directory structure:
'''
./
./PBS.input
./PBS.input/FilesToAnalyze.txt
./OGE.input
./OGE.input/FilesToAnalyze.txt
./UsageAnalyzer
./UsageAnalyzer/script.to.run.sub
'''
