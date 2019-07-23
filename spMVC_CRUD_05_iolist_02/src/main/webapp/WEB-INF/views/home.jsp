<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<c:set var="rootPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" 
	href="${rootPath}/css/main1.css?ver=20190723">
<link rel="stylesheet" type="text/css" 
	href="${rootPath}/css/list.css?ver=20190723">
<link rel="stylesheet" type="text/css" 
	href="${rootPath}/css/input.css?ver=2019072201">
	<link rel="stylesheet" type="text/css" 
	href="${rootPath}/css/mybutton.css?ver=20190723">	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>	

<style>
	section.main-section{
		display:flex;
	}
	article.body-box{
		flex:1 1 33%;
		overflow:auto;
	}
</style>

<script>
	$(function(){
		$.ajax({
			url: "${rootPath}/iolist/list",
			method : "GET"
		})
		.done(function(result){
			$("#iolist_view").html(result)
		})
	})
</script>

</head>
<body>
	<%@ include file="/WEB-INF/views/include/include-header.jspf" %>
<section class="main-section">
	<article class="body-box" id="company">
		<%@ include file="/WEB-INF/views/ajax_body/company/body.jspf" %>
	</article>
	<article class="body-box" id="iolist">
		<%@ include file="/WEB-INF/views/ajax_body/iolist/input.jspf" %>
		<div id="iolist_view"></div>
	</article>
	<article class="body-box" id="product">
		<%@ include file="/WEB-INF/views/ajax_body/product/body.jspf" %>
	</article>
</section>
</body>
</html>