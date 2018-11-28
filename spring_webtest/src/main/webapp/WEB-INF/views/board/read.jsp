<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.rlist{
line-height : 1.2em;
font-size : 15px;
font-weight : bold;
text-align: center;
border : 1px solid #AAAAAA;
width : 55%;
padding : 10px;
margin : 20px auto;
}
.rcreate{
font-size : 18px;
font-weight:bold;
text-align: left;
border : 1px solid #AAAAAA;
width : 55%;
padding : 10px;
margin : 20px auto;
}
</style>
<script type="text/javascript">

function del(){
	var url = "delete";
	url += "?col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";
	url += "&num=${dto.num}";
	url += "&oldfile=${dto.filename}";
	
	location.href=url;
}

function fileDown(){
	var url = "${pageContext.request.contextPath}/download";
	
	url += "?filename=${dto.filename}";
	url += "&dir=/storage";
	
	location.href=url;
}

function replyb(){
	var url = "reply";
	url += "?col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";
	url += "&num=${dto.num}";
	
	location.href=url;
}
function updateb(){
	var url = "update";
	url += "?col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";
	url += "&num=${dto.num}";
	
	location.href=url;
}
function listb(){
	var url = "list";
	url += "?col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";

	
	location.href=url;
}

function input(f){
	if('${sessionScope.id}'==''){
		if(confirm("로그인이 필요합니다.")){
			var url="../member/login";
			url += "?num=${dto.num}";
			url += "&nPage=${nPage}";
			url += "&col=${param.col}";
			url += "&word=${param.word}";
			url += "&nowPage=${param.nowPage}";
			url += "&flag=../board/read";
			location.href=url;
			
			return false;
		}else{
			return false;
		}
	}else if(f.content.value==''){
		alert("댓글 내용을 입력하세요");
		f.content.focus();
		return false;
	}
}

function rupdate(rnum,content){
	var f = document.rform;
	f.content.value=content;
	f.rnum.value=rnum;
	f.rbutton.value='수정';
	f.action="./rupdate";
	
}

function rdelete(rnum){
	if (confirm("정말 삭제하겠습니까?")){
		var url = "./rdelete";
		url += "?rnum="+rnum;
		url += "&num=${dto.num}";
		url += "&nowPage=${param.nowPage}";
		url += "&col=${param.col}";
		url += "&word=${param.wrord}";
		url += "&nPage=${nPage}";
		
		location.href=url;
	}
	
}
</script>
</head>
<body>
<div class="container">
  <h2>조회</h2>
  <table class="table">
   
      <tr>
        <th>성명</th>
        <td>${dto.name}</td>
       </tr>
    
      <tr>
        <th>제목</th>
        <td>${dto.subject}</td>
      </tr>
      <tr>
        <th>내용</th>
        <td>${content }</td>
      </tr>
      <tr>
        <th>파일</th>
        <td>
        
        <c:choose>
         <c:when test="${empty dto.filename }">파일없음</c:when>
         <c:otherwise>
          <a href="javascript:fileDown()">${dto.filename}(${dto.filesize})</a>           
         </c:otherwise>
        </c:choose>  
        
        </td>
      </tr>
      <tr>
        <th>등록일</th>
        <td>${dto.regdate}</td>
      </tr>
      <tr>
        <th>조회수</th>
        <td>${dto.count}</td>
      </tr>
      <tr>
        <th>아이피</th>
        <td>${dto.ip}</td>
      </tr>

  </table>
  <button onclick="listb()">목록</button>
  <button onclick="updateb()">수정</button>
  <button onclick="del()">삭제</button>
  <button onclick="replyb()">답변</button>
</div>

<hr>
<c:forEach var="rdto" items="${rlist }">
  
  <div class=rlist style=text-align:center;>
  	${rdto.id }<br>
  	<p>${rdto.content }</p>
  	${rdto.regdate }
  	<c:if test="${sessionScope.id==rdto.id }">
  	<span style="float:right">
  		<a href="javascript:rupdate('${rdto.rnum }','${rdto.content }')">수정</a> | 
  		<a href="javascript:rdelete('${rdto.rnum }')">삭제</a>	
  	</span>		
  	</c:if>
  </div>
  
  </c:forEach>
  <div style="text-align:center">${paging }</div>
  
  <div class=rcreate style=text-align:center;>
  	<form name="rform" action="./rcreate" method="POST" onsubmit="return input(this)">
	<textarea rows="3" cols="34" name="content"></textarea>
	<input type="submit" name="rbutton" value="등록">
	
	<input type="hidden" name="id" value="${sessionScope.id }">
	<input type="hidden" name="num" value="${dto.num }">
	<input type="hidden" name="nPage" value="${nPage }">
	<input type="hidden" name="nowPage" value="${param.nowPage }">	
	<input type="hidden" name="col" value="${param.col }">
	<input type="hidden" name="word" value="${param.word }">		
	<input type="hidden" name="rnum" value="${0}">
  	</form>
  </div>

</body>
</html>