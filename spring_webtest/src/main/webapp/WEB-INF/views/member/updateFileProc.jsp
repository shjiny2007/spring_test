<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/SSI/ssi.jsp"%>  


<%
	boolean flag=(Boolean)request.getAttribute("flag");
	String id=(String)request.getAttribute("id");
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
<script type="text/javascript">
function read(){
	var url="read.do";
	url=url+"?id=${id}";
	location.href=url;
}

</script>
</head> 
<!-- *********************************************** -->
<body>
<div class="container">
 
<h2>사진변경</h2>
<c:choose>
	<c:when test="${flag }">진변경을 했습니다<br></c:when>
	<c:otherwise>사진변경을 실패했습니다.<br></c:otherwise>
</c:choose>

  
  <DIV class='bottom'>

<c:choose>
<c:when test="${flag }">
    <input type='button' value='나의 정보' onclick="read()">
</c:when>

<c:otherwise>
    <input type='button' value='다시시도' onclick="history.back()">
</c:otherwise>
</c:choose>
 
  </DIV>

 
 
</body>
<!-- *********************************************** -->
</html> 

