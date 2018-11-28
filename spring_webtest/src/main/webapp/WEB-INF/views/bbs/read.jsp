<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/SSI/ssi.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
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
function blist(){
	var url = "list";
	url = url + "?col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	
	location.href = url;
	
}
function bupdate(){
	var url = "update";
	url = url + "?bbsno=${dto.bbsno}";
	url = url + "&col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	location.href = url;
}
function bdel(){
	var url = "delete";
	url = url + "?bbsno=${dto.bbsno}";
	url = url + "&col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	url = url + "&oldfile=${dto.filename}";
	location.href = url;
}
function breply(){
	var url = "reply";
	url = url + "?bbsno=${dto.bbsno}";
	url = url + "&col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	location.href = url;
}
function fileDown(filename){
	 var url = "${root}/download";
	 url = url + "?filename=${dto.filename}";
	 url = url + "&dir=bbs/storage";
	 
	 location.href = url;
	 
}

function input(f){
	if('${sessionScope.id}'==''){
		if(confirm("로그인이 필요합니다.")){
			var url="../member/login";
			url += "?bbsno=${dto.bbsno}";
			url += "&nPage=${nPage}";
			url += "&col=${param.col}";
			url += "&word=${param.word}";
			url += "&nowPage=${param.nowPage}";
			url += "&flag=../bbs/read";
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
		url += "&bbsno=${dto.bbsno}";
		url += "&nowPage=${param.nowPage}";
		url += "&col=${param.col}";
		url += "&word=${param.wrord}";
		url += "&nPage=${nPage}";
		
		location.href=url;
	}
	
}
</script>
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 20px; 
} 
</style> 
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head> 
<!-- *********************************************** -->
<body>

 <div class="container">
<DIV class="title"><h2>조회</h2></DIV>
 
  <TABLE class="table" style="width: 60%; padding:20px;">
    <TR>
      <th>제목</th>
      <td>${dto.title}</td>
     </tr>
     <tr>
      <th>성명</th>
      <td>${dto.wname}</td>
     </tr>
     <tr>
     	<th>글내용</th>
     	<td colspan="2">${dto.content}</td>
     </tr>
     <tr>
      <th>조회수</th>
      <td>${dto.viewcnt}</td>
     </tr>
     <tr>
      <th>등록일</th>
      <td>${dto.wdate}</td>
    </TR>
     <tr>
      <th>파일</th>
      <td>
      <c:choose>
      <c:when test="${not empty dto.filename}">
       <a href="javascript:fileDown()">
          ${dto.filename}
        </a>
        (${(dto.filesize/1024)} KB)
      </c:when>
      <c:otherwise>파일없음</c:otherwise>
      </c:choose>
      </td>
    </TR>
  </TABLE>
  
  <DIV class='bottom'>
    <input type='submit' value='목록' onclick="blist()">
    <input type='button' value='등록' onclick="location.href='create'">
    <button onclick="bupdate()">수정</button>
    <button onclick="bdel()">삭제</button>
    <button onclick="breply()">답변</button>
  </DIV>

</div>

<hr>
<c:forEach var="rdto" items="${rlist}">
  
  <div class=rlist>
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
  
  <div class=rcreate>
  	<form name="rform" action="./rcreate" method="POST" onsubmit="return input(this)">
	<textarea rows="3" cols="34" name="content"></textarea>
	<input type="submit" name="rbutton" value="등록">
	
	<input type="hidden" name="id" value="${sessionScope.id}">
	<input type="hidden" name="bbsno" value="${dto.bbsno}">
	<input type="hidden" name="nPage" value="${nPage}">
	<input type="hidden" name="nowPage" value="${param.nowPage}">	
	<input type="hidden" name="col" value="${param.col}">
	<input type="hidden" name="word" value="${param.word}">		
	<input type="hidden" name="rnum" value="${0}">
  	</form>
  </div>

</body>

</html> 
