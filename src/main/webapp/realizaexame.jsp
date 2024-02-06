<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="autenticacao.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Realiza Exame</title>
</head>
<body>
	<div>
		<h1>Realização do exame</h1>
		<h2>Exame Solicitado</h2>
		<p>${requestScope.exame.exame}</p>
		
		<h2>Médico solicitante</h2>
		<p>${requestScope.exame.medico.crm}</p>
		<p>${requestScope.exame.medico.nome}</p>

		<h2>Informações do paciente</h2>
		<p>${requestScope.exame.paciente.cpf}</p>
		<p>${requestScope.exame.paciente.nome}</p>

		<form action="controladora" method="post">
			Realizado por: <input name="residente" type="text" value="${sessionScope.nome}" readonly /> 
			Exame: <input name="exame" type="text" value="${requestScope.exame.id}" readonly /> 
			Data de realização: <input name="data"  /> 
			Hora: <input name="hora" > <br /> 
			<input type="hidden" name="logica" value="RealizaExame"> 
			<input type="submit" value="Enviar">
		</form>
		<c:import url="rodape.jsp" />
	</div>
</body>
</html>