<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="Cadastro" method="post">
	     Login: <input type="text"  	name="login" size=15 > 	<br><br>
	     Senha:	<input  type="password" name=senha	 size=15 ><br><br>	    
	     <input type='submit' value="Enviar">
	</form>
<%
		String ok = (String) request.getAttribute("ok");
		if(ok!=null && !ok.trim().equals("")){
			out.print(ok);
		}
	
		String erro = (String) request.getAttribute("erro");
		if(erro!=null && !erro.trim().equals("")){
			out.print(erro);
		}
	%>		
</body>
</html>
