package com.scribble.vo;

public class SboardVo {
	
	private int board_id;
	private String title;
	private String content;
	private int hit;
	private String reg_date;
	private String img_name;
	private String isdeleted;
	private int user_id;
	
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getImg_name() {
		return img_name;
	}
	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
	public String getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public SboardVo() {
		super();
	}
	public SboardVo(int board_id, String title, String content, int hit, String reg_date, String img_name,
			String isdeleted, int user_id) {
		super();
		this.board_id = board_id;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.reg_date = reg_date;
		this.img_name = img_name;
		this.isdeleted = isdeleted;
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "SboardVo [board_id=" + board_id + ", title=" + title + ", content=" + content + ", hit=" + hit
				+ ", reg_date=" + reg_date + ", img_name=" + img_name + ", isdeleted=" + isdeleted + ", user_id="
				+ user_id + "]";
	}

		
	
}
