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
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<script type="text/javascript">
function emailCheck(email){
	if(email==""){
		alert("이메일을 입력해주세요.");
		document.frm.email.focus();
	}else{
		var url="emailCheck";
		$.ajax({ //ajax로 비동기 통신
			url:url,
			dataType:'text',
			type:"get",
			data:{"email" : email}, //파라미터 값
			success : function(data){//성공하면 data 가지고 움직인다.호출된다.
				$("#emailcheck").text(data.trim()).css("color","red"); //trim() : 빈공간 없애주는 것
				
				if(data.trim().indexOf("사용이 가능합니다.")!=-1){
				$("#emailcheck").append("<button onclick='use()'>적용</button>") 
				}
			} 
		});
	}
}

function use(){
	//alert(document.frm.email.value);
	opener.frm.email.value=document.frm.email.value;
	self.close();
}
</script>
</head> 
<!-- *********************************************** -->
<body>


<div class="container">
<h3>Email <br>중복확인</h3>
 
<FORM name='frm'>
<div>Email를 입력해주세요.</div>
  <TABLE class="talbe">
    <TR>
      <TH>Email</TH>
      <TD><input type="text" name="email" size="20">  

      </TD>
    </TR>
  </TABLE>
    <div id="emailcheck"></div>
<br>
    <input type="button" value="Email중복확인" onclick="emailCheck(document.frm.email.value)">

    <input type='button' value='취소' onclick="window.close()">

</FORM>
 
 </div>
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html> 

