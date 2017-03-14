<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>

<customTags:pageTemplate bodyClass="container" title="Produtos">
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal" var="user"/>
		<spring:message code="users.welcome" arguments="${user.name}"/>
	</sec:authorize>
	
	<div>
		${success}
	</div>
	<table class="table table-striped">
		<thead class="thead-inverse">
			<tr>
				<th>Titulo</th>
				<th>Valores</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${products}" var="product">
				<tr>
					<td><a href="${spring:mvcUrl('PC#show').arg(0,product.id).build()}">${product.title}</a></td>
					<td>
						R$ 
						<c:forEach items="${product.prices}" var="price">
							[${price.value} - ${price.bookType}]
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</customTags:pageTemplate>	
