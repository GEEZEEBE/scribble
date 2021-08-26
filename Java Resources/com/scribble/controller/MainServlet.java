package com.scribble.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.scribble.dao.SCommentDao;
import com.scribble.dao.SCommentDaoImpl;
import com.scribble.dao.SboardDao;
import com.scribble.dao.SboardDaoImpl;
import com.scribble.etc.PageCreator;
import com.scribble.etc.PageVo;
import com.scribble.util.WebUtil;
import com.scribble.vo.SboardSuserVo;
import com.scribble.vo.SboardVo;
import com.scribble.vo.SuserVo;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
			
		String actionName = request.getParameter("a");
		System.out.println("main:" + actionName);

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
			
			SboardDao dao = new SboardDaoImpl();
			pc.setTotalCount(dao.getTotalCount(keyword));
			pc.calcForPaging();
			System.out.println(pc);
			
			List<SboardSuserVo> list = dao.getList(pvo);
			List<SboardSuserVo> list4 = dao.getHitTop4();
			
			HttpSession session = request.getSession();
			SuserVo authUser = (SuserVo)session.getAttribute("authUser");

			List<SboardSuserVo> listMy = null;
			if (authUser != null) {
				int userNo = authUser.getUser_id();
				listMy = dao.getMyList(page);
			}

