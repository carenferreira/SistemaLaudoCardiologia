<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="autenticacao.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Avaliar Laudo</title>
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
		    Exame: <input name="exame" type="text" value="${requestScope.laudo.idExame}" readonly />
		    Residente: <input name="medico" type="text" value="${requestScope.laudo.medico.getCrm()}" readonly />
		    <label>Descrição do laudo:</label><br>
		    <textarea rows="5" cols="33" name="descricao">${requestScope.laudo.laudo}</textarea>
		    <label>Diagnóstico:</label> 
		    <select name="diagnostico"> 
		        <option value="paradaCardiaca" ${requestScope.laudo.diagnostico == 'paradaCardiaca' ? 'selected' : ''}>Parada cardíaca</option> 
		        <option value="taquicardiaParoxistica" ${requestScope.laudo.diagnostico == 'taquicardiaParoxistica' ? 'selected' : ''}>Taquicardia paroxística</option>
		        <option value="flutterFibrilacaoAtrial" ${requestScope.laudo.diagnostico == 'flutterFibrilacaoAtrial' ? 'selected' : ''}>Flutter e fibrilacao atrial</option>
		        <option value="outras" ${requestScope.laudo.diagnostico == 'outras' ? 'selected' : ''}>Outras arritmias cardíacas</option>
		        <option value="cardiomiopatias" ${requestScope.laudo.diagnostico == 'cardiomiopatias' ? 'selected' : ''}>Cardiomiopatias</option>
		    </select>
		    <input type="hidden" name="logica" value="AvaliarLaudo"> 
		    <input type="submit" value="Enviar">
		</form>
		<c:import url="rodape.jsp" />
	</div>
</body>
</html>