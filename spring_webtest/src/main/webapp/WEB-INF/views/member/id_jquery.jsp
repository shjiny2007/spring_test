<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/SSI/ssi.jsp"%> 


<%
	boolean flag = (Boolean) request.getAttribute("flag");

	if (flag) {
		out.print("중복되어서 사용할 수 없습니다.");
	} else {
		out.print("사용이 가능합니다.");
	}
%>


