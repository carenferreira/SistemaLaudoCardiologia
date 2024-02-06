<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="autenticacao.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Pedido Exame</title>
</head>
<body>
<div>
<h1>Pedido de exame</h1>
	
	<p name="aviso">${requestScope.aviso}</p>
	
	<form action="controladora" method="post">
	Paciente: <input name="cpf" type="text" value="${param.cpf}" readonly>
	Médico: <input  name="medico" type="text" value="${sessionScope.nome}" readonly> <br/>
	<label>Exame:</label> 
	    <select name="exame" id="exame"> 
	        <option value="ecocardiograma">Ecocardiograma</option> 
	        <option value="eletrocardiograma">Eletrocardiograma</option>  
	    </select>
	 <br>
	
	<label>Hipótese diagnóstica:</label> 
	    <select name="hipotese" id="exame"> 
	        <option value="paradaCardiaca">Parada cardíaca</option> 
	        <option value="taquicardiaParoxistica">Taquicardia paroxística</option>
	        <option value="flutterFibrilacaoAtrial">Flutter e fibrilacao atrial</option>
	        <option value="outras">Outras arritmias cardíacas</option>
	        <option value="cardiomiopatias">Cardiomiopatias</option>  
	    </select>
	    
	<br>
	
	<label>Recomendações para realização do exame:</label><br>
		<textarea rows="5" cols="33" name="recomendacao"></textarea>
	<input type="hidden" name="logica" value="PedidoExame"> 
	<input type="submit" value="Solicitar">
	</form>
		<c:import url="rodape.jsp" />
</div>

</body>
</html>