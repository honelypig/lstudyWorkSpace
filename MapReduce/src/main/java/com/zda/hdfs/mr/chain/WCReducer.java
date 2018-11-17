package com.zda.hdfs.mr.chain;

import com.zda.hdfs.mr.Util;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * reducer阶段的reduce
 */
public class WCReducer extends Reducer<Text, IntWritable,Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0 ;
        for(IntWritable iw : values){
            count = count + iw.get() ;
        }
        context.write(key,new IntWritable(count));
        context.getCounter("m", Util.getInfo(this,"reducer")).increment(1);
    }
}
