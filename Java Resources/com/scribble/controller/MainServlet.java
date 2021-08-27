package com.scribble.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
import com.scribble.vo.SCommentUserVo;
import com.scribble.vo.SCommentVo;
import com.scribble.vo.SboardSuserVo;
import com.scribble.vo.SboardVo;
import com.scribble.vo.SuserVo;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String  SAVEFOLDER = "C:/mini_Project/scribble/WebContent/filestorage/";
	private static final String ENCTYPE = "UTF-8";
	private static int MAXSIZE = 5*1024*1024;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
			
		String actionName = request.getParameter("a");
		System.out.println("main:" + actionName);

		if ("list".equals(actionName)) {
			String keyword = "";
			if (request.getParameter("keyword") != null) {
				keyword = request.getParameter("keyword");
				System.out.println("keyword : " + keyword);
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

			request.setAttribute("list", list);
			request.setAttribute("list4", list4);
			request.setAttribute("listMy", listMy);
			request.setAttribute("pc", pc);
			request.setAttribute("keyword", keyword);
			WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp");
			
		} else if ("view".equals(actionName)) {

			int page = 1;
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			String keyword = request.getParameter("keyword"); 
			
			int board_id = Integer.parseInt(request.getParameter("no"));
			
			SboardDao dao = new SboardDaoImpl();
			
			// 동일 페이지 조회수 증가 방지 - Cookie
			Boolean visitor = false;
			Cookie[] cookies = request.getCookies();			// 쿠키 불러오기			
			for (Cookie cookie : cookies) {						// 모든 쿠키 확인
				if (cookie.getName().equals("visited")) {		// 쿠키 중에 visited 이라는 이름이 있는지 확인
					visitor = true;								// 이전 방문 클라이언트
					if (cookie.getValue().contains(request.getParameter("no"))) {	// visited 안에 해당 게시물 번호가 있는지 확인
						// 쿠키에 visited가 있고 해당 게시물 번호가 있으면 이전에 방문한 클라이언트 (조회수 증가하지 않음)
					} else {									// visited는 있지만 해당 게시물 번호가 없으면 번호 추가해 주고 조회수 증가
						cookie.setValue(cookie.getValue() + "_" + request.getParameter("no"));
						response.addCookie(cookie);
						dao.upHitCount(board_id);
					}
				}
			}
			if (!visitor) {										// visited 쿠키가 없는 경우 쿠키 만들고 조회수 증가
				Cookie cookie1 = new Cookie("visited", request.getParameter("no"));
				response.addCookie(cookie1);
				System.out.println("visited Cookie 생성!");
				dao.upHitCount(board_id);
			}
			// 동일 페이지 조회수 증가 방지 - Cookie
						
			SboardVo vo = dao.get(board_id);
//			System.out.println(vo);

			PageVo pvo = new PageVo();
			pvo.setKeyword(Integer.toString(board_id));
			pvo.setPage(page);
			PageCreator pc = new PageCreator(pvo);
			
			SCommentDao cdao = new SCommentDaoImpl();
			pc.setTotalCount(board_id);
			pc.calcForPaging();
			
			List<SCommentUserVo> clist = cdao.getList(pvo);
//			System.out.println(clist);
			
			request.setAttribute("vo", vo);
			request.setAttribute("clist", clist);
			request.setAttribute("page", page);
			request.setAttribute("keyword", keyword);
			request.setAttribute("boardNo", board_id);		// 댓글 삭제 후 재진입을 위한 파라미터
			WebUtil.forward(request, response, "/WEB-INF/views/main/single.jsp");
			
		} else if ("modifyform".equals(actionName)) {
			try {	// 주소 직접 접근 방지
				
				HttpSession session = request.getSession();
				SuserVo authUser = (SuserVo)session.getAttribute("authUser");
				int userNo = authUser.getUser_id();  
			
				String keyword = request.getParameter("keyword");
				int board_id = Integer.parseInt(request.getParameter("no"));
				SboardDao dao = new SboardDaoImpl(); 
				SboardVo vo = dao.get(board_id);

				if (userNo != vo.getUser_id()) {
					System.out.println(vo);
					System.out.println("Unauthorized Access");
					WebUtil.redirect(request, response, "/scribble/main?a=list");
				} else {
					int page = Integer.parseInt(request.getParameter("page"));
					
					request.setAttribute("vo", vo);
					request.setAttribute("page", page);
					request.setAttribute("keyword", keyword);
					WebUtil.forward(request, response, "/WEB-INF/views/main/modifyform.jsp");
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
			
				String imgName = null;
				
				int board_id = Integer.parseInt(request.getParameter("no"));
				SboardDao dao = new SboardDaoImpl();
				SboardSuserVo vo = dao.get(board_id);
				
				if ( userNo != vo.getUser_id()) {
					System.out.println("Unauthorized Access");
					WebUtil.redirect(request, response, "/scribble/main?a=list");
				} else {
					File file = new File(SAVEFOLDER);
					if (!file.exists())
						file.mkdirs();
					
					MultipartRequest multi = new MultipartRequest(request, SAVEFOLDER,MAXSIZE, ENCTYPE,
											 new DefaultFileRenamePolicy());
					
					String title = multi.getParameter("title");
					String content = multi.getParameter("content");
					String page = multi.getParameter("page");
					String keyword = multi.getParameter("keyword");
				
					if (multi.getFilesystemName("img_name") != null) {
						imgName = multi.getFilesystemName("img_name");
						vo.setImg_name(imgName);
					}
					
					vo.setTitle(title);
					vo.setContent(content);
					
					System.out.println(vo);

					dao.update(vo);
					
					vo = dao.get(board_id);
					request.setAttribute("vo", vo);
					request.setAttribute("page", page);
					request.setAttribute("keyword", keyword);
					WebUtil.forward(request, response, "/WEB-INF/views/main/single.jsp");
				}
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Unauthorized Access");
				WebUtil.redirect(request, response, "/scribble/main?a=list");
			}
			
		} else if ("writeform".equals(actionName)) {
			try {	// 주소 직접 접근 방지
				HttpSession session = request.getSession();
				SuserVo authUser = (SuserVo)session.getAttribute("authUser");
				authUser.getUser_id();				
				
				WebUtil.forward(request, response, "/WEB-INF/views/main/writeform.jsp");
				
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Unauthorized Access");
				WebUtil.redirect(request, response, "/scribble/main?a=list");
			}

		} else if ("write".equals(actionName)) {
			try {	// 주소 직접 접근 방지
				HttpSession session = request.getSession();
				SuserVo authUser = (SuserVo)session.getAttribute("authUser");
				int userNo = authUser.getUser_id();
			
				String imgName = null;
				
				File file = new File(SAVEFOLDER);
				if (!file.exists())
					file.mkdirs();
				
				MultipartRequest multi = new MultipartRequest(request, SAVEFOLDER,MAXSIZE, ENCTYPE,
										 new DefaultFileRenamePolicy());
				
				String title = multi.getParameter("title");
				String content = multi.getParameter("content");
			
				if (multi.getFilesystemName("img_name") != null) {
					imgName = multi.getFilesystemName("img_name");
				}
				
				SboardVo vo = new SboardVo();
				vo.setUser_id(userNo);
				vo.setImg_name(imgName);
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
			
		} else if ("reply".equals(actionName)) {
			int boardId = 0;
			int page = 0;
			String keyword = null;
			
			try {	// 주소 직접 접근 방지
				HttpSession session = request.getSession();
				SuserVo authUser = (SuserVo)session.getAttribute("authUser");
				int userNo = authUser.getUser_id();
				
				boardId = Integer.parseInt(request.getParameter("no"));	
				String reply = request.getParameter("reply");
				page = Integer.parseInt(request.getParameter("page"));
				keyword = request.getParameter("keyword");
				
				SCommentVo vo = new SCommentVo();
				vo.setContent(reply);
				vo.setBoardId(boardId);
				vo.setUserId(userNo);
				
				System.out.println(vo);
				
				SCommentDao dao = new SCommentDaoImpl();
				dao.insert(vo);
				
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Unauthorized Access");
			} finally {
				String query_str = "&no=" + boardId + "&page=" + page + "&keyword=" + keyword;
				WebUtil.redirect(request, response, "/scribble/main?a=view" + query_str);
			}
			
		} else if ("delete".equals(actionName)) {
			try {	// 주소 직접 접근 방지
				HttpSession session = request.getSession();
				SuserVo authUser = (SuserVo)session.getAttribute("authUser");
				int userNo = authUser.getUser_id();
			
				int board_id = Integer.parseInt(request.getParameter("no"));
				int page = Integer.parseInt(request.getParameter("page"));
				String keyword = request.getParameter("keyword");
				
				SboardDao dao = new SboardDaoImpl();
				SboardSuserVo vo = dao.get(board_id);
	
				if (userNo != vo.getUser_id()) {
					System.out.println("Unauthorized Access");
					WebUtil.redirect(request, response, "/scribble/main?a=list");
				} else {
					dao.delete(board_id);
					
					String encKeyword = URLEncoder.encode(keyword, "utf-8");
					WebUtil.redirect(request, response, "main?a=list&no=&page=" + page + "&keyword=" + encKeyword);
				}
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Unauthorized Access");
				WebUtil.redirect(request, response, "/scribble/main?a=list");
			}
			
		} else if ("cmtdelete".equals(actionName)) {
			try {	// 주소 직접 접근 방지
				HttpSession session = request.getSession();
				SuserVo authUser = (SuserVo)session.getAttribute("authUser");
				int userNo = authUser.getUser_id();
			
				int board_id = Integer.parseInt(request.getParameter("no"));
				int cmt_id = Integer.parseInt(request.getParameter("cmtNo"));
				int page = Integer.parseInt(request.getParameter("page"));
				String keyword = request.getParameter("keyword");
				System.out.println(board_id + "/" + cmt_id);
				SCommentDao dao = new SCommentDaoImpl();
				SCommentUserVo vo = dao.get(cmt_id);
//				System.out.println(vo);
	
				if (userNo != vo.getUserId()) {
					System.out.println(userNo + "/" + vo.getUserId());
					System.out.println("Unauthorized Access");
					WebUtil.redirect(request, response, "/scribble/main?a=list");
				} else {
					dao.delete(cmt_id);
					
					String encKeyword = URLEncoder.encode(keyword, "utf-8");
					WebUtil.redirect(request, response, "main?a=view&no=" + board_id + "&page=" + page + "&keyword=" + encKeyword);
				}
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Unauthorized Access");
				WebUtil.redirect(request, response, "/scribble/main?a=list");
			}
			
		} else {
			WebUtil.redirect(request, response, "/scribble/main?a=list");
		}
	}	
		
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
