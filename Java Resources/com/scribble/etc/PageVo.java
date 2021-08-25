package com.scribble.etc;

public class PageVo {
	
	private int page;			//사용자가 요청한 페이지 번호
	private int countPerPage;	//한 페이지당 들어갈 게시물의 수
	private String keyword;
	
	public PageVo() {
		this.page = 1;
		this.countPerPage = 10;
	}
	
	//클라이언트가 전달한 페이지번호를 데이터베이스의 ROWNUM에 맞는 숫자로 변환.
	public int getPageStart() {
		return (this.page - 1) * this.countPerPage;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
			return;
		}		
		this.page = page;
	}
	
	public int getCountPerPage() {
		return countPerPage;
	}
	
	public void setCountPerPage(int countPerPage) {
		if(countPerPage <= 0 || countPerPage > 50) {
			this.countPerPage = 10;
			return;
		}
		this.countPerPage = countPerPage;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "PageVO [page=" + page + ", countPerPage=" + countPerPage + "]";
	}
	
	

}



