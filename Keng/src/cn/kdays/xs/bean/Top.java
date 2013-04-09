package cn.kdays.xs.bean;

import org.json.JSONArray;

public class Top {

	private int code;
	private String msg;
	private String type;
	private JSONArray result;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public JSONArray getResult() {
		return result;
	}
	public void setResult(JSONArray result) {
		this.result = result;
	}
	
}
