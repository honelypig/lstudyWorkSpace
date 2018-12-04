package com.zda.spider.gecco.pipelines;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.zda.spider.gecco.pojo.Blog;

/**
 * @author cwj
 * 2017年8月6日
 * 运行完Blog.java 根据@PipelineName 来这里
 */
@PipelineName(value="blogPipelines")
public class BlogPipelines implements Pipeline<Blog> {

    /**
     * 将抓取到的内容进行处理  这里是打印在控制台
     */
    public void process(Blog blog) {
        System.out.println(blog.getRequest().getUrl());
        //System.out.println(blog.getContent());
    }

}