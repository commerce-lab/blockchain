package util.http;
 
 import java.io.BufferedReader;
 import java.io.IOException;
import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.OutputStream;
import java.net.ConnectException;
 import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
 import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

 import javax.net.ssl.HttpsURLConnection;
 import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
// import org.apache.http.HttpEntity;
// import org.apache.http.client.methods.CloseableHttpResponse;
// import org.apache.http.client.methods.HttpPost;
// import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
// import org.apache.http.entity.StringEntity;
// import org.apache.http.impl.client.CloseableHttpClient;
// import org.apache.http.impl.client.HttpClients;
//import org.apache.http.ssl.SSLContexts;
//import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 public class HttpsUtil
 {
	 private static Logger log = LoggerFactory.getLogger(HttpsUtil.class);
	 public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
	     StringBuffer buffer = null;
	   try
	     {
		   SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		   sslContext.init(null, null, new SecureRandom());
	       SSLSocketFactory ssf = sslContext.getSocketFactory();
	      URL url = new URL(requestUrl);
	       HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
	      conn.setSSLSocketFactory(ssf);
	      conn.setDoOutput(true);
	      conn.setDoInput(true);
	      conn.setUseCaches(false);

	     conn.setRequestMethod(requestMethod);
	 
	      if (outputStr != null) {
	        OutputStream outputStream = conn.getOutputStream();
	        outputStream.write(outputStr.getBytes("UTF-8"));
	         outputStream.close();
	       }

	       InputStream inputStream = conn.getInputStream();
	       InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
	      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	       String str = null;
	       buffer=new StringBuffer();
	      while ((str = bufferedReader.readLine()) != null) {
	         buffer.append(str);
	       }

	       bufferedReader.close();
	       inputStreamReader.close();
	      inputStream.close();
	      inputStream = null;
	       conn.disconnect();
	     }
	     catch (ConnectException ce) {
	       log.error("连接服务器错误:" + ce.getMessage());
	     } catch (IOException ioe) {
	       log.error("创建ssl失败:" + ioe.getMessage());
	     }catch (NoSuchAlgorithmException e1) {
	 		log.error("SSLContext.getInstance失败"+e1.getMessage());
	 	} catch (NoSuchProviderException e2) {
	 		log.error("SSLContext.getInstance失败"+e2.getMessage());
	 	}
	   catch (KeyManagementException e3) {
			log.error("sslContext.init失败"+e3.getMessage());
		}
	     return buffer.toString();
	   }
 
//   public static String httpsAuthor(String password, String pekPath, String xml, String url)
//     throws Exception
//   {
//     StringBuilder sb = new StringBuilder();
//     KeyStore keyStore = KeyStore.getInstance("PKCS12");
//			URL realUrl = new URL(pekPath);
//
//       URLConnection connection = realUrl.openConnection();
//       connection.setRequestProperty("accept", "*/*");
//       connection.setRequestProperty("connection", "Keep-Alive");
//       connection.setRequestProperty("user-agent",
//         "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//       connection.connect();
//
//       InputStream instream =
//        connection.getInputStream();
//     try {
//       keyStore.load(instream, password.toCharArray());
//     } finally {
//       instream.close();
//     }
//
//     SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, password.toCharArray()).build();
//
//     SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
//
//     CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
//     try {
//       HttpPost httpPost = new HttpPost(url);
//       StringEntity strentity = new StringEntity(xml, "UTF-8");
//       httpPost.addHeader("Content-Type", "text/xml;charset=UTF-8");
//       httpPost.setEntity(strentity);
//       CloseableHttpResponse response = httpclient.execute(httpPost);
//       try {
//        HttpEntity entity = response.getEntity();
//         if (entity != null) {
//           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
//           String text;
//           while ((text = bufferedReader.readLine()) != null) {
//              text = new String(text.getBytes(), "UTF-8");
//             sb.append(text);
//           }
//         }
//
//         EntityUtils.consume(entity);
//       } finally {
//        response.close();
//       }
//     } finally {
//       httpclient.close();
//     }
//
//     return sb.toString();
//   }
 
 }