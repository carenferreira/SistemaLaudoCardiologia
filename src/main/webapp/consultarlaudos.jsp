<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="autenticacao.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Consultar Laudos</title>
</head>
<body>

<jsp:useBean id="laudos" class="cfs.laudos.cardiologia.dao.LaudoDAO"/>
<div>
	<h1>Laudos Definitivos</h1>
	<h2>Paciente</h2>
	<p>CPF: ${param.cpf}</p>
	
	<h2>Laudos</h2>
	<table>
		<thead>
			<tr>
				<th>Médico</th>
				<th>Laudo</th>
				<th>Diagnostico</th>
			</tr>
		</thead>
		<c:forEach var="laudo" items="${laudos.listaLaudosDefinitivos(param.cpf)}" varStatus="id">
			<tr>
				<td>${laudo.medico.crm }</td>
				<td>${laudo.laudo }</td>
				<td>${laudo.diagnostico }</td>
			</tr>
		</c:forEach>
	</table>
	<c:import url="rodape.jsp" />
</div>
</body>
</html>