<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>글쓰기</title>
<link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>
	<table width="800" cellpadding="0" cellspacing="0" border="1">
		<form action="write.do" method="post">
			<tr>
				<td class="header">Name </td>
				<td><input type="text" name="bName" size="80"></td>
			</tr>
			<tr>
				<td class="header">Title </td>
				<td><input type="text" name="bTitle" size="80"></td>
			</tr>
			<tr>
				<td class="header">Content</td>
				<td><textarea rows="10" name="bContent" style="width:80%"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" class="add">
				<input type="submit" value="Write">
				<a href="list.do">List</a>
				</td>
		</form>
	</table>
</body>
</html>