/**
 * S. Chatchawal 
 * Nov 29, 2560 BE 10:30:39 AM
 * http://www.conan.in.th
 * chatchawal.sangkeettrakarn@nectec.or.th, conan@conan.in.th
 */
package collector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * @author conan
 *
 */
public class CNCollector {
	public static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:5.0) Gecko/20100101 Firefox/5.0";
	public static int BUFFER_SIZE = 128;
	public static String ENCODING = "utf-8";
	public static String CONTENT_TYPE = "text/plain";
	private static String responseContent = "";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println(CNCollector.get("http://google.com"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String get(String reqUrl) throws IOException {
		HttpURLConnection http = (HttpURLConnection) new URL(reqUrl).openConnection();
		http.setRequestProperty("User-agent", USER_AGENT);
		http.setRequestProperty("Content-type", CONTENT_TYPE+";charset="+ENCODING);
		http.setRequestMethod("GET");
		http.connect();
		int respCode = http.getResponseCode();
		
		if(respCode != 200) 
			return ""+respCode;
		
		StringBuffer contents = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				http.getInputStream(),ENCODING), BUFFER_SIZE);
		String line = "";

		while ((line = reader.readLine()) != null) {
			contents.append(line);
		}

		reader.close();
		String response = (contents == null) ? "" : contents.toString().trim();
		responseContent = response;
		return response;
	}
	
	public static String post(String requrl) throws IOException{
		
		String uurl = requrl.substring(0, requrl.indexOf('?'));
		URL url = new URL(uurl);
		String data = requrl.substring(requrl.indexOf("?")+1);
		System.out.println("url: "+uurl+"\ndata: "+data);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("User-agent", USER_AGENT);
		conn.setRequestProperty("Content-type", "text/html;charset=utf8");
		conn.setRequestProperty("Content-length", data.getBytes().length + "");
		conn.setDoInput(true);
		conn.setDoOutput(true);

		
		// send data
		OutputStream output = conn.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
		writer.close();
	
		int responseCode = conn.getResponseCode();
		
		if(responseCode == 200){
			// get response
			InputStream input = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			StringBuffer buf = new StringBuffer();
			String line = "";
			while((line = reader.readLine()) != null){
				buf.append(line);
			}
			
			responseContent = buf.toString();
			if(reader != null) reader.close();
			if(input != null) input.close();
			return responseContent;
		}
		
		if(writer != null) writer.close();
		if(output != null) output.close();
		if(conn != null) conn.disconnect();
		return "";
	}
	
	public static String post(String requrl,String enc) throws IOException{
		
		URL url = new URL(requrl);
		String data = requrl.substring(requrl.indexOf("?") );
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("User-agent", USER_AGENT);
		conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-length", data.getBytes().length + "");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		
		// send data
		OutputStream output = conn.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
		writer.close();
	
		int responseCode = conn.getResponseCode();
		
		String cont = "";
		
		if(responseCode == 200){
			// get response
			InputStream input = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input,enc));
			StringBuffer buf = new StringBuffer();
			String line = "";
			while((line = reader.readLine()) != null){
				buf.append(line);
			}
			
			cont= buf.toString();
			if(reader != null) reader.close();
			if(input != null) input.close();
			
			return cont;
		}
		
		if(writer != null) writer.close();
		if(output != null) output.close();
		if(conn != null) conn.disconnect();
		return cont;
	}
	

	public  String postHttps(String requrl) throws IOException{
		
		URL url = new URL(requrl);
		String data = requrl.substring(requrl.indexOf("?") );
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("User-agent", USER_AGENT);
		conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-length", data.getBytes().length + "");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setConnectTimeout(20000);
		conn.setReadTimeout(20000);
		conn.connect();

		// send data
		OutputStream output = conn.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
		writer.close();
	
		int responseCode = conn.getResponseCode();
		
		String cont = "";
		
		if(responseCode == 200){
			// get response
			InputStream input = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input,"utf8"));
			StringBuffer buf = new StringBuffer();
			String line = "";
			while((line = reader.readLine()) != null){
				buf.append(line);
			}
			
			cont= buf.toString();
			if(reader != null) reader.close();
			if(input != null) input.close();
			
			return cont;
		}
		
		if(writer != null) writer.close();
		if(output != null) output.close();
		if(conn != null) conn.disconnect();
		return cont;
	}
	
	public static String getHttps(String reqUrl) throws IOException{
		HttpsURLConnection https = (HttpsURLConnection) new URL(reqUrl).openConnection();
		https.setRequestProperty("User-agent", USER_AGENT);
		https.setRequestProperty("Content-type", CONTENT_TYPE+";charset="+ENCODING);
		https.setRequestMethod("GET");
		https.connect();
		int respCode = https.getResponseCode();
		
		if(respCode != 200) 
			return ""+respCode;
		
		StringBuffer contents = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				https.getInputStream()), BUFFER_SIZE);
		String line = "";

		while ((line = reader.readLine()) != null) {
			contents.append(line);
		}

		reader.close();
		String response = (contents == null) ? "" : contents.toString().trim();
		responseContent = response;
		return response;
	}
	
	
	
	public String getResponse(){
		return responseContent;
	}
}
