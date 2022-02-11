<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<c:if test="${post eq null }">
					<h2 style="color: gray; font-weight: bold; text-align: center;">작성 된 글이 없습니다.</h2>
					</c:if>
					<h4>${post.title }</h4>
					<p>
					${post.content }
					<p>
				</div>
				<hr style="margin-top: 36px">
				<ul class="blog-list">
				<c:if test="${postList eq null }">
					<h3 style="color: gray; font-weight: bold; text-align: center;">해당 카테고리에 글이 없습니다.</h3>
				</c:if>
				<c:forEach items="${postList }" var="postItem" varStatus="status">
					<li><a href="${pageContext.request.contextPath }/${blogId }/${postItem.categoryNo }/${postItem.no }">${postItem.title }</a> <span>${postItem.regDate }</span>	</li>
				</c:forEach>
				</ul>
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/blog-category.jsp" />
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
	</div>
</body>
</html>