<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>
<%@ page import="Dto.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%!
	ArrayList<Dto> dtos = null; //ListCommand에서 설정한 게시글 전체를 넣을 ArrayList 변수 선언
	
	int totalCount=0, rowCount=10, pageSize=0, pageCount=5; //totalCount: 총 글 갯수, rowCount: 한 페이지에 넣을 글 갯수, pageSize: 페이지 갯수, pageCount: 한번에 보여줄 페이지 갯수
	int startNum=0, endNum=0; //startNum: 한 페이지에 시작할 글번호, endNum: 한 페이지에 끝날 글 번호
	
	String pageNum = null; //페이지 클릭시 어떤 페이지를 클릭했는지 request에서 받아올 변수
	int pageNow=0; //페이지 클릭한 숫자를 넣을 변수 선언
	
	int pageStart=1, pagePrev=0, pageNext=0; //pageStart: 처음, pagePrev: 이전, pageNext: 다음 페이지를 넣을 변수 선언
%> 

<%
	dtos = (ArrayList)request.getAttribute("list"); //setAttribute한 게시글 전체를  dtos에 저장

	if(dtos != null){ //게시글이 존재하면
		totalCount = dtos.size(); //게시글의 전체 크기를 totalCount에 저장
		
		System.out.println("totalCount : " + totalCount);
		
		if(totalCount > 10 ){	//totalCount가 10보다 크면
			pageSize = totalCount/rowCount;  //pageSize에 (글 전체수 / 한 페이지의 글 목록 수) 저장
						
			if(totalCount % rowCount != 0){ //나머지가 있으면
				pageSize++;				//pageSize에 1증가
			}
			
		}else{	//게시글이 10보다 적으면 
				pageSize = 1; //pageSize에 1을 저장
		}
		
		System.out.println("pageSize: " + pageSize);
 
		
		pageNum = request.getParameter("pageNum");  //클릭한 pageNum을 가져와 문자열 pageNum에 저장
		
		if(pageNum != null){
			pageNow = Integer.parseInt(pageNum);	//pageNum을 int형으로 형변환
			
			//이전과 이후의 버튼을 눌렀을 때 페이지를 설정
			pagePrev = pageNow-1;
			pageNext = pageNow+1;
			
			//페이지의 범위를 벗어나는 것을 방지
			if(pagePrev < pageStart){ 		//pagePrev가 pageStart보다 작으면
				pagePrev = pageStart; 		//pagePrev에 pageStart를 저장
			}else if(pageNext > pageSize){	//pageNext가 pageSize보다 크면
				pageNext = pageSize;		//pageNext에 pageSize를 저장 
			}
		
		}else{ //처음 list.do를 했을 때
			pageNow = 1; //처음 페이지를 1페이지로 설정
			
			pagePrev = pageStart;	//pagePrev에 pageStart를 저장
			pageNext = pageNow+1;	//pageNext에 pageNow+1을 저장
		}
		
		System.out.println("pageNow: " + pageNow);
		
		//dtos가 ArrayList이므로 0부터 시작하므로 0~9, 10~19, 20~29, ....
		startNum=(pageNow*rowCount-rowCount); 
		endNum=pageNow*rowCount-1;	
		
	}else{ 
		  System.out.println("등록된 게시물이 없습니다.");
		}
		
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="style.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>글 목록</title>
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
			
			<c:forEach items="${list}" var="dto" begin="<%=startNum %>" end="<%=endNum %>"> <%-- items: 넣을 값 var: 변수명  --%>
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