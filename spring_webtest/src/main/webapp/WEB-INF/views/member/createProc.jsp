<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/SSI/ssi.jsp"%> 
 
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
<h2>회원가입 처리</h2>

<c:choose>
<c:when test="${flag }">회원가입이 성공했습니다.</c:when>
<c:otherwise>실패했습니다.</c:otherwise>
</c:choose>

<c:choose>
<c:when test="${flag }">
  <DIV class='bottom'>
    <input type='button' value='로그인' onclick="location.href='login'">
    <input type='button' value='홈' onclick="location.href='/'">
  </DIV>
</c:when>
<c:otherwise>
 <DIV class='bottom'>
    <input type='button' value='다시시도' onclick="history.back()">
    <input type='button' value='홈' onclick="location.href='/'">
  </DIV>
</c:otherwise>
</c:choose>

 
 </div>
</body>
<!-- *********************************************** -->
</html> 

