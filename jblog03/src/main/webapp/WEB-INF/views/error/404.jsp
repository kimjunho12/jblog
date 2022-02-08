<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
	<div id="wrapper">
		<div id="content">
			<h1>Not Found (404) - Ooooooooooooops!</h1>
			<p>죄송합니다. 요청하신 페이지를 찾을 수 없습니다.</p>
		</div>
	</div>
	<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
</body>
</html>