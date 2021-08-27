package com.scribble.vo;

public class SCommentUserVo extends SCommentVo {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SCommentUserVo [name=" + name + ", getCommentId()=" + getCommentId() + ", getContent()=" + getContent()
				+ ", getBoardId()=" + getBoardId() + ", getUserId()=" + getUserId() + ", getRegDate()=" + getRegDate()
				+ ", getIsdeleted()=" + getIsdeleted() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
