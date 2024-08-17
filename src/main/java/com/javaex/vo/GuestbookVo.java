package com.javaex.vo;

public class GuestbookVo {
	
	//필드
	private int scriptNo;
	private String name;
	private String password;
	private String date;
	private String script;
	
	//생성자
	public GuestbookVo() {
	}
	
	public GuestbookVo(int scriptNo) {
		this.scriptNo = scriptNo;
	}
	
	public GuestbookVo(int scriptNo, String password) {
		this.scriptNo = scriptNo;
		this.password = password;
	}
	
	public GuestbookVo(int scriptNo, String name, String script) {
		this.scriptNo = scriptNo;
		this.name = name;
		this.script = script;
	}
	
	public GuestbookVo(int scriptNo, String name, String date, String script) {
		this.scriptNo = scriptNo;
		this.name = name;
		this.date = date;
		this.script = script;
	}
	
	public GuestbookVo(int scriptNo, String name, String password, String date, String script) {
		this.scriptNo = scriptNo;
		this.name = name;
		this.password = password;
		this.date = date;
		this.script = script;
	}
	
	//메소드 -gs
	public int getScriptNo() {
		return scriptNo;
	}
	public void setScriptNo(int scriptNo) {
		this.scriptNo = scriptNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	
	//메소드 일반
	@Override
	public String toString() {
		return "GuestbookVo [scriptNo=" + scriptNo + ", name=" + name + ", password=" + password + ", date=" + date
				+ ", script=" + script + "]";
	}
	

}
