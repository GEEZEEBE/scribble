package com.scribble.dao;

import java.util.List;

import com.scribble.etc.PageVo;
import com.scribble.vo.SCommentUserVo;
import com.scribble.vo.SCommentVo;

public interface SCommentDao {
	
	public List<SCommentUserVo> getList(PageVo vo);
	public SCommentUserVo get(int no);
	public int insert(SCommentVo vo);
	public int update(SCommentVo vo);
	public int delete(int no);

	public int getTotalCount(int board_id);
	
}
