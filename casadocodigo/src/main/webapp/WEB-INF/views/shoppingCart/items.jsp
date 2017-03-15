<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>

<fmt:message key="shoppingCart.title" var="title" />
<customTags:pageTemplate title="${title}">
	
	<jsp:attribute name="extraScripts">
		<script>
			$(function() {
				$('#checkout').click(function() {
					_gaq.push([ '_trackPageview', '/checkout/finalizaCompra' ]);
				});
			});
		</script>
	</jsp:attribute>
	
	<jsp:body>
		
		<div class="page-header">
			<h2 id="cart-title">Seu carrinho de compras</h2>
		</div>
		
		<div class="table page-header">
			<table class="table table-hover">
				
				<thead>
					<tr>
						<th>Item - Tipo</th>
						<th>Preço</th>
						<th>Quantidade</th>
						<th>Total</th>
						<th>Excluir Item</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${shoppingCart.list}" var="item">
						<tr>
							<td>
								${item.product.title} - ${item.bookType}
							</td>
							<td>
								R$ ${item.price}
							</td>
							<td>
								<input type="number" min="0" readonly="readonly" value="${shoppingCart.getQuantity(item)}">
							</td>
							<td>
								R$ ${shoppingCart.getTotal(item)}
							</td>
							<td class="remove-item">
								<form:form method="post" action="${spring:mvcUrl('SCC#remove').arg(0,item.product.id).arg(1,item.bookType).build()}">
									<button type="button" class="btn btn-danger">Remover Item</button>
								</form:form>
							</td>
						</tr>
					</c:forEach>
	
				</tbody>
				<tfoot>
					<tr>
						<td></td>
						<td class="numeric-cell">
							R$ ${shoppingCart.total}
						</td>
						<td></td>
						<td></td>
						<td>
							<form:form action="${spring:mvcUrl('PC#checkout').build()}" method="post">
								<button type="submit" class="checkout btn btn-success" name="checkout" id="checkout">Finalizar compra</button>
							</form:form>
						</td>
					</tr>
				</tfoot>
			</table>
			
		</div>
	</jsp:body>	
</customTags:pageTemplate>