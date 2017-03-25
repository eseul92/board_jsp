<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>
<%@ page import="Dto.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%!
	ArrayList<Dto> dtos = null; //ListCommand���� ������ �Խñ� ��ü�� ���� ArrayList ���� ����
	
	int totalCount=0, rowCount=10, pageSize=0, pageCount=5; //totalCount: �� �� ����, rowCount: �� �������� ���� �� ����, pageSize: ������ ����, pageCount: �ѹ��� ������ ������ ����
	int startNum=0, endNum=0; //startNum: �� �������� ������ �۹�ȣ, endNum: �� �������� ���� �� ��ȣ
	
	String pageNum = null; //������ Ŭ���� � �������� Ŭ���ߴ��� request���� �޾ƿ� ����
	int pageNow=0; //������ Ŭ���� ���ڸ� ���� ���� ����
	
	int pageStart=1, pagePrev=0, pageNext=0; //pageStart: ó��, pagePrev: ����, pageNext: ���� �������� ���� ���� ����
%> 

<%
	dtos = (ArrayList)request.getAttribute("list"); //setAttribute�� �Խñ� ��ü��  dtos�� ����

	if(dtos != null){ //�Խñ��� �����ϸ�
		totalCount = dtos.size(); //�Խñ��� ��ü ũ�⸦ totalCount�� ����
		
		System.out.println("totalCount : " + totalCount);
		
		if(totalCount > 10 ){	//totalCount�� 10���� ũ��
			pageSize = totalCount/rowCount;  //pageSize�� (�� ��ü�� / �� �������� �� ��� ��) ����
						
			if(totalCount % rowCount != 0){ //�������� ������
				pageSize++;				//pageSize�� 1����
			}
			
		}else{	//�Խñ��� 10���� ������ 
				pageSize = 1; //pageSize�� 1�� ����
		}
		
		System.out.println("pageSize: " + pageSize);
 
		
		pageNum = request.getParameter("pageNum");  //Ŭ���� pageNum�� ������ ���ڿ� pageNum�� ����
		
		if(pageNum != null){
			pageNow = Integer.parseInt(pageNum);	//pageNum�� int������ ����ȯ
			
			//������ ������ ��ư�� ������ �� �������� ����
			pagePrev = pageNow-1;
			pageNext = pageNow+1;
			
			//�������� ������ ����� ���� ����
			if(pagePrev < pageStart){ 		//pagePrev�� pageStart���� ������
				pagePrev = pageStart; 		//pagePrev�� pageStart�� ����
			}else if(pageNext > pageSize){	//pageNext�� pageSize���� ũ��
				pageNext = pageSize;		//pageNext�� pageSize�� ���� 
			}
		
		}else{ //ó�� list.do�� ���� ��
			pageNow = 1; //ó�� �������� 1�������� ����
			
			pagePrev = pageStart;	//pagePrev�� pageStart�� ����
			pageNext = pageNow+1;	//pageNext�� pageNow+1�� ����
		}
		
		System.out.println("pageNow: " + pageNow);
		
		//dtos�� ArrayList�̹Ƿ� 0���� �����ϹǷ� 0~9, 10~19, 20~29, ....
		startNum=(pageNow*rowCount-rowCount); 
		endNum=pageNow*rowCount-1;	
		
	}else{ 
		  System.out.println("��ϵ� �Խù��� �����ϴ�.");
		}
		
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="style.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�� ���</title>
</head>
<body>
	
		<table width="800" cellpadding="0" cellspacing="0" border="1">
			<tr class="header">
				<td>No</td>
				<td>Name</td>
				<td>Title</td>
				<td>Date</td>
				<td>Hit</td>
			</tr>
			
			<c:forEach items="${list}" var="dto" begin="<%=startNum %>" end="<%=endNum %>"> <%-- items: ���� �� var: ������  --%>
				<tr class="context">
					<td>${dto.bId}</td>
					<td>${dto.bName}</td>
					<td><c:forEach begin="1" end="${dto.bIndent}">-></c:forEach>
						<a href="content_view.do?bId=${dto.bId}" class="bTitle">${dto.bTitle }</a></td>
					<td>${dto.bDate}</td>
					<td>${dto.bHit}</td>
				</tr>
			</c:forEach>
				<tr>
					<td colspan="5" class="add"><a href="write_view.do">Add</a></td>
				</tr>
		</table>
		<div class="pageIndex">
		<a href="list.do?pageNum=<%= pageStart %>">[First]</a>
		<a href="list.do?pageNum=<%= pagePrev %>">[Prev]</a>
		
		<%
			for(int i=pageStart; i<=pageSize; i++){
					if(pageNow==i){%>
						<a href="list.do?pageNum=<%=i %>" style="text-decoration:none; color:red;" >[<%=i %>]</a>
			<% 
					}else{
			%>			<a href="list.do?pageNum=<%=i %>">[<%=i %>]</a>
					
			<%		}
			}
		%>
			
		<a href="list.do?pageNum=<%=pageNext %>">[Next]</a>
		<a href="list.do?pageNum=<%=pageSize %>">[End]</a>
		</div>
</body>
</html>