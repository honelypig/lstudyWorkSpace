package com.zda.utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpRequestUtils {

    /**
     * 通过传递参数：url和image进行文字识别
     *
     * @param param 区分是url还是image识别
     * @return 识别结果
     * @throws URISyntaxException URI打开异常
     * @throws IOException        IO流异常
     */
    public static String HttpPost(String param, String requestUrl) throws URISyntaxException, IOException {
        //开始搭建post请求
        HttpClient httpClient =HttpClients.createDefault();
        HttpPost post = new HttpPost();
        URI url = new URI(requestUrl);
        post.setURI(url);
        //设置请求头，请求头必须为application/x-www-form-urlencoded，因为是传递一个很长的字符串，不能分段发送
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        StringEntity entity = new StringEntity(param);
        post.setEntity(entity);
        HttpResponse response = httpClient.execute(post);
        System.out.println(response.toString());
        if (response.getStatusLine().getStatusCode() == 200) {
            String str;
            try {
                /*读取服务器返回过来的json字符串数据*/
                str = EntityUtils.toString(response.getEntity());
                System.out.println(str);
                return str;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String URLGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //connection.setRequestProperty("Content-Type",  "application/json");
            // connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            // for (String key : map.keySet()) {
            //System.out.println(key + "--->" + map.get(key));
            //}
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url    发送请求的 URL
     * @param params 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String URLPost(String url, Map params) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    public static String URLPostJson(String url, String json) {
        String result = "";
        try {
            URL targetUrl = new URL(url);
            HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.setRequestProperty("Accept-Charset", "UTF-8");
            httpConnection.setRequestProperty("contentType", "UTF-8");
            OutputStream outputStream = httpConnection.getOutputStream();
            outputStream.write(json.getBytes());
            outputStream.flush();
            if (httpConnection.getResponseCode() == 200) {
                BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
                        (httpConnection.getInputStream()), "UTF-8"));
                String output;
                while ((output = responseBuffer.readLine()) != null) {
                    result += output;
                }
            }
            httpConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    public static String HttpPut(String url, Map<String, Object> params) {
        String result = "";
        try {
            HttpClient client = HttpClients.createDefault();
            HttpResponse response = null;
            HttpPut request = new HttpPut(url);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (String key : params.keySet()) {
                nvps.add(new BasicNameValuePair(key, String.valueOf(params.get(key))));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nvps, "UTF-8");
            request.setEntity(formEntity);
            response = client.execute(request);
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public static String call(final String url, final String method, String param) {
        String result = null;
        HttpURLConnection conn = null;
        try {
            URL target = new URL(url);
            conn = (HttpURLConnection) target.openConnection();
            conn.setRequestMethod(method);
            conn.setRequestProperty("Accept", "application/json");
            if (200 != conn.getResponseCode()) {
                throw new RuntimeException("failed, error code is " + conn.getResponseCode());
            }
            byte[] temp = new byte[conn.getInputStream().available()];
            if (conn.getInputStream().read(temp) != -1) {
                result = new String(temp);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return result;
    }

    /**
     * 构造json字符串用于url使用
     *
     * @param json   json字符串
     * @param encode 编码格式，如UTF-8等
     * @return
     */
    public static String encodeUrl(String json, String encode) {
        try {
            return URLEncoder.encode(json, encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return json;
        }
    }

    public static void main(String[] args) {
//        // System.out.println(call("http://localhost:8080/TAP_API/sys/dept/11519520", "GET"));
//        System.out.println(call("http://localhost:8080/TAP_API/sys/dept", "PUT",""));//POST PUT
    }

    public static void main2(String[] args) {
//        //发送 GET 请求
//        // String s=HttpRequest.sendGet("http://localhost:8080/TAP_API/sys/dept/11519520", "key=123&v=456");
//        String s=HttpRequest.HttpGet("http://localhost:8080/TAP_API/sys/dept/11519520", "key=123&v=456");
//        Map map = JSONFactory.JSONToMap(s);
//        System.out.println(map);
//        System.out.println(map.get("data"));
//
//        //发送 POST 请求
//        String sr=HttpRequest.HttpGet("http://localhost:8080/TAP_API/sys/dept", "key=123&v=456");
//        System.out.println(sr);
    }
}
