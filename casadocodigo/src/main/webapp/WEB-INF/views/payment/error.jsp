<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="customTags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<customTags:pageTemplate title="Compra concluída">

	<sec:authentication property="principal" var="user"/>

	<div class="jumbotron">
		<div class="alert alert-danger" role="alert">
			<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			<span class="sr-only">Error:</span>
			Ocorreu um erro com sua compra <strong>${user.name}</strong>. Verifique se as informações de pagamento estão corretas e
			tente novamente.
		</div>
	</div>

</customTags:pageTemplate>