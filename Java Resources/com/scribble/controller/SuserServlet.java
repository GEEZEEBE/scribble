package com.scribble.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scribble.vo.SuserVo;
import com.scribble.dao.SuserDao;
import com.scribble.dao.SuserDaoImpl;
import com.scribble.util.WebUtil;

@WebServlet("/user")
public class SuserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	
		String actionName =request.getParameter("a");
		System.out.println("user:" + actionName);
		
		//Insert users' info
		if("joinform".equals(actionName)) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/joinform.jsp");
			rd.forward(request, response);
			
		} else if("join".equals(actionName)) {
			String email = request.getParameter("email");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			
			SuserVo vo = new SuserVo(name, email, password);
			SuserDao dao = new SuserDaoImpl();
			
			dao.insert(vo);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/joinsuccess.jsp");
			rd.forward(request, response);
			
		//Update users' info 	
		} else if("modify".equals(actionName)){
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			
			SuserVo vo = new SuserVo();
			vo.setName(name);
			vo.setPassword(password);
			
			HttpSession session = request.getSession();
			SuserVo authUser = (SuserVo)session.getAttribute("authUser");
			
			int user_id = authUser.getUser_id();
			vo.setUser_id(user_id);
			
			SuserDao dao = new SuserDaoImpl();
			dao.update(vo);
			
			authUser.setName(name);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/main/index.jsp");
			rd.forward(request, response);
		//Update form
		} else if("modifyform".equals(actionName)) {
			HttpSession session = request.getSession();
			SuserVo authUser = (SuserVo)session.getAttribute("authUser");
			int no = authUser.getUser_id();
			
			SuserDao dao = new SuserDaoImpl();
			SuserVo suserVo = dao.getUser(no);
			System.out.println(suserVo.toString());
			
			request.setAttribute("suserVo", suserVo);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/modifyform.jsp");
			rd.forward(request, response);
		// Login	
		} else if("loginform".equals(actionName)){
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp");
			rd.forward(request, response);
			
		} else if("login".equals(actionName)){
			String email = request.getParameter("email");
			String password =request.getParameter("password");
			
			SuserDao dao = new SuserDaoImpl();
			SuserVo vo = dao.getUser(email, password);
			
			if(vo==null) {
				System.out.println("Login fail");
				response.sendRedirect("/scribble/user?a=loginform&result=fail");
			} else {
				System.out.println("Login success");
				HttpSession session = request.getSession(true);
				session.setAttribute("authUser", vo);
				
				response.sendRedirect("/scribble/main");
				return;
			}
		// Logout	
		} else if("logout".equals(actionName)){
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();
			response.sendRedirect("/scribble/main");
			
		} else {
			WebUtil.redirect(request, response, "/scribble/main");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
