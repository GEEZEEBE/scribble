package com.scribble.etc;

public class PageCreator {

	private PageVo paging;			//페이지번호와 한 페이지당 들어갈 게시물 수를 갖고 있는 객체
	private int totalCount;			// 게시판의 총 게시물 수
	private int beginPage; 			// 시작 페이지 번호
	private int endPage; 			// 끝 페이지 번호
	private boolean prev; 			// 이전 버튼 활성화 여부
	private boolean next; 			// 다음 버튼 활성화 여부
	
	// 한 화면에 보여질 페이지 수
	private final int displayPageNum = 3;
		
	public PageCreator(PageVo paging) {
		super();
		this.paging = paging;
	}

	// 페이징 알고리즘을 수행할 메서드 선언.
	public void calcForPaging() {
		
		// 보정 전 끝 페이지 구하기
		endPage = (int)Math.ceil(paging.getPage() / (double)displayPageNum) * displayPageNum;
		
		// 시작 페이지 번호 구하기
		beginPage = (endPage - displayPageNum) + 1;
		
		// 현재 시작페이지가 1이라면 이전버튼 활성화 여부를 false로 지정
		prev = (beginPage == 1) ? false : true;
		
		// 마지막 페이지인지 여부 확인 후 다음 버튼 비활성.
		next = (totalCount <= (endPage * paging.getCountPerPage())) ? false : true;
		
		// 마지막 블럭 페이지 수 계산(페이지 수 보정)
		if(!isNext()) {
			// 끝 페이지 재보정하기
			endPage = (int)Math.ceil(totalCount / (double)paging.getCountPerPage());
		}
	}

	public PageVo getPaging() {
		return paging;
	}

	public void setPaging(PageVo paging) {
		this.paging = paging;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	@Override
	public String toString() {
		return "PageCreator [paging=" + paging + ", totalCount=" + totalCount + ", beginPage=" + beginPage
				+ ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + ", displayPageNum=" + displayPageNum
				+ "]";
	}

}








