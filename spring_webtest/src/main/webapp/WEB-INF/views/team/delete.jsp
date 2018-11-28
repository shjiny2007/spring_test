<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/SSI/ssi.jsp" %>

<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<script type = "text/javascript">
function tlist(){
	var url = "list";
	url = url + "?col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	
	location.href=url;
}
</script>
</head> 
<body> 
<div class="container">
<h2>처리결과</h2>
<div>
<c:choose>
	<c:when test="${dflag }">답변글이 있으므로 삭제할 수 없습니다.</c:when>
	<c:when test="${flag }">삭제를 하였습니다.</c:when>
	<c:otherwise>삭제를 실패했습니다.</c:otherwise>
</c:choose>

</div>

<button onclick="tlist()">목록</button>

</div>
</body> 
</html> 