<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="style.css" type="text/css">
<title>${content_view.bTitle}</title>
</head>
<body>
	<table width="800" cellpadding="0" cellspacing="0" border="1">
		<form action="modify.do" method="post">
			<input type="hidden" name="bId" value="${content_view.bId}">
			<tr>
				<td class="header">No</td>
				<td>${content_view.bId }</td>
			</tr>
			<tr>
				<td class="header">Hit</td>
				<td>${content_view.bHit }</td>
			</tr>
			<tr>
				<td class="header">Name</td>
				<td><input type="text" name="bName" value="${content_view.bName }"></td>
			</tr>
			<tr>
				<td class="header">Title</td>
				<td><input type="text" name="bTitle" value="${content_view.bTitle }"></td>
			</tr>
			<tr>
				<td class="header">Content</td>
				<td><textarea rows="10" name="bContent" style="width:80%">${content_view.bContent }</textarea></td>
			</tr>
			<tr>
				<td colspan="2" class="add">
					<input type="submit" value="modify">&nbsp;&nbsp;
					<a href="delete.do?bId=${content_view.bId }">Delete</a>&nbsp;&nbsp;
					<a href="reply_view.do?bId=${content_view.bId }">Reply</a>&nbsp;&nbsp;
					<a href="list.do">List</a>
				</td>
			</tr>
		</form>
	</table>
</body>
</html>