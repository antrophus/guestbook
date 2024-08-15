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
		<table border="1" width="540px">
			<tr>
				<td><label for="text-name">이름</label></td>
				<td><input id="text-name" type="text" name="name" required></td>
				<td><label for="pw">비밀번호</label></td>
				<td><input id="pw" type="password" name="password" required></td>
			</tr>
			<tr>
				<td colspan="4"><textarea cols="72" rows="5" name="script"
						required></textarea></td>
			</tr>
			<tr>
				<td colspan="4"><button type="submit">등록</button></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="insert">
	</form>
	<br>

	<%
	for (int i = 0; i < guestList.size(); i++) {
	%>
	<table border="1" width="540px">
		<tr>
			<td><%=guestList.get(i).getScriptNo()%></td>
			<td><%=guestList.get(i).getName()%></td>
			<td><%=guestList.get(i).getDate()%></td>
			<td><a
				href="/guestbook/gbc?action=deleteform&no=<%=guestList.get(i).getScriptNo()%>">삭제</a></td>
		</tr>
		<tr>
			<td colspan="4"><%=guestList.get(i).getScript()%></td>
		</tr>
	</table>
	<br>
	<%
	}
	%>
</body>
</html>