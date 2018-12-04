package com.zda.recognition.baiduAPI;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MyUse {
    public static void main(String []args){
        check("E:\\1.png");
    }
//识别某张图片内容
    public  static void check(String path){
        String str=BaiduApiAdapter.accurate(path);     //图片识别
        JSONObject jsonObject=JSONObject.parseObject(str);
        JSONArray jsonArray=jsonObject.getJSONArray("words_result");
        jsonArray.forEach(a->{
            JSONObject ja=(JSONObject)a;
            System.out.println(a);
            System.out.println(ja.get("words"));
        });

    }
}
