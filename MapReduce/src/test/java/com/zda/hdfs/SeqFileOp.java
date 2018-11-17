package com.zda.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Test;

import java.io.IOException;


/**
 * 序列文件
 */
public class SeqFileOp {

    /**
     * 创建一个seq文件，并写入数据
     */
    @Test
    public void write() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");//表示本地模式

        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("d:/mr/seq/1.seq");
        //创建一个writer，并制定key和value的类型
        SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf, path, IntWritable.class, Text.class);
        for (int i = 0; i <= 10; i++) {
            writer.append(new IntWritable(i), new IntWritable(i));
            //添加一个同步点
            writer.sync();
        }
        for (int i = 0; i < 10; i++) {
            writer.append(new IntWritable(i), new Text("tom" + i));
            if (i % 2 == 0) {
                writer.sync();
            }
        }
        writer.close();
    }

    @Test
    public void read() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");//表示本地模式

        FileSystem fs = FileSystem.get(conf);
   //     Path path = new Path("d:/mr/seq/1.seq");
        Path path=new Path("d:/mr/data/tempSeq.seq");
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, path, conf);
        Writable key = (Writable) ReflectionUtils.newInstance(reader.getKeyClass(), conf);
        Writable value = (Writable) ReflectionUtils.newInstance(reader.getValueClass(), conf);
        long position = reader.getPosition();
        while (reader.next(key, value)) {
            System.out.println("读之前的长度：" + position);
            System.out.println(key + "===>" + value);
            position = reader.getPosition();
        }
        reader.close();

    }

    /**
     * 读操作,得到当前value
     */
    @Test
    public void read2() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("d:/mr/seq/1.seq");
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, path, conf);

        IntWritable key = new IntWritable();
        Text value = new Text();
        while (reader.next(key)) {
            reader.getCurrentValue(value);
            System.out.println(value.toString());
        }
        reader.close();
    }

    /**
     * 读操作
     */
    @Test
    public void read3() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("d:/mr/seq/1.seq");
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, path, conf);
        IntWritable key = new IntWritable();
        Text value = new Text();
        reader.seek(288);

        reader.next(key, value);
        System.out.println(value.toString());
        reader.close();
    }

    /**
     * 操纵同步点
     */
    @Test
    public void read4() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("d:/mr/seq/1.seq");
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, path, conf);
        IntWritable key = new IntWritable();
        Text value = new Text();

        //
        reader.sync(648);
        while (reader.next(key, value)) {
            System.out.println(reader.getPosition() + "   " + key.get() + "-" + value.toString());
        }
        reader.close();
    }
}
