<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Result Page</title>
</head>
<body>
	<h2>요청 정보</h2>
	<p>메서드 : <%= request.getMethod() %></p>
	<p>URI : <%= request.getRequestURI() %></p>
	<p>쿼리 스트링 : <%= request.getQueryString() %></p>
	<h2>파라미터 정보</h2>
	<p>criteria.pageNum : <c:out value="${criteria.pageNum}" /></p>
	<p>criteria.amount : <c:out value="${criteria.amount}" /></p>
<%-- 	<p>cri.pageNum : <c:out value="${cri.pageNum}" /></p>
	<p>cri.amount : <c:out value="${cri.amount}" /></p>
	<p>pageNum : <c:out value="${pageNum}" /></p>
	<p>amount : <c:out value="${amount}" /></p>
--%>
	<form action="/board/test" method="get">
		<button type="submit">Go Test</button>	
	</form>
	<h2>결론</h2>
	<p>form 태그 안의 input 태그로 받은 데이터 전송할 경우</p>
	<p>[GET] : 쿼리 스트링에 데이터 담아서 전송</p>
	<p>[POST] : HTTP Body 에 데이터 담아서 전송</p>
	<p>POST 인 경우 쿼리 스트링에 데이터가 없어도</p> 
	<p>Controller 에서 값 받는데 문제 없다.</p>
	<p>redirect 로 왔다갔다 해서 헷갈렸던거임</p>
	<p>controller 에서 url 요청 받을 때는 get, post 로 form 태그로 받으면 됨</p>
	<p>controller 에서 값을 받아 jsp 로 view 하기 위해서는 Model 에 저장하면 됨</p>
	
	<h3>redirect 과정 </h3>
	<p>버튼 클릭 -> controller 요청 받음 -> 필요한 데이터 RedirectAttributes 에 저장</p>
	<p>-> 리다이렉트 -> controller 요청 받음 -> 필요한 데이터 Model 에 저장</p>
	<p>-> View 호출 -> Model 에 있는 값 이용해 JSP </p>
	
	<p>이 과정을 한번에 진행되는 과정으로 생각해서 헷갈렸다. </p>
</body>
</html>