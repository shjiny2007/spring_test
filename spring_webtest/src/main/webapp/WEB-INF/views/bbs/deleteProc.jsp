<%@ page contentType="text/html; charset=UTF-8"%>

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
</style>
<script type="text/javascript">
function blist(){
	var url = "list";
	url = url + "?col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	location.href = url;
}
</script>
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head>

<body>

	<div class="container">
	
		<h2>처리결과</h2>

		<div class="content">
		
		<c:choose>
		<c:when test="${pflag==false}">비밀번호가 일치하지 않습니다.<br><br></c:when>
		<c:when test="${flag}">글을 삭제했습니다<br><br></c:when>
		<c:otherwise>글 삭제를 실패했습니다.<br><br></c:otherwise>
		</c:choose>

		</div>
		
		<c:choose>
		<c:when test="${pflag==false || flag==false}">
		<input type='button' value='다시시도' onclick="history.back()"> <input
			type='button' value='목록' onclick="blist()">
		</c:when>
		<c:otherwise>
		<input type='button' value='목록' onclick="blist()">
		</c:otherwise>
		</c:choose>

	</div>
</body>

</html>
