<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sistema de Laudos de Cardiologia</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<div>
	<h1>Sistema de Laudos de Cardiologia</h1>
	<form action="controladora" method="post">
		Login: <input type="text" name="login" /> <br />
		Senha: <input type="password" name="senha"> <br />
		<input type="hidden" name="logica" value="EfetuaLogin">
		<input type="submit" value="Entrar">
	</form>
</div>
</body>
</html>