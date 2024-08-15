package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestbookVo;

public class GuestbookDao {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/guestbook_db";
	private String id = "guestbook";
	private String pw = "guestbook";
	// 생성자
	// 기본생성자 사용(그래서 생략)

	// 메소드 gs
	// 필드값을 외부에서 사용하면 안됨(그래서 생략)

	// 메소드 일반
	// DB연결 메소드
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 자원정리 메소드
	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 방명록 등록
	public int insertGuestBook(GuestbookVo guestbookVo) {

		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// *SQL문 준비
			String query = "";
			query += " insert into guest (name, password, script) ";
			query += " values (?, ?, ?) ";

			// *바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, guestbookVo.getName());
			pstmt.setString(2, guestbookVo.getPassword());
			pstmt.setString(3, guestbookVo.getScript());

			// *실행
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);

		}
		this.close();
		System.out.println(count);
		return count;
	}

	// 방명록 읽기
	public List<GuestbookVo> getGuestlist() {

		List<GuestbookVo> guestList = new ArrayList<GuestbookVo>();

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// *SQL문 준비
			String query = "";
			query += " select script_no ";
			query += " 		 ,name ";
			query += " 		 ,date ";
			query += " 		 ,script ";
			query += " from guest ";

			// 바인딩
			pstmt = conn.prepareStatement(query);

			// 실행
			rs = pstmt.executeQuery();

			// 4. 결과처리
			while (rs.next()) {
				int no = rs.getInt("script_no");
				String name = rs.getString("name");
				String date = rs.getString("date");
				String script = rs.getString("script");

				GuestbookVo guestVo = new GuestbookVo(no, name, date, script);

				guestList.add(guestVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();
		return guestList;
	}

	// 방명록 삭제
	public int delete(GuestbookVo guestVo) {
		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// *SQL문 준비
			String query = "";
			query += " delete from guest ";
			query += " where script_no = ? ";

			// *바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, guestVo.getScriptNo());

			// *실행
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);

		}
		this.close();
		System.out.println(count);
		return count;
	}

}
