<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/SSI/ssi.jsp"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
* {
	font-family: gulim;
	font-size: 20px;
}
</style>
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head>

<body>


	<DIV class="title">답변</DIV>

	<FORM name='frm' method='POST' action='./reply'
		enctype="multipart/form-data" >
		<input type="hidden" name="no" value="${dto.no}"> 
		<input type="hidden" name="grpno" value="${dto.grpno}"> 
			<input type="hidden" name="indent" value="${dto.indent}"> 
			<input type="hidden" name="ansnum" value="${dto.ansnum}">       
		<TABLE class="table table-striped">
			<TR>
				<TH>성명</TH>
				<TD><input type="text" name="name"></TD>
			</TR>
			<TR>
				<TH>제목</TH>
				<TD><input type="text" name="title" value="${dto.title}"></TD>
			</TR>
			<TR>
				<TH>사진파일</TH>
				<TD><input type="file" name="filenameMF" accept=".jpg,.png,.gif"></TD>
			</TR>
			<TR>
				<TH>비밀번호</TH>
				<TD><input type="password" name="passwd"></TD>
			</TR>
		</TABLE>

		<DIV class='bottom'>
			<input type='submit' value='등록'> <input type='button'
				value='목록' onclick="location.href='list'">
		</DIV>
	</FORM>

</body>
<!-- *********************************************** -->
</html>