//			System.out.println(list);

			request.setAttribute("list", list);		// 뿌려줄 DB 묶음
			request.setAttribute("list4", list4);
			request.setAttribute("listMy", listMy);
			request.setAttribute("pc", pc);			// 페이지 표시	
			request.setAttribute("keyword", keyword); //검색창에 키워드 유지하려고?
			WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp");
			
		} else if ("view".equals(actionName)) {
			int page = 1;
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			//현재 보고 있는 페이지를 들고 가자
			
			String keyword = request.getParameter("keyword"); 
			//keyword 없으면 null, 있으면 해당 value 들고 가자...
			
			int board_id = Integer.parseInt(request.getParameter("board_id"));
			
			//board_id로 DB를 vo로 list에 담아서 가져가자 
			
			SboardDao dao = new SboardDaoImpl();
			
			dao.upHitCount(board_id); //조회수 계산
			
			SboardVo vo = dao.get(board_id); //get 메서드 실행해서 vo에 담고,

			System.out.println(vo);

			// 게시물 화면에 보내기
			request.setAttribute("vo", vo);
			request.setAttribute("page", page);
			request.setAttribute("keyword", keyword);
			WebUtil.forward(request, response, "/WEB-INF/views/main/single.jsp");
			// view.jsp에 뿌리기
			
		} else if ("modifyform".equals(actionName)) {
			try {	// 주소 직접 접근 방지
				HttpSession session = request.getSession();
				SuserVo authUser = (SuserVo)session.getAttribute("authUser");
				int userNo = authUser.getUser_id();  
			
				String keyword = request.getParameter("keyword");
				int board_id = Integer.parseInt(request.getParameter("board_id"));
				SboardDao dao = new SboardDaoImpl(); 
				SboardVo vo = dao.get(board_id);
				
				if (userNo != vo.getUser_id()) {
					System.out.println("Unauthorized Access");
					WebUtil.redirect(request, response, "/scribble/main?a=list");
				} else {
					int page = Integer.parseInt(request.getParameter("page"));
					
					request.setAttribute("vo", vo);
					request.setAttribute("page", page);
					request.setAttribute("keyword", keyword);
					WebUtil.forward(request, response, "/WEB-INF/views/board/modifyform.jsp");
				}
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Unauthorized Access");
				WebUtil.redirect(request, response, "/scribble/main?a=list");
			}

		} else if ("modify".equals(actionName)) {
			try {	// 주소 직접 접근 방지
				HttpSession session = request.getSession();
				SuserVo authUser = (SuserVo)session.getAttribute("authUser");
				int userNo = authUser.getUser_id();
				
				int board_id = Integer.parseInt(request.getParameter("board_id"));
				SboardDao dao = new SboardDaoImpl();
				SboardSuserVo vo = dao.get(board_id);
	
				if ( userNo != vo.getUser_id()) {
					System.out.println("Unauthorized Access");
					WebUtil.redirect(request, response, "/scribble/main?a=list");
				} else {
					String title = request.getParameter("title");
					String content = request.getParameter("content");					
					int page = Integer.parseInt(request.getParameter("page"));
					String keyword = request.getParameter("keyword");
					
					vo.setTitle(title);
					vo.setContent(content);
										
					dao.update(vo);
					
					vo = dao.get(board_id);
					request.setAttribute("vo", vo);
					request.setAttribute("page", page);
					request.setAttribute("keyword", keyword);
					WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");
				}
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Unauthorized Access");
				WebUtil.redirect(request, response, "/scribble/main?a=list");
			}
			
			//////////////////////////08/25 11:30 /////////////////////////
			
		} else if ("writeform".equals(actionName)) {
			try {	// 주소 직접 접근 방지
//				HttpSession session = request.getSession();
//				SuserVo authUser = (SuserVo)session.getAttribute("authUser");
//				authUser.getUser_id();				
				
				WebUtil.forward(request, response, "/writeform.jsp");
				
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Unauthorized Access");
				WebUtil.redirect(request, response, "/scribble/main?a=list");
			}

		} else if ("write".equals(actionName)) {
			try {	// 주소 직접 접근 방지
//				HttpSession session = request.getSession();
//				SuserVo authUser = (SuserVo)session.getAttribute("authUser");
//				int userNo = authUser.getUser_id();
			
				
				
				int user_id = 1; // "1" ,로그인기능 병합시 삭제
				String img_name = "beauty"; // "beauty" ,로그인기능 병합시 삭제				
				String title = request.getParameter("title");
				String content = request.getParameter("content");
//			
//				File file = new File(SAVEFOLDER);
//				if (!file.exists())
//					file.mkdirs();
//				
//				MultipartRequest multi = new MultipartRequest(request, SAVEFOLDER,MAXSIZE, ENCTYPE,
//										 new DefaultFileRenamePolicy());
//				
//				String title = multi.getParameter("title");
//				String content = multi.getParameter("content");
//
//			
//				if (multi.getFilesystemName("img_name") != null) {
//					img_name = multi.getFilesystemName("img_name");
//
//				}
//			
//				
//				//참고용"board_id,  title,content, hit, reg_date,img_name,isdeleted, user_id"
				SboardVo vo = new SboardVo();

				
				vo.setUser_id(user_id); // 로그인 기능 병합시 변경			
				vo.setImg_name(img_name); // 로그인 기능 병합시 변경				
				vo.setTitle(title);
				vo.setContent(content);
												
				System.out.println(vo);

				SboardDao dao = new SboardDaoImpl();
				dao.insert(vo);
	
				WebUtil.redirect(request, response, "/scribble/main?a=list");
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Unauthorized Access");
				WebUtil.redirect(request, response, "/scribble/main?a=list");
			}

		} else if ("delete".equals(actionName)) {
			try {	// 주소 직접 접근 방지
				HttpSession session = request.getSession();
				SuserVo authUser = (SuserVo)session.getAttribute("authUser");
				int userNo = authUser.getUser_id();
			
				int board_id = Integer.parseInt(request.getParameter("board_id"));
				int page = Integer.parseInt(request.getParameter("page"));
				String keyword = request.getParameter("keyword");
				
				SboardDao dao = new SboardDaoImpl();
				SboardSuserVo vo = dao.get(board_id);
	
				if (userNo != vo.getUser_id()) {
					System.out.println("Unauthorized Access");
					WebUtil.redirect(request, response, "/scribble/main?a=list");
				} else {
					dao.delete(board_id);
					
					String encKeyword = URLEncoder.encode(keyword, "utf-8"); //웹에서 인식하는 것으로 변환?
					WebUtil.redirect(request, response, "/scribble/main?a=list&no=&page=" + page + "&keyword=" + encKeyword);
				}
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Unauthorized Access");
				WebUtil.redirect(request, response, "/scribble/main?a=list");
			}
			
//		} else if ("download".equals(actionName)) {
//			WebUtil.forward(request, response, "/WEB-INF/views/board/download.jsp");
			
		} else {
			WebUtil.redirect(request, response, "/scribble/main?a=list");
		}
	}	
		
		
		
		
		
		
		
		
		
	////////////////////////////////////////////////////////////////////////////////////	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
