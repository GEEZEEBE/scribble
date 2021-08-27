package com.scribble.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.scribble.dao.SboardDao;
import com.scribble.dao.SboardDaoImpl;
import com.scribble.etc.PageVo;
import com.scribble.vo.SboardSuserVo;
import com.scribble.vo.SboardVo;
import com.sun.tools.javac.util.List;

public class BoardDaoTest {

	@Test
	public void testGetList() {
		SboardDao dao = new SboardDaoImpl();
		
		PageVo pvo = new PageVo();
		pvo.setPage(1);
		pvo.setCountPerPage(10);
		pvo.setKeyword("");
		
		List<SboardVo> list = null;
		assertEquals(list, dao.getList(pvo));
	}

	@Test
	public void testGet() {
		SboardDao dao = new SboardDaoImpl();
		assertEquals(1, dao.get(1));
	}

	@Test
	public void testInsert() {
		SboardVo vo = new SboardVo();
		vo.setBoard_id(1000);
		vo.setContent("JUnit Test 테스트");
		vo.setHit(0);
		vo.setImg_name(null);
		vo.setIsdeleted(null);
		vo.setReg_date("2021-08-25");
		vo.setTitle("Unit Test 테스트");
		vo.setUser_id(1);
		
		SboardDao dao = new SboardDaoImpl();
		assertEquals(1, dao.insert(vo));
	}

	@Test
	public void testUpdate() {
		SboardVo vo = new SboardVo();
		vo.setBoard_id(1111);
		vo.setContent("JUnit Test update 테스트");
		vo.setHit(0);
		vo.setImg_name(null);
		vo.setIsdeleted(null);
		vo.setReg_date("2021-08-25");
		vo.setTitle("Unit Test 테스트");
		vo.setUser_id(1);
		
		SboardDao dao = new SboardDaoImpl();
		assertEquals(1, dao.insert(vo));
	}

	@Test
	public void testDelete() {
		SboardDao dao = new SboardDaoImpl();

		assertEquals(1, dao.delete(1));
	}

	@Test
	public void testGetTotalCount() {
		SboardDao dao = new SboardDaoImpl();

		assertNotEquals(0, dao.getTotalCount("홍길동"));
	}


	@Test
	public void testGetHitTop4() {
		SboardDao dao = new SboardDaoImpl();
		List<SboardVo> list = null;
		
		assertEquals(list, dao.getHitTop4());
	}

	@Test
	public void testGetMyList() {
		SboardDao dao = new SboardDaoImpl();
		List<SboardSuserVo> list = null;
		
		assertEquals(list, dao.getMyList(1));
	}

}
