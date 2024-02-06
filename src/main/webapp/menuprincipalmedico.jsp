<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="autenticacao.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<style type="text/css">
/* Footer (By Caren) */
#rodape {
	margin-top: 20px;
	color: #ccc; /* Light gray text */
}

p span {
	font-weight: bold;
}


</style>
<title>Menu Principal</title>
</head>
<body>

<div>
<h1>Sistema de Laudos: Médico</h1>

<nav>
  <a href="buscapaciente.jsp">Pedido de Exame</a>
  <a href="buscapaciente.jsp">Consultar laudos</a>
</nav>

<c:if test="${sessionScope.status == true}">
<a id="sair" href=controladora?logica=EfetuaLogout>Sair</a>
</c:if>

<p id="rodape">
  By <span>Caren</span>
</p>
</div>
</body>
</html>