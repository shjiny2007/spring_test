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

 
<h2>패스워드 변경</h2>
<div class="content">

<c:choose>
	<c:when test="${pflag==false }">기존의 패스워드가 맞지 않습니다.<br></c:when>
	<c:when test="${flag }">패스워드를 변경했습니다.<br></c:when>
	<c:otherwise><br></c:otherwise>
</c:choose>

</div> 

  
  <DIV class='bottom'>
    <input type='button' value='다시시도' onclick="history.back()">
    <input type='button' value='확인' onclick="read()">
  </DIV>

 

</body>
<!-- *********************************************** -->
</html> 

