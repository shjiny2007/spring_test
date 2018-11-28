<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/SSI/ssi.jsp"%> > 
<%
	//String id=request.getParameter("id");
%>
 
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
<script type="text/javascript">
function inCheck(f){
	if(f.passwd.value==""){
		alert("비밀번호를 입력하세요.");
		f.passwd.focus();
		return false;
	}
	if(f.repasswd.value==""){
		alert("비밀번호 확인을 입력하세요.");
		f.repasswd.focus();
		return false;
	}
	if(f.passwd.value!=f.repasswd.value){
		alert("비밀번호가 일치하지 않습니다.");
		f.passwd.focus();
		return false;
	}
}
</script>

</head> 
<!-- *********************************************** -->
<body>
<div class="container">
 
<h2>비밀번호 변경</h2>
<div style="margin: 10px auto">

<FORM name='frm' method='POST' action='./updatePwProc' onsubmit="return inCheck(this)">
<input type="hidden" name="id" value="${param.id}">

  
<table>
  <tr>
  	<th>기존비밀번호 입력</th>
  	<td><input type="password" name="oldpasswd"></td> 
  </tr>
</table>
</div>
 

<br>
<br>
  
  <TABLE>
    <TR>
      <TH>패스워드</TH>
      <TD><input type="password" name="neww"></TD>
    </TR>
    <TR>
      <TH>패스워드확인</TH>
      <TD><input type="password" name="repasswd"></TD>
    </TR>
  </TABLE>

  
  <DIV class='bottom'>
    <input type='submit' value='수정'>
    <input type='button' value='취소' onclick="history.back()">
  </DIV>

</FORM>
</div> 

</body>
<!-- *********************************************** -->
</html> 

