<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>

<customTags:pageTemplate title="Cadastrar Produto">
	
	<spring:hasBindErrors name="product">
		<ul>
		<c:forEach var="error" items="${errors.allErrors}">	
			<li>${error.code}-${error.field}</li>
		</c:forEach>
		</ul>
	</spring:hasBindErrors>
	
	<div class="page-header">
		<h2>Cadastrar Produto</h2>
		<form:form action="${spring:mvcUrl('PC#save').build()}" method="post" commandName="product" enctype="multipart/form-data" cssClass="form-horizontal">
			<div class="form-group">
				<label for="titulo" class="col-sm-2 control-label">Titulo</label>
				<div class="col-sm-10">
					<form:input path="title" cssClass="form-control"/>
					<form:errors path="title"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="numeroPaginas" class="col-sm-2 control-label">N�mero de paginas</label>
				<div class="col-sm-10">
					<form:input path="pages" cssClass="form-control datepicker"/>
					<form:errors path="pages"/>
				</div>
			</div>
			<div class="form-group">
				<label for="releaseDate" class="col-sm-2 control-label">Data de lan�amento</label>
				<div class="col-sm-10">
					<form:input path="releaseDate" type="date" cssClass="form-control" id="date" name="date" placeholder="MM/DD/YYYY" />			
					<form:errors path="releaseDate"/>
				</div>
			</div>	
		
			<c:forEach items="${bookTypes}" var="bookType" varStatus="status">
				<div class="form-group">
					<label for="preco_${bookType}" class="col-sm-2 control-label">${bookType} - R$: </label>
					<div class="col-sm-10">
						<input type="text" name="prices[${status.index}].value" id="preco_${bookType}" class="form-control"/>
						<input type="hidden" name="prices[${status.index}].bookType" value="${bookType}"/>
					</div>
				</div>
			</c:forEach>
			
			<div class="form-group">
				<label for="descricao" class="col-sm-2 control-label">Descri��o</label>
				<div class="col-sm-10">
					<form:textarea path="description" rows="4" cssClass="form-control"/>
					<form:errors path="description"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="summary" class="col-sm-2 control-label">Sumario do livro</label>
				<div class="col-sm-10">
					<input type="file" name="summary" />
					<form:errors path="summaryPath"/>
				</div>
			</div>	
			<div>
				<button type="submit" class="btn btn-primary pull-right">Cadastrar</button>
			</div>
		</form:form>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			var date_input = $('input[name="date"]');
			var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "body";
			var options = {
					format: 'mm/dd/yyyy',
					container: container,
					todayHighlight: true,]
					autoclose: true,
			};
			date_input.datepicker(options);
		});
	</script>

</customTags:pageTemplate>