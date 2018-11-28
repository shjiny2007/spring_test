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
div{
text-align:center;
}
</style> 
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head> 
<!-- *********************************************** -->
<body>


<DIV class="title">이미지 등록</DIV>
 
<FORM name='frm' method='POST' action='./create'

enctype="multipart/form-data">
  <TABLE  class="table table-striped">
    <TR>
      <TH>제목</TH>
      <TD><input type="text" name="title" ></TD>
    </TR>
    <TR>
      <TH>이름</TH>
      <TD><input type="text" name="name" ></TD>
    </TR>
    <TR>
      <TH>패스워드</TH>
      <TD><input type="password" name="passwd" ></TD>
    </TR>
    <TR>
      <TH>사진</TH>
      <TD><input type="file" name="filenameMF" ></TD>
    </TR>
  </TABLE>
  
  <DIV class='bottom'>
    <input type='submit' value='등록'>
    <input type='button' value='취소' onclick="history.back()">
  </DIV>
</FORM>
 

</body>

</html> 