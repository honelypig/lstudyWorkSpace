package httprequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SocketHelper {
	public static String send(String urlParam, Map<String, Object> params, String charset) {
		String result="";
		StringBuffer sbParams=new StringBuffer();
		if(params!=null && params.size()>0){
			for (Entry<String, Object> entry : params.entrySet()) {
				sbParams.append(entry.getKey());
				sbParams.append("=");
				//sbParams.append(entry.getValue());
				try {			
					sbParams.append(URLEncoder.encode(String.valueOf(entry.getValue()), charset));	
					} catch (UnsupportedEncodingException e) {			
						throw new RuntimeException(e);		
			}
				sbParams.append("&");
			}
		}
		Socket socket=null;
		OutputStreamWriter osw=null;
		InputStream is=null;
		try {
			URL url=new URL(urlParam);
			String host=url.getHost();
			System.out.println("===>"+host);
			int port=url.getPort();
			System.out.println("===>"+port);
			if(-1==port){
				port=80;
			}
			String path=url.getPath();
			socket=new Socket(host,port);
			StringBuffer sb=new StringBuffer();
			sb.append("POST " + path + " HTTP/1.1\r\n");		
			sb.append("Host: " + host + "\r\n");	
			sb.append("Connection: Keep-Alive\r\n");
			sb.append("Content-Type: application/x-www-form-urlencoded; charset=utf-8 \r\n");		
			sb.append("Content-Length: ").append(sb.toString().getBytes().length).append("\r\n");
			sb.append("\r\n");
			if (sbParams != null && sbParams.length() > 0) {
				sb.append(sbParams.substring(0, sbParams.length() - 1));
			}
			osw=new OutputStreamWriter(socket.getOutputStream());
			osw.write(sb.toString());
			osw.flush();
			is=socket.getInputStream();
			System.out.println(is.toString());
			System.out.println(is.available());

			String temp=null;
			// 服务器响应体数据长度		
			int contentLength = 0;			
			// 读取http响应头部信息			
			do {			
				temp = readLine(is, 0, charset);		
				if (temp.startsWith("Content-Length")) {	
					// 拿到响应体内容长度				
					contentLength = Integer.parseInt(temp.split(":")[1].trim());	
					}				// 如果遇到了一个单独的回车换行，则表示请求头结束		
				} while (!temp.equals("\r\n"));			
			// 读取出响应体数据（就是你要的数据）		
			result = readLine(is, contentLength, charset);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {			
			if (osw != null) {	
				try {			
					osw.close();	
					} catch (IOException e) {		
						osw = null;				
						throw new RuntimeException(e);	
						} finally {				
							if (socket != null) {		
								try {					
									socket.close();		
									} catch (IOException e) {	
										socket = null;			
										throw new RuntimeException(e);	
										}				
								}			
							}		
				}		
			if (is != null) {		
				try {				
					is.close();		
					} catch (IOException e) {	
						is = null;				
						throw new RuntimeException(e);
						} finally {			
							if (socket != null) {		
								try {				
									socket.close();		
									} catch (IOException e) {	
										socket = null;	
										throw new RuntimeException(e);	
										}			
								}			
							}		
				}		
			}
		return result;
	}
	public static String sendGet(String urlParam, Map<String, Object> params, String charset) {
		return "";
	}
	private static String readLine(InputStream is, int contentLength, String charset) throws IOException {
		List<Byte> lineByte = new ArrayList<Byte>();	
		byte tempByte;		
		int cumsum = 0;		
		if (contentLength != 0) {		
			do {			
				tempByte = (byte) is.read();	
				lineByte.add(Byte.valueOf(tempByte));			
				cumsum++;		
				} while (cumsum < contentLength);
			// cumsum等于contentLength表示已读完		
			} else {		
				do {		
					tempByte = (byte) is.read();		
					lineByte.add(Byte.valueOf(tempByte));		
					} while (tempByte != 10);// 换行符的ascii码值为10	
				} 		
		byte[] resutlBytes = new byte[lineByte.size()];		
		for (int i = 0; i < lineByte.size(); i++) {		
			resutlBytes[i] = (lineByte.get(i)).byteValue();		
			}	
		return new String(resutlBytes, charset);	
	}
		
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("appcode", "TDZZ");
	String result=send("http://localhost:7090/ApplicationConfigs", params, "UTF-8");
	System.out.println(result);
	}

}
