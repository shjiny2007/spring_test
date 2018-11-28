<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/SSI/ssi.jsp"%>
<%
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
* {
	font-family: gulim;
	font-size: 20px;
}

div {
	text-align: center;
	margin-top: 20px;
	margin-bottom: 20px;
}
</style>
<script>
	function mlist(){
		var url = "list.do"
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
 <c:when test="${flag}">
 답글이 존재합니다.<br>삭제할 수 없습니다.<br><br><br>
 <input type='button' value='목록' onclick="mlist()"></c:when>
 <c:otherwise>
 <form method="post" action="delete">
		<input type="hidden" name="memono" value="${param.memono}"> 
		<input type="hidden" name="col" value="${param.col}">
		<input type="hidden" name="word" value="${param.word}"> 
		<input type="hidden" name="nowPage" value="${param.nowPage}">
		<div>
			삭제를 하면 복구될 수 없습니다. <br>
			<br> 삭제하시려면 삭제버튼을 클릭하세요<br>
			<br> 취소는 '목록'버튼을 누르세요 <br>
			<br> <input type="submit" value="삭제"> <input
				type="button" value="목록" onclick="location.href='./list.jsp'">
		</div>
	</form>
 </c:otherwise>
 </c:choose>

	</div>
	</body>
</html>
