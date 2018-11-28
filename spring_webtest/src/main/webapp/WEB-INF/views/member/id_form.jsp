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
<link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>
 
<DIV class="title">ID <br>중복확인</DIV>
 
<FORM name='frm' method='POST' action='./id_proc.jsp'>
<div class="content">ID를 입력해주세요.</div>
  <TABLE>
    <TR>
      <TH>아이디</TH>
      <TD><input type="text" name="id" size="20"></TD>
    </TR>
  </TABLE>
  
  <DIV class='bottom'>
    <input type='submit' value='중복확인'>
    <input type='button' value='취소' onclick="window.close()">
  </DIV>
</FORM>
 
 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html> 

