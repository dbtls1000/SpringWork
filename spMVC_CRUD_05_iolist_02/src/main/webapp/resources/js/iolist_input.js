$(function(){
	$("#btn-save").click(function(){
		//유효성검사 1 : 값이 입력되지 않은 항목 거르기
		if($("#io_date").val() == ""){
			alert("거래일자를 입력하세요")
			$("#io_date").focus()
			return false;
		}
		
		if($(":radio[name='io_inout']:checked").length < 1){
			alert("매입매출을 선택하세요")
			$("#io_inout").focus()
			return false;
		}
		if($("#io_ccode").val() == ""){
			alert("거래처를 입력하세요")
			$("#comp_name").focus()
			return false;
		}
		if($("#io_pcode").val() == ""){
			alert("상품을 입력하세요")
			$("#pro_name").focus()
			return false;
		}
		if($("#io_price").val() == ""){
			alert("상품단가를 입력하세요")
			$("#io_price").focus()
			return false;
		}
		if(isNaN($("#io_price").val() )){
			alert("단가는 숫자로 입력하세요")
			$("#io_price").focus()
			$("#io_price").select()
			return false;
		}
		if($("#io_amt").val() == ""){
			alert("상품수량을 입력하세요")
			$("#io_amt").focus()
			return false;
		}
		if(isNaN($("#io_amt").val() )){
			alert("수량은 숫자로 입력하세요")
			$("#io_amt").focus()
			$("#io_amt").select()
			return false;
		}
		if(!isNaN($("#io_price").val()) && !isNaN($("#io_amt").val()) ){
			let io_price = Number($("#io_price").val() )
			let io_amt = Number($("#io_amt").val() )
			
			let io_total = io_price * io_amt
			
			$("#io_total").val(io_total)
		}

		
		$.ajax({
			url:rootPath + "/iolist/input",
			method : "POST",
			data : $("form").serialize()
		})
		.done(function(result){
			$("#iolist_view").html(result)
		})
		
		//jq 3.0이상에
//		$.post(rootPath + "/iolist/input", {"iolist":$("form").serialize()},
//				function(result){
//					$("#iolist").html(result)
//				}
//		)
		//		$("#io_price").keypress(function(event){
//			if(!isNaN($("#io_price").val()) || !isNaN($("#io_amt").val()) ){
//				let io_price = Number($("#io_price").val())
//				let io_amt = Number($("#io_amt").val())
//				let io_total = io_price * io_amt
//				
//				$("#io_total").val(io_total)
//			}
//		})
//		$("#io_amt").keypress(function(event){
//			if(!isNaN($("#io_price").val()) || !isNaN($("#io_amt").val()) ){
//				let io_price = Number($("#io_price").val())
//				let io_amt = Number($("#io_amt").val())
//				let io_total = io_price * io_amt
//				
//				$("#io_total").val(io_total)
//			}
//		})
		
//		$('form').submit()
//		"${rootPath}/iolist/input"
		
		
		
		
		
		
		
	})
})