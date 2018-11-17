package com.zda.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class PrepareData {

    @Test
    public void writeSeq() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");//表示本地模式

        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("d:/mr/seq/1.seq");
        SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf, path, IntWritable.class, Text.class);
        for (int i = 0; i <= 10; i++) {
            writer.append(new IntWritable(i), new Text("tom" + i));
            //添加一个同步点
            writer.sync();
        }
        writer.close();
    }

    @Test
    public void makeData() throws IOException {
        FileWriter fw = new FileWriter("d:/mr/temp.txt");
        for(int i = 0 ; i < 6000 ; i ++){
            int year = 1970 + new Random().nextInt(100) ;
            int temp = -30 + new Random().nextInt(100);
            fw.write("" + year + " " + temp + "\r\n");
        }
        fw.close();
    }
    @Test
    public void prepareSeqTempData() throws IOException {
        Configuration conf=new Configuration();
        conf.set("fs.defaultFS","file:///");

        FileSystem fs=FileSystem.get(conf);
        Path p=new Path("d:/MyShare/tempSeq.seq");
        //创建一个writer，并制定key和value的类型
        SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf, p, IntWritable.class, IntWritable.class);
        for (int i = 0; i <= 60; i++) {
            int year = 2010 + new Random().nextInt(10) ;
            int temp = -30 + new Random().nextInt(100);
            writer.append(new IntWritable(year),new IntWritable(temp));
            //添加一个同步点
           // writer.sync();
        }
        writer.close();

    }
}
