package com.scribble.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

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
		
		// Insert users' info
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
			
		// Update users' info 	
		} else if("modify".equals(actionName)){
			try {	// 주소 직접 접근 방지
				HttpSession session = request.getSession();
				SuserVo authUser = (SuserVo)session.getAttribute("authUser");
				int authuser = authUser.getUser_id();
	
				int user_id = Integer.parseInt(request.getParameter("user_id"));
				SuserDao dao = new SuserDaoImpl();
				SuserVo vo = dao.get(user_id);
				
					if (authuser != vo.getUser_id()) {
						System.out.println("Unauthorized Access to modify1");
						response.sendRedirect("/scribble/main");
					}else {
						String name = request.getParameter("name");
						String password = request.getParameter("password");
						
						vo.setName(name);
						vo.setPassword(password);
						
						dao.update(vo);

						response.sendRedirect("/scribble/main");	
					}
			}catch (Exception e) {
					System.out.println(e);
					System.out.println("Unauthorized Access to modify2");
					HttpSession session = request.getSession();
					session.removeAttribute("authUser");
					session.invalidate();
					WebUtil.redirect(request, response, "/scribble/user?a=loginform");
			}
			
		// Update form
		} else if("modifyform".equals(actionName)) {
			try {
				HttpSession session = request.getSession();
				SuserVo authUser = (SuserVo)session.getAttribute("authUser");
				int user_id = authUser.getUser_id();
				
				SuserDao dao = new SuserDaoImpl();
				SuserVo SuserVo = dao.getUser(user_id);
				System.out.println(SuserVo.toString());
				
				request.setAttribute("SuserVo", SuserVo);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/modifyform.jsp");
				rd.forward(request, response);;
			}catch (Exception e) {
				System.out.println(e);
				System.out.println("Unauthorized Access to modifyform");
				WebUtil.redirect(request, response, "/scribble/user?a=loginform");
			}	
			
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
				System.out.println("Fail to login");
				response.sendRedirect("/scribble/user?a=loginform&result=fail");
			}else {
				System.out.println("Succeed to login");
				
				HttpSession session = request.getSession(true);
				session.setAttribute("authUser", vo);
				
				response.sendRedirect("/scribble/main");
				//return;
			}
			
		// Logout	
		} else if("logout".equals(actionName)){
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();
			response.sendRedirect("/scribble/main");
			
		// Delete User	
		} else if("delete".equals(actionName)){
			try {	// 주소 직접 접근 방지
				HttpSession session = request.getSession();
				SuserVo authUser = (SuserVo)session.getAttribute("authUser");
				int authuser = authUser.getUser_id();
	
				int user_id = Integer.parseInt(request.getParameter("user_id"));
				SuserDao dao = new SuserDaoImpl();
				SuserVo vo = dao.getUser(user_id);
				
					if (authuser != vo.getUser_id()) {
						System.out.println("Unauthorized Access to delete1");
						response.sendRedirect("/scribble/user?a=loginform");
					}else {
						System.out.println("Delete Success");
						
						dao.delete(user_id);
						
						session.removeAttribute("authUser");
						session.invalidate();
						response.sendRedirect("/scribble/main");
					}
			}catch (Exception e) {
					System.out.println(e);
					System.out.println("Unauthorized Access to delete2");
					WebUtil.redirect(request, response, "/scribble/user?a=loginform");
			}
			
		// Email check	
		} else if("checkid".equals(actionName)) {
			String email = request.getParameter("email");
		
			SuserDao dao = new SuserDaoImpl();
			JSONObject data = dao.checkId(email);
			System.out.println(data);
			
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(data.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
