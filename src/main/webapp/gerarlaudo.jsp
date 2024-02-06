<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="autenticacao.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Laudo do Exame</title>
</head>
<body>
	<div>
		<h1>Laudo do Exame</h1>
		<h2>Exame </h2>
		<p>${requestScope.exame.exame}</p>
		
		<h2>Informações do paciente</h2>
		<p>${requestScope.exame.paciente.cpf}</p>
		<p>${requestScope.exame.paciente.nome}</p>
		
		<h2>Médico solicitante</h2>
		<p>${requestScope.exame.medico.crm}</p>
		<p>${requestScope.exame.medico.nome}</p>
		
		<!-- <h2>Realizado por</h2>
		<p>${requestScope.exame.medico.crm}</p>
		<p>${requestScope.exame.medico.nome}</p> -->

		<form action="controladora" method="post">
			Exame: <input name="exame" type="text" value="${requestScope.exame.id}" readonly />
			Residente: <input name="medico" type="text" value="${sessionScope.nome}" readonly />
			<label>Descrição do laudo:</label><br>
			<textarea rows="5" cols="33" name="descricao"></textarea>
			<label>Diagnóstico:</label> 
		    <select name="diagnostico" > 
		        <option value="paradaCardiaca">Parada cardíaca</option> 
		        <option value="taquicardiaParoxistica">Taquicardia paroxística</option>
		        <option value="flutterFibrilacaoAtrial">Flutter e fibrilacao atrial</option>
		        <option value="outras">Outras arritmias cardíacas</option>
		        <option value="cardiomiopatias">Cardiomiopatias</option>  
		    </select>
			<input type="hidden" name="logica" value="GerarLaudo"> 
			<input type="submit" value="Enviar">
		</form>
		<c:import url="rodape.jsp" />
	</div>
</body>
</html>