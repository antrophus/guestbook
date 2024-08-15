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
		System.out.println("등록 성공");

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

		} else if ("deleteform".equals(action)) {
			// 파라미터 꺼내기
			int no = Integer.parseInt(request.getParameter("no"));
			GuestbookVo guestVo = new GuestbookVo(no);

			// phonebookDao를 메모리에 올린다.
			GuestbookDao guestbookDao = new GuestbookDao();

			// db데이터 가져오기
			List<GuestbookVo> guestList = guestbookDao.getGuestlist();
			// 화면그리기 --> foward
			// request에 리스트주소 넣기
			request.setAttribute("guestList", guestList);

			// 포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/deleteForm.jsp");
			rd.forward(request, response);

			// phonebookDao를 통해서 삭제delete을 시킨다.
			guestbookDao.delete(guestVo);

			// 리다이렉트
			response.sendRedirect("/guestbook/gbc?action=addlist");

		} else if ("insert".equals(action)) {
			// 방명록 항목 추가
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String script = request.getParameter("script");

			GuestbookVo guestbookVo = new GuestbookVo(0, name, password, null, script);
			GuestbookDao guestbookDao = new GuestbookDao();

			// 추가 실행
			guestbookDao.insertGuestBook(guestbookVo);

			// 추가 후 목록 페이지로 리다이렉트
			response.sendRedirect("/guestbook/gbc?action=addlist");

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
