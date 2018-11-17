package com.zda.hdfs.mr.chain;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.chain.ChainMapper;
import org.apache.hadoop.mapreduce.lib.chain.ChainReducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 链式Job
 */
public class WCChainApp {
    public static  void main(String[]args) throws Exception {
        Configuration conf=new Configuration();
        conf.set("fs.defaultFS","file:///");
        Job job=Job.getInstance(conf);

        job.setJobName("WCChain");
        job.setJarByClass(WCChainApp.class);

        job.setInputFormatClass(TextInputFormat.class);

        FileInputFormat.addInputPath(job,new Path("d:/mr/skew"));
        FileOutputFormat.setOutputPath(job,new Path("d:/mr/skew/out"));
        //在mapper链条上增加Mapper1
        ChainMapper.addMapper(job,WCMapMapper1.class, LongWritable.class, Text.class,Text.class, IntWritable.class,conf);
        //在mapper链条上增加Mapper2
        ChainMapper.addMapper(job,WCMapMapper2.class, Text.class, IntWritable.class,Text.class, IntWritable.class,conf);

        //在reduce链条上设置reduce
        ChainReducer.setReducer(job,WCReducer.class, Text.class, IntWritable.class,Text.class, IntWritable.class,conf);
        //在reduce链条上增加WCReduceMapper1
        ChainReducer.addMapper(job,WCReduceMapper1.class, Text.class, IntWritable.class,Text.class, IntWritable.class,conf);
        job.waitForCompletion(true);
    }

}
