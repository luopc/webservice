package cn.gov.web.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpClientTool {

	public static final String SYS_USER_NAME = "admin";
	public static final String SYS_PASS_WORD = "ces~!@admin";

	/**
	 * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
	 */
	public static String post(String url, List<NameValuePair> params) {
		// 创建默认的httpClient实例.
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpClient httpclient = httpClientBuilder.build();
		// HttpHost target = new HttpHost("172.16.66.62", 80, "http");
		// HttpHost proxy = new HttpHost("localhost", 8888, "http");
		// RequestConfig config =
		// RequestConfig.custom().setProxy(proxy).build();
		// httpclient..getHostConfiguration().setProxy("127.0.0.1", 8888);
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		// httppost.setConfig(config);
		// 创建参数队列
		// List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		// formparams.add(new BasicNameValuePair("type", "house"));
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(params, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null)
					return EntityUtils.toString(entity, "UTF-8");
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 发送 get请求
	 */
	public static String get(String url) {
		return getCyyy(url, 1);
	}

	public static String getCyyy(String url, int i) {
		HttpClient httpclient = null;
		try {
			httpclient = new DefaultHttpClient();
			// 创建httpget.
			HttpGet httpget = new HttpGet(url);
			// 执行get请求.
			HttpResponse response = httpclient.execute(httpget);
			int statusCode = response.getStatusLine().getStatusCode();
			while (statusCode != 200 && i < 50) {
				System.out.println("第" + i + "次请求：" + response.getStatusLine());
				return getCyyy(url, ++ i);
			}
			// 获取响应实体
			HttpEntity entity = response.getEntity();
			// 打印响应状态
			if (entity != null)
				return EntityUtils.toString(entity, "UTF-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			if (httpclient != null)
				httpclient.getConnectionManager().shutdown();
		}
		return null;
	}
}

