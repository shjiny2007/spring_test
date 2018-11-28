<%@ page contentType="text/html; charset=UTF-8" %> 
 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<script type="text/javascript">
function blist(){
 var url="list";
 url += "?col=${col}"
 url += "&word=${word}"
 url += "&nowPage=${nowPage}"
 location.href=url;
}
</script>
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 20px; 
} 
</style> 

<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>

</head> 

<body>

 
<DIV class="title">처리결과</DIV>
 <div class="content">
 
 <c:choose>
 <c:when test="${flag}">답변글이 등록되었습니다.</c:when>
 <c:otherwise>답변글 등록이 실패했습니다.</c:otherwise>
 </c:choose>

 </div>
  
  <DIV class='bottom'>
    <input type='button' value='목록' onclick="blist()">
    <input type='button' value='글등록' onclick="location.href='create'">
  </DIV>


</body>

</html>