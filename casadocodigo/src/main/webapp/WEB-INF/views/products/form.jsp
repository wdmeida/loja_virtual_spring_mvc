<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Cadastro de produtos</title>
	</head>
	
	<body>
		
		<form:form action="${spring:mvcUrl('PC#save').build()}" method="post" 
				   commandName="product" enctype="multipart/form-data">
			<div>
				<label for="title">Titulo</label>
				<form:input path="title"/>
				<form:errors path="title"/>
			</div>
			
			<div>
				<label for="description">Descrição</label>
				<form:textarea path="description" rows="10" cols="20" />
				<form:errors path="description" />
			</div>
			
			<div>
				<label for="pages">Número de páginas</label>
				<form:input path="pages"/>
				<form:errors path="pages"/>
			</div>
			
			<div>
				<label for="releaseDate">Data de lançamento</label>
				<input type="date" name="releaseDate"/>
				<form:errors path="releaseDate"/>
			</div>
			
			<c:forEach items="${types }" var="bookType" varStatus="status">
				<div>
					<label for="price_${bookType }">${bookType }</label>
					<input type="text" name="prices[${status.index }].value" id="price_${bookType }"/>
					<input type="hidden" name="prices[${status.index }].bookType" value="${bookType }"/>
				</div>
			</c:forEach>
			
			<div>
				<label for="summary">Sumário do livro</label>
				<input type="file" name="summary"/>
				<form:errors path="summaryPath"/>
			</div>
			
			<div>
				<input type="submit" value="Enviar">
			</div>
		</form:form>
		
	</body>
</html>