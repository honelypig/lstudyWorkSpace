package com.zda.spider.Jsoup;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;

public class JsoupFirst {
    //Document document= Jsoup.connect(url).get()获取一整个页面
    //再用document.getElementsXXX获取页面的对象
    public static void main(String[] args) throws IOException {
        String url="https://www.zhipin.com/job_detail/?query=&scity=101230100&industry=&position=100101";
        printElement(url);
        //https://www.zhipin.com/c101230100-p100101/?page=2&ka=page-next
        //https://www.zhipin.com/c101230100-p100101/?page=3&ka=page-next
    }

    public static  void printElement(String url)throws IOException{
        Document document= Jsoup.connect(url).get();
        Elements elements= document.getElementsByClass("info-primary");
        StringBuilder sb=new StringBuilder();
        elements.forEach(a->{
            Elements children=a.select("a");
         String nextHref=   children.attr("href");
         String data_lid=children.attr("data-lid");
         String ka=children.attr("ka");
         String nextUrl="https://www.zhipin.com" +nextHref+"?ka="+ka+"_blank&lid="+data_lid;
            try {
                Document detail= Jsoup.connect(nextUrl).get();

                //要求
                Elements job_banner=detail.getElementsByClass("job-banner");
                String companyName=job_banner.select("h3.name").text();
                //薪资
                String salary=job_banner.select(".badge").text().trim();
                String basemsg=job_banner.select(".info-primary").select("p").toString().replace("<em class=\"vline\"></em>",",");

                String msg=basemsg.substring(3,basemsg.length()-4);
                //发布时间
                String time=job_banner.select(".info-primary").select("span.time").text();
                //公司网址
                Elements info_company_p=detail.getElementsByClass("info-company").select("p");
                String companyUrl="";
                if(info_company_p.size()==2){
                    companyUrl=detail.getElementsByClass("info-company").select("p").last().text();
                }
                //职位描述
                Elements detail_content=detail.getElementsByClass("detail-content");
                String content=detail_content.select("div").first().select(".text")
                        .first()
                        .html().toString()
                        .replace("<br>","")
                        .replace("\n","\n       ");
//                        .replace("&nbsp;","")
                //公司介绍
                System.out.println(companyName+":  "+nextUrl);
                System.out.println("|--薪资"+salary);
                System.out.println("|--发布时间"+time);
                System.out.println("|--要求"+msg);
                System.out.println("|--公司网址"+companyUrl);
                System.out.println("|--"+content);
                sb.append(companyName+":  "+nextUrl+"\n");
                sb.append("|--薪资"+salary+"\n");
                sb.append("|--发布时间"+time+"\n");
                sb.append("|--要求"+msg+"\n");
                sb.append("|--公司网址"+companyUrl+"\n");
                sb.append("|--"+content+"\n");

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        WriteStringToFile5("E://work.txt",sb.toString());
    }
    public static void WriteStringToFile5(String filePath,String sb) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(sb.replace("\n","\r\n").getBytes());
            fos.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
