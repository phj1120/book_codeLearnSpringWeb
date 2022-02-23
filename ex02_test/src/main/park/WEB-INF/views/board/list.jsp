<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Tables</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
      		Board List Page
			<button id='regBtn' type="button" class="btn btn-xs pull-right">Register New Board</button>
           
            
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <table class="table table-striped table-boardered table-hover">
	                <thead>
	                    <tr>
	                        <th>번호
		                        <button id='sortBtn' tyep="button" class="btn btn-xs center">${sort }</button>
	                        </th>
	                        <th>제목</th>
	                        <th>작성자</th>
	                        <th>작성일</th>
	                        <th>수정일</th>
	                    </tr>
	                </thead>

					<c:forEach items="${list }" var="board">
						<tr>
							<td><c:out value="${board.bno }" /></td>
							<td><a href="<c:url value='/board/get?bno=${board.bno }' />" />
							<c:out value="${board.title }"/></a></td>
							<td><c:out value="${board.writer }" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate }" /></td>					
							<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate }" /></td>					
						</tr>
					</c:forEach>
	            </table>
	            
	            <!-- Modal 추가 시작 -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                            </div>
                            <div class="modal-body"> 처리가 완료되었습니다. </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
	            <!-- Modal 추가 완료 -->
	            
	            
	            
            </div>
            <!-- end panel-body -->
        </div>
        <!-- end panel -->
    </div>
    <!-- /.col-lg-6 -->
</div>

<script type="text/javascript">
$(document).ready(function(){
	var result = '<c:out value="${result}"/>';
	console.log("[result] : ", result)
	
	checkModal(result);
	console.log("[checkModal]")
	console.log("[history.state] : ",history.state)
	
	
	history.replaceState({}, null, null);
	console.log("[history.replaceState]")
	console.log("[history.state] : ",history.state)
	/* 모달창 */ 
	function checkModal(result){
		if (result === '' || history.state ){
			console.log("[return]")	
			return;
		}
		
		if(parseInt(result)>0){
			$(".modal-body").html("게시글 "+parseInt(result)+" 번이 등록되었습니다.");
		}
		
		$("#myModal").modal("show");

		}
	
	$("#regBtn").on("click", function(){
		self.location = "<c:url value='/board/register' />";
	});	
	
	$("#sortBtn").on("click", function(){
		
		if("${sort }"==="asc"){
			self.location = "<c:url value='/board/list?sort=desc' />"
		}
		else if("${sort }"==="desc"){
			self.location = "<c:url value='/board/list?sort=asc' />"
		}
	});
});


</script>
<%@include file="../includes/footer.jsp" %>