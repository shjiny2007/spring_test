<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/SSI/ssi.jsp"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title></title> 
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 24px; 
} 
.bottom{
margin: 20px auto;
  text-align: center;
  width: 100%;
  padding: 10px;  /* 위 오른쪽 아래 왼쪽 */
}
</style> 
<script type="text/javascript">
function read(){
	var url="read";
	url+="?no=${param.no}";
	location.href=url;
}

function ilist(){
	var url = "list"
	url += "?col=${param.col}"
	url += "&col=${param.word}"
	url += "&col=${param.nowPage}"

	location.href = url;
}
</script>
</head> 
<body> 


<div class="container">
<h2>삭제</h2>

<c:choose>
<c:when test="${flag}">답글이 존재합니다<br>삭제할 수 없는 글입니다.
<button type='button' onclick="ilist()">목록</button></c:when>
<c:otherwise>삭제시 복구할 수 없습니다.<br> 삭제를 원하시면 아래 패스워드를 입력하시고<br>삭제 버튼을 눌러주세요.
<form name="frm" method="post" action="./delete" >

<input type="hidden" name="no" value="${param.no}">

<table class="table table-striped">
<tr>
<th>비밀번호</th>
<td><input type="password" name="passwd">

<button type = "submit">삭제</button>
<button type="button" onclick="history.back()">취소</button>
<button type='button' onclick="ilist()">목록</button>

</td>
</tr>
</table>

</form>
</c:otherwise>
</c:choose>

</div>


</body> 
</html> 