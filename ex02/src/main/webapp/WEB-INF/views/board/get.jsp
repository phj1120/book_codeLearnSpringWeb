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

            <div class="panel-heading">Board Read Page</div>
            <div class="panel-body">
            
    		<div class="form-group">
    			<label>Bno</label> 
    			<input class="form-control" name="bno" value='<c:out value="${board.bno }"/>' readonly="readonly">
   			</div>
   			
    		<div class="form-group">
    			<label>Title</label> 
    			<input class="form-control" name="title" value='<c:out value="${board.title }"/>' readonly="readonly">
   			</div>
    		
    		<div class="form-group">
    			<label>Text Area</label> 
    			<textarea class="form-control" name="content" rows="3" readonly="readonly"> <c:out value="${board.content }"/></textarea>
   			</div>
   			
    		<div class="form-group">
    			<label>Writer</label> 
    			<input class="form-control" name="writer" value='<c:out value="${board.writer }"/>' readonly="readonly">
   			</div>
   			
<%--        버튼으로 직접 링크 처리    	
			<button data-oper='modify' class="btn btn-default" onclick="location.href='/board/modify?bno=<c:out value="${board.bno }"/>'">modify Button</button>		       		
           	<button data-oper="list" class="btn btn-default" onclick="location.href='/board/list'">List</button> --%>
           	<button data-oper='modify' class="btn btn-default">modify Button</button>		       		
           	<button data-oper="list" class="btn btn-default">List</button>		       		
       		
			<form id='operForm' action="/board/modify" method="get">
				<!-- list를 보여 줄 경우 bno 는 필요 없어지므로 script 에서 지우기 위해 id 지정  -->
				<input type="hidden" id='bno' name='bno' value='<c:out value="${board.bno }" />'>
				<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum }" />'/>
           		<input type='hidden' name='amount' value='<c:out value="${cri.amount}" />'/>
       		</form>
       		</div>
        </div>
        <!-- end panel -->
    </div>
    <!-- /.col-lg-6 -->
</div>
<script type="text/javascript">
$(document).ready(function(){
	var operForm = $("#operForm");
	
	$("button[data-oper='modify']").on("click", function(e){
		operForm.attr("action", "/board/modify").submit();
	});
	
	$("button[data-oper='list']").on("click", function(e){
		operForm.find("#bno").remove();
		operForm.attr("action", "/board/list")
		operForm.submit();
	});
});

</script>
<%@include file="../includes/footer.jsp" %>
