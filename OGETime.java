import java.io.IOException;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.StringTokenizer;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

// Maps jobs by group and reduces the amount of time taken for each job.
public class OGETime {

	// Instance to map jobs.
	public static class TokenManager extends Mapper<Object, Text, Text, DoubleWritable> {

		public DoubleWritable time = new DoubleWritable(1.0);
		public Text groupName = new Text();
		
		// Maps the time taken for a job to their group name.
		// Parameters: key (Object) unused key object
		//             value (Text) file text
		//             context (Context) map/reduce object to interact with Hadoop system
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

			BufferedReader buff = new BufferedReader(new StringReader(value.toString()));
			
			String[] tokens;
			String line = null;
			while ((line = buff.readLine()) != null) {
				String groupString = null;
				double walltime = 0;
				tokens = line.split(":");
				if (tokens[2] != "") {
					groupString = tokens[2];
				}
				if (groupString != null) {
					Date date = new Date(Long.parseLong(tokens[8]) * 1000);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
					String timeStamp = sdf.format(date);
					walltime = Double.parseDouble(tokens[13]);
					groupString += "::" + timeStamp;

					groupName.set(groupString);
					time.set(walltime);
				}
				context.write(groupName, time);
			}
		}
	}

	// Instance to reduce jobs.
	public static class DoubleSumReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

		private DoubleWritable result = new DoubleWritable();
		
		// Reduces the time taken for a job by their group name.
		// Paraemters: key (Text) group name
		//             values (Iterable<DoubleWritable>) times for each job
		//             context (Context) map/reduce object to interact with Hadoop system
		public void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
			double sum = 0.0;
			for (DoubleWritable val: values) {
				sum += val.get();
			}
			result.set(sum);
			context.write(key, result);
		}
	}

	// Creates and configures a new Hadoop job.
	// Maps by job group name and reduces by time spent on each job.
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "all time");
		job.setJarByClass(OGETime.class);
		job.setMapperClass(TokenManager.class);
		job.setCombinerClass(DoubleSumReducer.class);
		job.setReducerClass(DoubleSumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
