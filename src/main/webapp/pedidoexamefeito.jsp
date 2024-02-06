<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="autenticacao.jsp"%>
<html>
<head>
    <title>Exame Solicitado</title>
    <link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<jsp:useBean id="laudo" class="cfs.laudos.cardiologia.dao.LaudoDAO"/>
    <div>
        <h1>Exame ${requestScope.exame.exame}</h1>

        <h2>Médico solicitante</h2>
        <p>CRM: ${requestScope.exame.medico.crm}</p>
        <p>Nome: ${requestScope.exame.medico.nome}</p>

        <h2>Informações do paciente</h2>
        <p>CPF: ${requestScope.exame.paciente.cpf}</p>
        <p>Nome: ${requestScope.exame.paciente.nome}</p>
		
		<h2>Informações do exame</h2>
        <p>Exame: ${requestScope.exame.exame}</p>
        <p>Hipotese: ${requestScope.exame.hipotese}</p>
        <p>Recomendações: ${requestScope.exame.recomendacao}</p>
		<p>Data prevista de realização: <fmt:formatDate value="${requestScope.exame.dataRealizacao.time}" pattern="dd/MM/yyyy"/></p>
		
		<c:choose>
			<c:when test="${requestScope.exame.situacao eq 'Aguardando Laudo'}">
				<h2>Exame realizado</h2>
		        <p>CRM: ${requestScope.exame.medicoResitente.crm}</p>
		        <p>Nome: ${requestScope.exame.medicoResitente.nome}</p>
				<p>Data de realização: <fmt:formatDate value="${requestScope.exame.dataRealizacao.time}" pattern="dd/MM/yyyy"/></p>
				<p>Horario: ${requestScope.exame.hora} </p>
			</c:when>
			<c:when test="${requestScope.exame.situacao eq 'Laudo Realizado'}">
				<p>Data de realização: <fmt:formatDate value="${requestScope.exame.dataRealizacao.time}" pattern="dd/MM/yyyy"/></p>
				<p>Horario: ${requestScope.exame.hora} </p>
				<h2>Laudo</h2>
				<p>Médico residente:${requestScope.laudo.medico.getCrm()}</p>
				<p>Laudo: ${requestScope.laudo.laudo}</p>
				<p>Diagnostico:  ${requestScope.laudo.diagnostico}</p>
			</c:when>
		</c:choose>
				
		<c:import url="rodape.jsp" />
    </div>
</body>
</html>