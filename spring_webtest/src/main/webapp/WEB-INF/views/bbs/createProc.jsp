<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/SSI/ssi.jsp" %>

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
function blist(){
	var url = "list.do";
	location.href = url;
}
</script>

<%-- <link href="<%=root%>/css/style.css" rel="stylesheet" type="text/css"> --%>
</head> 

<body>

<DIV class="title">처리결과</DIV>
 
<DIV class="content">

<c:choose>
<c:when test="${flag}">글이 등록되었습니다.</c:when>
<c:otherwise>글 등록을 실패했습니다.</c:otherwise>
</c:choose>

</DIV>
  
  <DIV class='bottom'>
    <input type='button' value='목록' onclick="blist()">
    <input type='button' value='계속 등록' onclick="location.href='./create.do'">
  </DIV>
 
</body>

</html> 
