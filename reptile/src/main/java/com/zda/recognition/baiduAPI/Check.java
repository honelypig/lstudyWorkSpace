package com.zda.recognition.baiduAPI;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class Check {

    public static void main(String[] args) {
        String path = "E:\\bigData\\workspace\\imgs\\esik.png";

            long now = System.currentTimeMillis();
           String str=BaiduApiAdapter.accurate(path);     //图片识别
            JSONObject jsonObject=JSONObject.parseObject(str);
            JSONArray jsonArray=jsonObject.getJSONArray("words_result");
            jsonArray.forEach(a->{
                JSONObject ja=(JSONObject)a;
                System.out.println(a);
                System.out.println(ja.get("words"));
            });

        System.out.println("耗时：" + (System.currentTimeMillis() - now) / 1000 + "s");

    }
}