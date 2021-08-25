package com.scribble.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scribble.dao.SboardDao;
import com.scribble.dao.SboardDaoImpl;
import com.scribble.etc.PageCreator;
import com.scribble.etc.PageVo;
import com.scribble.util.WebUtil;
import com.scribble.vo.SboardSuserVo;
import com.scribble.vo.SuserVo;

@WebServlet("/main")
public class SboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//request.setCharacterEncoding("UTF-8"); //(20)위에 있으니까
		
		String actionName = request.getParameter("a");
		System.out.println("board:" + actionName);
		
		////////////////////////////////////////////////////////////////
		/////list///////////////////////////////////////////////////////
		
		
		if ("list".equals(actionName)) {
			String keyword = "";
			if (request.getParameter("keyword") != null) {
				keyword = request.getParameter("keyword");
			}
			
			int page = 1;
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}

			PageVo pvo = new PageVo();
			pvo.setKeyword(keyword);
			pvo.setPage(page);
			PageCreator pc = new PageCreator(pvo);
			
			BoardDao dao = new BoardDaoImpl();
			pc.setTotalCount(dao.getTotalCount(keyword));
			pc.calcForPaging();
			System.out.println(pc);
			
			List<BoardUserVo> list = dao.getList(pvo);

//			System.out.println(list);

			request.setAttribute("list", list);
			request.setAttribute("pc", pc);
			request.setAttribute("keyword", keyword);
			WebUtil.forward(request, response, "/WEB-INF/views/mian/index.jsp");
			
	}	
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/main/index.jsp");
//		rd.forward(request, response); 기존에 있던거
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}


