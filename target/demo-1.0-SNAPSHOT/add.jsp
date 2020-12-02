<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加-修改</title>
</head>
<link rel="icon" href="images/search.gif" type="img/x-ico" />
<link href="css/login.css" type="text/css" rel="stylesheet">
<link href="css/index.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jquery-3.3.1.min.js"></script>
<body>
<%@ include file="top.jsp" %>
	<div id="main">
		<div class="navigation">
			当前位置:&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/admin?method=getBorrowed&page=1">还书管理</a>
			&nbsp;<a href="${pageContext.request.contextPath }/admin?method=findAll&page=1">图书列表</a>
			<div id="readerBlock">欢迎回来&nbsp;:${admin.username }&nbsp;<a href="${pageContext.request.contextPath }/logout">注销</a></div>
		</div>
		<div class="img_block">
			<img src="images/main_booksort.gif" class="img" />
		</div>
		<div class="table">
			<form action="${pageContext.request.contextPath }/admin?method=add" method="post" id="loginForm">
				<table border="0">
					<tr>
						<td class="title">书名:</td>
						<td class="input">
							<input type="text" name="name" id="name" class="login_input" value="${book.name}"/>
						</td>
					</tr>
					<tr>
						<td class="title">作者:</td>
						<td class="input">
							<input type="text" name="author" id="author" class="login_input" value="${book.author}"/>
						</td>
					</tr>
					<tr>
						<td class="title">出版社:</td>
						<td class="input">
							<input type="text" name="publish" value="${book.publish}" class="login_input"  />
						</td>
					</tr>
					<tr>
						<td class="title">页数:</td>
						<td class="input">
							<input type="number" name="pages" value="${book.pages}"  class="login_input"/>
						</td>
					</tr>
					<tr>
						<td class="title">价钱:</td>
						<td class="input">
							<input type="number" name="price" value="${book.price}"  class="login_input"/>
						</td>
					</tr>
					<tr>
						<td class="title">类型:</td>
						<td class="input">
							<select name="bookcaseid" class="login_input">
								<c:forEach items="${bookcases}" var="bookcase">
									<c:choose>
										<c:when test="${book.bookCase.id == bookcase.id}">
											<option value="${bookcase.id}" selected>${bookcase.name} </option>
										</c:when>
										<c:otherwise>
											<option value="${bookcase.id}">${bookcase.name} </option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="title">是否上架:</td>
						<td class="input">
							<c:choose>
								<c:when test="${empty book.bookCase.id}">
									<input type="radio" name="abled" value="0" checked="checked"/>&nbsp;&nbsp;否
									<input type="radio" name="abled" value="1"/>&nbsp;&nbsp;是&nbsp;
								</c:when>
								<c:otherwise>
									<input type="radio" name="abled" value="0" />&nbsp;&nbsp;否
									<input type="radio" name="abled" value="1" checked="checked"/>&nbsp;&nbsp;是&nbsp;
								</c:otherwise>
							</c:choose>
						&nbsp;
						</td>
					</tr>

					<input  type="hidden" name="id" value="${book.id}"/>

					<tr>
						<td class="title"></td>
						<td class="input">
							<input  type="submit" value="提交"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div id="footer">
		<div class="foot_content">
			CopyRight &copy; 2020
		</div>
		<div class="foot_content">
			版权所有 Library V1.0
		</div>
	</div>
</body>
</html>