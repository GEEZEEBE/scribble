package com.scribble.dao;

import java.util.List;

import org.json.JSONObject;

import com.scribble.vo.SuserVo;

public interface SuserDao {

	public List<SuserVo> getList();
	
	public int insert(SuserVo vo);

	public int update(SuserVo vo);
	
	public int delete(int user_id);

	public SuserVo getUser(String email, String password);
	
	public SuserVo getUser(int user_id);
	
	public SuserVo get(int no);
	
	public JSONObject checkId(String email);
	
}
