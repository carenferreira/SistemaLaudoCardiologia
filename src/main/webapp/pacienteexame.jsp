<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="autenticacao.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Paciente Exame</title>
</head>
<body>
<jsp:useBean id="exames" class="cfs.laudos.cardiologia.dao.ExameDAO"/>
<jsp:useBean id="user" class="cfs.laudos.cardiologia.dao.UsuarioDAO"/>
<div>

	<h1>Informações do paciente</h1>
	<p>CPF: ${requestScope.paciente.cpf}</p>
	<p>Nome: ${requestScope.paciente.nome}</p>
	<p>Email: ${requestScope.paciente.email}</p>
	<p>Sexo: ${requestScope.paciente.sexo}</p>
	<p>Idade: ${requestScope.paciente.idade}</p>
	<p>Data nascimento: <fmt:formatDate value="${requestScope.paciente.dataNascimento.time}"	pattern="dd/MM/yyyy"/></p>
	
	<h2>Exames</h2>
	<table>
		<thead>
			<tr>
				<th>Médico</th>
				<th>Exame</th>
				<th>Data</th>
				<th>Situação</th>
				<c:if test="${user.recuperaUsuario(sessionScope.nome).getTipo() == 3 || 
				user.recuperaUsuario(sessionScope.nome).getTipo() == 2}">
					<th>Próximo passo</th>
				</c:if>
			</tr>
		</thead>
		<c:forEach var="exame" items="${exames.listaExamesPorPaciente(requestScope.paciente.cpf)}" varStatus="id">
			<tr>
				<td>${exame.medico.crm}</td>
				<td>${exame.exame}</td>
				 <td>
					
						<fmt:formatDate value="${exame.data.time}" pattern="dd/MM/yyyy"/>
				
				</td> 
				<td>${exame.situacao}</td>
				<c:if test="${user.recuperaUsuario(sessionScope.nome).getTipo() == 3}">
					<td>
						<c:choose>
							<c:when
								test="${exame.situacao eq 'Aguardando Exame'}">
								<a href="controladora?id=${exame.id}&logica=RecuperaExame">Realizar Exame</a>
							</c:when>
							<c:when test="${exame.situacao eq 'Aguardando Laudo'}">
								<a href="controladora?id=${exame.id}&logica=RecuperaExame">Gerar Laudo</a>
							</c:when>
							<c:otherwise>
								Laudo Realizado
							</c:otherwise>
						</c:choose> 
					</td>
				</c:if>
				<c:if test="${user.recuperaUsuario(sessionScope.nome).getTipo() == 2}">
					<td>
						<c:choose>
							<c:when
								test="${exame.situacao eq 'Laudo Realizado'}">
								<a href="controladora?id=${exame.id}&logica=RecuperaExame">Avaliar laudo</a>
							</c:when>
							<c:otherwise>
								${exame.situacao}
							</c:otherwise>
						</c:choose> 
					</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${user.recuperaUsuario(sessionScope.nome).getTipo() == 1}">
	<div class="buttons">
     
            <a href="pedidoexame.jsp?cpf=${requestScope.paciente.cpf}">Novo Exame</a>
            <a href="consultarlaudos.jsp?cpf=${requestScope.paciente.cpf}">Consultar Laudos</a>
        
    </div>
    </c:if>
	<c:import url="rodape.jsp" />
</div>
</body>
</html>