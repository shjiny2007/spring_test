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
<script type="text/javascript">
function mread(){
	var url="read";
	url=url+"?id=${param.id}";
	location.href=url;
}
</script> 

</head> 
<!-- *********************************************** -->
<body>

 
 <div class="container">
<h2>정보 수정</h2>

<c:choose>
	<c:when test="${flag }">정보 수정에 성공했습니다.<br></c:when>
	<c:otherwise>정보 수정이 실패했습니다.<br></c:otherwise>
</c:choose>

 </div>
<br><br>

    <input type='button' value='정보확인' onclick="mread()">
    <input type='button' value='다시시도' onclick="history.back()">

</body>
<!-- *********************************************** -->
</html> 

