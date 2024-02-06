<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="autenticacao.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Busca de paciente</title>
</head>
<body>
<div>
<h1>Busca Paciente</h1>
<form action="controladora" method="post">
<input type="text" name="cpf" placeholder="CPF do paciente:" />
<input type="hidden" name="logica" value="RecuperaPaciente"> 
<input type="submit" value="Buscar">
</form>
<c:import url="rodape.jsp" />
</div>


</body>
</html>