package com.zda.hdfs;

import com.hadoop.compression.lzo.LzoCodec;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.*;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 解压缩
 */
public class Compress {
    public  static void main(String[] args) throws IOException {
        Class[] zipClasses={
                DeflateCodec.class,
                GzipCodec.class,
                BZip2Codec.class,
                //SnappyCodec.class,
               // Lz4Codec.class,
                LzoCodec.class
        };
        for (Class zipClass : zipClasses) {
            zip(zipClass);
        }
        System.out.println("------------------------");
        for (Class zipClass : zipClasses) {
            unzip(zipClass);
        }
    }

    public static void zip(Class codecClass) throws IOException {
        long start=System.currentTimeMillis();
        //实例化对象
        CompressionCodec codec=(CompressionCodec) ReflectionUtils.newInstance(codecClass,new Configuration());
        //创建文件输出流，得到默认扩展名
        FileOutputStream fos=new FileOutputStream("d:/mr/comp/a.copy"+codec.getDefaultExtension());
        //创建压缩流
        CompressionOutputStream cos=codec.createOutputStream(fos);

        IOUtils.copyBytes(new FileInputStream("d:/mr/comp/a.pdf"),cos,1024);
        cos.close();
        System.out.println(codecClass.getSimpleName() + " : " + (System.currentTimeMillis() - start));
    }

    public  static void unzip(Class codecClass) throws IOException {
        long start=System.currentTimeMillis();
        CompressionCodec codec=(CompressionCodec) ReflectionUtils.newInstance(codecClass,new Configuration());
        //创建文件输入流,得到默认扩展名
        FileInputStream fis=new FileInputStream("d:/mr/comp/a.copy"+codec.getDefaultExtension());
        //得到压缩流
        CompressionInputStream cis=codec.createInputStream(fis);

        IOUtils.copyBytes(cis,new FileOutputStream("d:/mr/comp/b" + codec.getDefaultExtension() + ".pdf"),1024);

        cis.close();

        System.out.println(codecClass.getSimpleName() + " : " + (System.currentTimeMillis() - start));

    }
}
