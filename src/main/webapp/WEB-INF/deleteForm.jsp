<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>deleteForm - jstl</title>
</head>
<body>
	<form action="/guestbook/gbc" method="get">
		<table>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password" required></td>
				
				<td>
				<button type="submit">삭제</button>
				<input type="hidden" name="action" value="delete">
        		<input type="hidden" name="no" value="${guestbookVo.scriptNo}">		
				</td>
			</tr>
		</table>
	</form>
	<br>
	<br>
	<a href="/guestbook/gbc?action=addlist">메인으로 돌아가기</a>
</body>
</html>