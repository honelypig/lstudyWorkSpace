package com.zda.spider.gecco.main;

import com.geccocrawler.gecco.GeccoEngine;


public class Main {
    public static void main(String[] args) {
        GeccoEngine.create()
                //工程的包路径
                .classpath("com.zda.spider.gecco")
                //开始抓取的页面地址
                .start("http://www.cnblogs.com/boychen/p/7226831.html")
                //开启几个爬虫线程
                .thread(10)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(5)
                //使用pc端userAgent
                .mobile(false)
                //开始运行
                .run();
    }
}
