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
<script type="text/javascript">
function updateFile(){
	var url="updateFile";
	url=url+"?id=${dto.id}";
	url=url+"&oldfile=${dto.fname}";
	location.href=url;
}
function updatePwForm(){
	var url="updatePwForm";
	url=url+"?id=${dto.id}";
	location.href=url;
}
function mupdate(){
	var url="update";
	url=url+"?id=${dto.id}";
	url=url+"&col=${param.col}"
	url=url+"&word=${param.word}"
	url=url+"&nowPage=${param.nowPage}"
	
	location.href=url;
}
function mdel(){
	var url="delete";
	url=url+"?id=${dto.id}";
	url=url+"&col=${param.col}"
	url=url+"&word=${param.word}"
	url=url+"&nowPage=${param.nowPage}"
	location.href=url;
}
</script>


</head> 
<!-- *********************************************** -->
<body>

 <div class="container">
<h2>${dto.mname}의 회원정보</h2>
<table class="table">
    <TR>
    <td colspan="2" style="text-align:center" >
    <img src="${root}/member/storage/${dto.fname}" style='width:350px; height:350px'></td>
    </tr>
    
    <tr>
      <TH>아이디</TH>
      <TD>${dto.id }</TD>
    </TR>
    <tr>
      <TH>성명</TH>
      <TD>${dto.mname }</TD>
    </TR>
    <tr>
      <TH>전화번호</TH>
      <TD>${dto.tel}</TD>
    </TR>
    <tr>
      <TH>이메일</TH>
      <TD>${dto.email }</TD>
    </TR>
    <tr>
      <TH>우편번호</TH>
      <TD>${dto.zipcode }</TD>
    </TR>
    <tr>
      <TH>주소</TH>
      <TD>${dto.address1}
      		${dto.address2 }
      </TD>
    </TR>
    <tr>
      <TH>직업</TH>
      <TD>직업코드 :${dto.job}
      			(${util:jobName(dto.job)})



      </TD>
    </TR>
    <tr>
      <TH>날짜</TH>
      <TD>${dto.mdate }</TD>
    </TR>
</TABLE>
  
  <DIV class='bottom'>
    <input type='button' value='정보수정' onclick="mupdate()">
    <input type='button' value='회원탈퇴' onclick="mdel()">
    <c:if test = "${not empty sessionScope.id && sessionScope.grade != 'A'}">
  	<input type='button' value='회원목록' onclick="history.back()">
    <input type='button' value='사진수정' onclick="updateFile()">
    <input type='button' value='패스워드 변경' onclick="updatePwForm()">
    </c:if>
  </DIV>

 <!-- location.href='../admin/list.jsp' -->
</div>
</body>
<!-- *********************************************** -->
</html> 

