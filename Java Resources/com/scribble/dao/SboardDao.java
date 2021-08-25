package com.scribble.dao;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import com.scribble.etc.PageVo;
import com.scribble.vo.SboardSuserVo;
import com.scribble.vo.SboardVo;

public interface SboardDao {
	
	public List<SboardSuserVo> getList(PageVo vo);
	public SboardSuserVo get(int no);
	public int insert(SboardVo vo);
	public int update(SboardVo vo);
	public int delete(int no);

	public int getTotalCount(String keyword);
	public void upHitCount(int no);

	
	
//	public int reply(BoardVo vo);
//	public int upReplies(int ref, int pos);
//	public void downLoad(HttpServletRequest req, HttpServletResponse res, JspWriter out, PageContext pageContext);
	
}	