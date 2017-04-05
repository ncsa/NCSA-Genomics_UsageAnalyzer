# UsageAnalyzer
UsageAnalyzer is a usage log analyzer for HPC schedulers that uses the Hadoop open source framework for data analysis.

## PBSTime.java
PBSTime.java will process PBS torque usage logs and will sum by month the time per group for all jobs that ended.

## OGETime.java
OGETime.java will process OGE/SGE usage logs and will sum by month the time per group for all jobs that ended.

## Submission Script Example
```
# PBS.run.sub
#!/bin/bash                                                                                                                             
#PBS -l nodes=1:ppn=24
#PBS -l walltime=4:00:00
#PBS -q normal

cd ~/UsageAnalyzer/
rm -rf ../PBS.output/
module load $(HADOOP_HOME)/hadoop-x.x.x
hadoop jar pbs.jar PBSTime ../PBS.input/ ../PBS.output/
```

## Usage
Remember to add hadoop binaries and libraries to your paths before compiling. Copy and edit the example script for your environment.The installation, build, and run steps will depend on your environment. We will use the following general variables as placeholders for a general example:
```
$(JOB_TYPE) = PBS, OGE
$(L_JOB_TYPE) = pbs, oge
$(HADOOP_HOME) = path/to/hadoop/installation
$(HADOOP_VER) = hadoop-2.8.0, etc.
$(SUBMIT_CMD) = qsub, etc.
```
UsageAnalyzer expects the following file directory structure:
```
./
./$(JOB_TYPE).input
./$(JOB_TYPE).input/FilesToAnalyze.txt
./UsageAnalyzer
./UsageAnalyzer/$(JOB_TYPE).run.sub
```
### Installation
```
git clone https://github.com/rchui/UsageAnalyzer.git
cd UsageAnalyzer/
module load $(HADOOP_HOME)/$(HADOOP_VER)
./compile.sh
```

### Run
#### With Script
```
$(SUBMIT_CMD) $(JOB_TYPE).run.sub
```

#### Without Script
```
hadoop jar $(L_JOB_TYPE).jar $(JOB_TYPE)Time ../$(JOB_TYPE).input/ ../$(JOB_TYPE).output/
```
