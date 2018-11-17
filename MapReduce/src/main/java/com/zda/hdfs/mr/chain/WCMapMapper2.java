package com.zda.hdfs.mr.chain;

import com.zda.hdfs.mr.Util;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 过滤非法文字
 */
public class WCMapMapper2 extends Mapper<Text, IntWritable,Text, IntWritable> {
    @Override
    protected void map(Text key, IntWritable value, Context context) throws IOException, InterruptedException {
        context.getCounter("m", Util.getInfo(this,"map2")).increment(1);
        if(!key.toString().equals("falungong")){
            context.write(key,value);
        }
    }
}
