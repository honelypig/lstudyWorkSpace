package com.zda.hdfs.mr.chain;

import com.zda.hdfs.mr.Util;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * map阶段的 map，将文本分割成文字和1
 */
public class WCMapMapper1 extends Mapper<LongWritable, Text,Text,IntWritable> {
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[]  arr=value.toString().split(" ");
        Text keyOut = new Text();
        IntWritable valueOut = new IntWritable();
        for (String s : arr) {
            keyOut.set(s);
            valueOut.set(1);
            context.write(keyOut,valueOut);
        }
        context.getCounter("m", Util.getInfo(this,"map1")).increment(1);

        //tlw-PC:8892:Thread-25:WCMapMapper1@23815834:map1=10     表示这个map1执行了10次
    }
}
