package httprequest;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
public class HttpURLConnectionHelper {
	
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
		HttpURLConnection con=null;
		OutputStreamWriter osw=null;
		BufferedReader br=null;
		try {
			URL url=new URL(urlParam);
			con=(HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			if(sbParams!=null&&sbParams.length()>0){
				osw=new OutputStreamWriter(con.getOutputStream(), charset);
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
				}finally {
					if(con!=null){
						con.disconnect();
						con=null;
					}
				}
			}
			if(br!=null){
				try {
					br.close();
				} catch (Exception e) {
					br=null;
					throw new RuntimeException(e);
				}finally {
					if(con!=null){
						con.disconnect();
						con=null;
					}
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
		HttpURLConnection con=null;
		BufferedReader br=null;
		try {
			if(sbParams.length()>0){
				urlParam=urlParam+"?"+sbParams.substring(0,sbParams.length()-1).toString();
			}
			URL url=new URL(urlParam);
			con=(HttpURLConnection)url.openConnection();
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
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
				}finally {
					if(con!=null){
						con.disconnect();
						con=null;
					}
				}
			}
		}
		return resultBuffer.toString();
	}
	public static void downRemoteFile(String urlParam, Map<String, Object> params, String fileSavePath) {
		StringBuffer sbParams=new StringBuffer();
		if(params!=null  && params.size()>0){
			params.forEach((key,value)->{
				sbParams.append(key);
				sbParams.append("=");
				sbParams.append(value);
				sbParams.append("&");
			});
		}
		HttpURLConnection con=null;
		BufferedReader br=null;
		FileOutputStream fos=null;
		try {
			if(sbParams.length()>0){
				urlParam=urlParam+"?"+sbParams.substring(0,sbParams.length()-1).toString();
			}
			URL url=new URL(urlParam);
			con=(HttpURLConnection)url.openConnection();
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.connect();
			InputStream in=con.getInputStream();
			fos=new FileOutputStream(fileSavePath);
			byte []buf=new byte[1024];
			int count=0;
			String temp;
			while ((count=in.read(buf))!=-1) {
				fos.write(buf,0,count);
			}
			fos.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			if(fos!=null){
				try {
					fos.close();
				} catch (Exception e) {
					fos=null;
					throw new RuntimeException(e);
				}finally {
					if(con!=null){
						con.disconnect();
						con=null;
					}
				}
			}
			if(br!=null){
				try {
					br.close();
				} catch (Exception e) {
					br=null;
					throw new RuntimeException(e);
				}finally {
					if(con!=null){
						con.disconnect();
						con=null;
					}
				}
			}
		}
	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("appcode", "TDZZ");
	String result=sendGet("http://localhost:7090/ApplicationConfigs", params, "UTF-8");
	System.out.println(result);
	}

}
