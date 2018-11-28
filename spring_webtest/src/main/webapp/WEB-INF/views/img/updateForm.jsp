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
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
 
<DIV class="title">내용 수정</DIV>
 
<FORM name='frm' method='POST' action='./update'
enctype="multipart/form-data">

<input type="hidden" name="oldfile" value="${param.oldfile}">
<input type="hidden" name="no" value="${param.no}">

  <TABLE class="table table-striped">
     
     <TR>
      <Td colspan="2" style="text-align: center">
      <img src="./storage/${param.oldfile}" width="250px" height="250px">
      </Td>
    </TR>
    <TR>
      <TH>제목</TH>
      <TD><input type="text" name="title" value="${dto.title}"></TD>
    </TR>
    <TR>
      <TH>이름</TH>
      <TD><input type="text" name="name" value="${dto.name}"></TD>
    </TR>
    <TR>
      <TH>파일</TH>
      <TD><input type="file" name="filenameMF"></TD>
    </TR>
    <TR>
      <TH>비밀번호</TH>
      <TD><input type="password" name="passwd"></TD>
    </TR>
  </TABLE>
  
  <DIV class='bottom'>
    <input type='submit' value='수정'>
    <input type='button' value='취소' onclick="history.back()">
  </DIV>
</FORM>

</body>
<!-- *********************************************** -->
</html> 
