package com.zda.hdfs.mr.multiinputwordcount;

import com.zda.hdfs.mr.wordcount.MyPartitioner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MultiFileInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.io.IOException;

/**
 * 计算单词出现次数的job类
 */
public class WCApp {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf=new Configuration();
        conf.set("fs.defaultFS","file:///");

        Job job=Job.getInstance(conf);

        job.setJobName("myCount");

        job.setJarByClass(WCApp.class);

       // job.setInputFormatClass(TextInputFormat.class);


     //   FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[0]));
//这个path的参数好像可以不用写成file:///d:/mr/words,就是path的路径前可以不用添加file:/// 因为前面已经设置为本地

        MultipleInputs.addInputPath(job,new Path("d:/mr/words"),TextInputFormat.class,WCtextMapper.class);
        MultipleInputs.addInputPath(job,new Path("d:/mr/seq"), SequenceFileInputFormat.class,WCseqMapper.class);

        //设置分区类
      //  job.setPartitionerClass(MyPartitioner.class);   //设置自定义分区

        //设置合成类
        //job.setCombinerClass(WCReducer.class);          //设置combiner类

      //  job.setMapperClass(WCMapper.class);
        job.setReducerClass(WCReducer.class);

        job.setNumReduceTasks(3);                       //reduce个数
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.waitForCompletion(true);
    }
}
