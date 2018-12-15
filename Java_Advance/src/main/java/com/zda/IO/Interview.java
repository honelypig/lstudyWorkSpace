package com.zda.IO;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;

public class Interview {
    public static void main(String args[]) {
        recursionPrintFile("E:\\bigData");
    }

    /**
     * 复制文件
     */
    public static void copyFile() {
        File origin = new File("d:/helloWorld.txt");//原始文件
        if (!origin.exists()) {
            try {
                origin.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File destination = new File("d:/helloWorld4.txt");//目的文件
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(origin);
            out = new FileOutputStream(destination);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = in.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 下载网络资源
     * @param urlStr
     * @param fileName
     * @param savePath
     */
    public static void downLoadFile(String urlStr, String fileName, String savePath){
        InputStream is=null;
        BufferedOutputStream baos=null;
        try {
            URL url=new URL(urlStr);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(3*1000);
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            File file=new File(savePath,fileName);
            file.getParentFile().mkdirs();

            baos=new BufferedOutputStream(new FileOutputStream(file));
            is=conn.getInputStream();

            int count=0;
            byte[] buff=new byte[8*1024];

            while ((count=is.read(buff))!=0){
                baos.write(buff,0,count);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 递归输出目录
     * @param path
     */
    public static void recursionPrintFile(String path){
        if (path == null) {
            return;// 因为下面的new File如果path为空，回报异常
        }
        File[] files=new File(path).listFiles();
        if(files==null) return;
        for (File file : files) {
            if(file.isFile()){
                System.out.println(file.getName());
            }else if(file.isDirectory()){
                recursionPrintFile(file.getPath());
            }else{
                System.err.println("ERROR");
            }
        }
    }
    public static String getSplit(String str,int count){
        String result="";
        for (int i=0;i<count;count++){
            result+=str;
        }
        result+="  ";
        return result;
    }
}
