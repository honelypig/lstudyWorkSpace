package com.zda.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.Text;
import org.junit.Test;

import java.io.IOException;

/**
 * mapFile,相当于加有索引和排过序的sequenceFile
 */
public class MapFileOp {
    @Test
    public  void write() throws IOException {
        Configuration conf=new Configuration();
        conf.set("fs.defaultFS","file:///");

        FileSystem fs = FileSystem.get(conf);
        MapFile.Writer writer = new MapFile.Writer(conf,fs,"d:/mr/map", IntWritable.class, Text.class);
        for(int i = 0 ; i < 100 ; i ++){
            writer.append(new IntWritable(i),new Text("tom" + i));
            writer.append(new IntWritable(i),new Text("tom" + i));
        }
        writer.close();
    }

    @Test
    public  void read() throws IOException {
        Configuration conf=new Configuration();
        conf.set("fs.defaultFS","file:///");

        FileSystem fs = FileSystem.get(conf);
        MapFile.Reader reader = new MapFile.Reader(fs,"d:/mr/map",conf);
        IntWritable key = new IntWritable();
        Text value = new Text();
        while (reader.next(key,value))
        {
            System.out.println(key+":"+value);
        }
        reader.close();
    }
}
