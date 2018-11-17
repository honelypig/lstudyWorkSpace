package com.zda.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.junit.Test;

import java.io.IOException;

/**
 * 用java操作远程dfs系统
 */
public class MyHdfsDemo {

    /**
     * 读取文件内容
     */
    @Test
    public  void read() throws  Exception{
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://192.168.209.200:9000");
        FileSystem fs = FileSystem.get(conf);
        Path path=new Path("/logs/2014/10/10/log.txt");
        FSDataInputStream in =fs.open(path);
//        IOUtils.copyBytes(in, System.out,4096,false);
//        System.out.println("==>seek from 2");
        in.seek(2);//设置偏移量,消耗较高
        IOUtils.copyBytes(in, System.out,4096,false);
        IOUtils.closeStream(in);
    }
    /**
     * 创建一个文件并写入数据
     */
    @Test
    public void testCreateFileWrite() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://192.168.209.200:9000");
        FileSystem fs = FileSystem.get(conf);
        FSDataOutputStream fout = fs.create(new Path("/user/zhangdada/c.txt"), new Progressable() {
            @Override
            public void progress() {
                System.out.println("当做回调用.正在写入。。。。");
            }
        });
        fout.write("how are you?/n".getBytes());
        fout.write("换行~".getBytes());
       IOUtils.closeStream(fout);
    }

    /**
     * 追加
     * @throws Exception
     */
    @Test
    public  void testAppendWrite() throws  Exception{
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://192.168.209.200:9000");
        conf.setBoolean("dfs.support.append", true);
        FileSystem fs = FileSystem.get(conf);
        Path path=new Path("/user/zhangdada/b.txt");
        FSDataOutputStream fsdo= fs.append(path);
        fsdo.write("  append".getBytes());
        IOUtils.closeStream(fsdo);
    }
    /**
     * 创建文件夹，一般create文件会自动创建文件夹
     */
    @Test
    public  void testMkdir() throws  Exception{
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://192.168.209.200:9000");
        FileSystem fs = FileSystem.get(conf);
     //   Path path=new Path("/logs");
        String uri="/logs/";
        for (int n=2014;n<=2018;n++){
            String s=uri+n;
            for (int y=1;y<13;y++){
                if(y==1 ||y==3 ||y==5 ||y==7 ||y==8||y==10 ||y==12 ){
                    for(int r=1;r<=31;r++){
                        createFile(fs,s+"/"+y+"/"+r);
                        //fs.mkdirs(new Path(s+"/"+y+"/"+r));
                    }
                }else if(y==4 ||y==6 ||y==9 ||y==11 ){
                    for(int r=1;r<=30;r++){
                        createFile(fs,s+"/"+y+"/"+r);
                       // fs.mkdirs(new Path(s+"/"+y+"/"+r));
                    }
                }else{
                    for(int r=1;r<=27;r++){
                        createFile(fs,s+"/"+y+"/"+r);
                        //fs.mkdirs(new Path(s+"/"+y+"/"+r));
                    }
                }

            }
        }
     //  fs.mkdirs(path);
    }
    public void createFile(FileSystem fs,String url) throws  Exception{
        FSDataOutputStream fout = fs.create(new Path(url+"/log.txt"));
        fout.write(url.getBytes());
        IOUtils.closeStream(fout);
    }
    /**
     * 查看路径下的文件  listStatus
     */
    @Test
    public  void testLookFile() throws  Exception{
        FileSystem fs =getFileSystem();
         Path path=new Path("/user");
        FileStatus[] fileStatus= fs.listStatus(path);

        for (FileStatus status : fileStatus) {
            System.out.println(status.getPath());
        }
    }
    /**
     * 查看路径下的文件，使用正则匹配 globStatus
     */
    @Test
    public  void testLookFileWithReg() throws  Exception{
        FileSystem fs =getFileSystem();
        Path path=new Path("/logs/201[45]/[1-9]/[12]/*.txt");
        FileStatus[] fileStatus= fs.globStatus(path,new RegexExcludePathFilter(""));

        for (FileStatus status : fileStatus) {
            System.out.println(status.getPath());
        }
        
        System.out.println("自己定义的匹配器");
        fileStatus= fs.globStatus(new Path("/logs/2017/*/*/*.txt"),new RegexExcludePathFilter("^.*/2017/12/31$"));

        for (FileStatus status : fileStatus) {
            System.out.println(status.getPath());
        }
    }
    public FileSystem getFileSystem () throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://192.168.209.200:9000");
        return  FileSystem.get(conf);
    }

    /**
     *
     */
    @Test
    public void testDeleteFile()throws Exception{
        FileSystem fs=getFileSystem();
        fs.delete(new Path("/user/zhangdada/c.txt"),true);
    }
}
