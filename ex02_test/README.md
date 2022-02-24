# 2022.02.24.
ex02_test 페이징 기능 구현 실습
어제 했는데 이해가 잘 안되서 
머리 짜내면서 했음
일단 어떻게든 하긴 했는데 조급하지 말고 꾸준히 하자
처음 하는 거라 어려워할 수밖에 없음
꼬우면 자주 했어야지. 내 탓 임.


리스트 페이지에서 전체를 호출하니 부분만 보여주자

BoardMapper.java

```java
public List<BoardVO> getList(Criteria cri);
```

SQL Developer 로 db 테스트 후 xml 에 적용

BoardMapper.xml

```java
<select id="getList" resultType="org.zerock.domain.BoardVO">
	<![CDATA[
	SELECT bno, title, content, writer, regDate, updateDate
	FROM (SELECT /*+ INDEX_DESC (tbl_board pk_board) */ ROWNUM rn, bno, title, content, writer, regDate, updateDate FROM tbl_board WHERE rownum <= #{pageNum} * #{amount} )
	WHERE rn > (#{pageNum}-1)*#{amount}
	]]>
	</select>
```

BoardMapperTests.java

```java
@Test
	public void testGetList() {
		log.info("[testGetList]");
		mapper.getList(new Criteria(2, 10)).forEach(board -> log.info(board));
		log.info("[getBoardTest] : " +boardVO);
	}
```

BoardService.java

```java
public List<BoardVO> getList(Criteria cri);
```

BoardServiceImpl.java

```java
@Override
public List<BoardVO> getList(Criteria cri) {
	log.info("[getList]");
	return mapper.getList(cri);
}
```

BoardServiceTests.java

```java
@Test
public void readListTest() {
	service.getList(new Criteria(2, 10));
	log.info("[readListTest]");
}
```

BoardController.java

```java
@GetMapping("/list")
public void list(Criteria cri, Model model) {
	List<BoardVO> list = service.getList(cri);
	
	model.addAttribute("list", list);
	log.info("[list]");
}

@PostMapping("modify")
public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
	if(service.update(board)) {
		rttr.addFlashAttribute("result", "success");
	}
	log.info("[modify]");
	return "redirect:/board/list";
}

@PostMapping("remove")
public String remove(long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
	if(service.delete(bno)) {
		rttr.addFlashAttribute("result", "success");
	}
	log.info("[remove]");
	return "redirect:/board/list";
}

@PostMapping("/register")
public String registerPost(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
	service.create(board);
	rttr.addFlashAttribute("result", board.getBno());
	log.info("[registerPost]");
	return "redirect:/board/list";
}
```

PageDTO.java

list 에 페이지 정보를 주기 위한 DTO

```java
package org.zerock.domain;

import lombok.Data;

@Data
public class PageDTO {

	private int startPage;
	private int endPage;

	private boolean next;
	private boolean prev;
	private Criteria cri; // pageNum, amount
	
	public PageDTO(Criteria cri, int totalCount) {
		this.cri = cri;
//		endPage = ((cri.getPageNum()/10)+1)*10; 10 에서 문제 발생 올림 해줘야함(ceil)
		endPage = (int)Math.ceil(cri.getPageNum()/10.0)* 10;
		
		startPage = endPage - 9;
		int realEndPage = (int)Math.ceil(totalCount/cri.getAmount())+1;
		
		if(endPage > realEndPage ) {
			this.endPage = realEndPage;
		}
		
		prev = startPage > 1;
		next = endPage < realEndPage;
	}
}
```

get.jsp

```java
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
       		
			<form id='operForm' action="<c:url value='/board/modify' />" method="get">
				<!-- modify 의 경우 파라미터로 bno 가 필요하므로 보이지 않는 hidden 을 이용해 처리 -->
				<input type="hidden" id='bno' name='bno' value='<c:out value="${board.bno }" />'>
 				<input type="hidden" name="pageNum" value="${cri.pageNum }"/>
	   			<input type="hidden" name="amount" value="${cri.amount }"/>
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
		operForm.attr("action", "<c:url value='/board/modify' />").submit();
	});
	
	$("button[data-oper='list']").on("click", function(e){
		// form 의 input 에서 bno 를 받아 파라미터로 같이 보내는데 
		// 전체 리스트로 갈 경우 필요 없으므로 파라미터에서 삭제
		operForm.find("#bno").remove();
		operForm.attr("action", "<c:url value='/board/list' />")
		operForm.submit();
	});
});

</script>
<%@include file="../includes/footer.jsp" %>
```

