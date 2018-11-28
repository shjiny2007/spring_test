<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
div{
text-align:center;
}
</style>
<script type="text/javascript">
	function incheck(f) {
		if (f.title.value == "") {
			alert("제목을 입력하세요");
			f.title.focus;
			return false;
		}
		if (f.content.value == "") {
			alert("내용을 입력하세요");
			f.content.focus();
			return false;
		}
	}
</script>
</head>
<body>

<div class="container"></div>
	<span class="glyphicon glyphicon-pencil"></span>
	
	<h2>등록</h2>
	<form name="frm" method="POST" action="./create"
		onsubmit="return incheck(this)">
		<table class= table table - border>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" size="30" value="두시회의" /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="30" name="content">SQL 관련</textarea>
				</td>
			</tr>
		</table>
		<div>
			<input type="submit" value="전송" >
		</div>
	</form>
	</tbody>

</body>
</html>
