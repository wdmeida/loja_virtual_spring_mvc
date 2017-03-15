<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="customTags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<customTags:pageTemplate title="Compra conclu�da">

	<sec:authentication property="principal" var="user"/>

	<div class="jumbotron">
		<h2>Compra conclu�da com sucesso</h2>
		<p class="lead text-justify">
			Obrigado por comprar na nossa loja, <strong>${user.name}</strong>. As informa��es de compra foram enviadas para seu email. 
			Fique por dentro sobre novas promo��es e novidades, sempre se mantendo atualizado e acessando nosso site.
		</p>
	</div>

</customTags:pageTemplate>