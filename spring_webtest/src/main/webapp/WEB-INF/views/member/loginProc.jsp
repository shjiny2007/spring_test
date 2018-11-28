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
 
<h2>로그인 처리</h2>
 
<c:choose>
<c:when test="${flag}">로그인이 되었습니다.</c:when>
<c:otherwise>잘못입력하셨거나 <br>
			회원이 아닙니다. 회원가입을 진행해주세요.
</c:otherwise>
</c:choose>

  
  <DIV class='bottom'>
    <input type='button' value='홈' onclick="location.href='${root}/'">
    <input type='button' value='다시시도' onclick="history.back()">
  </DIV>
 
</div>
</body>
<!-- *********************************************** -->
</html> 

