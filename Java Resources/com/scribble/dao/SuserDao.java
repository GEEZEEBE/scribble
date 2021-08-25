package com.scribble.dao;

import com.scribble.vo.SuserVo;

public interface SuserDao {

	public int insert(SuserVo vo);

	public int update(SuserVo vo);
	
	public int delete(SuserVo vo);

	public SuserVo getUser(String email, String password);
	
	public SuserVo getUser(int user_id);
	
}
