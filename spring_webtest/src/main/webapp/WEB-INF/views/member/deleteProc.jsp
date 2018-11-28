<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/SSI/ssi.jsp"%>

<%
	//boolean flag=(Boolean)request.getAttribute("flag");
%> 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 20px; 
} 
</style> 

</head> 
<!-- *********************************************** -->
<body>

 
<div class="container">
<h2>회원 탈퇴 처리</h2>
<c:choose>
	<c:when test="${flag }">탈퇴되었습니다. 자동 로그아웃됩니다.</c:when>
	<c:otherwise>탈퇴가 실패되었습니다.</c:otherwise>
</c:choose>

<br><br>

    <input type='button' value='홈' onclick="location.href='../index.jsp'">
    <input type='button' value='다시시도' onclick="history.back()">

 </div>
</body>
<!-- *********************************************** -->
</html> 

