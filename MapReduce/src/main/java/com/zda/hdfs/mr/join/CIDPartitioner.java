package com.zda.hdfs.mr.join;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 根据客户id分区
 */
public class CIDPartitioner  extends  Partitioner<ComboKey2, NullWritable> {

    @Override
    public int getPartition(ComboKey2 combkey2, NullWritable nullWritable, int numPartitions) {
        return combkey2.getCid()%numPartitions;
    }
}
