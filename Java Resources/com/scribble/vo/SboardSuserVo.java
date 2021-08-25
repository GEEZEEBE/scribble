package com.scribble.vo;

public class SboardSuserVo extends SboardVo {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SboardSuserVo [name=" + name + ", getBoard_id()=" + getBoard_id() + ", getTitle()=" + getTitle()
				+ ", getContent()=" + getContent() + ", getHit()=" + getHit() + ", getReg_date()=" + getReg_date()
				+ ", getImg_name()=" + getImg_name() + ", getIsdeleted()=" + getIsdeleted() + ", getUser_id()="
				+ getUser_id() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}
	
	
}
