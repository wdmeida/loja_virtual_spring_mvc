<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>

<customTags:pageTemplate title="${product.title }">
	
	<article id="${product.title}">
		
		<!-- Header infos book -->
		<div id="product-overview" class="page-header">
			<img width="280px" height="395px" src='' class="product-featured-image" alt="${product.title}">
			<h1>${product.title}</h1>

			<p class="well">
				${product.description}
			 	Veja o <a href="${product.summaryPath}" target="_blank">sum&#225;rio</a> completo do livro!
			</p>
		</div>
		<!-- Header infos book -->
		
		<!-- Form buy book -->
		<div class="page-header">
			<form:form servletRelativeAction="/shopping">
				<input type="hidden" value="${product.id}" name="productId"/>	
				<c:forEach items="${product.prices}" var="price">	
					<label class="radio-inline"> 
						<input type="radio" name="bookType" id="${product.id}-${price.bookType}" 
							value="${price.bookType}" ${price.bookType.name() == 'COMBO' ? 'checked' : ''}> ${price.bookType} - R$ ${price.value}						
					</label> 	
				</c:forEach>
				<button type="submit" class="btn btn-primary">Compre Agora!</button> 
			</form:form>
		</div>
		<!-- Form buy book -->
		
		<!-- Details book -->
		<div class="page-header">
			<h3>${product.title}</h3>
			<span>
				<p>${product.description}</p>
			</span>
		</div>

		<div class="page-header">
			<h3>Dados do livro:</h3>
			<ul>
				<li>Número de paginas: <span itemprop="numberOfPages">${product.pages}</span></li>
			</ul>
		</div>
		<!-- Details book -->
		
		<div class="page-header">
			<h3>Ajude a melhorar nossos produtos:</h3>	
			<ul>
				<li>Encontrou um erro? <a href='/submissao-errata' target='_blank'>Submeta uma errata</a></li>
			</ul>
		</div>
		
	</article>
</customTags:pageTemplate>