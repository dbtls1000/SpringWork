<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
div.btn-box {
	width: 95%;
	margin: 0 auto;
}
</style>
<script>
	$(function() {

		$("#btn_write").click(function() {
			location.href = "${rootPath}/memo/write"
		})
		
		$(".memo_row").click(function() {
			let memo_seq = $(this).attr("data-memo")
			
			location.href="${rootPath}/memo/view?mo_seq=" + memo_seq
		})
		
	})
</script>
<table class="list">
	<tr>
		<th class="list">NO</th>
		<th class="list">작성일자</th>
		<th class="list">작성시각</th>
		<th class="list">작성자</th>
		<th class="list">제목</th>
	</tr>
	<% // c tag에서 어떤 값을 비교할때
		// a==b , a eq b 
		// a !=b , a ne b
		// a == null
		// empty : a == null || a ==""
	%>
	<c:choose>
		<c:when test="${empty MEMOS}">
			<tr>
				<td colspan="5">메모가 없습니다</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${MEMOS}" var="memoVO" varStatus="index">
				<tr class="memo_row" data-memo="${memoVO.mo_seq}">
					<td class="list">${index.count}</td>
					<td class="list">${memoVO.mo_date}</td>
					<td class="list">${memoVO.mo_time}</td>
					<td class="list">${memoVO.mo_author}</td>
					<td class="list">${memoVO.mo_subject}</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>


</table>
<div class="btn-box">
	<button id="btn_write" class="bz-btn input">메모 작성</button>
</div>