<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Guestbook jstl</title>
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

	<c:forEach items="${requestScope.guestList}" var="guestbookVo">
	
	<table border="1" width="540px">
		<tr>
			<td>${guestbookVo.scriptNo }</td>
			<td>${guestbookVo.name }</td>
			<td>${guestbookVo.date }</td>
			<td><a
				href="/guestbook/gbc?action=deleteform&no=${guestbookVo.scriptNo }">삭제폼으로 이동</a></td>
		</tr>
		<tr>
			<td colspan="4">${guestbookVo.script}</td>
		</tr>
	</table>
	<br>
	</c:forEach>
</body>
</html>