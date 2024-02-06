<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="autenticacao.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="user" class="cfs.laudos.cardiologia.dao.UsuarioDAO"/>
	<c:if test="${user.recuperaUsuario(sessionScope.nome).getTipo() == 1}">
		<a id="menuprincipal" href="menuprincipalmedico.jsp">Menu
			Principal</a>
	</c:if>
	<c:if test="${user.recuperaUsuario(sessionScope.nome).getTipo() == 2}">
		<a id="menuprincipal" href="menuprincipaldocente.jsp">Menu
			Principal</a>
	</c:if>
	<c:if test="${user.recuperaUsuario(sessionScope.nome).getTipo() == 3}">
		<a id="menuprincipal" href="menuprincipalresidente.jsp">Menu
			Principal</a>
	</c:if>
	<c:if test="${sessionScope.status == true}">
	<a id="sair" href=controladora?logica=EfetuaLogout>Sair</a>
	</c:if>
</body>
</html>