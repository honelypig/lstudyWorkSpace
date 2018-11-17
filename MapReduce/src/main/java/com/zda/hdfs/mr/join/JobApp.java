package com.zda.hdfs.mr.join;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class JobApp {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");
        //FileSystem fs=FileSystem.get(conf);

        Job job = Job.getInstance(conf);
        job.setJobName("ReducerJoin");

        job.setJarByClass(JobApp.class);

        FileInputFormat.addInputPath(job, new Path("D:\\mr\\join"));
        FileOutputFormat.setOutputPath(job, new Path("D:\\mr\\join\\out"));

        job.setMapperClass(JoinMapper.class);
        job.setReducerClass(JoinReducer.class);

        job.setMapOutputKeyClass(ComboKey2.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(Text.class);

        job.setPartitionerClass(CIDPartitioner.class);

        job.setGroupingComparatorClass(CIDGroupComparator.class);

        job.setSortComparatorClass(ComboKey2Comparator.class);

        job.setNumReduceTasks(2);

        job.waitForCompletion(true);

    }
//数据：
    /*
   customers:
    1,tom,12
    2,tom,13
    3,tom,14
    4,tom,15
   orders:
    1,no001,12.23,1
    2,no001,12.23,1
    3,no001,12.23,2
    4,no001,12.23,2
    5,no001,12.23,2
    6,no001,12.23,3
    7,no001,12.23,3
    8,no001,12.23,3
    9,no001,12.23,3
    * */

    //过程
    /**
    在mapper端生成key：
         两种情况：客户：cid 、cinfo有值
                   订单：cid、oid、oinfo有值
    分区
    在进入reducer函数前会先
         1、键对比，根据comkey2中的对比器进行比较，排序
                其中，我们是先根据cid从小到大排序，
                同一个cid时，客户信息排前面，订单信息排前面，
                订单信息根据oid从小到大排序
         2、组对比，键会根据cid对比，当键不同时，分为一组。所以每次进reducer的一组数据的cid是一致的
    此时进入reducer的一组数据结构;
        第一条为客户信息 ：           cid，name，age
        从第二条开始为客户+订单信息： cid，name，age，oid，oorderno，price
     ...

     */



    //结果：
    /*
    2,tom,13,3,no001,12.23
    2,tom,13,4,no001,12.23
    2,tom,13,5,no001,12.23

    1,tom,12,1,no001,12.23
    1,tom,12,2,no001,12.23
    3,tom,14,6,no001,12.23
    3,tom,14,7,no001,12.23
    3,tom,14,8,no001,12.23
    3,tom,14,9,no001,12.23

    * */
}
