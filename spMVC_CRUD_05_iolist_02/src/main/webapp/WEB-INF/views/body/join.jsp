<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${rootPath}/css/input.css?ver=20190723">
<link rel="stylesheet" type="text/css" href="${rootPath}/css/mybutton.css?ver=20190723">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style>
	span#check_id{
		color:red;
		font-weight: bold;
		display:hidden;
	}
</style>
<script>
	$(function(){
		// blur : 입력박스에 focus가 벗어날때
		$("#m_userid").blur(function(){
			let m_userid = $(this).val()
			$.ajax({
				url:"${rootPath}/member/check_id?m_userid=" + m_userid,
				method : "GET"						
			})
			.done(function(result){
				if(result == m_userid){
					//alert("이미 가입된 ID입니다 \n" + "다른 ID를 입력해 주세요")
					let msg = "이미 가입된 ID입니다"
					$("#check_id").text(msg)
					$("#check_id").css("display","block")
					
					
					
					$("#m_userid").focus()
					$("#m_userid").select()
					return false;
				}
				
				
			})
		})
		$("#btn-join").click(function(){
			let pass = $("#m_password").val()
			let repass = $("#m_repassword").val()
			
			if(pass == ""){
				alert("비밀번호를 입력하세요")
				$("#m_password").focus()
				return false;
			}
			if(repass == ""){
				alert("비밀번호확인을 입력하세요")
				$("#m_repassword").focus()
				return false;
			}
			if(pass != repass){
				alert("비밀번호와 비밀번호 확인문자가 일치하지 않습니다")
				$("#m_password").val("")
				$("#m_repassword").val("")
				$("#password").focus()
				return false;
			}
		})
	})
</script>
<form method="POST">

	<fieldset>
		<legend>회원가입</legend>
		<div class="in-box">
			<label for="m_userid">회원아이디</label> <input type="text"
				name="m_userid" id="m_userid"><br/>
			<span id="check_id"></span>
		</div>
		
		<div class="in-box">
			<label for="m_password">비밀번호</label> <input type="password"
				name="m_password" id="m_password">
		</div>
		<div class="in-box">
			<label for="m_repassword">비밀번호확인</label> <input type="password"
				name="m_repassword" id="m_repassword">
		</div>
		<div class="in-box">
			<label for="m_name">회원이름</label> <input type="text" name="m_name"
				id="m_name">
		</div>
		<div class="in-box">
			<label for="m_email">email</label> <input type="text" name="m_email"
				id="m_email">
		</div>
		<div class="in-box">
			<label for="m_tel">전화번호</label> <input type="text" name="m_tel"
				id="m_tel">
		</div>
		<div class="btn-box center">
			<button type="button" id="btn-join" class="bz-btn save">회원가입</button>
		</div>
	</fieldset>
</form>