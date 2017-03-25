<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="style.css" type="text/css">
<title>Insert title here</title>
</head>
<body>
	<table width="800" cellpadding="0" cellspacing="0" border="1">
		<form action="reply.do" method="post">
			<input type="hidden" name="bId" value="${reply_view.bId}">
			<input type="hidden" name="bGroup" value="${reply_view.bGroup }">
			<input type="hidden" name="bStep" value="${reply_view.bStep }">
			<input type="hidden" name="bIndent" value="${reply_view.bIndent }">
			
			<tr>
				<td class="header">No </td>
				<td>${reply_view.bId}</td>
			</tr>
			<tr>
				<td class="header">Hit </td>
				<td>${reply_view.bHit }</td>
			</tr>
			<tr>
				<td class="header">Name </td>
				<td><input type="text" name="bName" value="${reply_view.bName }"></td>
			</tr>
			<tr>
				<td class="header">Title </td>
				<td><input type="text" name="bTitle" value="${reply_view.bTitle }"></td>
			</tr>
			<tr>
				<td class="header">Content </td>
				<td><textarea rows="10" name="bContent">${reply_view.bContent }</textarea> </td>
			</tr>
			<tr>
				<td colspan="2" class="add"><input type="submit" value="reply">&nbsp;&nbsp;
					<a href="list.do">List</a>
				</td>
			</tr>
		</form>
	</table>
</body>
</html>