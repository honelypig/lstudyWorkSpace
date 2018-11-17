package com.zda.hdfs.mr.chain;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 过滤
 */
public class WCReduceMapper1 extends Mapper<Text, IntWritable,Text, IntWritable> {
    @Override
    protected void map(Text key, IntWritable value, Context context) throws IOException, InterruptedException {
        if(value.get()>5){
            context.write(key,value);
        }
    }
}
