package cn.kdays.xs.common;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import cn.kdays.xs.bean.User;

public class Login extends AbstractFactory{

	private static String ACTION = "get_access_token";
	private static String SIT = "md5";
	
	private String username;
	private String password;
	private User user;
	public Login(String username, String password) throws Exception {
		this.username = Encryption.Base64(username);
		this.password = Encryption.MD5(password);
		this.getToken();
	}
	private User getToken() throws JSONException{
		List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("apikey", PUBLIC_KEY));
		params.add(new BasicNameValuePair("action", ACTION));
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("sit", SIT));
		Date time = new Date();
		String curr_time = String.valueOf(time.getTime());
		params.add(new BasicNameValuePair("sig_time", curr_time));
		String sig = username + ACTION + curr_time + PRIVATE_KEY;
		params.add(new BasicNameValuePair("sig", Encryption.MD5(sig)));
		String param = URLEncodedUtils.format(params, "UTF-8");
		String baseUrl = KDAYS_API_URL;
		HttpGet getMethod = new HttpGet(baseUrl + "?" + param);
		String result = getResponce(getMethod);
		return JSONProcessor.ConvertToUser(result);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
