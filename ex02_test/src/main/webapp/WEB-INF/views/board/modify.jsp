<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Board Register</h1>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
			<form action="<c:url value='/board/modify' />" method="post">
	            <div class="panel-heading">Board modify Page</div>
	            <div class="panel-body">
	            
	    		<div class="form-group">
	    			<label>Bno</label> 
	    			<input class="form-control" name="bno" value='<c:out value="${board.bno }"/>' readonly="readonly">
	   			</div>
	   			
	    		<div class="form-group">
	    			<label>Title</label> 
	    			<input class="form-control" name="title" value='<c:out value="${board.title }"/>'>
	   			</div>
	    		
	    		<div class="form-group">
	    			<label>Text Area</label> 
	    			<textarea class="form-control" name="content" rows="3" ><c:out value="${board.content }"/></textarea> 
	   			</div>
	   			
	    		<div class="form-group">
	    			<label>Writer</label> 
	    			<input class="form-control" name="writer" value='<c:out value="${board.writer }"/>'>
	   			</div>
	   			
				<div class="form-group">
	    			<label>RegDate</label> 
	    			<input class="form-control" name="regDate" value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.regDate }"/>' readonly="readonly">
	   			</div>
	   			
	   			<div class="form-group">
	    			<label>updateDate</label> 
	    			<input class="form-control" name="updateDate" value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.updateDate }"/>' readonly="readonly">
	   			</div>
	   			
	           	<button type="submit" data-oper='modify' class="btn btn-default">Modify</button>		       		
	           	<button type="submit" data-oper='remove' class="btn btn-default">Remove</button>		       		
	           	<button type="submit" data-oper="list" class="btn btn-default">List</button>		       		
	       		</div>
       		</form>
        </div>
        <!-- end panel -->
    </div>
    <!-- /.col-lg-6 -->
</div>

<script type="text/javascript">
$(document).ready(function(){
	
	var formObj = $("form");
	
	$('button').on("click", function(e){
		e.preventDefault();
		
		var operation =$(this).data("oper");
		
		console.log(operation);
		
		if(operation === 'remove'){
			// form 에서 method=post 로 지정
			formObj.attr("action", "<c:url value='/board/remove' />");
		}else if (operation === 'list'){
			// board/list 는 get 이므로
			// self.location ="/board/list";
			// return;
			formObj.attr("action", "<c:url value='/board/list' />").attr("method", "get")
			formObj.empty();
 		}
		formObj.submit();
	});
});

</script>

<%@include file="../includes/footer.jsp" %>
