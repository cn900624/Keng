package cn.kdays.xs.common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.kdays.xs.bean.*;

public class JSONProcessor {



	public JSONProcessor(String str) {

	}

	public Top ConvertToTop() {
		Top top = new Top();
		try {
			int code = jsonObj.getInt("code");
			if (code != 200) {
				top.setCode(code);
				top.setMsg(jsonObj.getString("msg"));
				return top;
			}
			top.setCode(jsonObj.getInt("code"));
			top.setMsg(jsonObj.getString("msg"));
			top.setType(jsonObj.getString("type"));
			top.setResult(jsonObj.getJSONArray("result"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return top;
	}

	private Book[] ConvertToBook(Top obj) {
		Book[] book = null;
		try {
			String type = obj.getType();
			JSONArray tmp = obj.getResult();
			int length = tmp.length();
			book = new Book[length];
			for (int i = 0; i < length; i++) {
				JSONObject temp = tmp.getJSONObject(i);
				if (type.equalsIgnoreCase("hits")) {
					book[i].setHit(temp.getString("hits"));
				} else if (type.equalsIgnoreCase("fav")) {
					book[i].setFav(temp.getString("fav"));
				} else if (type.equalsIgnoreCase("addtime")) {
					book[i].setAddtime(temp.getString("addtime"));
				} else if (type.equalsIgnoreCase("update_book")) {
					book[i].setUpdate_book(temp.getString("update_book"));
				}
				book[i].setIntroduce(temp.getString("introduce"));
				book[i].setId(temp.getInt("id"));
				book[i].setName(temp.getString("name"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}
	
	public static User ConvertToUser(String str) throws JSONException {
		JSONObject obj = processJson(str);
		User user = new User();
		int code = obj.getInt("code");
		user.setCode(code);
		if(code != 200) {
			user.setMsg(obj.getString("msg"));
		} else {
			user.setUid(obj.getInt("uid"));
			user.setActived(obj.getInt("actived"));
			user.setToken(obj.getString("token"));
		}
		return user;
	}
	
	private static JSONObject processJson(String str) {
		JSONObject jsonObj;
		jsonObj = null;
		try {
			jsonObj = new JSONObject(str);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObj;
	}

}
