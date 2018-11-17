package com.zda.hdfs.mr.maxtemp.allsort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * WCTextMapper
 */
public class MaxTempMapper extends Mapper<IntWritable, IntWritable, IntWritable, IntWritable> {
    protected void map(IntWritable key, IntWritable value, Context context) throws IOException, InterruptedException {
        context.write(key,value);
    }
}
