package com.scribble.vo;

/*
CREATE TABLE scomment
(
  comment_id NUMBER         NOT NULL,
  content    VARCHAR2(2500) NOT NULL,
  board_id   NUMBER         NOT NULL,
  user_id    NUMBER         NOT NULL,
  reg_date   DATE	        NOT NULL,
  isdeleted  VARCHAR2(10)  ,
  CONSTRAINT PK_scomment PRIMARY KEY (comment_id)
);
*/

public class SCommentVo {
	
	private int commentId;
	private String content;
	private int boardId;
	private int userId;
	private String regDate;
	private String isdeleted;
	
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	@Override
	public String toString() {
		return "SCommentVo [commentId=" + commentId + ", content=" + content + ", boardId=" + boardId + ", userId="
				+ userId + ", regDate=" + regDate + ", isdeleted=" + isdeleted + "]";
	}
	
}
