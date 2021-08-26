package com.scribble.vo;

public class SuserVo {

	private int user_id;
	private String email;
	private String name;
	private String password;
	private String isdeleted;

	public SuserVo() {
		super();
	}

	public SuserVo(String isdeleted) {
		super();
		this.isdeleted = isdeleted;
	}

	public SuserVo(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public SuserVo(String email, String name, String password) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
	}
	
	public SuserVo(int user_id, String email, String name, String password, String isdeleted) {
		super();
		this.user_id = user_id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.isdeleted = isdeleted;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}

	@Override
	public String toString() {
		return "SuserVo [Suser_id=" + user_id + ", Email=" + email + ", Name=" + name + ", Password=" + password + ", isdeleted="
				+ isdeleted + "]";
	}

}
