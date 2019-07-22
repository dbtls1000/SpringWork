"use strict"
$(function() {
	$("input").focus(function() {
		$(this).select()
	})
	
	$("#btn-save").click(function() {
		let mo_date = $("#mo_date").val()
		let mo_time = $("#mo_time").val()
		let mo_author = $("#mo_author").val()
		let mo_subject = $("#mo_subject").val()
		let mo_memo = $("#mo_memo").val()
		
		let msg = "작성날짜 : " + mo_date +"\n"
		msg += "작성시각 : " + mo_time +"\n"
		msg += "작성자 : " + mo_author +"\n"
		msg += "제목 : " + mo_subject +"\n"
		msg += "내용 : " + mo_memo
		
		alert(msg)
	})
})