list.jsp

```java
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
	                        <th>번호</th>
	                        <th>제목</th>
	                        <th>작성자</th>
	                        <th>작성일</th>
	                        <th>수정일</th>
	                    </tr>
	                </thead>

					<c:forEach items="${list }" var="board">
						<tr>
							<td><c:out value="${board.bno }" /></td>
							<td><a href="<c:url value='/board/get?bno=${board.bno }&pageNum=${pageMaker.cri.pageNum }&amount=${pageMaker.cri.amount }' />" />
							<c:out value="${board.title }"/></a></td>
							<td><c:out value="${board.writer }" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate }" /></td>					
							<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate }" /></td>					
						</tr>
					</c:forEach>
	            </table>
	            
              	<form id='actionForm' action="/board/list" method='get'>
            		<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum }'/>
            		<input type='hidden' name='amount' value='${pageMaker.cri.amount}'/>
            	</form>
	            
	            <div class='pull-right'>
            		<ul class="pagination">
            			<c:if test="${pageMaker.prev }">
            				<li class="paginate_button previous"><a href="${pageMaker.startPage -1 }">Previous</a></li>
           				</c:if>
 
			            <c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
			            	<li class="paginate_button ${pageMaker.cri.pageNum == num ? 'active' : ''} ">
			            		<a href="${num}">${num}</a>
			            	</li>
			            </c:forEach>
	            
            			<c:if test="${pageMaker.next }">
            				<li class="paginate_button next">
            					<a href="<c:out value='${pageMaker.endPage + 1 }'/>">Next</a>
            				</li>
           				</c:if>
           			</ul>
            	</div>
            	
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
	var actionForm = $("#actionForm")	
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
	
	$(".paginate_button a").on("click", function(e){
		e.preventDefault();
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
	});
});

</script>
<%@include file="../includes/footer.jsp" %>
```

modify.jsp

```java
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
			<form action="/board/modify" method="post">
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
				<input type='hidden' name='pageNum' value='${cri.pageNum }'/>
           		<input type='hidden' name='amount' value='${cri.amount}'/> 
 
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
		
		if(operation === 'remove'){
			formObj.attr("action", "/board/remove");
			// form 에 있는 내용 다 전송하는 것이 비효율적인것 같으나 일단 보류함
		}else if (operation === 'list'){
			formObj.attr("action", "/board/list").attr("method", "get");
			// form 태그 내의 내용 삭제 후 원하는 태그만 추가하는 작업
			var pageNumTag = $("input[name='pageNum']").clone();
			var amountTag = $("input[name='amount']").clone();
			formObj.empty();
			formObj.append(pageNumTag);
			formObj.append(amountTag);
 		}
		formObj.submit();
	});
});

</script>

<%@include file="../includes/footer.jsp" %>
```

총 페이지 추가

BoardMapper.java

```java
public int getTotalContent(Criteria cri);
```

SQL Developer

```java
SELECT /*+ INDEX (tbl_board pk_board) */count(bno) FROM tbl_board WHERE bno>0;
```

BoardMapper.xml

```java
<select id="getTotalContent" resultType="int">
	SELECT /*+ INDEX (tbl_board pk_board) */count(bno) FROM tbl_board WHERE bno>0
</select>
```

BoardService.java

```java
public int getTotalContent(Criteria cri);
```

BoardServieImpl.java

```java
@Override
public int getTotalContent(Criteria cri) {
	log.info("[getTotalContent]");
	return mapper.getTotalContent(cri);
}
```

BoardController.java

```java
@GetMapping("/list")
public void list(@ModelAttribute("cri") Criteria cri, Model model) {
	List<BoardVO> list = service.getList(cri);
	int totalContent = service.getTotalContent(cri);
	model.addAttribute("pageMaker", new PageDTO(cri, totalContent));
	log.info(new PageDTO(cri, totalContent));
	model.addAttribute("list", list);
	log.info("[list]");
}
```

