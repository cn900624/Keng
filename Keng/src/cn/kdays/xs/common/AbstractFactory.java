package cn.kdays.xs.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

public abstract class AbstractFactory {

	protected String KDAYS_API_URL = "http://kdays.cn/api";

	protected String NOVEL_API_URL = "http://xs.kdays.cn/api.php";

	protected String PUBLIC_KEY = "218b358e3dc1228";

	protected String PRIVATE_KEY = "404136b97514";
	
	
	protected String getResponce(HttpGet getMethod) {
		String html = "";
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpHost proxy = new HttpHost("web-proxy.sgp.hp.com", 8080);
		httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
				proxy);
		try {
			HttpResponse response = httpclient.execute(getMethod);
			InputStream in = response.getEntity().getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(in,
					"UTF-8"));
			String tempbf;
			StringBuffer te = new StringBuffer(100);
			while ((tempbf = br.readLine()) != null) {
				te.append(tempbf + "\n");
			}
			html = te.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return html;
	}
}
