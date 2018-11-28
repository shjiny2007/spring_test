<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/SSI/ssi.jsp"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<script type="text/javascript">

	function read(memono) {
		//alert("memono");

		var url = "./read";
		url = url + "?memono=" + memono;
		url = url + "&col=${col}";
		url = url + "&word=${word}";
		url = url + "&nowPage=${nowPage}";

		location.href = url;

	}
</script>
<style type="text/css">
* {
	font-family: gulim;
	font-size: 20px;
}

/*
div {
	text-align: center;
	margin-top: 20px;
	margin-bottom: 20px;
}

table {
	width: 60%;
	margin: 0 auto;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 10px;
}
*/
.search {
	text-align: center;
	margin: 5px auto;
}
</style>
</head>
<body>

	<div class="search">

		<form action="list" method="post">
			<select name="col">
				<option value="title" <c:if test="${col=='title'}">selected</c:if>>제목</option>
				<option value="content">
					<c:if test="${col=='content'}">selected</c:if>내용
				</option>
				<option value="total">전체출력</option>
			</select> <input type="text" name="word" value="${word}">
			<button>검색</button>
			<button type="button" onclick="location.href='create'">등록</button>
		</form>
	</div>
	<div class="container">
		<h2>
			<span class="glyphicon glyphicon-th-list"></span> 메모목록
		</h2>
		<table class="table table-hover">
			<thread>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>날짜</th>
				<th>조회수</th>
				<th>grpno</th>
				<th>indent</th>
				<th>ansnum</th>
			</thread>
			</tr>

			<c:choose>
				<c:when test="${empty list}">
					<tbody>
						<tr>
							<td colspan="7">등록된 메모가 없습니다.</td>
						</tr>
					</tbody>
				</c:when>

				<c:otherwise>
					<c:forEach var="dto" items="${list}">
						<tbody>
							<tr>
								<td>${dto.getMemono()}</td>
								<td>
								<c:forEach begin="1" end="${dto.indent}">&nbsp;&nbsp;</c:forEach>
									<c:if test="${dto.indent>0 }">
										<img src='../images/re.jpg' style=width:30px; height=25px;>
									</c:if> <a href="javascript:read('${dto.memono}')">${dto.title}</a></td>
								<td>${dto.getWdate()}</td>
								<td>${dto.getViewcnt()}</td>
								<td>${dto.getGrpno()}</td>
								<td>${dto.getIndent()}</td>
								<td>${dto.getAnsnum()}</td>
						</tbody>

					</c:forEach>
				</c:otherwise>
			</c:choose>

		</table>
		<div>${paging}</div>
	</div>

</body>
</html>