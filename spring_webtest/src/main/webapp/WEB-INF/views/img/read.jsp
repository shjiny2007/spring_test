<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/SSI/ssi.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
* {
	font-family: gulim;
	font-size: 20px;
}

.curImg {
	margin-right: 0;
	border-style: solid;
	border-width: 3px;
	border-color: red;
}

.td_padding {
	padding: 5px 5px;
}

table {
	margin: 0 auto;
}
</style>

<!-- <link href="../css/style.css" rel="Stylesheet" type="text/css"> -->
<script type="text/javascript">
	function read(no) {
		var url = "./read";
		url = url + "?no=" + no;

		location.href = url;
	}
	function iupdate() {
		var url = "update";
		url = url + "?no=${dto.no}";
		url = url + "&oldfile=${dto.img}";

		location.href = url;
	}
	function ireply() {
		var url = "reply";
		url = url + "?no=${dto.no}";

		location.href = url;

	}
	function del() {
		var url = "delete";
		url = url + "?no=${dto.no}";

		location.href = url;

	}
</script>
</head>

<body>
	<div class="container">
		<h2 style="text-align: center;">
			사진 조회<br>${dto.title}</h2>

		<TABLE class="table table-striped"
			style="width: 50%; text-align: center;">
			<TR>
				<TD colspan="2" style="text-align: center;"><img
					src="./storage/${dto.img}" style="width: 200px; height: 200px;"></TD>
			</TR>
			<TR>
				<TH>제목</TH>
				<TD>${dto.title}</TD>
			</TR>
			<TR>
				<TH>작성자</TH>
				<TD>${dto.name}</TD>
			</TR>
		</TABLE>

		<TABLE class="table table-striped" style="width: 50%">
			<TR>
				<c:forEach var="i" begin="0" end="4">
					<c:choose>
						<c:when test="${noArr[i]==-1}">
							<td class="td_padding"><img src="./storage/default.jpg"
								style="width: 150px; height: 150px;"></td>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${noArr[i]==dto.no}">
									<td class="td_padding"><a
										href="javascript:read('${noArr[i]}')"> <img class="curImg"
											src="./storage/${files[i]}"
											style="width: 150px; height: 150px;"0">
									</a></td>
								</c:when>
								<c:otherwise>
									<td class="td_padding"><a
										href="javascript:read('${noArr[i]}')"> <img
											src="./storage/${files[i]}"
											style="width: 150px; height: 150px;"0">
									</a></td>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</c:forEach>

			</TR>
		</TABLE>

		<DIV class='bottom'>
			<input type='button' value='등록' onclick="location.href='create'">
			<input type='button' value='목록' onclick="location.href='list'">
			<input type='button' value='수정' onclick="iupdate()"> <input
				type='button' value='삭제' onclick="del()"> <input
				type='button' value='답변' onclick="ireply()">
		</DIV>
	</div>
</body>
<!-- *********************************************** -->
</html>

