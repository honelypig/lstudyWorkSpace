package com.zda.hdfs.mr.maxtemp.secondsort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * WCTextMapper，这边使用组合key，值和key都在key里面，所以输出的value为nullwritable
 */
public class MaxTempMapper extends Mapper<LongWritable, Text, ComboKey, NullWritable> {

    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] arr = line.split(" ");

        ComboKey keyOut = new ComboKey();
        keyOut.setYear(Integer.parseInt(arr[0]));
        keyOut.setTemp(Integer.parseInt(arr[1]));
        context.write(keyOut, NullWritable.get());
    }
}
