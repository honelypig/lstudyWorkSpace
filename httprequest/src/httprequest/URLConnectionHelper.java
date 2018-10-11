package httprequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class URLConnectionHelper {

	public static String sendPost(String urlParam, Map<String, Object> params, String charset) {
		StringBuffer resultBuffer=null;
		StringBuffer sbParams=new StringBuffer();
		if(params!=null  && params.size()>0){
			params.forEach((key,value)->{
				sbParams.append(key);
				sbParams.append("=");
				sbParams.append(value);
				sbParams.append("&");
			});
		}
		URLConnection  con=null;
		OutputStreamWriter osw=null;
		BufferedReader br=null;
		try {
			URL realUrl =new URL(urlParam);
			con=realUrl.openConnection();
			con.setRequestProperty("accept", "*/*");
			con.setRequestProperty("connection", "Keep-Alive");	
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");			
			con.setDoOutput(true);
			con.setUseCaches(false);
			
			osw=new OutputStreamWriter(con.getOutputStream(), charset);
			if(sbParams!=null&&sbParams.length()>0){
				//´«Êä²ÎÊý
				osw.write(sbParams.substring(0,sbParams.length()-1));
				osw.flush();
			}
			resultBuffer=new StringBuffer();
			int contentLength=Integer.parseInt(con.getHeaderField("Content-Length"));
			if(contentLength>0){
				br=new BufferedReader(new InputStreamReader(con.getInputStream(),charset));
				String temp;
				while ((temp=br.readLine())!=null) {
					resultBuffer.append(temp);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			if(osw!=null){
				try {
					osw.close();
				} catch (Exception e) {
					osw=null;
					throw new RuntimeException(e);
				}
			}
			if(br!=null){
				try {
					br.close();
				} catch (Exception e) {
					br=null;
					throw new RuntimeException(e);
				}
			}
		}
		
		return resultBuffer.toString();
	}
	public static String sendGet(String urlParam, Map<String, Object> params, String charset) {
		StringBuffer resultBuffer=null;
		StringBuffer sbParams=new StringBuffer();
		if(params!=null  && params.size()>0){
			params.forEach((key,value)->{
				sbParams.append(key);
				sbParams.append("=");
				sbParams.append(value);
				sbParams.append("&");
			});
		}
		BufferedReader br=null;
		try {
			if(sbParams.length()>0){
				urlParam=urlParam+"?"+sbParams.substring(0,sbParams.length()-1).toString();
			}
			URL url=new URL(urlParam);
			URLConnection con=url.openConnection();
			con.setRequestProperty("accept", "*/*");
			con.setRequestProperty("connection", "Keep-Alive");	
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");	
			con.connect();
			resultBuffer=new StringBuffer();
			br=new BufferedReader(new InputStreamReader(con.getInputStream(),charset));
			String temp;
			while ((temp=br.readLine())!=null) {
				resultBuffer.append(temp);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			if(br!=null){
				try {
					br.close();
				} catch (Exception e) {
					br=null;
					throw new RuntimeException(e);
				}
			}
		}
		return resultBuffer.toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("appcode", "TDZZ");
	String result=sendGet("http://localhost:7090/ApplicationConfigs", params, "UTF-8");
	System.out.println(result);

	}

}
