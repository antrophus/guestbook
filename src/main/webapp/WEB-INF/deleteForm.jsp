<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.javaex.vo.GuestbookVo"%>

<%
List<GuestbookVo> guestList = (List<GuestbookVo>) request.getAttribute("guestList");
System.out.println("여기는 jsp");
System.out.println(guestList);
%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/guestbook/gbc" method="get">
		<table>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name=""></td>
				<!-- if input password == 디비상 패스워드일 때 삭제 발동?? -->
				<td><button type="submit">삭제</button></td>
			</tr>
		</table>
	</form>

	<br>
	<br>
	<a href="/guestbook/gbc?action=addlist">메인으로 돌아가기</a>
</body>
</html>