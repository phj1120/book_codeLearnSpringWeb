<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="/board/result" method = "post">
<table>
	<tr>
		<td>페이지 번호 : </td>
		<td><input name = pageNum value ="${cri.pageNum }"/></td> 
	</tr>
	<tr>
		<td>데이터  양  : </td>
		<td><input name = amount value ="${cri.amount }"/></td>
	</tr>
	<tr>
		<td>
			<button type="submit" data-oper='get'>GET</button>		       		
			<button type="submit" data-oper='post'>POST</button>
		</td>
	</tr>
</table>
</form>
</body>

<script type="text/javascript">
$(document).ready(function(){
	var formObj = $("form");
	console.log(formObj)
	$('button').on("click", function(e){
		e.preventDefault();
		var operation =$(this).data("oper");
		if(operation === 'post'){
			formObj.attr("method", "post")
		}else if (operation === 'get'){
			formObj.attr("method", "get")
			}
		formObj.submit();
	});
});
</script>
</html>