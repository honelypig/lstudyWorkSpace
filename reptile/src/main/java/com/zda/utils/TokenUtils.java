package com.zda.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class TokenUtils {
    /**
     * 获取权限token
     *
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    public static String getBaiduAuth() { //获取的地址为https://console.bce.baidu.com/ai/?_=1541559087079#/ai/ocr/app/detail~appId=618159
        //百度host
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        // 官网获取的 API Key 更新为你注册的
        String clientId = "rEm2ZPVT5AC2ySnlHBnlHr83";// "你注册应用时生成的API Key";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "Bxp3OGeQvWqUb3xNZVkNTe8PC2kF776G ";// "你注册应用时生成的Secret Key";
        //百度的参数
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + clientId
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + clientSecret;
        String params="grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + clientId
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + clientSecret;
        return getBaiduAuth(authHost, params);
    }

    /**
     * http://ai.baidu.com/docs#/Auth/top
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     *
     * @param authHost - 获取token的主机地址
     * @param params -需要的参数
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    private static String getBaiduAuth(String authHost, String params) {
        String getAccessTokenUrl = authHost;
        if(authHost.endsWith("?")){
            getAccessTokenUrl+=params;
        }else{
            getAccessTokenUrl+=("?"+params);
        }
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = JSON.parseObject(result.toString());
            return jsonObject.getString("access_token");
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

    public static void main(String[] args) {
        String auth = getBaiduAuth();
        System.out.println(auth);
    }

}
