package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@WebServlet("/gbc")
public class Guestbookcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Controller 접수받는 일
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("서버 연결됨");

		// 파라미터 읽기 (action) 뭔지 알아야 됨
		String action = request.getParameter("action");

		if ("addlist".equals(action)) {
			// 접수
			System.out.println("방명록 등록폼");
			// db데이터 가져오기
			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> guestList = guestbookDao.getGuestlist();

			// 화면그리기 --> foward
			// request에 리스트주소 넣기
			request.setAttribute("guestList", guestList);

			// 포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp");
			rd.forward(request, response);

		} else if ("insert".equals(action)) {
			// 방명록 항목 데이터 입력
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String script = request.getParameter("script");
			GuestbookVo guestbookVo = new GuestbookVo(0, name, password, null, script);

			// Dao를 메모리에 올리고
			GuestbookDao guestbookDao = new GuestbookDao();

			// 데이터 추가 실행
			guestbookDao.insertGuestBook(guestbookVo);

			// 데이터 추가 후 목록 페이지로 리다이렉트
			response.sendRedirect("/guestbook/gbc?action=addlist");

		} else if ("deleteform".equals(action)) {
			// 파라미터 꺼내기: 삭제하려는 항목의 번호(no)
			int no = Integer.parseInt(request.getParameter("no"));

			// Dao를 통해 디비에서 해당 항목 정보 가져오기
			GuestbookDao guestbookDao = new GuestbookDao();
			GuestbookVo guestbookVo = guestbookDao.getScriptNo(no);

			// 가져온 방명록 항목의 정보를 request 객체에 설정
			request.setAttribute("guestbookVo", guestbookVo);

			// 삭제 폼 페이지로 포워드 (deleteForm.jsp)
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/deleteForm.jsp");
			rd.forward(request, response);

		} else if ("delete".equals(action)) {
			System.out.println("삭제 요청");
			//파라미터에서 password="", no="" 값 꺼내기
			String inputPassword = request.getParameter("password");
			int no = Integer.parseInt(request.getParameter("no"));
			//Vo에 값 넣고 주소 저장
			GuestbookVo guestbookVo = new GuestbookVo(no,inputPassword);
			
			//Dao를 메모리에 올리고
			GuestbookDao guestbookDao = new GuestbookDao();
			
			//Dao를 통해 파라미터 값에 해당하는 디비 삭제
			guestbookDao.delete(guestbookVo);

			//리다이렉트
			response.sendRedirect("/guestbook/gbc?action=addlist");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
