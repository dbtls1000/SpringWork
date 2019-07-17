"use strict"
// 지금부터 철저한 문법규칙을 준수한다는 선언
// 기본문법과 런타임 동작을 모두 검사하여
// 실수를 에러로 변환시키고
// 변수 사용을 단수화 할 수 있도록 선언

//var num1 = 10
//let num2 = 20
//name = "홍길동"
// js표준에는 사용할수 있는 문법이지만 가급적 사용 지양
$(function() {
	
	$("input").focus(function() {
		$(this).select()
	})
	
	$("#btn-save").click(function() {
		let s_title = $("#b_title").val()
		let s_comp = $("#b_comp").val()
		let s_author = $("#b_author").val()
		let s_price = $("#b_price").val()
		
		let msg = "도서명:" + s_title+"\n"
		msg += "출판사:" + s_comp+"\n"
		msg += "저자:" + s_author+"\n"
		msg += "가격:" + s_price
		/* 값의 유효성 검사
		 * 1. 입력값이 있냐 2. 입력값이 원칙, 규칙에 맞느냐
		 * 3. 모두 통과 했을 때만 서버로 데이터를 보냄
		 * 
		 * 입력값을 전송하기전에 유효성 검사
		 * 1. 데이터를 서버로 보내서 유효성 검사를 할수도 있지만
		 * 최소한 원칙에 맞는 데이털르 사전에 검사하여
		 * 서버가 할일을 줄여준다
		 * 2. 사용자 입장에서 내가 입력한 값을 확인하면서
		 * 잘 입력됬는지를 알려주는 이정표
		 * 
		 */
		// 입력되지 않은 항목이 있는지 검사
		if(s_title == ""){
			alert("도서명은 반드시 입력해야 합니다")
			// 입력자에게 경고를 보여주고 해당항목을 입력할 수 있도록 커서를
			// 입력박스에 생성시켜 준다.
			$("#b_title").focus()
			// 현재 항목에 유효성 검사를 하고
			// 통과하지 못하면 다음으로 진행x
			return false;
		}
		if(s_comp == ""){
			alert("출판사는 반드시 입력해야 합니다")
			$("#b_comp").focus()
			return false;
		}
		if(s_author == ""){
			alert("저자는 반드시 입력해야 합니다")
			$("#b_author").focus()
			return false;
		}
		if(s_price == ""){
			alert("가격은 반드시 입력해야 합니다")
			$("#b_price").focus()
			return false;
		}
		if(isNaN(s_price)){
			alert("가격은 숫자로만 입력해야 합니다")
			//입력박스에 담긴 값을 지워라
//			$("#b_price").val("")
			// 원래 입력된 text를 모두 감싸고
			// 아무 글자나 입력하면 원래 text를 삭제할 수 있는기능
			$("#b_price").select()
			$("#b_price").focus()
			return false;
		}
		$('form').submit();
	})
})