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
.search{
	width:80%;
	text-align: center;
	margin: 2px auto;
}

</style> 
<script type="text/javascript">
 function read(bbsno){
	 var url = "read.do";
	 url = url + "?bbsno=" + bbsno;
	 url = url + "&col=${col}";
	 url = url + "&word=${word}";
	 url = url + "&nowPage=${nowPage}";
	 
	 location.href = url ;
 }
 function fileDown(filename){
	 var url = "${root}/download";
	 url = url + "?filename=" + filename;
	 url = url + "&dir=bbs/storage";
	 
	 location.href = url;
	 
 }
</script>
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head> 

<body>

 


<div class="search">
<form method="post" action="./list">

<select name="col">

<option value="wname" 
<c:if test="${col=='wname'}">selected</c:if>
>성명</option>
<option value="title"
<c:if test="${col=='title'}">selected</c:if>
>제목</option>
<option value="content"
<c:if test="${col=='content'}">selected</c:if>
>내용</option>
<option value="total">전체출력</option>

</select>
<input type="text" name="word" value="${word}">
<button>검색</button>
<button type="button" onclick="location.href='create'">등록</button>
</form>
</div>

<div class="container">
<h2>
<span class="glyphicon glyphicon-th-list"></span>
목록</h2>

  <TABLE class="table table-hover">
  <thead>
    <TR>
      <TH>번호</TH>
      <th>제목</th>
      <th>성명</th>
      <th>조회수</th>
      <th>등록일</th>
      <th>파일명</th>
          
    </TR>
    </thead>
    
     <c:choose>
    <c:when test="${empty list}"> 
<tbody>
	<tr>
		<td colspan="6">등록된 글이 없습니다.</td>
	</tr>
  </tbody>
  </c:when>
  
  <c:otherwise>
  <c:forEach var="dto" items="${list}">
   <tbody>
    <tr>
    	<td>${dto.bbsno}</td>
    	<td>
    	
    	<c:forEach begin="1" end="${dto.indent}">&nbsp;&nbsp;</c:forEach>
    	<c:if test="${dto.indent>0}"><img src='${root}/images/re.jpg' style=width:15px;height:15px;></c:if>
    	<c:set var="rcount" value="${util:rcount(dto.bbsno,rdao) }"/>
    	<a href="javascript:read('${dto.bbsno}')">${dto.title}</a>
    	
    	<c:if test="${rcount>0 }">
            <span style="color:red;">(${rcount})</span>
          </c:if>
    	<c:if test="${util:newImg(dto.wdate)}"><img src='${root}/images/new.gif' style=width:15px;height:15px;></c:if>
    	
    	</td>
    	<td>${dto.wname}</td>
    	<td>${dto.viewcnt}</td>
    	<td>${dto.wdate}</td>
    	<td>
    	<c:choose>
    	<c:when test="${not empty dto.filename}">
    		<a href="javascript:fileDown('${dto.filename}')">${dto.filename}</a>
    		</c:when>
    		<c:otherwise>파일없음</c:otherwise>
    		</c:choose>
    	
    	</td>
    </tr>
  </tbody>
</c:forEach>
        </c:otherwise>
        </c:choose>
  </TABLE>
  
${paging} 

</div>

</body>

</html> 
