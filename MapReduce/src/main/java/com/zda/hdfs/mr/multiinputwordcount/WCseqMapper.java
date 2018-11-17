package com.zda.hdfs.mr.multiinputwordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WCseqMapper  extends Mapper<IntWritable, Text,Text,IntWritable> {
    @Override
    protected void map(IntWritable key, Text value, Context context) throws IOException, InterruptedException {
        Text keyout=new Text();
        IntWritable valueout=new IntWritable();
        String[] arr=value.toString().split(" ");
        for (String s : arr) {
            keyout.set(s);
            valueout.set(1);
            context.write(keyout,valueout);
        }

    }
}
