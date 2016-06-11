package com.gsh.cs.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


public class httpClient {
	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param params
	 *            请求的查询参数,可以为null
	 * @param charset
	 *            字符集
	 * @param pretty
	 *            是否美化
	 * @return 返回请求响应的HTML
	 */
	public static String getDoPostResponseDataByURL(String url,
			Map<String, String> params, String charset, boolean pretty) {
		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();
		PostMethod method = new UTF8PostMethod(url);

		// 设置Http Post数据
		if (params != null) {
			NameValuePair[] param = new NameValuePair[params.size()];
			int i = 0;
			for (Map.Entry<String, String> entry : params.entrySet()) {
				param[i] = new NameValuePair(entry.getKey(), entry.getValue());
				i++;
			}
			method.setRequestBody(param);
			method.releaseConnection();
			method.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		}
		try {
			// 执行method
			int statusCode = client.executeMethod(method);
			System.out.println(statusCode);
			String c = method.getResponseCharSet();
			System.out.println(c);
			// HttpClient请求返回值200后，验证返回结果并打印结果内容
			if (statusCode == HttpStatus.SC_OK) {
				// 读取为 InputStream，在网页内容数据量大时候推荐使用
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(method.getResponseBodyAsStream(),
								c));
				String line;
				while ((line = reader.readLine()) != null) {
					if (pretty)
						response.append(line).append(
								System.getProperty("line.separator"));
					else
						response.append(line);
				}
				reader.close();
			}
			// HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发
			// 301或者302
			if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY
					|| statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
				// 从头中取出转向的地址
				Header locationHeader = method.getResponseHeader("location");
				String location = null;
				if (locationHeader != null) {
					location = locationHeader.getValue();
					System.out
							.println("The page was redirected to:" + location);
				} else {
					System.err.println("Location field value is null.");
				}
			}
		} catch (IOException e) {
			System.out.println("执行HTTP Post请求" + url + "时，发生异常！");
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		System.out.println(response.toString());
		return response.toString();
	}
	
	//重载方法UTF-8
		public static class UTF8PostMethod extends PostMethod {
			public UTF8PostMethod(String url) {
				super(url);
			}

			@Override
			public String getRequestCharSet() {
				// return super.getRequestCharSet();
				return "utf-8";
			}
		}
}