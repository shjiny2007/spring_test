<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/SSI/ssi.jsp" %> 

<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<script type ="text/javascript">
function treply(){
	var url = "reply";
	url = url+"?no=${dto.no}";
	url = url + "&col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	location.href = url;
}
function tlist(){
	var url = "list";
	url = url + "?col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	
	location.href=url;
}
</script>
</head> 
<body> 
<div class="container">
<h2>조회</h2>
<table class="table">
	<tr>
		<th>이름</th>
		<td>${dto.name}</td>
	</tr>
	<tr>
		<th>성별</th>
		<td>${dto.gender}</td>
	</tr>
	<tr>
		<th>보유기술</th>
		<td>${dto.skills}</td>
	</tr>
	<tr>
		<th>취미</th>
		<td>${dto.hobby}</td>
	</tr>
	<tr>
		<th>전화번호</th>
		<td>${dto.phone}</td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td>${dto.zipcode}</td>
	</tr>
	<tr>
		<th>주소</th>
		<td>${dto.address}<br>
		${dto.address2}</td>
	</tr>
</table>
<div>
	<button type="button" onclick="tlist()">목록</button>
	<button onclick="treply()">답변</button>
						
</div>
</div>
</body> 
</html> 