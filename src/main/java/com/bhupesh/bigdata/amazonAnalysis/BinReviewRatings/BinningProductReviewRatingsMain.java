package com.neu.bigdata.amazonAnalysis.BinReviewRatings;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.log4j.Logger;

import java.io.IOException;

//Binning pattern is used to divide the reviews based on the ratings in separate bins.
//This is map-side pattern
//Multiple outputs are generated for each bin

public class BinningProductReviewRatingsMain {

    final static Logger logger = Logger.getLogger(com.neu.bigdata.amazonAnalysis.BinReviewRatings.BinningProductReviewRatingsMain.class);
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        try {
            long startTime = System.currentTimeMillis();

            Job binningJob = Job.getInstance(conf, "Binning Pattern");
            binningJob.setJarByClass(com.neu.bigdata.amazonAnalysis.BinReviewRatings.BinningProductReviewRatingsMain.class);

            binningJob.setMapperClass(BinningProductReviewRatingsMapper.class);
            binningJob.setMapOutputKeyClass(Text.class);
            binningJob.setMapOutputValueClass(NullWritable.class);
            binningJob.setNumReduceTasks(1);

            FileInputFormat.setInputPaths(binningJob, new Path(args[0]));
            FileOutputFormat.setOutputPath(binningJob, new Path(args[1]));
            if (fs.exists(new Path(args[1]))) {
                fs.delete(new Path(args[1]), true);
            }

            MultipleOutputs.addNamedOutput(binningJob, "bins", TextOutputFormat.class, Text.class, NullWritable.class);
            MultipleOutputs.setCountersEnabled(binningJob, true);

            long endTime = System.currentTimeMillis();
            logger.info("Time taken in milliseconds : " + (endTime - startTime));
            logger.info("Time taken in seconds : " + (endTime - startTime)/1000);
            System.exit(binningJob.waitForCompletion(true) ? 0 : 1);

        } catch (Exception e ) {
            System.out.println("Something went wrong in main class: ");
            e.printStackTrace();
        }
    }
}
