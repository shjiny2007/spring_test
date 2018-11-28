<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/SSI/ssi.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">
function blist(){
	var url = "list.do";
	url = url + "?col=${col}";
	url = url + "&word=${word}";
	url = url + "&nowPage=${nowPage}";
	
	location.href = url;
}

</script>
<style type="text/css">
* {
	font-family: gulim;
	font-size: 20px;
}
</style>
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head>
<!-- *********************************************** -->
<body>

	<div class="container">
	<DIV class="title">처리 결과</DIV>

	<div class="content">
<c:choose>
<c:when test="${pflag==false}">비밀번호가 일치하지 않습니다.</c:when>
<c:when test="${flag}">글을 변경했습니다</c:when>
<c:otherwise>글 변경을 실패했습니다.</c:otherwise>
</c:choose>

	</div>

	<DIV class='bottom'>
		<input type='button' value='다시시도' onclick="history.back()"> <input
			type='button' value='목록' onclick="blist()">
	</DIV>

</div>
</body>

</html>
